/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Stores and manages a group of Item objects
 *
 * Room uses ItemCollection for its contents Player uses ItemCollection for
 * inventory
 *
 * @author Dylan Canfield
 */
public class ItemCollection {

    // List of Item objects in the collection
    private final List<Item> storedItems = new ArrayList<>();

    // add item into collection
    public void addItem(Item newItem) {
        storedItems.add(newItem);
    }

    // Creates a display list of stored items
    // ex  : "coin, axe" or "nothing"
    public String listItemNames() {
        if (isEmpty()) {
            return "nothing";
        }

        StringJoiner itemNames = new StringJoiner(", ");

        for (Item storedItem : storedItems) {
            itemNames.add(storedItem.getName());
        }

        return itemNames.toString();
    }

    public boolean isEmpty() {
        return storedItems.isEmpty();
    }

    // Finds item by name and removes it from ItemCollection list
    // Returns item or null if not found
    public boolean removeItem(Item item) {
        if (storedItems.remove(item)) {
            return true;
        }
        
        for (Item currentItem : storedItems) {
            if (currentItem instanceof Container contentsContainer) {
                if (contentsContainer.removeItem(item)) {
                    return true;
                }
            }
        }
            
            return false;
        }

    // Finds item by name without removing it from ItemCollection
    // Used by look or examine; returns empty list if not found
    

    public List<Item> findItemsByName(String itemName) {
        List<Item> searchResults = new ArrayList<>();

        for (int i = 0; i < storedItems.size(); i++) {
            Item currentItem = storedItems.get(i);

            if (currentItem.getName().equals(itemName)) {
                searchResults.add(currentItem);
            }

            if (currentItem instanceof Container contentsContainer) {
                List<Item> containerMatches = contentsContainer.findItemsByName(itemName);
                searchResults.addAll(containerMatches);

            }
        }
        return searchResults;
    }

    // Create a list of Item Ids for SaveManager
    public List<String> getItemIds() {
        List<String> itemIds = new ArrayList<>();

        for (Item item : storedItems) {
            itemIds.add(item.getId());
        }
        return itemIds;
    }
    
    public String describeVisibleItems () {
        StringBuilder description = new StringBuilder();
        
        description.append(listItemNames());
        
        for (Item storedItem : storedItems) {
            if (storedItem instanceof Container container && container.isOpen()) {
                description.append("\nInside the " + container.getName() + ": " + container.describeVisibleContents());
                
            }
        }
        return description.toString();
        
        
        
    
    }

    public void clear() {
        storedItems.clear();
    }
}
