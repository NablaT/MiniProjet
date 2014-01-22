package model.material;

abstract class Tablet extends ComputerDevice {

	public Tablet(String name, String brandName, int screenSize, ScreenType screenType) {
		super(name, brandName, screenSize, screenType);
	}
}