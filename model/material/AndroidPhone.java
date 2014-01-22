package model.material;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


class AndroidPhone extends Phone {

	private AndroidVersion version;

	public AndroidPhone() {
		super();
	}
	
	public AndroidPhone(String name, String brandName, int screenSize, ScreenType screenType, AndroidVersion version) {
		super(name, brandName, screenSize, screenType);
		this.setVersion(version);
	}
	
	public AndroidPhone(Map<String, Object> description) {
		super(description);
	}
	
	public AndroidVersion getVersion() {
		return this.version;
	}
	
	public void setVersion(AndroidVersion version) {
		this.version = version;
	}
	
	public void restore(Map<String, Object> description) {
		super.restore(description);
		
		this.version =  (AndroidVersion)description.get("version");		
	}
	
	public Map<String, Object> getDescription() {
		Map<String, Object> result = new HashMap<String, Object>();
		
		result = super.getDescription();
		result.put("version", this.version);

		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o) && this.version.equals(((AndroidPhone)o).version);
	}
	
	@Override
	public String toString() {
		return super.toString() + "- OS : android - Version : " + this.version;
	}
}