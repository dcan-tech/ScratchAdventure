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
import java.util.Map;

public class SaveData {

    private final String currentRoomId;
    private final List<String> playerItemIds;
    private final Map<String, List<String>> roomItemIds;
    private final Map<String, ContainerSaveData> containerData;
    

    public SaveData(String currentRoomId, List<String> playerItemIds, Map<String, List<String>> roomItemIds, Map<String, ContainerSaveData> containerData) {
        this.currentRoomId = currentRoomId;
        this.playerItemIds = playerItemIds;
        this.roomItemIds = roomItemIds;
        this.containerData = containerData;
        
    }
    
    public String getCurrentRoomId() {
        return currentRoomId;
    }
    
    public List<String> getPlayerItemIds() {
        return playerItemIds;
    }
    
    public Map<String, List<String>> getRoomItemIds() {
        return roomItemIds;
    }
    
    public Map<String, ContainerSaveData> getContainerData() {
        return containerData;
    }
    
    

}
