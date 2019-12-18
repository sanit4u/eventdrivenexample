package de.sanit4u.evd.example.auth.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.sanit4u.evd.example.auth.domain.User;
import de.sanit4u.evd.example.auth.enums.CustomAuthorities;
import de.sanit4u.evd.example.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public User create(User user) {

		Optional<User> existing = userRepository.findByUsername(user.getUsername());
		existing.ifPresent((x) -> {
			throw new IllegalArgumentException("user already exists: " + x.getUsername());
		});

		hashPassword(user);

		// create user with role as user
		assignRole(user);

		User savedEntity = userRepository.save(user);

		log.info("new user has been created: {}", user.getUsername());
		return savedEntity;
	}

	private void hashPassword(User user) {
		String hash = passwordEncoder.encode(user.getPassword());
		user.setPassword(hash);
	}

	private void assignRole(User user) {
		Set<CustomAuthorities> authorities = new HashSet<>();
		authorities.add(CustomAuthorities.ROLE_USER);
		user.setAuthorities(authorities);
	}
}
