package model.loan;

import java.util.Calendar;
import java.util.List;

import model.State;
import model.material.Material;
import model.user.User;


/**
 * A loan contains the list of material concerned, who made it, start date and end date.
 * It also contains start states and end states for all material.
 */
public class Loan {
	private List<Material> material;
	private Calendar startDate;
	private Calendar endDate;
	private User user;
	private boolean gaveBack;
	private List<State> startState;
	private List<State> endState;

	//  
	public List<Class<? extends Material>> getTypesOfMaterial() {
		throw new UnsupportedOperationException();
	}

	public int getNumberOf(Object classe) {
		throw new UnsupportedOperationException();
	}

	public int getNumberOfDayOfDelay(Object calendar_currentDate) {
		throw new UnsupportedOperationException();
	}

	public boolean isOverdue(Object calendar_currentDate) {
		throw new UnsupportedOperationException();
	}
}