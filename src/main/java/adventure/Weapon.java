package adventure;

public class Weapon extends Item implements Tossable {
    
    /**
     * 
     * @param incomingId
     * @param incomingName
     * @param incomingDesc
     * @param incomingContainingRoom
     * @param incomingTypeOfItem
     */
    public Weapon(Long incomingId, String incomingName, String incomingDesc, 
                   Room incomingContainingRoom, String incomingTypeOfItem){
        super(incomingId, incomingName, incomingDesc, incomingContainingRoom, incomingTypeOfItem);
    }
    
    /**
     * @return String
     */
    public String toss() {
        return "You have tossed ";
    }

}
