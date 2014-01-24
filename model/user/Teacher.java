/**
 * This class models a Teacher object. It inherits from Borrower.
 * @author benni
 * @version 12/06/13
 */
package model.user;

import java.util.Map;

public class Teacher extends User {

	public Teacher() {
		super();
	}
	
	/**
	 * Constructor using the following parameters :
	 * @param id
	 * @param name
	 * 
	 * @author benni
	 */
	public Teacher(String id, String name) {
		super(id, name);
	}
	
	public Teacher(Map<String, Object> description) {
		super(description);
	}
	
	@Override
	public String toString() {
		return "\t[#" + getID() + "] [" + this.getClass().getSimpleName() + "]\t" + super.toString();
	}
}
