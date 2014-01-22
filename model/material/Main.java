package model.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		AndroidPhone p = new AndroidPhone("Galaxy Tab", "Samsung");
		
		p.setVersion(AndroidVersion.KITKAT);
		p.setScreenSize(4);
		p.setScreenType(ScreenType.CAPACITIV);
		
		AndroidPhone p2 = new AndroidPhone("Cink Five", "Wiko");
		
		p2.setVersion(AndroidVersion.JELLY_BEAN);
		p2.setScreenSize(5);
		p2.setScreenType(ScreenType.RESISTIV);
				
		StockManager sm2 = new StockManager();

		sm2.loadStock();
		
		
		AndroidPhone p3 = new AndroidPhone("Cink KING", "Wiko");
		p3.setVersion(AndroidVersion.ICE_CREAM_SANDWICH);
		p3.setScreenSize(5);
		p3.setScreenType(ScreenType.CAPACITIV);
		
		sm2.addProduct(p3);
		sm2.addProduct(p2);
//		sm2.addProduct(p);
		
		System.out.println(sm2.stockToList());
	}

}
