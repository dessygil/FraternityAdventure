package adventure;

public class BrandedClothing extends Clothing implements Readable {
    
    /**
     * 
     * @param incomingId
     * @param incomingName
     * @param incomingDesc
     * @param incomingContainingRoom
     * @param incomingTypeOfItem
     */
    public BrandedClothing(Long incomingId, String incomingName, 
                            String incomingDesc, Room incomingContainingRoom, String incomingTypeOfItem){
        super(incomingId, incomingName, incomingDesc, incomingContainingRoom, incomingTypeOfItem);
    }
    
    /**
     * @return String
     */
    public String read() {
        return "You have read ";
    }
}
