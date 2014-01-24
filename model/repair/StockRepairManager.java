/**
 * 
 */
package model.repair;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import model.util.ConfigXML;
import model.material.Material;

/**
 * 
 * @author mael
 * 
 */


public class StockRepairManager {

    private static final String PATH_FILE = "repaired_material";
    private static final String VERSION_FILE = "0.0.0";

    private ArrayList<Repair> listOfProducts;
    private GregorianCalendar currentDay;
    
    public StockRepairManager(){
        listOfProducts=new ArrayList<Repair>();
        currentDay=new GregorianCalendar();
    }
    
    /**
     * Initialize listOfProducts with an empty ArrayList
     * 
     * @param currentDay
     */
    public StockRepairManager(GregorianCalendar currentDay) {
        listOfProducts = new ArrayList<Repair>();
        this.currentDay = currentDay;
    }

    /**
     * 
     * @param material_m
     *            List of material send to the repairer
     * @param end
     *            Date when the repairer will send back the material repaired
     * 
     *            this method add a new object Repair to listOfProducts
     *            if the ArrayList materials is empty the method do nothing
     */
    public void addMaterial(ArrayList<Material> materials,
            GregorianCalendar end) {
        if(materials.size()!=0){
            Repair products = new Repair(materials, end);
            listOfProducts.add(products);
        }
    }

    /**
     * 
     * @return return all material which have reach their day of reparation with
     *         regard to currendDay
     */
    public ArrayList<Material> getRepairedMaterial() {
        ArrayList<Material> repairedMaterial = new ArrayList<Material>();
        ArrayList<Material> material = new ArrayList<Material>();
        for (int i = 0; i < listOfProducts.size(); i++) {
            
            if (listOfProducts.get(i).isRepaired(currentDay)) {
                material = listOfProducts.get(i).getMaterial();
                for (int j = 0; j < material.size(); j++) {
                    repairedMaterial.add(material.get(j));
                }
            }
        }
        return repairedMaterial;
    }

    /**
     * remove from listOfProducts material which have reach the date end with
     * regard to current day
     */
    public void removeRepairedMaterial() {
        for (int i = listOfProducts.size()-1; i >= 0; i--) {
            if (listOfProducts.get(i).isRepaired(currentDay)) {
                listOfProducts.remove(i);
            }
        }
    }

    /**
     * stock listOfProducts in XML
     */
    public void setXML() {
        ConfigXML.store(listOfProducts, PATH_FILE, VERSION_FILE);
    }

    /**
     * get listOfProducts from XML file
     */
    @SuppressWarnings("unchecked")
    public void getXML() {
        if (ConfigXML.load(PATH_FILE, VERSION_FILE) != null)
            listOfProducts = (ArrayList<Repair>) ConfigXML.load(PATH_FILE,
                    VERSION_FILE);
    }
    
   ArrayList<Repair> getListOfProducts(){
        return listOfProducts;
    }
    
}