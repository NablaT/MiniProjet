package model.material;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.User;

class AndroidPhone extends Phone {

	// Version of the current android device
	private AndroidVersion version;

	/**
	 * First default constructor<br>
	 * Call only super() default constructor
	 * 
	 * @see Phone#Phone()
	 * 
	 * @since v.0.0.0
	 */
	public AndroidPhone() {
		super();
	}

	/**
	 * Second android constructor<br>
	 * It will build a android phone with specified parameters.
	 * 
	 * @param name
	 *            name of the current android phone
	 * @param brandName
	 *            name of the brand that release the current android phone
	 * @param screenSize
	 *            screen size, in inch, of the current android phone
	 * @param screenType
	 *            screen type of the current android phone {@link ScreenType}
	 * @param version
	 *            version of the current android phone {@link AndroidVersion}
	 * @param state
	 *            state of the current android phone {@link State}
	 * @param limitsDescription
	 *            map describing limitations for the current android phone for a
	 *            specific user
	 * 
	 * @see AndroidVersion
	 * @see State
	 * @see ScreenType
	 * @see Phone#Phone(String, String, int, ScreenType, State, Map)
	 * 
	 * @since v.0.0.0
	 */
	public AndroidPhone(String name, String brandName, int screenSize,
			ScreenType screenType, AndroidVersion version, State state,
			Map<String, Map<Class<? extends User>, Integer>> limitsDescription) {
		super(name, brandName, screenSize, screenType, state, limitsDescription);
		this.setVersion(version);
	}

	/**
	 * Third android phone constructor<br>
	 * It will build an android phone for the specified description<br>
	 * It use the super(Map) constructor<br>
	 * See the second constructor to know wich must contains the map.
	 * 
	 * @param description
	 *            map describing the android phone
	 * 
	 * @see Phone#Phone(Map)
	 * @see AndroidPhone#AndroidPhone(String, String, int, ScreenType,
	 *      AndroidVersion, State, Map)
	 * 
	 * @since v.0.0.0
	 */
	public AndroidPhone(Map<String, Object> description) {
		super(description);
	}

	/**
	 * Getter wich allow access to the {@link AndroidVersion} of the current
	 * device
	 * 
	 * @return android version of the current device
	 * 
	 * @see AndroidVersion
	 * 
	 * @since v.0.0.0
	 */
	public AndroidVersion getVersion() {
		return this.version;
	}

	/**
	 * Setter wich allow to set the version of the current android device
	 * 
	 * @param version
	 *            the new version of the current android device
	 * 
	 * @see AndroidVersion
	 * 
	 * @since v.0.0.0
	 */
	public void setVersion(AndroidVersion version) {
		this.version = version;
	}

	/**
	 * Method restore<br>
	 * This method will set the current device with the map contents<br>
	 * If the description isn't complete, if attributes values are missing,
	 * exceptions can be raised.
	 * 
	 * @see Phone#restore(Map)
	 */
	public void restore(Map<String, Object> description) {
		super.restore(description);
		this.version = (AndroidVersion) description.get("version");
	}

	/**
	 * Method getDescription<br>
	 * This return the entire description of the current device<br>
	 * This will call super methods {@link Phone#getDescription()}.
	 * 
	 * @return complete description of the current device
	 * @see Phone#getDescription()
	 */
	public Map<String, Object> getDescription() {

		// Build the result, call super method and add
		// current android version to the super description
		Map<String, Object> result = new HashMap<String, Object>();
		result = super.getDescription();
		result.put("version", this.version);

		return result;
	}

	/**
	 * Equals method<br>
	 * An androidPhone is equal another if it has the same
	 * {@link AndroidVersion}<br>
	 * It uses the super overridden method {@link Phone#equals(Object)}
	 * 
	 * @param o
	 *            The object wich 'this' has to be compared
	 * 
	 * @return true if this is equals to the specified object o, false otherwise
	 * 
	 * @see Phone#equals(Object)
	 */
	@Override
	public boolean equals(Object o) {
		return super.equals(o)
				&& this.version.equals(((AndroidPhone) o).version);
	}

	@Override
	public String toString() {
		return super.toString() + "- OS : android - Version : " + this.version;
	}
}