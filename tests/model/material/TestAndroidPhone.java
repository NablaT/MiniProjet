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
		phoneDescription.put("version", AndroidPhoneVersion.ECLAIR);
		phoneDescription.put("state", State.GOOD);

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

		firstPhone = new AndroidPhone(phoneDescription);

		phoneDescription.put("id", "CW99");

		secondPhone = new AndroidPhone(phoneDescription);

		thirdPhone = new AndroidPhone("Galaxy S4", "Samsung", 5,
				ScreenType.CAPACITIV, AndroidPhoneVersion.CUPCAKE, State.GOOD,
				limitsDescription);
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
		assertEquals("Wiko", firstPhone.getBrandName());
		assertEquals("Cink Five", firstPhone.getName());
		assertEquals(5, firstPhone.getScreenSize());
		assertEquals(ScreenType.RESISTIV, firstPhone.getScreenType());
		assertEquals(AndroidPhoneVersion.ECLAIR, firstPhone.getVersion());
		assertNotNull(firstPhone.getId());
	}

	@Test
	public void testFirstConstructorWithId() {
		assertEquals("Wiko", secondPhone.getBrandName());
		assertEquals("Cink Five", secondPhone.getName());
		assertEquals(5, secondPhone.getScreenSize());
		assertEquals(ScreenType.RESISTIV, secondPhone.getScreenType());
		assertEquals(AndroidPhoneVersion.ECLAIR, secondPhone.getVersion());
		assertEquals("CW99", secondPhone.getId());
	}

	@Test
	public void testSecondConstructor() {
		assertEquals("Samsung", thirdPhone.getBrandName());
		assertEquals("Galaxy S4", thirdPhone.getName());
		assertEquals(5, thirdPhone.getScreenSize());
		assertEquals(ScreenType.CAPACITIV, thirdPhone.getScreenType());
		assertEquals(AndroidPhoneVersion.CUPCAKE, thirdPhone.getVersion());
		assertNotNull(thirdPhone.getId());
	}

	@Test
	public void testGetterSetter() {
		assertEquals("Samsung", thirdPhone.getBrandName());
		thirdPhone.setBrandName("Asus");
		assertEquals("Asus", thirdPhone.getBrandName());

		assertEquals("Galaxy S4", thirdPhone.getName());
		thirdPhone.setName("Nexus 7");
		assertEquals("Nexus 7", thirdPhone.getName());

		assertEquals(5, thirdPhone.getScreenSize());
		thirdPhone.setScreenSize(7);
		assertEquals(7, thirdPhone.getScreenSize());

		assertEquals(ScreenType.CAPACITIV, thirdPhone.getScreenType());
		thirdPhone.setScreenType(ScreenType.RESISTIV);
		assertEquals(ScreenType.RESISTIV, thirdPhone.getScreenType());

		assertEquals(AndroidPhoneVersion.CUPCAKE, thirdPhone.getVersion());
		thirdPhone.setVersion(AndroidPhoneVersion.JELLY_BEAN);
		assertEquals(AndroidPhoneVersion.JELLY_BEAN, thirdPhone.getVersion());

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
		descriptionDefaultPhone.put("state", null);
		descriptionDefaultPhone.put("limits", new HashMap<Object, Object>());

		assertEquals(descriptionDefaultPhone, defaultPhone.getDescription());
	}

	@Test
	public void testGetDescriptionFirstPhone() {
		Map<String, Object> descriptionFirstPhone = new HashMap<String, Object>();
		descriptionFirstPhone.put("name", "Cink Five");
		descriptionFirstPhone.put("brandName", "Wiko");
		descriptionFirstPhone.put("screenSize", 5);
		descriptionFirstPhone.put("screenType", ScreenType.RESISTIV);
		descriptionFirstPhone.put("version", AndroidPhoneVersion.ECLAIR);
		descriptionFirstPhone.put("id", firstPhone.getId());
		descriptionFirstPhone.put("state", State.GOOD);

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
		descriptionSecondPhone.put("name", "Cink Five");
		descriptionSecondPhone.put("brandName", "Wiko");
		descriptionSecondPhone.put("screenSize", 5);
		descriptionSecondPhone.put("screenType", ScreenType.RESISTIV);
		descriptionSecondPhone.put("version", AndroidPhoneVersion.ECLAIR);
		descriptionSecondPhone.put("id", secondPhone.getId());
		descriptionSecondPhone.put("state", State.GOOD);

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

		assertEquals(descriptionSecondPhone, secondPhone.getDescription());
	}

	@Test
	public void testGetDescriptionSecondThirdPhone() {
		Map<String, Object> descriptionSecondPhone = new HashMap<String, Object>();
		descriptionSecondPhone.put("name", "Galaxy S4");
		descriptionSecondPhone.put("brandName", "Samsung");
		descriptionSecondPhone.put("screenSize", 5);
		descriptionSecondPhone.put("screenType", ScreenType.CAPACITIV);
		descriptionSecondPhone.put("version", AndroidPhoneVersion.CUPCAKE);
		descriptionSecondPhone.put("id", thirdPhone.getId());
		descriptionSecondPhone.put("state", State.GOOD);

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
		phoneDescription.put("name", "Cink Five");
		phoneDescription.put("brandName", "Wiko");
		phoneDescription.put("screenSize", 5);
		phoneDescription.put("screenType", ScreenType.RESISTIV);
		phoneDescription.put("version", AndroidPhoneVersion.ECLAIR);
		phoneDescription.put("id", firstPhone.getId());

		phoneDescription.put("state", State.GOOD);

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
