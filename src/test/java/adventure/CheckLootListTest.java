package adventure;

import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;

public class CheckLootListTest {
    private JsonTesting jsonTesting;
    Long itemToSearchID;
    Long itemIDRoomOne;
    Long itemIDRoomTwo;
    Long itemIDNotInList;
    Item itemOne;
    Item itemTwo;
    ArrayList<Item> itemList;



    @Before
    public void setup() {
        jsonTesting = new JsonTesting();
        itemToSearchID = Long.valueOf(1);
        itemIDNotInList = Long.valueOf(46);
        itemIDRoomOne = Long.valueOf(2);
        itemIDRoomTwo = Long.valueOf(1);
        itemOne = new Item();
        itemTwo = new Item();
        itemOne.setId(itemIDRoomOne);
        itemTwo.setId(itemIDRoomTwo);
        itemList = new ArrayList<>();
        itemList.add(itemOne);
        itemList.add(itemTwo);
    }


    @Test(expected = InvalidJSONFileInput.class)
    public void checkInvalidInList() throws InvalidJSONFileInput {
        jsonTesting.checkLootList(itemList, itemIDNotInList);
    }
}