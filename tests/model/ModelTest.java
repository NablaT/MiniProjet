package model;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import model.course.CourseManager;
import model.loan.Loan;
import model.loan.LoanManager;
import model.user.IUser;
import model.user.Teacher;
import model.user.UserManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTest {

	private Model model;
	private LoanManager lm;
	private CourseManager cm;
	private UserManager um;

	@Before
	public void setUp() throws Exception {
		this.model = new Model();

		IUser user = new Teacher("#bj103876", "Bond");
		this.model.getUserManager().setCurrentSessionReplace(user);
		this.lm = new LoanManager();

		lm.load();

		List<String> list = new ArrayList<String>();

		list.add("CW22");

		HashMap<String, Object> hm = new HashMap<String, Object>();

		this.cm = new CourseManager();

		this.cm.load();
		Calendar c3 = Calendar.getInstance();

		c3.set(Calendar.DAY_OF_YEAR, 100);

		this.um = new UserManager();

		this.um.load();

		ArrayList<State> lstate = new ArrayList<State>();

		lstate.add(State.GOOD);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetUserTypes() {

		ArrayList<String> comp = new ArrayList<String>();
		comp.add("Student");
		comp.add("Teacher");

		assertEquals(
				this.model.getUserManager().getUserTypes().get(0)
						.equals(comp.get(1)), true);

		assertEquals(
				this.model.getUserManager().getUserTypes().get(1)
						.equals(comp.get(0)), true);
	}

	@Test
	public void testGetCopyLimitation() {
		/*
		 * Teacher t= new Teacher("bj103876", "Bond");
		 * assertEquals(this.model.getCopyLimitation(t.getClass(), "GW22"),1);
		 */
	}

	/*
	 * @Test public void testGetProductDescription() { }
	 * 
	 * @Test public void testGetAllowedProductsForSpecifiedCourse() { }
	 * 
	 * @Test public void testGetNameOf() { }
	 * 
	 * @Test public void testTryToConnect() { }
	 * 
	 * @Test public void testGetAllowedCoursesDescriptionForCurrentUser() { }
	 * 
	 * @Test public void testGetAllowedProductsDescription() { }
	 */
	@Test
	public void testSetDatesCurrentLoan() {

		Calendar startDate = Calendar.getInstance();
		startDate.set(Calendar.DAY_OF_YEAR, 165);

		Calendar endDate = Calendar.getInstance();

		endDate.set(Calendar.DAY_OF_YEAR, 145);

		Calendar askDate = Calendar.getInstance();

		askDate.set(Calendar.DAY_OF_YEAR, 130);
		this.model.setDatesCurrentLoan(askDate, startDate, endDate);

		assertEquals(
				this.model.getCurrentLoan().get("startDate").equals(startDate),
				true);
		assertEquals(
				this.model.getCurrentLoan().get("endDate").equals(endDate),
				true);
		assertEquals(
				this.model.getCurrentLoan().get("askDate").equals(askDate),
				true);

		/*
		 * public void setDatesCurrentLoan(Calendar askDate, Calendar startDate,
		 * Calendar endDate) { this.currentLoan.put("startDate", startDate);
		 * this.currentLoan.put("endDate", endDate);
		 * this.currentLoan.put("askDate", askDate); }
		 */
	}

	/*
	 * @Test public void testSetStartDateCurrentLoan() { }
	 * 
	 * @Test public void testSetEndDateCurrentLoan() {
	 * fail("Not yet implemented"); }
	 * 
	 * @Test public void testSetAskDateCurrentLoan() {
	 * fail("Not yet implemented"); }
	 * 
	 * @Test public void testSetStateCurrentLoan() { }
	 */

	@Test
	public void testCheckIfUserCanBorrowQuantity() {
		IUser user = new Teacher("#bj103876", "Bond");
		this.model.getUserManager().setCurrentSessionReplace(user);

		assertEquals(this.model.checkIfUserCanBorrowQuantity("CW22", 2), true);
		assertEquals(this.model.checkIfUserCanBorrowQuantity("CW22", 2002),
				false);

	}

	@Test
	public void testCheckIfUserCanBorrowDate() {
		this.model
				.getStockManager()
				.getMaterial("CW22")
				.setDurationLimitation(
						this.model.getUserManager().getCurrentSessionType(), 15);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, 123);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.DAY_OF_YEAR, 130);

		Calendar calendar3 = Calendar.getInstance();
		calendar3.set(Calendar.DAY_OF_YEAR, 230);

		assertEquals(this.model.checkIfUserCanBorrowDate("CW22", calendar,
				calendar2), true);
		assertEquals(this.model.checkIfUserCanBorrowDate("CW22", calendar,
				calendar3), false);

	}

	@Test
	public void testCheckIfUserCanBorrowAnotherItem() {

		IUser user = new Teacher("#bj103876", "Bond");
		this.model.getUserManager().setCurrentSessionReplace(user);
		assertEquals(this.model.checkIfUserCanBorrowAnotherItem(), true);

		Teacher t = new Teacher("#sj098765", "Stromboni");
		t.setMaxOfLoan(1);
		IUser user2 = t;

		this.model.getUserManager().setCurrentSessionReplace(user2);

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 165);

		Calendar c2 = Calendar.getInstance();

		c2.set(Calendar.DAY_OF_YEAR, 145);

		Calendar c3 = Calendar.getInstance();

		c3.set(Calendar.DAY_OF_YEAR, 130);

		ArrayList<String> material = new ArrayList<String>();
		material.add("CW22");
		ArrayList<State> startState = new ArrayList<State>();
		startState.add(State.GOOD);
		Loan l = new Loan(material, 1, c, c2, c3, user2, startState);
		this.model.getLoanManager().addLoan(l);

		assertEquals(this.model.checkIfUserCanBorrowAnotherItem(), false);

	}

	@Test
	public void testCheckIfMaterialAvailable() {

		assertEquals(this.model.checkIfMaterialAvailable("CW99", 1), true);
	}

	@Test
	public void testBuildListOfMaterialsAvailable() {

		ArrayList<String> list = new ArrayList<String>();
		list.add("CW22");
		list.add("CW99");
		list.add("GS37");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2035);

		Calendar c2 = Calendar.getInstance();

		c2.set(Calendar.DAY_OF_YEAR, 2036);

		ArrayList<String> result = (ArrayList<String>) this.model
				.buildListOfMaterialsAvailable(c, c2);
		for (int i = 0; i < result.size(); i++) {
			assertEquals(list.get(i).equals(result.get(i)), true);
		}
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("GW22");
		list2.add("GS37");

		Calendar start = this.model.getLoanManager().getUnreturnedLoans()
				.get(0).getStartDate();
		Calendar end = this.model.getLoanManager().getUnreturnedLoans().get(0)
				.getEndDate();

		ArrayList<String> result2 = (ArrayList<String>) this.model
				.buildListOfMaterialsAvailable(start, end);

		for (int i = 0; i < 2; i++) {
			assertEquals(list2.get(i).equals(result.get(i)), false);
		}

	}

	@Test
	public void testCloseCurrentLoan() {
	}

	/*
	 * @Test public void testAddMaterialToCurrentLoan() {
	 * fail("Not yet implemented"); }
	 */

}
