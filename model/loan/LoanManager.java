package model.loan;

import java.util.ArrayList;
import java.util.List;

import model.material.Material;
import model.material.StockManager;
import model.user.*;

/**
 * This class will provides services to manage loans.
 */
public class LoanManager {
	/**
	 * This list represents all loans unreturned to the school. It contains past
	 * loans unreturned, current loans or future loans.
	 */
	private ArrayList<Loan> unreturnedLoans;
	
	/**
	 * This list contains all old loans. An old loan is a loan which is returned.
	 */
	private ArrayList<Loan> oldLoans;

	public void LoanManagment(ArrayList<Loan> list) {
		this.unreturnedLoans=list; 
		this.oldLoans=new ArrayList<Loan>(); 
	}
/*
	public boolean makeALoan(Loan loan) {
		if (this.isAvailable(loan)
				&& askForALoan(loan.getUser()),loan) {
			this.unreturnedLoans.add(loan);
			return true;
		}
		return false;
	}
	
	/*
	private boolean askForALoan(Borrower user,Loan loan) {
		if(user.getClass().getName().equals("Teacher")){
			return askForATeacher(user, loan);
		}
		else if(user.getClass().getName().equals("Student")){
			return askForAStudent(user,loan); 
		}
		return false; 
	}

	private boolean askForATeacher(Borrower user){
		if(this.checkCourse)
		return false; 
	}
	
	private boolean askForAStudent(IUser user){
		if()
		return false; 
	}

	private int numberOfLoans(IUser user){
		int cpt=0; 
		for(int i=0; i<this.unreturnedLoans.size();i++){
			if(this.unreturnedLoans.get(i).getUser().equals(user){
				
			}
		}
	}
	*/
	private boolean isAvailable(Loan loan){
		for(int i=0; i<this.unreturnedLoans.size();i++){
			if(this.sameDate(this.unreturnedLoans.get(i),loan)){
				return false;
			}
		}
		return true; 
	}
	

	
	/**
	 * Method  sameDate. This method returns true if loans date are identical. Specifically, if loans interval (difference 
	 * between end date and start date) overlap.
	 * @param loan
	 * @param loan2
	 * @return
	 */
	private boolean sameDate(Loan loan, Loan loan2) {
		
		return false;
	}


	/**
	 * 
	 * @param material
	 *//*
	public void turnBackMaterial(Material material) {
		int i = 0;
		for (i = 0; i < this.unreturnedLoans.size()
				| !(this.unreturnedLoans.get(i).equals(material)); i++)
			;
		if (this.unreturnedLoans.get(i).equals(material)) {
			this.oldLoans.add(this.unreturnedLoans.get(i));
			this.unreturnedLoans.remove(i);
		} else {
			System.out.println("The material " + material.getId()
					+ " wasn't loan");
		}
	}
*/
	/**
	 * This method allows to find a Loan thanks to a material list.
	 * 
	 * @param material
	 * @return
	 */

	public Loan returnLoan(ArrayList<Material> material) {
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			if (Loan.sameList(this.unreturnedLoans.get(i).getMaterial(),
					material)) {
				return this.unreturnedLoans.get(i);
			}
		}
		return null;
	}

	/**
	 * This method removes the loan corresponding at the material in the
	 * unreturned loans list and puts this loan in the old loan list.
	 * 
	 * @param material
	 */

	public void turnBackMaterial(ArrayList<Material> material) {
		Loan loan= returnLoan(material);
		if (loan != null) {
			this.oldLoans.add(loan);
			this.unreturnedLoans.remove(loan); ////// A TESTER
		}
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