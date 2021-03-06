/**
 * This class is going to load the users data from the xml file to set up lists of borrowers.
 * @author benni
 * @version 12/06/13 
 */

package model.user;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Manager;
import model.util.ConfigXML;

public class UserManager implements Manager {
	private Map<Class<? extends User>, List<IUser>> listOfUsersAccounts = new HashMap<Class<? extends User>, List<IUser>>();

	private static final String PATH_ACCOUNT_FILE = "accounts";
	private static final String VERSION_ACCOUNT_FILE = "0.0.0";

	private IUser currentSession;
	private Class<? extends IUser> currentSessionType;

	/**
	 * This default constructor calls the buildListOfBorrowers() with the Map
	 * loaded from the xml file as a parameter.
	 * 
	 * @author benni
	 */
	public UserManager() {
	}

	/**
	 * This method calls the building method for both of the two lists :
	 * teachers and students (our two types of borrowers).
	 * 
	 * @param usersDescription
	 */
	public void restore(
			Map<Class<? extends IUser>, List<Map<String, Object>>> usersAccountsDescription) {

		if (usersAccountsDescription == null) {

		}

		if (usersAccountsDescription.isEmpty()) {

		}

		for (Class<? extends IUser> key : usersAccountsDescription.keySet()) {
			for (Map<String, Object> currentAccountDescription : usersAccountsDescription
					.get(key)) {
				this.addUser(restoreUser(key, currentAccountDescription));
			}
		}
	}

