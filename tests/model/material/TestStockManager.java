package model.material;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.IUser;
import model.user.Student;
import model.user.Teacher;

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
		phoneDescription.put("version", AndroidPhoneVersion.ECLAIR );
		phoneDescription.put("state", State.UNUSED );
		
		Map<String, Map<Class<?extends IUser>, Integer>> limitsDescription 
 		= new HashMap<String, Map<Class<?extends IUser>, Integer>>();		

		Map<Class<?extends IUser>, Integer> copyLimitsForFirstPhone = new HashMap<Class<?extends IUser>, Integer>();
		copyLimitsForFirstPhone.put(Student.class, new Integer(2));
		copyLimitsForFirstPhone.put(Teacher.class, new Integer(10));
				
		Map<Class<?extends IUser>, Integer> delayLimitsForFirstPhone = new HashMap<Class<?extends IUser>, Integer>();
		delayLimitsForFirstPhone.put(Student.class, new Integer(2));
		delayLimitsForFirstPhone.put(Teacher.class, new Integer(14));		
		
		Map<Class<?extends IUser>, Integer> durationLimitsForFirstPhone = new HashMap<Class<?extends IUser>, Integer>();
		durationLimitsForFirstPhone.put(Student.class, new Integer(14));
		durationLimitsForFirstPhone.put(Teacher.class, new Integer(30));
		
		limitsDescription.put(Material.KEY_LIMIT_COPY, copyLimitsForFirstPhone);
		limitsDescription.put(Material.KEY_LIMIT_DELAY, delayLimitsForFirstPhone);
		limitsDescription.put(Material.KEY_LIMIT_DURATION, durationLimitsForFirstPhone);
		
		phoneDescription.put("limits", limitsDescription);
		
		firstPhone = new AndroidPhone(phoneDescription);

		phoneDescription.put("id", "CW99");
		
		secondPhone = new AndroidPhone(phoneDescription);
		
		thirdPhone = new AndroidPhone("Galaxy S4", "Samsung", 5,
				ScreenType.CAPACITIV, AndroidPhoneVersion.CUPCAKE, State.DAMAGED, limitsDescription);
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
		sm.store();
		
		StockManager clone = new StockManager();
		clone.load();
		
		assertTrue(sm.equals(clone));
	}
}