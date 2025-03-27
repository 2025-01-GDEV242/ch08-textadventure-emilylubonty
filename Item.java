import java.util.HashMap;
import java.util.Set;
import java.util.Iterator; 
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private String itemWeight; 
    private HashMap<String, Item> items;

    /**
     * Constructor for objects of class Item
     */
    public Item(String itemDescription)
    {
        this.itemDescription = itemDescription; 
        this.itemWeight = itemWeight; 
        items = new HashMap<>(); 
    }

    public void setRoomItem(String description, Item name)
    {
        items.put(description, name); 
    }
    
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getItemDescription()
    {
        return itemDescription;
    }
    
    public String getItemWeight()
    {
        return itemWeight;
    }
    
    private String getLongDescription()
    {
        return "There is " + itemDescription + ".\n" + getItemString();
    }
    
    
    private String getItemString()
    {
        String returnString = "Items: ";
        
        Set<String> keys = items.keySet();
        for(String item : keys) {
            returnString += " " + item;
        }
        return returnString; 
    }
    
}
