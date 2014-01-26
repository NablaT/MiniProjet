package model.material;

import java.util.Map;

import model.State;
import model.user.IUser;

/**
 * This class represents every IOS tablet in the app
 * @author Benni Benjamin
 * @since v.0.0.0
 */
class IOSTablet extends Tablet {

	public IOSTablet(String name, String brandName, int screenSize,
			ScreenType screenType,
			State state,
			Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription) {
		super(name, brandName, screenSize, screenType, state, limitsDescription);
	}
}