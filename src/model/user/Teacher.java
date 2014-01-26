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
		this.maxNbLoans = 10;
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
		this.maxNbLoans = 10;
	}
	
	public Teacher(Map<String, Object> description) {
		super(description);
		this.maxNbLoans = 10;
	}
	
	@Override
	public String toString() {
		return "\t[#" + getID() + "] [" + this.getClass().getSimpleName() + "]\t" + super.toString();
	}
}
