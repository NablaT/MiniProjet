package model.material;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.User;

abstract class ComputerDevice extends Material {

	private int screenSize;
	private ScreenType screenType;

	public ComputerDevice() {
		super();
	}

	public ComputerDevice(String name, String brandName, int screenSize,
			ScreenType screenType,
			State state,
			Map<String, Map<Class<? extends User>, Integer>> limitsDescription) {
		super(name, brandName, state, limitsDescription);
		this.setScreenSize(screenSize);
		this.setScreenType(screenType);
	}

	public ComputerDevice(Map<String, Object> description) {
		super(description);
	}

	/**
	 * @return the screenSize
	 */
	public int getScreenSize() {
		return screenSize;
	}

	/**
	 * @param screenSize
	 *            the screenSize to set
	 */
	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	/**
	 * @return the screenType
	 */
	public ScreenType getScreenType() {
		return screenType;
	}

	/**
	 * @param screenType
	 *            the screenType to set
	 */
	public void setScreenType(ScreenType screenType) {
		this.screenType = screenType;
	}

	@Override
	public void restore(Map<String, Object> description) {
		super.restore(description);

		this.screenSize = (Integer) description.get("screenSize");
		this.screenType = (ScreenType) description.get("screenType");
	}

	@Override
	public Map<String, Object> getDescription() {
		Map<String, Object> result = new HashMap<String, Object>();

		result = super.getDescription();
		result.put("screenSize", this.screenSize);
		result.put("screenType", this.screenType);

		return result;
	}

	@Override
	public String toString() {
		return super.toString() + " - Type : phone - " + this.screenSize
				+ "\" (" + this.screenType + ")";
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o) && (screenSize == ((Phone) o).getScreenSize())
				&& (screenType.equals(((Phone) o).getScreenType()));
	}
}