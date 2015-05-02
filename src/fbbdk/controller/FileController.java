/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.controller;

import fbbdk.gui.WebBrowser;
import static fantasybaseballdraftkit.Fdk_PropertyType.DRAFT_SAVED_MESSAGE;
import static fantasybaseballdraftkit.Fdk_PropertyType.NEW_DRAFT_CREATED_MESSAGE;
import static fantasybaseballdraftkit.Fdk_PropertyType.SAVE_UNSAVED_WORK_MESSAGE;
import static fantasybaseballdraftkit.Fdk_StartupConstants.JSON_PLAYER_PATH;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_DRAFTS;
import fbbdk.data.Draft;
import fbbdk.data.DraftDataManager;
import fbbdk.error.ErrorHandler;
import fbbdk.file.DraftFileManager;
import fbbdk.file.DraftSiteExporter;
import fbbdk.gui.Fdk_gui;
import fbbdk.gui.MessageDialog;
import fbbdk.gui.YesNoCancelDialog;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class FileController {
    
    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;

    // THIS GUY KNOWS HOW TO READ AND WRITE COURSE DATA
    private final DraftFileManager draftIO;

    // THIS GUY KNOWS HOW TO EXPORT COURSE SCHEDULE PAGES
    private final DraftSiteExporter exporter;

    // THIS WILL PROVIDE FEEDBACK TO THE USER WHEN SOMETHING GOES WRONG
    ErrorHandler errorHandler;
    
    // THIS WILL PROVIDE FEEDBACK TO THE USER AFTER
    // WORK BY THIS CLASS HAS COMPLETED
    MessageDialog messageDialog;
    
    // AND WE'LL USE THIS TO ASK YES/NO/CANCEL QUESTIONS
    YesNoCancelDialog yesNoCancelDialog;
    
    // WE'LL USE THIS TO GET OUR VERIFICATION FEEDBACK
    PropertiesManager properties;

    /**
     * This default constructor starts the program without a course file being
     * edited.
     * 
     * @param initYesNoCancelDialog
     * 
     * @param initMessageDialog
     * 
     * @param initDraftIO The object that will be reading and writing draft data.
     * 
     * @param initExporter The object that will be exporting drafts to Web
     * sites.
     */
    public FileController(
            MessageDialog initMessageDialog,
            YesNoCancelDialog initYesNoCancelDialog,
            DraftFileManager initDraftIO,
            DraftSiteExporter initExporter) {
        // NOTHING YET
        saved = true;
        
        // KEEP THESE GUYS FOR LATER
        draftIO = initDraftIO;
        exporter = initExporter;
        
        // BE READY FOR ERRORS
        errorHandler = ErrorHandler.getErrorHandler();
        
        // AND GET READY TO PROVIDE FEEDBACK
        messageDialog = initMessageDialog;
        yesNoCancelDialog = initYesNoCancelDialog;
       
        properties = PropertiesManager.getPropertiesManager();
    }
    
    /**
     * This method starts the process of editing a new Course. If a course is
     * already being edited, it will prompt the user to save it first.
     * 
     * @param gui The user interface editing the Course.
     */
    public void handleNewDraftRequest(Fdk_gui gui) {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                DraftDataManager dataManager = gui.getDataManager();
                dataManager.reset();
                
                //now we need to load in new players
                draftIO.loadNewDraft(dataManager.getDraft(), JSON_PLAYER_PATH);
                //load the obervable players
                dataManager.getDraft().setObservablePlayers();
                //and reload the draft into the gui
                gui.reloadDraft(dataManager.getDraft());
                saved = false;

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                gui.updateToolbarControls(saved);
                // TELL THE USER THE COURSE HAS BEEN CREATED
                messageDialog.show(properties.getProperty(NEW_DRAFT_CREATED_MESSAGE));
            }
        } catch (IOException ioe) {
            // SOMETHING WENT WRONG, PROVIDE FEEDBACK
            errorHandler.handleNewCourseError();
        }
    }
    /**
     * This method marks the appropriate variable such that we know
     * that the current Course has been edited since it's been saved.
     * The UI is then updated to reflect this.
     * 
     * @param gui The user interface editing the Course.
     */
    public void markAsEdited(Fdk_gui gui) {
        // THE Course OBJECT IS NOW DIRTY
        saved = false;
        
        // LET THE UI KNOW
        gui.updateToolbarControls(saved);
    }
    /**
     * This method lets the user open a Course saved to a file. It will also
     * make sure data for the current Course is not lost.
     * 
     * @param gui The user interface editing the course.
     */
    public void handleLoadDraftRequest(Fdk_gui gui) {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO OPEN A Course
            if (continueToOpen) {
                // GO AHEAD AND PROCEED LOADING A Course
                promptToOpen(gui);
            }
        } catch (IOException ioe) {
            // SOMETHING WENT WRONG
            errorHandler.handleLoadCourseError();
        }
    }

    /**
     * This method will save the current course to a file. Note that we already
     * know the name of the file, so we won't need to prompt the user.
     * 
     * @param gui The user interface editing the Course.
     * @param draftToSave
     * 
     */
    public void handleSaveDraftRequest(Fdk_gui gui, Draft draftToSave) {
        try {
            // SAVE IT TO A FILE
            
            draftIO.saveDraft(draftToSave);
            
            // MARK IT AS SAVED
            saved = true;
            // TELL THE USER THE FILE HAS BEEN SAVED
            messageDialog.show(properties.getProperty(DRAFT_SAVED_MESSAGE));

            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            gui.updateToolbarControls(saved);
        } catch (IOException ioe) {
            errorHandler.handleSaveCourseError();
        }
    }

    /**
     * This method will export the current course.
     * 
     * @param gui
     */
    public void handleExportDraftRequest(Fdk_gui gui) {
        // EXPORT THE COURSE
        DraftDataManager dataManager = gui.getDataManager();
        Draft draftToExport = dataManager.getDraft();

        // WE'LL NEED THIS TO LOAD THE EXPORTED PAGE FOR VIEWING
        String draftURL = exporter.getPageURLPath(draftToExport, "this needs to be done");
        
        // NOW GET THE EXPORTER
        try {            
            
            // AND EXPORT THE COURSE
            exporter.exportCourseSite(draftToExport, gui.getWindow());
            if(exporter.isComplete){
                // AND THEN OPEN UP THE PAGE IN A BROWSER
                Stage webBrowserStage = new Stage();
                WebBrowser webBrowser = new WebBrowser(webBrowserStage, draftURL);
                webBrowserStage.show();
            }
        }
        // WE'LL HANDLE COURSE EXPORT PROBLEMS AND COURSE PAGE VIEWING
        // PROBLEMS USING DIFFERENT ERROR MESSAGES
        catch (MalformedURLException murle) {
            errorHandler.handleViewSchedulePageError(draftURL);
        } catch (Exception ioe) {
            errorHandler.handleExportDraftError(draftToExport);
        }
    }

    /**
     * This method will exit the application, making sure the user doesn't lose
     * any data first.
     * 
     * @param gui
     */
    public void handleExitRequest(Fdk_gui gui) {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToExit = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE
                continueToExit = promptToSave(gui);
            }

            // IF THE USER REALLY WANTS TO EXIT THE APP
            if (continueToExit) {
                // EXIT THE APPLICATION
                System.exit(0);
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ErrorHandler.getErrorHandler();
            eH.handleExitError();
        }
    }

    /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * Course, or opening another Course. Note that the user will be
     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
     * to save their work and continue the other action (we return true to
     * denote this), NO means don't save the work but continue with the other
     * action (true is returned), CANCEL means don't save the work and don't
     * continue with the other action (false is returned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave(Fdk_gui gui) throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        yesNoCancelDialog.show(properties.getProperty(SAVE_UNSAVED_WORK_MESSAGE));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (selection.equals(YesNoCancelDialog.YES)) {
            // SAVE THE COURSE
            DraftDataManager dataManager = gui.getDataManager();
            draftIO.saveDraft(dataManager.getDraft());
            saved = true;
            
        
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (selection.equals(YesNoCancelDialog.CANCEL)) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }

    /**
     * This helper method asks the user for a file to open. The user-selected
     * file is then loaded and the GUI updated. Note that if the user cancels
     * the open process, nothing is done. If an error occurs loading the file, a
     * message is displayed, but nothing changes.
     */
    private void promptToOpen(Fdk_gui gui) {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser courseFileChooser = new FileChooser();
        courseFileChooser.setInitialDirectory(new File(PATH_DRAFTS));
        File selectedFile = courseFileChooser.showOpenDialog(gui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
                Draft draftToLoad = gui.getDataManager().getDraft();
                draftIO.loadDraft(draftToLoad, selectedFile.getAbsolutePath());
                gui.reloadDraft(draftToLoad);
                
                saved = true;
                gui.updateToolbarControls(saved);
            } catch (Exception e) {
                ErrorHandler eH = ErrorHandler.getErrorHandler();
                eH.handleLoadCourseError();
            }
        }
    }

    /**
     * This mutator method marks the file as not saved, which means that when
     * the user wants to do a file-type operation, we should prompt the user to
     * save current work first. Note that this method should be called any time
     * the course is changed in some way.
     */
    public void markFileAsNotSaved() {
        saved = false;
    }

    /**
     * Accessor method for checking to see if the current course has been saved
     * since it was last edited.
     *
     * @return true if the current course is saved to the file, false otherwise.
     */
    public boolean isSaved() {
        return saved;
    }
}
