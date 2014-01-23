package model.loan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.State;
import model.material.Material;
import model.user.IUser;

/**
 * A loan contains the list of material concerned, who made it, start date and
 * end date. It also contains start states and end states for all material.
 */
public class Loan {
	private List<Class<? extends Material>> material;
	private Calendar startDate;
	private Calendar endDate;
	private IUser user;
	private boolean gaveBack;
	private List<State> startState;
	private List<State> endState; 

	public Loan(List<Class<? extends Material>> material, Calendar startDate,
			Calendar endDate, IUser user, List<State> startState) {
		this.material = material;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.gaveBack = false;
		this.startState = startState;
		this.endState = new ArrayList<State>();
	}

	public List<Class<? extends Material>> getMaterial() {
		return this.material;
	}

	public Calendar getStartDate() {
		return this.startDate;
	}

	public Calendar getEndDate() {
		return this.endDate;
	}

	public IUser getUser() {
		return this.user;
	}

	public boolean getGaveBack() {
		return this.gaveBack;
	}

	public void setGaveBack(boolean gaveBack) {
		this.gaveBack = gaveBack;
	}

	public List<State> getStartState() {
		return this.startState;
	}

	public List<State> getEndState() {
		return this.endState;
	}

	public void setEndState(List<State> listState) {
		this.endState = listState;
	}

	public void setStartDate(List<State> listState) {
		this.startState = listState;
	}

	public void setUser(IUser user) {
		this.user = user;
	}

	public void setEndDate(Calendar date) {
		this.endDate = date;
	}

	public void setStartDate(Calendar date) {
		this.startDate = date;
	}

	public void setMaterial(List<Class<? extends Material>> list) {
		this.material = list;
	}

	/**
	 * Methode getTypesOfMaterial. Cette methode retourne la liste des materials
	 * du même type de la classe specifiée en parametre.
	 * 
	 * @return
	 */
	public List<Class<? extends Material>> getTypesOfMaterial(
			Class<? extends Material> classe) {
		ArrayList<Class<? extends Material>> list = new ArrayList<Class<? extends Material>>();
		for (int i = 0; i < this.material.size(); i++) {
			if (this.material.get(i).getClass().getName()
					.equals(classe.getClass().getName())) {
				list.add(this.material.get(i));
			}
		}
		return list;
	}

	/**
	 * Methode getNumberOf. Cette methode retourne le nombre de material
	 * specifique se trouvant dans la liste de material.
	 * 
	 * @param classe
	 * @return
	 */
	public int getNumberOf(Class<? extends Material> classe) {
		return (this.getTypesOfMaterial(classe).size());

	}

	/**
	 * Methode getNumberOfDayOfDelay. Cette methode retourne le nombre de jour
	 * qu'il reste avant de rendre le ou les materials. Si le chiffre renvoyer
	 * est négatif, cela veut dire que le borrower est en retard. Si c'est égal
	 * à 0 le borrower doit rendre ce qu'il a emprunté aujourd'hui.
	 * 
	 * @param calendar_currentDate
	 * @return
	 */
	public double getNumberOfDayOfDelay(Calendar calendar_currentDate) {
		return this.endDate.getTimeInMillis()
				- calendar_currentDate.getTimeInMillis();
	}

	/**
	 * This method returns true if the loan is overdue. Else false.
	 * 
	 * @param calendar_currentDate
	 * @return
	 */
	public boolean isOverdue(Calendar calendar_currentDate) {
		return (this.getNumberOfDayOfDelay(calendar_currentDate) < 0);
	}

	/**
	 * This method compare two loans instances and returns true if they are
	 * identical.
	 * 
	 * @param loan
	 * @return
	 */
	public boolean equals(Loan loan) {
		return (this.material.equals(loan.getMaterial())
				&& (this.sameDate(startDate, loan.getStartDate()))
				&& (this.sameDate(this.endDate, loan.getEndDate()))
				&& this.user.equals(loan.getUser())
				&& (this.gaveBack == loan.getGaveBack())
				&& sameList(this.startState, loan.getStartState()) && sameList(
					this.endState, loan.getEndState()));
	}

	/**
	 * This method compare two lists and it returns true if they are identical. 
	 * @param l1
	 * @param l2
	 * @return
	 */
	
	public static boolean sameList(List l1, List l2) {
		if (l1.size() != l2.size()) {
			return false;
		}
		for (int i = 0; i < l1.size(); i++) {
			if (!(l1.get(i).equals(l2.get(i)))) {
				return false;
			}
		}
		return true;

	}

	/**
	 * This method, used in equals, compare two Calendar and return true if they
	 * have the same day in the same year.
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	private boolean sameDate(Calendar c1, Calendar c2) {
		return (c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR) && c1
				.get(Calendar.YEAR) == c2.get(Calendar.YEAR));
	}
}