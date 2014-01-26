package model.repair;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import model.material.Material;
import model.material.StockManager;

import org.junit.Before;
import org.junit.Test;

public class TestStockRepairManager {

    private GregorianCalendar date1 = new GregorianCalendar(2000, 1, 1);
    private GregorianCalendar today = new GregorianCalendar();
    private GregorianCalendar date2 = new GregorianCalendar(2030, 1, 1);
    private StockRepairManager manager1, manager2, managerNow;

    private ArrayList<Material> material1 = new ArrayList<Material>();
    private ArrayList<Material> material2 = new ArrayList<Material>();
    private ArrayList<Material> materialEmpty = new ArrayList<Material>();

    private StockManager sm;
    
    @Before
    public void setUp() {
    	
    	
    	
        manager1 = new StockRepairManager(date1);
        managerNow = new StockRepairManager(today);
        manager2 = new StockRepairManager(date2);

        sm.load();
        
        material1.add(sm.stockToList().get(0));
        material1.add(sm.stockToList().get(1));
        material2.add(sm.stockToList().get(2));
    }

    @Test
    public void addMaterialTest() {

        ArrayList<Material> m;

        // test if several material are add with anterior repaired date
        m = new ArrayList<Material>();
        manager1.addMaterial(material1, date1);
        manager1.addMaterial(material1, date1);
        for (int i = 0; i < material1.size(); i++)
            m.add(material1.get(i));
        for (int i = 0; i < material1.size(); i++)
            m.add(material1.get(i));
        assertTrue(m.equals(manager1.getRepairedMaterial()));

        // test if an empty arrayList is added
        manager1.addMaterial(materialEmpty, date2);
        assertTrue(m.equals(manager1.getRepairedMaterial()));

        // test if several material are add with different anterior and
        // posterior repaired date
        manager1.addMaterial(material1, date2);
        assertTrue(m.equals(manager1.getRepairedMaterial()));

        // test if actual date and repaired date are the same
        m = new ArrayList<Material>();
        managerNow.addMaterial(material2, today);
        m.add(material2.get(0));
        assertTrue(m.equals(managerNow.getRepairedMaterial()));

        // test for several material on several date before the repaired date
        // test also with an empty material
        m = new ArrayList<Material>();
        manager2.addMaterial(material2, date1);
        manager2.addMaterial(material1, today);
        manager2.addMaterial(materialEmpty, date1);
        m.add(material2.get(0));
        for (int i = 0; i < material1.size(); i++)
            m.add(material1.get(i));
        assertTrue(m.equals(manager2.getRepairedMaterial()));
    }

    @Test
    public void removeRepairedMaterialTest() {
        ArrayList<Material> m;

        // test if one material is not repaired
        m = new ArrayList<Material>();
        manager1.addMaterial(material1, date1);
        manager1.addMaterial(material2, date2);
        for (int i = 0; i < material1.size(); i++)
            m.add(material1.get(i));
        assertTrue(m.equals(manager1.getRepairedMaterial()));
        manager1.removeRepairedMaterial();
        m = new ArrayList<Material>();
        assertTrue(m.equals(manager1.getRepairedMaterial()));

        manager2.addMaterial(material1, date1);
        manager2.addMaterial(material2, date2);
        manager2.removeRepairedMaterial();
        assertTrue(manager2.getListOfProducts().size() == 0);

    }

    @Test
    public void XMLTest() {

        ArrayList<Material> m;
        int size;

        // first day we add material
        manager1.addMaterial(material1, date1);
        manager1.addMaterial(material2, today);
        manager1.addMaterial(material2, date2);
        size = manager1.getRepairedMaterial().size();

        m = new ArrayList<Material>();
        for (int i = 0; i < material1.size(); i++)
            m.add(material1.get(i));
        m.add(material2.get(0));
        m.add(material2.get(0));
        assertTrue(true);

        manager1.setXML();

        // second day we take it
        managerNow.getXML();
        assertTrue(managerNow != null);
        assertTrue(managerNow.getRepairedMaterial().size() != size);
        m = new ArrayList<>();
        for (int i = 0; i < material1.size(); i++)
            m.add(material1.get(i));
         m.add(material2.get(0));
        assertTrue(m.size()==managerNow.getRepairedMaterial().size());

        m = new ArrayList<>();
        managerNow.removeRepairedMaterial();
         assertTrue(m.equals(managerNow.getRepairedMaterial()));

        managerNow.setXML();

        manager2.getXML();
     
        m.add(material2.get(0));
        
        assertTrue(m.size()==manager2.getRepairedMaterial().size());
        
        m = new ArrayList<Material>();
        manager1.getXML();
        assertTrue(m.equals(manager1.getRepairedMaterial()));


    }

}
