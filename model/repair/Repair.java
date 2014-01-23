package model.repair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.material.Material;

/**
 * 
 * @author mael
 * 
 */

public class Repair {

	private ArrayList<Material> material;
	private GregorianCalendar endDate;

	/**
	 * @param listMaterial
	 *            list of material send in reparation
	 * @param end
	 *            date of the end of the reparation
	 */
	public Repair(ArrayList<Material> listMaterial, GregorianCalendar end) {
	        material = listMaterial;
		endDate = end;
	}

	/**
	 * 
	 * @param date
	 * @return true if material is repaired at date or anterior at date
	 * @return false if material is not repaired
	 */
	public boolean isRepaired(GregorianCalendar date) {
		if (endDate.get(Calendar.DAY_OF_YEAR) <= date.get(Calendar.DAY_OF_YEAR)
				&& endDate.get(Calendar.YEAR) == date.get(Calendar.YEAR))
			return true;
		else if(endDate.get(Calendar.YEAR)<=date.get(Calendar.YEAR)) return true;
		return false;
	}

	/**
	 * @return the material
	 */
	public ArrayList<Material> getMaterial() {
		return material;
	}
	
	

}