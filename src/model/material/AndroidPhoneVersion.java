package model.material;

enum AndroidPhoneVersion {

	PETIT_FOUR("PETIT_FOUR"), CUPCAKE("CUPCAKE"), DONUT("DONUT"), 
	ECLAIR("ECLAIR"), FROYO("FROYO"), GINGERBREAD("GINGERBREAD"),
	HONEYCOMB("HONEYCOMB"), ICE_CREAM_SANDWICH("ICE_CREAM_SANDWICH"),
	JELLY_BEAN("JELLY_BEAN"), KITKAT("KITKAT");

	private String name;

	private AndroidPhoneVersion(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
