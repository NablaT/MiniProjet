package model.material;

import java.util.Map;

import model.State;
import model.user.IUser;

/**
 * This class represents a headPhone in the app
 * @author Benni Benjamin
 * @since v.0.0.0
 */
class HeadPhone extends Material {

	public HeadPhone(String name, String brandName,State state,
			Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription) {
		super(name, brandName, state, limitsDescription);
	}
}