/**
 * This class models a Teacher object. It inherits from Borrower.
 * @author benni
 * @version 12/06/13
 */
package model.user;

public class Teacher extends User {

	private static final int MAX_LOANS=5; 
	
	/**
	 * Constructor using the following parameters :
	 * @param id
	 * @param name
	 * 
	 * @author benni
	 */
	public Teacher(String id, String name) {
		super(id, name);
		super.maxNumberLoan=MAX_LOANS; 
	}
	
}
