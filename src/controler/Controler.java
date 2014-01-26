package controler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import model.Model;
import view.View;

public class Controler {

	private Model model;
	private View view;

	Calendar today = Calendar.getInstance();

	public Controler() {
		today.set(2013, 9, 18);

		this.model = new Model();

		View.store();
		this.view = new View(this);
		this.view.init();

		this.view.askUserType(this.model.getUserTypes());
	}

	public void notifyUserTypeSelected(String userType) {
		this.model.setCurrentSessionType(userType);
		String potentialID = this.view.askUserLogin();
		while (!this.model.tryToConnect(potentialID)) {
			this.view.askUserLogin_fail();
			potentialID = this.view.askUserLogin();
		}
		this.view.askUserLogin_success();

		List<String> actions = new ArrayList<String>();
		actions.add("Make a loan");
		actions.add("Give back a loan");

		this.view.askActionType(actions);

	}

	public void notifyActionTypeSelected(String action) {
		switch (action) {
		case "Make a loan":
			
			List<Map<String, Object>> courseDescription = new ArrayList<Map<String, Object>>();
			
			courseDescription = this.model
					.getAllowedCoursesDescriptionForCurrentUser(today);
			
			if(courseDescription.size() == 0 ) {
				this.view.informNoMoreCoursesAvailable();
				return;
				
			}
			
			this.view.askStartDate();
			break;
		case "Give back a loan":
			break;
		default:
			break;
		}
	}
	
	public void notifyStartDateSelected(Calendar startDate) {
		this.model.setStartDateCurrentLoan(startDate);
		this.model.setAskDateCurrentLoan(today);
		
		List<Map<String, Object>> courseDescription = new ArrayList<Map<String, Object>>();
		
		courseDescription = this.model
				.getAllowedCoursesDescriptionForCurrentUser(startDate);
		
		if(courseDescription.size() == 0 ) {
			this.view.informNoMoreCoursesAvailable();
			return;
		}
		
		this.view.askEndDate();
	}
	

	public void notifyEndDateSelected(Calendar endDate) {
		this.model.setEndDateCurrentLoan(endDate);
		
		List<Map<String, Object>> courseDescription = new ArrayList<Map<String, Object>>();
		
		courseDescription = this.model
				.getAllowedCoursesDescriptionForCurrentUser(today);
		
		if(courseDescription.size() == 0 ) {
			this.view.informNoMoreCoursesAvailable();
			return;
		}
		
		this.view.askCourse(courseDescription);
	}

	
	
	public void notifyCourseSelected(int courseID) {

		this.view.askLoanMaterialID(this.model.getAllowedProductsDescription(
				courseID, today));
	}

	public boolean checkIfMaterialAvailable(String materialID, Integer quantity) {
		if( this.model.checkIfUserCanBorrowQuantity(materialID, quantity)
				&& this.model.checkIfMaterialAvailable(materialID, quantity)) 
		{
			this.model.addMaterialToCurrentLoan(materialID);
			this.model.closeCurrentLoan();
			return true;
		}
		return false;
	}
}
