/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 * 
 * Item class for room or player inventory
 * returns name, description or ID
 */
public class Item {
    private final String name;
    private final String description;
    private final String id;
    private final boolean takeable;
    
    public Item(String name, String description, String id, boolean takeable) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.takeable = takeable;
    }
    
    public boolean isTakeable() {
        return takeable;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    // item ID for saved game state
    public String getId() {
        return id;
    }
    
}
