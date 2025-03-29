import java.util.HashMap;
import java.util.Set;
import java.util.Iterator; 
/**
 * Class Item - Creates items to be found within rooms in the game.
 * 
 * This class creates items to be used in the "World of Zuul" game. The items
 * are assigned to rooms and players can pick up the item(s). 
 *
 * @author Emily Lubonty
 * @version 3-29-2025
 */
public class Item
{
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
    
    /**
     * Sets the item's weight. 
     * @param itemWeight the item's weight
     */
    public void setWeight(String itemWeight, Item item)
    {
        items.put(itemWeight, item); 
    }
    
    
    /**
     * The item's description.
     * 
     * @return returns the item's description.
     */
    public String getItemDescription()
    {
        return itemDescription;
    }
    
    /**
     * The item's weight.
     * 
     * @return returns the item's weight.
     */
    public String getItemWeight()
    {
        return itemWeight;
    }
    
    /**
     * @return returns the short description of the item
     */
    public String getShortDescription()
    {
        return itemDescription;
    }
    
    /**
     * @return returns the description of the item in the format of a string. 
     * 
     */
    public String getLongDescription()
    {
        return "There is " + itemDescription + ".\n" + getItemString();
    }
    
    /**
     * Returns the details of the items.
     * @return returns information regarding the items.
     */
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
