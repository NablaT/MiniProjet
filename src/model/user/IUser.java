/**
 * This interface models methods associates to a borrower.
 * @author benni
 * @version 12/06/13
 */
package model.user;

import java.util.List;
import java.util.Map;

public interface IUser {
	
	List<Integer> getListCoursesId();
	void setListCoursesId(List<Integer> coursesId);
	Map<String, Object> getDescription();
	boolean followCourse(Integer courseId);
	String getID();
	String getName();
	int getMaxNbLoans();
}
