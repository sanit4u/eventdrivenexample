package de.sanit4u.evd.example.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import de.sanit4u.evd.example.auth.domain.AuthClientDetails;

@Repository
public interface AuthClientRepository extends MongoRepository<AuthClientDetails, String> {

	Optional<AuthClientDetails> findByClientId(String clientId);
}
