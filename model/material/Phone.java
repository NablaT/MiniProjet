package model.material;

import java.util.Map;

abstract class Phone extends ComputerDevice {


	public Phone() {
		super();
	}
	
	public Phone(String name, String brandName, int screenSize, ScreenType screenType) {
		super(name, brandName, screenSize, screenType);
	}

	public Phone(Map<String, Object> description) {
		super(description);
	}

}