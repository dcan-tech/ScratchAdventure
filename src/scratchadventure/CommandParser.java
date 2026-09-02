/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */



public class CommandParser {
    public Command parse(String input) {
        String normalizedInput = input.trim().toLowerCase();
        if (normalizedInput.isBlank()) {
            return new Command("", null);
        }
        
        String[] words = normalizedInput.split(" ", 2);
        String target = null;
        String action = words[0];

        if (words.length >= 2) { // input > 2 possible targer
            target = normalizeTarget(words[1]);
        }
        return new Command(action, target);
    }
    
    private String normalizeTarget(String target){
        if (target.startsWith("the ")) {
            return target.substring(4);
        }
        
        return target;
    }
}
