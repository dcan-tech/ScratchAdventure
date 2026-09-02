/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dylan Canfield
 */
public class GamePersistence {
    
    private final GameState gameState;
    private final SaveManager saveManager;

    
    public GamePersistence (GameState gameState, SaveManager saveManager) {
    
    this.gameState = gameState;
    this.saveManager = saveManager;
    }

    
    
    
    /**
     * Collects the current game state and saves the player's location.
     *
     * Player and room item IDs are currently collected and displayed, but are
     * not yet written to the save file.
     */
    public void saveGame() {

        // Save the player current location as a stable room ID.
        String currentRoomId = gameState.getCurrentRoom().getId();

        // Collect the IDs of all items carried by the player.
        List<String> playerItemIds = gameState.getPlayer().getItemIds();

        // Map each room ID to the IDs of the items currently inside it.
        Map<String, List<String>> roomItemIds = new LinkedHashMap<>();
        
        Map<String, ContainerSaveData> containerData = new LinkedHashMap<>();

        

        for (Room room : gameState.getWorld().getRooms()) {
            roomItemIds.put(room.getId(), room.getItemIds());
        }
        
        for (Item item : gameState.getWorld().getItems()) {
            if (item instanceof Container container) {
                ContainerSaveData containerState = new ContainerSaveData(container.getItemIds(), container.isOpen());
                containerData.put(container.getId(), containerState);
        }
        }
            
        SaveData saveData = new SaveData(currentRoomId, playerItemIds, roomItemIds, containerData);
        
        // SaveManager currently writes only the current room ID.
        boolean saveSucceeded = saveManager.save(saveData);

        if (saveSucceeded) {
            System.out.println("Game saved.");
        } else {
            System.out.println("Save failed.");
        }
    }

    // load saved room ID and move player to it
    public void loadGame() {
        SaveData saveData = saveManager.load();

        if (saveData == null) {
            System.out.println("No saved game could be loaded.");

            return;
        }

        List<Item> loadedPlayerItems = new ArrayList<>();
        for (String itemId : saveData.getPlayerItemIds()) {
            Item loadedItem = gameState.getWorld().getItem(itemId);
            if (loadedItem == null) {
                System.out.println("No item found with ID: " + itemId);
                return;
            }
            loadedPlayerItems.add(loadedItem);
        }

        Map<Room, List<Item>> loadedRoomItems = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> entry : saveData.getRoomItemIds().entrySet()) {
            String roomId = entry.getKey();
            Room loadedRoom = gameState.getWorld().getRoom(roomId);
            if (loadedRoom == null) {
                System.out.println("No Room found with ID: " + roomId);
                return;
            }

            List<String> savedItemIds = entry.getValue();
            List<Item> loadedItemsForRoom = new ArrayList<>();

            for (String itemId : savedItemIds) {
                Item loadedItem = gameState.getWorld().getItem(itemId);
                if (loadedItem == null) {
                    System.out.println("No Item found with ID: " + itemId);
                    return;
                }
                loadedItemsForRoom.add(loadedItem);
            }

            loadedRoomItems.put(loadedRoom, loadedItemsForRoom);

        }
        
        Map<Container, ContainerSaveData> containerState = new LinkedHashMap<>();
        
        for (Map.Entry<String, ContainerSaveData> entry : saveData.getContainerData().entrySet()) {
            String containerId = entry.getKey();
            Item containerItem = gameState.getWorld().getItem(containerId);
            
            if (containerItem instanceof Container) {
                Container container = (Container) containerItem;
                ContainerSaveData savedState = entry.getValue();
                
                containerState.put(container, savedState);
                
            } else {
                System.out.println("No container found with ID: " + containerId);
                    return;
                
            }
            
            
        }

        // convert saved String ID into Room object
        String currentRoomId = saveData.getCurrentRoomId();
        Room loadedCurrentRoom = gameState.getWorld().getRoom(currentRoomId);

        if (loadedCurrentRoom == null) {
            System.out.println("World lookup failure.");
            System.out.println("No Room found with ID: " + currentRoomId);
            return;
        }

        gameState.getPlayer().clearItems();

        for (Room room : gameState.getWorld().getRooms()) {
            room.clearItems();
        }
        
        for (Container container : containerState.keySet()) {
            container.clearItems();
            
        }
        
        for (Map.Entry<Container, ContainerSaveData> entry : containerState.entrySet()) {
            Container container = entry.getKey();
            ContainerSaveData savedState = entry.getValue();
            List<String> savedItemIds = savedState.itemIds();
            
            for (String containerItemId : savedItemIds) {
                Item loadedItem = gameState.getWorld().getItem(containerItemId);
                
                if (loadedItem == null) {
                    System.out.println("No Item found with ID: " + containerItemId);
                    return;
                }
                
                container.addItem(loadedItem);
                container.setOpen(savedState.open());
                
            }
            
            
        }

        for (Item item : loadedPlayerItems) {
            gameState.getPlayer().addItem(item);
        }

        for (Map.Entry<Room, List<Item>> entry : loadedRoomItems.entrySet()) {
            Room room = entry.getKey();
            List<Item> items = entry.getValue();

            for (Item item : items) {
                room.addItem(item);
            }
        }

        gameState.setCurrentRoom(loadedCurrentRoom);
        System.out.println("Current room changed to: " + gameState.getCurrentRoom().getId());
        System.out.println("Game loaded.");
        System.out.println(gameState.getCurrentRoom().describe());
    }
    
}
