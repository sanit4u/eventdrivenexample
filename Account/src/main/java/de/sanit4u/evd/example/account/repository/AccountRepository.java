package de.sanit4u.evd.example.account.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import de.sanit4u.evd.example.account.domain.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

	Account findByName(String name);

}
