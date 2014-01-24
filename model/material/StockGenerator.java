package model.material;

import java.util.HashMap;
import java.util.Map;

import model.State;
import model.user.IUser;
import model.user.Student;
import model.user.Teacher;

public class StockGenerator {

	public static void storeStockDescription(StockManager sm) {
		AndroidPhone firstPhone = null;
		IOSPhone secondPhone = null;
		AndroidTablet firstTablet = null;

		Map<String, Map<Class<? extends IUser>, Integer>> limitsDescription = new HashMap<String, Map<Class<? extends IUser>, Integer>>();

		Map<Class<? extends IUser>, Integer> copyLimitation = new HashMap<Class<? extends IUser>, Integer>();
		Map<Class<? extends IUser>, Integer> delayLimitation = new HashMap<Class<? extends IUser>, Integer>();
		Map<Class<? extends IUser>, Integer> durationLimitation = new HashMap<Class<? extends IUser>, Integer>();

		
		
		copyLimitation.put(Student.class, new Integer(2));
		copyLimitation.put(Teacher.class, new Integer(10));

		delayLimitation.put(Student.class, new Integer(2));
		delayLimitation.put(Teacher.class, new Integer(14));

		durationLimitation.put(Student.class, new Integer(14));
		durationLimitation.put(Teacher.class, new Integer(30));

		limitsDescription.put(Material.KEY_LIMIT_COPY, copyLimitation);
		limitsDescription.put(Material.KEY_LIMIT_DELAY,
				delayLimitation);
		limitsDescription.put(Material.KEY_LIMIT_DURATION,
				durationLimitation);

		
		firstPhone = new AndroidPhone("Cink Five", "Wiko", 5,
				ScreenType.CAPACITIV, AndroidPhoneVersion.ICE_CREAM_SANDWICH, State.GOOD,
				limitsDescription);

		
		
		limitsDescription = new HashMap<String, Map<Class<? extends IUser>, Integer>>();

		copyLimitation = new HashMap<Class<? extends IUser>, Integer>();
		copyLimitation.put(Student.class, new Integer(1));
		copyLimitation.put(Teacher.class, new Integer(30));

		delayLimitation = new HashMap<Class<? extends IUser>, Integer>();
		delayLimitation.put(Student.class, new Integer(7));
		delayLimitation.put(Teacher.class, new Integer(15));

		durationLimitation = new HashMap<Class<? extends IUser>, Integer>();
		durationLimitation.put(Student.class, new Integer(7));
		durationLimitation.put(Teacher.class, new Integer(30));

		limitsDescription.put(Material.KEY_LIMIT_COPY, copyLimitation);
		limitsDescription.put(Material.KEY_LIMIT_DELAY,
				delayLimitation);
		limitsDescription.put(Material.KEY_LIMIT_DURATION,
				durationLimitation);
		
		secondPhone = new IOSPhone("IPhone 4S", "Apple", 4, ScreenType.CAPACITIV, IOSVersion.IOS6, State.UNUSED, limitsDescription);
	
	
	
		limitsDescription = new HashMap<String, Map<Class<? extends IUser>, Integer>>();

		copyLimitation = new HashMap<Class<? extends IUser>, Integer>();
		copyLimitation.put(Student.class, new Integer(1));
		copyLimitation.put(Teacher.class, new Integer(30));

		delayLimitation = new HashMap<Class<? extends IUser>, Integer>();
		delayLimitation.put(Student.class, new Integer(7));
		delayLimitation.put(Teacher.class, new Integer(15));

		durationLimitation = new HashMap<Class<? extends IUser>, Integer>();
		durationLimitation.put(Student.class, new Integer(7));
		durationLimitation.put(Teacher.class, new Integer(30));

		limitsDescription.put(Material.KEY_LIMIT_COPY, copyLimitation);
		limitsDescription.put(Material.KEY_LIMIT_DELAY,
				delayLimitation);
		limitsDescription.put(Material.KEY_LIMIT_DURATION,
				durationLimitation);
		
		firstTablet = new AndroidTablet("Galaxy Tab 2", "Samsung", 10, ScreenType.CAPACITIV, AndroidTabletVersion.JELLY_BEAN, State.UNUSED, limitsDescription);
	
		
		sm.addProduct(firstPhone);
		sm.addProduct(secondPhone);
		sm.addProduct(firstTablet);
		
		sm.store();
	}
}
