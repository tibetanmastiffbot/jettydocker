package com.me.mockito;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;

@RunWith(Parameterized.class)
public class BradsParameterizedMockTest {

	private UserManager userManager = new UserManagerImpl();

	@Mock
	private UserService userService;

	private String userName = null;
	private User expectedUser = null;

	public BradsParameterizedMockTest(String userName, User user) {
		this.userName = userName;
		this.expectedUser = user;
	}

	@Parameters
	public static List<Object[]> balanceRates() {
		return Arrays.asList(new Object[][] { { "user1", new User("user1") }, { "user2", new User("user2") } });
	}

	@Before
	public void setUp() {
		initMocks(this);
		userManager.setUserService(userService);
	}

	@Test
	public void shouldReturnExpectedUser() {
		// given
		when(userService.findUserByName(userName)).thenReturn(expectedUser);

		// when
		User user = userManager.findUser(userName);

		// then
		assertThat(user, is(equalTo(expectedUser)));
		verify(userService).findUserByName(userName);
		verifyNoMoreInteractions(userService);
	}
}