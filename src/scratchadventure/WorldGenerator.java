/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */

// creates rooms, items and exits for new game
public class WorldGenerator {
    // build and return game world
    public World generate() { // Room/Item (name, description, ID)
        Room forest = new Room(
        "Forest",
        "A quiet forest with tall trees and soft moss underfoot.",
        "forest_room"
        );
        
        Room trollRoom = new Room(
                "Troll Room",
                "A dank and gloomy room with an unpleasant smell.",
                "troll_room"
        );
        
        Room cave = new Room(
                "Cave",
                "A dark cave, filled with shadows and damp air.",
                "cave_room"
        );
        
        Container chest = new Container(
                "chest",
                "An old wooden chest.",
                "chest_item",
                false, // can it be taken
                false// is it open
                
        );
        
        Item axe = new Item("axe", 
        "This was once a high quality axe, but it has gotten so dull it refuses to cut anything.",
        "axe_item",
        true // can it be taken
        );
        
        Item coin = new Item("coin",
        "This coin sparkles as it catches the light and appears to be quite old.",
        "coin_item",
        true // can it be taken
        );
        
        // Place items in starting rooms
        trollRoom.addItem(axe);
        chest.addItem(coin);
        cave.addItem(chest);
        
        // add exits to rooms
        trollRoom.addExit("east", forest);
        
        forest.addExit("west", trollRoom);
        forest.addExit("north", cave);
        
        
        cave.addExit("south", forest);
        
        // creats world and starting room
        World world = new World(trollRoom);
        Room[] rooms = {trollRoom, forest, cave};
        Item[] items = {axe, coin, chest};
        
        for (Item item : items) {
            world.addItem(item);
        }
        
        // register rooms with roomId
        for (Room room : rooms) {
            System.out.println("Registering room: " + room.getId());
            world.addRoom(room);
            
        }
        
        return world;
    
    }
}
