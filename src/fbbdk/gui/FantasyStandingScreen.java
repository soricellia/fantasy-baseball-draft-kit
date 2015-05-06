/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_PropertyType.FANTASY_STANDING_SCREEN_HEADING_LABEL;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.BaseballTeam;
import fbbdk.data.DraftDataManager;
import static fbbdk.gui.HomeScreen.SCREEN_STYLE;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class FantasyStandingScreen extends BorderPane{
    
    Label headingLabel;
    
    GridPane pane;
    
    PropertiesManager properties;
    DraftDataManager dataManager;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    TableView playerTable;
    //and the table colums
    TableColumn<BaseballTeam, String> teamNameColumn;
    TableColumn<BaseballTeam, String> playersNeededColumn;
    TableColumn<BaseballTeam, String> moneyLeftColumn;
    TableColumn<BaseballTeam, String> moneyPerPersonColumn;
    TableColumn<BaseballTeam, String> runsColumn;
    TableColumn<BaseballTeam, String> homeRunsColumn;
    TableColumn<BaseballTeam, Integer> rbiColumn;
    TableColumn<BaseballTeam, Integer> sbColumn;
    TableColumn<BaseballTeam, Double> baColumn;
    TableColumn<BaseballTeam, Integer> winsColumn;
    TableColumn<BaseballTeam, Integer> savesColumn;
    TableColumn<BaseballTeam, Integer> kColumn;
    TableColumn<BaseballTeam, Double> eraColumn;
    TableColumn<BaseballTeam, Double> whipColumn;
    TableColumn<BaseballTeam, Integer> totalPointsColumn;
    
    //THESE ARE THE COLUMN HEADINGS FOR THE PLAYER TABLE
    private static final String TEAM_NAME_COLUMN = "Team Name";
    private static final String PLAYERS_NEEDED_COLUMN = "Players Needed";
    private static final String MONEY_LEFT_COLUMN = "$ Left";
    private static final String MONEY_PER_PERSON_COLUMN = "$ PP";
    private static final String RUNS_COLUMN = "R";
    private static final String HOME_RUNS_COLUMN = "HR";
    private static final String RBI_COLUMN = "RBI";
    private static final String SB_COLUMN = "SB";
    private static final String BA_COLUMN = "BA";
    private static final String WINS_COLUMN = "W";
    private static final String SAVES_COLUMN = "SV";
    private static final String K_COLUMN = "K";
    private static final String ERA_COLUMN = "ERA";
    private static final String WHIP_COLUMN = "WHIP";
    private static final String TOTAL_POINTS_COLUMN = "Total Points";
    
    public static final String PADDING_TOP_STYLE = "padding_top";
    public static final String PADDING_STYLE = "padding";
    
    final static String HEADING_STYLE = "heading_label";
    public FantasyStandingScreen(Fdk_gui initGui, Stage primaryStage,
            MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog){
        //init the properties manager
        properties = PropertiesManager.getPropertiesManager();
        //set the class
        this.getStyleClass().add(SCREEN_STYLE);
        dataManager = initGui.getDataManager();
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        
        
        //init the components
        initComponents();
    }

    private void initComponents() {
        //first init the gridPane
        pane = new GridPane();
        pane.getStyleClass().add(PADDING_STYLE);
        //construct the heading labels
        headingLabel = initGridLabel(pane,FANTASY_STANDING_SCREEN_HEADING_LABEL,HEADING_STYLE,1,1,1,1);
        playerTable = createTable(playerTable);
        pane.add(playerTable,1 , 2);
        GridPane.setHgrow(playerTable, Priority.ALWAYS);
        GridPane.setVgrow(playerTable, Priority.ALWAYS);
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
    
    
    public TableView<BaseballTeam> getFantasyStandingsTable(){
        return playerTable;
    }
    private TableView<BaseballTeam> createTable(TableView<BaseballTeam> table) {

        //first init the player table
        table = new TableView<>();
        table.setFixedCellSize(60);
        table.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.9) , 5, 0.0 , 0 , 1 );");
        teamNameColumn = new TableColumn<>(TEAM_NAME_COLUMN);
        teamNameColumn.setEditable(false);
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("observableTeamName"));
        teamNameColumn.setPrefWidth(150);
        
        playersNeededColumn = new TableColumn<>(PLAYERS_NEEDED_COLUMN);
        playersNeededColumn.setEditable(false);
        playersNeededColumn.setCellValueFactory(new PropertyValueFactory<>("playersNeeded"));
        playersNeededColumn.setPrefWidth(150);
        
        moneyLeftColumn = new TableColumn<>(MONEY_LEFT_COLUMN);
        moneyLeftColumn.setEditable(false);
        moneyLeftColumn.setCellValueFactory(new PropertyValueFactory<>("obvMoneyLeft"));
        moneyLeftColumn.setPrefWidth(75);
        
        moneyPerPersonColumn = new TableColumn<>(MONEY_PER_PERSON_COLUMN);
        moneyPerPersonColumn.setEditable(false);
        moneyPerPersonColumn.setCellValueFactory(new PropertyValueFactory<>("moneyPerPlayer"));
        moneyPerPersonColumn.setPrefWidth(75);
        
        runsColumn = new TableColumn<>(RUNS_COLUMN);
        runsColumn.setEditable(false);
        runsColumn.setCellValueFactory(new PropertyValueFactory<>("runs"));
        runsColumn.setPrefWidth(75);
        
        homeRunsColumn = new TableColumn<>(HOME_RUNS_COLUMN);
        homeRunsColumn.setEditable(false);
        homeRunsColumn.setCellValueFactory(new PropertyValueFactory<>("hr"));
        homeRunsColumn.setPrefWidth(75);
        
        rbiColumn = new TableColumn<>(RBI_COLUMN);
        rbiColumn.setEditable(false);
        rbiColumn.setCellValueFactory(new PropertyValueFactory<>("rbi"));
        rbiColumn.setPrefWidth(75);
        
        sbColumn = new TableColumn<>(SB_COLUMN);
        sbColumn.setEditable(false);
        sbColumn.setCellValueFactory(new PropertyValueFactory<>("obvSb"));
        sbColumn.setPrefWidth(75);
        
        baColumn = new TableColumn<>(BA_COLUMN);
        baColumn.setEditable(false);
        baColumn.setCellValueFactory(new PropertyValueFactory<>("ba"));
        baColumn.setPrefWidth(75);
        
        winsColumn = new TableColumn<>(WINS_COLUMN);
        winsColumn.setEditable(false);
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        winsColumn.setPrefWidth(75);
        
        savesColumn = new TableColumn<>(SAVES_COLUMN);
        savesColumn.setCellValueFactory(new PropertyValueFactory<>("sv"));
        savesColumn.setPrefWidth(75);
        
        kColumn = new TableColumn<>(K_COLUMN);
        kColumn.setEditable(false);
        kColumn.setCellValueFactory(new PropertyValueFactory<>("k"));
        kColumn.setPrefWidth(75);
        
        eraColumn = new TableColumn<>(ERA_COLUMN);
        eraColumn.setEditable(false);
        eraColumn.setCellValueFactory(new PropertyValueFactory<>("era"));
        eraColumn.setPrefWidth(75);
        
        whipColumn = new TableColumn<>(WHIP_COLUMN);
        whipColumn.setCellValueFactory(new PropertyValueFactory<>("whip"));
        whipColumn.setPrefWidth(75);
        
        totalPointsColumn = new TableColumn<>(TOTAL_POINTS_COLUMN);
        totalPointsColumn.setPrefWidth(150);
        
        //now we can add all of these bad girls to the table
        table.getColumns().addAll(teamNameColumn, playersNeededColumn, moneyLeftColumn,
                moneyPerPersonColumn, runsColumn, homeRunsColumn, rbiColumn,
                sbColumn, baColumn, winsColumn,savesColumn, kColumn, eraColumn,
                whipColumn,totalPointsColumn);
        //make the playerTable editable
        table.setEditable(true);
        //add the data to the table
        table.setItems(dataManager.getDraft().getObservableTeams());
        //ok now lets make the playerTable a bit bigger
        table.setPrefHeight(800);
        return table;
    }

    
}
