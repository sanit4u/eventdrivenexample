package de.sanit4u.evd.example.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "notification-service")
public interface NotificationServiceClient {

	@RequestMapping(method = RequestMethod.GET, value = "/notifications/welcome/{accountName}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void sendWelcomeEmail(@PathVariable("accountName") String accountName);

}
