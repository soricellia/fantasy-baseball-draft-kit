/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_PropertyType.AUTO_DRAFT_PLAYER_BUTTON_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.AUTO_DRAFT_PLAYER_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_ONE_PLAYER_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_PLAYER_BUTTON_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_PLAYER_CONTROL_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_SCREEN_HEADING_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.STOP_AUTO_DRAFT_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.STOP_AUTO_DRAFT_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_GUI_IMAGES;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.BaseballTeam;
import fbbdk.data.Draft;
import fbbdk.data.DraftDataManager;
import fbbdk.data.Pick;
import static fbbdk.gui.EditPlayerDialog.UNDER_SCORE;
import static fbbdk.gui.FantasyStandingScreen.PADDING_STYLE;
import static fbbdk.gui.HomeScreen.SCREEN_STYLE;
import static fbbdk.gui.PlayerScreen.SUB_HEADING;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class DraftScreen extends BorderPane {

    PropertiesManager properties;
    DraftDataManager dataManager;
    YesNoCancelDialog yesNoCancelDialog;
    MessageDialog messageDialog;

    Label draftHeadingLabel;

    GridPane pane;

    VBox controlPane;
    Label controlLabel;

    HBox buttonPane;
    Button addPlayerButton;
    Button startAutoDraftButton;
    Button stopAutoDraftButton;
    boolean autoDraft;

    TableView<Pick> pickTable;
    //and the table colums
    TableColumn<Pick, Integer> pickOrderColumn;
    TableColumn<Pick, String> firstNameColumn;
    TableColumn<Pick, String> lastNameColumn;
    TableColumn<Pick, String> proTeamColumn;
    TableColumn<Pick, String> positionColumn;
    TableColumn<Pick, String> contractColumn;
    TableColumn<Pick, Integer> salaryColumn;
    TableColumn<Pick, Integer> estimatedValueColumn;

    //these will be the constants needed for the table colums
    private static final String PICK_ORDER_COLUMN = "Pick #";
    private static final String FIRST_NAME_COLUMN = "First";
    private static final String LAST_NAME_COLUMN = "Last";
    private static final String PRO_TEAM_COLUMN = "Team";
    private static final String CONTRACT_COLUMN = "Contract";
    private static final String SALARY_COLUMN = "Salary";
    private static final String EV_COLUMN = "Estimated Value";

    final static String HEADING_STYLE = "heading_label";

    Fdk_gui gui;

    public DraftScreen(Fdk_gui initGui, Stage primaryStage,
            MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        //init the properties Manager
        properties = PropertiesManager.getPropertiesManager();
        dataManager = initGui.getDataManager();
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        gui = initGui;
        //set the style class
        this.getStyleClass().add(SCREEN_STYLE);

        //init the components
        initComponents();

        //set up event handlers
        initEventHandlers();
    }

    private void initTable(GridPane pane) {
        //first init the player table
        pickTable = new TableView<>();

        //and the table colums
        //note that some of the column headings are not set
        //this will be done later in an action listener
        pickOrderColumn = new TableColumn<>(PICK_ORDER_COLUMN);
        pickOrderColumn.setCellValueFactory(new PropertyValueFactory<>("pickOrder"));
        pickOrderColumn.setPrefWidth(150);

        firstNameColumn = new TableColumn<>(FIRST_NAME_COLUMN);
        firstNameColumn.setEditable(false);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameColumn.setPrefWidth(150);

        lastNameColumn = new TableColumn<>(LAST_NAME_COLUMN);
        lastNameColumn.setEditable(false);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameColumn.setPrefWidth(150);

        proTeamColumn = new TableColumn<>(PRO_TEAM_COLUMN);
        proTeamColumn.setEditable(false);
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        proTeamColumn.setPrefWidth(150);

        positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionColumn.setPrefWidth(150);

        contractColumn = new TableColumn<>(CONTRACT_COLUMN);
        contractColumn.setEditable(false);
        contractColumn.setCellValueFactory(new PropertyValueFactory<>("contract"));
        contractColumn.setPrefWidth(150);

        salaryColumn = new TableColumn<>(SALARY_COLUMN);
        salaryColumn.setEditable(false);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryColumn.setPrefWidth(150);

        estimatedValueColumn = new TableColumn<>(EV_COLUMN);
        estimatedValueColumn.setEditable(false);
        estimatedValueColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedValue"));
        estimatedValueColumn.setPrefWidth(150);

        //now we can add all of these bad girls to the table
        pickTable.getColumns().addAll(pickOrderColumn, firstNameColumn, lastNameColumn,
                proTeamColumn, positionColumn, contractColumn, salaryColumn, estimatedValueColumn);
        //make the pickTable editable
        pickTable.setEditable(true);
        //add the data to the table
        pickTable.setItems(dataManager.getDraft().getPickOrder());
        //ok now lets make the pickTable a bit bigger
        pickTable.setPrefHeight(800);

        pickTable.setStyle("-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.9) , 5, 0.0 , 0 , 1 );");
        //finally, add the pickTable to the gridPane
        pane.add(pickTable, 1, 3);
        GridPane.setHgrow(pickTable, Priority.ALWAYS);

    }

    //2033400830
    private void initComponents() {
        //init gridpane
        pane = new GridPane();
        pane.getStyleClass().add(PADDING_STYLE);
        //add the screen heading
        draftHeadingLabel = initGridLabel(pane, DRAFT_SCREEN_HEADING_LABEL, HEADING_STYLE, 1, 1, 1, 1);

        //lets make the control pane
        controlPane = new VBox();
        controlPane.setPadding(new Insets(15, 15, 15, 15));
        controlPane.setSpacing(5);

        //give the control pane a label
        controlLabel = new Label(properties.getProperty(DRAFT_PLAYER_CONTROL_LABEL.toString()));
        controlLabel.getStyleClass().add(SUB_HEADING);

        //set our buttons
        buttonPane = new HBox();
        addPlayerButton = initChildButton(buttonPane, DRAFT_ONE_PLAYER_ICON,
                DRAFT_PLAYER_BUTTON_TOOLTIP, false);

        startAutoDraftButton = initChildButton(buttonPane, AUTO_DRAFT_PLAYER_ICON,
                AUTO_DRAFT_PLAYER_BUTTON_TOOLTIP, false);

        stopAutoDraftButton = initChildButton(buttonPane, STOP_AUTO_DRAFT_ICON,
                STOP_AUTO_DRAFT_TOOLTIP, true);

        //now add everything to the controlPane at once
        controlPane.getChildren().addAll(controlLabel, buttonPane);

        pane.add(controlPane, 1, 2);
        //create the table and add it to the pane
        initTable(pane);

        this.setCenter(pane);
    }

    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, Fdk_PropertyType icon, Fdk_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_GUI_IMAGES + props.getProperty(icon.toString());
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        button.setPrefSize(50, 50);
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }

    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, Fdk_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }

    public void updateTable() {
        pickTable.setItems(dataManager.getDraft().getPickOrder());
    }

    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(Fdk_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }

    private void initEventHandlers() {
        addPlayerButton.setOnAction(e -> {
            Draft draft = dataManager.getDraft();
            if (!draft.playerDraftEnded()) {
                boolean drafted = false;
                for (int x = 0; x < draft.getTeams().size(); x++) {
                    if (draft.getTeams().get(x).needsMorePlayers()) {
                        if (draft.getTeams().get(x).needsMoreHitters()) {
                            //we are going to draft a hitter
                            BaseballPlayer player
                                    = draft.getHighestHitterEstValue(draft.getTeams().get(x));
                            //first we are going to do the data stuff
                            player.setPosition(getPlayerPosition(draft.getTeams().get(x), player));
                            player.setContract("S2");
                            player.setSalary(1);
                            draft.getTeams().get(x).addPlayer(player);

                            //now we do the visual stuff
                            Pick pick = new Pick(draft.getPickOrder().size() + 1,
                                    player.getFirstName(), player.getLastName(), draft.getTeams().get(x).getTeamName(),
                                    player.getPosition(), player.getContract(), player.getSalary(), player.getEstimatedValue());

                            draft.addPick(pick);
                            //make sure to update totalpoints
                            draft.calculateTotalPoints();
                            draft.getObservableTeams().clear();
                            draft.getObservableTeams().setAll(draft.getTeams());
                            draft.removePlayer(player);
                            gui.getHomeScreen().updateScreen(draft.getTeams().get(x));
                            drafted = true;

                        } else if (draft.getTeams().get(x).needsMorePitchers()) {
                            //we are going to draft a pitcher
                            BaseballPlayer player
                                    = draft.getHighestPitcherEstValue(draft.getTeams().get(x));
                            //first we do the data stuff
                            player.setPosition(player.getPositions());
                            player.setContract("S2");
                            player.setSalary(1);
                            draft.getTeams().get(x).addPlayer(player);

                            //now the visual stuff
                            Pick pick = new Pick(draft.getPickOrder().size() + 1,
                                    player.getFirstName(), player.getLastName(), draft.getTeams().get(x).getTeamName(),
                                    player.getPosition(), player.getContract(), player.getSalary(), player.getEstimatedValue());

                            draft.addPick(pick);
                            //make sure to update totalpoints
                            draft.calculateTotalPoints();
                            draft.getObservableTeams().clear();
                            draft.getObservableTeams().setAll(draft.getTeams());
                            draft.removePlayer(player);
                            drafted = true;

                        }
                        if (drafted) {
                            return;
                        }
                    }
                }
            } else {
                boolean drafted = false;
                for (int x = 0; x < draft.getTeams().size(); x++) {
                    if (draft.getTeams().get(x).needsMoreTaxiDraftPlayers()) {
                        //now we are going to draft a taxiPlayer
                        //we are going to draft a pitcher
                        BaseballPlayer player
                                = draft.getHighestPitcherEstValue(draft.getTeams().get(x));
                        //first we do the data stuff
                        player.setPosition(player.getPositions());
                        player.setContract("X");
                        //we dont want any salary, this is a taxi pick
                        player.setSalary(0);
                        draft.getTeams().get(x).addTaxiPlayer(player);

                        //now the visual stuff
                        Pick pick = new Pick(draft.getPickOrder().size() + 1,
                                player.getFirstName(), player.getLastName(), draft.getTeams().get(x).getTeamName(),
                                player.getPositions(), player.getContract(), player.getSalary(), player.getEstimatedValue());

                        draft.addPick(pick);
                        //make sure to update totalpoints
                        draft.calculateTotalPoints();
                        draft.getObservableTeams().clear();
                        draft.getObservableTeams().setAll(draft.getTeams());
                        draft.removePlayer(player);
                        drafted = true;
                    }
                    if (drafted) {
                        x = draft.getTeams().size();
                    }
                }
            }
        }
        );
        startAutoDraftButton.setOnAction(e -> {
            //first we enable the stop button
            stopAutoDraftButton.setDisable(false);
            startAutoDraftButton.setDisable(true);
            autoDraft = true;
            Task task = new Task() {

                @Override
                protected Object call() throws Exception {
                    Draft draft = dataManager.getDraft();
                    while (autoDraft) {
                      
                        if (!draft.playerDraftEnded()) {
                            boolean drafted = false;
                            for (int x = 0; x < draft.getTeams().size(); x++) {
                                if (draft.getTeams().get(x).needsMorePlayers()) {
                                    if (draft.getTeams().get(x).needsMoreHitters()) {
                                        //we are going to draft a hitter
                                        BaseballPlayer player
                                                = draft.getHighestHitterEstValue(draft.getTeams().get(x));
                                        //first we are going to do the data stuff
                                        player.setPosition(getPlayerPosition(draft.getTeams().get(x), player));
                                        player.setContract("S2");

                                        player.setPositions(player.getPositions());
                                        player.setSalary(1);
                                        draft.getTeams().get(x).addPlayer(player);

                                        //now we do the visual stuff
                                        Pick pick = new Pick(draft.getPickOrder().size() + 1,
                                                player.getFirstName(), player.getLastName(), draft.getTeams().get(x).getTeamName(),
                                                player.getPosition(), player.getContract(), player.getSalary(), player.getEstimatedValue());

                                        draft.addPick(pick);
                                        //make sure to update totalpoints
                                        draft.calculateTotalPoints();
                                        draft.getObservableTeams().clear();
                                        draft.getObservableTeams().setAll(draft.getTeams());
                                        draft.removePlayer(player);
                                        drafted = true;
                                    } else if (draft.getTeams().get(x).needsMorePitchers()) {
                                        //we are going to draft a pitcher
                                        BaseballPlayer player
                                                = draft.getHighestPitcherEstValue(draft.getTeams().get(x));
                                        //first we do the data stuff
                                        player.setPosition(player.getPositions());
                                        player.setPositions(player.getPositions());
                                        player.setContract("S2");
                                        player.setSalary(1);
                                        draft.getTeams().get(x).addPlayer(player);

                                        //now the visual stuff
                                        Pick pick = new Pick(draft.getPickOrder().size() + 1,
                                                player.getFirstName(), player.getLastName(), draft.getTeams().get(x).getTeamName(),
                                                player.getPosition(), player.getContract(), player.getSalary(), player.getEstimatedValue());

                                        draft.addPick(pick);
                                        //make sure to update totalpoints
                                        draft.calculateTotalPoints();
                                        draft.getObservableTeams().clear();
                                        draft.getObservableTeams().setAll(draft.getTeams());
                                        draft.removePlayer(player);
                                        drafted = true;
                                    }

                                }
                                if (drafted) {
                                    x = draft.getTeams().size();
                                }
                            }
                        } else {
                            boolean drafted = false;
                            for (int x = 0; x < draft.getTeams().size(); x++) {
                                if (draft.getTeams().get(x).needsMoreTaxiDraftPlayers()) {
                                    //now we are going to draft a taxiPlayer
                                    //we are going to draft a pitcher
                                    BaseballPlayer player
                                            = draft.getHighestPitcherEstValue(draft.getTeams().get(x));
                                    //first we do the data stuff
                                    player.setPosition(player.getPositions());
                                    player.setContract("X");
                                    //we dont want any salary, this is a taxi pick
                                    player.setSalary(0);
                                    draft.getTeams().get(x).addTaxiPlayer(player);
                                    
                                    //now the visual stuff
                                    Pick pick = new Pick(draft.getPickOrder().size() + 1,
                                            player.getFirstName(), player.getLastName(), draft.getTeams().get(x).getTeamName(),
                                            player.getPositions(), player.getContract(), player.getSalary(), player.getEstimatedValue());

                                    draft.addPick(pick);
                                    //make sure to update totalpoints
                                    draft.calculateTotalPoints();
                                    draft.getObservableTeams().clear();
                                    draft.getObservableTeams().setAll(draft.getTeams());
                                    draft.removePlayer(player);
                                    drafted = true;
                                }
                                if (drafted) {
                                    x = draft.getTeams().size();
                                }
                            }
                        }
                        
                        Thread.sleep(50);
                    }
                    return "";
                }
            };
            Thread thread = new Thread(task);
            thread.start();
        });
        stopAutoDraftButton.setOnAction(e -> {
            stopAutoDraftButton.setDisable(true);
            startAutoDraftButton.setDisable(false);
            autoDraft = false;
        });
    }

    private String getPlayerPosition(BaseballTeam team, BaseballPlayer player) {
        ArrayList<String> positions = new ArrayList<>();
        StringTokenizer tokens = new StringTokenizer(player.getPositions(), UNDER_SCORE);
        ArrayList<String> neededPositions = new ArrayList<>();
        //build the list
        while (tokens.hasMoreTokens()) {
            positions.add(tokens.nextToken());
        }
        //now we will get the positions
        StringTokenizer teamsNeededPositions = new StringTokenizer(
                team.getNeededPlayerPositions(), UNDER_SCORE);

        //and now we have to compare positions
        while (teamsNeededPositions.hasMoreTokens()) {
            String neededPos = teamsNeededPositions.nextToken();
            for (int x = 0; x < positions.size(); x++) {
                if (neededPos.equals("CI")) {
                    if (positions.contains("1B")) {
                        return "CI";
                    } else if (neededPos.contains("3B")) {
                        return "CI";
                    }
                } else if (neededPos.equals("MI")) {
                    if (positions.contains("SS")) {
                        return "MI";
                    } else if (positions.contains("2B")) {
                        return "MI";
                    }
                } else if (neededPos.equals("U")) {
                    return "U";
                } else if (positions.get(x).equals(neededPos)) {
                    return neededPos;
                }
            }
        }
        //this case should really never happen
        return "";
        //and now we on
    }
}
