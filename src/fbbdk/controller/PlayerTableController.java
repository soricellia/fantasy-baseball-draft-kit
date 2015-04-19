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
import java.util.StringTokenizer;
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
    String firstName;
    String lastName;

    public PlayerTableController(DraftDataManager dataManager) {
        this.dataManager = dataManager;
        playerList = dataManager.getDraft().getObservablePlayers();
        positionsSelected = new ArrayList<>();
        searchText = "";
        firstName = "";
        lastName = "";
    }

    public void handleSearchTextRequest(String text) {

        StringTokenizer firstAndLast = new StringTokenizer(text, " ");
        if(firstAndLast.countTokens() == 2){
            firstName = firstAndLast.nextToken();
            lastName = firstAndLast.nextToken();
            searchText = text;
            handleRadioSearch(positionsSelected,firstName,lastName);
            //ok we are done
            return;
        }
        //new to reset first and last name
        firstName = "";
        lastName = "";
        
        //need to set the searchText
        searchText = text.trim().toLowerCase();

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
        //error checking
        //if this occurs its because we are in the wrong handlesearch method
        if(!firstName.isEmpty()){
            //go here instead
            handleRadioSearch(positions,firstName,lastName);
            //and we are done
            return;
        }
        
        //first thing we do is set the PositionsSelected list
        positionsSelected = positions;
        
        //need the player list to search
        ArrayList<BaseballPlayer> allPlayers = dataManager.getDraft().getAvailablePlayers();

        //now we make sure positions isnt empty
        if (positions.isEmpty()) {
            //first clear the list
            playerList.clear();
            //just add everything
            if (searchText.isEmpty()) {
                playerList.addAll(allPlayers);
            }
            //ok now we search the playerList
            BaseballPlayer player;
            for (int x = 0; x < allPlayers.size(); x++) {
                player = allPlayers.get(x);
                if ((player.getFirstName().toLowerCase().startsWith(searchText)
                        || player.getLastName().toLowerCase().startsWith(searchText))) {
                    playerList.add(player);
                }
            }
        } else {
            //positionsSelected isnt empty

            //so lets clear the playerList and start from scratch
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
            }//end big positions for

            //ok now we check searchText
            //first we are going to create a new list
            ArrayList<BaseballPlayer> newList = new ArrayList<>();
            if (!searchText.isEmpty()) {
                for (int x = 0; x < playerList.size(); x++) {
                    player = playerList.get(x);
                    if ((player.getFirstName().toLowerCase().startsWith(searchText)
                            || player.getLastName().toLowerCase().startsWith(searchText))) {
                        newList.add(player);
                    }
                }
                //now we clear the playerList and add everything from the newList
                playerList.clear();
                playerList.addAll(newList);
            }
        }
    }

    private void handleRadioSearch(ArrayList<String> positions, String firstName, String lastName) {
        //first thing we do is set the PositionsSelected list
        positionsSelected = positions;

        //need the player list to search
        ArrayList<BaseballPlayer> allPlayers = dataManager.getDraft().getAvailablePlayers();

        //now we make sure positions isnt empty
        if (positions.isEmpty()) {
            //first clear the list
            playerList.clear();
            //just add everything
            if (searchText.isEmpty()) {
                playerList.addAll(allPlayers);
            }
            //ok now we search the playerList
            BaseballPlayer player;
            for (int x = 0; x < allPlayers.size(); x++) {
                player = allPlayers.get(x);
                if ((player.getFirstName().toLowerCase().startsWith(firstName)
                        && player.getLastName().toLowerCase().startsWith(lastName))) {
                    playerList.add(player);
                }
            }
        } else {
            //positionsSelected isnt empty

            //so lets clear the playerList and start from scratch
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
            }//end big positions for

            //ok now we check searchText
            //first we are going to create a new list
            ArrayList<BaseballPlayer> newList = new ArrayList<>();
            if (!searchText.isEmpty()) {
                for (int x = 0; x < playerList.size(); x++) {
                    player = playerList.get(x);
                    if ((player.getFirstName().toLowerCase().startsWith(firstName)
                            && player.getLastName().toLowerCase().startsWith(lastName))) {
                        newList.add(player);
                    }
                }
                //now we clear the playerList and add everything from the newList
                playerList.clear();
                playerList.addAll(newList);
            }
        }
    }
}
