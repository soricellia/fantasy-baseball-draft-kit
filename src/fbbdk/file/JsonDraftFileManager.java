/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.file;

import static fantasybaseballdraftkit.Fdk_StartupConstants.PATH_DRAFTS;
import fbbdk.data.BaseballPlayer;
import fbbdk.data.BaseballTeam;
import fbbdk.data.Draft;
import fbbdk.data.Hitter;
import fbbdk.data.Pitcher;
import fbbdk.data.Player;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

/**
 *
 * @author Tony
 */
public class JsonDraftFileManager implements DraftFileManager {
    // JSON FILE READING AND WRITING CONSTANTS

    String JSON_PLAYERS = "PLAYERS";
    String JSON_TEAMS = "TEAMS";
    String JSON_DRAFT_NAME = "DRAFT_NAME";

    String JSON_HITTER = "Hitters";
    String JSON_PITCHER = "Pitchers";

    String JSON_TITLE = "title";
    String JSON_DRAFT = "draft";

    //these are things hitters and pitchers will have
    String JSON_FIRST_NAME = "FIRST_NAME";
    String JSON_LAST_NAME = "LAST_NAME";
    String JSON_TEAM = "TEAM";
    String JSON_QP = "QP";
    String JSON_NOTES = "NOTES";
    String JSON_BIRTH_DATE = "YEAR_OF_BIRTH";
    String JSON_NATION_OF_BIRTH = "NATION_OF_BIRTH";
    String JSON_SALARY = "SALARY";
    String JSON_H = "H";
    String JSON_CONTRACT = "CONTRACT";
    String JSON_POSITION = "POSITION";

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
    String JSON_BB = "BB";
    String JSON_K = "K";

    String JSON_EXT = ".json";
    String SLASH = "/";

    String JSON_TEAM_PLAYERS = "TEAM_PLAYERS";
    String JSON_TEAM_NAME = "TEAM_NAME";
    String JSON_TEAM_OWNER = "TEAM_OWNER";
    String JSON_TEAM_HITTERS = "TEAM_HITTERS";
    String JSON_TEAM_PITCHERS = "TEAM_PITCHERS";

    /**
     * This method saves all the data associated with a course to a JSON file.
     *
     * @param draftToSave The course whose data we are saving.
     *
     * @throws IOException Thrown when there are issues writing to the JSON
     * file.
     */
    @Override
    public void saveDraft(Draft draftToSave) throws IOException {
        String draftListing = draftToSave.getDraftName();
        String jsonFilePath = PATH_DRAFTS + SLASH + draftListing + JSON_EXT;

        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);

        JsonWriter jsonWriter = Json.createWriter(os);

        // MAKE A JSON ARRAY FOR THE TEAMS ARRAY
        JsonArray teamsJsonArray = makeTeamsArray(draftToSave.getTeams());

        JsonArray hittersArray = makeHitterArray(draftToSave.getAvailablePlayers());
        JsonArray pitcherArray = makePitcherArray(draftToSave.getAvailablePlayers());
        JsonObject draftObject = Json.createObjectBuilder()
                .add(JSON_DRAFT_NAME, draftToSave.getDraftName())
                .add(JSON_TEAMS, teamsJsonArray)
                .add(JSON_HITTER, hittersArray)
                .add(JSON_PITCHER, pitcherArray)
                .build();

