package adventure;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.simple.JSONArray;

public class JsonTesting {

    /**
     * 
     * @param roomsList
     * @throws InvalidJSONFileInput
     */
    public void checkValidExitId(ArrayList<Room> roomsList) throws InvalidJSONFileInput {
        for(int i = 0 ; i < roomsList.size() ; i ++ ) {
            Collection<Long> values = roomsList.get(i).getEntranceMap().values();
            for(Long longValue: values){
                boolean doesLongValueExist = false;
                for (int j = 0 ; j < roomsList.size() ; j ++ ){
                    if( longValue == roomsList.get(j).getId()){
                        doesLongValueExist = true;
                    }
                }
                if(!doesLongValueExist){
                    throw new InvalidJSONFileInput("Error - An exit contains a room that has an exit to a false room");
                }
            }
        } 
    }

    /**
     * 
     * @param entranceDir
     * @throws InvalidJSONFileInput
     */
    public void checkValidDirection(String entranceDir) throws InvalidJSONFileInput {
        String[] availableDirections = {"N", "S", "W", "E", "up", "down"};    
        if (entranceDir == null){
            throw new InvalidJSONFileInput("Error - There was a null direction that was found in the JSON file");
        }
        if(!Arrays.asList(availableDirections).contains(entranceDir)){
            throw new InvalidJSONFileInput("Error - There was an incorrect direction that was found in the JSON file");
        }
    }

    /**
     * 
     * @param itemsList
     * @param tempLongForItemSearch
     * @throws InvalidJSONFileInput
     */
    public void checkLootList(ArrayList<Item> itemsList, Long tempLongForItemSearch) throws InvalidJSONFileInput {
        boolean itemIsValid = false;
        for(int i = 0 ; i < itemsList.size() ; i ++ ){
            if (itemsList.get(i).getId() == tempLongForItemSearch){
                itemIsValid = true;
            }
        }
        if(!itemIsValid){
            throw new InvalidJSONFileInput("Error - An item in the room doesn't exist in the entire item list");
        }
    }

    /**
     * 
     * @param jsonEntranceArray
     * @throws InvalidJSONFileInput
     */
    public void checkNotDeadEnd(JSONArray jsonEntranceArray) throws InvalidJSONFileInput {
        if(jsonEntranceArray == null || jsonEntranceArray.size() == 0 ) {
            throw new InvalidJSONFileInput("Error - The JSON file contains a room with a deadend");
        }
    }
}
