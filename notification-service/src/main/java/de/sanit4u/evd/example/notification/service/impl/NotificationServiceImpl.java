package de.sanit4u.evd.example.notification.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import de.sanit4u.evd.example.notification.domain.NotificationType;
import de.sanit4u.evd.example.notification.domain.Recipient;
import de.sanit4u.evd.example.notification.service.EmailService;
import de.sanit4u.evd.example.notification.service.NotificationService;
import de.sanit4u.evd.example.notification.service.RecipientService;

@Service
public class NotificationServiceImpl implements NotificationService {

	private final Logger log = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private AccountServiceClient client;

	@Autowired
	private RecipientService recipientService;

	@Autowired
	private EmailService emailService;

	@Override
	@Scheduled(cron = "${remind.cron}")
	public void sendRemindNotifications() {

		final NotificationType type = NotificationType.REMIND;

		List<Recipient> recipients = recipientService.findReadyToNotify(type);
		log.info("found {} recipients for remind notification", recipients.size());

		recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
			try {
				emailService.send(type, recipient, null);
				recipientService.markNotified(type, recipient);
			} catch (Throwable t) {
				log.error("an error during remind notification for {}", recipient, t);
			}
		}));
	}
}
