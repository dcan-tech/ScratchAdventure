/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Collection;

// stores the rooms for the game
public class World {
    private final Room startingRoom;
    // Maps each room id to room object
    private final Map<String, Room> rooms = new LinkedHashMap<>();
    private final Map<String, Item> items = new LinkedHashMap<>();
    
    public World (Room startingRoom) {
        this.startingRoom = startingRoom;
    }
    
    // adds room to map using ID as key
    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
    }
    
    public void addItem(Item item) {
        items.put(item.getId(), item);
    }
    
    // returns starting Room for new or saved game or null
    public Room getStartingRoom() {
        return this.startingRoom;
    }
    
    // finds room by ID or null
    public Room getRoom(String id) {
        return rooms.get(id);
    }
    
    // room collection
    public Collection<Room> getRooms() {
        return rooms.values();
    }
    
    // finds item by ID or null
    public Item getItem(String id) {
        return items.get(id);
    }
    
    public Collection<Item> getItems() {
        return items.values();
    }
    
}




