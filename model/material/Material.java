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
	private String id;
	private String name;
	private String brandName;
	private State state;

	private static final String KEY_LIMIT_COPY = "maxCopy";
	private static final String KEY_LIMIT_DAY = "maxDay";
	
	private Map<String, Map<Class<?extends User>, Integer>> limits
					= new HashMap<String, Map<Class<?extends User>, Integer>>();
	
	public Material(String id, String name, String brandName) {
		
	}
	
	/**
	 * Return a neutral description of the current material
	 */
	public Map<String, Object> getDescription() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Restore the current from a previous neutral description
	 */
	public boolean restore(java.util.Map<String, Object> description) {
		throw new UnsupportedOperationException();
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return this.state;
	}
}