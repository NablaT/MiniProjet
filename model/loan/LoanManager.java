package model.loan;

import java.util.ArrayList;

import model.material.Material;

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
	 * This list contains all old loans. An old loan is a loan wich is returned.
	 */
	private ArrayList<Loan> oldLoans;

	public void LoanManagment(Object list) {

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
			this.unreturnedLoans.remove(loan); 
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