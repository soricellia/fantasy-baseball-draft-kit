/*s
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public class Draft {
    
    private ArrayList<BaseballPlayer> availablePlayers;
    private ArrayList<BaseballTeam> teams;
    private ArrayList<BaseballPlayer> pickOrder;
    private String draftName;
    
    private ObservableList<BaseballPlayer> observablePlayers;
    private ObservableList<BaseballTeam> observableTeams;
    private ArrayList<BaseballTeam> mlbTeams;
    
    public static String[] MLB_TEAMS = {"ATL", "AZ", "CHC", "CIN", "COL", "LAD", "MIA", "MIL",
        "NYM", "PHI", "PIT", "SD", "SF", "STL", "WAS"};
    
    public Draft() {
        availablePlayers = new ArrayList<>();
        teams = new ArrayList<>();
        pickOrder = new ArrayList<>();
        observablePlayers = FXCollections.observableArrayList();
        observableTeams = FXCollections.observableArrayList();
        constructMLBTeams();
    }
    
    public void addPlayer(BaseballPlayer player) {
        availablePlayers.add(player);
        observablePlayers.add(player);
        addPlayerToMLBTeam(player);
    }
    /**
     * this method gets the MLB team corresponding to the team name
     * this method will return null if the mlbTeam doesn't exit
     * @param name name of the mlb team
     * @return the MLB team 
     */
    private BaseballTeam getMlbTeam(String name){
        for(int x = 0 ; x < mlbTeams.size() ; x ++){
            if(mlbTeams.get(x).teamName.equals(name))
                return mlbTeams.get(x);
        }
        return null;
    }
    public ArrayList<BaseballTeam> getMlbTeams(){
        return mlbTeams;
    }
    /**
     * this will construct an empty team with team name of the mlb team name
     */
    private void constructMLBTeams() {
        for (int x = 0; x < MLB_TEAMS.length; x++) {
            BaseballTeam team = new BaseballTeam();
            team.teamName = MLB_TEAMS[x];
            mlbTeams.add(team);
        }
    }

    /**
     * this will add a player to his MLB team
     *
     * @param player the player who needs to be added to the MLB team
     */
    private void addPlayerToMLBTeam(BaseballPlayer player) {
        for (int x = 0; x < mlbTeams.size(); x++) {
            if (mlbTeams.get(x).teamName.equals(player.getMlbTeam())) {
                mlbTeams.get(x).addPlayer(player);
            }
        }
    }

    /**
     * @return the availablePlayers
     */
    public ArrayList<BaseballPlayer> getAvailablePlayers() {
        return availablePlayers;
    }

    public void setObservablePlayers() {
        getObservablePlayers().addAll(availablePlayers);
    }

    /**
     * @param availablePlayers the availablePlayers to set
     */
    public void setAvailablePlayers(ArrayList<BaseballPlayer> availablePlayers) {
        this.availablePlayers = availablePlayers;
    }

    /**
     * @return the teams
     */
    public ArrayList<BaseballTeam> getTeams() {
        return teams;
    }

    /**
     * @param teams the teams to set
     */
    public void setTeams(ArrayList<BaseballTeam> teams) {
        this.teams = teams;
    }

    /**
     * @return the pickOrder
     */
    public ArrayList<BaseballPlayer> getPickOrder() {
        return pickOrder;
    }

    /**
     * @param pickOrder the pickOrder to set
     */
    public void setPickOrder(ArrayList<BaseballPlayer> pickOrder) {
        this.pickOrder = pickOrder;
    }

    public void sortTeams() {
        //@TODO
    }

    /**
     * @return the draftName
     */
    public String getDraftName() {
        return draftName;
    }

    /**
     * @param draftName the draftName to set
     */
    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }

    public void removeTeam(BaseballTeam teamToRemove) {
        //first we want to get all the players out of the team and into the pool
        int size = teamToRemove.getPlayers().size();
        BaseballPlayer player;
        for (int x = 0; x < size; x++) {
            player = teamToRemove.getPlayers().get(0);
            teamToRemove.removePlayer(player);
            addPlayer(player);
        }
        //we are done now, remove the team
        teams.remove(teamToRemove);
        observableTeams.remove(teamToRemove);
    }

    public void addTeam(BaseballTeam teamToAdd) {
        teams.add(teamToAdd);
        observableTeams.add(teamToAdd);
    }

    public void removePlayer(BaseballPlayer player) {
        availablePlayers.remove(player);
        //MAKE SURE THIS IS REFLECTED IN THE OBSERVABLE PLAYERS LIST
        observablePlayers.remove(player);
    }

    /**
     * @return the observablePlayers
     */
    public ObservableList<BaseballPlayer> getObservablePlayers() {
        return observablePlayers;
    }
    
    public ObservableList<BaseballTeam> getObservableTeams() {
        return observableTeams;
    }

    public void setObservableTeams(ObservableList<BaseballTeam> observableList) {
        this.observableTeams = observableList;
    }

    /**
     * @param observablePlayers the observablePlayers to set
     */
    public void setObservablePlayers(ObservableList<BaseballPlayer> observablePlayers) {
        this.observablePlayers = observablePlayers;
    }
    
}
