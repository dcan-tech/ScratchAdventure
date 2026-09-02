/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


/**
 *
 * @author Dylan Canfield
 */
public class Room {
    private final String name; // short display name
    private final String description; // room description
    private final String id; // identifier for World save/load
    // connects direction input to room destination
    private final Map<String, Room> exits = new LinkedHashMap<>();
    private final ItemCollection contents = new ItemCollection(); // room items
    
    // creates a room (name, description, id)
    public Room(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }
    
    // return RoomId
    public String getId() {
        return this.id;
    }
    
    // return Room content List
    public List<String> getItemIds() {
        return contents.getItemIds();
    }
    
    // room connection from direction
    public void addExit(String direction, Room destination) {
        exits.put(direction, destination);
    }
    
    // add item to room
    public void addItem(Item newItem) {
        contents.addItem(newItem);
    }
    
    // remove item from room or null
    public boolean removeItem(Item item) {
        return contents.removeItem(item);
    }
    
    // finds item without removal; ie 'examine'
    public List<Item> findItemsByName(String itemName) {
        return contents.findItemsByName(itemName);
    }
    
    public void clearItems() {
        contents.clear();
    }
    
    // return room connected to exit or null
    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    // room description template
    public String describe() {
        return "You are in the " + name + ".\n"
                + description + "\n"
                + "You see: " + contents.describeVisibleItems() + "\n"
                + "Obvious exits: " + describeExits();
    }
    
    // exit list
    private String describeExits() {
        if (exits.isEmpty()) {
            return "none";
        }
        return String.join(", ", exits.keySet());
    }
}
