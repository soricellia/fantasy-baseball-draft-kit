/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.controller;

import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_ITEM_MESSAGE;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.BaseballTeam;
import fbbdk.data.Draft;
import fbbdk.data.DraftDataManager;
import fbbdk.gui.Fdk_gui;
import fbbdk.gui.HomeScreen;
import fbbdk.gui.MessageDialog;
import fbbdk.gui.TeamDialog;
import fbbdk.gui.YesNoCancelDialog;
import java.util.Collections;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class TeamEditController {
     
    TeamDialog teamDialog;
    PlayerDialog playerDialog;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    public TeamEditController(Stage initPrimaryStage, Draft draft, MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        teamDialog = new TeamDialog(initPrimaryStage);
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
    }

    // THESE ARE FOR SCHEDULE ITEMS
    
    public void handleAddTeamRequest(Fdk_gui gui, HomeScreen screen) {
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        teamDialog.showAddTeamDialog();
        
        // DID THE USER CONFIRM?
        if (teamDialog.wasCompleteSelected()) {
            // GET THE SCHEDULE ITEM
            BaseballTeam team = teamDialog.getTeam();
            
            // AND ADD IT AS A ROW TO THE TABLE
            draft.getTeams().add(team);
            //update the gui
            screen.updateScreen(team);
            //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }
    
    public void handleEditTeamRequest(Fdk_gui gui, BaseballTeam teamToEdit
            ,HomeScreen screen) {
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        teamDialog.showEditTeamDialog(teamToEdit);
        
        // DID THE USER CONFIRM?
        if (teamDialog.wasCompleteSelected()) {
            // UPDATE THE SCHEDULE ITEM
            BaseballTeam team = teamDialog.getTeam();
            teamToEdit.setTeamName(team.getTeamName());
            teamToEdit.setCoach(team.getCoach());
            
            //update gui
            draft.getTeams().set(draft.getTeams().indexOf(teamToEdit), team);
            Collections.sort(draft.getTeams());
            
            screen.updateScreen(team);
            
            //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }        
    }
    
    public void handleRemoveTeamRequest(Fdk_gui gui, BaseballTeam teamToRemove
            ,HomeScreen screen) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) { 
            gui.getDataManager().getDraft().removeTeam(teamToRemove);
            
            //update gui
            screen.updateScreen(null);
            
             //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        }
        
    }
    public void handleAddPlayerRequest(Fdk_gui gui) {
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        playerDialog.showAddLectureDialog();
        
        // DID THE USER CONFIRM?
        if (playerDialog.wasCompleteSelected()) {
            // GET THE SCHEDULE ITEM
            BaseballPlayer player = playerDialog.getPlayer();
            
            // AND ADD IT AS A ROW TO THE TABLE
            Draft.addPlayer(player);
            
             //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }
    }
    
    public void handleEditPlayerRequest(Fdk_gui gui, BaseballPlayer playerToEdit) {
        DraftDataManager ddm = gui.getDataManager();
        Draft draft = ddm.getDraft();
        playerDialog.showEditPlayerDialog(playerToEdit);
        
        // DID THE USER CONFIRM?
        if (playerDialog.wasCompleteSelected()) {
            // UPDATE THE SCHEDULE ITEM
            BaseballPlayer player = playerDialog.getPlayer();
            playerToEdit.setPosition(player.getPosition());
            playerToEdit.setContract(player.getContract());
            playerToEdit.setSalary(player.getSalary());
            
            //update gui
            draft.getAvailablePlayers().set(draft.getAvailablePlayers().indexOf(playerToEdit), player);
            
             //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        }
        else {
            // THE USER MUST HAVE PRESSED CANCEL, SO
            // WE DO NOTHING
        }        
    }
    
    public void handleRemovePlayerRequest(Fdk_gui gui, BaseballPlayer playerToRemove) {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(PropertiesManager.getPropertiesManager().getProperty(REMOVE_ITEM_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) { 
            gui.getDataManager().getDraft().removePlayer(playerToRemove);
             //COURSE IS NOW DIRTY AND THUS CAN BE SAVED
            gui.getFileController().markAsEdited(gui);
        }
    }
        
}
