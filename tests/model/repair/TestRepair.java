package model.repair;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import model.material.*;

public class TestRepair {
    
    private GregorianCalendar date1=new GregorianCalendar(2000,1,1);
    private GregorianCalendar today=new GregorianCalendar();
    private GregorianCalendar date2=new GregorianCalendar(2030,1,1);
    
    private ArrayList<Material> material=new ArrayList<Material>();
    private ArrayList<Material> material2=new ArrayList<Material>();
    private ArrayList<Material> materialEmpty=new ArrayList<Material>();
    private Repair r,r1,r2,rEmpty;
    
    @Before
    public void setUp() {
        
        material.add(new Camera("102","Bob","Sony"));
        material.add(new AndroidPhone("103","Toto","Firefox"));
        
        material2.add(new IOSPhone("104","Titi","IOS"));
        
        r=new Repair(material,today);
        r1=new Repair(material,date1);
        r2=new Repair(material2,date2);
        rEmpty=new Repair(materialEmpty,today);
    }
    
    @Test
    public void isRepairedTest(){
        assertTrue(r.isRepaired(today));
        assertTrue(r1.isRepaired(date1));
        assertTrue(r2.isRepaired(date2));
        assertFalse(r.isRepaired(date1));
        assertTrue(r.isRepaired(date2));
        assertTrue(r1.isRepaired(today));
        assertTrue(rEmpty.isRepaired(today));
        assertFalse(rEmpty.isRepaired(date1));
    }
    
    @Test
    public void getMaterialTest(){
        assertTrue(r.getMaterial().equals(material));
        assertTrue(rEmpty.getMaterial().equals(materialEmpty));
    }
    
}
