package model.material;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.IUser;

/**
 * This class represents a camera
 * @author Benni Benjamin
 * @since v.0.0.0
 */
class Camera extends Material {

	public Camera(String name, String brandName, State state,
			Map<String, Map<Class<?extends IUser>, Integer>> limitsDescription) {
		super(name, brandName, state,limitsDescription);
	}

	private int zoomPower;
	private int resolution;

	public Camera() {
		super();
	}

	public Camera(int zoomPower, int resolution) {
		this.zoomPower = zoomPower;
		this.resolution = resolution;
	}

	public int getZoomPower() {
		return this.zoomPower;
	}

	public int getResolution() {
		return this.resolution;
	}

	public void setZoomPower(int newZoomPowerValue) {
		this.zoomPower = newZoomPowerValue;
	}

	public void setResolution(int newResolutionValue) {
		this.resolution = newResolutionValue;
	}

	@Override
	public Map<String, Object> getDescription() {
		Map<String, Object> description = new HashMap<String, Object>();

		description = super.getDescription();

		description.put("zoomPower", this.zoomPower);
		description.put("resolution", this.resolution);

		return description;
	}

	@Override
	public void restore(Map<String, Object> productDescription) {
		super.restore(productDescription);
		this.zoomPower = (Integer) productDescription.get("zoomPower");
		this.resolution = (Integer) productDescription.get("resolution");
	}

	@Override
	public String toString() {
		return "\n\tCamera:" + super.toString();
	}
}