package de.sanit4u.evd.example.notification.service;

import java.io.IOException;

import javax.mail.MessagingException;

import de.sanit4u.evd.example.notification.domain.NotificationType;
import de.sanit4u.evd.example.notification.domain.Recipient;

public interface EmailService {

	void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException, IOException;

}
