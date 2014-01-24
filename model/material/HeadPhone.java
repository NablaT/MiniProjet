package model.material;

import java.util.Map;

import model.State;
import model.user.User;

class HeadPhone extends Material {

	public HeadPhone(String name, String brandName,State state,
			Map<String, Map<Class<? extends User>, Integer>> limitsDescription) {
		super(name, brandName, state, limitsDescription);
	}
}