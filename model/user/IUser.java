/**
 * This interface models methods associates to a borrower.
 * @author benni
 * @version 12/06/13
 */
package model.user;

import java.util.List;
import java.util.Map;

public interface IUser {
	String getID();
//	Borrow borrow(Product p, int quantity, Course c, Date startDate, Date endDate);
//	Borrow borrow(List<Product> p,List<Integer> quantities, Course c, Date startDate, Date endDate);
//	List<Borrow> getListBorrow();
//	void setListBorrow(List<Borrow> listBorrow);
//	void resetListBorrow();
//	void addCourse(Course c);
	
	List<Integer> getListCoursesId();
	void setListCoursesId(List<Integer> coursesId);
	Map<String, Object> getDescription() ;
}
