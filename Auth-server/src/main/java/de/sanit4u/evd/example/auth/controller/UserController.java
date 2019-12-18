package de.sanit4u.evd.example.auth.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.sanit4u.evd.example.auth.domain.User;
import de.sanit4u.evd.example.auth.service.UserService;
import de.sanit4u.evd.example.auth.service.dto.UserDto;
import de.sanit4u.evd.example.auth.service.dto.UserRegistrationDto;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getUser(Principal principal) {
		return principal;
	}

	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(method = RequestMethod.POST)
	public UserDto createUser(@Valid @RequestBody UserRegistrationDto userRegistration) {
		User savedUser = userService.create(toUser(userRegistration));
		return toDto(savedUser);
	}

	private UserDto toDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		return userDto;
	}

	private User toUser(UserRegistrationDto userRegistration) {
		User user = new User();
		user.setUsername(userRegistration.getUsername());
		user.setPassword(userRegistration.getPassword());
		return user;
	}
}
