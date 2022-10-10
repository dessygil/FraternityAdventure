package adventure;

import java.util.ArrayList;
import java.util.HashMap;

public class Room implements java.io.Serializable {
    private static final long serialVersionUID = 74553494459074680L;

    private Long id;
    private String start;
    private String name;
    private String shortDescription;
    private String longDescription;
    private HashMap<String, Long> entrance;
    private ArrayList<Item> itemList;
    private ArrayList<Room> roomListInRoom;

    /**
     * zero param
     */
    public Room() {

        this.id = null;
        this.start = null;
        this.name = null;
        this.shortDescription = null;
        this.longDescription = null;
        this.entrance = null;
        this.itemList = null;
        this.roomListInRoom = null;
    }

    /**
     * 
     * @param incomingId
     * @param incomingStart
     * @param incomingName
     * @param incomingShortDescription
     * @param incomingLongDescription
     * @param incomingEntrance
     * @param incomingLoot
     */
    public Room(Long incomingId, String incomingStart, String incomingName, String incomingShortDescription,
            String incomingLongDescription, HashMap<String, Long> incomingEntrance, ArrayList<Item> incomingLoot) {

        this.id = incomingId;
        this.start = incomingStart;
        this.name = incomingName;
        this.shortDescription = incomingShortDescription;
        this.longDescription = incomingLongDescription;
        this.entrance = incomingEntrance;
        this.itemList = incomingLoot;
        this.roomListInRoom = null;
    }

    /**
     * gets connected room
     * 
     * @param direction
     * @return room
     * @throws InvalidCommandException
     * @throws NullPointerException
     */
    public Room getConnectedRoom(String direction) throws InvalidCommandException, NullPointerException {

        if (direction == null) {
            throw (new NullPointerException("Error - The direction entered was null. Please re-enter you input."));
        } else if (checkValidDirection(direction)) {
            for (int i = 0; i < roomListInRoom.size(); i++) {
                if (entrance.get(direction) == roomListInRoom.get(i).getId()) {
                    return roomListInRoom.get(i);
                }
            }
        }

        return null;
    }

    /**
     * makes sure a valid direction is reached
     * 
     * @param direction
     * @return boolean
     * @throws InvalidCommandException
     */
    public boolean checkValidDirection(String direction) throws InvalidCommandException {
        
        if (direction.equals("") || !entrance.containsKey(direction)) {
            throw new InvalidCommandException("Error - An invalid direction was entered. Please re-enter your input.");
        }
        return true;
    }

    /**
     * removes an item and makes sure that it did
     * 
     * @param itemIndex
     * @return boolean
     */
    public boolean removeItemRoom(int itemIndex) {
        Item tempItem = itemList.get(itemIndex);

        itemList.remove(itemIndex);

        if (!itemList.contains(tempItem)) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @return string
     */
    public String getName() {
        return this.name;
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
     * @return string
     */
    public String getLongDescription() {
        return this.longDescription;
    }

    /**
     * 
     * @param incomingLongDescription
     */
    public void setLongDescription(String incomingLongDescription) {
        this.longDescription = incomingLongDescription;
    }

    /**
     * 
     * @return string
     */
    public String getShortDescription() {
        return this.shortDescription;
    }

    /**
     * 
     * @param incomingShortDescription
     */
    public void setShortDescription(String incomingShortDescription) {
        this.shortDescription = incomingShortDescription;
    }

    /**
     * 
     * @return long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 
     * @param incomingId
     */
    public void setId(Long incomingId) {
        this.id = incomingId;
    }

    /**
     * 
     * @return string
     */
    public String getStart() {
        return this.start;
    }

    /**
     * 
     * @param incomingStart
     */
    public void setStart(String incomingStart) {
        this.start = incomingStart;
    }

    /**
     * 
     * @return hashmap
     */
    public HashMap<String, Long> getEntranceMap() {
        return this.entrance;
    }

    /**
     * 
     * @param incomingEntranceMap
     */
    public void setEntranceMap(HashMap<String, Long> incomingEntranceMap) {
        this.entrance = incomingEntranceMap;
    }

    /**
     * 
     * @return list of items
     */
    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    /**
     * 
     * @param incomingItemList
     */
    public void setItemList(ArrayList<Item> incomingItemList) {
        this.itemList = incomingItemList;
    }

    /**
     * 
     * @return list of rooms
     */
    public ArrayList<Room> getRoomList() {
        return this.roomListInRoom;
    }

    /**
     * 
     * @param incomingRoomListInRoom
     */
    public void setRoomListInRoom(ArrayList<Room> incomingRoomListInRoom) {
        roomListInRoom = incomingRoomListInRoom;
    }
}
