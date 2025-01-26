package br.com.sysaba.modules.acesso;

import br.com.sysaba.core.enums.TipoAssinaturaEnum;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
public class EmailService {

    @Value("${sendgrid.key}")
    private String sendgridKey;

    @Value("${sendgrid.host.url}")
    private String hostUrl;

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

        String templateId = "d-9fcb9fd042ba463ba0a8eb88d32f7d7e"; // Substitua pelo seu Template ID

        Email from = new Email("sysaba.suporte@gmail.com");
        Email to = new Email(email);

        // Criação do e-mail usando um template
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setTemplateId(templateId);
        Personalization personalization = new Personalization();
        personalization.addTo(to);

        // Adiciona a substituição do link no template
        personalization.addDynamicTemplateData("link", linkRedefinirSenha);
        mail.addPersonalization(personalization);

        // Envio do e-mail através da API SendGrid
        SendGrid sg = new SendGrid(sendgridKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sg.api(request);

        // Retorna status da operação
        return (response.getStatusCode() == 200 || response.getStatusCode() == 202) ? "OK" : "NO_OK";

    }

    private String criarLinkRedefinirSenha(UUID usuarioId, UUID randomKey) {
        return String.format("%s/reset-password/?u=%s&k=%s", hostUrl, usuarioId, randomKey);
    }
}
