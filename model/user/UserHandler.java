/**
 * This class is going to load the users data from the xml file to set up lists of borrowers.
 * @author benni
 * @version 12/06/13 
 */

package model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.util.ConfigXML;


public class UserHandler {
	private Map<Class<?extends Borrower>, List<IUser>> listOfUsersAccounts = new HashMap<Class<?extends Borrower>, List<IUser>>();

	private static final String PATH_ACCOUNT_FILE = "accounts";
	private static final String VERSION_ACCOUNT_FILE = "0.0.0";
	
	/**
	 * This default constructor calls the buildListOfBorrowers() with the Map loaded from
	 * the xml file as a parameter.
	 * @author benni
	 */
	public UserHandler() {
		restore((Map<String, List<Map<String, Object>>>) ConfigXML.load(PATH_ACCOUNT_FILE, VERSION_ACCOUNT_FILE));
	}
	
	/**
	 * This method calls the building method for both of the two lists : teachers and students 
	 * (our two types of borrowers).
	 * @param usersDescription
	 * @author boinaud
	 */
	private void restore(Map<String, List<Map<String, Object>>> usersDescription) {
		
		if(usersDescription == null) {
			
		}
		
		if(usersDescription.isEmpty()) {
			
		}
		
		buildListOfTeachers(usersDescription.get("teachers"));
		buildListOfStudents(usersDescription.get("students"));
	}
	
	
	/**
	 * This method will build a list of teachers used by our application.
	 * @author benni
	 * @param teachersDescription
	 */
	private void buildListOfTeachers(List<Map<String, Object>> teachersDescription) {
		
		List<IUser> listOfTeachers = new ArrayList<IUser>();
		
		for(Map<String, Object> userDescription : teachersDescription) {
			
			String borrowerName = (String)userDescription.get("name");
			String borrowerID = (String)userDescription.get("id");
			
			IUser currentBorrower = new Teacher(borrowerID,borrowerName);
			currentBorrower.setListCoursesId((List<Integer>)userDescription.get("listCourse"));
			listOfTeachers.add(currentBorrower);
		}
		listOfUsersAccounts.put(Teacher.class, listOfTeachers);
	}
	
	/**
	 * This method will build a list of students used by our application.
	 * @author benni
	 * @param teachersDescription
	 */
	private void buildListOfStudents(List<Map<String, Object>> studentsDescription) {
		
		List<IUser> listOfStudents = new ArrayList<IUser>();
		
		for(Map<String, Object> userDescription : studentsDescription) {
			
			String borrowerName = (String)userDescription.get("name");
			String borrowerID = (String)userDescription.get("id");
			
			System.out.println(borrowerName + borrowerID);
			
			IUser currentBorrower = new Student(borrowerID,borrowerName);
			currentBorrower.setListCoursesId((List<Integer>)userDescription.get("listCourse"));

			listOfStudents.add(currentBorrower);
		}
		listOfUsersAccounts.put(Student.class, listOfStudents);
	}
	
	
	
	public Map<Class<?extends Borrower>, List<IUser>> getBorrowerList() {
		return listOfUsersAccounts;
	}
	
	public List<IUser> getBorrowerList(Class<?extends Borrower> targetClass) {
		return listOfUsersAccounts.get(targetClass);
	}
	
	
	public boolean isBorrowerRegistred(String potentialID) {
		
		return findUser(potentialID) != null;
	}
	
	/**
	 * Method used to find a user in listOfUserAccount, returns a Borrower.
	 * @param userId
	 * @return
	 */
	private Class<?extends Borrower> findUser(String userId) {
		
		for(Class<?extends Borrower>  c : listOfUsersAccounts.keySet()) {
			for(IUser b : listOfUsersAccounts.get(c)) {
				if(b.getID().equalsIgnoreCase(userId)) { return c; }
			}
		}
		
		return null;
	}
	
	/**
	 * This method will connect a user using its id (at least a potential one) as a parameter. If you
	 * try to connect an unknown user, the method returns null.
	 * @param potentialID
	 * @return
	 */
	public IUser connectUser(String potentialID) {
		
		if(!isBorrowerRegistred(potentialID)) {
			return null;
		}
		
		Class<?extends Borrower> targetClass = findUser(potentialID);
		for(IUser b : listOfUsersAccounts.get(targetClass)) {
			if(b.getID().equalsIgnoreCase(potentialID)) {
				return b; 
			}
		}

		return null;

	}
	
	public String toString() {
		String res = "";
		
		for(Class<?extends Borrower> c : listOfUsersAccounts.keySet()) {
			res += "\n" + c.getSimpleName() + " :\n\t";
			for(IUser b : listOfUsersAccounts.get(c)) {
				res += b.toString() + " ";
			}
		}
		
		return res;
	}
	
}
