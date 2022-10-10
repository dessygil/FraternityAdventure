
# Player
|method sig|responsibility | instance vars used | other class methods called| objects used | lines of code|
|--|--|--|--|--|--|
|Player|constructor|all|NA|ArrayList|5|
|tossItem()|tosses the item into a room|items in inventory, current room|getName, setItemList, getItemsList|Room Item|11|
|eatItem()|eats the item|itemsInInventory|getName|item|8|
|wearItem()|wears the item|itemsInInventory|getName, setCurrentlyWearing|Item|8|
|checkValidItemInventoryAndCommand()|errors checks string input|itemsInInventory|getName, getTypeOfItem|Item|17|
|getName()|gets name|name|NA|NA|3|
|getInventory()|returns inventory|inventory|NA|Item|3|
|setName()|sets name|name|NA|NA|3|
|getItemsInInventory()|returns items in inventory|itemsInInventory|NA|Item|3|
|setItemsInInventory()|sets items in inventory|itemsInInventory|NA|Item|3|
|getCurrentRoom()|gets current room|currentRoom|NA|Room|3|
|setCurrentRoom()|sets current room|currentRoom|NA|Room|3|
|getSaveName()|NA|NA|NA||3|
|addItem()|add an item|itemsInInventory|NA|Item|3|
|printItemList()|returns item list as a string|ItemsInInventory|NA|Item|15|