package de.sanit4u.evd.example.account.binder;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;


public interface AccountBinding {
	String ACCOUNT_CREATE_OUTPUT = "accountcreateoutput";

	@Output(ACCOUNT_CREATE_OUTPUT)
	MessageChannel accountCreateOutput();
}
