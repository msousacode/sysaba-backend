package br.com.sysaba.core.security.filter;

import br.com.sysaba.core.security.config.RsaKeyConfigProperties;
import br.com.sysaba.core.security.config.TenantAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.UUID;

@Component
public class TenantAuthorizationFilter extends GenericFilterBean {


    private final RsaKeyConfigProperties rsaKeyConfigProperties;

    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final String HEADER_PREFIX = "Bearer ";

    public TenantAuthorizationFilter(RsaKeyConfigProperties rsaKeyConfigProperties) {
        this.rsaKeyConfigProperties = rsaKeyConfigProperties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String authorizationHeader = resolveToken((HttpServletRequest) request);

        if (StringUtils.hasText(authorizationHeader)) {
            TenantAuthenticationToken tenantAuthenticationToken = getTenantAuthenticationToken(
                    authorizationHeader);
            SecurityContextHolder.getContext().setAuthentication(tenantAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private TenantAuthenticationToken getTenantAuthenticationToken(String authorizationHeader) {
        TenantAuthenticationToken tenantAuthenticationToken = null;

        try {

            JwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeyConfigProperties.publicKey()).build();

            // Decodifica o token
            Jwt jwsClaims = jwtDecoder.decode(authorizationHeader);
            String email = (String) jwsClaims.getClaims().get("subject");

            String tenantId = (String) jwsClaims.getClaims().get("tenantId");
            tenantAuthenticationToken = new TenantAuthenticationToken(email, UUID.fromString(tenantId));
        } catch (Exception e) {
            log.error("Problems with JWT", e);
            throw new AuthenticationException("Problems with JWT", e.getCause()) {
            };
        }

        return tenantAuthenticationToken;
    }
}