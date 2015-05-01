/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_CANCEL_BUTTON_TEXT;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_COMPLETE_BUTTON_TEXT;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_EDIT_FANTASY_TEAM_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_EDIT_PLAYER_CONTRACT_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_EDIT_PLAYER_POS_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_PLAYER_HEADING_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DIALOG_PLAYER_SALARY_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.ILLEGAL_PLAYER_SALARY_MESSAGE;
import static fantasybaseballdraftkit.Fdk_PropertyType.NO_PLAYER_SALARY_MESSAGE;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_FLAG_IMAGES;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_PLAYER_IMAGES;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.BaseballTeam;
import fbbdk.data.Draft;
import static fbbdk.gui.AddPlayerDialog.CLASS_HEADING_LABEL;
import static fbbdk.gui.AddPlayerDialog.COMPLETE;
import static fbbdk.gui.Fdk_gui.PRIMARY_STYLE_SHEET;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class EditPlayerDialog extends Stage {

    //THIS IS THE DATA BEHIND THIS UI
    BaseballPlayer player;
    //everything goes in here
    GridPane gridPane;

    Scene dialogScene;

    //these things go into the adddialog 
    Label dialogEditHeadingLabel;

    ImageView playerPicturePane;
    Image playerPicture;
    ImageView playerFlagPane;
    Image playerFlag;

    Label dialogEditPlayerNameLabel;
    Label dialogEditPlayerPositionLabel;

    Label dialogEditPlayerFantasyTeamLabel;
    ComboBox<BaseballTeam> dialogEditPlayerTeamComboBox;

    Label dialogEditPlayerPosLabel;
    ComboBox<String> dialogEditPlayerPositionComboBox;

    Label dialogEditPlayerContractLabel;
    ComboBox<String> dialogEditPlayerContractComboBox;

    Label dialogEditPlayerSalaryLabel;
    TextField dialogEditPlayerSalaryTextField;

    Button dialogCompleteButton;
    Button dialogCancelButton;

    String selection;

    Draft draft;
    //this is for the free agent pool
    BaseballTeam freeAgent;
    public static String[] contractType = {"S2", "S1", "X"};
    public static String JPG_EXTENTION = ".jpg";
    public static String PNG_EXTENTION = ".png";
    public static String NO_IMAGE = "AAA_PhotoMissing";
    public static String FREE_AGENT = "Free Agents";

    public EditPlayerDialog(Stage primaryStage, Draft draft, MessageDialog messageDialog) {

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
        dialogEditHeadingLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_PLAYER_HEADING_LABEL.toString()));
        dialogEditHeadingLabel.getStyleClass().add(CLASS_HEADING_LABEL);
        //we need the player image
        //since we cant contruct the picture until we have a player we will
        //just use this pane to stick into the grid
        playerPicturePane = new ImageView();
        playerFlagPane = new ImageView();

        //now we need the position - nothing to add yet
        dialogEditPlayerPositionLabel = new Label();

        //now the first name
        dialogEditPlayerNameLabel = new Label();
        //now the pro team stuff
        dialogEditPlayerFantasyTeamLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_EDIT_FANTASY_TEAM_LABEL.toString()));
        dialogEditPlayerTeamComboBox = new ComboBox();

        dialogEditPlayerPosLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_EDIT_PLAYER_POS_LABEL.toString()));
        //the items in this will need to be set still
        dialogEditPlayerPositionComboBox = new ComboBox();

        dialogEditPlayerContractLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_EDIT_PLAYER_CONTRACT_LABEL.toString()));
        dialogEditPlayerContractComboBox = new ComboBox();

        for (int x = 0; x < contractType.length; x++) {
            dialogEditPlayerContractComboBox.getItems()
                    .add(contractType[x]);
        }
        dialogEditPlayerContractComboBox.getSelectionModel().selectFirst();
        dialogEditPlayerSalaryLabel = new Label(PropertiesManager
                .getPropertiesManager()
                .getProperty(DIALOG_PLAYER_SALARY_LABEL.toString()));
        dialogEditPlayerSalaryTextField = new TextField();

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

                if (dialogEditPlayerSalaryTextField == null
                        || dialogEditPlayerSalaryTextField.getText().isEmpty()) {
                    //need to pop up an error message
                    messageDialog.show(props.getProperty(NO_PLAYER_SALARY_MESSAGE.toString()));
                }
                else {
                   //we are going to try to set the players salary
                   //this will throw if the players salary is not a number
                   //in which case we need to handle the error
                    try{
                        player.setSalary(Integer.parseInt(dialogEditPlayerSalaryTextField.getText()));
                        player.setPosition(dialogEditPlayerPositionComboBox
                                .getSelectionModel().getSelectedItem());
                        this.selection = sourceButton.getText();
                        this.hide();
                    }catch(NumberFormatException e){
                        messageDialog.show(props.getProperty(ILLEGAL_PLAYER_SALARY_MESSAGE.toString()));
                    }
                    //NEEDS TO BE DONE
                }
            } else {
                //this will happen when cancel button is pressed
                this.selection = sourceButton.getText();
                this.hide();
            }
        };

        dialogCompleteButton.setOnAction(completeCancelHandler);
        dialogCancelButton.setOnAction(completeCancelHandler);

        //now we can add everything at once to the gridPane
        gridPane.add(dialogEditHeadingLabel, 0, 0, 2, 1);
        gridPane.add(playerPicturePane, 0, 1, 1, 4);
        gridPane.add(playerFlagPane, 1, 1, 1, 1);
        gridPane.add(dialogEditPlayerNameLabel, 1, 2, 1, 1);
        gridPane.add(dialogEditPlayerPositionLabel, 1, 3, 1, 1);
        gridPane.add(dialogEditPlayerFantasyTeamLabel, 0, 5, 1, 1);
        gridPane.add(dialogEditPlayerTeamComboBox, 1, 5, 1, 1);
        gridPane.add(dialogEditPlayerPosLabel, 0, 6, 1, 1);
        gridPane.add(dialogEditPlayerPositionComboBox, 1, 6, 1, 1);
        gridPane.add(dialogEditPlayerContractLabel, 0, 7, 1, 1);
        gridPane.add(dialogEditPlayerContractComboBox, 1, 7, 1, 1);
        gridPane.add(dialogEditPlayerSalaryLabel, 0, 8, 1, 1);
        gridPane.add(dialogEditPlayerSalaryTextField, 1, 8, 1, 1);
        gridPane.add(dialogCompleteButton, 0, 9, 1, 1);
        gridPane.add(dialogCancelButton, 1, 9, 1, 1);

        dialogScene = new Scene(gridPane);
        dialogScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        this.setScene(dialogScene);

    }

    public void showEditPlayerDialog(BaseballPlayer playerToEdit) {
        //first lets do the images
        player = playerToEdit;
        try {
            playerPicture = new Image("file:" + PATH_PLAYER_IMAGES + playerToEdit.getLastName()
                    + playerToEdit.getFirstName() + JPG_EXTENTION);
        } catch (IllegalArgumentException e) {
            System.out.println("I WENT HERE");
            playerPicture = new Image("file:" + PATH_PLAYER_IMAGES + NO_IMAGE + JPG_EXTENTION);
        }
        playerPicturePane.setImage(playerPicture);
        
        playerFlag = new Image("file:" + PATH_FLAG_IMAGES + playerToEdit.getCountryOfBirth()
                + PNG_EXTENTION);
        playerFlagPane.setImage(playerFlag);
        
        dialogEditPlayerNameLabel.setText(playerToEdit.getFirstName() + " "
                + playerToEdit.getLastName());
        
        //this is for the free agent pool. if this is select that means this 
        //player should show up in the availblePlayers list
        freeAgent = new BaseballTeam();
        freeAgent.setTeamName(FREE_AGENT);
        
        //NOW WE CLEAR THE TEAM COMBO BOX
        dialogEditPlayerTeamComboBox.getItems().clear();
        
        //WE ONLY WANT TO ADD TEAMS THAT NEED PLAYERS POSITIONS
        ArrayList<BaseballTeam> suitableTeams = getSuitableTeams(playerToEdit);
        dialogEditPlayerTeamComboBox.getItems().addAll(suitableTeams);
        
        //we always want freeAgent to show just incase there is no team a player
        //can join.
        dialogEditPlayerTeamComboBox.getItems().add(freeAgent);
        dialogEditPlayerTeamComboBox.getSelectionModel().selectFirst();
        
        //now we need to set the players position
        dialogEditPlayerPositionLabel.setText(playerToEdit.getPositions());
        
        //first we cleat the comboBox
        dialogEditPlayerPositionComboBox.getItems().clear();
        
        //we only want the positions we can use!
        dialogEditPlayerPositionComboBox.getItems().addAll(playerToEdit.getPositions());
        dialogEditPlayerPositionComboBox.getSelectionModel().selectFirst();
        
        dialogEditPlayerSalaryTextField.setText(""+player.getSalary());
        this.showAndWait();
    }

    public boolean wasCompleteSelected() {
        if (selection == null) {
            return false;
        }
        return selection.equals(COMPLETE);
    }
    /**
     * a suitable team is a team that needs a player that can play select positions
     * @param player
     * @return returns a list of teams that can use the player
     */
    public ArrayList<BaseballTeam> getSuitableTeams(BaseballPlayer player){
        ArrayList<BaseballTeam> teams = draft.getTeams();
        ArrayList<BaseballTeam> useableTeams = new ArrayList<>();
        for(int x = 0 ; x < teams.size() ; x++ ){
            if(teams.get(x).canUsePlayer(player)){
                useableTeams.add(teams.get(x));
            }
        }
        return useableTeams;
    }
    public BaseballPlayer getPlayer() {
        return player;
    }
    public BaseballTeam getPlayerTeam(){
        return dialogEditPlayerTeamComboBox.getSelectionModel().getSelectedItem();
    }

}
