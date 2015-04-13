/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fantasybaseballdraftkit;

import static fantasybaseballdraftkit.Fdk_PropertyType.PROP_APP_TITLE;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_BASE;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_DATA;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_SITES;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PROPERTIES_FILE_NAME;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import fbbdk.data.DraftDataManager;
import fbbdk.error.ErrorHandler;
import fbbdk.file.DraftSiteExporter;
import fbbdk.file.JsonDraftFileManager;
import fbbdk.gui.Fdk_gui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;

/**
 *
 * @author Tony
 */

//need to add GUI

public class FantasyBaseballDraftKit extends Application {
    // THIS IS THE FULL USER INTERFACE, WHICH WILL BE INITIALIZED
    // AFTER THE PROPERTIES FILE IS LOADED
   Fdk_gui gui;

    /**
     * This is where our Application begins its initialization, it will
     * create the GUI and initialize all of its components.
     * 
     * @param primaryStage This application's window.
     */
    @Override
    public void start(Stage primaryStage) {
        // LET'S START BY GIVING THE PRIMARY STAGE TO OUR ERROR HANDLER
        ErrorHandler eH = ErrorHandler.getErrorHandler();
        eH.initMessageDialog(primaryStage);
        
        // LOAD APP SETTINGS INTO THE GUI AND START IT UP
        boolean success = loadProperties();
        if (success) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty(PROP_APP_TITLE);
            try {                
                // WE WILL SAVE OUR COURSE DATA USING THE JSON FILE
                // FORMAT SO WE'LL LET THIS OBJECT DO THIS FOR US
                JsonDraftFileManager jsonFileManager = new JsonDraftFileManager();
                
                // AND THIS ONE WILL DO THE COURSE WEB PAGE EXPORTING
                DraftSiteExporter exporter = new DraftSiteExporter(PATH_BASE, PATH_SITES);
                
                         
                // AND NOW GIVE ALL OF THIS STUFF TO THE GUI
                // INITIALIZE THE USER INTERFACE COMPONENTS
                gui = new Fdk_gui(primaryStage);
                gui.setCourseFileManager(jsonFileManager);
                gui.setSiteExporter(exporter);
                
                // CONSTRUCT THE DATA MANAGER AND GIVE IT TO THE GUI
                DraftDataManager dataManager = new DraftDataManager(gui); 
                gui.setDataManager(dataManager);

                // FINALLY, START UP THE USER INTERFACE WINDOW AFTER ALL
                // REMAINING INITIALIZATION
                gui.initGUI(appTitle);                
            }
            catch(IOException ioe) {
                eH = ErrorHandler.getErrorHandler();
                eH.handlePropertiesFileError();
            }
        }
    }
    
    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
            props.loadProperties(PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handlePropertiesFileError();
            return false;
        }        
    }

    /**
     * This is where program execution begins. Since this is a JavaFX app
     * it will simply call launch, which gets JavaFX rolling, resulting in
     * sending the properly initialized Stage (i.e. window) to our start
     * method in this class.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }
}
