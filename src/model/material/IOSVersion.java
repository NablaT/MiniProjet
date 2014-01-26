package model.material;

/**
 * Enum representing every IOS version of an IOS device
 * 
 * @author Benni Benjamin
 * @since v.0.0.0
 */
public enum IOSVersion {

	IOS1("IOS1"), IOS2("IOS2"), IOS3("IOS3"), IOS4("IOS4"), IOS5("IOS5"), IOS6(
			"IOS6"), IOS7("IOS7");

	private String name;

	private IOSVersion(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
