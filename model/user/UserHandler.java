package model.user;

import java.util.List;
import java.util.Map;

public class UserHandler {
	private Map<Class<? extends User>, List<User>> listOfUsersAccounts;

	public UserHandler() {
		throw new UnsupportedOperationException();
	}

	public void buildListOfBorrowers(Map<String, List<Map<String, Object>>> description) {
		throw new UnsupportedOperationException();
	}

	public void buildListOfTeachers(List<Map<String, Object>> description) {
		throw new UnsupportedOperationException();
	}

	public void buildListOfStudents(List<Map<String, Object>> description) {
		throw new UnsupportedOperationException();
	}

	public Map<Class, List<User>> getBorrowerList() {
		throw new UnsupportedOperationException();
	}

	public Class<? extends User> findUser(String userID) {
		throw new UnsupportedOperationException();
	}

	public User connectUser(String potentialID) {
		throw new UnsupportedOperationException();
	}
}