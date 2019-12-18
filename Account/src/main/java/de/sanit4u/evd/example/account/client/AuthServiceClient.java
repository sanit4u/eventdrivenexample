package de.sanit4u.evd.example.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.sanit4u.evd.example.account.service.dto.UserDto;
import de.sanit4u.evd.example.account.service.dto.UserRegistrationDto;

@FeignClient(name = "auth-server")
public interface AuthServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/uaa/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	UserDto createUser(UserRegistrationDto user);

}
