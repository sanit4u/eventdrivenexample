package de.sanit4u.evd.example.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import de.sanit4u.evd.example.auth.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	User findByUsername(String username);
}
