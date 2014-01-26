package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import model.loan.Loan;
import model.loan.LoanManager;
import model.user.IUser;

public abstract class Statistics {

	public static LinkedHashMap<IUser, Integer> orderUsersByNumberOfLoan(LoanManager lm) {
		LinkedHashMap<IUser, Integer> result = new LinkedHashMap<IUser, Integer>();
		
		Map<IUser, Integer> tmp = new HashMap<IUser, Integer>();
		for(Loan l : lm.getOldLoans()) {
			if(tmp.containsKey(l.getUser())){
				tmp.put(l.getUser(), tmp.get(l.getUser())+1);
			}
			else {
				tmp.put(l.getUser(), 1);
			}
		}
		

		for(int i = 0 ; i < tmp.keySet().size() ; i++){
			//ORDER LINKEDHASHMAP
			
		}
		
		return result;
	}
	
	
	public static IUser getMostImportantBorrower(LoanManager lm) {
		LinkedHashMap<IUser, Integer> completeList = orderUsersByNumberOfLoan(lm);
		return (IUser)completeList.keySet().toArray()[0];
	}
	
}
