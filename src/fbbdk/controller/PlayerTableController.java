/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.controller;

import fbbdk.data.BaseballPlayer;
import fbbdk.data.Draft;
import fbbdk.data.DraftDataManager;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public class PlayerTableController {

    //i need this to get to the data
    DraftDataManager dataManager;
    //and this is going to display the data
    ObservableList<BaseballPlayer> playerList;
    //this is for the search
    ArrayList<String> positionsSelected;
    String searchText;

    public PlayerTableController(DraftDataManager dataManager) {
        this.dataManager = dataManager;
        playerList = dataManager.getDraft().getObservablePlayers();
        positionsSelected = new ArrayList<String>();
        searchText = "";
    }

    public void handleSearchTextRequest(String text) {

        //first thing we need to set the searchText
        searchText = text.toLowerCase();

        //going to need the list of all players
        ArrayList<BaseballPlayer> allPlayers = dataManager.getDraft().getAvailablePlayers();

        //and we need to clear the playerList
        playerList.clear();

        //now we check if the text is empty
        if (text.equals("")) {
            //check the positions list
            if (positionsSelected.isEmpty()) {
                //ok we can add everything
                playerList.addAll(allPlayers);
                //we can go home
                return;
            }
        }
        //we are going to need to add only the players
        //with the correct position -- this will also add players with right searchText
        handleRadioSearch(positionsSelected);
    }

    public void handleAllSearch() {
        //first we clear the positionsSelected
        positionsSelected.clear();
        
        //now we make sure there is no search text
        if (!searchText.equals("")) {
            handleSearchTextRequest(searchText);
            //handleSearchTextRequest will do the work for us
            //go home
            return;
        }
        //first clear the playerList
        playerList.clear();
        //now we add all the players
        ArrayList<BaseballPlayer> allPlayers = dataManager.getDraft().getAvailablePlayers();

        playerList.addAll(allPlayers);

    }

    public void handleRadioSearch(ArrayList<String> positions) {
        //first thing we do is set the PositionsSelected list
        positionsSelected = positions;
        //to do this we are going to need the playersList
        ArrayList<BaseballPlayer> allPlayers = dataManager.getDraft().getAvailablePlayers();

        //now we make sure positions isnt empty
        if (positions.isEmpty()) {
            //just add everything
            playerList.addAll(allPlayers);
            
            //ok now we check searchText
            BaseballPlayer player;
            if (!searchText.equals("")) {
                for (int x = 0; x < playerList.size(); x++) {
                    player = playerList.get(x);
                    
                    if ((player.getFirstName().contains(searchText) || player.getLastName().contains(searchText))) {
                        playerList.remove(x);
                        x--;
                    }
                }
            }
        } else {
            //now we wipe the playerList
            playerList.clear();

            //then we are going to add the players with said positions to the player list
            //and we are going to need a player to reference
            BaseballPlayer player;
            for (int x = 0; x < allPlayers.size(); x++) {
                player = allPlayers.get(x);
                //now we need to loop through the positions array and check each player
                for (int y = 0; y < positions.size(); y++) {
                    //check each position
                    if (player.getPositions().contains(positions.get(y))) {
                        //we found a player so add the player to playerList
                        playerList.add(player);
                    }//end position if
                }//end positions for
            }//end big for
            //ok now we check searchText
            if (!searchText.equals("")) {
                for (int x = 0; x < playerList.size(); x++) {
                    player = playerList.get(x);
                    if (!(player.getFirstName().startsWith(searchText) || player.getLastName().startsWith(searchText))) {
                        playerList.remove(player);
                    }
                }
            }
        }
    }

}
