/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_PropertyType.ADD_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.ADD_ICON_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.ADD_TEAM_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.ADD_TEAM_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_SEARCH_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.EDIT_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.EDIT_ICON_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.HOME_SCREEN_HEADING_LABEL;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_ICON_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_TEAM_ICON;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_TEAM_TOOLTIP;
import static fantasybaseballdraftkit.Fdk_PropertyType.SELECT_TEAM_LABEL;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_CSS;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_IMAGES;
import fbbdk.data.DraftDataManager;
import static fbbdk.gui.PlayerScreen.EMPTY_TEXT;
import static fbbdk.gui.PlayerScreen.SUB_HEADING;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class HomeScreen extends BorderPane{
    
    //this is the heading label
    Label teamsHeadingLabel;
    
    //put the grid pane in this 
    ScrollPane scrollPane;
     //this holds everything
    GridPane pane;

    //this holds the top stuff
    BorderPane borderTopPane;
    VBox topPane;
    //toppane is split into two parts
    HBox upperTopPane;
    HBox middleTopPane;
    HBox lowerTopPane;

    //these are the components that go in the topPane
    Button plusButton;
    Button minusButton;
    Button editDraftButton;
    Label selectTeamLabel;
    ComboBox teamsComboBox;
    

    HBox searchPanel;
    Label searchLabel;
    TextField searchText;
    
    //this is for getting all of my labels
    PropertiesManager propertiesManager;
    DraftDataManager dataManager;
    final static String HEADING_LABEL = "heading_label";
    final static String SCREEN_STYLE = "screen";
    final String TOP_HOME = "top_home";
    
    public HomeScreen(Scene primaryScene, DraftDataManager ddm){
        //first call super
        super();
        //set the class
        this.getStyleClass().add(SCREEN_STYLE);
        //init the prop manager
        propertiesManager = PropertiesManager.getPropertiesManager();
        dataManager = ddm;
        //init the components
        initComponents();
        
    }

    private void initComponents() {
        scrollPane = new ScrollPane();
        pane = new GridPane();
        //search bar and things go here
        initTopPane();
        //all the players go here
        initPlayerPane();
        //the taxi players table go here
        initTaxiPlane();
        //add the gridPane to the scrollPane
       scrollPane.setContent(pane);
       //now set homescreen to the scroll pane
       this.setCenter(pane);
    }
    
    private void initTopPane(){
        //everything in the top pane goes here
        topPane = new VBox();
        topPane.getStyleClass().add(TOP_HOME);
        //the heading label is going to go here
        upperTopPane = new HBox();
        teamsHeadingLabel = initLabel(HOME_SCREEN_HEADING_LABEL,HEADING_LABEL);
        upperTopPane.getChildren().add(teamsHeadingLabel);
        //the search bar stuff goes here
        middleTopPane = new HBox();
        searchLabel = initLabel(DRAFT_SEARCH_LABEL,SUB_HEADING);
        searchText = initTextField(25,EMPTY_TEXT,true);
        middleTopPane.getChildren().addAll(searchLabel,searchText);
        //now the buttons
        lowerTopPane = new HBox();
        plusButton = initChildButton(lowerTopPane, ADD_TEAM_ICON,ADD_TEAM_TOOLTIP,false);
        minusButton = initChildButton(lowerTopPane, REMOVE_TEAM_ICON,REMOVE_TEAM_TOOLTIP,true);
        editDraftButton = initChildButton(lowerTopPane, EDIT_ICON,EDIT_ICON_TOOLTIP,true);
        selectTeamLabel = initLabel(SELECT_TEAM_LABEL,SUB_HEADING);
        teamsComboBox = new ComboBox(dataManager.getDraft().getTeamNames());
        lowerTopPane.getChildren().addAll(selectTeamLabel,teamsComboBox);
        //now add them all to the topPane VBox
        topPane.getChildren().addAll(upperTopPane,middleTopPane,lowerTopPane);
        //and finally add the topPane to the gridPane
        pane.add(topPane, 1, 1);
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

    private void initPlayerPane() {
        
    }

    private void initTaxiPlane() {
    
    }
    
}
