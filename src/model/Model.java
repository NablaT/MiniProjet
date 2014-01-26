package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import model.course.CourseManager;
import model.loan.Loan;
import model.loan.LoanManager;
import model.material.StockManager;
import model.user.IUser;
import model.user.UserManager;

/**
 * Class Model<br>
 * This class provides services between controller and managers<br>
 * It contents all manager from modules<br>
 * 
 * @author Benni Benjamin,Pourtier Rémi
 * 
 */
public class Model {

	// All manager in the app
	private StockManager sm;
	private UserManager um;
	private LoanManager lm;
	private CourseManager cm;

	// Description of the current loan
	private Map<String, Object> currentLoan = new HashMap<String, Object>();

	public Model() {

		this.sm = new StockManager();
		// StockGenerator.storeStockDescription(sm);
		this.sm.load();

		this.um = new UserManager();
		// UserGenerator.storeStockDescription(um);
		this.um.load();

		this.cm = new CourseManager();
		// CourseGenerator.storeCourseDescription(cm);
		this.cm.load();

		this.lm = new LoanManager();
		// LoanGenerator.storeLoansDescription(lm, um);
		this.lm.load();

		System.out.println(PieceOfDisplay.stockInitial);
		System.out.println(sm.displayStock());
		System.out.println(PieceOfDisplay.someEquals);

		System.out.println(PieceOfDisplay.userAccounts);
		System.out.println(um.displayUsersAccounts());
		System.out.println(PieceOfDisplay.someEquals);

		System.out.println(PieceOfDisplay.availableCourses);
		System.out.println(cm.displayCourses());
		System.out.println(PieceOfDisplay.someEquals);

		System.out.println(PieceOfDisplay.notReturnLoans);
		System.out.println(lm.displayUnreturnedLoans());
		System.out.println(PieceOfDisplay.someEquals);

	}

	/**
	 * Get users types<br>
	 * It returns the different types of user available in the app
	 * 
	 * @return the different types of user available in the app
	 */
	public List<String> getUserTypes() {
		return this.um.getUserTypes();
	}

	/**
	 * It allows to set the type of the current session<br>
	 * 
	 * @param type
	 *            the type of user who is just connected
	 */
	public void setCurrentSessionType(String type) {
		this.um.setCurrentSessionType(this.um.getFullName(type));

	}

	/**
	 * Get copy limitation for the specified user and the specified material
	 * 
	 * @param classe
	 *            Class of the user who try to borrow the specified material
	 * @param materialID
	 *            The id of the material which the current user wants to borrow
	 * @return the number of copy that the specified user can loan for specified
	 *         material
	 */
	public int getCopyLimitation(Class<? extends IUser> classe,
			String materialID) {
		return this.sm.getMaterial(materialID).getCopyLimitation(classe);
	}

	/**
	 * Returns the product description of the specified material
	 * 
	 * @param materialID
	 *            id of the material
	 * @return the brandName and name of the specified material
	 */
	public String getProductDescription(String materialID) {
		return this.sm.getMaterial(materialID).getProductDescription();
	}

	/**
	 * Return the list of material id which are allowed for the specified course
	 * 
	 * @param courseId
	 *            id of the course
	 * @return the list of material which are allowed for the specified course
	 */
	public List<String> getAllowedProductsForSpecifiedCourse(int courseId) {
		return this.cm.getCourse(courseId).getAllowedProducts();
	}

	/**
	 * Return the name of the specified material
	 * 
	 * @param materialID
	 *            id of the material
	 * @return the name of the specified material
	 */
	public String getNameOf(String materialID) {
		return this.sm.getMaterial(materialID).getName();
	}

	/**
	 * Try to connect a user with his user id<br>
	 * It will check if the specified user is registered and will set the
	 * current session and the current loan user property.
	 * 
	 * @param potentialID
	 *            user id representing the potential user
	 * @return true if the user specified is registered and is in the current
	 *         session - false otherwise
	 */
	public boolean tryToConnect(String potentialID) {

		// Check if the specified user is registered
		if (!this.um.isBorrowerRegistred(potentialID)) {
			return false;
		}

		// Set the current session user field
		if (this.um.setCurrentSession(this.um.connectUser(potentialID))) {

			// Add in the current loan the user description specified by user id
			Map<String, Object> userDescription = this.um
					.getCurrentSessionUser().getDescription();
			userDescription.put("class", this.um.getCurrentSessionUser()
					.getClass());
			this.currentLoan.put("user", userDescription);
			return true;
		}
		return false;
	}

	/**
	 * Returns the allowed description for the current user session, from
	 * startDate specified
	 * 
	 * @param startDate
	 *            start date of the current loan
	 * @return list of courses description which are followed by current session
	 *         user
	 * 
	 */
	public List<Map<String, Object>> getAllowedCoursesDescriptionForCurrentUser(
			Calendar startDate) {
		List<Map<String, Object>> allowedCoursesDescription = new ArrayList<Map<String, Object>>();

		// Run through the courses id for the current user session
		for (Integer courseID : this.um.getCurrentSessionUser()
				.getListCoursesId()) {

			// Compare the course start date with the specified date
			if ((startDate
					.compareTo(this.cm.getCourse(courseID).getStartDate()) > 0)) {
				continue;
			}

			// add the course description
			allowedCoursesDescription.add(this.cm
					.getCourseDescription(courseID));
		}

		return allowedCoursesDescription;
	}

