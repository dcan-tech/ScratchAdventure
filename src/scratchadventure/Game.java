/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

import java.util.Scanner;

/**
 *
 * @author Dylan Canfield
 */
// runs the game, process player input
// coordinate World, Player, rooms, items and save system
public class Game {

    private final Scanner scanner = new Scanner(System.in); // player input
    private final CommandParser commandParser = new CommandParser();
    private final StructuredCommandParser structuredCommandParser = new StructuredCommandParser();
    private final GameState gameState;
    private final CommandHandler commandHandler;
    private final ItemCommandHandler itemCommandHandler;
    private final GamePersistence gamePersistence;

    // create new world and place player in starting room
    public Game() {
        Player player = new Player();
        World world = new WorldGenerator().generate();
        SaveManager saveManager = new SaveManager();

        gameState = new GameState(world, player);
        itemCommandHandler = new ItemCommandHandler(gameState);
        gamePersistence = new GamePersistence(gameState, saveManager);
        commandHandler = new CommandHandler(gameState, itemCommandHandler, gamePersistence);
    }

    public void start() { // start main game loop
        showIntro();
        runGameLoop();
    }

    private void runGameLoop() { // main loop until player quits
        while (gameState.isGameRunning()) {
            playOneTurn();
        }
    }

    private void showIntro() { // display into to player
        System.out.println("Welcome to Scratch Adventure.");
        System.out.println();
        commandHandler.showCurrentRoom();
    }

    private void playOneTurn() { // player input
        System.out.print("> ");
        String input = scanner.nextLine();
        Command command = commandParser.parse(input);
        ParsedCommand parsedCommand = structuredCommandParser.parse(input);

        if (parsedCommand.action().equals("put") || parsedCommand.action().equals("place")) {
            commandHandler.handleStructuredCommand(parsedCommand);
        } else {

            commandHandler.handleCommand(command);
        }
    }
}
