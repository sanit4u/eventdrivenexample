package de.sanit4u.evd.example.notification.service.impl;

import java.io.IOException;
import java.text.MessageFormat;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import de.sanit4u.evd.example.notification.domain.NotificationType;
import de.sanit4u.evd.example.notification.domain.Recipient;
import de.sanit4u.evd.example.notification.service.EmailService;

@Service
@RefreshScope
public class EmailServiceImpl implements EmailService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;

	private void defaultSend(NotificationType type, Recipient recipient, String attachment) {
		log.info("---------DEFAULT SEND HIT--------");
		System.out.println("---------DEFAULT SEND HIT--------");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@HystrixCommand(fallbackMethod = "defaultSend")
	public void send(NotificationType type, Recipient recipient, String attachment)
			throws MessagingException, IOException {

		final String subject = env.getProperty(type.getSubject());
		final String text = MessageFormat.format(env.getProperty(type.getText()), recipient.getAccountName());

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(recipient.getEmail());
		helper.setSubject(subject);
		helper.setText(text);

		if (StringUtils.hasLength(attachment)) {
			helper.addAttachment(env.getProperty(type.getAttachment()), new ByteArrayResource(attachment.getBytes()));
		}

		mailSender.send(message);

		log.info("{} email notification has been send to {}", type, recipient.getEmail());
	}
}
