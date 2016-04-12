package com.me.mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AddressSplitterTest {

	@InjectMocks
	private AddressSplitter subject = new AddressSplitter();

	@Mock
	private AddressInputQueue addressInputQueue;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void splitsAddressesByComma() {
		when(addressInputQueue.next()).thenReturn("jim@weirich.com,kent@beck.com");

		List<String> result = subject.split();

		assertThat(result, hasItems("jim@weirich.com", "kent@beck.com"));
	}

}
