package adventure;

import java.util.ArrayList;

public class Player implements java.io.Serializable {
    private static final long serialVersionUID = -4951464925185087582L;

    private String name;
    private ArrayList<Item> itemsInInventory;
    private Room currentRoom;

    /**
     * zero param
     */
    public Player() {
        this.name = "default";
        this.itemsInInventory = new ArrayList<>();
        this.currentRoom = null;
    }

    /**
     * normal constructor
     * 
     * @param incomingName
     */
    public Player(String incomingName) {
        this.name = incomingName;
        this.itemsInInventory = new ArrayList<>();
        this.currentRoom = null;
    }

    /**
     * 
     * @param itemName
     * @return String
     */
    public String tossItem(String itemName) {
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (itemsInInventory.get(i).getName().equals(itemName)) {
                ArrayList<Item> tempItemList = currentRoom.getItemList();
                tempItemList.add(itemsInInventory.get(i));
                currentRoom.setItemList(tempItemList);
                itemsInInventory.remove(i);
            }
        }
        return itemName + " was tossed into the current room";
    }

    /**
     * 
     * @param itemName
     * @return String
     */
    public String eatItem(String itemName) {
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (itemsInInventory.get(i).getName().equals(itemName)) {
                itemsInInventory.remove(i);
            }
        }
        return "You have eaten" + itemName;
    }

    /**
     * 
     * @param itemName
     * @return String
     */
    public String wearItem(String itemName) {
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (itemsInInventory.get(i).getName().equals(itemName)) {
                itemsInInventory.get(i).setCurrentlyWearing(true);
            }
        }
        return "You are now wearing " + itemName;
    }

    /**
     * 
     * @param itemName
     * @param action
     * @return boolean
     * @throws InvalidCommandException
     */
    public boolean checkValidItemInventoryAndCommand(String itemName, String action) throws InvalidCommandException {
        boolean itemExists = false;
        boolean correctAction = false;
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (itemsInInventory.get(i).getName().equals(itemName)) {
                itemExists = true;
                if (itemsInInventory.get(i).getTypeOfItem().contains(action)) {
                    correctAction = true;
                }
            }
        }
        if (!itemExists) {
            throw new InvalidCommandException("Error - An invalid item was entered. Please re-enter your input.");
        }
        if (!correctAction) {
            throw new InvalidCommandException("Error- An invalid action for the specific item was entered.");
        }
        return itemExists;
    }

    /**
     * @return string
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @return ArrayList<Item>
     */
    public ArrayList<Item> getInventory() {
        return this.itemsInInventory;
    }

    /**
     * 
     * @param incomingName
     */
    public void setName(String incomingName) {
        this.name = incomingName;
    }

    /**
     * 
     * @return array list of items
     */
    public ArrayList<Item> getItemsInInventory() {
        return this.itemsInInventory;
    }

    /**
     * 
     * @param incomingItemsInInventory
     */
    public void setItemsInInventory(ArrayList<Item> incomingItemsInInventory) {
        this.itemsInInventory = incomingItemsInInventory;
    }

    /**
     * @return room
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * 
     * @param incomingCurrentRoom
     */
    public void setCurrentRoom(Room incomingCurrentRoom) {
        this.currentRoom = incomingCurrentRoom;
    }

    /**
     * 
     * @return String
     */
    public String getSaveGameName() {
        return null;
    }

    /**
     * 
     * @param itemToAdd
     */
    public void addItem(Item itemToAdd) {
        itemsInInventory.add(itemToAdd);
    }

    /**
     * prints entire item list
     * @return String
     */
    public String printItemList() {
        String toReturn = "";
        
        if (itemsInInventory != null && itemsInInventory.size() > 0) {
            for (Item toPrint : itemsInInventory) {
                if (toPrint.getCurrentlyWearing()) {
                    toReturn = toReturn + "wearing: ";
                }
                toReturn = toReturn + toPrint.getName();
            }
        } else {
            toReturn = toReturn + "The inventory is empty";
        }
        return toReturn;
    }
    
}
