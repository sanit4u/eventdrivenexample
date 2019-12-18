package de.sanit4u.evd.example.auth.config.mongo.changelogs;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;

import de.sanit4u.evd.example.auth.domain.AuthClientDetails;
import de.sanit4u.evd.example.auth.domain.User;
import de.sanit4u.evd.example.auth.enums.CustomAuthorities;

@ChangeLog
public class InitialValuesChangeLog {

	@ChangeSet(order = "001", id = "insertBrowserClientDetails", author = "Santosh")
	public void insertBrowserClientDetails(MongoTemplate mongoTemplate) {
		AuthClientDetails browserClientDetails = new AuthClientDetails();
		browserClientDetails.setClientId("browser");
		browserClientDetails.setClientSecret("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");// hash generated from 1234 by BcryptPasswordEncoder
		browserClientDetails.setScopes("ui");
		browserClientDetails.setGrantTypes("refresh_token,password");

		mongoTemplate.save(browserClientDetails);
	}

	@ChangeSet(order = "002", id = "insertUserToTestAuthentication", author = "Santosh")
	public void insertUserToTestAuthentication(MongoTemplate mongoTemplate) {
		Set<CustomAuthorities> authorities = new HashSet<>();
		authorities.add(CustomAuthorities.ROLE_USER);

		User user = new User();
		user.setActivated(true);
		user.setAuthorities(authorities);
		user.setPassword("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
		user.setUsername("randomuser");

		mongoTemplate.save(user);
	}

	@ChangeSet(order = "003", id = "insertAccountServiceClientDetails", author = "Santosh")
	public void insertAccountServiceClientDetails(MongoTemplate mongoTemplate) {
		AuthClientDetails accountServiceClientDetails = new AuthClientDetails();
		accountServiceClientDetails.setClientId("account-service");
		accountServiceClientDetails.setClientSecret("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
		accountServiceClientDetails.setScopes("server");
		accountServiceClientDetails.setGrantTypes("refresh_token,client_credentials");

		mongoTemplate.save(accountServiceClientDetails);
	}

}