	public User restoreUser(Class<? extends IUser> classe,
			Map<String, Object> description) {

		Constructor<?> constructeur = null;
		try {
			constructeur = classe.getConstructor();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		User instance = null;

		try {
			instance = (User) constructeur.newInstance(new Object[] {});
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		instance.restore(description);
		return instance;
	}

	// /**
	// * This method will build a list of teachers used by our application.
	// *
	// * @author benni
	// * @param teachersDescription
	// */
	// @SuppressWarnings("unchecked")
	// private void buildListOfTeachers(
	// List<Map<String, Object>> teachersDescription) {
	//
	// List<IUser> listOfTeachers = new ArrayList<IUser>();
	//
	// for (Map<String, Object> userDescription : teachersDescription) {
	//
	// String borrowerName = (String) userDescription.get("name");
	// String borrowerID = (String) userDescription.get("id");
	//
	// IUser currentBorrower = new Teacher(borrowerID, borrowerName);
	// currentBorrower.setListCoursesId((List<Integer>) userDescription
	// .get("listCourse"));
	// listOfTeachers.add(currentBorrower);
	// }
	// listOfUsersAccounts.put(Teacher.class, listOfTeachers);
	// }
	//
	// /**
	// * This method will build a list of students used by our application.
	// *
	// * @author benni
	// * @param teachersDescription
	// */
	// @SuppressWarnings("unchecked")
	// private void buildListOfStudents(
	// List<Map<String, Object>> studentsDescription) {
	//
	// List<IUser> listOfStudents = new ArrayList<IUser>();
	//
	// for (Map<String, Object> userDescription : studentsDescription) {
	//
	// String borrowerName = (String) userDescription.get("name");
	// String borrowerID = (String) userDescription.get("id");
	//
	// System.out.println(borrowerName + borrowerID);
	//
	// IUser currentBorrower = new Student(borrowerID, borrowerName);
	// currentBorrower.setListCoursesId((List<Integer>) userDescription
	// .get("listCourse"));
	//
	// listOfStudents.add(currentBorrower);
	// }
	// listOfUsersAccounts.put(Student.class, listOfStudents);
	// }

	public void setCurrentSessionType(String fullName) {
		try {
			this.currentSessionType = (Class<? extends IUser>) Class
					.forName(fullName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Class<? extends IUser> getCurrentSessionType() {
		return this.currentSessionType;
	}

	public IUser getCurrentSessionUser() {
		return this.currentSession;
	}

	public boolean setCurrentSession(IUser u) {
		if (!u.getClass().equals(currentSessionType)) {
			return false;
		}

		this.currentSession = u;
		System.out.println("Current User : " + u);
		return true;
	}

	public Map<Class<? extends User>, List<IUser>> getBorrowerList() {
		return listOfUsersAccounts;
	}

	public List<IUser> getBorrowerList(Class<? extends User> targetClass) {
		return listOfUsersAccounts.get(targetClass);
	}

	public boolean isBorrowerRegistred(String potentialID) {

		return findUser(potentialID) != null;
	}

	/**
	 * Method used in JUnit test
	 * 
	 * @param user
	 */
	public void setCurrentSessionReplace(IUser user) {
		this.currentSession = user;
	}

	/**
	 * Method used to find a user in listOfUserAccount, returns a Borrower.
	 * 
	 * @param userId
	 * @return
	 */
	private Class<? extends User> findUser(String userId) {

		for (Class<? extends User> c : listOfUsersAccounts.keySet()) {
			for (IUser b : listOfUsersAccounts.get(c)) {
				if (b.getID().equalsIgnoreCase(userId)) {
					return c;
				}
			}
		}

		return null;
	}

	public void addUser(User u) {
		List<IUser> listUsers = new ArrayList<IUser>();

		if (this.listOfUsersAccounts.containsKey(u.getClass())) {
			listUsers = this.listOfUsersAccounts.get(u.getClass());
		}

		listUsers.add(u);
		this.listOfUsersAccounts.put(u.getClass(), listUsers);

	}

	/**
	 * This method will connect a user using its id (at least a potential one)
	 * as a parameter. If you try to connect an unknown user, the method returns
	 * null.
	 * 
	 * @param potentialID
	 * @return the user object specified by the id
	 */
	public IUser connectUser(String potentialID) {

		if (!isBorrowerRegistred(potentialID)) {
			return null;
		}

		Class<? extends User> targetClass = findUser(potentialID);
		for (IUser b : listOfUsersAccounts.get(targetClass)) {
			if (b.getID().equalsIgnoreCase(potentialID)) {
				return b;
			}
		}

		return null;

	}

	public List<IUser> usersToList() {
		List<IUser> result = new ArrayList<IUser>();

		for (List<IUser> currentList : this.listOfUsersAccounts.values()) {
			for (IUser m : currentList) {
				result.add(m);
			}
		}
		return result;
	}

	public List<String> getUserTypes() {
		List<String> userTypes = new ArrayList<String>();

		for (Class<? extends IUser> c : this.listOfUsersAccounts.keySet()) {
			userTypes.add(c.getSimpleName());
		}

		return userTypes;
	}

	public String getFullName(String lowerClass) {
		return this.getClass().getPackage().getName() + "." + lowerClass;
	}

	public String toString() {
		String res = "";

		for (Class<? extends User> c : listOfUsersAccounts.keySet()) {
			res += "\n" + c.getSimpleName() + " :\n\t";
			for (IUser b : listOfUsersAccounts.get(c)) {
				res += b.toString() + " ";
			}
		}

		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void load() {
		Map<Class<? extends IUser>, List<Map<String, Object>>> usersAccountsDescription = new HashMap<Class<? extends IUser>, List<Map<String, Object>>>();

		usersAccountsDescription = (Map<Class<? extends IUser>, List<Map<String, Object>>>) ConfigXML
				.load(PATH_ACCOUNT_FILE, VERSION_ACCOUNT_FILE);

		this.restore(usersAccountsDescription);
	}

	@Override
	public void store() {
		Map<Class<? extends IUser>, List<Map<String, Object>>> description = new HashMap<Class<? extends IUser>, List<Map<String, Object>>>();

		for (IUser m : this.usersToList()) {
			Class<? extends IUser> c = m.getClass();

			if (description.containsKey(c)) {
				description.get(c).add(m.getDescription());
			} else {
				List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
				newList.add(m.getDescription());
				description.put(c, newList);
			}
		}

		ConfigXML.store(description, PATH_ACCOUNT_FILE, VERSION_ACCOUNT_FILE);
	}

	public String displayUsersAccounts() {
		String result = "";

		for (IUser u : this.usersToList()) {
			result = result.concat(u.toString() + "\n");
		}

		return result;
	}

}
