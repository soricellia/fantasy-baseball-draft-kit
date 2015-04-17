/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.file;

import fbbdk.data.Draft;
import fbbdk.data.Hitter;
import fbbdk.data.Pitcher;
import fbbdk.data.Player;
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
    String PATH_DRAFTS = "";
    
    String JSON_HITTER = "Hitters";
    String JSON_PITCHER = "Pitchers";
    
    String JSON_TITLE = "title";
    String JSON_DRAFT = "draft";
    
    //these are things hitters and pitchers will have
    String JSON_FIRST_NAME = "FIRST_NAME";
    String JSON_LAST_NAME = "LAST_NAME";
    String JSON_TEAM = "TEAM";
    String JSON_QP = "QP";
    String JSON_NOTES ="NOTES";
    String JSON_BIRTH_DATE = "YEAR_OF_BIRTH";
    String JSON_NATION_OF_BIRTH = "NATION_OF_BIRTH";
    String JSON_H = "H";
    
    //this is hitter stuff
    String JSON_AB = "AB";
    String JSON_R = "R";
    String JSON_HR = "HR";
    String JSON_RBI = "RBI";
    String JSON_SB = "SB";
    
    //this is pitcher stuff
    String JSON_IP = "IP";
    String JSON_ER = "ER";
    String JSON_W = "W";
    String JSON_SV = "SV";
    String JSON_BB ="BB";
    String JSON_K ="K";
    
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
        
       //first load json files
       JsonObject json = loadJSONFile(jsonFilePath);
       
       //now lets clear all availble players from the list
       draftToLoad.getAvailablePlayers().clear();
       //create a variable to accsess player array
       ArrayList<Player> players = draftToLoad.getAvailablePlayers();
       
        //create a json array
       JsonArray array = json.getJsonArray(JSON_HITTER);
       //populate the hitters
       loadHitters(players, array);
       
       //create new array
       array = json.getJsonArray(JSON_PITCHER);
       //now we can load the pitchers
       loadPitchers(players, array);
       
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
    
    
    // BUILDS AND RETURNS A JsonArray CONTAINING THE PROVIDED DATA
    public JsonArray buildJsonArray(List<Object> data) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Object d : data) {
           jsb.add(d.toString());
        }
        JsonArray jA = jsb.build();
        return jA;
    }

  

    @Override
    public void savePlayers(List<Object> subjects, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> loadPlayers(String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private void loadHitters(ArrayList<Player> players, JsonArray array) {
        for(int i = 0; i < array.size() ; i++){
           JsonObject jso = array.getJsonObject(i);
           Hitter player = new Hitter();
           player.setFirstName(jso.getString(JSON_FIRST_NAME));
           player.setLastName(jso.getString(JSON_LAST_NAME));
           player.setMlbTeam(jso.getString(JSON_TEAM));
           player.setBirthDate(Integer.parseInt(jso.getString(JSON_BIRTH_DATE)));
           player.setCountryOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
           player.setQP(jso.getString(JSON_QP));
           player.setNotes(jso.getString(JSON_NOTES));
           
           player.setHits(Integer.parseInt(jso.getString(JSON_H)));
           player.setRuns(Integer.parseInt(jso.getString(JSON_R)));
           player.setHomeRunes(Integer.parseInt(jso.getString(JSON_HR)));
           player.setStolenBases(Integer.parseInt(jso.getString(JSON_SB)));
           player.setRunsBattedIn(Integer.parseInt(jso.getString(JSON_RBI)));
           player.setBa(Integer.parseInt(jso.getString(JSON_AB)), Integer.parseInt(jso.getString(JSON_H)));
           
           players.add(player);
       }
    }

    private void loadPitchers(ArrayList<Player> players, JsonArray array) {
        for(int i = 0; i < array.size() ; i++){
           JsonObject jso = array.getJsonObject(i);
           Pitcher player = new Pitcher();
           player.setFirstName(jso.getString(JSON_FIRST_NAME));
           player.setLastName(jso.getString(JSON_LAST_NAME));
           player.setMlbTeam(jso.getString(JSON_TEAM));
           player.setBirthDate(Integer.parseInt(jso.getString(JSON_BIRTH_DATE)));
           player.setCountryOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
           player.setNotes(jso.getString(JSON_NOTES));
           
           player.setWhip(Integer.parseInt(jso.getString(JSON_BB)),Integer.parseInt(jso.getString(JSON_H))
                   ,Double.parseDouble(jso.getString(JSON_IP)));
           player.setEra(Integer.parseInt(jso.getString(JSON_ER)), Double.parseDouble(jso.getString(JSON_IP)));
           player.setWins(Integer.parseInt(jso.getString(JSON_W)));
           player.setStrikeOuts(Integer.parseInt(jso.getString(JSON_K)));
           player.setSaves(Integer.parseInt(jso.getString(JSON_SV)));
           
           players.add(player);
       }
    }
}
