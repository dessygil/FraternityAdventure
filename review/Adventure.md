
# Adventure
|method sig|responsibility | instance vars used | other class methods called| objects used | lines of code|
|--|--|--|--|--|--|
|Adventure()|constructor|all|ArrayList|Items, Rooms|5|
|addItemToInventory()|send player an item to add|Player|addItem()|Item|3|
|wearItem()|send item to player for wearing|Player|wearItem|Item|3|
|tossItem()|send item to player to toss|Player|tossItem|item |3|
|eatItem()|send an item to player to eat|Player|eatItem|Item|3|
|readItem()|sends a string back|Player|NA|Item|3|
|setFirstCurrentRoom|gets the first room|rooms, Player, current room|setCurrentRoom, getStart|player, room|10|
|setCurrentRoom()|sets the current room in adventure and player|current room|setCurrentRoom, getConnectedRoom|room|4|
|getRooms()| get rooms|ArrayList, Rooms|NA|Rooms|3|
|getItems()| get items list|ArrayList, Item|NA|NA|3|
|getCurrentRoom()| returns the current room|currentRoom|NA|NA|3|
|setRooms()|set rooms list|ArrayList, Rooms|NA|Rooms|3|
|setItems()|sets item list|ArrayList, Items|NA|NA|3|
|getPlayer()|returns player|Player|NA|NA|3|
|setPlayer()|sets player|Player|NA|NA|3|
|listAllRooms()|returns Rooms list|ArrayList, Rooms|NA|NA|3|
|listAllItems()| returns items list|ArrayList, Item|NA|NA|3|
|checkValidItem()|makes sure the item is in the list|currentRoom, |getItemList, getName|Room|12|
|checkValidItemInventory()|send item to player to be checked|NA|checkValidItemInventoryAndCommand|NA|3|
|getItemIndex()|get the item index|currentRoom|getItemList, getName|NA|8|
|getCurrentRoomDescription()|currentRoom long description|currentRoom|getLongDescription|Room|3|
|removeItemAdventure|remove item from the current room|currentRoom|removeItemRoom|Room|6|
|setRoomListInRoomClass()|send room list to room class|ArrayList, Rooms|setRoomListInRoom|Room|5|

