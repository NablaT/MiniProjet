package model.loan;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import model.State;
import model.course.Course;
import model.course.CourseManager;
import model.material.Material;
import model.user.Teacher;
import model.user.UserManager;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoanManagerTest {

	private LoanManager lm;
	private UserManager um;
	private CourseManager cm;
	private Loan loan;

	

	@Before
	public void setUp() throws Exception {
		this.lm = new LoanManager();
		lm.load();
		List<String> list = new ArrayList<String>();
		list.add("CW22");
		HashMap<String, Object> hm = new HashMap<String, Object>();
		this.cm = new CourseManager();
		this.cm.load();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_YEAR, 165);
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.DAY_OF_YEAR, 145);

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
	public void testLoanManagment() {
		fail("Not yet implemented");
	}

	@Test
	public void testAskForALoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testAskForATeacher() {
		fail("Not yet implemented");
	}

	@Test
	public void testCanBorrow() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllMaterialsLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHMOfMaterial() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllMaterialsHeWants() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotLate() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnTypeOfMaterial() {
		fail("Not yet implemented");
	}

	@Test
	public void testAskForAStudent() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsAvailable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveBadLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testSameDate() {
		
		//assertEquals(this.lm.sameDate(this.lm.getUnreturnedLoans().get(0),this.lm.getUnreturnedLoans().get(1)),false); 
	}

	@Test
	public void testReturnLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testTurnBackMaterial() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetUnreturnedLoans() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUnreturnedLoans() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetOldLoans() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOldLoans() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLoan() {
		fail("Not yet implemented");
	}

	@Test
	public void testRestore() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoad() {
		fail("Not yet implemented");
	}

	@Test
	public void testStore() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisplayUnreturnedLoans() {
		fail("Not yet implemented");
	}

}
