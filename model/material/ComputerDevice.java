package model.material;

import java.util.Map;

abstract class ComputerDevice extends Material {

	public ComputerDevice(String id, String name, String brandName) {
		super(id, name, brandName);
		// TODO Auto-generated constructor stub
	}
	
	public ComputerDevice(String name, String brandName) {
		super(name, brandName);
		// TODO Auto-generated constructor stub
	}
	
	public ComputerDevice(Map<String, Object> description) {
		super(description);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.toString() + " - Categorie : devices";
	}
}