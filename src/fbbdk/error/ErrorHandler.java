/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.error;

import static fantasybaseballdraftkit.Fdk_PropertyType.ILLEGAL_TEAM_NAME_MESSAGE;
import static fantasybaseballdraftkit.Fdk_PropertyType.REMOVE_PLAYER_ERROR;
import static fantasybaseballdraftkit.Fdk_StartupConstants.CLOSE_BUTTON_LABEL;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PROPERTIES_FILE_ERROR_MESSAGE;
import fbbdk.data.Draft;
import fbbdk.gui.MessageDialog;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class ErrorHandler {
      // THIS CLASS USES A SINGLETON DESIGN PATTER, WHICH IS CONVENIENT
    // BECAUSE IT NEEDS TO BE USED BY SO MANY OTHER CLASSES
    static ErrorHandler singleton;
    
    // WE'LL MAKE USE OF THIS DIALOG TO PROVIDE OUR MESSAGE FEEDBACK
    MessageDialog messageDialog;
    
    // THE PROPERTIES MANAGER WILL GIVE US THE TEXT TO DISPLAY
    PropertiesManager properties;

    /**
     * Note that this constructor is private and so can never be called
     * outside of this class.
     */
    private ErrorHandler() {
        // THIS HELPS US KEEP TRACK OF WHETHER WE NEED TO
        // CONSTRUCT THE SINGLETON OR NOT EACH TIME IT'S ACCESSED
        singleton = null;
        
        // WE ONLY NEED TO GET THE SINGLETON ONCE
        properties = PropertiesManager.getPropertiesManager();
    }
    
    /**
     * This method initializes this error handler's message dialog
     * so that it may provide feedback when errors occur.
     * 
     * @param owner The parent window for the modal message dialog.
     */
    public void initMessageDialog(Stage owner) {
        // WE'LL USE THIS DIALOG TO PROVIDE FEEDBACK WHEN ERRORS OCCUR
        messageDialog = new MessageDialog(owner, CLOSE_BUTTON_LABEL);        
    }

    /**
     * Accessor method for getting this singleton.
     * 
     * @return The singleton ErrorHandler used by the entire
     * application for responding to error conditions.
     */
    public static ErrorHandler getErrorHandler() {
        // INITIALIZE THE SINGLETON ONLY THE FIRST TIME
        if (singleton == null)
            singleton = new ErrorHandler();
        
        // BUT ALWAYS RETURN IT
        return singleton;
    }
    
    public void handleNewCourseError() {
        
    }
    
    public void handleLoadCourseError() {
        
    }
    
    public void handleSaveCourseError() {
        
    }

    public void handleViewSchedulePageError(String pageURL) {
        
    }
     
    public void handleExportDraftError(Draft draftBeingExported) {
        
    }
        
    public void handleExitError() {
        
    }

    public void handleUpdateCourseError() {
        
    }
    public void handleMyCockError(){
        //HANDLE IT WELLLLLL YOUNG PADDAWON
        //8=========================D
    }
    public void handleTeamAddEditError(){
        messageDialog.show(properties.getProperty(ILLEGAL_TEAM_NAME_MESSAGE));
    }
    public void handleRemovePlayerError(){
        messageDialog.show(properties.getProperty(REMOVE_PLAYER_ERROR));
    }
    /**
     * This function provides feedback to the user when the properties.xml
     * file cannot be loaded.
     */
    public void handlePropertiesFileError() {
        messageDialog.show(properties.getProperty(PROPERTIES_FILE_ERROR_MESSAGE));
    }
}
