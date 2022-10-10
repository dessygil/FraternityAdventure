package adventure;

import java.util.ArrayList;

public class Adventure implements java.io.Serializable {
    private static final long serialVersionUID = -5336472569230063648L;

    private ArrayList<Room> rooms;
    private ArrayList<Item> items;
    private Room currentRoom;
    private Player player;

    /**
     * zero param constructor
     */
    public Adventure() {
        this.rooms = new ArrayList<>();
        this.items = new ArrayList<>();
        this.currentRoom = null;
    }

    /**
     * 
     * @param incomingRooms
     * @param incomingItems
     */
    public Adventure(ArrayList<Room> incomingRooms, ArrayList<Item> incomingItems) {
        this.rooms = incomingRooms;
        this.items = incomingItems;
        this.currentRoom = null;
    }

    /**
     * 
     * @param itemToAdd
     */
    public void addItemToInventory(Item itemToAdd) {
        player.addItem(itemToAdd);
    }

    /**
     * 
     * @param itemName
     * @return String
     */
    public String wearItem(String itemName) {
        return player.wearItem(itemName);
    }

    /**
     * 
     * @param itemName
     * @return String
     */
    public String tossItem(String itemName) {
        return player.tossItem(itemName);
    }

    /**
     * 
     * @param itemName
     * @return String
     */
    public String eatItem(String itemName) {
        return player.eatItem(itemName);
    }

    /**
     * 
     * @param itemName
     * @return String
     */
    public String readItem(String itemName) {
        return "You have read " + itemName;
    }

    /**
     * sets first room
     */
    public void setFirstCurrentRoom() {
        for (int i = 0; i < rooms.size(); i++) {
            if ((rooms.get(i).getStart()) != null) {
                if ((rooms.get(i).getStart()).equals("true")) {
                    this.currentRoom = rooms.get(i);
                    this.player.setCurrentRoom(rooms.get(i));
                }
            }
        }
    }

    /**
     * 
     * @param direction
     * @throws InvalidCommandException
     * @throws NullPointerException
     */
    public void setCurrentRoom(String direction) throws InvalidCommandException, NullPointerException {
        this.currentRoom = currentRoom.getConnectedRoom(direction);
        this.player.setCurrentRoom(this.currentRoom);
    }

    /**
     * @return room list
     */
    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    /**
     * 
     * @return item list
     */
    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * 
     * @return the current room
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * 
     * @param incomingRooms
     */
    public void setRooms(ArrayList<Room> incomingRooms) {
        this.rooms = incomingRooms;
    }

    /**
     * 
     * @param incomingItems
     */
    public void setItems(ArrayList<Item> incomingItems) {
        this.items = incomingItems;
    }

    /**
     * 
     * @return the player object
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * 
     * @param incomingPlayer
     */
    public void setPlayer(Player incomingPlayer) {
        this.player = incomingPlayer;
    }

    /**
     * 
     * @return ArrayList<Room>
     */
    public ArrayList<Room> listAllRooms() {
        return this.rooms;
    }

    /**
     * 
     * @return ArrayList<Item>
     */
    public ArrayList<Item> listAllItems() {

        return this.items;
    }

    /**
     * 
     * @param secondInput
     * @return a bolean if the input is valid
     * @throws InvalidCommandException
     */
    public boolean checkValidItem(String secondInput) throws InvalidCommandException {
        boolean itemExists = false;
        for (int i = 0; i < currentRoom.getItemList().size(); i++) {

            if (currentRoom.getItemList().get(i).getName().equals(secondInput)) {
                itemExists = true;
            }
        }
        if (!itemExists) {
            throw new InvalidCommandException("Error - and invalid item was entered. Please re-enter your input.");
        }
        return itemExists;
    }
    
    /**
     * 
     * @param itemName
     * @param action
     * @return boolean
     * @throws InvalidCommandException
     */
    public boolean checkValidItemInventory(String itemName, String action) throws InvalidCommandException {
        return player.checkValidItemInventoryAndCommand(itemName, action);
    }

    /**
     * 
     * @param secondInput
     * @return get the index of the item
     */
    public int getItemIndex(String secondInput) {

        for (int i = 0; i < currentRoom.getItemList().size(); i++) {

            if (currentRoom.getItemList().get(i).getName().equals(secondInput)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * @return a string of the current rooms desc
     */
    public String getCurrentRoomDescription() {
        return currentRoom.getLongDescription();
    }

    /**
     * 
     * @param itemIndex
     * @return a boolean if the item has been removed
     */
    public boolean removeItemAdventure(int itemIndex) {
        if (currentRoom.removeItemRoom(itemIndex)) {
            return true;
        }
        return false;
    }

    /**
     * Puts the room list in each room
     */
    public void setRoomListInRoomClass() {
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).setRoomListInRoom(rooms);
        }
    }

}
