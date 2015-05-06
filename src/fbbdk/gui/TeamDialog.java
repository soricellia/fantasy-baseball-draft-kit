/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_CANCEL_BUTTON_TEXT;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_COMPLETE_BUTTON_TEXT;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_TEAM_HEADING_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_TEAM_NAME_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_TEAM_OWNER_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.ILLEGAL_TEAM_NAME_MESSAGE;
import static fantasybaseballdraftkit.Fdk_PropertyType.ILLEGAL_TEAM_OWNER_MESSAGE;
import fbbdk.data.BaseballTeam;
import static fbbdk.gui.Fdk_gui.PRIMARY_STYLE_SHEET;
import static fbbdk.gui.PlayerScreen.SUB_HEADING;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class TeamDialog extends Stage {

    //THIS IS THE OBJECT DATA BEHIND THIS UI
    BaseballTeam team;

    //everything goes in here
    GridPane gridPane;

    Scene dialogScene;

    //things that go into gridPane
    Label dialogTeamHeadingLabel;
    Label dialogTeamNameLabel;
    Label dialogTeamOwnerLabel;

    TextField nameTextField;
    TextField ownerTextField;

    Button dialogCompleteButton;
    Button dialogCancelButton;

    MessageDialog messageDialog;

    String selection;

    public static final String CLASS_HEADING_LABEL = "heading_label";
    public static final String COMPLETE = "Complete";
    public static final String CANCEL = "Cancel";
    public static final String ADD_TEAM_TITLE = "Add Fantasy Team";
    public static final String EDIT_TEAM_TITLE = "Edit Fantasy Team";

    public TeamDialog(Stage primaryStage, MessageDialog initMessageDialog) {
        // MAKE THIS DIALOG MODAL, MEANING OTHERS WILL WAIT
        // FOR IT WHEN IT IS DISPLAYED
        initModality(Modality.WINDOW_MODAL);
        initOwner(primaryStage);
        messageDialog = initMessageDialog;
        // FIRST OUR CONTAINER
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // PUT THE HEADING IN THE GRID
        dialogTeamHeadingLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_TEAM_HEADING_LABEL.toString()));
        dialogTeamHeadingLabel.getStyleClass().add(CLASS_HEADING_LABEL);

        //now the name
        dialogTeamNameLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_TEAM_NAME_LABEL.toString()));
        dialogTeamNameLabel.getStyleClass().add(SUB_HEADING);
        nameTextField = new TextField();

        //now the owner
        dialogTeamOwnerLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_TEAM_OWNER_LABEL.toString()));
        dialogTeamOwnerLabel.getStyleClass().add(SUB_HEADING);
        ownerTextField = new TextField();

        dialogCompleteButton = new Button(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_COMPLETE_BUTTON_TEXT.toString()));
        dialogCancelButton = new Button(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_CANCEL_BUTTON_TEXT.toString()));

        //now that everything is constructed we will set up the event handlers
        initEventHandlers();
        
        //now we can add everything at once to the gridPane
        gridPane.add(dialogTeamHeadingLabel, 0, 0, 2, 1);
        gridPane.add(dialogTeamNameLabel, 0, 1, 1, 1);
        gridPane.add(nameTextField, 1, 1, 1, 1);
        gridPane.add(dialogTeamOwnerLabel, 0, 2, 1, 1);
        gridPane.add(ownerTextField, 1, 2, 1, 1);
        gridPane.add(dialogCompleteButton, 0, 3, 1, 1);
        gridPane.add(dialogCancelButton, 1, 3, 1, 1);

        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);
    }

    /**
     * Accessor method for getting the selection the user made.
     *
     * @return Either complete or cancel, depending on which button the user
     * selected when this dialog was presented.
     */
    public String getSelection() {
        return selection;
    }

    public BaseballTeam getTeam() {
        return team;
    }

    public boolean wasCompleteSelected() {
        if (selection == null) {
            return false;
        }
        return selection.equals(COMPLETE);
    }

    /**
     * This method loads a custom message into the label and then pops open the
     * dialog.
     *
     *
     * @return Team being added
     */
    public BaseballTeam showAddTeamDialog() {
        // SET THE DIALOG TITLE
        setTitle(ADD_TEAM_TITLE);

        // RESET THE SCHEDULE ITEM OBJECT WITH DEFAULT VALUES
        team = new BaseballTeam();

        loadGUIData();

        // AND OPEN IT UP
        this.showAndWait();

        return team;
    }

    public void loadGUIData() {
        // LOAD THE UI STUFF

        nameTextField.setText(team.getTeamName());
        ownerTextField.setText(team.getCoach());

    }

    private void initEventHandlers() {
        //first lets set on the properties manager
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        //now lets construct the event handler for our complete and cancel
        //buttons
        EventHandler completeCancelHandler = (EventHandler<ActionEvent>) (ActionEvent ae) -> {
            Button sourceButton = (Button) ae.getSource();
            if (sourceButton.getText().equals(COMPLETE)) {
                if (team.getTeamName() == null) {
                    //need to pop up an error message
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_NAME_MESSAGE.toString()));
                } else if (team.getTeamName().trim().isEmpty()) {
                    //need to pop up an error message
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_NAME_MESSAGE.toString()));
                } else if (team.getCoach() == null) {
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_OWNER_MESSAGE.toString()));
                } else if (team.getCoach().trim().isEmpty()) {
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_OWNER_MESSAGE.toString()));
                } else {
                    this.selection = sourceButton.getText();
                    this.hide();
                }
            } else {
                this.selection = sourceButton.getText();
                this.hide();
            }
        };
        //now we add the handler
        dialogCompleteButton.setOnAction(completeCancelHandler);
        dialogCancelButton.setOnAction(completeCancelHandler);

        //now lets set out textField listeners
        ownerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            team.setCoach(newValue);
        });
        
        //when enter button is hit we want to be responsive
        ownerTextField.setOnKeyPressed(e->{
            
            if(e.getCode().equals(KeyCode.ENTER)){
                if (team.getTeamName() == null) {
                    //need to pop up an error message
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_NAME_MESSAGE.toString()));
                } else if (team.getTeamName().trim().isEmpty()) {
                    //need to pop up an error message
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_NAME_MESSAGE.toString()));
                } else if (team.getCoach() == null) {
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_OWNER_MESSAGE.toString()));
                } else if (team.getCoach().trim().isEmpty()) {
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_OWNER_MESSAGE.toString()));
                } else {
                    this.selection = COMPLETE;
                    this.hide();
                }
            }else if(e.getCode().equals(KeyCode.ESCAPE)){
                selection = CANCEL;
                this.hide();
            }
        });
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            team.setTeamName(newValue);
        });
        
        //when enter button is hit we want to be responsive
        nameTextField.setOnKeyPressed(e->{
            if(e.getCode().equals(KeyCode.ENTER)){
                if (team.getTeamName() == null) {
                    //need to pop up an error message
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_NAME_MESSAGE.toString()));
                } else if (team.getTeamName().trim().isEmpty()) {
                    //need to pop up an error message
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_NAME_MESSAGE.toString()));
                } else if (team.getCoach() == null) {
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_OWNER_MESSAGE.toString()));
                } else if (team.getCoach().trim().isEmpty()) {
                    messageDialog.show(props.getProperty(ILLEGAL_TEAM_OWNER_MESSAGE.toString()));
                } else {
                    this.selection = COMPLETE;
                    this.hide();
                }
            }else if(e.getCode().equals(KeyCode.ESCAPE)){
                selection = CANCEL;
                this.hide();
            }
        });
        
        
    }

    public void showEditTeamDialog(BaseballTeam teamToEdit) {
        // SET THE DIALOG TITLE
        setTitle(EDIT_TEAM_TITLE);

        // LOAD THE SCHEDULE ITEM INTO OUR LOCAL OBJECT
        team = new BaseballTeam();
        team.setTeamName(teamToEdit.getTeamName());
        team.setCoach(teamToEdit.getCoach());
        team.setPlayers(teamToEdit.getPlayers());
        // AND THEN INTO OUR GUI
        loadGUIData();

        // AND OPEN IT UP
        this.showAndWait();
    }
}
