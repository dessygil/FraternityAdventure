package adventure;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// JSON stuff
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

public class Game {

    private ArrayList<String> stringArgs;
    private Adventure adventureObj;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Game theGame = new Game();

            ArrayList<String> stringListToAdd = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                stringListToAdd.add(args[i]);
            }
            theGame.setStringArgs(stringListToAdd);
            System.out.println(theGame.printWelcomeMessage());
            boolean isValidFile = false;
            while (!isValidFile) {
                try {
                    theGame.decideToStartOrLoad(theGame);
                    isValidFile = true;
                } catch (InvalidJSONFileInput xcpt) {
                    System.out.println(xcpt);
                } catch (IOException xcpt) {
                    System.out.println(xcpt);
                } catch (ParseException xcpt) {
                    System.out.println(xcpt);
                } catch (NullPointerException xcpt) {
                    System.out.println(xcpt);
                }
                if (!isValidFile) {
                    System.out.print("Please enter another file name.\n>");
                    String newFileName = reader.readLine();
                    theGame.setStringArgs(theGame.changeFileNameString(newFileName, theGame.getStringArgs()));
                }
            }
            System.out.print("Please enter the players name: \n>");
            theGame.getAdventureObj().getPlayer().setName(reader.readLine());

            System.out.print("\n" + theGame.printRoom(theGame.getAdventureObj().getCurrentRoom()));

            while (true) {
                System.out.print("\n>");
                Parser gameParser = new Parser();
                try {
                    Command gameCommand = gameParser.parseUserCommand(reader.readLine());
                    if (gameCommand.getActionWord().equals("quit")) {
                        System.out.println("Would you like to save your progress (yes or no)?");
                        if (reader.readLine().equals("yes")) {
                            saveGame(theGame.getAdventureObj());
                        }
                        System.out.println("You have exited the game");
                        break;
                    }
                    System.out.println(theGame.runGame(theGame, gameCommand));

                } catch (InvalidCommandException xcpt) {
                    System.out.println(xcpt);
                } catch (NullPointerException xcpt) {
                    System.out.println(xcpt);
                }
            }
            reader.close();
        } catch (IOException xcpt) {
            return;
        }

    }

    /**
     * 
     * @return Adventure
     */
    public Adventure getAdventureObj() {
        return this.adventureObj;
    }

    /**
     * 
     * @param incomingAdventureObj
     */
    public void setAdventureObj(Adventure incomingAdventureObj) {
        this.adventureObj = incomingAdventureObj;
    }

    /**
     * 
     * @param newFileName
     * @param oldArrayList
     * @return ArrayList<String>
     */
    public ArrayList<String> changeFileNameString(String newFileName, ArrayList<String> oldArrayList) {
        ArrayList<String> newStringArray = new ArrayList<>();
        newStringArray.add(oldArrayList.get(0));
        newStringArray.add(newFileName);
        return newStringArray;
    }

    /**
     * 
     * @param theGame
     * @throws InvalidJSONFileInput
     * @throws IOException
     * @throws ParseException
     * @throws NullPointerException
     */
    public void decideToStartOrLoad(Game theGame)
            throws InvalidJSONFileInput, IOException, ParseException, NullPointerException {
        JSONObject jsonObj;

        if (theGame.getStringArgs().get(0).equals("-a")) {

            jsonObj = decideWhichGameToLoad(theGame);
            theGame.setAdventureObj(theGame.generateAdventure(jsonObj));
            theGame.getAdventureObj().setRoomListInRoomClass();
            theGame.getAdventureObj().setPlayer(new Player());
            theGame.getAdventureObj().setFirstCurrentRoom();

        } else if (theGame.getStringArgs().get(0).equals("-l")) {
            theGame.setAdventureObj(helperToLoad(theGame));
        }
    }

    /**
     * 
     * @param theGame
     * @return JSONObject
     * @throws InvalidJSONFileInput
     * @throws IOException
     * @throws ParseException
     * @throws NullPointerException
     */
    public static JSONObject decideWhichGameToLoad(Game theGame)
            throws InvalidJSONFileInput, IOException, ParseException, NullPointerException {
        JSONObject jsonObj;
        InputStream inputStream;
        if (theGame.getStringArgs().size() == 2) {
            inputStream = Game.class.getClassLoader().getResourceAsStream(theGame.getStringArgs().get(1));
        } else {
            inputStream = Game.class.getClassLoader().getResourceAsStream("desmondsadventure.json");
        }
        jsonObj = theGame.loadAdventureJson(inputStream);
        return jsonObj;
    }

    /**
     * 
     * @return the list of args
     */
    public ArrayList<String> getStringArgs() {
        return this.stringArgs;
    }

    /**
     * 
     * @param incomingStringArgs
     */
    public void setStringArgs(ArrayList<String> incomingStringArgs) {
        this.stringArgs = incomingStringArgs;
    }

    // THIS IS NO LONGER USED AS IT IS TAKEN FROM THE CMND LINE PARAMETERS
    /**
     * 
     * @param filename
     * @return a json object using filereader
     */
    public JSONObject loadAdventureJson(String filename) {
        JSONObject jsonObject = null;
        try (Reader reader = new FileReader(filename)) {
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(reader);
        } catch (IOException e) {
            System.out.println("ERROR: The File choosen could not be opened");
        } catch (ParseException e) {
            System.out.println("ERROR: The File choosen could not be opened");
        }
        return jsonObject;
    }

    /**
     * 
     * @param inputStream
     * @return JSON object from input stream
     * @throws IOException
     * @throws ParseException
     */
    public JSONObject loadAdventureJson(InputStream inputStream)
            throws IOException, ParseException, NullPointerException {

        JSONObject jsonObject;
        try {
            jsonObject = setUpJsonObj(inputStream);
        } catch (IOException xcpt) {
            throw (new IOException("Fatal Error:" + xcpt));
        } catch (ParseException xcpt) {
            throw (new ParseException(1));
        }

        return jsonObject;
    }

    /**
     * 
     * @param inputStream
     * @return jsonobj
     * @throws NullPointerException
     * @throws ParseException
     * @throws IOException
     */
    public JSONObject setUpJsonObj(InputStream inputStream) throws NullPointerException, 
                                                            ParseException, IOException {
        JSONObject jsonObject;
        JSONParser jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(inputStream));
        if (jsonObject == null){
            throw new NullPointerException("Error - There is an error when entering the file into the stream");
        }
        return jsonObject;
    }

    /**
     * 
     * @param savedGameName
     * @return a save adv file
     */
    public static Adventure loadGame(String savedGameName) {

        Adventure gotItBack = null;

        // Deserialization
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(savedGameName));) {

            // Method for deserialization of object
            gotItBack = (Adventure) in.readObject();
            System.out.println("Object has been deserialized ");
        } catch (IOException ex) {
            System.out.println("IOException is caught " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught " + ex);
        }
        return gotItBack;
    }

    /**
     * saves
     * 
     * @param adventureObj
     */
    public static void saveGame(Adventure adventureObj) {
        try {
            FileOutputStream outPutStream = new FileOutputStream(adventureObj.getPlayer().getName());
            ObjectOutputStream outPutDest = new ObjectOutputStream(outPutStream);
            outPutDest.writeObject(adventureObj);
            outPutDest.close();
            outPutStream.close();
            System.out.println("Object has been serialized");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    // THE GENERATE ADVENTURE SERIES OF MAIN METHOD AND HELPER
    // MEHTODS///////////////

    /**
     * 
     * @param incomingItems
     * @return ArrayList<Item>
     */
    public static ArrayList<Item> makeItemList(JSONArray incomingItems) {
        Iterator itr1 = incomingItems.iterator();
        ArrayList<Item> itemsList = new ArrayList<Item>();

        while (itr1.hasNext()) {
            JSONObject temp = (JSONObject) itr1.next();

            Long id = (Long) temp.get("id");
            String name = (String) temp.get("name");
            String desc = (String) temp.get("desc");
            Boolean wearable = (Boolean) temp.get("wearable");
            Boolean readable = (Boolean) temp.get("readable");
            Boolean tossable = (Boolean) temp.get("tossable");
            Boolean edible = (Boolean) temp.get("edible");

            if (wearable != null && readable != null && wearable && readable) {
                BrandedClothing tempItem = new BrandedClothing(id, name, desc, null, "wearable readable");
                itemsList.add(tempItem);
            } else if (edible != null & tossable != null && edible && tossable) {
                SmallFood tempItem = new SmallFood(id, name, desc, null, "edible tossable");
                itemsList.add(tempItem);
            } else if (wearable != null && wearable) {
                Clothing tempItem = new Clothing(id, name, desc, null, "wearable");
                itemsList.add(tempItem);
            } else if (edible != null && edible) {
                Food tempItem = new Food(id, name, desc, null, "edible");
                itemsList.add(tempItem);
            } else if (readable != null && readable) {
                Spell tempItem = new Spell(id, name, desc, null, "readable");
                itemsList.add(tempItem);
            } else if (tossable != null && tossable) {
                Weapon tempItem = new Weapon(id, name, desc, null, "tossable");
                itemsList.add(tempItem);
            }
        }

        return itemsList;
    }

    /**
     * 
     * @param incomingRooms
     * @param itemsList
     * @return ArrayList<Room>
     * @throws InvalidJSONFileInput
     */
    public static ArrayList<Room> makeRoomsList(JSONArray incomingRooms, ArrayList<Item> itemsList)
            throws InvalidJSONFileInput {
        ArrayList<Room> roomsList = new ArrayList<Room>();
        Iterator itr2 = incomingRooms.iterator();
        JsonTesting jsonTesting = new JsonTesting();

        while (itr2.hasNext()) {

            HashMap<String, Long> entranceMap;
            ArrayList<Item> itemListForSpecificRoom;
            JSONObject temp2 = (JSONObject) itr2.next();

            Long id = (Long) temp2.get("id");
            String start = (String) temp2.get("start");
            String name = (String) temp2.get("name");
            String shortDescription = (String) temp2.get("short_description");
            String longDescription = (String) temp2.get("long_description");

            // get entrances array
            JSONArray jsonEntranceArray = (JSONArray) temp2.get("entrance");
            jsonTesting.checkNotDeadEnd(jsonEntranceArray);
            entranceMap = makeEntranceMap(jsonEntranceArray);

            // get loot item list
            JSONArray jsonLootArray = (JSONArray) temp2.get("loot");
            itemListForSpecificRoom = makeLootList(jsonLootArray, itemsList);

            Room tempRoom = new Room(id, start, name, shortDescription, longDescription, entranceMap,
                    itemListForSpecificRoom);
            roomsList.add(tempRoom);
        }

        return roomsList;
    }

    /**
     * 
     * @param jsonLootArray
     * @param itemsList
     * @return ArrayList<Room>
     * @throws InvalidJSONFileInput
     */
    public static ArrayList<Item> makeLootList(JSONArray jsonLootArray, ArrayList<Item> itemsList)
            throws InvalidJSONFileInput {
        ArrayList<Item> itemListForSpecificRoom = new ArrayList<>();
        JsonTesting jsonTesting = new JsonTesting();
        if (jsonLootArray != null) {
            Iterator itrLoot = jsonLootArray.iterator();
            while (itrLoot.hasNext()) {
                JSONObject jsonLootObj = (JSONObject) itrLoot.next();
                Long tempLongForItemSearch = (Long) jsonLootObj.get("id");
                jsonTesting.checkLootList(itemsList, tempLongForItemSearch);
                for (int i = 0; i < itemsList.size(); i++) {
                    if (itemsList.get(i).getId() == tempLongForItemSearch) {
                        itemListForSpecificRoom.add(itemsList.get(i));
                    }
                }
            }
        }

        return itemListForSpecificRoom;
    }

    /**
     * 
     * @param jsonEntranceArray
     * @return HashMap<String,Long>
     * @throws InvalidJSONFileInput
     */
    public static HashMap<String, Long> makeEntranceMap(JSONArray jsonEntranceArray) throws InvalidJSONFileInput {
        Iterator itrEntrance = jsonEntranceArray.iterator();
        HashMap<String, Long> entranceMap = new HashMap<>();
        JsonTesting jsonTesting = new JsonTesting();
        while (itrEntrance.hasNext()) {
            JSONObject entranceTemp = (JSONObject) itrEntrance.next();

            Long entranceId = (Long) entranceTemp.get("id");
            String entranceDir = (String) entranceTemp.get("dir");
            jsonTesting.checkValidDirection(entranceDir);
            entranceMap.put(entranceDir, entranceId);
        }
        return entranceMap;
    }

    /**
     * 
     * @param obj
     * @return an adventure obj with everything in it
     */
    public Adventure generateAdventure(JSONObject obj) throws InvalidJSONFileInput {
        JsonTesting jsonTesting = new JsonTesting();

        // obtain everything inside the json file after adventure
        JSONObject parentObj = (JSONObject) obj;
        JSONObject jsonAdventureObj = (JSONObject) parentObj.get("adventure");

        // make json array of all rooms and all items
        JSONArray items = (JSONArray) jsonAdventureObj.get("item");
        JSONArray rooms = (JSONArray) jsonAdventureObj.get("room");

        // obtain items list
        ArrayList<Item> itemsList = makeItemList(items);
        // obtain rooms list
        ArrayList<Room> roomsList = makeRoomsList(rooms, itemsList);
        jsonTesting.checkValidExitId(roomsList);

        // setting adventure
        return new Adventure(roomsList, itemsList);

    }

    /**
     * 
     * @param theGame
     * @return Adventure
     */
    public static Adventure helperToLoad(Game theGame) {
        Adventure adventureObj = loadGame(theGame.getStringArgs().get(1));
        if (adventureObj == null) {
            throw new NullPointerException("Error - save adventure failed to load, please enter another file");
        }

        return adventureObj;
    }

    /**
     * looks at input and allows for interaction
     * 
     * @param theGame
     * @throws IOException
     * @return String
     * @param gameCommand
     */
    public String runGame(Game theGame, Command gameCommand) throws InvalidCommandException, NullPointerException {
        String stringReturnedFromCommand = "";

        if (gameCommand.getActionWord().equals("look")) {
            if (gameCommand.hasSecondWord()) {
                if (theGame.getAdventureObj().checkValidItem(gameCommand.getNoun())) {
                    stringReturnedFromCommand = printDescriptionOfItem(gameCommand.getNoun(),
                            theGame.getAdventureObj().getCurrentRoom());
                }
            } else {
                stringReturnedFromCommand = printLongDescription(theGame.getAdventureObj().getCurrentRoom());
            }
            // will look to take the item
        } else if (gameCommand.getActionWord().contains("take") && hasSecondCommand(gameCommand)) {

            if (theGame.getAdventureObj().checkValidItem(gameCommand.getNoun())) {
                int itemIndex = theGame.getAdventureObj().getItemIndex(gameCommand.getNoun());
                theGame.getAdventureObj()
                        .addItemToInventory(theGame.getAdventureObj().getCurrentRoom().getItemList().get(itemIndex));
                stringReturnedFromCommand = "you took "
                        + theGame.getAdventureObj().getCurrentRoom().getItemList().get(itemIndex).getName();
                theGame.getAdventureObj().removeItemAdventure(itemIndex);
            }

        } else if (gameCommand.getActionWord().contains("go") && hasSecondCommand(gameCommand)) {

            theGame.getAdventureObj().setCurrentRoom(gameCommand.getNoun());
            stringReturnedFromCommand = printRoom(theGame.getAdventureObj().getCurrentRoom());

        } else if (gameCommand.getActionWord().equals("inventory")) {
            stringReturnedFromCommand = theGame.getAdventureObj().getPlayer().printItemList();

        } else if (gameCommand.getActionWord().equals("help")) {
            Parser gameParser = new Parser();
            stringReturnedFromCommand = gameParser.allCommands();
        } else if (gameCommand.getActionWord().equals("toss")) {
            if (theGame.getAdventureObj().checkValidItemInventory(gameCommand.getNoun(), "tossable")) {
                stringReturnedFromCommand = theGame.getAdventureObj().tossItem(gameCommand.getNoun());
            }
        } else if (gameCommand.getActionWord().equals("eat")) {
            if (theGame.getAdventureObj().checkValidItemInventory(gameCommand.getNoun(), "edible")) {
                stringReturnedFromCommand = theGame.getAdventureObj().eatItem(gameCommand.getNoun());
            }
        } else if (gameCommand.getActionWord().equals("wear")) {
            if (theGame.getAdventureObj().checkValidItemInventory(gameCommand.getNoun(), "wearable")) {
                stringReturnedFromCommand = theGame.getAdventureObj().wearItem(gameCommand.getNoun());
            }
        } else if (gameCommand.getActionWord().equals("read")) {
            if (theGame.getAdventureObj().checkValidItemInventory(gameCommand.getNoun(), "readable")) {
                stringReturnedFromCommand = theGame.getAdventureObj().readItem(gameCommand.getNoun());
            }
        }
        return stringReturnedFromCommand;
    }

    /**
     * prints generic input static
     * 
     * @return String
     */
    public String printWelcomeMessage() {

        String toReturn = "\n\n\nWELCOME! You are about to embark on a grand adventure! which one is up to you, BUT \n"
                + "might I suggest attempting the default game mode. The rules are simple, you will simply type \n"
                + "with it's corresponding direction shown as CAPITAL letter. other commands are look to get\n"
                + "\"go\" a description of the room or look or take with the name of a specific item, or obtain it. \n"
                + "Inventory will show you the item you have. Help will list all commands and quit exits the \n"
                + "game with the option to save\n";
        return toReturn;
    }

    /**
     * static print out of the room
     * 
     * @return String
     * @param room
     */
    public String printRoom(Room room) {
        String toReturn = "You are at: " + room.getName() + "\n" + room.getShortDescription() + "\n";

        // print item list
        if (room.getItemList() != null) {
            toReturn = toReturn + "The following items are contained in the room: ";
            for (int i = 0; i < room.getItemList().size(); i++) {
                toReturn = toReturn + room.getItemList().get(i).getName() + " ";
            }
            toReturn = toReturn + "\n";
        }
        return toReturn;
    }

    /**
     * prints out the desc of the room
     * 
     * @return String
     * @param room
     */
    public static String printLongDescription(Room room) {
        return room.getLongDescription();
    }

    /**
     * prints a specific item
     * 
     * @return String
     * @param secondInput
     * @param room
     */
    public static String printDescriptionOfItem(String secondInput, Room room) {
        if (room.getItemList() != null) {
            for (int i = 0; i < room.getItemList().size(); i++) {
                if (room.getItemList().get(i).getName().equals(secondInput)) {
                    return room.getItemList().get(i).getDesc();
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param command
     * @return a bolean if the second cmd exists
     * @throws InvalidCommandException
     */
    public static boolean hasSecondCommand(Command command) throws InvalidCommandException {
        if (!command.hasSecondWord()) {
            throw new InvalidCommandException("Error - A second input wasn't entered");
        }
        return true;
    }
}
