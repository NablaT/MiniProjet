package model.material;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.State;
import model.user.User;

/**
 * This superclass represents any material in the app. It is abstract because
 * you can't create an instance of a Material.
 */
public abstract class Material {

	// Unique ID of the current material
	private String id;

	// Name of the material. Most of the time
	// it represents the name version (ex : 5s, 3gs)
	private String name;

	// Name of the brand wich release the current
	// material
	private String brandName;

	// Represents the current state of the specified material
	private State state;

	private static int idCounter = 1;

	// Const keys wich represent copy and day limits
	// for each users described in the app
	public static final String KEY_LIMIT_COPY = "maxCopy";
	public static final String KEY_LIMIT_DELAY = "maxDelay";
	public static final String KEY_LIMIT_DURATION = "maxDuration";

	// Map describing copy and day limits for each user
	private Map<String, Map<Class<? extends User>, Integer>> limits = new HashMap<String, Map<Class<? extends User>, Integer>>();

	public Material() {
		idCounter++;
	}

	public Material(String name, String brandName, State state,
			Map<String, Map<Class<? extends User>, Integer>> limitsDescription) {
		this.setName(name);
		this.setBrandName(brandName);
		this.setId(this.generateId(name, brandName));
		this.setLimits(limitsDescription);
		this.setState(state);
		idCounter++;
	}

	public Material(Map<String, Object> description) {
		idCounter++;
		this.restore(description);
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the brandName
	 */
	public final String getBrandName() {
		return brandName;
	}

	public final State getState() {
		return this.state;
	}

	public final int getDelayLimitation(Class<?extends User> targetClass) {
		return this.limits.get(KEY_LIMIT_DELAY).get(targetClass);
	}

	public final int getDurationLimitation(Class<?extends User> targetClass) {
		return this.limits.get(KEY_LIMIT_DURATION).get(targetClass);
	}

	public final int getCopyLimitation(Class<?extends User> targetClass) {
		return this.limits.get(KEY_LIMIT_COPY).get(targetClass);
	}

	public static final int getIdCounter() {
		return Material.idCounter;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(String id) {
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException(
					"The id specified is null or empty");
		}

		this.id = id;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException(
					"The name specified is null or empty");
		}

		this.name = name;
	}

	/**
	 * @param brandName
	 *            the brandName to set
	 */
	public final void setBrandName(String brandName) {
		if (brandName == null || brandName.length() == 0) {
			throw new IllegalArgumentException(
					"The brandName specified is null or empty");
		}

		this.brandName = brandName;
	}

	public final void setState(State state) {
		if (state == null) {
			throw new IllegalArgumentException("The state specified is null");
		}
		this.state = state;
	}

	public final void setLimits(
			Map<String, Map<Class<? extends User>, Integer>> limits) {
		this.limits = limits;
	}

	public final void setCopiesLimitation(Class<?extends User> classe, Integer newValue) {
		this.limits.get(KEY_LIMIT_COPY).put(classe, newValue);
	}

	public final void setDelayLimitation(Class<?extends User> classe, Integer newValue) {
		this.limits.get(KEY_LIMIT_DELAY).put(classe, newValue);
	}

	public final void setDurationLimitation(Class<?extends User> classe,
			Integer newValue) {
		this.limits.get(KEY_LIMIT_DELAY).put(classe, newValue);
	}

	public static final void incrementIdCounter() {
		Material.idCounter++;
	}

	public static final void resetIdCounter() {
		Material.idCounter = 1;
	}

	/**
	 * Return a neutral description of the current material
	 */
	public Map<String, Object> getDescription() {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("id", this.getId());
		result.put("name", this.getName());
		result.put("brandName", this.getBrandName());
		result.put("state", this.state);
		result.put("limits", this.limits);

		return result;
	}

	private String generateId(String name, String brandName) {

		String result = "";
		int random = new Random().nextInt(9);

		result = result.concat("" + name.charAt(0));
		result = result.concat("" + brandName.charAt(0));
		result = result.concat("" + idCounter);
		result = result.concat("" + random);

		return result;
	}

	/**
	 * Restore the current from a previous neutral description
	 */
	@SuppressWarnings("unchecked")
	public void restore(Map<String, Object> description) {

		// Get specifics attributes
		String id = (String) description.get("id");
		String name = (String) description.get("name");
		String brandName = (String) description.get("brandName");
		State state = (State) description.get("state");
		Map<String, Map<Class<?extends User>, Integer>> limits = (Map<String, Map<Class<?extends User>, Integer>>) description
				.get("limits");

		if (id == null) {
			this.setId(this.generateId(name, brandName));
		} else {
			this.setId(id);
		}
		this.setName(name);
		this.setBrandName(brandName);
		this.setState(state);
		this.setLimits(limits);
	}

	@Override
	public String toString() {
		return "#" + id + " :: " + brandName + " " + name;
	}

	@Override
	public boolean equals(Object o) {
		return this.id.equals(((Material) o).getId());
	}
}