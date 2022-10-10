package adventure;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

public class RoomTest {
    private Room room1, room2;

    @Before
    public void setup() {
        ArrayList<Room> roomList = new ArrayList<>();
        HashMap<String, Long> room1Map = new HashMap<>();
        HashMap<String, Long> room2Map = new HashMap<>();
        room1 = new Room();
        room2 = new Room();

        Long first = Long.valueOf(100);
        Long second = Long.valueOf(101);
        // set up room id
        room1.setName("room1Name");
        room1.setId(first);
        room1Map.put("S", second);
        room1.setEntranceMap(room1Map);
        roomList.add(room1);

        room2.setName("room2Name");
        room2.setId(second);
        room2Map.put("N", first);
        room2.setEntranceMap(room2Map);
        roomList.add(room2);

        // set up room lists
        room1.setRoomListInRoom(roomList);
        room2.setRoomListInRoom(roomList);
    }

    @Test
    public void testSetNameWithValidInput() {
        System.out.println("Testing setName with valid name");
        String roomName = "one";
        room1.setName(roomName);
        assertTrue(room1.getName().equals(roomName));
    }

    @Test
    public void testGetCurrentRoomWithValidInputInEntranceMap() throws InvalidCommandException, NullPointerException {
        System.out.println("Testing getCurrentRoom with valid input in entrance list");
        Room tempRoom = room1.getConnectedRoom("S");
        assertTrue(tempRoom.getName().equals(room2.getName()));
    }

    @Test(expected = InvalidCommandException.class)
    public void testGetCurrentRoomWithValidInputNotInEntranceMap()
            throws InvalidCommandException, NullPointerException {
        System.out.println("Testing getCurrentRoom with valid input not in entrance list");
        room1.getConnectedRoom("W");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGetCurrentRoomWithInvalidInput() throws InvalidCommandException, NullPointerException {
        System.out.println("Testing getCurrentRoom with invalid input");
        room1.getConnectedRoom("this is a test");
    }

    @Test(expected = InvalidCommandException.class)
    public void testGetCurrentRoomWithValidAndInvalidInputTogether()
            throws InvalidCommandException, NullPointerException {
        System.out.println("Testing getCurrentRoom with a mixture of valid and invalid input mixed together");
        room1.getConnectedRoom("S is a test");
    }

    @Test(expected = NullPointerException.class)
    public void testGetCurrentRoomWithNull() throws InvalidCommandException, NullPointerException {
        System.out.println("Testing getCurrentRoom with null");
        room1.getConnectedRoom(null);
    }

    @Test(expected = InvalidCommandException.class)
    public void testGetCurrentRoomWithEmptyString() throws InvalidCommandException, NullPointerException {
        System.out.println("Testing getCurrentRoom with an empty string");
        room1.getConnectedRoom("");
    }

}