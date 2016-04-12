package com.me.mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

public class MockitoTutorial {
	@Test
	public void iterator_will_return_hello_world() {
		// arrange
		Iterator<String> i = Mockito.mock(Iterator.class);
		Mockito.when(i.next()).thenReturn("Hello").thenReturn("World");
		// act
		String result = i.next() + " " + i.next();
		// assert
		Assert.assertEquals("Hello World", result);
	}

	@Test
	public void with_arguments() {
		Comparable<String> c = Mockito.mock(Comparable.class);
		Mockito.when(c.compareTo("Test")).thenReturn(1);
		Assert.assertEquals(1, c.compareTo("Test"));
	}

	@Test
	public void with_unspecified_arguments() {
		Comparable c = Mockito.mock(Comparable.class);
		Mockito.when(c.compareTo(Matchers.anyInt())).thenReturn(-1);
		Assert.assertEquals(-1, c.compareTo(5));
	}

	@Test(expected = IOException.class)
	public void OutputStreamWriter_rethrows_an_exception_from_OutputStream() throws IOException {
		OutputStream mock = Mockito.mock(OutputStream.class);
		OutputStreamWriter osw = new OutputStreamWriter(mock);
		Mockito.doThrow(new IOException()).when(mock).close();
		osw.close();
	}

	@Test
	public void OutputStreamWriter_Closes_OutputStream_on_Close() throws IOException {
		OutputStream mock = Mockito.mock(OutputStream.class);
		OutputStreamWriter osw = new OutputStreamWriter(mock);
		osw.close();
		Mockito.verify(mock).close();
	}

	@Test
	public void OutputStreamWriter_Buffers_And_Forwards_To_OutputStream() throws IOException {
		OutputStream mock = Mockito.mock(OutputStream.class);
		OutputStreamWriter osw = new OutputStreamWriter(mock);
		osw.write('a');
		osw.flush();
		// can't do this as we don't know how long the array is going to be
		// verify(mock).write(new byte[]{'a'},0,1);

		BaseMatcher<byte[]> arrayStartingWithA = new BaseMatcher<byte[]>() {

			@Override
			public void describeTo(Description description) {
				// nothing
			}

			// check that first character is A
			@Override
			public boolean matches(Object item) {
				byte[] actual = (byte[]) item;
				return actual[0] == 'a';
			}

		};
		// check that first character of the array is A,
		// and that the other two arguments are 0 and 1
		Mockito.verify(mock).write(Mockito.argThat(arrayStartingWithA), Mockito.eq(0), Mockito.eq(1));
	}
}
