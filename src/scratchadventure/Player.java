/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */

import java.util.List;


// Player class and personal inventory
public class Player {
    // player inventory
    private final ItemCollection inventory = new ItemCollection();
    
    // inventory output
    public String describeInventory() {
        return inventory.listItemNames();
    }
    
    // add item to inventory
    public void addItem(Item item) {
        inventory.addItem(item);
    }
    
    // remove item from inventory
    public boolean removeItem(Item item) {
        return inventory.removeItem(item);
    }
    
    // find item without removal; ie 'examine'
    public List<Item> findItemsByName(String itemName) {
        return inventory.findItemsByName(itemName);
    }
    
    // saved list
    public List<String> getItemIds() {
        return inventory.getItemIds();
    
    }
    
    public void clearItems() {
        inventory.clear();
    }
    
}
