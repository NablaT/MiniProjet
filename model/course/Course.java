package model.course;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Course {
	private Calendar startDate;
	private Calendar endDate;
	private String name;
	private List<Integer> productsAllowed;
	private int id;
 
	public Map<String, Object> getDescription() {
		throw new UnsupportedOperationException();
	}

	public int getID() {
		throw new UnsupportedOperationException();
	}

	public List<Integer> getAllowedProduct() {
		throw new UnsupportedOperationException();
	}

	public void restore(Map<String, Object> courseDescription) {
		throw new UnsupportedOperationException();
	}
}