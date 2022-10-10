package adventure;

public class SmallFood extends Food implements Tossable {

    /**
     * 
     * @param incomingId
     * @param incomingName
     * @param incomingDesc
     * @param incomingContainingRoom
     * @param incomingTypeOfItem
     */
    public SmallFood(Long incomingId, String incomingName, String incomingDesc, 
                      Room incomingContainingRoom, String incomingTypeOfItem){
        super(incomingId, incomingName, incomingDesc, incomingContainingRoom, incomingTypeOfItem);
    }

    /**
     * @return String
     */
    public String toss(){
        return "You have tossed " ; 
    }
}
