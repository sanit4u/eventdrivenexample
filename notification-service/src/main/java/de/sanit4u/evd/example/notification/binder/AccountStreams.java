package de.sanit4u.evd.example.notification.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AccountStreams {

	String ACCOUNT_CREATE_INPUT = "accountcreateinput";
	String PROCESSED_ACCOUNT_CREATE_OUTPUT = "processedaccountcreateoutput";

	@Input(ACCOUNT_CREATE_INPUT)
	MessageChannel accountCreateInput();

	@Output(PROCESSED_ACCOUNT_CREATE_OUTPUT)
	MessageChannel processedAccountCreateOutput();
}
