package model.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Manager;
import model.util.ConfigXML;

public class CourseManager implements Manager {
	public List<Course> listOfCourses = new ArrayList<Course>();

	public static final String KEY_PATH_FILE = "courses";
	public static final String KEY_VERSION_FILE = "0.0.0";
	
	/**
	 * Loads courses from the xml
	 */
	public CourseManager() {}
	
	public void addCourse(Course c) {
		this.listOfCourses.add(c);
	}
	
	/**
	 * Build courses description
	 * @param loadCoursesDescription
	 * @author benni
	 */
	private void restore(
			List<Map<String, Object>> loadCoursesDescription) {
		
		for(Map<String, Object> currentCourseDescription : loadCoursesDescription) {
			Course c = new Course();
			c.restore(currentCourseDescription);
			listOfCourses.add(c);
		}
	}
	


	@Override
	@SuppressWarnings("unchecked")
	public void load() {
		List<Map<String, Object>> courseDescription 
		= new ArrayList<Map<String, Object>>();

		courseDescription = (List<Map<String, Object>>)
				ConfigXML.load(KEY_PATH_FILE, KEY_VERSION_FILE);
		
		this.restore(courseDescription);
	}

	@Override
	public void store() {
		List<Map<String, Object>> description 
		= new ArrayList<Map<String, Object>>();

		for (Course c : this.listOfCourses) {			
			description.add(c.getDescription());
		}
		
		ConfigXML.store(description,KEY_PATH_FILE, KEY_VERSION_FILE);
	}
	
	public String displayCourses() {
		String result = "";

		for (Course c : this.listOfCourses) {
			result = result.concat(c.toString() + "\n");
		}

		return result;
	}
	
	public List<Map<String, Object>> getCoursesDescription() {
		List<Map<String, Object>> coursesDescription = new ArrayList<Map<String, Object>>();
	
		for(Course currentCourseDescription : listOfCourses) {
			coursesDescription.add(currentCourseDescription.getDescription());
		}
		
		return coursesDescription;
	}
	
	public Map<String, Object> getCourseDescription(int courseId) {
		Map<String, Object> courseDescription = new HashMap<String, Object>();
		
		courseDescription = getCourse(courseId).getDescription();
		
		return courseDescription;
	}
	
	/**
	 * Find a course and returns it by an access from its ID
	 * @param courseId
	 * @return
	 */
	public Course getCourse(int courseId) {
		for(Course c : listOfCourses) {
			if(c.getID() == courseId) { return c;}
		}
		
		return null;
	}
}