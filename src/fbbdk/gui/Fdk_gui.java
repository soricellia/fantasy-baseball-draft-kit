/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.gui;

import fantasybaseballdraftkit.Fdk_PropertyType;
import static fantasybaseballdraftkit.Fdk_StartupConstants.CLOSE_BUTTON_LABEL;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_CSS;
import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_IMAGES;
import fbbdk.controller.DraftController;
import fbbdk.controller.FileController;
import fbbdk.data.Draft;
import fbbdk.data.DraftDataManager;
import fbbdk.data.DraftDataView;
import fbbdk.file.DraftFileManager;
import fbbdk.file.DraftSiteExporter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Rectangle2D;
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
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author Tony
 */
public class Fdk_gui implements DraftDataView{

    //this will handle all file saving
    DraftFileManager dfm;
    //this deals with all of our data
    DraftDataManager ddm;
    //this is the controller for all file events
    FileController fileController;
    //this is the controller for all draft change events
    DraftController draftController;
    //this manages exporting the site that contains draft information
    DraftSiteExporter draftSiteExporter;
    
    //this is the application window
    Stage primaryStage;
    //this is the stages scene graph
    Scene primaryScene;
    
    //everything goes in here
    BorderPane fdkPane;
    //this is my file toolbar
    FlowPane fileToolbarPane;
    //and the buttons to go with it
    Button newDraftButton;
    Button loadDraftButton;
    Button saveDraftButton;
    Button exportDraftButton;
    Button exitButton;
    
    //this is where all of the data will show
    BorderPane workspacePane;
    ScrollPane workspaceScrollPane;
    //this is for controlling input
    boolean workspaceActivated;
    
    //these go at the bottom to switch the workspace
    FlowPane screenToolbarPane;
    Button homeScreenButton;
    Button playerScreenButton;
    Button fantasyStandingScreenButton;
    Button draftScreenButton;
    Button mlbTeamsScreenButton;
    
    MessageDialog messageDialog;
    YesNoCancelDialog yesNoCancelDialog;
    
    
    //constants used for GUI
    static BorderPane DEFAULT_WORKSPACE = new HomeScreen();
    static final String PRIMARY_STYLE_SHEET = PATH_CSS + "fdk_style.css";
    /**
     * Constructor for making this GUI, note that it does not initialize the UI
     * controls. To do that, call initGUI.
     *
     * @param initPrimaryStage Window inside which the GUI will be displayed.
     */
    public Fdk_gui(Stage initPrimaryStage){
        this.primaryStage = initPrimaryStage;
    }
    
    /**
     * Accessor method for the data manager.
     *
     * @return The CourseDataManager used by this UI.
     */
    public DraftDataManager getDataManager() {
        return ddm;
    }

    /**
     * Accessor method for the file controller.
     *
     * @return The FileController used by this UI.
     */
    public FileController getFileController() {
        return fileController;
    }

    /**
     * Accessor method for the course file manager.
     *
     * @return The CourseFileManager used by this UI.
     */
    public DraftFileManager getCourseFileManager() {
        return dfm;
    }

    /**
     * Accessor method for the site exporter.
     *
     * @return The CourseSiteExporter used by this UI.
     */
    public DraftSiteExporter getSiteExporter() {
        return draftSiteExporter;
    }

    /**
     * Accessor method for the window (i.e. stage).
     *
     * @return The window (i.e. Stage) used by this UI.
     */
    public Stage getWindow() {
        return primaryStage;
    }
    
    public MessageDialog getMessageDialog() {
        return messageDialog;
    }
    
    public YesNoCancelDialog getYesNoCancelDialog() {
        return yesNoCancelDialog;
    }

    /**
     * Mutator method for the data manager.
     *
     * @param initDataManager The CourseDataManager to be used by this UI.
     */
    public void setDataManager(DraftDataManager initDataManager) {
        ddm = initDataManager;
    }

    /**
     * Mutator method for the course file manager.
     *
     * @param initCourseFileManager The CourseFileManager to be used by this UI.
     */
    public void setCourseFileManager(DraftFileManager initCourseFileManager) {
       dfm = initCourseFileManager;
    }

    /**
     * Mutator method for the site exporter.
     *
     * @param initSiteExporter The CourseSiteExporter to be used by this UI.
     */
    public void setSiteExporter(DraftSiteExporter initSiteExporter) {
        draftSiteExporter = initSiteExporter;
    }
    
     private void initDialogs() {
        messageDialog = new MessageDialog(primaryStage, CLOSE_BUTTON_LABEL);
        yesNoCancelDialog = new YesNoCancelDialog(primaryStage);
    }
      public void initGUI(String windowTitle) throws IOException {
        // INIT THE DIALOGS
        initDialogs();
        
        // INIT THE TOOLBAR
        initFileToolbar();

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
        // TO THE WINDOW YET
        initWorkspace(DEFAULT_WORKSPACE);
        
        //INIT THE SCREEN TOOLBAR
        initScreenToolbar();
        
        // NOW SETUP THE EVENT HANDLERS
        initEventHandlers();

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        initWindow(windowTitle);
    }
      
    /**
     * When called this function puts the workspace into the window,
     * revealing the controls for editing a Course.
     */
    public void activateWorkspace() {
        if (!workspaceActivated) {
            // PUT THE WORKSPACE IN THE GUI
            fdkPane.setCenter(workspaceScrollPane);
            workspaceActivated = true;
        }
    }
    
