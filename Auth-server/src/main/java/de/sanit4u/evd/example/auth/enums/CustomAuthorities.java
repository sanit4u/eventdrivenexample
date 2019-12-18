package de.sanit4u.evd.example.auth.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * This enum is responsible to define the authorities of our auth service and it
 * implements the GrantedAuthority interface which represents an authority
 * granted to an Authentication object
 * 
 *
 */
public enum CustomAuthorities implements GrantedAuthority {
	ROLE_USER;

	@Override
	public String getAuthority() {
		return name();
	}
}