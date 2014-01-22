package model.user;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String id;
	private String name;
	private List<Integer> listCourse = new ArrayList<Integer>();
	protected int maxDurationBorrow;
	protected long CONT_DURATION_OF_DAY = 1000l * 60 * 60 * 24;

	public User() {
		throw new UnsupportedOperationException();
	}

	public User(String id, String name) {
		throw new UnsupportedOperationException();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setListCourse(List<Integer> listCourse) {
		this.listCourse = listCourse;
	}

	public List<Integer> getListCourse() {
		return this.listCourse;
	}
	
	/**
	 * Method equals. This method return true if two users have the same id. 
	 * @param u
	 * @return
	 */
	public boolean equals(User u){
		return (this.id.equals(u.getId())); 
	}
}