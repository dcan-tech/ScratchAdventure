/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scratchadventure;

/**
 *
 * @author Dylan Canfield
 */

import java.util.ArrayList;
import java.util.List;


public class Container extends Item {
    private final ItemCollection contents = new ItemCollection( );
    private boolean isOpen;
    
    public Container(String name, String description, String id, boolean isOpen, boolean takeable) {
        super(name, description, id, takeable);
        this.isOpen = isOpen;
        
    }
    
    public void addItem(Item item) {
        contents.addItem(item);
    }
    
    @Override public String getDescription() {
        String baseDescription = super.getDescription();
        if (isOpen == false) {
            return baseDescription +
                    "\nThe " + getName() +
                            " is closed.";
        }
        String contentsDescription = contents.describeVisibleItems();
        return baseDescription + "\nInside you see: " + contentsDescription;
    }
    
    public List<Item> findItemsByName(String itemName) {
        
        if (isOpen == false) {
            return List.of();
        }
        return contents.findItemsByName(itemName);
    }
    
    public List<String> getItemIds() {
        return contents.getItemIds();
       
    }
    
    public boolean removeItem (Item item) {
        if (isOpen == false) {
            
            return false;
            
        }
        return contents.removeItem(item);
    }
    
   public boolean open() {
        if (isOpen) {
            return false;
        } 
        
        isOpen = true;
        return true;
        }
   
   public boolean close() {
       if (!isOpen) {
           return false;
       }
       
       isOpen = false;
       return true;
   }
   
   
   public boolean isOpen() {
       return isOpen;
   }
   
   public void setOpen(boolean setBoolean) {
       isOpen = setBoolean;
   }
   
   public String describeVisibleContents() {
       return contents.describeVisibleItems();
   }
   
   public void clearItems() {
        contents.clear();
    }
        
}

