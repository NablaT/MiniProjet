package model.material;

import java.util.Map;

abstract class ComputerDevice extends Material {

	public ComputerDevice() {
		super();
	}
	
	public ComputerDevice(String name, String brandName) {
		super(name, brandName);
	}
	
	public ComputerDevice(Map<String, Object> description) {
		super(description);
	}
	
	@Override
	public String toString() {
		return super.toString() + " - Categorie : devices";
	}
}