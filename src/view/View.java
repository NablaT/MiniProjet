package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.util.ConfigXML;
import controler.Controler;

public class View {

	public static final String KEY_PATH_FILE = "view";
	public static final String KEY_VERSION_FILE = "0.0.0";

	private Map<String, String> texts = new HashMap<String, String>();

	private Controler controler;

	private BufferedReader bufferRead = null;

	public View(Controler c) {
		this.controler = c;
		this.load();
		bufferRead = new BufferedReader(new InputStreamReader(System.in));
	}

	public void init() {
		System.out.println(this.texts.get("welcome"));

	}

	public void askUserType(List<String> userTypes) {
		System.out.println(this.texts.get("userType"));

		String list = "";

		for (int i = 0; i < userTypes.size(); i++) {
			list = list.concat("\t[" + (i + 1) + "]\t" + userTypes.get(i)
					+ "\n");
		}

		System.out.println(list);

		String rep = "";
		int indexRep = -1;
		boolean error = true;

		while (error) {

			try {
				rep = this.getAnswer();
				indexRep = Integer.parseInt(rep);
				error = !(indexRep > 0 && indexRep < userTypes.size() + 1);
				if (!error)
					break;
				System.err.println("Wrong index : out of bounds");
			} catch (NumberFormatException e) {
				System.err.println("Invalid input - Please try again");
			}

		}

		this.controler.notifyUserTypeSelected(userTypes.get(indexRep - 1));

	}

	public String askUserLogin() {
		System.out.println(this.texts.get("login"));
		return this.getAnswer();
	}

	public void askUserLogin_loading() {
		System.out.println(this.texts.get("login_loading"));
	}

	public void askUserLogin_success() {
		System.out.println(this.texts.get("login_success"));
	}

	public void askUserLogin_fail() {
		System.out.println(this.texts.get("login_fail"));
	}

	public void askActionType(List<String> actions) {
		System.out.println(this.texts.get("actionType"));

		String result = "";

		for (int i = 0; i < actions.size(); i++) {
			result += "\t[" + (i + 1) + "]\t" + actions.get(i) + "\n";
		}

		System.out.println(result);

		String rep = "";
		int indexRep = -1;
		boolean error = true;

		while (error) {

			try {
				rep = this.getAnswer();
				indexRep = Integer.parseInt(rep);
				error = !(indexRep > 0 && indexRep < actions.size() + 1);
				if (!error)
					break;
				System.err.println("Wrong index : out of bounds");
			} catch (NumberFormatException e) {
				System.err.println("Invalid input - Please try again");
			}

		}

		this.controler.notifyActionTypeSelected(actions.get(indexRep - 1));
	}

	public void askLoanId() {
		System.out.println(this.texts.get("gaveBack_askID"));
	}

	public void wrongLoanId() {
		System.out.println(this.texts.get("gaveBack_askID_error"));

	}

	public void askMaterialIDGivenBack() {
		System.out.println(this.texts.get("gaveBack_askMaterialID"));

	}

	public void wrongMaterialIdForSpecifiedLoan() {
		System.out.println(this.texts.get("gaveBack_askMaterialID_error"));

	}

	public void askCourse(List<Map<String, Object>> coursesDescription) {
		System.out.println(this.texts.get("borrow_askCourseID"));

		String result = "";

		for (Map<String, Object> currentCourseDescription : coursesDescription) {

			Calendar startDate = (Calendar) currentCourseDescription
					.get("startDate");
			Calendar endDate = (Calendar) currentCourseDescription
					.get("endDate");

			DateFormat ndf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String startDateStr = ndf.format(startDate.getTime());
			String endDateStr = ndf.format(endDate.getTime());

			result = result.concat("\t[" + currentCourseDescription.get("id")
					+ "]");
			result = result.concat("\t- "
					+ currentCourseDescription.get("name"));
			result = result.concat("\t(" + startDateStr + " --> " + endDateStr
					+ ")");
		}

		System.out.println(result);

		String rep = "";
		int indexRep = -1;
		boolean error = true;

		while (error) {

			try {
				rep = this.getAnswer();
				indexRep = Integer.parseInt(rep);
				error = !(indexRep > 0 && indexRep < coursesDescription.size() + 1);
				if (!error)
					break;
				System.err.println("Wrong index : out of bounds");
			} catch (NumberFormatException e) {
				System.err.println("Invalid input - Please try again");
			}
		}

		this.controler.notifyCourseSelected((Integer) ((coursesDescription
				.get(indexRep - 1)).get("id")));
	}

