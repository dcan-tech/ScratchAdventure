/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */
public class StructuredCommandParser {

    public ParsedCommand parse(String input) {
        String normalizedInput = input.trim().toLowerCase();
        if (normalizedInput.isBlank()) {
            return new ParsedCommand("", null, null, null);
        }
        String[] words = normalizedInput.split("\\s+");

        String action = words[0];
        String target = null;
        String connector = null;
        String secondTarget = null;
        int connectorIndex = -1;

        // If "in" or "into" exists capture index integer
        for (int index = 1; index < words.length; index++) {
            if (words[index].equals("in") || words[index].equals("into")) {
                connectorIndex = index;
                break;
            }
        }
        
        // "in" or "into" does not exist from input
        if (connectorIndex == -1) {
            if (words.length > 1) {
                StringBuilder targetBuilder = new StringBuilder();
                for (int index = 1; index < words.length; index++) {
                    if (index > 1) {
                        targetBuilder.append(" ");
                    }
                    targetBuilder.append(words[index]);
                }

                target = targetBuilder.toString();
                
            }

        } else { // "in" or "into" was found
            connector = words[connectorIndex];
            StringBuilder targetBuilder = new StringBuilder();

            for (int index = 1; index < connectorIndex; index++) {
                if (index > 1) {
                    targetBuilder.append(" ");
                }
                targetBuilder.append(words[index]);
            }

            target = targetBuilder.toString();
            if (target.isBlank()) {
                target = null;
            }

            StringBuilder secondTargetBuilder = new StringBuilder();

            for (int index = connectorIndex + 1; index < words.length; index++) {
                if (index > connectorIndex + 1) {
                    secondTargetBuilder.append(" ");
                }
                secondTargetBuilder.append(words[index]);
            }

            secondTarget = secondTargetBuilder.toString();
            if (secondTarget.isBlank()) {
                secondTarget = null;
            }

        }

        System.out.println("\nStructuredCommandParser output :"
                + "\nAction: " + action
                + "\nTarget: " + target
                + "\nConnector: " + connector
                + "\nSecond Target: " + secondTarget);

        return new ParsedCommand(action, target, connector, secondTarget);

    }

}
