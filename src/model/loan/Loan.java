package model.loan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.State;
import model.course.Course;
import model.user.IUser;
import model.user.UserManager;

/**
 * A loan contains the list of material concerned, who made it, start date and
 * end date. It also contains start states and end states for all material.
 */
public class Loan {
	private List<String> material;
	private int courseId;
	private Calendar startDate;
	private Calendar endDate;
	private Calendar askDate;
	private IUser user;
	private boolean gaveBack;
	private List<State> startState;
	private List<State> endState;
	private static String id;

	public Loan() {
	}

	public Loan(List<String> material, int courseId, Calendar startDate,
			Calendar endDate, Calendar askDate, IUser user,
			List<State> startState) {
		this.material = material;
		this.courseId = courseId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.askDate = askDate;
		this.user = user;
		this.gaveBack = false;
		this.startState = startState;
		this.endState = new ArrayList<State>();
	}

	public Loan(Map<String, Object> description) {
		this.restore(description);
	}

	public List<String> getListOfMaterials() {
		return this.material;
	}

	public int getCourse() {
		return this.courseId;
	}

	public Calendar getStartDate() {
		return this.startDate;
	}

	public Calendar getEndDate() {
		return this.endDate;
	}

	public Calendar getAskDate() {
		return this.askDate;
	}

	public IUser getUser() {
		return this.user;
	}

	public boolean getGaveBack() {
		return this.gaveBack;
	}

	public void setCourse(int c) {
		this.courseId = c;
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

	public void setAskDate(Calendar date) {
		this.askDate = date;
	}

	public void setStartDate(Calendar date) {
		this.startDate = date;
	}

	public void setMaterial(List<String> list) {
		this.material = list;
	}

	/**
	 * Methode getTypesOfMaterial. Cette methode retourne la liste des materials
	 * du même type de la classe specifiée en parametre.
	 * 
	 * @return
	 */
	/*
	 * public List<? extends Material> getTypesOfMaterial(Class<? extends
	 * Material> classe) { ArrayList<Class<? extends Material>> list = new
	 * ArrayList<Class<? extends Material>>(); for (int i = 0; i <
	 * this.material.size(); i++) { if
	 * (this.material.get(i).getClass().getName()
	 * .equals(classe.getClass().getName())) { list.add(this.material.get(i)); }
	 * } return list; }
	 */
	/**
	 * Methode getNumberOf. Cette methode retourne le nombre de material
	 * specifique se trouvant dans la liste de material.
	 * 
	 * @param classe
	 * @return
	 */
	/*
	 * public int getNumberOf(Class<? extends Material> classe) { return
	 * (this.getTypesOfMaterial(classe).size());
	 * 
	 * }
	 */

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
		return (this.material.equals(loan.getListOfMaterials())
				&& (this.sameDate(startDate, loan.getStartDate()))
				&& (this.sameDate(this.endDate, loan.getEndDate()))
				&& (this.sameDate(this.askDate, loan.getAskDate()))
				&& this.user.equals(loan.getUser())
				&& (this.gaveBack == loan.getGaveBack())
				&& this.startState.equals(loan.getStartState()) && this.endState
					.equals(loan.getEndState()));
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

	public Map<String, Object> getDescription() {
		Map<String, Object> description = new HashMap<String, Object>();

		description.put("material", material);
		description.put("courseId", courseId);
		description.put("startDate", startDate);
		description.put("endDate", endDate);
		description.put("askDate", askDate);

		Map<String, Object> userDescription = new HashMap<String, Object>();
		userDescription = user.getDescription();
		userDescription.put("class", user.getClass());

		description.put("user", userDescription);
		description.put("gaveBack", gaveBack);
		description.put("startState", startState);
		description.put("endState", endState);

		return description;
	}

	@SuppressWarnings("unchecked")
	public void restore(Map<String, Object> description) {

		System.out.println(description);
		
		Calendar startDate = (Calendar) description.get("startDate");
		Calendar endDate = (Calendar) description.get("endDate");
		Calendar askDate = (Calendar) description.get("askDate");
		List<State> startState = (List<State>) description.get("startState");
		List<State> endState = (List<State>) description.get("endState");
		Boolean gaveBack = (Boolean) description.get("gaveBack");

		Map<String, Object> userDescription = (Map<String, Object>) description
				.get("user");
		IUser user = new UserManager().restoreUser(
				(Class<? extends IUser>) userDescription.get("class"),
				userDescription);

		List<String> material = (List<String>) description.get("material");
		int course = (Integer) description.get("courseId");

		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setAskDate(askDate);
		this.setStartDate(startState);
		if (endState != null) {
			this.setEndState(endState);
		}
		this.setGaveBack(gaveBack);
		this.setUser(user);
		this.setMaterial(material);
		this.setCourse(course);
	}

	@Override
	public String toString() {
		String result = "";

		DateFormat ndf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String startDateStr = ndf.format(startDate.getTime());
		String endDateStr = ndf.format(endDate.getTime());

		result += "[" + user.getID() + "]\t" + "Course #" + courseId + "\t"
				+ startDateStr + " - " + endDateStr;

		result += (gaveBack) ? " - gaveBack" : " - unreturned";

		result += "\n";

		for (int i = 0; i < this.material.size(); i++) {
			result += "\t[" + material.get(i) + "]\t - ";
			result += startState.get(i) + "\n";
		}

		return result;
	}
}