package model;

import java.util.LinkedHashMap;

import model.loan.LoanManager;
import model.user.User;

public abstract class Statistics {

	public static LinkedHashMap<User, Integer> orderUsersByNumberOfLoan(LoanManager lm) {
		LinkedHashMap<User, Integer> result = new LinkedHashMap<User, Integer>();
		
		
		
		return result;
	}
	
	
	public static User getMostImportantBorrower(LoanManager lm) {
		LinkedHashMap<User, Integer> completeList = orderUsersByNumberOfLoan(lm);
		return (User)completeList.keySet().toArray()[0];
	}
	
}
