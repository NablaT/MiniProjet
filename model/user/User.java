/**
 * This class models a Borrower, usefull for our simulation.
 * @author benni
 * @version 12/06/13
 */
package model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Borrower implements IUser  {
	
	private String name;
	private String id;
	private List<Integer> listCourse = new ArrayList<Integer>();
	
	protected int maxDurationBorrow;
	
	protected long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
	
	protected Borrower() { }
	
	/**
	 * Constructor using the followin parameters :
	 * @param id
	 * @param name
	 * @author benni
	 */
	protected Borrower(String id, String name) {
		if(id == null) {
			throw new IllegalArgumentException("La propri�t� id ne peux pas �tre null");
		}
		
		if(name == null) {
			throw new IllegalArgumentException("La propri�t� nom ne peux pas �tre null");
		}
		this.setID(id);
		this.setName(name);
	}
	
	public List<Integer> getListCoursesId() {
		return this.listCourse;
	}
	
	public void setListCoursesId(List<Integer> coursesId) {
		this.listCourse = coursesId;
	}

	public String getID() {
		return this.id;
	}
	
	/**
	 * Build a description stored in a Map. This description is based on 
	 * variable's values.
	 * @author benni
	 */
	public Map<String, Object> getDescription() {
		Map<String, Object> description = new HashMap<String, Object>();
		
		description.put("name",this.getName());
		description.put("id",this.getID());
		description.put("maxDurationBorrow",this.maxDurationBorrow);
		description.put("listCourse",this.listCourse);
				
		return description;		
	}
	
	/**
	 * Reset the variable's values from a description content.
	 * @param description
	 * @author benni
	 */
	public void restore(Map<String, Object> description) {
		this.setName((String)description.get("name"));
		this.setID((String)description.get("id"));
		this.listCourse = (List<Integer>)description.get("listCourse");
		this.maxDurationBorrow = (Integer)description.get("maxDurationBorrow");
	}
	
	@Override
	public String toString() {
		return "(#" + getID() + " :: " + getName() + ")";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setID(String id) {
		this.id = id;
	}
	
		
	
}