	public void wrongCourseId() {
		System.out.println(this.texts.get("borrow_askCourseID_fail"));

	}

	public void askLoanDates() {
		System.out.println(this.texts.get("borrow_askDates"));

	}

	public void wrongLoanDates() {
		System.out.println(this.texts.get("borrow_askDates_fail"));

	}

	public void askStartDate() {
		System.out.println(this.texts.get("borrow_askDate"));

		String[] rep = this.getAnswer().split("/");

		while (rep.length != 3) {
			System.err
					.println("Error, please enter the end date as dd/mm/yyyy");
			rep = this.getAnswer().split("/");
		}
		int day = 0;
		int month = 0;
		int year = 0;

		boolean error = true;

		while (error) {
			try {
				day = Integer.parseInt(rep[0]);
				month = Integer.parseInt(rep[1]);
				year = Integer.parseInt(rep[2]);
				error = false;
			} catch (NumberFormatException e) {

			}
		}

		Calendar startDate = Calendar.getInstance();
		startDate.set(year, month - 1, day);

		DateFormat ndf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String endDateStr = ndf.format(startDate.getTime());

		System.out.println("Date detectee : " + endDateStr);
		this.controler.notifyStartDateSelected(startDate);
	}
	
	public void askEndDate() {
		System.out.println(this.texts.get("borrow_askDate"));

		String[] rep = this.getAnswer().split("/");

		while (rep.length != 3) {
			System.err
					.println("Error, please enter the end date as dd/mm/yyyy");
			rep = this.getAnswer().split("/");
		}
		int day = 0;
		int month = 0;
		int year = 0;

		boolean error = true;

		while (error) {
			try {
				day = Integer.parseInt(rep[0]);
				month = Integer.parseInt(rep[1]);
				year = Integer.parseInt(rep[2]);
				error = false;
			} catch (NumberFormatException e) {

			}
		}

		Calendar endDate = Calendar.getInstance();
		endDate.set(year, month - 1, day);

		DateFormat ndf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String endDateStr = ndf.format(endDate.getTime());

		System.out.println("Date detectee : " + endDateStr);
		this.controler.notifyEndDateSelected(endDate);
	}

	public void askLoanMaterialID(List<Map<String, Object>> list) {
		System.out.println(this.texts.get("borrow_askMaterialID"));

		String result = "";

		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> currentMaterialDescritpion = list.get(i);
			result = result.concat("\t[" + (i + 1) + "]");
			// result = result.concat("\t[#" +
			// currentMaterialDescritpion.get("id") + "]");
			result = result.concat("\t"
					+ currentMaterialDescritpion.get("brandName"));
			result = result.concat("\t"
					+ currentMaterialDescritpion.get("name") + "\n");
		}

		System.out.println(result);

		Map<Integer, Integer> mapMaterialIDQuantity = this
				.parseAskLoanMaterialID(this.getAnswer(), list.size());

		while (mapMaterialIDQuantity == null) {
			mapMaterialIDQuantity = this.parseAskLoanMaterialID(
					this.getAnswer(), list.size());
		}
		

		for (Integer key : mapMaterialIDQuantity.keySet()) {
			if (!this.controler.checkIfMaterialAvailable(
					(String) (list.get(key).get("id")),
					mapMaterialIDQuantity.get(key))) {
				System.err
						.println("You can not borrow the material "
								+ list.get(key).get("brandName")
								+ " "
								+ list.get(key).get("name")
								+ ". The specified material has limitation or there is not enough items in the stock");
				return;
			}
		}
		
