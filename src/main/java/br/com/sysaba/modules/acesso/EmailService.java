package br.com.sysaba.modules.acesso;

import br.com.sysaba.core.enums.TipoAssinaturaEnum;
import br.com.sysaba.modules.acesso.dto.EmailDTO;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class EmailService {

    @Value("${mailtrap.key}")
    private String mailtrapKey;

    private final UsuarioService usuarioService;

    public EmailService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Transactional
    public String forget(String email) throws IOException {

        Usuario usuario = usuarioService.getByEmail(email);

        if (!TipoAssinaturaEnum.ASSINANTE.equals(usuario.getAssinatura().getTipoAssinatura())) {
            return "NAO_ASSINANTE";
        }

        usuario.setRedefinirSenhaKey(UUID.randomUUID());
        Usuario usuarioUpdated = usuarioService.save(usuario);

        String linkRedefinirSenha = criarLinkRedefinirSenha(usuarioUpdated.getUsuarioId(), usuarioUpdated.getRedefinirSenhaKey());

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        EmailDTO.From from = new EmailDTO.From();
        from.setEmail("hello@demomailtrap.com");
        from.setName("SysABA");

        EmailDTO.To to = new EmailDTO.To();
        to.setEmail("sysaba.suporte@gmail.com");

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setFrom(from);
        emailDTO.setTo(List.of(to));
        emailDTO.setSubject("Recupere sua senha");
        emailDTO.setText("<p>Redefina sua senha:" + linkRedefinirSenha +"</p>");
        emailDTO.setCategory("Integration Test");

        Gson gson = new Gson();
        String json = gson.toJson(emailDTO);

        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("https://send.api.mailtrap.io/api/send")
                .method("POST", body)
                .addHeader("Authorization", "Bearer 617b00ef80cd4227c519abadd4aa1aa2")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        return response.code() == 200 ? "OK" : "NO_OK";
    }

    private String criarLinkRedefinirSenha(UUID usuarioId, UUID randomKey) {
        String hashBase64 = Arrays.toString(Base64.getEncoder().encode(String.format("?uuid={}&key={}", usuarioId, randomKey).getBytes()));
        return String.format("{}/reset-password/{}", "http://localhost:9000", hashBase64);
    }
}
