package model.course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
	private Calendar startDate;
	private Calendar endDate;
	private String name;
	private List<String> productsAllowed = new ArrayList<String>();
	private int id;

	public Course() {
	}

	public Course(Map<String, Object> courseDescription) {
		this.restore(courseDescription);
	}

	/**
	 * @return the date
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @return the date
	 */
	public Calendar getEndDate() {

		return endDate;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setStartDate(Calendar date) {
		this.startDate = date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setEndDate(Calendar date) {
		this.endDate = date;
	}

	/**
	 * @return the nom
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the nom to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns a Map<String, Object> built by the object's variable's values.
	 */
	public Map<String, Object> getDescription() {
		Map<String, Object> courseDescription = new HashMap<String, Object>();

		courseDescription.put("id", this.id);
		courseDescription.put("startDate", this.startDate);
		courseDescription.put("endDate", this.endDate);
		courseDescription.put("name", this.name);
		courseDescription.put("productsAllowed", this.productsAllowed);

		return courseDescription;
	}

	public int getID() {
		return this.id;
	}

	public List<String> getAllowedProducts() {
		return this.productsAllowed;
	}

	/**
	 * Will reset variable's values from the object's description
	 * 
	 * @param courseDescription
	 *            description equals at {@link Course#getDescription()}
	 */
	@SuppressWarnings("unchecked")
	public void restore(Map<String, Object> courseDescription) {
		this.id = (Integer) courseDescription.get("id");
		this.startDate = (Calendar) courseDescription.get("startDate");
		this.endDate = (Calendar) courseDescription.get("endDate");
		this.name = (String) courseDescription.get("name");
		this.productsAllowed = (List<String>) courseDescription
				.get("productsAllowed");
	}

	@Override
	public String toString() {

		DateFormat ndf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String startDateStr = ndf.format(startDate.getTime());
		String endDateStr = ndf.format(endDate.getTime());

		return "\t[#" + id + "]\t" + name + " \t(" + startDateStr + " --> "
				+ endDateStr + ")";
	}
}