/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fantasybaseballdraftkit;

/**
 *
 * @author Tony
 */
public class Fdk_StartupConstants {
   
    // WE NEED THESE CONSTANTS JUST TO GET STARTED
    // LOADING SETTINGS FROM OUR XML FILES
    public static final String PROPERTIES_FILE_NAME = "properties.xml";
    public static final String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";    
    public static final String PATH_DATA = "./data/";
    public static final String PATH_COURSES = PATH_DATA + "courses/";
    public static final String PATH_IMAGES = "./images/";
    public static final String PATH_CSS = "csb/css/";
    public static final String PATH_SITES = "sites/";
    public static final String PATH_BASE = PATH_SITES + "base/";
    public static final String PATH_EMPTY = ".";

    // THESE ARE THE DATA FILES WE WILL LOAD AT STARTUP
    public static final String JSON_FILE_PATH_SUBJECTS = PATH_DATA + "subjects.json";
    
    // ERRO MESSAGE ASSOCIATED WITH PROPERTIES FILE LOADING ERRORS
    public static String PROPERTIES_FILE_ERROR_MESSAGE = "Error Loading properties.xml";

    // ERROR DIALOG CONTROL
    public static String CLOSE_BUTTON_LABEL = "Close";
}