	/**
	 * Return the list of material description for the specified course id and
	 * specified date<br>
	 * The specified date is used to build the stock state at this date.
	 * 
	 * @param courseID
	 *            course id
	 * @param today
	 *            date used to build the stock description at this date
	 * @return the list of material description for the specified course id and
	 *         specified date
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllowedProductsDescription(
			int courseID, Calendar today) {

		// Set the current loan fields
		this.currentLoan.put("courseId", courseID);
		this.currentLoan.put("gaveBack", false);

		List<Map<String, Object>> allowedProductsDescription = new ArrayList<Map<String, Object>>();

		// Get the list of materials id allowed for the specified course
		List<String> listIDAllowedProducts = (List<String>) (this.cm
				.getCourseDescription(courseID)).get("productsAllowed");

		int daysBetweenTodayAndBeginningOfCourse = 0;

		// Check if course start date and course end date match with the
		// specified date
		Calendar beginningOfCourse = this.cm.getCourse(courseID).getStartDate();

		if (today.compareTo(beginningOfCourse) > 0) {
			daysBetweenTodayAndBeginningOfCourse = (int) TimeUnit.MILLISECONDS
					.toDays(today.getTimeInMillis()
							- beginningOfCourse.getTimeInMillis());
		} else {
			daysBetweenTodayAndBeginningOfCourse = (int) TimeUnit.MILLISECONDS
					.toDays(beginningOfCourse.getTimeInMillis()
							- today.getTimeInMillis());
		}

		// Run through the material id list and check the limitation for current
		// material
		for (String materialID : listIDAllowedProducts) {

			if (daysBetweenTodayAndBeginningOfCourse > this.sm.getMaterial(
					materialID).getDelayLimitation(
					this.um.getCurrentSessionUser().getClass())) {
				continue;
			}

			Map<String, Object> currentMaterialDescription = new HashMap<String, Object>();

			currentMaterialDescription = this.sm.getMaterial(materialID)
					.getDescription();
			allowedProductsDescription.add(currentMaterialDescription);
		}

		return allowedProductsDescription;
	}

	/**
	 * Set the dates for the current loan session.
	 * 
	 * @param askDate
	 *            the date when the loan was asked
	 * @param startDate
	 *            the date when the current loan session will be effective
	 * @param endDate
	 *            the date when the current loan session will be given back
	 */
	public void setDatesCurrentLoan(Calendar askDate, Calendar startDate,
			Calendar endDate) {
		// Set current loan session fields
		this.currentLoan.put("startDate", startDate);
		this.currentLoan.put("endDate", endDate);
		this.currentLoan.put("askDate", askDate);
	}

	/**
	 * Set the start date for the current loan session.
	 * 
	 * @param startDate
	 *            the start date for the current loan session
	 */
	public void setStartDateCurrentLoan(Calendar startDate) {
		this.currentLoan.put("startDate", startDate);
	}

	/**
	 * Set the endDate date for the current loan session.
	 * 
	 * @param endDate
	 *            the endDate date for the current loan session
	 */
	public void setEndDateCurrentLoan(Calendar endDate) {
		this.currentLoan.put("endDate", endDate);
	}

	/**
	 * Set the askDate date for the current loan session.
	 * 
	 * @param askDate
	 *            the askDate date for the current loan session
	 */
	public void setAskDateCurrentLoan(Calendar askDate) {
		this.currentLoan.put("askDate", askDate);
	}

	/**
	 * Set the state of material for the current loan session.
	 * 
	 * @param s
	 *            the state fo material for the current loan session
	 */
	public void setStateCurrentLoan(State s) {
		this.currentLoan.put("startState", s);
	}

	/**
	 * Check if the current user can borrow the specified material in the
	 * specified quantity
	 * 
	 * @param materialID
	 *            the id of the material which the current user wants to borrow
	 * @param quantity
	 *            the quantity of specified material the current user wants to
	 *            borrow
	 * @return true if the current session user can borrow the specified
	 *         material in the specified quantity
	 */
	public boolean checkIfUserCanBorrowQuantity(String materialID, int quantity) {

		// get the copy limitation
		int quantityMax = this.sm.getMaterial(materialID).getCopyLimitation(
				this.um.getCurrentSessionUser().getClass());

		return quantityMax > quantity;
	}

