package model.material;

import java.util.Map;

import model.State;
import model.user.IUser;

/**
 * This class represents every phone
 * @author Benni Benjamin
 * @since v.0.0.0
 */
abstract class Phone extends ComputerDevice {

	public Phone() {
		super();
	}

	public Phone(String name, String brandName, int screenSize,
			ScreenType screenType, State state,
			Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription) {
		super(name, brandName, screenSize, screenType, state, limitsDescription);
	}

	public Phone(Map<String, Object> description) {
		super(description);
	}

}