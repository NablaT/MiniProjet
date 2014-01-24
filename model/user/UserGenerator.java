package model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class UserGenerator {
	public static void storeStockDescription(UserManager um) {

		
		Map<String, Object> user1 = new HashMap<String, Object>();
		user1.put("name", "Franck");
		user1.put("id", "df105266");
		
		List<Integer> listCourseUser1 = new ArrayList<Integer>();
		listCourseUser1.add(1);
		listCourseUser1.add(2);
		user1.put("listCourse", listCourseUser1);
		
		Map<String, Object> user2 = new HashMap<String, Object>();
		user2.put("name", "Benjamin");
		user2.put("id", "bb102748");
		
		List<Integer> listCourseUser2 = new ArrayList<Integer>();
		listCourseUser2.add(1);
		user2.put("listCourse", listCourseUser2);
		
		Map<String, Object> user5 = new HashMap<String, Object>();
		user5.put("name", "Camille");
		user5.put("id", "bc208556");
		
		List<Integer> listCourseUser5 = new ArrayList<Integer>();
		listCourseUser5.add(2);
		user5.put("listCourse", listCourseUser5);
		
		Map<String, Object> user3 = new HashMap<String, Object>();
		user3.put("name", "Bond");
		user3.put("id", "bj103876");
		
		List<Integer> listCourseUser3 = new ArrayList<Integer>();
		listCourseUser3.add(1);
		listCourseUser3.add(2);
		listCourseUser3.add(3);
		user3.put("listCourse", listCourseUser3);
		
		Map<String, Object> user4 = new HashMap<String, Object>();
		user4.put("name", "Stromboni");
		user4.put("id", "sj098765");
		
		User u1 = new Student(user1);
		User u2 = new Student(user2);
		User u3 = new Teacher(user3);
		User u4 = new Teacher(user4);

		um.addUser(u1);
		um.addUser(u2);
		um.addUser(u3);
		um.addUser(u4);
		
		um.store();
	}
}
