package model.loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.State;
import model.course.Course;
import model.user.IUser;
import model.user.UserManager;

public abstract class LoanGenerator {

	public static void storeLoansDescription(LoanManager lm, UserManager um) {
		Map<String, Object> descriptionLoan1 = new HashMap<String, Object>();
		
		Calendar c = Calendar.getInstance();
		
		c.set(2014, 1, 30);
		descriptionLoan1.put("startDate", c.clone());
		
		c.set(2014, 2, 15);
		descriptionLoan1.put("endDate", c.clone());
		
		c.set(2014, 1, 15);
		descriptionLoan1.put("askDate", c.clone());

		List<State> startStates = new ArrayList<State>();
		startStates.add(State.GOOD);
		startStates.add(State.UNUSED);
		descriptionLoan1.put("startState", startStates);

		List<State> endStates = new ArrayList<State>();
		descriptionLoan1.put("endState", endStates);
		
		descriptionLoan1.put("gaveBack", false);
	
		Map<String, Object> userDescription = new HashMap<String, Object>();
		
		IUser u = um.usersToList().get(0);
		
		userDescription = u.getDescription();
		userDescription.put("class", u.getClass());
		
		
		descriptionLoan1.put("user", userDescription);	

		List<String> borrowedMaterialIDs = new ArrayList<String>();
		borrowedMaterialIDs.add("CW22");
		borrowedMaterialIDs.add("GS37");
		
		descriptionLoan1.put("material", borrowedMaterialIDs);
		
		descriptionLoan1.put("courseId", 1);
		
		Loan l = new Loan(descriptionLoan1);
		lm.addLoan(l);
		
		lm.store();
	}

}
