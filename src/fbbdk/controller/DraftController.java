/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.controller;

import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_PLAYER_MESSAGE;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_TEAM_MESSAGE;
import fbbdk.gui.AddPlayerDialog;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.BaseballTeam;
import fbbdk.data.Draft;
import fbbdk.data.DraftDataManager;
import fbbdk.data.Pick;
import fbbdk.gui.EditPlayerDialog;
import static fbbdk.gui.EditPlayerDialog.FREE_AGENT;
import fbbdk.gui.Fdk_gui;
import fbbdk.gui.HomeScreen;
import fbbdk.gui.MessageDialog;
import fbbdk.gui.TeamDialog;
import fbbdk.gui.YesNoCancelDialog;
import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class DraftController {

    TeamDialog teamDialog;
    AddPlayerDialog addPlayerDialog;
    EditPlayerDialog editPlayerDialog;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;

    Draft draft;

    public DraftController(Stage initPrimaryStage, Draft draft, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {

        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;

        this.draft = draft;

        teamDialog = new TeamDialog(initPrimaryStage, messageDialog);
        addPlayerDialog = new AddPlayerDialog(initPrimaryStage, draft, messageDialog);
        editPlayerDialog = new EditPlayerDialog(initPrimaryStage, draft, messageDialog);

    }

    // THESE ARE FOR SCHEDULE ITEMS
    public void handleAddTeamRequest(Fdk_gui gui, HomeScreen screen) {
        DraftDataManager ddm = gui.getDataManager();
        teamDialog.showAddTeamDialog();

        // DID THE USER CONFIRM?
        if (teamDialog.wasCompleteSelected()) {
            // GET THE SCHEDULE ITEM
            BaseballTeam team = teamDialog.getTeam();

            // AND ADD IT AS A ROW TO THE TABLE
            ddm.getDraft().addTeam(team);
            //update the gui
            screen.updateScreen(team);
            gui.getDataManager().getDraft().calculateEstimatedValue();
            //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }

    public void handleEditTeamRequest(Fdk_gui gui, BaseballTeam teamToEdit, HomeScreen screen) {
        DraftDataManager ddm = gui.getDataManager();

        teamDialog.showEditTeamDialog(teamToEdit);

        // DID THE USER CONFIRM?
        if (teamDialog.wasCompleteSelected()) {
            //UPDATE THE TEAM
            BaseballTeam team = teamDialog.getTeam();
            teamToEdit.setTeamName(team.getTeamName());
            teamToEdit.setCoach(team.getCoach());

            //update gui
            draft.getTeams().set(draft.getTeams().indexOf(teamToEdit), team);
            draft.getObservableTeams().set(draft.getTeams().indexOf(teamToEdit), team);

            //now we sort the teams
            Collections.sort(draft.getTeams());

            screen.updateScreen(team);

            //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }

    public void handleRemoveTeamRequest(Fdk_gui gui, BaseballTeam teamToRemove, HomeScreen screen) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_TEAM_MESSAGE));

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            gui.getDataManager().getDraft().removeTeam(teamToRemove);

            //update gui
            screen.updateScreen(null);

            //calculate the estimated value
            gui.getDataManager().getDraft().calculateEstimatedValue();

            //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        }

    }

    /**
     * adding a player is simply means adding a NEW player to the
     * availablePlayers list see editPlayer to add a player to a team
     *
     * @param gui
     */
    public void handleAddPlayerRequest(Fdk_gui gui) {
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        addPlayerDialog.showAddPlayerDialog();

        // DID THE USER CONFIRM?
        if (addPlayerDialog.wasCompleteSelected()) {
            // GET THE PLAYER
            BaseballPlayer player = addPlayerDialog.getPlayer();

            // AND ADD IT AS A ROW TO THE TABLE
            draft.addPlayer(player);

            //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }

    public void handleEditPlayerRequest(Fdk_gui gui, BaseballPlayer playerToEdit) {
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        editPlayerDialog.showEditPlayerDialog(playerToEdit);
        if (playerToEdit == null) {
            return;
        }
        // DID THE USER CONFIRM?
        if (editPlayerDialog.wasCompleteSelected()) {
            // UPDATE THE SCHEDULE ITEM
            BaseballPlayer player = editPlayerDialog.getPlayer();
            playerToEdit.setPosition(player.getPosition());
            playerToEdit.setContract(player.getContract());
            playerToEdit.setSalary(player.getSalary());

            //update gui
            ArrayList<BaseballTeam> teams = draft.getTeams();
            BaseballTeam playersTeam = editPlayerDialog.getPlayerTeam();

            //first list check if the team is the free agents
            if (playersTeam.getTeamName().equals(FREE_AGENT)) {
                //first we can check if the player is already in the pool
                if (!draft.getAvailablePlayers().contains(playerToEdit)) {
                    //we need to find out what team the player is on
                    //we need to remove him
                    //them add him to the free agents pool
                    //we will do this in one step
                    draft.addPlayer(
                            searchAndRemovePlayerFromTeam(teams, player, ddm));
                }
            } //ok hes not going to the free agents so we need to add him to a team
            else {
                //LETS CHECK THAT THE DRAFT ISNT ENDED
                if (draft.playerDraftEnded()) {
                //ok we are just going to add the player to the team that
                    //is selected
                    playersTeam.addTaxiPlayer(player);
                    //now lets check the player wasnt on another team
                    for (int x = 0; x < teams.size(); x++) {
                        if (teams.get(x).getPlayers().contains(player)) {
                            teams.get(x).removePlayer(player);
                        }
                    }
                    draft.removePlayer(player);
                    return;
                }
                for (int x = 0; x < teams.size(); x++) {
                    if (teams.get(x).equals(playersTeam)) {
                        teams.get(x).addPlayer(player);
                        //add the new pick
                        Pick pick = new Pick(ddm.getDraft().getPickOrder().size() + 1,
                                player.getFirstName(), player.getLastName(), teams.get(x).getTeamName(),
                                player.getPosition(), player.getContract(),
                                player.getSalary(), player.getEstimatedValue());
                        ddm.getDraft().addPick(pick);
                        //make sure to update totalpoints
                        draft.calculateTotalPoints();
                        draft.getObservableTeams().clear();
                        draft.getObservableTeams().setAll(teams);
                        draft.removePlayer(player);

                    } else if (teams.get(x).getPlayers().contains(player)) {
                        teams.get(x).removePlayer(player);

                        ddm.getDraft().removePick(new Pick(ddm.getDraft().getPickOrder().size() + 1,
                                player.getFirstName(), player.getLastName(), teams.get(x).getTeamName(),
                                player.getPosition(), player.getContract(), player.getSalary(), player.getEstimatedValue()));

                        //make sure to update totalpoints
                        draft.calculateTotalPoints();
                        draft.getObservableTeams().clear();
                        draft.getObservableTeams().setAll(teams);

                    }

                }
            }
            //AFTER THE PLAYER IS ADDED WE NOW NEED TO REDUE THE ESTIMATED VALUE
            draft.calculateEstimatedValue();
            gui.getPlayerScreen().updateTable();
            gui.getDraftScreen().updateTable();
            //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        } else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }

    public void handleRemovePlayerRequest(Fdk_gui gui, BaseballPlayer playerToRemove) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_PLAYER_MESSAGE));

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            gui.getDataManager().getDraft().removePlayer(playerToRemove);
            //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        }
    }

    private BaseballPlayer searchAndRemovePlayerFromTeam(ArrayList<BaseballTeam> teams, BaseballPlayer player, DraftDataManager ddm) {
        //ill use these guy
        BaseballPlayer playerIterator;
        BaseballTeam teamIterator;
        //so first we need to check if theyre on a team other than free agents
        //so for each team
        for (int x = 0; x < teams.size(); x++) {
            //we need to look through the teams roster
            teamIterator = teams.get(x);
            //first we look at the players list
            for (int y = 0; y < teamIterator.getPlayers().size(); y++) {
                playerIterator = teamIterator.getPlayers().get(y);
                if (playerIterator.equals(player)) {
                    //ok we found him we need to remove him
                    teams.get(x).removePlayer(player);
                    ddm.getDraft().removePick(new Pick(ddm.getDraft().getPickOrder().size() + 1,
                            playerIterator.getFirstName(), playerIterator.getLastName(), teamIterator.getTeamName(),
                            player.getPosition(), playerIterator.getContract(), playerIterator.getSalary(),
                            playerIterator.getEstimatedValue()));
                    //make sure to update totalpoints
                    draft.calculateTotalPoints();
                    draft.getObservableTeams().clear();
                    draft.getObservableTeams().setAll(teams);
                }
            }
            //now we need to check the taxiPlayers
            for (int y = 0; y < teamIterator.getTaxiPlayers().size(); y++) {
                playerIterator = teamIterator.getTaxiPlayers().get(y);
                if (playerIterator.equals(player)) {
                    //same thing we found him and we need to remove him
                    teams.get(x).removeTaxiPlayer(player);
                    ddm.getDraft().removePick(new Pick(ddm.getDraft().getPickOrder().size() + 1,
                            playerIterator.getFirstName(), playerIterator.getLastName(), teams.get(x).getTeamName(),
                            player.getPosition(), playerIterator.getContract(), playerIterator.getSalary(),
                            playerIterator.getEstimatedValue()));
                    draft.getObservableTeams().clear();
                    draft.getObservableTeams().setAll(teams);

                }
            }

        }
        return player;
    }

}
