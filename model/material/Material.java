package model.material;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.User;


/**
 * This superclass represents any material in the app.
 * It is abstract because you can't create an instance of a Material.
 */
public abstract class Material {
	
	//	Unique ID of the current material
	private String id;
	
	//	Name of the material. Most of the time
	//	it represents the name version (ex : 5s, 3gs)
	private String name;
	
	//	Name of the brand wich release the current
	//	material
	private String brandName;
	
	//	Represents the current state of the specified material
	private State state;
	
	//	Const keys wich represent copy and day limits
	//	for each users described in the app
	private static final String KEY_LIMIT_COPY = "maxCopy";
	private static final String KEY_LIMIT_DAY = "maxDay";
	
	//	Map describing copy and day limits for each user
	private Map<String, Map<Class<?extends User>, Integer>> limits
					= new HashMap<String, Map<Class<?extends User>, Integer>>();
	
	public Material(String id, String name, String brandName) {
		
		//	Check arguments
		if(id == null || id.length() == 0) {
			throw new IllegalArgumentException("The id specified is null or empty");
		}
		
		if(name == null || name.length() == 0) {
			throw new IllegalArgumentException("The name specified is null or empty");
		}
		
		if(brandName == null || brandName.length() == 0) {
			throw new IllegalArgumentException("The brandName specified is null or empty");
		}
		
		this.id = id;
		this.name = name;
		this.brandName = brandName;
	}
	
	/**
	 * Return a neutral description of the current material
	 */
	public Map<String, Object> getDescription() {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("id", this.id);
		result.put("name", this.name);
		result.put("brandName", this.brandName);
		
		return result;
	}

	/**
	 * Restore the current from a previous neutral description
	 */
	public void restore(Map<String, Object> description) {
		
		//	Get specifics attributes
		String id = (String) description.get("id");
		String name = (String) description.get("name");
		String brandName = (String) description.get("brandName");
		
		
		//	Check arguments
		if(id == null || id.length() == 0) {
			throw new IllegalArgumentException("The id restored is null or empty");
		}
		
		if(name == null || name.length() == 0) {
			throw new IllegalArgumentException("The name restored is null or empty");
		}
		
		if(brandName == null || brandName.length() == 0) {
			throw new IllegalArgumentException("The brandName restored is null or empty");
		}

		this.id = id;
		this.name = name;
		this.brandName = brandName;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return this.state;
	}
}