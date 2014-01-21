package model.loan;

import java.util.ArrayList;

/**
 * This class will provides services to manage loans.
 */
public class LoanManager {
	/**
	 * This list represents all loans unreturned to the school.
	 * It contains past loans unreturned, current loans or future loans.
	 */
	private ArrayList<Loan> unreturnedLoans;
	/** 
	 * This list contains all old loans. An old loan is a loan wich is returned.
	 */
	private ArrayList<Loan> oldLoans;
	
	
	public void LoanManagment(Object list) {
		
	}

	public void turnBackMaterial(Object material) {
		
	}

	public void setUnreturnedLoans(ArrayList<Loan> unreturnedLoans) {
		this.unreturnedLoans = unreturnedLoans;
	}

	public ArrayList<Loan> getUnreturnedLoans() {
		return this.unreturnedLoans;
	}

	public void setOldLoans(ArrayList<Loan> oldLoans) {
		this.oldLoans = oldLoans;
	}

	public ArrayList<Loan> getOldLoans() {
		return this.oldLoans;
	}
}