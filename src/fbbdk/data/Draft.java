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
    private ArrayList<Team> teams;
    private ArrayList<BaseballPlayer> pickOrder;
    private String draftName;
    
    private ObservableList<BaseballPlayer> observablePlayers;
    private ObservableList<String> teamNames;
    
    public Draft(){
        availablePlayers = new ArrayList<>();
        teams = new ArrayList<>();
        pickOrder = new ArrayList<>();
        observablePlayers = FXCollections.observableArrayList();
        teamNames = FXCollections.observableArrayList();
        
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
    public ArrayList<Team> getTeams() {
        return teams;
    }
    public ObservableList<String> getTeamNames(){
        return teamNames;
    }
    /**
     * @param teams the teams to set
     */
    public void setTeams(ArrayList<Team> teams) {
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
