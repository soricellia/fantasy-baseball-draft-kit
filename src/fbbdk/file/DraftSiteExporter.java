/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.file;

import fbbdk.data.Draft;
import javafx.stage.Stage;

/**
 *
 * @author Tony
 */
public class DraftSiteExporter {
    
    // THERE ARE A NUMBER OF CONSTANTS THAT WE'LL USE FOR FINDING
    // ELEMENTS IN THE PAGES WE'RE LOADING, AS WELL AS THINGS WE'LL
    // BUILD INTO OUR PAGE WHILE EXPORTING
    public static final String ID_NAVBAR = "navbar";
    public static final String ID_BANNER = "banner";
    public static final String ID_SCHEDULE = "schedule";
    public static final String ID_HWS = "hws";
    public static final String ID_HOME_LINK = "home_link";
    public static final String ID_SYLLABUS_LINK = "syllabus_link";
    public static final String ID_SCHEDULE_LINK = "schedule_link";
    public static final String ID_HWS_LINK = "hws_link";
    public static final String ID_PROJECTS_LINK = "projects_link";
    public static final String ID_INSTRUCTOR_LINK = "instructor_link";
    public static final String ID_INLINED_COURSE = "inlined_course";
    public static final String CLASS_NAV = "nav";
    public static final String CLASS_OPEN_NAV = "open_nav";
    public static final String CLASS_SCH = "sch";
    public static final String CLASS_HOLIDAY = "holiday";
    public static final String CLASS_LECTURE = "lecture";
    public static final String CLASS_HW = "hw";
    public static final String CLASS_HWS = "hws";

    // THIS IS TEXT WE'LL BE ADDING TO OUR PAGE
    public static final String INDEX_HEADER = "Home";
    public static final String SYLLABUS_HEADER = "Syllabus";
    public static final String SCHEDULE_HEADER = "Schedule";
    public static final String HWS_HEADER = "HWs";
    public static final String PROJECTS_HEADER = "Projects";
    public static final String MONDAY_HEADER = "MONDAY";
    public static final String TUESDAY_HEADER = "TUESDAY";
    public static final String WEDNESDAY_HEADER = "WEDNESDAY";
    public static final String THURSDAY_HEADER = "THURSDAY";
    public static final String FRIDAY_HEADER = "FRIDAY";
    public static final String LECTURE_HEADER = "Lecture ";
    public static final String DUE_HEADER = "due @ 11:59pm";
    public static final String DUE_TIME = " @11:59pm";
    public static final String DEFAULT_GRADING_TEXT = "TBD";
    // THESE ARE THE POSSIBLE SITE PAGES OUR SCHEDULE PAGE
    // MAY NEED TO LINK TO
    public static String INDEX_PAGE = "index.html";
    public static String SYLLABUS_PAGE = "syllabus.html";
    public static String SCHEDULE_PAGE = "schedule.html";
    public static String HWS_PAGE = "hws.html";
    public static String PROJECTS_PAGE = "projects.html";

    // THIS IS THE DIRECTORY STRUCTURE USED BY OUR SITE
    public static final String CSS_DIR = "css";
    public static final String IMAGES_DIR = "images";

    // AND SOME TEXT WE'LL NEED TO ADD ON THE FLY
    public static final String CONT_LECTURE = " (Continuted)";
    public static final String OPEN_PARENTH = "(";
    public static final String CLOSE_PARENTH = ")";
    public static final String COMMA = ", ";
    public static final String SLASH = "/";
    public static final String DASH = " - ";
    public static final String LINE_BREAK = "<br />";
    public static final String EXPORT_TEXT = "Exporting ";
    public static final String EXPORT_TEXT_COMPLETE = "Export Complete ";
    public static final String PAGE_TEXT = " Page";

    //THIS IS TO CHANGE THE HOMEWORK PAGES TABEL COLOR
    public static final int BASE_RED = 240;
    public static final int BASE_GREEN = 240;
    public static final int BASE_BLUE = 255;
    public static final String COLOR_STYLE = "background-color:rgb";

    public boolean isComplete = false;
    // THESE ARE THE DIRECTORIES WHERE OUR BASE SCHEDULE
    // FILE IS AND WHERE OUR COURSE SITES WILL BE EXPORTED TO
    String baseDir;
    String sitesDir;

    // WE'LL USE THIS VARIABLE TO KEEP TRACK OF EXPORTING PROGRESS
    double perc = 0;
    int pageIndex;
    
   /**
     * This constructor initializes this exporter to load the schedule page from
     * the initBaseDir and export course pages to directories found in
     * initSitesDir.
     *
     * @param initBaseDir Directory that contains the base site files.
     *
     * @param initSitesDir Directory where course sites will be exported to.
     * Note that each course will have a directory here containing its site.
     */
    public DraftSiteExporter(String initBaseDir, String initSitesDir) {
        baseDir = initBaseDir;
        sitesDir = initSitesDir;
    }
}
