package adventure;

public class Item implements java.io.Serializable {
    private static final long serialVersionUID = -8302342012025223657L;

    private Long id;
    private String name;
    private String desc;
    private Room containingRoom;
    private String typeOfItem;
    private boolean currentlyWearing;

    /**
     * zero param
     */
    public Item() {
        this.id = null;
        this.name = null;
        this.desc = null;
        this.containingRoom = null;
    }

    /**
     * ordinary constructor
     * 
     * @param incomingId
     * @param incomingName
     * @param incomingDesc
     * @param incomingContainingRoom
     * @param incomingTypeOfItem
     */
    public Item(Long incomingId, String incomingName, String incomingDesc, Room incomingContainingRoom,
            String incomingTypeOfItem) {
        this.id = incomingId;
        this.name = incomingName;
        this.desc = incomingDesc;
        this.containingRoom = incomingContainingRoom;
        this.typeOfItem = incomingTypeOfItem;
    }

    /**
     * 
     * @param incomingCurrentlyWearing
     */
    public void setCurrentlyWearing(boolean incomingCurrentlyWearing) {
        this.currentlyWearing = incomingCurrentlyWearing;
    }

    /**
     * 
     * @return boolean
     */
    public boolean getCurrentlyWearing(){
        return this.currentlyWearing;
    }

    /**
     * 
     * @return String
     */
    public String getTypeOfItem(){
        return this.typeOfItem;
    }

    /**
     * 
     * @return String
     */
    public String getLongDescription() {
        return this.desc;
    }

    /**
     * 
     * @return id
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
     * @return name
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
     * @return desc
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 
     * @param incomingDesc
     */
    public void setDesc(String incomingDesc) {
        this.desc = incomingDesc;
    }

    /**
     * 
     * @return room
     */
    public Room getContainingRoom() {
        return this.containingRoom;
    }

    /**
     * 
     * @param incomingContainingRoom
     */
    public void setContainingRoom(Room incomingContainingRoom) {
        this.containingRoom = incomingContainingRoom;
    }
}