		System.out.println("Loan correctly registred.");
	}

	private Map<Integer, Integer> parseAskLoanMaterialID(String answer,
			int indexMax) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		String[] couplesOfMaterialQuantities = answer.split(",");

		for (int i = 0; i < couplesOfMaterialQuantities.length; i++) {
			String[] coupleMaterialQuantity = couplesOfMaterialQuantities[i]
					.split(" ");
			if (coupleMaterialQuantity.length != 2) {
				System.err
						.println("Error :use : materialID1 quantity1 [, materialID2, quantity2]");
				return null;
			}
			if (Integer.parseInt(coupleMaterialQuantity[0]) == 0
					|| Integer.parseInt(coupleMaterialQuantity[0]) > indexMax) {
				System.err
						.println("Error : material id specified out of bounds");
				return null;
			}
			result.put(Integer.parseInt(coupleMaterialQuantity[0]) -1,
					Integer.parseInt(coupleMaterialQuantity[1]));
		}

		return result;
	}

	public void materialAddedToCurrentLoan() {
		System.out.println(this.texts.get("borrow_askMaterialID_success"));

	}

	public void errorParsingLoanMaterialId() {
		System.out.println(this.texts.get("borrow_askMaterialID_fail"));

	}

	public void errorQuantityLoanMaterial(String materialID) {
		System.out.println(this.texts
				.get("borrow_askMaterialID_fail_quantity1")
				+ " "
				+ materialID
				+ " " + this.texts.get("borrow_askMaterialID_fail_quantity2"));

	}

	public String getAnswer() {

		try {
			return bufferRead.readLine();
		} catch (IOException e) {
			System.err.println("ERREUR DE SAISIE !");
		}
		return "";
	}

	public void load() {
		this.texts = (Map<String, String>) ConfigXML.load(KEY_PATH_FILE,
				KEY_VERSION_FILE);
	}

	public static void store() {
		Map<String, Object> description = new HashMap<String, Object>();

		String header = "======================================================\n";
		header += "\t\tWelcome on eLoan App !\t\tv.1.0\n";
		header += "======================================================\n";

		description.put("welcome", header);

		description.put("userType", "Please select a category :");

		description.put("login", "Please enter your id : ");
		description.put("login_success", "Connection done");
		description.put("login_loading", "Connecting ...");
		description.put("login_fail", "Connection fail - wrong id");

		description.put("actionType", "Please select an action :");

		description.put("gaveBack_askID", "Please select the id of the loan :");
		description.put("gaveBack_askID_error",
				"The loan id specified can not be found");
		description.put("gaveBack_askMaterialID",
				"Please select the id (or list) of the material :");
		description.put("gaveBack_askMaterialID_error",
				"The material ID doesn't match with specified loan");

		description.put("borrow_askCourseID", "Please select a course : ");
		description.put("borrow_askCourseID_fail",
				"Error, please select a course from the list below :");

		description
				.put("borrow_askDate",
						"Please enter when you will return the material (use : dd/mm/yyyy)");
		description.put("borrow_askDates_fail",
				"Error, use : jj/mm/aaaa jj/mm/aaaa");

		description
				.put("borrow_askMaterialID",
						"Please enter the list material_id quantity desired : materialID1 quantity1 [,materialID2 quantity2 ...]");
		description.put("borrow_askMaterialID_success",
				"Material added to the loan");
		description
				.put("borrow_askMaterialID_fail",
						"Error, use : materialID1 quantity1 [,materialID2 quantity2 ...]");
		description
				.put("borrow_askMaterialID_fail_quantity1", "The material #");
		description.put("borrow_askMaterialID_fail_quantity2",
				"can not be borrowed - not enough items");

		ConfigXML.store(description, KEY_PATH_FILE, KEY_VERSION_FILE);
	}

	public void informNoMoreCoursesAvailable() {
		System.err.println("No more courses availables. Are you sure that you are correctly registred to courses ?");
	}
}
