/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Arrays;
import java.util.LinkedHashMap;

// Read and write game save data
public class SaveManager {

    // Location of save file
    private static final Path SAVE_FILE = Path.of("savegame.txt");

    // Write the current game state to the save file
    public boolean save(SaveData saveData) {

        // Hold save data as String key/value pairs
        Properties properties = new Properties();

        // Save current room ID
        properties.setProperty(
                "currentRoomId",
                saveData.getCurrentRoomId()
        );

        // Convert player item IDs into one String
        String playerItems = String.join(
                ",",
                saveData.getPlayerItemIds()
        );

        // Save player item IDs
        properties.setProperty("playerItemIds", playerItems);

        // Retrieve room IDs/item IDs
        Map<String, List<String>> roomItemIds
                = saveData.getRoomItemIds();

        // Retrieve container IDs/container state (items, open state)
        Map<String, ContainerSaveData> containerData
                = saveData.getContainerData();

        // Create one property for each room
        for (Map.Entry<String, List<String>> entry
                : roomItemIds.entrySet()) {

            // Current room ID
            String roomId = entry.getKey();

            // Item IDs in the current room
            List<String> itemIds = entry.getValue();

            // Create combined key (ie room.troll_room)
            String roomKey = "room." + roomId;

            // Convert item IDs into one String
            String roomItems = String.join(",", itemIds);

            // Save room item IDs
            properties.setProperty(roomKey, roomItems);
        }

        for (Map.Entry<String, ContainerSaveData> entry : containerData.entrySet()) {

            String containerId = entry.getKey();
            ContainerSaveData containerState = entry.getValue();

            String containerKey = "container." + containerId;
            String containerItems = String.join(",", containerState.itemIds());

            properties.setProperty(containerKey + ".items", containerItems);
            properties.setProperty(containerKey + ".open", Boolean.toString(containerState.open()));

        }

        // Open and automatically close the file writer
        try (BufferedWriter writer
                = Files.newBufferedWriter(SAVE_FILE)) {

            // Write properties to the save file
            properties.store(writer, "Scratch Adventure save file");

            System.out.println(
                    "File path: " + SAVE_FILE.toAbsolutePath()
            );

            return true; // Save succeeded

        } catch (IOException exception) {

            System.out.println(
                    "Save failed: " + exception.getMessage()
            );

            return false; // Save failed
        }
    }

    // Load room ID or return null
    // This method must be replaced for the new save format
    public SaveData load() {
        Properties properties = new Properties();

        try (BufferedReader reader = Files.newBufferedReader(SAVE_FILE)) {
            properties.load(reader);
            String currentRoomId = properties.getProperty("currentRoomId");
            String playerItemIdsText = properties.getProperty("playerItemIds");
            
            List<String> playerItemIds = parseItemIds(playerItemIdsText);
            Map<String, List<String>> roomItemIds = new LinkedHashMap<>();
            Map<String, ContainerSaveData> containerData = new LinkedHashMap<>();
            
            for (String propertyName : properties.stringPropertyNames()) {
                if (propertyName.startsWith("room.")) {
                    String roomId = propertyName.substring("room.".length());
                    String roomItemIdsText = properties.getProperty(propertyName);
                    
                    List<String> itemIds = parseItemIds(roomItemIdsText);
                    roomItemIds.put(roomId, itemIds);
                    
                }
                if (propertyName.startsWith("container.") && 
                        propertyName.endsWith(".items")) {
                    String containerId = propertyName.substring(
                            "container.".length(),
                            propertyName.length() - ".items".length());
                    
                    String containerItemIdsText = properties.getProperty(propertyName);
                    
                    List<String> itemIds = parseItemIds(containerItemIdsText);
                    String openText = properties.getProperty("container." + containerId + ".open");
                    boolean open = Boolean.parseBoolean(openText);
                    ContainerSaveData containerState = new ContainerSaveData(itemIds, open);
                    
                    
                    containerData.put(containerId, containerState);
                }
            }

            return new SaveData(currentRoomId, playerItemIds, roomItemIds, containerData);

        } catch (IOException exception) {
            System.out.println(
                    "Load failed: " + exception.getMessage()
            );

            return null;
        }
    }
    
    private List<String> parseItemIds(String itemIdsText) {
        if (itemIdsText ==null || itemIdsText.isBlank()) {
            return List.of();
        }
        String[] itemIdArray = itemIdsText.split(",");
        
        return Arrays.asList(itemIdArray);
        
    }
}
