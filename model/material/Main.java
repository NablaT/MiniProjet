package model.material;

import java.util.HashMap;
import java.util.Map;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Material p = new AndroidPhone("id", "Galaxy Tab", "Samsung");
		
		//Material m = new AndroidPhone(p.getDescription());

		((AndroidPhone)p).setVersion(AndroidVersion.KITKAT);
		((AndroidPhone)p).setScreenSize(4);
		((AndroidPhone)p).setScreenType(ScreenType.CAPACITIV);
		
		Material m = new AndroidPhone(p.getDescription());
		System.out.println(m);

	}

}
