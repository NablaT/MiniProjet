package model.material;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestStockManager {

	private static StockManager sm;
	private static Map<String, Object> phoneDescription;
	private static AndroidPhone firstPhone;
	private static AndroidPhone secondPhone;
	private static AndroidPhone thirdPhone;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sm = new StockManager();
		
		phoneDescription = new HashMap<String, Object>();
		
		phoneDescription.put("name", "Cink Five");
		phoneDescription.put("brandName", "Wiko");
		phoneDescription.put("screenSize", 5);
		phoneDescription.put("screenType", ScreenType.RESISTIV);
		phoneDescription.put("version", AndroidVersion.ECLAIR );
		
		firstPhone = new AndroidPhone(phoneDescription);

		phoneDescription.put("id", "CW99");
		
		secondPhone = new AndroidPhone(phoneDescription);
		
		thirdPhone = new AndroidPhone("Galaxy S4", "Samsung", 5,
				ScreenType.CAPACITIV, AndroidVersion.CUPCAKE);
	}

	@Test
	public void testAddProduct() {
		assertFalse(sm.stockToList().contains(firstPhone));
		assertFalse(sm.stockToList().contains(secondPhone));
		assertFalse(sm.stockToList().contains(thirdPhone));
		
		sm.addProduct(firstPhone);
		sm.addProduct(secondPhone);
		sm.addProduct(thirdPhone);
		
		assertTrue(sm.stockToList().contains(firstPhone));
		assertTrue(sm.stockToList().contains(secondPhone));
		assertTrue(sm.stockToList().contains(thirdPhone));
	}
	
	@Test
	public void testGetNumberOfAndIsPresent() {

		sm = new StockManager();
		
		assertFalse(sm.stockToList().contains(firstPhone));
		assertFalse(sm.stockToList().contains(secondPhone));
		assertFalse(sm.stockToList().contains(thirdPhone));
		
		assertEquals(0, sm.getNumberOf(AndroidPhone.class));
		assertEquals(0, sm.getNumberOf(IOSPhone.class));

		sm.addProduct(firstPhone);
		
		assertEquals(1, sm.getNumberOf(AndroidPhone.class));
		assertEquals(0, sm.getNumberOf(IOSPhone.class));

		assertTrue(sm.isPresent(firstPhone.getId()));
		assertFalse(sm.isPresent(secondPhone.getId()));
		assertFalse(sm.isPresent(thirdPhone.getId()));
		
		sm.addProduct(secondPhone);
		
		assertEquals(2, sm.getNumberOf(AndroidPhone.class));
		assertEquals(0, sm.getNumberOf(IOSPhone.class));

		assertTrue(sm.isPresent(firstPhone.getId()));
		assertTrue(sm.isPresent(secondPhone.getId()));
		assertFalse(sm.isPresent(thirdPhone.getId()));
		
		sm.addProduct(thirdPhone);
		
		assertEquals(3, sm.getNumberOf(AndroidPhone.class));
		assertEquals(0, sm.getNumberOf(IOSPhone.class));
		
		assertTrue(sm.isPresent(firstPhone.getId()));
		assertTrue(sm.isPresent(secondPhone.getId()));
		assertTrue(sm.isPresent(thirdPhone.getId()));
		
		assertTrue(sm.stockToList().contains(firstPhone));
		assertTrue(sm.stockToList().contains(secondPhone));
		assertTrue(sm.stockToList().contains(thirdPhone));
	}
	
	@Test
	public void testGetMaterial() {
		assertEquals(secondPhone, sm.getMaterial("CW99"));
		assertEquals(null, sm.getMaterial("CW7599"));
		assertTrue(sm.isPresent(secondPhone.getId()));
	}
	
	@Test
	public void testStoreLoadAndRestoreStock() {
		sm.storeStock();
		
		StockManager clone = new StockManager();
		clone.loadStock();
		
		assertTrue(sm.equals(clone));
	}
}