    /**
     * This method is used to activate/deactivate toolbar buttons when
     * they can and cannot be used so as to provide foolproof design.
     * 
     * @param saved Describes whether the loaded Course has been saved or not.
     */
    public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveDraftButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST COURSE BEGINS
        loadDraftButton.setDisable(false);
        exportDraftButton.setDisable(false);

        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }
    
    // INITIALIZE THE WINDOW (i.e. STAGE) PUTTING ALL THE CONTROLS
    // THERE EXCEPT THE WORKSPACE, WHICH WILL BE ADDED THE FIRST
    // TIME A NEW Course IS CREATED OR LOADED
    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        fdkPane = new BorderPane();
        fdkPane.setTop(fileToolbarPane);
        primaryScene = new Scene(fdkPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(PRIMARY_STYLE_SHEET);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newDraftButton = initChildButton(fileToolbarPane, Fdk_PropertyType.NEW_DRAFT_ICON, Fdk_PropertyType.NEW_DRAFT_TOOLTIP, false);
        loadDraftButton = initChildButton(fileToolbarPane, Fdk_PropertyType.LOAD_DRAFT_ICON, Fdk_PropertyType.LOAD_DRAFT_TOOLTIP, false);
        saveDraftButton = initChildButton(fileToolbarPane, Fdk_PropertyType.SAVE_DRAFT_ICON, Fdk_PropertyType.SAVE_DRAFT_TOOLTIP, true);
        exportDraftButton = initChildButton(fileToolbarPane, Fdk_PropertyType.EXPORT_PAGE_ICON, Fdk_PropertyType.EXPORT_PAGE_TOOLTIP, true);
        exitButton = initChildButton(fileToolbarPane, Fdk_PropertyType.EXIT_ICON, Fdk_PropertyType.EXIT_TOOLTIP, false);
    }
    private void initWorkspace(BorderPane screen) throws IOException {
       
        workspacePane = screen;
        
        // AND NOW PUT IT IN THE WORKSPACE
        workspaceScrollPane = new ScrollPane();
        workspaceScrollPane.setContent(workspacePane);
        workspaceScrollPane.setFitToWidth(true);

        // NOTE THAT WE HAVE NOT PUT THE WORKSPACE INTO THE WINDOW,
        // THAT WILL BE DONE WHEN THE USER EITHER CREATES A NEW
        // COURSE OR LOADS AN EXISTING ONE FOR EDITING
        workspaceActivated = false;
    }
     /**
     * This function initializes all the buttons in the toolbar at the bottom of
     * the application window. These are related to screen management.
     */
    private void initScreenToolbar() {
        screenToolbarPane = new FlowPane();

        // HERE ARE OUR SCREEN TOOLBAR BUTTONS, NOTE THAT ALL WILL
        // START AS ENABLED (false)
        homeScreenButton = initChildButton(screenToolbarPane,
                Fdk_PropertyType.HOME_SCREEN_ICON, Fdk_PropertyType.HOME_SCREEN_TOOLTIP, false);
        
        playerScreenButton = initChildButton(screenToolbarPane,
                Fdk_PropertyType.PLAYER_SCREEN_ICON, Fdk_PropertyType.PLAYER_SCREEN_TOOLTIP, false);
        
        fantasyStandingScreenButton = initChildButton(screenToolbarPane, 
                Fdk_PropertyType.FANTASY_STANDING_SCREEN_ICON, Fdk_PropertyType.FANTASY_STANDING_SCREEN_TOOLTIP, false);
        
        draftScreenButton = initChildButton(screenToolbarPane,
                Fdk_PropertyType.DRAFT_SCREEN_ICON, Fdk_PropertyType.DRAFT_SCREEN_TOOLTIP, false);
        
        mlbTeamsScreenButton = initChildButton(screenToolbarPane,
                Fdk_PropertyType.MLB_TEAMS_SCREEN_ICON, Fdk_PropertyType.MLB_TEAMS_SCREEN_TOOLTIP, false);
    }
    @Override
    public void reloadDraft(Draft draftToReload) {
        //TODO
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
    
    // INIT A LABEL AND SET IT'S STYLESHEET CLASS
    private Label initLabel(Fdk_PropertyType labelProperty, String styleClass) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelText = props.getProperty(labelProperty);
        Label label = new Label(labelText);
        label.getStyleClass().add(styleClass);
        return label;
    }
    
    // INIT A LABEL AND PLACE IT IN A GridPane INIT ITS PROPER PLACE
    private Label initGridLabel(GridPane container, Fdk_PropertyType labelProperty, String styleClass, int col, int row, int colSpan, int rowSpan) {
        Label label = initLabel(labelProperty, styleClass);
        container.add(label, col, row, colSpan, rowSpan);
        return label;
    }
    // INIT A LABEL AND PUT IT IN A TOOLBAR
    private Label initChildLabel(Pane container, Fdk_PropertyType labelProperty, String styleClass) {
        Label label = initLabel(labelProperty, styleClass);
        container.getChildren().add(label);
        return label;
    }
    // INIT A COMBO BOX AND PUT IT IN A GridPane
    private ComboBox initGridComboBox(GridPane container, int col, int row, int colSpan, int rowSpan) throws IOException {
        ComboBox comboBox = new ComboBox();
        container.add(comboBox, col, row, colSpan, rowSpan);
        return comboBox;
    }
     // INIT A TEXT FIELD AND PUT IT IN A GridPane
    private TextField initGridTextField(GridPane container, int size, String initText, boolean editable, int col, int row, int colSpan, int rowSpan) {
        TextField tf = new TextField();
        tf.setPrefColumnCount(size);
        tf.setText(initText);
        tf.setEditable(editable);
        container.add(tf, col, row, colSpan, rowSpan);
        return tf;
    }

    private void initEventHandlers() {
        //TODO
    }

}