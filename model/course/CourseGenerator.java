package model.course;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CourseGenerator {

	public static void storeCourseDescription(CourseManager cm) {
			
		Map<String, Object> course1 = new HashMap<String, Object>();
		course1.put("name", "SSII");
		course1.put("id", 1);
		
		Calendar c11 = Calendar.getInstance();
		c11.set(2013, 9, 20);
		
		Calendar c12 = Calendar.getInstance();
		c12.set(2013, 9, 20);

		course1.put("endDate", c11);
		course1.put("startDate",c12 );
		
		List<String> listOfAllowedProductsForCourse1 = new ArrayList<String>();
		listOfAllowedProductsForCourse1.add("CW22");
		listOfAllowedProductsForCourse1.add("CW99");
		course1.put("productsAllowed", listOfAllowedProductsForCourse1);
		
		
		
		Map<String, Object> course2 = new HashMap<String, Object>();
		course2.put("name", "POO4");
		course2.put("id", 2);
		
		Calendar c21 = Calendar.getInstance();
		c21.set(2014, 1, 5);
		
		Calendar c22 = Calendar.getInstance();
		c22.set(2014, 5, 15);
		
		course2.put("endDate", c21);
		course2.put("startDate",c22 );
		
		
		List<String> listOfAllowedProductsForCourse2 = new ArrayList<String>();
		listOfAllowedProductsForCourse2.add("CW22");
		listOfAllowedProductsForCourse2.add("GS37");
		course2.put("productsAllowed", listOfAllowedProductsForCourse2);
		
		
		
		Map<String, Object> course3 = new HashMap<String, Object>();
		course3.put("name", "AA");
		course3.put("id", 3);

		Calendar c31 = Calendar.getInstance();
		c31.set(2013, 9, 5);
		
		Calendar c32 = Calendar.getInstance();
		c32.set(2013, 12, 15);
		
		course3.put("endDate", c31);
		course3.put("startDate",c32 );
		
		List<String> listOfAllowedProductsForCourse3 = new ArrayList<String>();
		listOfAllowedProductsForCourse3.add("GS37");
		course3.put("productsAllowed", listOfAllowedProductsForCourse3);
		
		Course c1 = new Course(course1);
		Course c2 = new Course(course2);
		Course c3 = new Course(course3);
		
		cm.addCourse(c1);
		cm.addCourse(c2);
		cm.addCourse(c3);
		
		cm.store();
		
	}
}
