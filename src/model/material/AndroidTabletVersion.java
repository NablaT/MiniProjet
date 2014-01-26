package model.material;

/**
 * Enum representing versions of an android tablet
 * 
 * @author Benni Benjamin
 * @since v.0.0.0
 */
public enum AndroidTabletVersion {

	HONEYCOMB("HONEYCOMB"), ICE_CREAM_SANDWICH("ICE_CREAM_SANDWICH"), JELLY_BEAN(
			"JELLY_BEAN"), KITKAT("KITKAT");

	private String name;

	private AndroidTabletVersion(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

}
