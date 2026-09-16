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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class CommandHandler {

    private final GameState gameState;

    private final ItemCommandHandler itemCommandHandler;
    private final GamePersistence gamePersistence;

    public CommandHandler(GameState gameState, ItemCommandHandler itemCommandHandler, GamePersistence gamePersistence) {
        this.gameState = gameState;

        this.itemCommandHandler = itemCommandHandler;
        this.gamePersistence = gamePersistence;
    }

    /**
     * Separates the player's command into an action and optional target, then
     * runs the matching game command.
     *
     * The first word is treated as the action. The last word is treated as the
     * target when one is present.
     */
    public void handleCommand(Command command) {
        String action = command.action();
        String target = command.target();

        switch (action) { // calls for player input
            case "take", "get" -> {
                if (target == null) {
                    System.out.println("What do you want to take?");
                } else {
                    itemCommandHandler.takeItem(target);
                }
            }
            case "drop" -> {
                if (target == null) {
                    System.out.println("What do you want to drop?");
                } else {
                    itemCommandHandler.dropItem(target);
                }
            }

            case "open" -> {
                itemCommandHandler.openItem(target);
            }

            case "close" -> {
                itemCommandHandler.closeItem(target);
            }
            case "east", "e" ->
                move("east");
            case "west", "w" ->
                move("west");
            case "north", "n" ->
                move("north");
            case "south", "s" ->
                move("south");
            case "look", "l" -> {
                if (target == null) {
                    showCurrentRoom();
                } else {
                    itemCommandHandler.examineItem(target);
                }
            }

            case "inventory", "i" -> {
                System.out.println("You are carrying:");
                System.out.println(gameState.getPlayer().describeInventory());
            }
            case "examine" -> {
                if (target == null) {
                    System.out.println("What do you want to examine?");
                } else {
                    itemCommandHandler.examineItem(target);
                }
            }
            case "save" ->
                gamePersistence.saveGame();
            case "load" ->
                gamePersistence.loadGame();
            case "help", "?" ->
                showHelp();
            case "quit", "q" -> {
                System.out.println("Thanks for Playing! Goodbye.");
                gameState.stopGame();
            }
            default -> {
                System.out.println("I don't understand that command.");
                System.out.println("Try help or ?");
            }
        }
    }

    public void handleStructuredCommand(ParsedCommand command) {
        String action = command.action();
        String target = command.target();
        String connector = command.connector();
        String secondTarget = command.secondTarget();

        switch (action) {
            case "put", "place" -> {
                handlePutCommand(target, connector, secondTarget);
            }
        }
    }

    private void handlePutCommand(String target, String connector, String secondTarget) {
        if (target == null) {
            System.out.println("What are you trying to put or place?");
            return;
        }
        
        if (connector == null) {
            System.out.println("What are you trying to do with it?");
            return;
        }

        if (!connector.equals("in") && !connector.equals("into")) {
            System.out.println("Invalid connector.");
            return;
        }

        if (secondTarget == null) {
            System.out.println("What are you trying to put or place into?");
            return;
        }
        
        System.out.println("Ready to place " + target + " " + connector + " " + secondTarget + ".");
        itemCommandHandler.putItem(target, secondTarget);

    }

    public void showCurrentRoom() { // room description
        System.out.println(gameState.getCurrentRoom().describe());
    }

    // move player through exit or null
    public void move(String direction) {
        Room nextRoom = gameState.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("You can't go that way.");
            return;
        }

        // player changes currentRoom 
        gameState.setCurrentRoom(nextRoom);

        System.out.println("You go " + direction + ".");
        System.out.println();
        showCurrentRoom();
    }

    private void showHelp() { // display help to player
        System.out.println("Scratch Adventure Help");
        System.out.println("Known commands:");
        System.out.println("North, South, East, West (n, s, e, w)");
        System.out.println("Look (l), Quit (q), Help (?)");
    }
}
