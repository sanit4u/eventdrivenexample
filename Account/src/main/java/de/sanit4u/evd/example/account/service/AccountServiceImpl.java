package de.sanit4u.evd.example.account.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import de.sanit4u.evd.example.account.client.AuthServiceClient;
import de.sanit4u.evd.example.account.domain.Account;
import de.sanit4u.evd.example.account.domain.User;
import de.sanit4u.evd.example.account.repository.AccountRepository;
import de.sanit4u.evd.example.account.service.dto.UserRegistrationDto;

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger log = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private NotificationServiceClient notificationClient;

	@Autowired
	private AuthServiceClient authClient;

	@Autowired
	private AccountRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Account findByName(String accountName) {
		Assert.hasLength(accountName);
		return repository.findByUserName(accountName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Account> findAll() {
		return repository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Account create(User user) {

		Account existing = repository.findByUserName(user.getUsername());
		Assert.isNull(existing, "account already exists: " + user.getUsername());

		authClient.createUser(user2UserRegistrationDto(user));

		Account account = new Account();
		account.setUserName(user.getUsername());
		account.setLastSeen(new Date());
		account.setEmail(user.getEmail());

		repository.save(account);

		log.info("new account has been created: " + account.getUserName());

//		notificationClient.sendWelcomeEmail(user.getUsername());

		return account;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveChanges(String name, Account update) {

		Account account = repository.findByUserName(name);
		Assert.notNull(account, "can't find account with name " + name);

		account.setEmail(update.getEmail());
		account.setNote(update.getNote());
		account.setLastSeen(new Date());
		repository.save(account);

		log.debug("account {} changes has been saved", name);

	}

	private UserRegistrationDto user2UserRegistrationDto(User user) {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		userRegistrationDto.setUsername(user.getUsername());
		userRegistrationDto.setPassword(user.getPassword());
		return userRegistrationDto;
	}
}
