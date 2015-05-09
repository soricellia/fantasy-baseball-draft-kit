/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_SCREEN_HEADING_LABEL;
import fbbdk.data.DraftDataManager;
import fbbdk.data.Pick;
import static fbbdk.gui.HomeScreen.SCREEN_STYLE;
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
public class DraftScreen extends BorderPane {

    PropertiesManager properties;
    DraftDataManager dataManager;
    YesNoCancelDialog yesNoCancelDialog;
    MessageDialog messageDialog;

    Label draftHeadingLabel;

    GridPane pane;
    TableView<Pick> pickTable;
    //and the table colums
    TableColumn<Pick, Integer> pickOrderColumn;
    TableColumn<Pick, String> firstNameColumn;
    TableColumn<Pick, String> lastNameColumn;
    TableColumn<Pick, String> proTeamColumn;
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

    public DraftScreen(Fdk_gui initGui, Stage primaryStage,
            MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog) {
        //init the properties Manager
        properties = PropertiesManager.getPropertiesManager();
        dataManager = initGui.getDataManager();
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;

        //set the style class
        this.getStyleClass().add(SCREEN_STYLE);

        //init the components
        initComponents();

    }

    private void initTable(GridPane pane) {
        //first init the player table
        pickTable = new TableView<>();

        //and the table colums
        //note that some of the column headings are not set
        //this will be done later in an action listener
        pickOrderColumn = new TableColumn<>(PICK_ORDER_COLUMN);
        pickOrderColumn.setCellValueFactory(new PropertyValueFactory<>("pickOrder"));

        firstNameColumn = new TableColumn<>(FIRST_NAME_COLUMN);
        firstNameColumn.setEditable(false);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        lastNameColumn = new TableColumn<>(LAST_NAME_COLUMN);
        lastNameColumn.setEditable(false);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        proTeamColumn = new TableColumn<>(PRO_TEAM_COLUMN);
        proTeamColumn.setEditable(false);
        proTeamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));

        contractColumn = new TableColumn<>(CONTRACT_COLUMN);
        contractColumn.setEditable(false);
        contractColumn.setCellValueFactory(new PropertyValueFactory<>("contract"));

        salaryColumn = new TableColumn<>(SALARY_COLUMN);
        salaryColumn.setEditable(false);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        estimatedValueColumn = new TableColumn<>(EV_COLUMN);
        estimatedValueColumn.setEditable(false);
        estimatedValueColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedValue"));
        //now we can add all of these bad girls to the table
        pickTable.getColumns().addAll(pickOrderColumn,firstNameColumn, lastNameColumn,
                proTeamColumn, contractColumn, salaryColumn, estimatedValueColumn);
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

        //add the screen heading
        draftHeadingLabel = initGridLabel(pane, DRAFT_SCREEN_HEADING_LABEL, HEADING_STYLE, 1, 1, 1, 1);

        //create the table and add it to the pane
        initTable(pane);

        this.setCenter(pane);
    }

    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE

    private Label initGridLabel(GridPane container, Fdk_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }
    public void updateTable(){
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
}
