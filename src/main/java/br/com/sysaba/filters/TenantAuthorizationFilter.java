package br.com.sysaba.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class TenantAuthorizationFilter extends GenericFilterBean {

    private static final SessionFactory sessionFactory = null;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession httpSession = request.getSession();

        // Obter ou definir o tenantId na sessão
        String tenantId = (String) httpSession.getAttribute("tenantId");
        if (tenantId == null) {
            tenantId = "tenant_id"; // Setar tenant ID padrão ou obtê-lo de outra fonte
            httpSession.setAttribute("tenantId", tenantId);
        }

        // Abrir a sessão do Hibernate e configurar o tenantFilter com o tenantId
        Session hibernateSession = sessionFactory.openSession();
        hibernateSession.enableFilter("tenantFilter").setParameter("tenantId", tenantId);

        try {
            // Executar a cadeia de filtros
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            // Fechar a sessão do Hibernate para evitar vazamentos de recursos
            hibernateSession.close();
        }
    }
}
