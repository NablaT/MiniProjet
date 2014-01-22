/**
 * 
 */
package model.repair;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import model.util.ConfigXML;
import model.material.Material;

/**
 * 
 * @author mael
 * 
 */

public class StockRepairManager {

	private static final String PATH_FILE = "repaired_material";
	private static final String VERSION_FILE = "0.0.0";

	private ArrayList<Repair> listOfProducts;
	private GregorianCalendar currentDay;

	/**
	 * Initialize listOfProducts with an empty ArrayList
	 */
	public StockRepairManager(GregorianCalendar currentDay) {
		listOfProducts = new ArrayList<Repair>();
		this.currentDay = currentDay;
	}

	/**
	 * 
	 * @param material_m
	 *            List of material send to the repairer
	 * @param end
	 *            Date when the repairer will send back the material repaired
	 * 
	 *            this method add a new object Repair to listOfProducts
	 */
	public void addMaterial(ArrayList<Material> material_m,
			GregorianCalendar end) {
		Repair products = new Repair(material_m, end);
		listOfProducts.add(products);
	}

	/**
	 * 
	 * @return return all material which have reach their day of reparation with
	 *         regard to currendDay
	 */
	public List<Material> getRepairedMaterial() {
		ArrayList<Material> repairedMaterial = new ArrayList<Material>();
		ArrayList<Material> material = new ArrayList<Material>();
		for (int i = 0; i < listOfProducts.size(); i++) {
			if (listOfProducts.get(i).isRepaired(currentDay)) {
				material = listOfProducts.get(i).getMaterial();
				for (int j = 0; j < material.size(); j++) {
					repairedMaterial.add(material.get(j));
				}
			}
		}
		return repairedMaterial;
	}

	public void removeRepairedMaterial() {
		for (int i = listOfProducts.size() - 1; i <= 0; i--) {
			if (listOfProducts.get(i).isRepaired(currentDay)) {
				listOfProducts.remove(i);
			}
		}
	}

	public void setXML() {
		ConfigXML.store(listOfProducts, PATH_FILE, VERSION_FILE);
	}

	@SuppressWarnings("unchecked")
	public void getXML() {
		listOfProducts=(ArrayList<Repair>) ConfigXML.load(PATH_FILE, VERSION_FILE);
	}
}