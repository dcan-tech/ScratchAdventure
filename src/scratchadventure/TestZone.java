/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */

// Temporary main for testing purposes
public class TestZone {
    public static void main(String[] args) {
    StructuredCommandParser test = new StructuredCommandParser();
    test.parse("put rusty axe in old wooden chest");
    test.parse("look");
    test.parse("take axe");
    test.parse("take rusty axe");
    
    System.out.println("Second round of testing:\n");
    
    test.parse("put axe into chest");
    test.parse("put axe in");
    test.parse("put in chest");
    test.parse("put axe chest");
}

}


    
