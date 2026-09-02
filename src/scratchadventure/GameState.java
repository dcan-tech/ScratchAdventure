/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */



public class GameState {
    private final World world;
    private final Player player;
    private Room currentRoom;
    private boolean isGameRunning = true;
    
    public GameState(World world, Player player) {
        this.world = world;
        this.player = player;
        this.currentRoom = world.getStartingRoom();
        
    }
    
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    public World getWorld() {
        return world;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public boolean isGameRunning() {
        return isGameRunning;
    }
    
    public void stopGame() {
        isGameRunning = false;
    }
}
