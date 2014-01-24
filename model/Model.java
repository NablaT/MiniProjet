package model;

import model.course.CourseManager;
import model.loan.LoanGenerator;
import model.loan.LoanManager;
import model.material.StockManager;
import model.user.IUser;
import model.user.UserManager;

public class Model {
	
	private StockManager sm;
	private UserManager um;
	private LoanManager lm;
	private CourseManager cm;
	
	public Model() {
		
		this.sm = new StockManager();
		//StockGenerator.storeStockDescription(sm);
		this.sm.load();
		
		this.um = new UserManager();
		//UserGenerator.storeStockDescription(um);
		this.um.load();
		
		this.cm = new CourseManager();
		//CourseGenerator.storeCourseDescription(cm);
		this.cm.load();
		
		this.lm = new LoanManager();
		//LoanGenerator.storeLoansDescription(lm, um);
		this.lm.load();
		
		System.out.println("\n====================================== Stock Initial ========================================\n");
		System.out.println(sm.displayStock());
		System.out.println("=============================================================================================\n");
	
		
		System.out.println("\n=================================== Comptes Utilisateurs ====================================\n");
		System.out.println(um.displayUsersAccounts());
		System.out.println("==============================================================================================\n");
	
	
		System.out.println("\n=================================== Cours disponibles ====================================\n");
		System.out.println(cm.displayCourses());
		System.out.println("==============================================================================================\n");
	
		System.out.println("\n=================================== Emprunts non rendus ====================================\n");
		System.out.println(lm.displayUnreturnedLoans());
		System.out.println("==============================================================================================\n");
	
	}
	
	public int getCopyLimitation(Class<?extends IUser> classe, String materialID) {
		return this.sm.getMaterial(materialID).getCopyLimitation(classe);
	}
	
	public String getProductDescription(String materialID) {
		return this.sm.getMaterial(materialID).getProductDescription();
	}
	
	public String getNameOf(String materialID) {
		return this.sm.getMaterial(materialID).getName();
	}
}