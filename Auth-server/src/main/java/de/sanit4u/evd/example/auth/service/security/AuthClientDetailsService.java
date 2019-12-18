package de.sanit4u.evd.example.auth.service.security;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import de.sanit4u.evd.example.auth.repository.AuthClientRepository;

@Service
public class AuthClientDetailsService implements ClientDetailsService {

	private final AuthClientRepository authClientRepository;

	public AuthClientDetailsService(AuthClientRepository authClientRepository) {
		this.authClientRepository = authClientRepository;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) {
		return authClientRepository.findByClientId(clientId).orElseThrow(IllegalArgumentException::new);
	}
}
