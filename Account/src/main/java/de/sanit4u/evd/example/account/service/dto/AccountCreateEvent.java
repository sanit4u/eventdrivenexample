package de.sanit4u.evd.example.account.service.dto;

public class AccountCreateEvent {
	private String userName;

	private String email;

	public AccountCreateEvent() {
	}

	public AccountCreateEvent(String userName, String email) {
		super();
		this.userName = userName;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

}
