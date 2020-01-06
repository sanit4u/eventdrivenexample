package de.sanit4u.evd.example.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.sanit4u.evd.example.notification.service.RecipientService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private RecipientService recipientService;

	@RequestMapping(path = "/notifications/welcome/{name}", method = RequestMethod.GET)
	public Object getAccountByName(@PathVariable String name) {
		return recipientService.sendWelcomeEmail(name);
	}

}