package de.sanit4u.evd.example.notification.domain;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sun.istack.NotNull;

import de.sanit4u.evd.example.validator.CustomEmailValidator;

@Document(collection = "recipients")
public class Recipient {

	@Id
	private String accountName;

	@NotNull
	//	@Email
	@CustomEmailValidator
	private String email;

	@Valid
	private Map<NotificationType, NotificationSettings> scheduledNotifications;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<NotificationType, NotificationSettings> getScheduledNotifications() {
		return scheduledNotifications;
	}

	public void setScheduledNotifications(Map<NotificationType, NotificationSettings> scheduledNotifications) {
		this.scheduledNotifications = scheduledNotifications;
	}

	@Override
	public String toString() {
		return "Recipient{" + "accountName='" + accountName + '\'' + ", email='" + email + '\'' + '}';
	}
}
