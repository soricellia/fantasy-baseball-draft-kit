/*
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
   

    public Draft(){
        availablePlayers = new ArrayList<>();
        teams = new ArrayList<>();
        pickOrder = new ArrayList<>();
        observablePlayers = FXCollections.observableArrayList();
    }
    
     public static void addPlayer(BaseballPlayer player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * @return the availablePlayers
     */
    public ArrayList<BaseballPlayer> getAvailablePlayers() {
        return availablePlayers;
    }
    public void setObservablePlayers(){
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
    public void sortTeams(){
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
    public void removeTeam(BaseballTeam teamToRemove){
        teams.remove(teamToRemove);
        }
    public void addTeam(BaseballTeam teamToAdd){
        teams.add(teamToAdd);
    }
    public void removePlayer(BaseballPlayer player){
        availablePlayers.remove(player);
    }
    /**
     * @return the observablePlayers
     */
    public ObservableList<BaseballPlayer> getObservablePlayers() {
        return observablePlayers;
    }

    /**
     * @param observablePlayers the observablePlayers to set
     */
    public void setObservablePlayers(ObservableList<BaseballPlayer> observablePlayers) {
        this.observablePlayers = observablePlayers;
    }
    
}
