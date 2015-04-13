/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.file;

import fbbdk.data.Draft;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author Tony
 */
public class JsonDraftFileManager implements DraftFileManager{
     // JSON FILE READING AND WRITING CONSTANTS
    String JSON_PLAYERS = "players";
    
    //@TODO
    String PATH_DRAFTS = "";
    
    String JSON_PLAYER = "player";
    String JSON_TEAMS = "teams";
    String JSON_TITLE = "title";
    String JSON_DRAFT = "draft";
    String JSON_YEAR = "year";
    String JSON_SECTION = "section";
    String JSON_PAGES = "pages";
    String JSON_STARTING_MONDAY = "startingMonday";
    String JSON_ENDING_FRIDAY = "endingFriday";
    String JSON_MONTH = "month";
    String JSON_DAY = "day";
    String JSON_INSTRUCTOR = "instructor";
    String JSON_INSTRUCTOR_NAME = "instructorName";
    String JSON_HOMEPAGE_URL = "homepageURL";
    String JSON_LECTURE_DAYS = "lectureDays";
    String JSON_SCHEDULE_ITEMS = "scheduleItems";
    String JSON_LECTURES = "lectures";
    String JSON_SCHEDULE_ITEM_DESCRIPTION = "description";
    String JSON_SCHEDULE_ITEM_DATE = "date";
    String JSON_SCHEDULE_ITEM_LINK = "link";
    String JSON_LECTURE_TOPIC = "topic";
    String JSON_LECTURE_SESSIONS = "sessions";
    String JSON_PLAYER_NAME = "name";
    String JSON_EXT = ".json";
    String SLASH = "/";

    /**
     * This method saves all the data associated with a course to
     * a JSON file.
     * 
     * @param draftToSave The course whose data we are saving.
     * 
     * @throws IOException Thrown when there are issues writing
     * to the JSON file.
     */
    @Override
    public void saveDraft(Draft draftToSave) throws IOException {
       //@Todo
    }
    
    /**
     * Loads the courseToLoad argument using the data found in the json file.
     * 
     * @param courseToLoad Course to load.
     * @param jsonFilePath File containing the data to load.
     * 
     * @throws IOException Thrown when IO fails.
     */
    @Override
    public void loadDraft(Draft draftToLoad, String jsonFilePath) throws IOException {
            //@Todo
    
    }
    
    // AND HERE ARE THE PRIVATE HELPER METHODS TO HELP THE PUBLIC ONES
    
    // LOADS A JSON FILE AS A SINGLE OBJECT AND RETURNS IT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }    
    
    // LOADS AN ARRAY OF A SPECIFIC NAME FROM A JSON FILE AND
    // RETURNS IT AS AN ArrayList FULL OF THE DATA FOUND
    private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
        JsonObject json = loadJSONFile(jsonFilePath);
        ArrayList<String> items = new ArrayList();
        JsonArray jsonArray = json.getJsonArray(arrayName);
        for (JsonValue jsV : jsonArray) {
            items.add(jsV.toString());
        }
        return items;
    }
    
    // MAKES AND RETURNS A JSON OBJECT FOR THE PROVIDED DATE
    private JsonObject makeLocalDateJsonObject(LocalDate dateToSave) {
        JsonObject jso = Json.createObjectBuilder().add(JSON_YEAR, dateToSave.getYear())
                                                   .add(JSON_MONTH, dateToSave.getMonthValue())
                                                   .add(JSON_DAY, dateToSave.getDayOfMonth())
                                                   .build(); 
        return jso;
    }
    
    // BUILDS AND RETURNS A JsonArray CONTAINING THE PROVIDED DATA
    public JsonArray buildJsonArray(List<Object> data) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Object d : data) {
           jsb.add(d.toString());
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    // BUILDS AND RETURNS A JsonObject CONTAINING A JsonArray
    // THAT CONTAINS THE PROVIDED DATA
    public JsonObject buildJsonArrayObject(List<Object> data) {
        JsonArray jA = buildJsonArray(data);
        JsonObject arrayObject = Json.createObjectBuilder().add(JSON_TEAMS, jA).build();
        return arrayObject;
    }

    @Override
    public void savePlayers(List<Object> subjects, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> loadPlayers(String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
