/**
 * This class models a Student object. It inherits from Borrower.
 * @author benni
 * @version 12/06/13
 */

package model.user;


public class Student extends Borrower implements IUser {

	/**
	 * Constructor using the following parameters :
	 * @param id
	 * @param name
	 * 
	 * @author benni
	 */
	public Student(String id, String name) {
		super(id, name);
		this.maxDurationBorrow = 7;
	}

}
