package model.loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


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
	 * This is the first step for a loan. This method returns true if the
	 * reservation succeed. Else false. It calls two methods: isAvailable and
	 * askForALoan.
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
	 * This method allows to check if a borrower can do this loan. It calls two
	 * methods, askForATeacher if the user is a teacher and askForAStudent if
	 * the user is a student.
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

	/**
	 * Three methods called. They etablish the three prerogative which verify if
	 * the user can borrow. 1 - User has to ask material which correspond to his
	 * courses. 2 - User hasn't be late on others loans. 3 - User can't ask more
	 * material than he cans.
	 * 
	 * @param loan
	 * @return
	 */
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
		HashMap<String, Integer> oldMaterial = getAllMaterialsLoan(loan
				.getUser());
		HashMap<String, Integer> newMaterial = getAllMaterialsHeWants(loan);
		for (int i = 0; i < loan.getMaterial().size(); i++) {
			if (loan.getMaterial().get(i).getCopyLimitation(loan.getUser()) < (oldMaterial
					.get(loan.getMaterial().get(i)))
					+ newMaterial.get(loan.getMaterial().get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This methods returns the loans number for a user.
	 * 
	 * @param user
	 * @return
	 */
	/*
	 * private int howManyLoan(User user) { int cpt = 0; for (int i = 0; i <
	 * this.unreturnedLoans.size(); i++) { if
	 * (this.unreturnedLoans.get(i).getUser().equals(user)) { cpt++; } } return
	 * cpt; }
	 */

	/**
	 * This method returns an hashmap which contains for each material loaning
	 * by the user the quantity of this material <Key = brandName+version,
	 * value= quantity>
	 * 
	 * @param user
	 * @return
	 */

	private HashMap<String, Integer> getAllMaterialsLoan(User user) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			// Dans la liste des emprunts, si un emprunt est a été fait par le
			// user alors
			if (this.unreturnedLoans.get(i).getUser().equals(user)) {
				// On parcourt les materiels empruntés
				for (int j = 0; j < this.unreturnedLoans.get(i).getMaterial()
						.size(); j++) {
					// Si on a deja mis le materiel dans l'hasmap alors on
					// incremente juste le nombre
					String key = this.unreturnedLoans.get(i).getMaterial()
							.get(j).getProductDescription();
					if (hm.get(key) != null) {
						hm.put(key, hm.get(key) + 1);
					}
					// Sinon on le rajoute a l'hashmap.
					else {
						hm.put(key, 1);
					}
				}
			}
		}
		return hm;
	}

	/**
	 * Returns an hashmap which contains for each material ask its quantity.
	 * <key = brandName+version, value = quantity>
	 * 
	 * @param loan
	 * @return
	 */

	private HashMap<String, Integer> getHMOfMaterial(
			List<? extends Material> list) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			// Si on a deja mis le materiel dans l'hashmap alors on incremente
			// juste le nombre
			if (hm.get(list.get(i).getName()) != null) {
				hm.put(list.get(i).getName(), hm.get(list.get(i).getName()) + 1);
			}
			// Sinon on le rajoute a l'hashmap.
			else {
				hm.put(list.get(i).getName(), 1);
			}

		}
		return hm;

	}

	private HashMap<String, Integer> getAllMaterialsHeWants(Loan loan) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (int i = 0; i < loan.getMaterial().size(); i++) {
			// ? extends Material m = this.unreturnedLoans.get(i)
			// .getMaterial().get(i);
			// Si on a deja mis le materiel dans l'hasmap alors on incremente
			// juste le nombre
			String key = this.unreturnedLoans.get(i).getMaterial().get(i)
					.getProductDescription();
			if (hm.get(key) != null) {
				hm.put(key, hm.get(key) + 1);
			}
			// Sinon on le rajoute a l'hashmap.
			else {
				hm.put(key, 1);
			}

		}
		return hm;

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
		int cpt = 0;
		// Pour chaque materiel que l'emprunteur veut, on verifie qu'il se
		// trouve bien dans la liste des
		// materiels disponibles pour ce cours là.
		for (int i = 0; i < loan.getMaterial().size(); i++) {
			for (int j = 0; j < loan.getCourse().getAllowedProduct().size(); i++) {
				if (loan.getMaterial().get(i).getId()
						.equals(loan.getCourse().getAllowedProduct().get(j))) {
					cpt++;
					break;
				}
			}
		}
		if (cpt == loan.getMaterial().size()) {
			return true;
		}
		return false;
	}

	private ArrayList<String> returnTypeOfMaterial(ArrayList<Material> list) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {

		}
		return result;
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

	/**
	 * This method verify if all of the asked material is free.
	 * 
	 * @param loan
	 * @return
	 */
	private boolean isAvailable(Loan loan) {

		for (int i = 0; i < this.unreturnedLoans.size(); i++) {

		}
		return true;
	}

	/**
	 * This method returns the material list which can't be loaning.
	 * 
	 * @param loan
	 * @return
	 */

	public ArrayList<Loan> saveBadLoan(Loan loan) {
		ArrayList<Loan> loansNotLoanable = new ArrayList<Loan>();
		HashMap<String, Integer> newHm=this.getHMOfMaterial(loan.getMaterial()); 
		
		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			/*
			 * Si la date est la même: On verifie si ce sont les mêmes materiels
			 */
			if (sameDate(this.unreturnedLoans.get(i), loan)) {
				HashMap<String, Integer> currentHm= this.getHMOfMaterial(this.unreturnedLoans.get(i).getMaterial());
				
				
			}
		}
		return loansNotLoanable;
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
		return (!(loan.getEndDate().before(loan2.getStartDate())) || (loan2
				.getStartDate().before(loan.getStartDate()) && loan2
				.getEndDate().before(loan.getStartDate())));
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