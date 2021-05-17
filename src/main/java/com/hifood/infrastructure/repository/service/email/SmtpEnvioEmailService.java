package com.hifood.infrastructure.repository.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.hifood.core.email.EmailProperties;
import com.hifood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private Configuration freemarkerConfig;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			var corpo = processarTemplate(mensagem);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Nao foi possivel enviar o e-mail", e);
		}
	}

	private String processarTemplate(Mensagem mensagem) {
		try {
			var template = freemarkerConfig.getTemplate(mensagem.getCorpo());

			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Nao foi possivel carregar o template de email", e);
		}
	}

}
