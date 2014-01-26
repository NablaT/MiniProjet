package model.loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Manager;
import model.Model;
import model.material.Material;
import model.user.IUser;
import model.util.ConfigXML;

/**
 * This class will provides services to manage loans.
 */
public class LoanManager implements Manager {

	public static final String KEY_LOAN_FILE = "loans";
	public static final String KEY_LOAN_VERSION = "0.0.0";

	/**
	 * This list represents all loans unreturned to the school. It contains past
	 * loans unreturned, current loans or future loans.
	 */
	private ArrayList<Loan> unreturnedLoans = new ArrayList<Loan>();

	/**
	 * This list contains all old loans. An old loan is a loan which is
	 * returned.
	 */
	private ArrayList<Loan> oldLoans = new ArrayList<Loan>();

	private Model model;

	public void LoanManagment(Model m, ArrayList<Loan> list) {
		this.unreturnedLoans = list;
		this.oldLoans = new ArrayList<Loan>();
		this.model = m;
	}

	public int getNbOfUnreturnedLoansFor(String userID) {
		int result = 0;

		for (Loan l : this.unreturnedLoans) {
			if (l.getUser().getID().equals(userID)) {
				result++;
			}
		}

		return result;
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
		Map<String, Integer> oldMaterial = getAllMaterialsLoan(loan.getUser());
		Map<String, Integer> newMaterial = getAllMaterialsHeWants(loan);

		for (int i = 0; i < loan.getListOfMaterials().size(); i++) {

			String currentMaterialID = loan.getListOfMaterials().get(i);
			int copyLimitation = this.model.getCopyLimitation(loan.getUser()
					.getClass(), currentMaterialID);

			// It represents how much copy of the current material
			// the current user has already loan
			int numberOfMaterialBorrowed = oldMaterial.get(currentMaterialID);
			numberOfMaterialBorrowed += newMaterial.get(currentMaterialID);

			if (copyLimitation < numberOfMaterialBorrowed) {
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

	private HashMap<String, Integer> getAllMaterialsLoan(IUser user) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();

		// Run through the unreturned loans
		for (Loan currentUnreturnedLoan : this.unreturnedLoans) {

			// Check if the current loan concerned the specified user
			if (currentUnreturnedLoan.getUser().equals(user)) {

				// Run through the material contained in the current loan
				for (String materialID : currentUnreturnedLoan
						.getListOfMaterials()) {

					String key = this.model.getProductDescription(materialID);
					// Update the hashMap content
					if (hm.containsKey(key)) {
						hm.put(key, hm.get(key) + 1);
					} else {
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

	private HashMap<String, Integer> getHMOfMaterial(List<String> list) {

		HashMap<String, Integer> hm = new HashMap<String, Integer>();

		for (String materialID : list) {
			String key = this.model.getNameOf(materialID);
			if (hm.containsKey(key)) {
				hm.put(key, hm.get(key) + 1);
			} else {
				hm.put(key, 1);
			}
		}

		// for (int i = 0; i < list.size(); i++) {
		// // Si on a deja mis le materiel dans l'hashmap alors on incremente
		// // juste le nombre
		// if (hm.get(list.get(i).getName()) != null) {
		// hm.put(list.get(i).getName(), hm.get(list.get(i).getName()) + 1);
		// }
		// // Sinon on le rajoute a l'hashmap.
		// else {
		// hm.put(list.get(i).getName(), 1);
		// }
		// }

		return hm;

	}

	private HashMap<String, Integer> getAllMaterialsHeWants(Loan loan) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();

		for (String materialID : loan.getListOfMaterials()) {

			String key = this.model.getProductDescription(materialID);

			if (hm.containsKey(key)) {
				hm.put(key, hm.get(key) + 1);
			} else {
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
		// int cpt = 0;
		// Pour chaque materiel que l'emprunteur veut, on verifie qu'il se
		// trouve bien dans la liste des
		// materiels disponibles pour ce cours là.

		// Run through the material contained in the loan
		for (String materialID : loan.getListOfMaterials()) {

			
					// Check if the current material is contained in the list
			// of allowed product in the loan'course
			if (!this.model.getAllowedProductsForSpecifiedCourse(loan.getCourse()).contains(materialID)) {
				return false;
			}
		}

		// for (int i = 0; i < loan.getListOfMaterials().size(); i++) {
		// for (int j = 0; j < loan.getCourse().getAllowedProducts().size();
		// i++) {
		// if (loan.getListOfMaterials().get(i).getId()
		// .equals(loan.getCourse().getAllowedProducts().get(j))) {
		// cpt++;
		// break;
		// }
		// }
		// }
		// if (cpt == loan.getListOfMaterials().size()) {
		// return true;
		// }

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
		HashMap<String, Integer> newHm = this.getHMOfMaterial(loan
				.getListOfMaterials());

		for (int i = 0; i < this.unreturnedLoans.size(); i++) {
			/*
			 * Si la date est la m�me: On verifie si ce sont les mêmes materiels
			 */
			if (sameDate(this.unreturnedLoans.get(i), loan)) {
				HashMap<String, Integer> currentHm = this
						.getHMOfMaterial(this.unreturnedLoans.get(i)
								.getListOfMaterials());

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
			if (this.unreturnedLoans.get(i).getListOfMaterials()
					.equals(material)) {
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

	// TODO DELETE THIS TOKENS AFTER TESTS
	public void addLoan(Loan l) {
		this.unreturnedLoans.add(l);
	}

	public boolean isCurrentlyBorrowed(String materialID, Calendar startDate,
			Calendar endDate) {

		for (Loan l : this.unreturnedLoans) {
			if ((l.getStartDate().after(startDate) 
					&& l.getStartDate().before(endDate))
					|| (l.getEndDate().after(startDate)
					&& l.getEndDate().before(endDate))
					&& l.getListOfMaterials().contains(materialID)) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public void restore(Map<String, Object> description) {

		List<Map<String, Object>> unreturnedLoanDescription = (List<Map<String, Object>>) description
				.get("unreturnedLoans");
		List<Map<String, Object>> oldLoansDescription = (List<Map<String, Object>>) description
				.get("oldLoans");

		for (Map<String, Object> currentUnreturnedLoanDescription : unreturnedLoanDescription) {
			Loan currentUnreturnedLoan = new Loan(
					currentUnreturnedLoanDescription);
			this.unreturnedLoans.add(currentUnreturnedLoan);
		}

		for (Map<String, Object> currentOldLoanDescription : oldLoansDescription) {
			Loan currentOldLoan = new Loan(currentOldLoanDescription);
			this.oldLoans.add(currentOldLoan);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void load() {
		Map<String, Object> description = new HashMap<String, Object>();

		description = (Map<String, Object>) ConfigXML.load(KEY_LOAN_FILE,
				KEY_LOAN_VERSION);

		this.restore(description);
	}

	@Override
	public void store() {
		Map<String, Object> description = new HashMap<String, Object>();

		List<Map<String, Object>> unreturnedLoanDescription = new ArrayList<Map<String, Object>>();
		for (Loan currentUnreturnedLoan : unreturnedLoans) {
			unreturnedLoanDescription.add(currentUnreturnedLoan
					.getDescription());
		}

		List<Map<String, Object>> oldLoans = new ArrayList<Map<String, Object>>();
		for (Loan currentOldLoan : this.oldLoans) {
			unreturnedLoanDescription.add(currentOldLoan.getDescription());
		}

		description.put("unreturnedLoans", unreturnedLoanDescription);
		description.put("oldLoans", oldLoans);

		ConfigXML.store(description, KEY_LOAN_FILE, KEY_LOAN_VERSION);
	}

	public String displayUnreturnedLoans() {
		String result = "";

		for (Loan l : this.unreturnedLoans) {
			result += l.toString();
		}

		return result;
	}
}