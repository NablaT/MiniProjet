package model;

import java.util.LinkedHashMap;

import model.loan.LoanManager;
import model.user.IUser;

public abstract class Statistics {

	public static LinkedHashMap<IUser, Integer> orderUsersByNumberOfLoan(LoanManager lm) {
		LinkedHashMap<IUser, Integer> result = new LinkedHashMap<IUser, Integer>();
		
		
		
		return result;
	}
	
	
	public static IUser getMostImportantBorrower(LoanManager lm) {
		LinkedHashMap<IUser, Integer> completeList = orderUsersByNumberOfLoan(lm);
		return (IUser)completeList.keySet().toArray()[0];
	}
	
}
