package com.me.mockito;

import static java.util.Arrays.asList;

import java.util.List;

public class AddressSplitter {

	private AddressInputQueue addressInputQueue;

	public List<String> split() {
		return asList(addressInputQueue.next().split(","));
	}

}
