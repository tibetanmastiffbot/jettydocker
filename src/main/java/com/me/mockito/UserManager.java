package com.me.mockito;

public interface UserManager {
	void setUserService(UserService userService);

	User findUser(String userName);
}
