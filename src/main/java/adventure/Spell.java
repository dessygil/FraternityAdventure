package adventure;

public class Spell extends Item implements Readable {
    
    /**
     * 
     * @param incomingId
     * @param incomingName
     * @param incomingDesc
     * @param incomingContainingRoom
     * @param incomingTypeOfItem
     */
    public Spell(Long incomingId, String incomingName, String incomingDesc, 
                Room incomingContainingRoom, String incomingTypeOfItem){
        super(incomingId, incomingName, incomingDesc, incomingContainingRoom, incomingTypeOfItem);
    }
    
    /**
     * @return String
     */
    public String read() {
        return "You have just read " + " to cast a spell";
    }

}
