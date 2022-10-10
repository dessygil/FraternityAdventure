package adventure;

public class Clothing extends Item implements Wearable{

    /**
     * 
     * @param incomingId
     * @param incomingName
     * @param incomingDesc
     * @param incomingContainingRoom
     * @param incomingTypeOfItem
     */
    public Clothing(Long incomingId, String incomingName, String incomingDesc, 
                    Room incomingContainingRoom, String incomingTypeOfItem){
        super(incomingId, incomingName, incomingDesc, incomingContainingRoom, incomingTypeOfItem);
    }

    /**
     * @return String
     */
    public String wear(){
        return "You are now wearing " ;
    }

}
