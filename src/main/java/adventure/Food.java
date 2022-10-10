package adventure;

public class Food extends Item implements Edible {
    /**
     * 
     * @param incomingId
     * @param incomingName
     * @param incomingDesc
     * @param incomingContainingRoom
     * @param incomingTypeOfItem
     */
    public Food(Long incomingId, String incomingName, String incomingDesc, 
                Room incomingContainingRoom, String incomingTypeOfItem){
        super(incomingId, incomingName, incomingDesc, incomingContainingRoom, incomingTypeOfItem);
    }
    /**
     * @return String
     */
    public String eat(){
        return "You have ate ";
    }

}
