/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_PropertyType.ADD_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.ADD_ICON_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.PLAYER_SCREEN_HEADING_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_ICON_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.SEARCH_LABEL;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_GUI_IMAGES;
import fbbdk.controller.DraftController;
import fbbdk.controller.PlayerTableController;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.DraftDataManager;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class PlayerScreen extends BorderPane {

    //these are my managers

    PropertiesManager properties;
    DraftDataManager dataManager;

    //this is the GUI
    Fdk_gui gui;
    Stage primaryStage;
    //controllers
    PlayerTableController playerTableController;
    DraftController draftController;

    //my dialogs
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    //this holds everything
    GridPane pane;

    Label playerHeadingLabel;
    //this holds the top stuff
    BorderPane borderTopPane;
    VBox topPane;
    //toppane is split into two parts
    HBox upperTopPane;
    HBox lowerTopPane;

    //these are the components that go in the topPane
    Button addPlayerButton;
    Button removePlayerButton;
    //this is the pane for the buttons
    FlowPane buttonPane;

    HBox searchPanel;
    Label searchLabel;
    TextField searchText;

    //this will hold the middle stuff
    FlowPane midPane;
    //these will be the components that go in the midPane
    RadioButton allRadioButton;
    RadioButton cRadioButton;
    RadioButton firstBaseRadioButton;
    RadioButton ciRadioButton;
    RadioButton secondBaseRadioButton;
    RadioButton thirdBaseRadioButton;
    RadioButton miRadioButton;
    RadioButton ssRadioButton;
    RadioButton ofRadioButton;
    RadioButton uRadioButton;
    RadioButton pitcherRadioButton;

    //this will hold the table
    FlowPane tablePane;
    //and the table that holds all the data
    TableView<BaseballPlayer> playerTable;
    //and the table colums
    TableColumn<BaseballPlayer, String> firstNameColumn;
    TableColumn<BaseballPlayer, String> lastNameColumn;
    TableColumn<BaseballPlayer, String> proTeamColumn;
    TableColumn<BaseballPlayer, String> positionsColumn;
    TableColumn<BaseballPlayer, Integer> yearOfBirthColumn;
    TableColumn<BaseballPlayer, Integer> winRunColumn;
    TableColumn<BaseballPlayer, Integer> savesHRColumn;
    TableColumn<BaseballPlayer, Integer> kRBIColumn;
    TableColumn<BaseballPlayer, Double> eraSBColumn;
    TableColumn<BaseballPlayer, Double> whipBAColumn;
    TableColumn<BaseballPlayer, Integer> evColumn;
    TableColumn<BaseballPlayer, String> notesColumn;

    private static final String PLAYER_RADIO_STYLE = "player_radio_style";
    private static final String PLAYER_RADIO_PANEL_STYLE = "player_radio_panel_style";
    private static final String SCREEN_STYLE = "screen";
    private static final String PADDING_STYLE = "padding";
    public static final String HEADING_STYLE = "heading_label";
    public static final String SUB_HEADING = "subheading_label";
    public static final String EMPTY_TEXT = "";

    //these are the constants needed for the radio buttons
    private static final String ALL = "ALL";
    private static final String C = "C";
    private static final String FB = "1B";
    private static final String SB = "2B";
    private static final String TB = "3B";
    private static final String CI = "CI";
    private static final String SS = "SS";
    private static final String MI = "MI";
    private static final String OF = "OF";
    private static final String U = "U";
    private static final String P = "P";

    //these will be the constants needed for the table colums
    private static final String FIRST_NAME_COLUMN = "First";
    private static final String LAST_NAME_COLUMN = "Last";
    private static final String PRO_TEAM_COLUMN = "Pro Team";
    private static final String POSITIONS_COLUMN = "Positions";
    private static final String YEAR_OF_BIRTH_COLUMN = "Year of Birth";
    private static final String RUN_WIN_COLUMN = "R/W";
    private static final String RUN_COLUMN = "R";
    private static final String WIN_COLUMN = "W";
    private static final String HR_SV_COLUMN = "HR/SV";
    private static final String HR_COLUMN = "HR";
    private static final String SV_COLUMN = "SV";
    private static final String RBI_K_COLUMN = "RBI/K";
    private static final String RBI_COLUMN = "RBI";
    private static final String K_COLUMN = "K";
    private static final String SB_ERA_COLUMN = "SB/ERA";
    private static final String SB_COLUMN = "SB";
    private static final String ERA_COLUMN = "ERA";
    private static final String BA_WHIP_COLUMN = "BA/WHIP";
    private static final String BA_COLUMN = "BA";
    private static final String WHIP_COLUMN = "WHIP";
    private static final String ESTIMATED_VALUE_COLUMN = "Estimated Value";
    private static final String NOTES_COLUMN = "Notes";

    public PlayerScreen(Fdk_gui initGui, Stage initPrimaryStage,
            MessageDialog initMessageDialog,YesNoCancelDialog initYesNoCancelDialog) {
        //init the important stuff
        properties = PropertiesManager.getPropertiesManager();
        gui = initGui;
        dataManager = gui.getDataManager();
        primaryStage = initPrimaryStage;
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
        //init the playerTableControler
        playerTableController = new PlayerTableController(dataManager);
        draftController = new DraftController(primaryStage,dataManager.getDraft(),
                messageDialog,yesNoCancelDialog);
        
        //set the class
        this.getStyleClass().add(SCREEN_STYLE);

        //init the components
        initComponents();
    }

    private void initComponents() {
        pane = new GridPane();
        pane.getStyleClass().addAll(PADDING_STYLE, SCREEN_STYLE);

        initHeading(pane);

        initRadioButtons(pane);

        initTable(pane);

        initEventHandlers();

        //select the all button and apply the correct table headings
        allRadioButton.setSelected(true);
        setMixedTableHeadings();

        this.setCenter(pane);
    }

    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(Fdk_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }

    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, Fdk_PropertyType icon, Fdk_PropertyType tooltip, boolean disabled) {
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

    /**
     *
     * @return returns all positions selected in radio buttons
     */
    private ArrayList<String> getSelectedPositions() {
        ArrayList<String> positions = new ArrayList<>();
        if (cRadioButton.isSelected()) {
            positions.add(C);
        }
        if (firstBaseRadioButton.isSelected()) {
            positions.add(FB);
        }
        if (ciRadioButton.isSelected()) {
            //cornor infielder can either be first or third baseman
            positions.add(FB);
            positions.add(TB);
        }
        if (secondBaseRadioButton.isSelected()) {
            positions.add(SB);
        }
        if (thirdBaseRadioButton.isSelected()) {
            positions.add(TB);
        }
        if (miRadioButton.isSelected()) {
            //middle infielder can either be second baseman or shortstop
            positions.add(SB);
            positions.add(SS);
        }
        if (ssRadioButton.isSelected()) {
            positions.add(SS);
        }
        if (ofRadioButton.isSelected()) {
            positions.add(OF);
        }
        if (uRadioButton.isSelected()) {
            //ok for utility we are going to have to
            //clear the positions list then add everything
            positions.clear();
            String[] list = {FB, SB, TB, MI, SS, C, OF, CI};
            for (int x = 0; x < list.length; x++) {
                positions.add(list[x]);
            }

        }
        if (pitcherRadioButton.isSelected()) {
            positions.add(P);
        }

        return positions;
    }

    private void initHeading(GridPane pane) {
        //first lets init the topPane
        borderTopPane = new BorderPane();
        topPane = new VBox();
        //init the Vboxes
        upperTopPane = new HBox();
        lowerTopPane = new HBox();

        //now add the heading label
        playerHeadingLabel = initLabel(PLAYER_SCREEN_HEADING_LABEL, HEADING_STYLE);
        upperTopPane.getChildren().add(playerHeadingLabel);

        //now we init the buttons
        //first create the flowpane to add the buttons to
        buttonPane = new FlowPane();
        addPlayerButton = initChildButton(lowerTopPane, ADD_ICON, ADD_ICON_TOOLTIP, false);
        removePlayerButton = initChildButton(lowerTopPane, REMOVE_ICON, REMOVE_ICON_TOOLTIP, false);

        searchPanel = new HBox();

        searchLabel = initLabel(SEARCH_LABEL, SUB_HEADING);

        searchText = initTextField(1, EMPTY_TEXT, true);
        searchText.prefColumnCountProperty().set(70);

        searchPanel.getChildren().addAll(searchLabel, searchText);
        searchPanel.setPadding(new Insets(0, 0, 0, 50));
        searchPanel.setSpacing(10.0);

        lowerTopPane.getChildren().add(searchPanel);

        topPane.getChildren().addAll(upperTopPane, lowerTopPane);

        borderTopPane.setCenter(topPane);

        pane.add(borderTopPane, 1, 1);
    }

    private RadioButton initRadioButton(FlowPane pane, String name) {
        RadioButton button = new RadioButton();
        button.setText(name);
        button.getStyleClass().add(PLAYER_RADIO_STYLE);
        pane.getChildren().add(button);

        return button;
    }

    private void initRadioButtons(GridPane pane) {
        //first init the midpane
        midPane = new FlowPane();
        //these will be the components that go in the midPane
        allRadioButton = initRadioButton(midPane, ALL);
        cRadioButton = initRadioButton(midPane, C);
        firstBaseRadioButton = initRadioButton(midPane, FB);
        ciRadioButton = initRadioButton(midPane, CI);
        secondBaseRadioButton = initRadioButton(midPane, SB);
        thirdBaseRadioButton = initRadioButton(midPane, TB);
        miRadioButton = initRadioButton(midPane, MI);
        ssRadioButton = initRadioButton(midPane, SS);
        ofRadioButton = initRadioButton(midPane, OF);
        uRadioButton = initRadioButton(midPane, U);
        pitcherRadioButton = initRadioButton(midPane, P);

        //ok now set the style
        midPane.getStyleClass().add(PLAYER_RADIO_PANEL_STYLE);

        //add to the gridpane
        pane.add(midPane, 1, 2);
    }

    private void initTable(GridPane pane) {
        //first init the player table
        playerTable = new TableView<>();

        //and the table colums
        //note that some of the column headings are not set
        //this will be done later in an action listener
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

        yearOfBirthColumn = new TableColumn<>(YEAR_OF_BIRTH_COLUMN);
        yearOfBirthColumn.setEditable(false);
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        winRunColumn = new TableColumn<>();
        winRunColumn.setEditable(false);
        winRunColumn.setCellValueFactory(new PropertyValueFactory<>("winRun"));

        savesHRColumn = new TableColumn<>();
        savesHRColumn.setEditable(false);
        savesHRColumn.setCellValueFactory(new PropertyValueFactory<>("savesHR"));

        kRBIColumn = new TableColumn<>();
        kRBIColumn.setEditable(false);
        kRBIColumn.setCellValueFactory(new PropertyValueFactory<>("kRBI"));

        eraSBColumn = new TableColumn<>();
        eraSBColumn.setEditable(false);
        eraSBColumn.setCellValueFactory(new PropertyValueFactory<>("eraSB"));

        whipBAColumn = new TableColumn<>();
        whipBAColumn.setEditable(false);
        whipBAColumn.setCellValueFactory(new PropertyValueFactory<>("whipBA"));

        evColumn = new TableColumn<>(ESTIMATED_VALUE_COLUMN);
        evColumn.setEditable(false);
        evColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedValue"));
        notesColumn = new TableColumn<>(NOTES_COLUMN);
        notesColumn.setEditable(true);
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        //this allows you to type into the notes column on doubleclick
        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //we want to notes column a little thicker
        notesColumn.setPrefWidth(350);

        //now we can add all of these bad girls to the table
        playerTable.getColumns().addAll(firstNameColumn, lastNameColumn,
                proTeamColumn, positionsColumn, yearOfBirthColumn, winRunColumn,
                savesHRColumn, kRBIColumn, eraSBColumn, whipBAColumn, evColumn,
                notesColumn);
        //make the playerTable editable
        playerTable.setEditable(true);
        //add the data to the table
        playerTable.setItems(dataManager.getDraft().getObservablePlayers());
        //ok now lets make the playerTable a bit bigger
        playerTable.setPrefHeight(500);

        //finally, add the playerTable to the gridPane
        pane.add(playerTable, 1, 3);

    }

    private void initEventHandlers() {

        allRadioButton.setOnAction(e -> {
            allButtonClicked();
            playerTableController.handleAllSearch();
        });
        cRadioButton.setOnAction(e -> {
            radioButtonCheck();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        firstBaseRadioButton.setOnAction(e -> {
            radioButtonCheck();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        ciRadioButton.setOnAction(e -> {
            radioButtonCheck();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        secondBaseRadioButton.setOnAction(e -> {
            radioButtonCheck();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        thirdBaseRadioButton.setOnAction(e -> {
            radioButtonCheck();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        miRadioButton.setOnAction(e -> {
            radioButtonCheck();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        ssRadioButton.setOnAction(e -> {
            radioButtonCheck();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        ofRadioButton.setOnAction(e -> {
            radioButtonCheck();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });

        uRadioButton.setOnAction(e -> {
            uButtonClicked();
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        pitcherRadioButton.setOnAction(e -> {
            if (allRadioButton.isSelected()) {
                allRadioButton.setSelected(false);
            }
            setCorrectHeadings();
            playerTableController.handleRadioSearch(getSelectedPositions());
        });
        addPlayerButton.setOnAction(e -> {
            draftController.handleAddPlayerRequest(gui);
        });
        removePlayerButton.setOnAction(e -> {

        });
        searchText.setOnKeyReleased(e -> {
            playerTableController.handleSearchTextRequest(searchText.getText());
        });
        notesColumn.setOnEditCommit(e -> {
            e.getRowValue().setNotes(e.getNewValue());
        });

    }

    private void unCheckAllButtons() {
        allRadioButton.setSelected(false);
        cRadioButton.setSelected(false);
        firstBaseRadioButton.setSelected(false);
        ciRadioButton.setSelected(false);
        secondBaseRadioButton.setSelected(false);
        thirdBaseRadioButton.setSelected(false);
        miRadioButton.setSelected(false);
        ssRadioButton.setSelected(false);
        ofRadioButton.setSelected(false);
        uRadioButton.setSelected(false);
        pitcherRadioButton.setSelected(false);

    }

    private void uButtonClicked() {
        if (pitcherRadioButton.isSelected()) {
            //this means pitcher is selected and uradiobutton isnt selected
            if (!uRadioButton.isSelected()) {
                unCheckAllButtons();
                //so just select the pitcher
                pitcherRadioButton.setSelected(true);
            } else {
                //this means both pitcher and uradioButton are selected
                unCheckAllButtons();
                pitcherRadioButton.setSelected(true);
                uRadioButton.setSelected(true);
            }
        } else {
            //lets check if uradioButton is selected or not
            if (uRadioButton.isSelected()) {
                //this means that pitcher isnt selected and uradioButton is
                //so lets uncheck all other boxes and then recheck uRadioButton
                unCheckAllButtons();
                uRadioButton.setSelected(true);
            } else {
                //this case means that neither pitcher nor uradioButton is
                //selected. lets uncheck all buttons
                unCheckAllButtons();
            }
        }
    }

    private void allButtonClicked() {
        //first i need to set the column titles
        unCheckAllButtons();

        //now make sure all button is selected
        allRadioButton.setSelected(true);

        //now i can set the correct columns
        setMixedTableHeadings();

    }

    //THIS METHOD WILL SET THE CORRECT HEADINGS FOR THE PLAYER TABLE
    private void setCorrectHeadings() {
        if (pitcherRadioButton.isSelected() && hitterButtonSelected()) {
            setMixedTableHeadings();
        } else {
            if (pitcherRadioButton.isSelected()) {
                setPitcherTableHeadings();
            } else {
                setHitterTableHeadings();
            }
        }
    }

    //SETS ALL TABLE HEADINGS TO PITCHER AND HITTER HEADINGS
    private void setMixedTableHeadings() {
        winRunColumn.setText(RUN_WIN_COLUMN);
        savesHRColumn.setText(HR_SV_COLUMN);
        kRBIColumn.setText(RBI_K_COLUMN);
        eraSBColumn.setText(SB_ERA_COLUMN);
        whipBAColumn.setText(BA_WHIP_COLUMN);
    }

    //SETS ALL TABLE HEADINGS TO HITTER TABLE HEADINGS
    private void setHitterTableHeadings() {
        winRunColumn.setText(RUN_COLUMN);
        savesHRColumn.setText(HR_COLUMN);
        kRBIColumn.setText(RBI_COLUMN);
        eraSBColumn.setText(SB_COLUMN);
        whipBAColumn.setText(BA_COLUMN);
    }

    //THIS METHOD WILL RETURN TRUE IF ANY HITTER RADIO BUTTON IS SELECTED
    //FALSE OTHERWISE
    private boolean hitterButtonSelected() {
        if (cRadioButton.isSelected() || firstBaseRadioButton.isSelected() || ciRadioButton.isSelected()
                || secondBaseRadioButton.isSelected() || thirdBaseRadioButton.isSelected() || miRadioButton.isSelected()
                || ssRadioButton.isSelected() || ofRadioButton.isSelected() || uRadioButton.isSelected()) {

            return true;
        }

        return false;
    }

    //SETS THE PLAYER TABLE TO PITCHER HEADINGS
    private void setPitcherTableHeadings() {
        winRunColumn.setText(WIN_COLUMN);
        savesHRColumn.setText(SV_COLUMN);
        kRBIColumn.setText(K_COLUMN);
        eraSBColumn.setText(ERA_COLUMN);
        whipBAColumn.setText(WHIP_COLUMN);
    }

    private void radioButtonCheck() {
        //we dont want these guys selected at first
        if (allRadioButton.isSelected()) {
            allRadioButton.setSelected(false);
        }
        if (uRadioButton.isSelected()) {
            uRadioButton.setSelected(false);
        }

        if (ssRadioButton.isSelected() && secondBaseRadioButton.isSelected()) {
            miRadioButton.setSelected(true);
        }
        if (miRadioButton.isSelected()) {
            //if this button is selected we dont want ss and 2b selected
            //because they mean the same thing
            ssRadioButton.setSelected(false);
            secondBaseRadioButton.setSelected(false);
        }
        if (firstBaseRadioButton.isSelected()
                && thirdBaseRadioButton.isSelected()) {
            ciRadioButton.setSelected(true);
        }
        if (ciRadioButton.isSelected()) {
            //if this button is selected we dont want 1b or 3b selected
            //because they mean the same thing
            firstBaseRadioButton.setSelected(false);
            thirdBaseRadioButton.setSelected(false);
        }
        //ok, now if there is nothing selected we are going to want the
        //all button selected
        if (getSelectedPositions().isEmpty()) {
            allRadioButton.setSelected(true);
        }
    }

}
