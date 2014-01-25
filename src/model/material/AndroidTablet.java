package model.material;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.IUser;

class AndroidTablet extends Tablet {

	private AndroidTabletVersion version;
	
	public AndroidTablet() { 
		super();
	}
	
	public AndroidTablet(String name, String brandName, int screenSize,
			ScreenType screenType,
			AndroidTabletVersion version,
			State state,
			Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription) {
		super(name, brandName, screenSize, screenType,state, limitsDescription);
		this.version = version;
	}
	
	/**
	 * Third android tablet constructor<br>
	 * It will build an android phone for the specified description<br>
	 * It use the super(Map) constructor<br>
	 * See the second constructor to know wich must contains the map.
	 * 
	 * @param description
	 *            map describing the android phone
	 * 
	 * @see Phone#Phone(Map)
	 * @see AndroidTablet#AndroidTablet(String, String, int, ScreenType,
	 *      AndroidTabletVersion, State, Map)
	 * 
	 * @since v.0.0.0
	 */
	public AndroidTablet(Map<String, Object> description) {
		super(description);
	}
	
	/**
	 * Getter wich allow access to the {@link AndroidTabletVersion} of the current
	 * device
	 * 
	 * @return android version of the current device
	 * 
	 * @see AndroidTabletVersion
	 * 
	 * @since v.0.0.0
	 */
	public AndroidTabletVersion getVersion() {
		return this.version;
	}

	/**
	 * Setter wich allow to set the version of the current android device
	 * 
	 * @param version
	 *            the new version of the current android device
	 * 
	 * @see AndroidTabletVersion
	 * 
	 * @since v.0.0.0
	 */
	public void setVersion(AndroidTabletVersion version) {
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
		this.version = (AndroidTabletVersion) description.get("version");
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
	 * An androidTablet is equal another if it has the same
	 * {@link AndroidTabletVersion}<br>
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
				&& this.version.equals(((AndroidTablet)o).version);
	}

	@Override
	public String toString() {
		return "\t[#" + id + "] [" + this.getClass().getSimpleName() + "]\t" +  " " + super.toString() + " - " + this.version;
	}
}