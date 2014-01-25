package model.material;

import java.util.Map;

import model.State;
import model.user.IUser;

abstract class Tablet extends ComputerDevice {

	public Tablet() {
		super();
	}
	
	public Tablet(String name, String brandName, int screenSize,
			ScreenType screenType,
			State state,
			Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription) {
		super(name, brandName, screenSize, screenType, state, limitsDescription);
	}
	
	public Tablet(Map<String, Object> description) {
		super(description);
	}
}