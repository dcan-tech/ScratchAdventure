/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dylan Canfield
 */




public class ItemCommandHandler {
    
    private final GameState gameState;
    
    public ItemCommandHandler(GameState gameState) {
        this.gameState = gameState;
    }
    
    
     // remove item from Room and add to player inventory
    public void takeItem(String itemName) {
        List<Item> searchResults = gameState.getCurrentRoom().findItemsByName(itemName);

        switch (searchResults.size()) {

            case 0 -> {
                System.out.println("There is no " + itemName + " here!");
            }

            case 1 -> {
                Item foundItem = searchResults.get(0);
                if (!foundItem.isTakeable()) {
                    System.out.println("You can't pick up the " + foundItem.getName() + "!");
                } else {
                    boolean removed = gameState.getCurrentRoom().removeItem(foundItem);
                    if (removed) {
                        gameState.getPlayer().addItem(foundItem);
                        System.out.println("You picked up the " + foundItem.getName() + "!");

                    }

                }
            }

            default -> {
                System.out.println("I see more than one item called " + itemName
                        + ". Please be more specific.");

            }

        }
    }
    
    // remove item from player inventory and add to Room
    public void dropItem(String itemName) {
        List<Item> searchResults = gameState.getPlayer().findItemsByName(itemName);

        switch (searchResults.size()) {
            case 0 -> {
                System.out.println("You aren't carrying the " + itemName + ".");
            }
            case 1 -> {
                Item foundItem = searchResults.get(0);
                boolean dropped = gameState.getPlayer().removeItem(foundItem);
                if (dropped) {
                    gameState.getCurrentRoom().addItem(foundItem);
                    System.out.println("You dropped the " + foundItem.getName() + ".");

                }

            }
            default -> {
                System.out.println("I have more than one item called " + itemName
                        + ". Please be more specific.");
            }

        }
    }
    
    // find item and display descripton or null
    public void examineItem(String itemName) {
        List<Item> searchResults = new ArrayList<>();

        searchResults.addAll(gameState.getPlayer().findItemsByName(itemName));
        searchResults.addAll(gameState.getCurrentRoom().findItemsByName(itemName));

        switch (searchResults.size()) {
            case 0 -> {
                System.out.println("I don't see anything like that here.");
            }

            case 1 -> {
                Item foundItem = searchResults.get(0);
                System.out.println(foundItem.getDescription());
            }

            default -> {
                System.out.println("I see more than one item called " + itemName
                        + ". Please be more specific.");
            }

        }

    }
    
    public void openItem(String itemName) {
        List<Item> searchResults = new ArrayList<>();
        
        searchResults.addAll(gameState.getPlayer().findItemsByName(itemName));
        searchResults.addAll(gameState.getCurrentRoom().findItemsByName(itemName));
        
        switch (searchResults.size()) {
            case 0 -> {
                System.out.println("I don't see anything like that to open.");
            }
            
            case 1 -> {
                Item foundItem = searchResults.get(0);
                
                if (foundItem instanceof Container container) {
                    boolean opened = container.open();
                    
                    if (opened) {
                        System.out.println("You open the " + container.getName() + ".");
                        System.out.println(container.getDescription());
                    } else {
                        System.out.println("The " + container.getName() + " is already open.");
                    } 
                } else {
                    System.out.println("You can't open the " + foundItem.getName() + ".");
                            
                            }
                    
                    }
            
            default -> {
                 System.out.println("I see more than one item called " + itemName
                        + ". Please be more specific.");
                
            }
        }
        }
    
    public void closeItem(String itemName) {
        List<Item> searchResults = new ArrayList<>();
        
        searchResults.addAll(gameState.getPlayer().findItemsByName(itemName));
        searchResults.addAll(gameState.getCurrentRoom().findItemsByName(itemName));
        
        switch (searchResults.size()) {
            case 0 -> {
                System.out.println("I don't see anything like that to close.");
            }
            
            case 1 -> {
                Item foundItem = searchResults.get(0);
                
                if (foundItem instanceof Container container) {
                    boolean opened = container.close();
                    
                    if (opened) {
                        System.out.println("You close the " + container.getName() + ".");
                        
                    } else {
                        System.out.println("The " + container.getName() + " is already closed.");
                    } 
                } else {
                    System.out.println("You can't close the " + foundItem.getName() + ".");
                            
                            }
                    
                    }
            
            default -> {
                 System.out.println("I see more than one item called " + itemName
                        + ". Please be more specific.");
                
            }
    }
    }
        
        
        
    }


