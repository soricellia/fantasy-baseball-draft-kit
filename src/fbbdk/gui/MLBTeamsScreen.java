/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_PropertyType.MLB_SCREEN_TEAM_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.MLB_TEAMS_SCREEN_HEADING_LABEL;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.BaseballTeam;
import fbbdk.data.DraftDataManager;
import static fbbdk.gui.FantasyStandingScreen.PADDING_STYLE;
import static fbbdk.gui.HomeScreen.SCREEN_STYLE;
import static fbbdk.gui.PlayerScreen.SUB_HEADING;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class MLBTeamsScreen extends BorderPane{
    PropertiesManager props;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    DraftDataManager dataManager;
    
    Label mlbTeamsHeadingLabel;
    Label mlbTeamsLabel;
    
    GridPane pane;
    
    HBox selectionPane;
    //we need a combo box to select the mlb tea
    ComboBox<BaseballTeam> mlbTeams;

    public MLBTeamsScreen() {
    }
    //this is the table that the players will be displayed in
     TableView<BaseballPlayer> playerTable;
    //and the table colums
    TableColumn<BaseballPlayer, String> firstNameColumn;
    TableColumn<BaseballPlayer, String> lastNameColumn;
    TableColumn<BaseballPlayer, String> positionsColumn;
    TableColumn<BaseballPlayer, Integer> yearOfBirthColumn;
    TableColumn<BaseballPlayer, Integer> winRunColumn;
    TableColumn<BaseballPlayer, Integer> savesHRColumn;
    TableColumn<BaseballPlayer, Integer> kRBIColumn;
    TableColumn<BaseballPlayer, Double> eraSBColumn;
    TableColumn<BaseballPlayer, Double> whipBAColumn;
   
    
    final static String HEADING_STYLE = "heading_label";
    
    //these will be the constants needed for the table colums
    private static final String FIRST_NAME_COLUMN = "First";
    private static final String LAST_NAME_COLUMN = "Last";
    private static final String POSITIONS_COLUMN = "Positions";
    private static final String YEAR_OF_BIRTH_COLUMN = "Year of Birth";
    private static final String RUN_WIN_COLUMN = "R/W";
    private static final String HR_SV_COLUMN = "HR/SV";
    private static final String RBI_K_COLUMN = "RBI/K";
    private static final String SB_ERA_COLUMN = "SB/ERA";
    private static final String BA_WHIP_COLUMN = "BA/WHIP";
    
    
    
    public MLBTeamsScreen(Fdk_gui initGui, Stage primaryStage,
            MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog){
        //init the properties Manager
        props = PropertiesManager.getPropertiesManager();
        dataManager = initGui.getDataManager();
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        
        //set the style class
        this.getStyleClass().add(SCREEN_STYLE);
        
        //init the components
        initComponents();
        
        //now we do the event handlers
        initEventHandlers();
        
        //and we select the first team in the mlb teams combo box
        //this will trigger an event handler, setting the table to the correct
        //mlb team
        mlbTeams.getSelectionModel().selectFirst();
       
    }

    private void initComponents() {
        
        //init the gridpane
        pane = new GridPane();
        pane.getStyleClass().add(PADDING_STYLE);
        
        mlbTeamsHeadingLabel = initGridLabel(pane,MLB_TEAMS_SCREEN_HEADING_LABEL,HEADING_STYLE,1,1,1,1);
        
        //first we will construct the pane that the teams selection items will
        //go on
        selectionPane = new HBox();
        selectionPane.setPadding(new Insets(15,15,15,0));
        selectionPane.setSpacing(15);
        
        //now we construct the label
        mlbTeamsLabel = new Label(props
                .getProperty(MLB_SCREEN_TEAM_LABEL.toString()));
        mlbTeamsLabel.getStyleClass().add(SUB_HEADING);
        
        //and now we construct the mlb teams combo box
        mlbTeams = new ComboBox();
        mlbTeams.getItems().addAll(dataManager.getDraft().getMlbTeams());
        
        //and we add everything at once to the selection pane
        selectionPane.getChildren().addAll(mlbTeamsLabel,mlbTeams);
        
        //now we can add the selectionPane to the grid
        pane.add(selectionPane, 1, 2);
        createTable();
        
        this.setCenter(pane);
    }
      // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, Fdk_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }
    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(Fdk_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }
    private void createTable(){
        //first init the player table
        playerTable = new TableView<>();

        //and the table colums
        //note that some of the column headings are not set
        //this will be done later in an action listener
        firstNameColumn = new TableColumn<>(FIRST_NAME_COLUMN);
        firstNameColumn.setEditable(false);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.setPrefWidth(150);

        lastNameColumn = new TableColumn<>(LAST_NAME_COLUMN);
        lastNameColumn.setEditable(false);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setPrefWidth(150);
        
        positionsColumn = new TableColumn<>(POSITIONS_COLUMN);
        positionsColumn.setEditable(false);
        positionsColumn.setCellValueFactory(new PropertyValueFactory<>("positions"));
        positionsColumn.setPrefWidth(150);
        
        yearOfBirthColumn = new TableColumn<>(YEAR_OF_BIRTH_COLUMN);
        yearOfBirthColumn.setEditable(false);
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        yearOfBirthColumn.setPrefWidth(150);
        
        winRunColumn = new TableColumn<>();
        winRunColumn.setEditable(false);
        winRunColumn.setCellValueFactory(new PropertyValueFactory<>("winRun"));
        winRunColumn.setPrefWidth(120);

        savesHRColumn = new TableColumn<>();
        savesHRColumn.setEditable(false);
        savesHRColumn.setCellValueFactory(new PropertyValueFactory<>("savesHR"));
        savesHRColumn.setPrefWidth(120);
            
        kRBIColumn = new TableColumn<>();
        kRBIColumn.setEditable(false);
        kRBIColumn.setCellValueFactory(new PropertyValueFactory<>("kRBI"));
        kRBIColumn.setPrefWidth(120);
        
        eraSBColumn = new TableColumn<>();
        eraSBColumn.setEditable(false);
        eraSBColumn.setCellValueFactory(new PropertyValueFactory<>("eraSB"));
        eraSBColumn.setPrefWidth(120);
        
        whipBAColumn = new TableColumn<>();
        whipBAColumn.setEditable(false);
        whipBAColumn.setCellValueFactory(new PropertyValueFactory<>("whipBA"));
        whipBAColumn.setPrefWidth(120);

        //now we can add all of these bad girls to the table
        playerTable.getColumns().addAll(firstNameColumn, lastNameColumn,
                positionsColumn, yearOfBirthColumn, winRunColumn,
                savesHRColumn, kRBIColumn, eraSBColumn, whipBAColumn);
        //make the playerTable editable
        playerTable.setEditable(true);
        
        //ok now lets make the playerTable a bit bigger
        playerTable.setPrefHeight(800);
        
        playerTable.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.9) , 5, 0.0 , 0 , 1 );");
        
        //lets set the table headings
        setMixedTableHeadings();
        
        //and finally we can add the table to the gridPane
        pane.add(playerTable, 1, 3);
        GridPane.setHgrow(playerTable, Priority.ALWAYS);
    }
    private void initEventHandlers(){
        mlbTeams.setOnAction(e->{
            playerTable.setItems(dataManager.getDraft().getMlbTeam(
                    mlbTeams.getSelectionModel().getSelectedItem().getTeamName())
                    .getObservablePlayers());
        });
        
    }
       //SETS ALL TABLE HEADINGS TO PITCHER AND HITTER HEADINGS
    private void setMixedTableHeadings() {
        winRunColumn.setText(RUN_WIN_COLUMN);
        savesHRColumn.setText(HR_SV_COLUMN);
        kRBIColumn.setText(RBI_K_COLUMN);
        eraSBColumn.setText(SB_ERA_COLUMN);
        whipBAColumn.setText(BA_WHIP_COLUMN);
    }
}
