package model.material;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestAndroidPhone {

	private static AndroidPhone defaultPhone;
	private static Map<String, Object> phoneDescription;
	private static AndroidPhone firstPhone;
	private static AndroidPhone secondPhone;
	private static AndroidPhone thirdPhone;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		defaultPhone = new AndroidPhone();

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
	public void testDefaultConstructor() {
		assertEquals(null,defaultPhone.getBrandName());
		assertEquals(null,defaultPhone.getName());
		assertEquals(0,defaultPhone.getScreenSize());
		assertEquals(null,defaultPhone.getScreenType());
	}

	@Test
	public void testFirstConstructor() {
		assertEquals("Wiko",firstPhone.getBrandName());
		assertEquals("Cink Five",firstPhone.getName());
		assertEquals(5,firstPhone.getScreenSize());
		assertEquals(ScreenType.RESISTIV,firstPhone.getScreenType());
		assertEquals(AndroidVersion.ECLAIR,firstPhone.getVersion());
		assertNotNull(firstPhone.getId());
	}
	
	@Test
	public void testFirstConstructorWithId() {
		assertEquals("Wiko",secondPhone.getBrandName());
		assertEquals("Cink Five",secondPhone.getName());
		assertEquals(5,secondPhone.getScreenSize());
		assertEquals(ScreenType.RESISTIV,secondPhone.getScreenType());
		assertEquals(AndroidVersion.ECLAIR,secondPhone.getVersion());
		assertEquals("CW99",secondPhone.getId());
	}
	
	
	@Test
	public void testSecondConstructor() {
		assertEquals("Samsung",thirdPhone.getBrandName());
		assertEquals("Galaxy S4",thirdPhone.getName());
		assertEquals(5,thirdPhone.getScreenSize());
		assertEquals(ScreenType.CAPACITIV,thirdPhone.getScreenType());
		assertEquals(AndroidVersion.CUPCAKE,thirdPhone.getVersion());
		assertNotNull(thirdPhone.getId());
	}
	
	
	@Test
	public void testGetterSetter() {
		assertEquals("Samsung",thirdPhone.getBrandName());
		thirdPhone.setBrandName("Asus");
		assertEquals("Asus",thirdPhone.getBrandName());

		assertEquals("Galaxy S4",thirdPhone.getName());
		thirdPhone.setName("Nexus 7");
		assertEquals("Nexus 7",thirdPhone.getName());

		assertEquals(5,thirdPhone.getScreenSize());
		thirdPhone.setScreenSize(7);
		assertEquals(7,thirdPhone.getScreenSize());
		
		assertEquals(ScreenType.CAPACITIV,thirdPhone.getScreenType());
		thirdPhone.setScreenType(ScreenType.RESISTIV);
		assertEquals(ScreenType.RESISTIV,thirdPhone.getScreenType());
		
		assertEquals(AndroidVersion.CUPCAKE,thirdPhone.getVersion());
		thirdPhone.setVersion(AndroidVersion.JELLY_BEAN);
		assertEquals(AndroidVersion.JELLY_BEAN,thirdPhone.getVersion());

		assertNotNull(thirdPhone.getId());
		thirdPhone.setId("NA85");
		assertEquals("NA85", thirdPhone.getId());
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
		descriptionFirstPhone.put("name", "Cink Five");
		descriptionFirstPhone.put("brandName", "Wiko");
		descriptionFirstPhone.put("screenSize", 5);
		descriptionFirstPhone.put("screenType", ScreenType.RESISTIV);
		descriptionFirstPhone.put("version", AndroidVersion.ECLAIR );
		descriptionFirstPhone.put("id", firstPhone.getId());
		
		assertEquals(descriptionFirstPhone, firstPhone.getDescription());
	}
	
	
	@Test
	public void testGetDescriptionSecondPhone() {
		Map<String, Object> descriptionSecondPhone = new HashMap<String, Object>();
		descriptionSecondPhone.put("name", "Cink Five");
		descriptionSecondPhone.put("brandName", "Wiko");
		descriptionSecondPhone.put("screenSize", 5);
		descriptionSecondPhone.put("screenType", ScreenType.RESISTIV);
		descriptionSecondPhone.put("version", AndroidVersion.ECLAIR );
		descriptionSecondPhone.put("id", secondPhone.getId());
		
		assertEquals(descriptionSecondPhone, secondPhone.getDescription());
	}
	
	
	@Test
	public void testGetDescriptionSeconThirdPhone() {
		Map<String, Object> descriptionSecondPhone = new HashMap<String, Object>();
		descriptionSecondPhone.put("name", "Galaxy S4");
		descriptionSecondPhone.put("brandName", "Samsung");
		descriptionSecondPhone.put("screenSize", 5);
		descriptionSecondPhone.put("screenType", ScreenType.CAPACITIV);
		descriptionSecondPhone.put("version", AndroidVersion.CUPCAKE );
		descriptionSecondPhone.put("id", thirdPhone.getId());
		
		assertEquals(descriptionSecondPhone, thirdPhone.getDescription());
	}


	@Test
	public void testRestoreAndEquals() {
		Map<String, Object> phoneDescription = new HashMap<String, Object>();
		phoneDescription.put("name", "Cink Five");
		phoneDescription.put("brandName", "Wiko");
		phoneDescription.put("screenSize", 5);
		phoneDescription.put("screenType", ScreenType.RESISTIV);
		phoneDescription.put("version", AndroidVersion.ECLAIR);
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
