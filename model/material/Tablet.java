package model.material;

import java.util.Map;

import model.State;
import model.user.User;

abstract class Tablet extends ComputerDevice {

	public Tablet(String name, String brandName, int screenSize,
			ScreenType screenType,
			State state,
			Map<String, Map<Class<? extends User>, Integer>> limitsDescription) {
		super(name, brandName, screenSize, screenType, state, limitsDescription);
	}
}