	/**
	 * Check if the current user can borrow the specified material in the
	 * specified quantity
	 * 
	 * @param materialID
	 *            the id of the material which the current user wants to borrow
	 * @param startDate
	 *            the date when the current user wants to take the specified
	 *            material
	 * @param endDate
	 *            the date when the current user wants to give back the
	 *            specified material
	 * @return true if the current session user can borrow the specified
	 *         material between the specified dates
	 */
	public boolean checkIfUserCanBorrowDate(String materialID,
			Calendar startDate, Calendar endDate) {

		// Get the duration limitation for the current session user and the
		// specified material
		int durationMax = this.sm.getMaterial(materialID)
				.getDurationLimitation(
						this.um.getCurrentSessionUser().getClass());

		// Calculate numbers of days between startDate and endDate
		int nbDaysDuration = (int) TimeUnit.MILLISECONDS.toDays(endDate
				.getTimeInMillis() - startDate.getTimeInMillis());

		return durationMax > nbDaysDuration;
	}

	/**
	 * Check if the current user can borrow another item<br>
	 * check if he hasn't reach his copy limitation
	 * 
	 * @return true if the current user can borrow another item false otherwise
	 */
	public boolean checkIfUserCanBorrowAnotherItem() {

		// Get the max loan number for the current session user
		int nbMaxLoan = this.um.getCurrentSessionUser().getMaxNbLoans();

		// Get how many loans the current session user has already made
		int nbLoanAlreadyMade = this.lm.getNbOfUnreturnedLoansFor(this.um
				.getCurrentSessionUser().getID());

		return nbMaxLoan > nbLoanAlreadyMade;
	}

	/**
	 * Check if the specified material is available in the specified quantity<br>
	 * It will use the current loan fields "startDate" and "endDate" to build
	 * the stock description between these dates.
	 * 
	 * @param materialID
	 *            the id of the material
	 * @param quantity
	 *            the quantity of material desired by the current user
	 * @return true if the material is available - false otherwise
	 */
	public boolean checkIfMaterialAvailable(String materialID, int quantity) {

		// Get the current loan fields
		Calendar startDate = (Calendar) this.currentLoan.get("startDate");
		Calendar endDate = (Calendar) this.currentLoan.get("endDate");

		// Get the list of material available between start and end date
		List<String> materialAvailable = this.buildListOfMaterialsAvailable(
				startDate, endDate);

		int res = 0;

		// Run through the material available and check if its the same material
		// group
		for (String currentAvailableMaterialID : materialAvailable) {
			if (this.sm
					.getMaterial(currentAvailableMaterialID)
					.getProductDescription()
					.equals(this.sm.getMaterial(materialID)
							.getProductDescription())) {
				res++;
			}
		}
		return quantity <= res;
	}

	/**
	 * Build list of material available between the two specified dates
	 * 
	 * @param startDate
	 *            start date to check materials available
	 * @param endDate
	 *            start date to check materials available
	 * @return list of material available between the two specified dates
	 */
	public List<String> buildListOfMaterialsAvailable(Calendar startDate,
			Calendar endDate) {
		List<String> listOfMaterialsAvailable = new ArrayList<String>();

		for (String materialID : this.sm.getMaterialIDs()) {
			if (!this.lm.isCurrentlyBorrowed(materialID, startDate, endDate)) {
				listOfMaterialsAvailable.add(materialID);
			}
		}

		return listOfMaterialsAvailable;
	}

	/**
	 * Close the current loan, it will add the current loan description to the
	 * LoanManager and ask to LoanManager to store the complete loans
	 * description
	 */
	public void closeCurrentLoan() {

		Loan l = new Loan(this.currentLoan);
		this.lm.addLoan(l);
		this.lm.store();
	}

	/**
	 * Add the specified material to the current loan<br>
	 * It will also set the "startState" field from the current state of the
	 * material specified by the material id
	 * 
	 * @param materialID
	 *            the id of the material to add
	 */
	@SuppressWarnings("unchecked")
	public void addMaterialToCurrentLoan(String materialID) {
		List<String> materials = new ArrayList<String>();

		if (currentLoan.containsKey("material")) {
			materials = (List<String>) currentLoan.get("material");
		}
		materials.add(materialID);
		currentLoan.put("material", materials);

		List<State> startStates = new ArrayList<State>();
		if (currentLoan.containsKey("startState")) {
			startStates = (List<State>) currentLoan.get("startState");
		}
		startStates.add(this.sm.getMaterial(materialID).getState());

		currentLoan.put("startState", startStates);
	}

	/**
	 * For tests only
	 * 
	 * @return -
	 */
	public UserManager getUserManager() {
		return this.um;
	}

	/**
	 * For tests only
	 * 
	 * @return -
	 */
	public StockManager getStockManager() {
		return this.sm;
	}

	/**
	 * For tests only
	 * 
	 * @return -
	 */
	public LoanManager getLoanManager() {
		return this.lm;
	}

	/**
	 * For tests only
	 * 
	 */
	public void setCurrentLoan(Map<String, Object> loan) {
		this.currentLoan = loan;
	}

	/**
	 * For tests only
	 * 
	 * @return -
	 */
	public Map<String, Object> getCurrentLoan() {
		return this.currentLoan;
	}
}