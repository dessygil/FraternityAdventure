package adventure;

import java.util.HashMap;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;

public class CheckValidExitIDTest {

    private JsonTesting jsonTesting;
    private Room room1, room2;
    ArrayList<Room> roomsList;

    @Before
    public void setup() {
        jsonTesting = new JsonTesting();
        roomsList = new ArrayList<>();
        HashMap<String, Long> room1Map = new HashMap<>();
        HashMap<String, Long> room2Map = new HashMap<>();
        room1 = new Room();
        room2 = new Room();

        Long first = Long.valueOf(100);
        Long second = Long.valueOf(101);
        Long third = Long.valueOf(102);
        // set up room id
        room1.setName("room1Name");
        room1.setId(first);
        room1Map.put("S", second);
        room1.setEntranceMap(room1Map);
        roomsList.add(room1);

        room2.setName("room2Name");
        room2.setId(second);
        room2Map.put("N", third);
        room2.setEntranceMap(room2Map);
        roomsList.add(room2);

        // set up room lists
        room1.setRoomListInRoom(roomsList);
        room2.setRoomListInRoom(roomsList);
    }

    @Test(expected = InvalidJSONFileInput.class)
    public void testInvalidID () throws InvalidJSONFileInput {
        jsonTesting.checkValidExitId(roomsList);
    }

}