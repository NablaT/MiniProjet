package model.material;

public enum AndroidTabletVersion {

	HONEYCOMB("HONEYCOMB"), ICE_CREAM_SANDWICH("ICE_CREAM_SANDWICH"),
	JELLY_BEAN("JELLY_BEAN"), KITKAT("KITKAT");

	private String name;

	private AndroidTabletVersion(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
	
}
