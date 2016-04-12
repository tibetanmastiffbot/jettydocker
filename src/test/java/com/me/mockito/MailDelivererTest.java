package com.me.mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MailDelivererTest {

	@InjectMocks
	private MailDeliverer subject = new MailDeliverer();

	@Mock
	private ExternalMailSystem externalMailSystem;
	@Mock
	private Timestamper timestamper;

	@Captor
	private ArgumentCaptor<Email> emailCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void sendsEmailByConstructingEmailObject() {
		String expectedUser = "tim";
		String expectedDomain = "wingfield.com";
		String expectedBody = "Hi Tim!";

		subject.deliver(expectedUser + "@" + expectedDomain, expectedBody);

		verify(externalMailSystem).send(emailCaptor.capture());
		Email email = emailCaptor.getValue();
		assertThat(email.getUser(), is(expectedUser));
		assertThat(email.getDomain(), is(expectedDomain));
		assertThat(email.getBody(), is(expectedBody));
	}

	@Test
	public void setsTheTimestampOnTheEmail() {
		Date expected = new Date(8932l);
		when(timestamper.stamp()).thenReturn(expected);

		subject.deliver("a@b", null);

		verify(externalMailSystem).send(emailCaptor.capture());
		Email email = emailCaptor.getValue();
		assertThat(email.getTimestamp(), is(expected));
	}

}
