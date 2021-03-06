package model.material;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.IUser;

/**
 * This class represents every IOS phone
 * @author Benni Benjamin
 * @since v.0.0.0
 */
class IOSPhone extends Phone {

	private IOSVersion version;

	public IOSPhone() {
		super();
	}

	public IOSPhone(String name, String brandName, int screenSize,
			ScreenType screenType, IOSVersion iosVersion, State state,
			Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription) {
		super(name, brandName, screenSize, screenType, state, limitsDescription);
		this.version = iosVersion;
	}

	public IOSPhone(Map<String, Object> description) {
		super(description);
	}

	public IOSVersion getVersion() {
		return this.version;
	}

	public void setVersion(IOSVersion version) {
		this.version = version;
	}

	public void restore(Map<String, Object> description) {
		super.restore(description);

		this.version = (IOSVersion) description.get("version");
	}

	public Map<String, Object> getDescription() {
		Map<String, Object> result = new HashMap<String, Object>();

		result = super.getDescription();
		result.put("version", this.version);

		return result;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o) && this.version.equals(((IOSPhone) o).version);
	}

	@Override
	public String toString() {
		return "\t[#" + id + "] [" + this.getClass().getSimpleName() + "]\t" +  " " + super.toString() + " - " + this.version;
	}
}