package model.material;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.util.ConfigXML;

/**
 * This class will provides services to manage the stock.
 */
public class StockManager {

	private static final String KEY_STOCK_FILE = "stockDescription.xml";
	private static final String KEY_STOCK_VERSION = "0.0.0";

	private Map<Class<?extends Material>,List<Material>> stockDescription 
				= new HashMap<Class<?extends Material>, List<Material>>();

	public void addProduct(Material material) {
		
		if(this.isPresent(material.getId())) {
			throw new IllegalArgumentException("The id specified is already used");
		}
		
		List<Material> listMaterial = new ArrayList<Material>();
		
		if(this.stockDescription.containsKey(material.getClass())) {
			listMaterial = this.stockDescription.get(material.getClass());
		}

		listMaterial.add(material);

		this.stockDescription.put(material.getClass(), listMaterial);
		Material.incrementIdCounter();
	}

	public void removeProduct(Material material) {

	}

	public int getNumberOf(Class<? extends Material> classe) {
		if(!this.stockDescription.containsKey(classe)) {
			throw new IllegalArgumentException("The material type specified" +
										" is absent from the stock description");
		}
		
		return this.stockDescription.get(classe).size();
	}
	
	public Material getMaterial(String id) {
	
		Material result = null;
		
		for(Material m : this.stockToList()) {
			if(m.getId().equals(id)) {
				result = m;
			}
		}
		
		return result;		
	}

	public boolean isPresent(String id) {
		return getMaterial(id) != null;
	}
	
	
	public void storeStock() {

		Map<Class<? extends Material>, List<Map<String, Object>>> description 
					= new HashMap<Class<? extends Material>, List<Map<String, Object>>>();

		for (Material m : this.stockToList()) {
			Class<? extends Material> c = m.getClass();

			if (description.containsKey(c)) {
				description.get(c).add(m.getDescription());
			} else {
				List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
				newList.add(m.getDescription());
				description.put(c, newList);
			}
		}

		ConfigXML.store(description, KEY_STOCK_FILE, KEY_STOCK_VERSION);
	}

	public void loadStock() {
		Material.resetIdCounter();
		Map<Class<? extends Material>, List<Map<String, Object>>> stockDescription 
					= new HashMap<Class<? extends Material>, List<Map<String, Object>>>();
		
		stockDescription = (Map<Class<? extends Material>, List<Map<String, Object>>>)
					ConfigXML.load(KEY_STOCK_FILE, KEY_STOCK_VERSION);

		this.restore(stockDescription);
	}

	private void restore(Map<Class<? extends Material>, List<Map<String, Object>>>stockDescription) {
		
		for(Class<?extends Material> key : stockDescription.keySet()) {
			for(Map<String, Object> currentProductDescription : stockDescription.get(key)) {

				Constructor constructeur = null;
				try {
					constructeur = key.getConstructor();
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				Material instance = null;

				try {
					instance = (Material)constructeur.newInstance(new Object[] {});
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				
				instance.restore(currentProductDescription);
				
				this.addProduct(instance);
			}
		}
	}

	public List<Material> stockToList() {
		List<Material> result = new ArrayList<Material>();
		
		for(List<Material> currentList : this.stockDescription.values()) {
			for(Material m : currentList) {
				result.add(m);
			}
		}
		
		return result;
	}
	
	public String displayStock() {
		String result = "";

		for (Material m : this.stockToList()) {
			result = result.concat(m.toString() + "\n");
		}

		return result;
	}
}