package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.course.CourseManager;
import model.loan.Loan;
import model.loan.LoanGenerator;
import model.loan.LoanManager;
import model.material.Material;
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
		
		System.out.println(PieceOfDisplay.stockInitial);
		System.out.println(sm.displayStock());
		System.out.println(PieceOfDisplay.someEquals);
	
		
		System.out.println(PieceOfDisplay.userAccounts);
		System.out.println(um.displayUsersAccounts());
		System.out.println(PieceOfDisplay.someEquals);
	
	
		System.out.println(PieceOfDisplay.availableCourses);
		System.out.println(cm.displayCourses());
		System.out.println(PieceOfDisplay.someEquals);
	
		System.out.println(PieceOfDisplay.notReturnLoans);
		System.out.println(lm.displayUnreturnedLoans());
		System.out.println(PieceOfDisplay.someEquals);
	
	}
	
	public void test(){
		
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
	
	public StockManager getStockManager(){
		return this.sm; 
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
		if (this.isAvailable(loan) && this.lm.askForALoan(loan)) {
			this.lm.getUnreturnedLoans().add(loan);
			return true;
		}
		return false;
	}

	
	public boolean isAvailable(Loan loan) {
		ArrayList<Loan> loansNotLoanable= this.lm.saveBadLoan(loan);
		int mark=0;
		HashMap<Class<?extends Material>,List<Material>> hm= new HashMap<Class<?extends Material>,List<Material>>();
		//On recupere l'ensemble du stock dans une seule liste
		ArrayList<Material> temp= (ArrayList<Material>) this.sm.stockToList(); 
		//On enleve de cette liste la tous les mauvais materials, ceux que l'on ne pourra pas emprunter. 
		temp= removeBadLoans(loansNotLoanable, temp); 
		//Ensuite on regarde si tous les types de materiels demandés se trouvent dans le stock. 
		for(int j=0; j<loan.getListOfMaterials().size();j++){
			Material current=this.sm.getMaterial(loan.getListOfMaterials().get(j)); 
			
			for(int i=0; i<temp.size(); i++){
				if(temp.get(i).getProductDescription().equals(current.getProductDescription())){
					mark=1;
				}
			}
			//Ici cela veut dire que l'on a parcouru tout le stock et que nous n'avons pas trouvé un des materiels demandés
			//On retourne faux. 
			if(mark==0){
				return false; 
			}
		}
		return true;
	}
	
	/**
	 * This method removes all loansNotLoanable elements which are in result list.
	 * @param loansNotLoanable
	 * @param result
	 * @return
	 */
	
	private ArrayList<Material> removeBadLoans(ArrayList<Loan> loansNotLoanable, ArrayList<Material> result) {
		for(int i=0; i<loansNotLoanable.size(); i++){
			for(int k=0; k<loansNotLoanable.get(i).getListOfMaterials().size(); k++){
				for(int j=0; j<result.size(); j++){
					if(result.get(j).getId().equals(loansNotLoanable.get(i).getListOfMaterials().get(k))){
						result.remove(j);
						break; 
					}
				}
			}
		}
		return result;
	}

	
	
	
	
}