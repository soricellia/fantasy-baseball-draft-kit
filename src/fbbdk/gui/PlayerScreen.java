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
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_IMAGES;
import fbbdk.data.DraftDataManager;
import fbbdk.data.Player;
import static fbbdk.gui.HomeScreen.SCREEN_STYLE;
import java.util.Comparator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class PlayerScreen extends BorderPane{
    PropertiesManager properties;
    
    DraftDataManager dataManager;
    
    Label playerHeadingLabel;
   
    //this holds everything
    GridPane pane;
    
    //this holds the top stuff
    BorderPane borderTopPane;
    VBox topPane;
    //toppane is split into two parts
    HBox upperTopPane;
    HBox lowerTopPane;
    
    //these are the components that go in the topPane
    Button plusButton;
    Button minusButton;
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
    TableView<Player> playerTable;
    //and the table colums
    TableColumn<Player,String> firstNameColumn;
    TableColumn<Player,String> lastNameColumn;
    TableColumn<Player,String> proTeamColumn;
    TableColumn<Player,String> positionsColumn;
    TableColumn<Player,Integer> yearOfBirthColumn;
    TableColumn<Player,Integer> winRunColumn;
    TableColumn<Player,Integer> savesHRColumn;
    TableColumn<Player,Integer> kRBIColumn;
    TableColumn<Player,Double> eraSBColumn;
    TableColumn<Player,Double> whipBAColumn;
    TableColumn<Player,Integer> evColumn;
    TableColumn<Player,String> notesColumn;
    
    final static String PLAYER_RADIO_STYLE = "player_radio_style";
    final static String PLAYER_RADIO_PANEL_STYLE = "player_radio_panel_style";
    final static String HEADING_STYLE = "heading_label";
    final static String SUB_HEADING = "subheading_label";
    final static String EMPTY_TEXT = "";
    
    //these are the constants needed for the radio buttons
    private static String ALL = "ALL";
    private static String C = "C";
    private static String FB = "1B";
    private static String SB = "2B";
    private static String TB = "3B";
    private static String CI = "CI";
    private static String SS = "SS";
    private static String MI = "MI";
    private static String OF = "OF";
    private static String U = "U";
    private static String P = "P";
    
    //these will be the constants needed for the table colums
    private static String FIRST_NAME_COLUMN = "First";
    private static String LAST_NAME_COLUMN = "Last";
    private static String PRO_TEAM_COLUMN = "Pro Team";
    private static String POSITIONS_COLUMN = "Positions";
    private static String YEAR_OF_BIRTH_COLUMN = "Year of Birth";
    private static String RUN_WIN_COLUMN = "R/W";
    private static String RUN_COLUMN = "R";
    private static String WIN_COLUMN = "W";
    private static String HR_SV_COLUMN = "HR/SV";
    private static String HR_COLUMN = "HR";
    private static String SV_COLUMN = "SV";
    private static String RBI_K_COLUMN = "RBI/K";
    private static String RBI_COLUMN = "RBI";
    private static String K_COLUMN = "K";
    private static String SB_ERA_COLUMN = "SB/ERA";
    private static String SB_COLUMN = "SB";
    private static String ERA_COLUMN = "ERA";
    private static String BA_WHIP_COLUMN = "BA/WHIP";
    private static String BA_COLUMN = "BA";
    private static String WHIP_COLUMN = "WHIP";
    private static String ESTIMATED_VALUE_COLUMN = "Estimated Value";
    private static String NOTES_COLUMN = "Notes";
    
    
    public PlayerScreen(Scene primaryScene,DraftDataManager dataManager){
        //init the properties manager
        properties = PropertiesManager.getPropertiesManager();
        this.dataManager = dataManager;
        
        //set the class
        this.getStyleClass().add(SCREEN_STYLE);
        
        //init the components
        initComponents();
    }

    private void initComponents() {
        pane = new GridPane();
        
        initHeading(pane);
        
        initRadioButtons(pane);
        
        initTable(pane);
        
        initEventHandlers();
        
        //select the all button and apply the correct table headings
        allRadioButton.setSelected(true);
        setMixedTableHeadings();
        
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
    // INIT A BUTTON AND ADD IT TO A CONTAINER IN A TOOLBAR
    private Button initChildButton(Pane toolbar, Fdk_PropertyType icon, Fdk_PropertyType tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_IMAGES + props.getProperty(icon.toString());
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

    private void initHeading(GridPane pane) {
        //first lets init the topPane
        borderTopPane = new BorderPane();
        topPane = new VBox();
        
        //init the Vboxes
        upperTopPane = new HBox();
        lowerTopPane = new HBox();
        
        //now add the heading label
        playerHeadingLabel = initLabel(PLAYER_SCREEN_HEADING_LABEL,HEADING_STYLE);
        upperTopPane.getChildren().add(playerHeadingLabel);
        
        //now we init the buttons
        //first create the flowpane to add the buttons to
        buttonPane = new FlowPane();
        plusButton = initChildButton(lowerTopPane,ADD_ICON,ADD_ICON_TOOLTIP,false);
        minusButton = initChildButton(lowerTopPane,REMOVE_ICON,REMOVE_ICON_TOOLTIP,false);
        
        searchPanel = new HBox();
        
        searchLabel = initLabel(SEARCH_LABEL,SUB_HEADING);
        
        searchText = initTextField(1,EMPTY_TEXT,true);
        searchText.prefColumnCountProperty().set(60);
        searchPanel.getChildren().addAll(searchLabel,searchText);
        searchPanel.setPadding(new Insets(0,0,0,50));
        searchPanel.setSpacing(10.0);

        
        lowerTopPane.getChildren().add(searchPanel);
        
        
        topPane.getChildren().addAll(upperTopPane,lowerTopPane);
        
        borderTopPane.setCenter(topPane);
       
        pane.add(borderTopPane, 1, 1); 
    }
    private RadioButton initRadioButton(FlowPane pane, String name){
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
        allRadioButton = initRadioButton(midPane,ALL);
        cRadioButton = initRadioButton(midPane, C);
        firstBaseRadioButton = initRadioButton(midPane,FB);
        ciRadioButton = initRadioButton(midPane,CI);
        secondBaseRadioButton = initRadioButton(midPane,SB);
        thirdBaseRadioButton = initRadioButton(midPane,TB);
        miRadioButton = initRadioButton(midPane,MI);
        ssRadioButton = initRadioButton(midPane,SS);
        ofRadioButton = initRadioButton(midPane,OF);
        uRadioButton = initRadioButton(midPane,U);
        pitcherRadioButton = initRadioButton(midPane,P);
        
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
        positionsColumn.setCellValueFactory(new PropertyValueFactory<>("qp"));
        
        yearOfBirthColumn = new TableColumn<>(YEAR_OF_BIRTH_COLUMN);
        yearOfBirthColumn.setEditable(false);
        yearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        
        winRunColumn = new TableColumn<>();
        winRunColumn.setEditable(false);
        
        
        savesHRColumn = new TableColumn<>();
        savesHRColumn.setEditable(false);
        
        kRBIColumn = new TableColumn<>();
        kRBIColumn.setEditable(false);
        
        eraSBColumn = new TableColumn<>();
        eraSBColumn.setEditable(false);
        
        whipBAColumn = new TableColumn<>();
        whipBAColumn.setEditable(false);
        
        evColumn = new TableColumn<>(ESTIMATED_VALUE_COLUMN);
        evColumn.setEditable(false);
        
        notesColumn = new TableColumn<>(NOTES_COLUMN);
        notesColumn.setEditable(true);
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        
        //now we can add all of these bad girls to the table
        playerTable.getColumns().addAll(firstNameColumn,lastNameColumn,proTeamColumn,positionsColumn
                ,yearOfBirthColumn,winRunColumn,savesHRColumn,kRBIColumn,eraSBColumn,whipBAColumn,evColumn,notesColumn);
        
        //add the data to the table
        playerTable.setItems(dataManager.getDraft().getObservablePlayers());
        
        //finally, add the playerTable to the gridPane
        pane.add(playerTable, 1, 3);
        
    }
    public void initEventHandlers(){
        
        allRadioButton.setOnAction(e->{
            allButtonClicked();
            //still need to call controller to sort
        });
        cRadioButton.setOnAction(e->{
            if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
            setCorrectHeadings();
            //still need to call controller to sort
        });
        firstBaseRadioButton.setOnAction(e->{
            if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
            setCorrectHeadings();
            //still need to call controller to sort
        });
        ciRadioButton.setOnAction(e->{
            if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
            setCorrectHeadings();
            //still need to call controller to sort
        });
        secondBaseRadioButton.setOnAction(e->{
           if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
           setCorrectHeadings();
            //still need to call controller to sort
        });
        thirdBaseRadioButton.setOnAction(e->{
           if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
           setCorrectHeadings();
            //still need to call controller to sort
        });
        miRadioButton.setOnAction(e->{
            if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
            setCorrectHeadings();
            //still need to call controller to sort
        });
        ssRadioButton.setOnAction(e->{
            if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
            setCorrectHeadings();
            //still need to call controller to sort
        });
        ofRadioButton.setOnAction(e->{
            if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
            setCorrectHeadings();
            //still need to call controller to sort
        });
        uRadioButton.setOnAction(e->{
            if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
            setCorrectHeadings();
            //still need to call controller to sort
        });
        pitcherRadioButton.setOnAction(e->{
            if(allRadioButton.isSelected())
                allRadioButton.setSelected(false);
            setCorrectHeadings();
            //still need to call controller to sort
        });
        plusButton.setOnAction(e->{
            
        });
        minusButton.setOnAction(e->{
            
        });
        searchText.setOnAction(e->{
            //do stuff
        });
    }
    private void unCheckAllButtons(){
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
    private void allButtonClicked() {
        //first i need to set the column titles
        unCheckAllButtons();
        
        //now make sure all button is selected
        allRadioButton.setSelected(true);
        
        //now i can set the correct columns
        setMixedTableHeadings();
        
    }
    //THIS METHOD WILL SET THE CORRECT HEADINGS FOR THE PLAYER TABLE
    private void setCorrectHeadings(){
        if(pitcherRadioButton.isSelected()&&hitterButtonSelected()){
                setMixedTableHeadings();
            }else{
                if(pitcherRadioButton.isSelected()){
                    setPitcherTableHeadings();
                }else{
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
       if(cRadioButton.isSelected() || firstBaseRadioButton.isSelected() || ciRadioButton.isSelected() || 
               secondBaseRadioButton.isSelected() || thirdBaseRadioButton.isSelected() || miRadioButton.isSelected() || 
               ssRadioButton.isSelected() || ofRadioButton.isSelected() || uRadioButton.isSelected()){
           
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
}
