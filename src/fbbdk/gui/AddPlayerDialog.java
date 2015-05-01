/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_ADD_FIRST_NAME_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_ADD_LAST_NAME_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_ADD_PRO_TEAM_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_CANCEL_BUTTON_TEXT;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_COMPLETE_BUTTON_TEXT;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_PLAYER_HEADING_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.ILLEGAL_PLAYER_NAME_MESSAGE;
import static fantasybaseballdraftkit.Fdk_PropertyType.ILLEGAL_PLAYER_POSITION_MESSAGE;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.Draft;
import static fbbdk.gui.Fdk_gui.PRIMARY_STYLE_SHEET;
import static fbbdk.gui.PlayerScreen.SUB_HEADING;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class AddPlayerDialog extends Stage {

    //THIS IS THE DATA BEHIND THIS UI

    BaseballPlayer player;
    //everything goes in here
    GridPane gridPane;

    Scene dialogScene;

    //these things go into the adddialog 
    Label dialogAddHeadingLabel;

    Label dialogAddFirstNameLabel;
    TextField dialogAddFirstNameTextField;

    Label dialogAddLastNameLabel;
    TextField dialogAddLastNameTextField;

    Label dialogAddProTeamLabel;
    ComboBox dialogAddProTeamComboBox;
    
    HBox dialogAddPositionCheckBoxPane;
    CheckBox dialogAddCCheckBox;
    CheckBox dialogAddFBCheckBox;
    CheckBox dialogAddSBCheckBox;
    CheckBox dialogAddTBCheckBox;
    CheckBox dialogAddCICheckBox;
    CheckBox dialogAddSSCheckBox;
    CheckBox dialogAddMICheckBox;
    CheckBox dialogAddOFCheckBox;
    CheckBox dialogAddPCheckBox;
    
    Button dialogCompleteButton;
    Button dialogCancelButton;
    
    String selection;

    Draft draft;
    
    private static final String C = "C";
    private static final String FB = "1B";
    private static final String SB = "2B";
    private static final String TB = "3B";
    private static final String CI = "CI";
    private static final String SS = "SS";
    private static final String MI = "MI";
    private static final String OF = "OF";
    private static final String P = "P";
    private static final String UNDER_SCORE = "_";
  
    public static final String CLASS_HEADING_LABEL = "heading_label";
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    
    //this will be needed for the comboBox
    public static final String ADD_PLAYER_TITLE = "Add New Baseball Player";
    public static String[] MLB_TEAMS = {"ATL", "AZ", "CHC", "CIN", "COL", "LAD", "MIA", "MIL",
                "NYM", "PHI", "PIT", "SD", "SF", "STL", "WAS"};
    

    public AddPlayerDialog(Stage primaryStage, Draft draft, MessageDialog messageDialog) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        this.draft = draft;
        
        
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // PUT THE HEADING IN THE GRID
        dialogAddHeadingLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_PLAYER_HEADING_LABEL.toString()));
        dialogAddHeadingLabel.getStyleClass().add(CLASS_HEADING_LABEL);

        //now the first name
        dialogAddFirstNameLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_ADD_FIRST_NAME_LABEL.toString()));
        dialogAddFirstNameLabel.getStyleClass().add(SUB_HEADING);
        dialogAddFirstNameTextField = new TextField();
        dialogAddFirstNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            player.setFirstName(newValue);
        });

        //now the last name
        dialogAddLastNameLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_ADD_LAST_NAME_LABEL.toString()));
        dialogAddLastNameLabel.getStyleClass().add(SUB_HEADING);
        dialogAddLastNameTextField = new TextField();
        dialogAddLastNameTextField.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    player.setLastName(newValue);
                });
        //now the pro team stuff
        dialogAddProTeamLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_ADD_PRO_TEAM_LABEL.toString()));
        dialogAddProTeamLabel.getStyleClass().add(SUB_HEADING);
        dialogAddProTeamComboBox = new ComboBox();
        
        //anddd the checkbox
        dialogAddPositionCheckBoxPane = new HBox();
        dialogAddCCheckBox = new CheckBox(C);
        dialogAddFBCheckBox = new CheckBox(FB);
        dialogAddSBCheckBox = new CheckBox(SB);
        dialogAddTBCheckBox = new CheckBox(TB);
        dialogAddCICheckBox = new CheckBox(CI);
        dialogAddSSCheckBox = new CheckBox(SS);
        dialogAddMICheckBox = new CheckBox(MI);
        dialogAddOFCheckBox = new CheckBox(OF);
        dialogAddPCheckBox = new CheckBox(P);
        //lets add them all now b4 we forget!
        dialogAddPositionCheckBoxPane.getChildren().addAll(dialogAddCCheckBox,
                dialogAddFBCheckBox,dialogAddSBCheckBox,dialogAddTBCheckBox,
                dialogAddCICheckBox,dialogAddSSCheckBox,dialogAddMICheckBox,
                dialogAddOFCheckBox,dialogAddPCheckBox);
        //and make it look pretty
        dialogAddPositionCheckBoxPane.setSpacing(15);
        dialogAddPositionCheckBoxPane.setPadding(new Insets(1,1,5,1));
        
        //and my buttons
        dialogCompleteButton = new Button(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_COMPLETE_BUTTON_TEXT.toString()));
        dialogCancelButton = new Button(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_CANCEL_BUTTON_TEXT.toString()));

        //event handler for buttons
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            if (sourceButton.getText().equals(COMPLETE)) {
                PropertiesManager props = PropertiesManager.getPropertiesManager();

                if (player.getFirstName() == null
                        || player.getLastName() == null) {
                    //need to pop up an error message
                    messageDialog.show(props.getProperty(ILLEGAL_PLAYER_NAME_MESSAGE.toString()));
                } else if(player.getFirstName().trim().isEmpty()
                        || player.getLastName().trim().isEmpty()){
                    //this is really a just in case
                    messageDialog.show(props.getProperty(ILLEGAL_PLAYER_NAME_MESSAGE.toString()));
                }else if(getPlayerPosition().isEmpty()) {
                    messageDialog.show(props.getProperty(ILLEGAL_PLAYER_POSITION_MESSAGE.toString()));
                } else {
                    //first we are going to need to get the position!!!! so...
                    player.setPositions(getPlayerPosition());
                    //and we are going to need the pro team!
                    player.setMlbTeam(dialogAddProTeamComboBox.getSelectionModel()
                            .getSelectedItem().toString());
                    //ok we are good to go!
                    this.selection = sourceButton.getText();
                    uncheckAllCheckBox();
                    this.hide();
                }
            } else {
                //this will happen when cancel button is pressed
                this.selection = sourceButton.getText();
                uncheckAllCheckBox();
                this.hide();
            }
        };

        dialogCompleteButton.setOnAction(completeCancelHandler);
        dialogCancelButton.setOnAction(completeCancelHandler);

        //now we can add everything at once to the gridPane
        gridPane.add(dialogAddHeadingLabel, 0, 0, 2, 1);
        gridPane.add(dialogAddFirstNameLabel, 0, 1, 1, 1);
        gridPane.add(dialogAddFirstNameTextField, 1, 1, 1, 1);
        gridPane.add(dialogAddLastNameLabel, 0, 2, 1, 1);
        gridPane.add(dialogAddLastNameTextField, 1, 2, 1, 1);
        gridPane.add(dialogAddProTeamLabel, 0, 3, 1, 1);
        gridPane.add(dialogAddProTeamComboBox, 1, 3, 1, 1);
        gridPane.add(dialogAddPositionCheckBoxPane, 0, 4,2,1);
        gridPane.add(dialogCompleteButton, 0, 7, 1, 1);
        gridPane.add(dialogCancelButton, 1, 7, 1, 1);

        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);

    }

    public BaseballPlayer showAddPlayerDialog() {
         // SET THE DIALOG TITLE
        setTitle(ADD_PLAYER_TITLE);
        
        // RESET THE ASSIGNMENT OBJECT WITH DEFAULT VALUES
        player = new BaseballPlayer();
        // LOAD THE UI STUFF
        dialogAddFirstNameTextField.setText(player.getFirstName());
        dialogAddLastNameTextField.setText(player.getLastName());
        
        
        dialogAddProTeamComboBox.getItems().clear();
        for(int x = 0 ; x < MLB_TEAMS.length ; x++){
            dialogAddProTeamComboBox.getItems().add(MLB_TEAMS[x]);
        }
        dialogAddProTeamComboBox.getSelectionModel().selectFirst();
        
        // AND OPEN IT UP
        this.showAndWait();
        
        return player;
    
    }

    public boolean wasCompleteSelected() {
        //just in case
        if(selection == null)
            return false;
        return selection.equals(COMPLETE);
    }

    public BaseballPlayer getPlayer() {
        return player;
    }

    private String getPlayerPosition() {
        String position = "";
        if(dialogAddCCheckBox.isSelected()){
            position=C;
        }
        if(dialogAddFBCheckBox.isSelected()){
            position+=UNDER_SCORE+FB;
        }
        if(dialogAddSBCheckBox.isSelected()){
            position+=UNDER_SCORE+SB;
        }
        if(dialogAddTBCheckBox.isSelected()){
            position+=UNDER_SCORE+TB;
        }
        if(dialogAddCICheckBox.isSelected()){
            position+=UNDER_SCORE+CI;
        }
        if(dialogAddSSCheckBox.isSelected()){
            position+=UNDER_SCORE+SS;
        }
        if(dialogAddMICheckBox.isSelected()){
            position+=UNDER_SCORE+MI;
        }
        if(dialogAddOFCheckBox.isSelected()){
            position+=UNDER_SCORE+OF;
        }
        if(dialogAddPCheckBox.isSelected()){
            position+=UNDER_SCORE+P;
        }
        return position;
    }
    private void uncheckAllCheckBox(){
        dialogAddCCheckBox.selectedProperty().set(false);
        dialogAddFBCheckBox.selectedProperty().set(false);
        dialogAddSBCheckBox.selectedProperty().set(false);
        dialogAddTBCheckBox.selectedProperty().set(false);
        dialogAddCICheckBox.selectedProperty().set(false);
        dialogAddSSCheckBox.selectedProperty().set(false);
        dialogAddMICheckBox.selectedProperty().set(false);
        dialogAddOFCheckBox.selectedProperty().set(false);
        dialogAddPCheckBox.selectedProperty().set(false);
    }

}
