package model.material;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestIOSPhone {

	private static IOSPhone  defaultPhone;
	private static Map<String, Object> phoneDescription;
	private static IOSPhone  firstPhone;
	private static IOSPhone  secondPhone;
	private static IOSPhone  thirdPhone;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		defaultPhone = new IOSPhone ();

		phoneDescription = new HashMap<String, Object>();
		
		phoneDescription.put("name", "3Gs");
		phoneDescription.put("brandName", "Apple");
		phoneDescription.put("screenSize", 5);
		phoneDescription.put("screenType", ScreenType.RESISTIV);
		phoneDescription.put("version", IOSVersion.IOS4 );
		
		firstPhone = new IOSPhone (phoneDescription);

		phoneDescription.put("id", "A385");
		
		secondPhone = new IOSPhone(phoneDescription);
		
		thirdPhone = new IOSPhone ("IPhone 1", "Apple", 5,
				ScreenType.CAPACITIV, IOSVersion.IOS1);
	}

	@Test
	public void testDefaultConstructor() {
		assertEquals(null,defaultPhone.getBrandName());
		assertEquals(null,defaultPhone.getName());
		assertEquals(0,defaultPhone.getScreenSize());
		assertEquals(null,defaultPhone.getScreenType());
	}

	@Test
	public void testFirstConstructor() {
		assertEquals("Apple",firstPhone.getBrandName());
		assertEquals("3Gs",firstPhone.getName());
		assertEquals(5,firstPhone.getScreenSize());
		assertEquals(ScreenType.RESISTIV,firstPhone.getScreenType());
		assertEquals(IOSVersion.IOS4 ,firstPhone.getVersion());
		assertNotNull(firstPhone.getId());
	}
	
	@Test
	public void testFirstConstructorWithId() {
		assertEquals("Apple",secondPhone.getBrandName());
		assertEquals("3Gs",secondPhone.getName());
		assertEquals(5,secondPhone.getScreenSize());
		assertEquals(ScreenType.RESISTIV,secondPhone.getScreenType());
		assertEquals(IOSVersion.IOS4 ,secondPhone.getVersion());
		assertNotNull(secondPhone.getId());
		assertEquals("A385",secondPhone.getId());
	}
	
	
	@Test
	public void testSecondConstructor() {
		assertEquals("Apple",thirdPhone.getBrandName());
		assertEquals("IPhone 1",thirdPhone.getName());
		assertEquals(5,thirdPhone.getScreenSize());
		assertEquals(ScreenType.CAPACITIV,thirdPhone.getScreenType());
		assertEquals(IOSVersion.IOS1,thirdPhone.getVersion());
		assertNotNull(thirdPhone.getId());
	}
	
	
	@Test
	public void testGetterSetter() {
		assertEquals("Apple",thirdPhone.getBrandName());
		thirdPhone.setBrandName("Apple Inc");
		assertEquals("Apple Inc",thirdPhone.getBrandName());

		assertEquals("IPhone 1",thirdPhone.getName());
		thirdPhone.setName("IPhone 5C");
		assertEquals("IPhone 5C",thirdPhone.getName());

		assertEquals(5,thirdPhone.getScreenSize());
		thirdPhone.setScreenSize(4);
		assertEquals(4,thirdPhone.getScreenSize());
		
		assertEquals(ScreenType.CAPACITIV,thirdPhone.getScreenType());
		thirdPhone.setScreenType(ScreenType.RESISTIV);
		assertEquals(ScreenType.RESISTIV,thirdPhone.getScreenType());
		
		assertEquals(IOSVersion.IOS1,thirdPhone.getVersion());
		thirdPhone.setVersion(IOSVersion.IOS5);
		assertEquals(IOSVersion.IOS5,thirdPhone.getVersion());

		assertNotNull(thirdPhone.getId());
		thirdPhone.setId("A3102");
		assertEquals("A3102", thirdPhone.getId());
	}
	
	
	@Test
	public void testGetDescriptionDefaultPhone() {
		Map<String, Object> descriptionDefaultPhone = new HashMap<String, Object>();
		descriptionDefaultPhone.put("name", null);
		descriptionDefaultPhone.put("brandName", null);
		descriptionDefaultPhone.put("screenSize", 0);
		descriptionDefaultPhone.put("screenType", null);
		descriptionDefaultPhone.put("version", null);
		descriptionDefaultPhone.put("id", null);
		
		assertEquals(descriptionDefaultPhone, defaultPhone.getDescription());
	}
	
	@Test
	public void testGetDescriptionFirstPhone() {
		Map<String, Object> descriptionFirstPhone = new HashMap<String, Object>();
		descriptionFirstPhone.put("name", "3Gs");
		descriptionFirstPhone.put("brandName", "Apple");
		descriptionFirstPhone.put("screenSize", 5);
		descriptionFirstPhone.put("screenType", ScreenType.RESISTIV);
		descriptionFirstPhone.put("version",  IOSVersion.IOS4 );
		descriptionFirstPhone.put("id", firstPhone.getId());
		
		assertEquals(descriptionFirstPhone, firstPhone.getDescription());
	}
	
	
	@Test
	public void testGetDescriptionSecondPhone() {
		Map<String, Object> descriptionSecondPhone = new HashMap<String, Object>();
		descriptionSecondPhone.put("name", "3Gs");
		descriptionSecondPhone.put("brandName", "Apple");
		descriptionSecondPhone.put("screenSize", 5);
		descriptionSecondPhone.put("screenType", ScreenType.RESISTIV);
		descriptionSecondPhone.put("version",  IOSVersion.IOS4 );
		descriptionSecondPhone.put("id", "A385");
		
		assertEquals(descriptionSecondPhone, secondPhone.getDescription());
	}
	
	
	@Test
	public void testGetDescriptionSeconThirdPhone() {
		Map<String, Object> descriptionSecondPhone = new HashMap<String, Object>();
		descriptionSecondPhone.put("name", "IPhone 1");
		descriptionSecondPhone.put("brandName", "Apple");
		descriptionSecondPhone.put("screenSize", 5);
		descriptionSecondPhone.put("screenType", ScreenType.CAPACITIV);
		descriptionSecondPhone.put("version", IOSVersion.IOS1 );
		descriptionSecondPhone.put("id", thirdPhone.getId());
		
		assertEquals(descriptionSecondPhone, thirdPhone.getDescription());
	}
	
	@Test
	public void testRestoreAndEquals() {
		Map<String, Object> phoneDescription = new HashMap<String, Object>();
		phoneDescription.put("name", "3Gs");
		phoneDescription.put("brandName", "Apple");
		phoneDescription.put("screenSize", 5);
		phoneDescription.put("screenType", ScreenType.RESISTIV);
		phoneDescription.put("version", IOSVersion.IOS4 );
		phoneDescription.put("id", firstPhone.getId());
		
		assertEquals(phoneDescription, firstPhone.getDescription());
		
		defaultPhone.restore(phoneDescription);
		
		assertTrue(defaultPhone.equals(firstPhone));
		assertFalse(secondPhone.equals(firstPhone));
		assertFalse(thirdPhone.equals(firstPhone));
		
		secondPhone.restore(phoneDescription);
		thirdPhone.restore(phoneDescription);

		assertTrue(defaultPhone.equals(thirdPhone));
		assertTrue(firstPhone.equals(thirdPhone));
		assertTrue(secondPhone.equals(thirdPhone));
	}
}
