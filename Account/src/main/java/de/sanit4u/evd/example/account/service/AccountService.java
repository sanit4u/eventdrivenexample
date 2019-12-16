package de.sanit4u.evd.example.account.service;

import de.sanit4u.evd.example.account.domain.Account;
import de.sanit4u.evd.example.account.domain.User;

public interface AccountService {

	/**
	 * Finds account by given name
	 *
	 * @param accountName
	 * @return found account
	 */
	Account findByName(String accountName);

	/**
	 * Finds all accounts
	 *
	 * @return all accounts
	 */
	Iterable<Account> findAll();

	/**
	 * Checks if account with the same name already exists Invokes Auth Service user
	 * creation Creates new account with default parameters
	 *
	 * @param user
	 * @return created account
	 */
	Account create(User user);

	/**
	 * Validates and applies incoming account updates Invokes Statistics Service
	 * update
	 *
	 * @param name
	 * @param update
	 */
	void saveChanges(String name, Account update);
}
