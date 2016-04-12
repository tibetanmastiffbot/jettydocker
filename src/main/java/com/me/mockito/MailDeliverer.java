package com.me.mockito;

public class MailDeliverer {

	public MailDeliverer() {
		super();
		// to demonstrate the mock injection actually worked.
		externalMailSystem = null;
		timestamper = null;
		System.out.println(this.externalMailSystem);
	}

	private ExternalMailSystem externalMailSystem;
	private Timestamper timestamper;

	public void deliver(String address, String body) {
		System.out.println(this.externalMailSystem + " null ? " + Boolean.toString((this.externalMailSystem == null)));
		Email email = new Email();
		email.setBody(body);
		applyUserAndDomainUsingProvidedAddress(address, email);
		email.setTimestamp(timestamper.stamp());
		externalMailSystem.send(email);
	}

	private void applyUserAndDomainUsingProvidedAddress(String address, Email email) {
		// TODO - refactor this into the Email class (constructor? smart
		// accessor?)
		String[] addressComponents = address.split("@");
		email.setUser(addressComponents[0]);
		email.setDomain(addressComponents[1]);
	}
}
