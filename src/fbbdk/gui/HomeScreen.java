/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_PropertyType.ADD_TEAM_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.ADD_TEAM_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_SEARCH_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.EDIT_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.EDIT_ICON_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.HOME_SCREEN_HEADING_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.HOME_SCREEN_PLAYER_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.HOME_SCREEN_TAXI_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_TEAM_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_TEAM_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.SELECT_TEAM_LABEL;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_GUI_IMAGES;
import fbbdk.controller.DraftController;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.BaseballTeam;
import fbbdk.data.DraftDataManager;
import static fbbdk.gui.PlayerScreen.EMPTY_TEXT;
import static fbbdk.gui.PlayerScreen.SUB_HEADING;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class HomeScreen extends BorderPane {

    //need this
    Stage primaryStage;

    //this is the heading label
    Label teamsHeadingLabel;

    //put the grid pane in this 
    ScrollPane scrollPane;
    //this holds everything
    VBox pane;

    //this holds the top stuff
    BorderPane borderTopPane;
    VBox topPane;
    //toppane is split into two parts
    HBox upperTopPane;
    HBox middleTopPane;
    HBox lowerTopPane;

    //these are the components that go in the topPane
    Button addTeamButton;
    Button removeTeamButton;
    Button editTeamButton;
    Label selectTeamLabel;
    ComboBox<BaseballTeam> teamsComboBox;

    HBox searchPanel;
    Label draftNameLabel;
    TextField draftNameTextField;

    //this will hold the table
    BorderPane playerPane;
    BorderPane taxiPane;
    SplitPane splitPane;

    //label for playerTable
    Label playerLabel;
    //label for taxiTable
    Label taxiLabel;

    //and the table that holds all the data
    TableView<BaseballPlayer> playerTable;
    TableView<BaseballPlayer> taxiTable;
    //and the table colums
    TableColumn<BaseballPlayer, String> positionColumn;
    TableColumn<BaseballPlayer, String> firstNameColumn;
    TableColumn<BaseballPlayer, String> lastNameColumn;
    TableColumn<BaseballPlayer, String> proTeamColumn;
    TableColumn<BaseballPlayer, String> positionsColumn;
    TableColumn<BaseballPlayer, String> contractColumn;
    TableColumn<BaseballPlayer, Integer> winRunColumn;
    TableColumn<BaseballPlayer, Integer> savesHRColumn;
    TableColumn<BaseballPlayer, Integer> kRBIColumn;
    TableColumn<BaseballPlayer, Double> eraSBColumn;
    TableColumn<BaseballPlayer, Double> whipBAColumn;
    TableColumn<BaseballPlayer, Integer> evColumn;
    TableColumn<BaseballPlayer, Integer> salaryColumn;

    //THESE ARE THE COLUMN HEADINGS FOR THE PLAYER TABLE
    private static final String POSITION_COLUMN = "Position";
    private static final String FIRST_NAME_COLUMN = "First";
    private static final String LAST_NAME_COLUMN = "Last";
    private static final String PRO_TEAM_COLUMN = "Pro Team";
    private static final String POSITIONS_COLUMN = "Positions";
    private static final String CONTRACT_COLUMN = "Contract";
    private static final String RUN_WIN_COLUMN = "R/W";
    private static final String HR_SV_COLUMN = "HR/SV";
    private static final String RBI_K_COLUMN = "RBI/K";
    private static final String SB_ERA_COLUMN = "SB/ERA";
    private static final String BA_WHIP_COLUMN = "BA/WHIP";
    private static final String ESTIMATED_VALUE_COLUMN = "Estimated Value";
    private static final String SALARY_COLUMN = "Salary";
    public static final String PADDING_TOP_STYLE = "padding_top";

    //this is some of the important stuff ill need for this screen
    PropertiesManager propertiesManager;
    DraftDataManager dataManager;
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    Fdk_gui gui;
    DraftController draftController;

    final static String HEADING_LABEL = "heading_label";
    final static String SCREEN_STYLE = "screen";
    final String PADDING_STYLE = "padding";
    final String BORDER_STYLE = "bordered_pane";
    final String SPACING_STYLE = "spacing";
    final String BACKGROUND_STYLE = "background";
    final String TABLE_HEADING = "table_heading";

    public HomeScreen(Fdk_gui initGui, Stage initPrimaryStage, MessageDialog initMessageDialog,
            YesNoCancelDialog initYesNoCancelDialog) {
        //first call super
        super();
        this.getStyleClass().add(SCREEN_STYLE);
        //init the important stuff
        propertiesManager = PropertiesManager.getPropertiesManager();
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        gui = initGui;
        dataManager = gui.getDataManager();
        primaryStage = initPrimaryStage;
        draftController = gui.getDraftController();
        //set the class
        //init the components
        initComponents();
        initEventHandlers();

    }

    private void initComponents() {
        scrollPane = new ScrollPane();
        scrollPane.setBorder(Border.EMPTY);
        scrollPane.setFitToWidth(true);
        
        pane = new VBox();
        
        pane.getStyleClass().addAll(PADDING_STYLE, SCREEN_STYLE);
        //search bar and things go here
        initTopPane();
        //all the players go here
        playerPane = initPlayerPane();
        //the taxi players table go here
        taxiPane = initTaxiPane();

        pane.getChildren().add(playerPane);
        pane.getChildren().add(taxiPane);
        //add the gridPane to the scrollPane
        scrollPane.setContent(pane);
        //now set homescreen to the scroll pane
        this.setCenter(scrollPane);
    }

    private void initTopPane() {
        //everything in the top pane goes here
        topPane = new VBox();
        //the heading label is going to go here

        upperTopPane = new HBox();
        teamsHeadingLabel = initLabel(HOME_SCREEN_HEADING_LABEL, HEADING_LABEL);
        upperTopPane.getChildren().add(teamsHeadingLabel);
        //the search bar stuff goes here
        middleTopPane = new HBox();
        middleTopPane.getStyleClass().add(PADDING_TOP_STYLE);
        draftNameLabel = initLabel(DRAFT_SEARCH_LABEL, SUB_HEADING);
        draftNameTextField = initTextField(19, EMPTY_TEXT, true);
        middleTopPane.getChildren().addAll(draftNameLabel, draftNameTextField);
        //now the buttons
        lowerTopPane = new HBox();
        lowerTopPane.getStyleClass().addAll(SPACING_STYLE,PADDING_TOP_STYLE);

        HBox tempOneBox = new HBox();
        addTeamButton = initChildButton(tempOneBox,
                ADD_TEAM_ICON, ADD_TEAM_TOOLTIP, false);
        removeTeamButton = initChildButton(tempOneBox,
                REMOVE_TEAM_ICON, REMOVE_TEAM_TOOLTIP, true);

        HBox tempBox = new HBox();
        tempBox.getStyleClass().add(SPACING_STYLE);
        editTeamButton = initChildButton(tempBox,
                EDIT_ICON, EDIT_ICON_TOOLTIP, true);

        selectTeamLabel = initLabel(SELECT_TEAM_LABEL, SUB_HEADING);
        teamsComboBox = new ComboBox();
        teamsComboBox.setDisable(true);

        tempBox.getChildren().addAll(selectTeamLabel, teamsComboBox);

        lowerTopPane.getChildren().addAll(tempOneBox, tempBox);

        //now add them all to the topPane VBox
        topPane.getChildren().addAll(upperTopPane, middleTopPane, lowerTopPane);
        //and finally add the topPane to the gridPane
        pane.getChildren().add(topPane);
    }

    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, Fdk_PropertyType icon,
            Fdk_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_GUI_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }

    // INIT A TEXT FIELD AND PUT IT IN A GridPane
    private TextField initTextField(int size, String initText, boolean editable) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        return tf;
    }

    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container,
            Fdk_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
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

    private BorderPane initPlayerPane() {
        playerPane = new BorderPane();
        playerPane.getStyleClass().addAll(BORDER_STYLE, BACKGROUND_STYLE);

        FlowPane labelPane = new FlowPane();
        labelPane.getStyleClass().add(PADDING_STYLE);

        playerLabel = initLabel(HOME_SCREEN_PLAYER_LABEL, TABLE_HEADING);
        labelPane.getChildren().add(playerLabel);
        playerPane.setTop(labelPane);

        //init the table
        playerTable = createTable(playerTable);

        //set the table to the center
        playerPane.setCenter(playerTable);

        return playerPane;
    }

    private BorderPane initTaxiPane() {
        taxiPane = new BorderPane();
        taxiPane.getStyleClass().addAll(BORDER_STYLE, BACKGROUND_STYLE);

        FlowPane labelPane = new FlowPane();
        labelPane.getStyleClass().add(PADDING_STYLE);

        taxiLabel = initLabel(HOME_SCREEN_TAXI_LABEL, SUB_HEADING);
        labelPane.getChildren().add(taxiLabel);
        taxiPane.setTop(labelPane);

        taxiPane.getStyleClass().add(BORDER_STYLE);
        //first init the player table
        taxiTable = createTable(taxiTable);

        taxiPane.setCenter(taxiTable);

        return taxiPane;
    }

    private TableView<BaseballPlayer> createTable(TableView<BaseballPlayer> table) {

        //first init the player table
        table = new TableView<>();

        //and the table colums
        //note that some of the column headings are not set
        //this will be done later in an action listener
        positionColumn = new TableColumn<>(POSITION_COLUMN);
        positionColumn.setEditable(false);
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        firstNameColumn = new TableColumn<>(FIRST_NAME_COLUMN);
        firstNameColumn.setEditable(false);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        lastNameColumn = new TableColumn<>(LAST_NAME_COLUMN);
        lastNameColumn.setEditable(false);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        proTeamColumn = new TableColumn<>(PRO_TEAM_COLUMN);
        proTeamColumn.setEditable(false);
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<>("mlbTeam"));

        positionsColumn = new TableColumn<>(POSITIONS_COLUMN);
        positionsColumn.setEditable(false);
        positionsColumn.setCellValueFactory(new PropertyValueFactory<>("positions"));

        contractColumn = new TableColumn<>(CONTRACT_COLUMN);
        contractColumn.setEditable(false);
        contractColumn.setCellValueFactory(new PropertyValueFactory<>("contract"));

        winRunColumn = new TableColumn<>(RUN_WIN_COLUMN);
        winRunColumn.setEditable(false);
        winRunColumn.setCellValueFactory(new PropertyValueFactory<>("winRun"));

        savesHRColumn = new TableColumn<>(HR_SV_COLUMN);
        savesHRColumn.setEditable(false);
        savesHRColumn.setCellValueFactory(new PropertyValueFactory<>("savesHR"));

        kRBIColumn = new TableColumn<>(RBI_K_COLUMN);
        kRBIColumn.setEditable(false);
        kRBIColumn.setCellValueFactory(new PropertyValueFactory<>("kRBI"));

        eraSBColumn = new TableColumn<>(SB_ERA_COLUMN);
        eraSBColumn.setEditable(false);
        eraSBColumn.setCellValueFactory(new PropertyValueFactory<>("eraSB"));

        whipBAColumn = new TableColumn<>(BA_WHIP_COLUMN);
        whipBAColumn.setEditable(false);
        whipBAColumn.setCellValueFactory(new PropertyValueFactory<>("whipBA"));

        evColumn = new TableColumn<>(ESTIMATED_VALUE_COLUMN);
        evColumn.setEditable(false);
        evColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedValue"));
        salaryColumn = new TableColumn<>(SALARY_COLUMN);

        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        //now we can add all of these bad girls to the table
        table.getColumns().addAll(positionColumn, firstNameColumn, lastNameColumn,
                proTeamColumn, positionsColumn, contractColumn, winRunColumn,
                savesHRColumn, kRBIColumn, eraSBColumn, whipBAColumn, evColumn,
                salaryColumn);
        //make the playerTable editable
        table.setEditable(true);
        //add the data to the table
        table.setItems(null);
        //ok now lets make the playerTable a bit bigger
        table.setPrefHeight(500);
        return table;
    }

    private void initEventHandlers() {
        //first lets init the controller
        DraftController tec = new DraftController(primaryStage,
                dataManager.getDraft(), messageDialog, yesNoCancelDialog);
        draftNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            dataManager.getDraft().setDraftName(newValue);
        });
        addTeamButton.setOnAction(e -> {
            tec.handleAddTeamRequest(gui, this);
        });

        editTeamButton.setOnAction(e -> {
            tec.handleEditTeamRequest(gui,
                    teamsComboBox.getSelectionModel().getSelectedItem(), this);
        });

        removeTeamButton.setOnAction(e -> {
            tec.handleRemoveTeamRequest(gui,
                    teamsComboBox.getSelectionModel().getSelectedItem(), this);
        });

        teamsComboBox.setOnAction(e -> {
            updateScreen(teamsComboBox.getSelectionModel().getSelectedItem());
        });
        playerTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                //OPEN PLAYER EDITOR
                if(playerTable.getSelectionModel().getSelectedIndex()==-1){
                    playerTable.getSelectionModel().select(0);
                }
                
                
                draftController.handleEditPlayerRequest(gui,
                        playerTable.getSelectionModel().getSelectedItem());
            }
        });

    }

    /**
     *
     * @param team the team that is to be set
     */
    public void updateScreen(BaseballTeam team) {
        
        //this jsut makes my life easier
        ArrayList<BaseballTeam> teams = dataManager.getDraft().getTeams();
        //if teams isnt empty then we can start doing stuff
        if (!teams.isEmpty()) {
            
            //first we make sure the combo box is enabled
            teamsComboBox.setDisable(false);
            //now we can do stuff to it

            teamsComboBox.getItems().clear();

            teamsComboBox.getItems().addAll(teams);

            //make sure our buttons are ok
            removeTeamButton.setDisable(false);
            editTeamButton.setDisable(false);
            if (team != null) {
                teamsComboBox.getSelectionModel().select(team);
            } else {
                teamsComboBox.getSelectionModel().selectFirst();
            }
            //and now we update the players list
            //team will only be null after team is deleted
            if (playerTable.getItems() != null) {

                //    playerTable.getItems().clear();
            }

            playerTable.setItems(team.getObservablePlayers());

            taxiTable.setItems(team.getObservableTaxiPlayers());
        } else {
            removeTeamButton.setDisable(true);
            editTeamButton.setDisable(true);
            teamsComboBox.setDisable(true);
            playerTable.setItems(null);
            taxiTable.setItems(null);
        }
    }
    public TextField getDraftNameTextField(){
        return draftNameTextField;
    }
}
