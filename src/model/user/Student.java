/**
 * This class models a Student object. It inherits from Borrower.
 * @author benni
 * @version 12/06/13
 */

package model.user;

import java.util.Map;


public class Student extends User implements IUser {

	public Student() {
		super();
	}
	
	/**
	 * Constructor using the following parameters :
	 * @param id
	 * @param name
	 * 
	 * @author benni
	 */
	public Student(String id, String name) {
		super(id, name);
	}
	
	public Student(Map<String, Object> description) {
		super(description);
	}
	
	
	@Override
	public String toString() {
		return "\t[#" + getID() + "] [" + this.getClass().getSimpleName() + "]\t" + super.toString();
	}
}