        jsonWriter.writeObject(draftObject);
    }

    /**
     * Loads the courseToLoad argument using the data found in the json file.
     *
     * @param draftToLoad
     * @param jsonFilePath File containing the data to load.
     *
     * @throws IOException Thrown when IO fails.
     */
    @Override
    public void loadNewDraft(Draft draftToLoad, String jsonFilePath) throws IOException {

        //first load json files
        JsonObject json = loadJSONFile(jsonFilePath);

        //now lets clear all availble players from the list
        draftToLoad.getAvailablePlayers().clear();
        //create a variable to accsess player array
        ArrayList<BaseballPlayer> players = draftToLoad.getAvailablePlayers();

        //create a json array
        JsonArray array = json.getJsonArray(JSON_HITTER);
        //populate the hitters
        loadNewHitters(players, array);

        //create new array
        array = json.getJsonArray(JSON_PITCHER);
        //now we can load the pitchers
        loadNewPitchers(players, array);

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

    private void loadHitters(ArrayList<BaseballPlayer> players, JsonArray array) {
        for (int i = 0; i < array.size(); i++) {
            JsonObject jso = array.getJsonObject(i);
            
            BaseballPlayer player = new BaseballPlayer();
            player.setFirstName(jso.getString(JSON_FIRST_NAME));
            player.setLastName(jso.getString(JSON_LAST_NAME));
            player.setMlbTeam(jso.getString(JSON_TEAM));
            player.setBirthDate(jso.getInt(JSON_BIRTH_DATE));
            player.setCountryOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            player.setNotes(jso.getString(JSON_NOTES));
            player.setPositions(jso.getString(JSON_QP));
            
            //create hitter stats then add the stats from the json file
            Hitter hitterStats = new Hitter();

            hitterStats.setHits(jso.getInt(JSON_H));
            hitterStats.setRuns(jso.getInt(JSON_R));
            hitterStats.setHomeRunes(jso.getInt(JSON_HR));
            hitterStats.setStolenBases(jso.getInt(JSON_SB));
            hitterStats.setRunsBattedIn(jso.getInt(JSON_RBI));
            hitterStats.setBa(jso.getInt(JSON_AB), jso.getInt(JSON_H));
            //now add the hitter stats to the player
            player.setHitterStats(hitterStats);
            
            players.add(player);
        }
    }

    private void loadNewHitters(ArrayList<BaseballPlayer> players, JsonArray array) {
        for (int i = 0; i < array.size(); i++) {
            JsonObject jso = array.getJsonObject(i);
            BaseballPlayer player = new BaseballPlayer();
            player.setFirstName(jso.getString(JSON_FIRST_NAME));
            player.setLastName(jso.getString(JSON_LAST_NAME));
            player.setMlbTeam(jso.getString(JSON_TEAM));
            player.setBirthDate(Integer.parseInt(jso.getString(JSON_BIRTH_DATE)));
            player.setCountryOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            player.setNotes(jso.getString(JSON_NOTES));
            player.setPositions(jso.getString(JSON_QP));

            //create hitter stats then add the stats from the json file
            Hitter hitterStats = new Hitter();

            hitterStats.setHits(Integer.parseInt(jso.getString(JSON_H)));
            hitterStats.setRuns(Integer.parseInt(jso.getString(JSON_R)));
            hitterStats.setHomeRunes(Integer.parseInt(jso.getString(JSON_HR)));
            hitterStats.setStolenBases(Integer.parseInt(jso.getString(JSON_SB)));
            hitterStats.setRunsBattedIn(Integer.parseInt(jso.getString(JSON_RBI)));
            hitterStats.setBa(Integer.parseInt(jso.getString(JSON_AB)), Integer.parseInt(jso.getString(JSON_H)));
            //now add the hitter stats to the player
            player.setHitterStats(hitterStats);
            players.add(player);
        }
    }

    private void loadPitchers(ArrayList<BaseballPlayer> players, JsonArray array) {
        for (int i = 0; i < array.size(); i++) {
            JsonObject jso = array.getJsonObject(i);
            //first add the player stuff
            BaseballPlayer player = new BaseballPlayer();
            player.setFirstName(jso.getString(JSON_FIRST_NAME));
            player.setLastName(jso.getString(JSON_LAST_NAME));
            player.setMlbTeam(jso.getString(JSON_TEAM));
            player.setBirthDate(jso.getInt(JSON_BIRTH_DATE));
            player.setCountryOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            player.setNotes(jso.getString(JSON_NOTES));

            //now add the pitcher stats
            Pitcher pitcherStats = new Pitcher();
            pitcherStats.setWhip(jso.getInt(JSON_BB), jso.getInt(JSON_H), jso.getInt(JSON_IP));
            pitcherStats.setEra(jso.getInt(JSON_ER), jso.getInt(JSON_IP));
            pitcherStats.setWins(jso.getInt(JSON_W));
            pitcherStats.setStrikeOuts(jso.getInt(JSON_K));
            pitcherStats.setSaves(jso.getInt(JSON_SV));

            //add the stats to the player
            player.setPitcherStats(pitcherStats);
            players.add(player);
        }
    }

    private void loadNewPitchers(ArrayList<BaseballPlayer> players, JsonArray array) {
        for (int i = 0; i < array.size(); i++) {
            JsonObject jso = array.getJsonObject(i);
            //first add the player stuff
            BaseballPlayer player = new BaseballPlayer();
            player.setFirstName(jso.getString(JSON_FIRST_NAME));
            player.setLastName(jso.getString(JSON_LAST_NAME));
            player.setMlbTeam(jso.getString(JSON_TEAM));
            player.setBirthDate(Integer.parseInt(jso.getString(JSON_BIRTH_DATE)));
            player.setCountryOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            player.setNotes(jso.getString(JSON_NOTES));

            //now add the pitcher stats
            Pitcher pitcherStats = new Pitcher();
            pitcherStats.setWhip(Integer.parseInt(jso.getString(JSON_BB)), Integer.parseInt(jso.getString(JSON_H)), Double.parseDouble(jso.getString(JSON_IP)));
            pitcherStats.setEra(Integer.parseInt(jso.getString(JSON_ER)), Double.parseDouble(jso.getString(JSON_IP)));
            pitcherStats.setWins(Integer.parseInt(jso.getString(JSON_W)));
            pitcherStats.setStrikeOuts(Integer.parseInt(jso.getString(JSON_K)));
            pitcherStats.setSaves(Integer.parseInt(jso.getString(JSON_SV)));

            //add the stats to the player
            player.setPitcherStats(pitcherStats);
            players.add(player);
        }
    }

    private JsonArray makeTeamsArray(ArrayList<BaseballTeam> teams) {
        JsonArrayBuilder jsPitchersBuilder = Json.createArrayBuilder();
        JsonArrayBuilder jsHittersBuilder = Json.createArrayBuilder();
        JsonArrayBuilder jsTeamsBuilder = Json.createArrayBuilder();
        //we are going to make an array of json player objects
        for (int x = 0; x < teams.size(); x++) {
            for (int y = 0; y < teams.get(x).getPlayers().size(); y++) {
                if (teams.get(x).getPlayers().get(y).isPitcher()) {
                    jsPitchersBuilder.add(buildPitcherObject(teams.get(x).getPlayers().get(y)));
                } else {
                    jsHittersBuilder.add(buildHitterObject(teams.get(x).getPlayers().get(y)));
                }

            }
            JsonArray jP = jsPitchersBuilder.build();
            JsonArray jH = jsHittersBuilder.build();
            //now we can add the entire team to an object
            JsonObject jT = Json.createObjectBuilder()
                    .add(JSON_TEAM_NAME, teams.get(x).getTeamName())
                    .add(JSON_TEAM_OWNER, teams.get(x).getCoach())
                    .add(JSON_TEAM_PITCHERS, jP)
                    .add(JSON_TEAM_HITTERS, jH)
                    .build();
            //add the object to the teams builder
            jsTeamsBuilder.add(jT);
            //now weset the players builder for the next team
            jsPitchersBuilder = Json.createArrayBuilder();
            jsHittersBuilder = Json.createArrayBuilder();
        }
        //we now return all of the teams as an array
        return jsTeamsBuilder.build();

    }

    private JsonObject buildPitcherObject(BaseballPlayer player) {
        JsonObject jso = Json.createObjectBuilder().add(JSON_FIRST_NAME, player.getFirstName())
                .add(JSON_LAST_NAME, player.getLastName())
                .add(JSON_BIRTH_DATE, player.getBirthDate())
                .add(JSON_NATION_OF_BIRTH, player.getCountryOfBirth())
                .add(JSON_CONTRACT, player.getContract())
                .add(JSON_TEAM, player.getMlbTeam())
                .add(JSON_NOTES, player.getNotes())
                .add(JSON_QP, player.getPositions())
                .add(JSON_POSITION, player.getPosition())
                .add(JSON_SALARY, player.getSalary())
                .add(JSON_BB, player.getPitcherStats().getWalks())
                .add(JSON_H, player.getPitcherStats().getHits())
                .add(JSON_IP, player.getPitcherStats().getInningsPitched())
                .add(JSON_ER, player.getPitcherStats().getEr())
                .add(JSON_W, player.getPitcherStats().getWalks())
                .add(JSON_K, player.getPitcherStats().getStrikeOuts())
                .add(JSON_SV, player.getPitcherStats().getSaves())
                .build();
        return jso;
    }

    private JsonObject buildHitterObject(BaseballPlayer player) {
        //this is the object we are building
        JsonObject jso = Json.createObjectBuilder().add(JSON_FIRST_NAME, player.getFirstName())
                .add(JSON_LAST_NAME, player.getLastName())
                .add(JSON_BIRTH_DATE, player.getBirthDate())
                .add(JSON_NATION_OF_BIRTH, player.getCountryOfBirth())
                .add(JSON_CONTRACT, player.getContract())
                .add(JSON_TEAM, player.getMlbTeam())
                .add(JSON_NOTES, player.getNotes())
                .add(JSON_QP, player.getPositions())
                .add(JSON_POSITION, player.getPosition())
                .add(JSON_SALARY, player.getSalary())
                .add(JSON_H, player.getHitterStats().getHits())
                .add(JSON_R, player.getHitterStats().getRuns())
                .add(JSON_HR, player.getHitterStats().getHomeRunes())
                .add(JSON_SB, player.getHitterStats().getStolenBases())
                .add(JSON_RBI, player.getHitterStats().getRunsBattedIn())
                .add(JSON_AB, player.getHitterStats().getAtBat())
                .build();
        return jso;
    }

    private JsonArray makeHitterArray(ArrayList<BaseballPlayer> availablePlayers) {
        JsonArrayBuilder jsHittersBuilder = Json.createArrayBuilder();
        BaseballPlayer player;
        for (int x = 0; x < availablePlayers.size(); x++) {
            player = availablePlayers.get(x);
            if (!player.isPitcher()) {
                jsHittersBuilder.add(buildAvailHitterObject(player));
            }
        }
        return jsHittersBuilder.build();

    }

    private JsonArray makePitcherArray(ArrayList<BaseballPlayer> availablePlayers) {
        JsonArrayBuilder jsPitchersBuilder = Json.createArrayBuilder();
        BaseballPlayer player;
        for (int x = 0; x < availablePlayers.size(); x++) {
            player = availablePlayers.get(x);
            if (player.isPitcher()) {
                jsPitchersBuilder.add(buildAvailPitcherObject(player));
            }
        }
        return jsPitchersBuilder.build();

    }

    private JsonObject buildAvailPitcherObject(BaseballPlayer player) {
        JsonObject jso = Json.createObjectBuilder().add(JSON_FIRST_NAME, player.getFirstName())
                .add(JSON_LAST_NAME, player.getLastName())
                .add(JSON_BIRTH_DATE, player.getBirthDate())
                .add(JSON_NATION_OF_BIRTH, player.getCountryOfBirth())
                .add(JSON_TEAM, player.getMlbTeam())
                .add(JSON_NOTES, player.getNotes())
                .add(JSON_QP, player.getPositions())
                .add(JSON_BB, player.getPitcherStats().getWalks())
                .add(JSON_H, player.getPitcherStats().getHits())
                .add(JSON_IP, player.getPitcherStats().getInningsPitched())
                .add(JSON_ER, player.getPitcherStats().getEr())
                .add(JSON_W, player.getPitcherStats().getWalks())
                .add(JSON_K, player.getPitcherStats().getStrikeOuts())
                .add(JSON_SV, player.getPitcherStats().getSaves())
                .build();
        return jso;
    }

    private JsonObject buildAvailHitterObject(BaseballPlayer player) {
        JsonObject jso = Json.createObjectBuilder().add(JSON_FIRST_NAME, player.getFirstName())
                .add(JSON_LAST_NAME, player.getLastName())
                .add(JSON_BIRTH_DATE, player.getBirthDate())
                .add(JSON_NATION_OF_BIRTH, player.getCountryOfBirth())
                .add(JSON_TEAM, player.getMlbTeam())
                .add(JSON_NOTES, player.getNotes())
                .add(JSON_QP, player.getPositions())
                .add(JSON_H, player.getHitterStats().getHits())
                .add(JSON_R, player.getHitterStats().getRuns())
                .add(JSON_HR, player.getHitterStats().getHomeRunes())
                .add(JSON_SB, player.getHitterStats().getStolenBases())
                .add(JSON_RBI, player.getHitterStats().getRunsBattedIn())
                .add(JSON_AB, player.getHitterStats().getAtBat())
                .build();
        return jso;
    }

    @Override
    public void loadDraft(Draft draftToLoad, String jsonFilePath) throws IOException {
        //first load json files
        JsonObject json = loadJSONFile(jsonFilePath);

        //now lets clear all availble players from the list
        draftToLoad.getAvailablePlayers().clear();
        draftToLoad.getObservablePlayers().clear();
        //create a variable to accsess player array
        ArrayList<BaseballPlayer> players = draftToLoad.getAvailablePlayers();
        //create a json array
        JsonArray array = json.getJsonArray(JSON_HITTER);
        //populate the hitters
        loadHitters(players, array);
        //create new array
        array = json.getJsonArray(JSON_PITCHER);
        //now we can load the pitchers
        loadPitchers(players, array);
        
        draftToLoad.getTeams().clear();
        
        ArrayList<BaseballTeam> teams = draftToLoad.getTeams();
        JsonArray jsonTeamsArray = json.getJsonArray(JSON_TEAMS);
        loadTeams(draftToLoad, jsonTeamsArray);
        draftToLoad.setDraftName(json.getString(JSON_DRAFT_NAME));
        
        //now set the observablePlayers
        draftToLoad.setObservablePlayers();
    }

    private void loadTeams(Draft draft, JsonArray array) {
      
        for (int x = 0; x < array.size(); x++) {
            JsonObject jso = array.getJsonObject(x);
             
       
            BaseballTeam team = new BaseballTeam();
            JsonArray hitter = jso.getJsonArray(JSON_TEAM_HITTERS);
            loadHitter(team, hitter);
            JsonArray pitcher = jso.getJsonArray(JSON_TEAM_PITCHERS);
            loadPitcher(team, pitcher);
            
            team.setCoach(jso.getString(JSON_TEAM_OWNER));
            team.setTeamName(jso.getString(JSON_TEAM_NAME));
            
            draft.addTeam(team);
        }
    }

    private void loadHitter(BaseballTeam players, JsonArray array) {
        for (int i = 0; i < array.size(); i++) {

            JsonObject jso = array.getJsonObject(i);
            BaseballPlayer player = new BaseballPlayer();

            player.setFirstName(jso.getString(JSON_FIRST_NAME));
            player.setLastName(jso.getString(JSON_LAST_NAME));
            player.setMlbTeam(jso.getString(JSON_TEAM));
            player.setPosition(jso.getString(JSON_POSITION));
            player.setContract(jso.getString(JSON_CONTRACT));
            player.setSalary(jso.getInt(JSON_SALARY));
            player.setBirthDate(jso.getInt(JSON_BIRTH_DATE));
            player.setCountryOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            player.setNotes(jso.getString(JSON_NOTES));
            player.setPositions(jso.getString(JSON_QP));

            //create hitter stats then add the stats from the json file
            Hitter hitterStats = new Hitter();
            hitterStats.setRuns(jso.getInt(JSON_R));
            hitterStats.setHits((jso.getInt(JSON_H)));
            hitterStats.setHomeRunes((jso.getInt(JSON_HR)));
            hitterStats.setStolenBases((jso.getInt(JSON_SB)));
            hitterStats.setRunsBattedIn((jso.getInt(JSON_RBI)));
            hitterStats.setBa((jso.getInt(JSON_AB)), (jso.getInt(JSON_H)));
            //now add the hitter stats to the player
            
            player.setHitterStats(hitterStats);
            players.addPlayer(player);
        }
    }

    private void loadPitcher(BaseballTeam players, JsonArray array) {
        for (int i = 0; i < array.size(); i++) {

            JsonObject jso = array.getJsonObject(i);
            BaseballPlayer player = new BaseballPlayer();

            player.setFirstName(jso.getString(JSON_FIRST_NAME));
            player.setLastName(jso.getString(JSON_LAST_NAME));
            player.setMlbTeam(jso.getString(JSON_TEAM));
            player.setPosition(jso.getString(JSON_POSITION));
            player.setContract(jso.getString(JSON_CONTRACT));
            player.setSalary(jso.getInt(JSON_SALARY));
            player.setBirthDate(jso.getInt(JSON_BIRTH_DATE));
            player.setCountryOfBirth(jso.getString(JSON_NATION_OF_BIRTH));
            player.setNotes(jso.getString(JSON_NOTES));
            player.setPositions(jso.getString(JSON_QP));
            //now add the pitcher stats
            Pitcher pitcherStats = new Pitcher();
            pitcherStats.setWhip((jso.getInt(JSON_BB)), (jso.getInt(JSON_H)), jso.getInt(JSON_IP));
            pitcherStats.setEra((jso.getInt(JSON_ER)), (jso.getInt(JSON_IP)));
            pitcherStats.setWins(jso.getInt(JSON_W));
            pitcherStats.setStrikeOuts(jso.getInt(JSON_K));
            pitcherStats.setSaves(jso.getInt(JSON_SV));

            //add the stats to the player
            player.setPitcherStats(pitcherStats);
            players.addPlayer(player);
            
            
        }
    }
}
