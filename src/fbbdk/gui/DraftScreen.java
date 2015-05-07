/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_SCREEN_HEADING_LABEL;
import fbbdk.data.DraftDataManager;
import static fbbdk.gui.HomeScreen.SCREEN_STYLE;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class DraftScreen extends BorderPane{
    
    PropertiesManager properties;
    DraftDataManager dataManager;
    YesNoCancelDialog yesNoCancelDialog;
    MessageDialog messageDialog;
    
    Label draftHeadingLabel;
    
    GridPane pane;
    
    final static String HEADING_STYLE = "heading_label";
    
    public DraftScreen(Fdk_gui initGui, Stage primaryStage,
            MessageDialog initMessageDialog, YesNoCancelDialog initYesNoCancelDialog){
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
    //2033400830
    private void initComponents() {
        //init gridpane
        pane = new GridPane();
        
        draftHeadingLabel = initGridLabel(pane,DRAFT_SCREEN_HEADING_LABEL,HEADING_STYLE,1,1,1,1);
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
}
