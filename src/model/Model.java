package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import model.course.CourseManager;
import model.loan.Loan;
import model.loan.LoanGenerator;
import model.loan.LoanManager;
import model.material.StockManager;
import model.user.IUser;
import model.user.UserManager;

public class Model {

	private StockManager sm;
	private UserManager um;
	private LoanManager lm;
	private CourseManager cm;

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

	public List<String> getUserTypes() {
		return this.um.getUserTypes();
	}

	public void setCurrentSessionType(String type) {
		this.um.setCurrentSessionType(this.um.getFullName(type));

	}

	public int getCopyLimitation(Class<? extends IUser> classe,
			String materialID) {
		return this.sm.getMaterial(materialID).getCopyLimitation(classe);
	}

	public String getProductDescription(String materialID) {
		return this.sm.getMaterial(materialID).getProductDescription();
	}

	public List<String> getAllowedProductsForSpecifiedCourse(int courseId) {
		return this.cm.getCourse(courseId).getAllowedProducts();
	}

	public String getNameOf(String materialID) {
		return this.sm.getMaterial(materialID).getName();
	}

	public boolean tryToConnect(String potentialID) {
		if (!this.um.isBorrowerRegistred(potentialID)) {
			return false;
		}
		if (this.um.setCurrentSession(this.um.connectUser(potentialID))) {
			Map<String, Object> userDescription = this.um
					.getCurrentSessionUser().getDescription();
			userDescription.put("class", this.um.getCurrentSessionUser()
					.getClass());
			this.currentLoan.put("user", userDescription);
			return true;
		}
		return false;
	}

	public List<Map<String, Object>> getAllowedCoursesDescriptionForCurrentUser(
			Calendar startDate) {
		List<Map<String, Object>> allowedCoursesDescription = new ArrayList<Map<String, Object>>();

		for (Integer courseID : this.um.getCurrentSessionUser()
				.getListCoursesId()) {

			DateFormat ndf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String todayStr = ndf.format(startDate.getTime());
			String courseStartDate = ndf.format(this.cm.getCourse(courseID)
					.getStartDate().getTime());

			if ( (startDate.compareTo(this.cm.getCourse(courseID).getStartDate()) > 0)) {
				continue;
			}

			allowedCoursesDescription.add(this.cm
					.getCourseDescription(courseID));
		}

		return allowedCoursesDescription;
	}

	public List<Map<String, Object>> getAllowedProductsDescription(
			int courseID, Calendar today) {
		this.currentLoan.put("courseId", courseID);
		this.currentLoan.put("gaveBack", false);

		List<Map<String, Object>> allowedProductsDescription = new ArrayList<Map<String, Object>>();

		List<String> listIDAllowedProducts = (List<String>) (this.cm
				.getCourseDescription(courseID)).get("productsAllowed");

		int daysBetweenTodayAndBeginningOfCourse = 0;

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

	public void setDatesCurrentLoan(Calendar askDate, Calendar startDate,
			Calendar endDate) {
		this.currentLoan.put("startDate", startDate);
		this.currentLoan.put("endDate", endDate);
		this.currentLoan.put("askDate", askDate);
	}

	public void setStartDateCurrentLoan(Calendar startDate) {
		this.currentLoan.put("startDate", startDate);
	}

	public void setEndDateCurrentLoan(Calendar endDate) {
		this.currentLoan.put("endDate", endDate);
	}

	public void setAskDateCurrentLoan(Calendar askDate) {
		this.currentLoan.put("askDate", askDate);
	}

	public void setStateCurrentLoan(State s) {
		this.currentLoan.put("startState", s);
	}

	public boolean checkIfUserCanBorrowQuantity(String materialID, int quantity) {
		int quantityMax = this.sm.getMaterial(materialID).getCopyLimitation(
				this.um.getCurrentSessionUser().getClass());

		return quantityMax > quantity;
	}

	public boolean checkIfUserCanBorrowDate(String materialID,
			Calendar startDate, Calendar endDate) {
		int durationMax = this.sm.getMaterial(materialID)
				.getDurationLimitation(
						this.um.getCurrentSessionUser().getClass());
		int nbDaysDuration = (int) TimeUnit.MILLISECONDS.toDays(endDate
				.getTimeInMillis() - startDate.getTimeInMillis());

		return durationMax > nbDaysDuration;
	}

	public boolean checkIfUserCanBorrowAnotherItem() {
		int nbMaxLoan = this.um.getCurrentSessionUser().getMaxNbLoans();
		int nbLoanAlreadyMade = this.lm.getNbOfUnreturnedLoansFor(this.um
				.getCurrentSessionUser().getID());

		return nbMaxLoan > nbLoanAlreadyMade;
	}

	public boolean checkIfMaterialAvailable(String materialID, int quantity) {

		Calendar startDate = (Calendar) this.currentLoan.get("startDate");
		Calendar endDate = (Calendar) this.currentLoan.get("endDate");

		List<String> materialAvailable = this.buildListOfMaterialsAvailable(
				startDate, endDate);

		int res = 0;

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

	public void closeCurrentLoan() {

		Loan l = new Loan(this.currentLoan);
		this.lm.addLoan(l);
		this.lm.store();
	}

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
}