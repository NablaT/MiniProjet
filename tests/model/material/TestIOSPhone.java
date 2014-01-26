package model.material;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.IUser;
import model.user.Student;
import model.user.Teacher;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestIOSPhone {

	private static IOSPhone defaultPhone;
	private static Map<String, Object> phoneDescription;
	private static IOSPhone firstPhone;
	private static IOSPhone secondPhone;
	private static IOSPhone thirdPhone;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		defaultPhone = new IOSPhone();

		phoneDescription = new HashMap<String, Object>();

		phoneDescription.put("name", "3Gs");
		phoneDescription.put("brandName", "Apple");
		phoneDescription.put("screenSize", 5);
		phoneDescription.put("screenType", ScreenType.RESISTIV);
		phoneDescription.put("version", IOSVersion.IOS4);
		phoneDescription.put("state", State.BROKEN);

		Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription = new HashMap<String, Map<Class<? extends IUser>, Integer>>();

		Map<Class<? extends IUser>, Integer> copyLimitsForFirstPhone = new HashMap<Class<? extends IUser>, Integer>();
		copyLimitsForFirstPhone.put(Student.class, new Integer(2));
		copyLimitsForFirstPhone.put(Teacher.class, new Integer(10));

		Map<Class<? extends IUser>, Integer> delayLimitsForFirstPhone = new HashMap<Class<? extends IUser>, Integer>();
		delayLimitsForFirstPhone.put(Student.class, new Integer(2));
		delayLimitsForFirstPhone.put(Teacher.class, new Integer(14));

		Map<Class<? extends IUser>, Integer> durationLimitsForFirstPhone = new HashMap<Class<? extends IUser>, Integer>();
		durationLimitsForFirstPhone.put(Student.class, new Integer(14));
		durationLimitsForFirstPhone.put(Teacher.class, new Integer(30));

		limitsDescription.put(Material.KEY_LIMIT_COPY, copyLimitsForFirstPhone);
		limitsDescription.put(Material.KEY_LIMIT_DELAY,
				delayLimitsForFirstPhone);
		limitsDescription.put(Material.KEY_LIMIT_DURATION,
				durationLimitsForFirstPhone);

		phoneDescription.put("limits", limitsDescription);

		firstPhone = new IOSPhone(phoneDescription);

		phoneDescription.put("id", "A385");

		secondPhone = new IOSPhone(phoneDescription);

		thirdPhone = new IOSPhone("IPhone 1", "Apple", 5, ScreenType.CAPACITIV,
				IOSVersion.IOS1, State.BROKEN, limitsDescription);
	}

	@Test
	public void testDefaultConstructor() {
		assertEquals(null, defaultPhone.getBrandName());
		assertEquals(null, defaultPhone.getName());
		assertEquals(0, defaultPhone.getScreenSize());
		assertEquals(null, defaultPhone.getScreenType());
	}

	@Test
	public void testFirstConstructor() {
		assertEquals("Apple", firstPhone.getBrandName());
		assertEquals("3Gs", firstPhone.getName());
		assertEquals(5, firstPhone.getScreenSize());
		assertEquals(ScreenType.RESISTIV, firstPhone.getScreenType());
		assertEquals(IOSVersion.IOS4, firstPhone.getVersion());
		assertNotNull(firstPhone.getId());
	}

	@Test
	public void testFirstConstructorWithId() {
		assertEquals("Apple", secondPhone.getBrandName());
		assertEquals("3Gs", secondPhone.getName());
		assertEquals(5, secondPhone.getScreenSize());
		assertEquals(ScreenType.RESISTIV, secondPhone.getScreenType());
		assertEquals(IOSVersion.IOS4, secondPhone.getVersion());
		assertNotNull(secondPhone.getId());
		assertEquals("A385", secondPhone.getId());
	}

	@Test
	public void testSecondConstructor() {
		assertEquals("Apple", thirdPhone.getBrandName());
		assertEquals("IPhone 1", thirdPhone.getName());
		assertEquals(5, thirdPhone.getScreenSize());
		assertEquals(ScreenType.CAPACITIV, thirdPhone.getScreenType());
		assertEquals(IOSVersion.IOS1, thirdPhone.getVersion());
		assertNotNull(thirdPhone.getId());
	}

	@Test
	public void testGetterSetter() {
		assertEquals("Apple", thirdPhone.getBrandName());
		thirdPhone.setBrandName("Apple Inc");
		assertEquals("Apple Inc", thirdPhone.getBrandName());

		assertEquals("IPhone 1", thirdPhone.getName());
		thirdPhone.setName("IPhone 5C");
		assertEquals("IPhone 5C", thirdPhone.getName());

		assertEquals(5, thirdPhone.getScreenSize());
		thirdPhone.setScreenSize(4);
		assertEquals(4, thirdPhone.getScreenSize());

		assertEquals(ScreenType.CAPACITIV, thirdPhone.getScreenType());
		thirdPhone.setScreenType(ScreenType.RESISTIV);
		assertEquals(ScreenType.RESISTIV, thirdPhone.getScreenType());

		assertEquals(IOSVersion.IOS1, thirdPhone.getVersion());
		thirdPhone.setVersion(IOSVersion.IOS5);
		assertEquals(IOSVersion.IOS5, thirdPhone.getVersion());

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
		descriptionDefaultPhone.put("state", null);
		descriptionDefaultPhone.put("limits", new HashMap<Object, Object>());

		assertEquals(descriptionDefaultPhone, defaultPhone.getDescription());
	}

	@Test
	public void testGetDescriptionFirstPhone() {
		Map<String, Object> descriptionFirstPhone = new HashMap<String, Object>();
		descriptionFirstPhone.put("name", "3Gs");
		descriptionFirstPhone.put("brandName", "Apple");
		descriptionFirstPhone.put("screenSize", 5);
		descriptionFirstPhone.put("screenType", ScreenType.RESISTIV);
		descriptionFirstPhone.put("version", IOSVersion.IOS4);
		descriptionFirstPhone.put("id", firstPhone.getId());
		descriptionFirstPhone.put("state", State.BROKEN);

		Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription = new HashMap<String, Map<Class<? extends IUser>, Integer>>();

		Map<Class<? extends IUser>, Integer> copyLimitsForFirstPhone = new HashMap<Class<? extends IUser>, Integer>();
		copyLimitsForFirstPhone.put(Student.class, new Integer(2));
		copyLimitsForFirstPhone.put(Teacher.class, new Integer(10));

		Map<Class<? extends IUser>, Integer> delayLimitsForFirstPhone = new HashMap<Class<? extends IUser>, Integer>();
		delayLimitsForFirstPhone.put(Student.class, new Integer(2));
		delayLimitsForFirstPhone.put(Teacher.class, new Integer(14));

		Map<Class<? extends IUser>, Integer> durationLimitsForFirstPhone = new HashMap<Class<? extends IUser>, Integer>();
		durationLimitsForFirstPhone.put(Student.class, new Integer(14));
		durationLimitsForFirstPhone.put(Teacher.class, new Integer(30));

		limitsDescription.put(Material.KEY_LIMIT_COPY, copyLimitsForFirstPhone);
		limitsDescription.put(Material.KEY_LIMIT_DELAY,
				delayLimitsForFirstPhone);
		limitsDescription.put(Material.KEY_LIMIT_DURATION,
				durationLimitsForFirstPhone);

		descriptionFirstPhone.put("limits", limitsDescription);
	
		assertEquals(descriptionFirstPhone, firstPhone.getDescription());
	}

	@Test
	public void testGetDescriptionSecondPhone() {
		Map<String, Object> descriptionSecondPhone = new HashMap<String, Object>();
		descriptionSecondPhone.put("name", "3Gs");
		descriptionSecondPhone.put("brandName", "Apple");
		descriptionSecondPhone.put("screenSize", 5);
		descriptionSecondPhone.put("screenType", ScreenType.RESISTIV);
		descriptionSecondPhone.put("version", IOSVersion.IOS4);
		descriptionSecondPhone.put("id", "A385");

		descriptionSecondPhone.put("state", State.BROKEN);

		Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription = new HashMap<String, Map<Class<? extends IUser>, Integer>>();

		Map<Class<? extends IUser>, Integer> copyLimitsForSecondPhone = new HashMap<Class<? extends IUser>, Integer>();
		copyLimitsForSecondPhone.put(Student.class, new Integer(2));
		copyLimitsForSecondPhone.put(Teacher.class, new Integer(10));

		Map<Class<? extends IUser>, Integer> delayLimitsForSecondPhone = new HashMap<Class<? extends IUser>, Integer>();
		delayLimitsForSecondPhone.put(Student.class, new Integer(2));
		delayLimitsForSecondPhone.put(Teacher.class, new Integer(14));

		Map<Class<? extends IUser>, Integer> durationLimitsForSecondPhone = new HashMap<Class<? extends IUser>, Integer>();
		durationLimitsForSecondPhone.put(Student.class, new Integer(14));
		durationLimitsForSecondPhone.put(Teacher.class, new Integer(30));

		limitsDescription
				.put(Material.KEY_LIMIT_COPY, copyLimitsForSecondPhone);
		limitsDescription.put(Material.KEY_LIMIT_DELAY,
				delayLimitsForSecondPhone);
		limitsDescription.put(Material.KEY_LIMIT_DURATION,
				durationLimitsForSecondPhone);

		descriptionSecondPhone.put("limits", limitsDescription);

		assertEquals(descriptionSecondPhone, secondPhone.getDescription());	}

	@Test
	public void testGetDescriptionThirdPhone() {
		Map<String, Object> descriptionSecondPhone = new HashMap<String, Object>();
		descriptionSecondPhone.put("name", "IPhone 5C");
		descriptionSecondPhone.put("brandName", "Apple Inc");
		descriptionSecondPhone.put("screenSize", 4);
		descriptionSecondPhone.put("screenType", ScreenType.RESISTIV);
		descriptionSecondPhone.put("version", IOSVersion.IOS5);
		descriptionSecondPhone.put("id", thirdPhone.getId());
		descriptionSecondPhone.put("state", State.BROKEN);

		Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription = new HashMap<String, Map<Class<? extends IUser>, Integer>>();

		Map<Class<? extends IUser>, Integer> copyLimitsForSecondPhone = new HashMap<Class<? extends IUser>, Integer>();
		copyLimitsForSecondPhone.put(Student.class, new Integer(2));
		copyLimitsForSecondPhone.put(Teacher.class, new Integer(10));

		Map<Class<? extends IUser>, Integer> delayLimitsForSecondPhone = new HashMap<Class<? extends IUser>, Integer>();
		delayLimitsForSecondPhone.put(Student.class, new Integer(2));
		delayLimitsForSecondPhone.put(Teacher.class, new Integer(14));

		Map<Class<? extends IUser>, Integer> durationLimitsForSecondPhone = new HashMap<Class<? extends IUser>, Integer>();
		durationLimitsForSecondPhone.put(Student.class, new Integer(14));
		durationLimitsForSecondPhone.put(Teacher.class, new Integer(30));

		limitsDescription
				.put(Material.KEY_LIMIT_COPY, copyLimitsForSecondPhone);
		limitsDescription.put(Material.KEY_LIMIT_DELAY,
				delayLimitsForSecondPhone);
		limitsDescription.put(Material.KEY_LIMIT_DURATION,
				durationLimitsForSecondPhone);

		descriptionSecondPhone.put("limits", limitsDescription);
		
		assertEquals(descriptionSecondPhone, thirdPhone.getDescription());	
	}

	@Test
	public void testRestoreAndEquals() {
		Map<String, Object> phoneDescription = new HashMap<String, Object>();
		phoneDescription.put("name", "3Gs");
		phoneDescription.put("brandName", "Apple");
		phoneDescription.put("screenSize", 5);
		phoneDescription.put("screenType", ScreenType.RESISTIV);
		phoneDescription.put("version", IOSVersion.IOS4);
		phoneDescription.put("id", firstPhone.getId());
		phoneDescription.put("state", State.BROKEN);

		Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription = new HashMap<String, Map<Class<? extends IUser>, Integer>>();

		Map<Class<? extends IUser>, Integer> copyLimitsForDefaultPhone = new HashMap<Class<? extends IUser>, Integer>();
		copyLimitsForDefaultPhone.put(Student.class, new Integer(2));
		copyLimitsForDefaultPhone.put(Teacher.class, new Integer(10));

		Map<Class<? extends IUser>, Integer> delayLimitsForDefaultPhone = new HashMap<Class<? extends IUser>, Integer>();
		delayLimitsForDefaultPhone.put(Student.class, new Integer(2));
		delayLimitsForDefaultPhone.put(Teacher.class, new Integer(14));

		Map<Class<? extends IUser>, Integer> durationLimitsForDefaultPhone = new HashMap<Class<? extends IUser>, Integer>();
		durationLimitsForDefaultPhone.put(Student.class, new Integer(14));
		durationLimitsForDefaultPhone.put(Teacher.class, new Integer(30));

		limitsDescription.put(Material.KEY_LIMIT_COPY,
				copyLimitsForDefaultPhone);
		limitsDescription.put(Material.KEY_LIMIT_DELAY,
				delayLimitsForDefaultPhone);
		limitsDescription.put(Material.KEY_LIMIT_DURATION,
				durationLimitsForDefaultPhone);

		phoneDescription.put("limits", limitsDescription);

		System.out.println(phoneDescription + "\n" + firstPhone.getDescription());
		
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
