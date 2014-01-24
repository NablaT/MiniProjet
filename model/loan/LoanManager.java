package model.loan;

import java.util.ArrayList;
import java.util.HashMap;
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
	 * This list contains all old loans. An old loan is a loan which is
	 * returned.
	 */
	private ArrayList<Loan> oldLoans;

	public void LoanManagment(ArrayList<Loan> list) {
		this.unreturnedLoans = list;
		this.oldLoans = new ArrayList<Loan>();
	}

	/**
	 * Method makeALoan. This method returns true if the reservation succeed.
	 * Else false.
	 * 
	 * @param loan
	 * @return
	 */

	public boolean makeALoan(Loan loan) {
		if (this.isAvailable(loan) && askForALoan(loan)) {
			this.unreturnedLoans.add(loan);
			return true;
		}
		return false;
	}

	/**
	 * This method allows to check if
	 * 
	 * @param user
	 * @param loan
	 * @return
	 */
	private boolean askForALoan(Loan loan) {
		if (loan.getUser().getClass().getName().equals("Teacher")) {
			return askForATeacher(loan);
		} else if (loan.getUser().getClass().getName().equals("Student")) {
			return askForAStudent(loan);
		}
		return false;
	}

	private boolean askForATeacher(Loan loan) {
		return (this.checkCourses(loan) && this.notLate(loan) && canBorrow(loan));
	}

	/**
	 * Method canBorrow. A teacher can borrow if he don't exceed his loans
	 * number.
	 * 
	 * @param user
	 * @param loan
	 * @return
	 */
	private boolean canBorrow(Loan loan) {
		//If the loans number is less than the max loans number and if 

		HashMap<Class<? extends Material>, Integer> oldMaterial=getAllMaterialsLoan(loan.getUser()) ;
		HashMap<Class<? extends Material>, Integer> newMaterial=getAllMaterialsHeWants(loan);
		Class<? extends Material> material = loan.getMaterial().get(0); 
		for(int i=0; i<loan.getMaterial().size(); i++){
			//if(loan.getMaterial().get(i).getCopyLimitation())
		}
		return false;
	}

	/**
	 * This methods returns the loans number for a user.
	 * 
	 * @param user
	 * @return
	 */
/*
	private int howManyLoan(User user) {
		int cpt = 0;
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			if (this.unreturnedLoans.get(i).getUser().equals(user)) {
				cpt++;
			}
		}
		return cpt;
	}*/

	/**
	 * This method returns an hashmap which contains for each material loaning
	 * by the user the quantity of this material <Key = material, value= quantity>
	 * 
	 * @param user
	 * @return
	 */
	private HashMap<Class<? extends Material>, Integer> getAllMaterialsLoan(
			User user) {
		HashMap<Class<? extends Material>, Integer> hm = new HashMap<Class<? extends Material>, Integer>();
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			// Dans la liste des emprunts, si un emprunt est a été fait par le
			// user alors
			if (this.unreturnedLoans.get(i).getUser().equals(user)) {
				// On parcourt les materiels empruntés
				for (int j = 0; j < this.unreturnedLoans.get(i).getMaterial()
						.size(); j++) {
					Class<? extends Material> m = this.unreturnedLoans.get(i)
							.getMaterial().get(j);
					// Si on a deja mis le materiel dans l'hasmap alors on
					// incremente juste le nombre
					if (hm.get(m) != null) {
						hm.put(m, hm.get(m) + 1);
					}
					// Sinon on le rajoute a l'hashmap.
					else {
						hm.put(m, 1);
					}
				}
			}
		}
		return hm;

	}

	/**
	 * Returns an hashmap which contains for each material ask its quantity. 
	 * <key = material, value = quantity>
	 * @param loan
	 * @return
	 */
	private HashMap<Class<? extends Material>, Integer> getAllMaterialsHeWants(
			Loan loan) {
		HashMap<Class<? extends Material>, Integer> hm = new HashMap<Class<? extends Material>, Integer>();
		for (int i = 0; i < loan.getMaterial().size(); i++) {
			Class<? extends Material> m = this.unreturnedLoans.get(i)
					.getMaterial().get(i);
			// Si on a deja mis le materiel dans l'hasmap alors on incremente
			// juste le nombre
			if (hm.get(m) != null) {
				hm.put(m, hm.get(m) + 1);
			}
			// Sinon on le rajoute a l'hashmap.
			else {
				hm.put(m, 1);
			}

		}
		return hm;

	}

	private int howManyMaterialCurrentlyLoan(Material material, User user) {
		int cpt = 0;
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			// Si le loan i a été fait par le user alors on regarde combien de
			// fois il a emprunté le materiel en question
			if (this.unreturnedLoans.get(i).getUser().equals(user)) {
				for (int j = 0; j < this.unreturnedLoans.get(i).getMaterial()
						.size(); j++) {
					if (this.unreturnedLoans.get(i).getMaterial().get(j)
							.equals(material)) {
						cpt++;
					}
				}
			}
		}
		return cpt;
	}

	/**
	 * Returns true if the user is not late. He could be late with a current
	 * loan if the delay of this loan is out of date
	 * 
	 * @param user
	 * @return
	 */
	private boolean notLate(Loan loan) {
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			if (this.unreturnedLoans.get(i).getUser().equals(loan.getUser())
					&& this.unreturnedLoans.get(i).isOverdue(loan.getAskDate())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method returns true if the borrower asks the good material for the
	 * good course.
	 * 
	 * @param loan
	 * @return
	 */
	private boolean checkCourses(Loan loan) {

		return false;
	}

	/**
	 * This method verifies if the loan is possible for a Student.
	 * 
	 * @param loan
	 * @return
	 */
	private boolean askForAStudent(Loan loan) {
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			if (this.unreturnedLoans.get(i).getUser().equals(loan.getUser())) {
				return true;
			}
		}
		return true;
	}

	/*
	 * private int numberOfLoans(User user){ int cpt=0; for(int i=0;
	 * i<this.unreturnedLoans.size();i++){
	 * if(this.unreturnedLoans.get(i).getUser().equals(user){
	 * 
	 * } } }
	 */
	private boolean isAvailable(Loan loan) {
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			if (this.sameDate(this.unreturnedLoans.get(i), loan)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method sameDate. This method returns true if loans date are identical.
	 * Specifically, if loans interval (difference between end date and start
	 * date) overlap.
	 * 
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
	 */
	/*
	 * public void turnBackMaterial(Material material) { int i = 0; for (i = 0;
	 * i < this.unreturnedLoans.size() |
	 * !(this.unreturnedLoans.get(i).equals(material)); i++) ; if
	 * (this.unreturnedLoans.get(i).equals(material)) {
	 * this.oldLoans.add(this.unreturnedLoans.get(i));
	 * this.unreturnedLoans.remove(i); } else {
	 * System.out.println("The material " + material.getId() + " wasn't loan");
	 * } }
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
		Loan loan = returnLoan(material);
		if (loan != null) {
			this.oldLoans.add(loan);
			this.unreturnedLoans.remove(loan); // //// A TESTER
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