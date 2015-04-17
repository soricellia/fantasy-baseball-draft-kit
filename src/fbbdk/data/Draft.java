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
    private ArrayList<Player> availablePlayers;
    private ArrayList<Team> teams;
    private ArrayList<Player> pickOrder;
    private String draftName;
    private ObservableList<Player> observablePlayers;
    
    public Draft(){
        availablePlayers = new ArrayList<>();
        teams = new ArrayList<>();
        pickOrder = new ArrayList<>();
        observablePlayers = FXCollections.observableArrayList();
        
    }
    /**
     * @return the availablePlayers
     */
    public ArrayList<Player> getAvailablePlayers() {
        return availablePlayers;
    }
    public void setObservablePlayers(){
        getObservablePlayers().addAll(availablePlayers);
    }
    /**
     * @param availablePlayers the availablePlayers to set
     */
    public void setAvailablePlayers(ArrayList<Player> availablePlayers) {
        this.availablePlayers = availablePlayers;
    }

    /**
     * @return the teams
     */
    public ArrayList<Team> getTeams() {
        return teams;
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
    public ArrayList<Player> getPickOrder() {
        return pickOrder;
    }

    /**
     * @param pickOrder the pickOrder to set
     */
    public void setPickOrder(ArrayList<Player> pickOrder) {
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
    public ObservableList<Player> getObservablePlayers() {
        return observablePlayers;
    }

    /**
     * @param observablePlayers the observablePlayers to set
     */
    public void setObservablePlayers(ObservableList<Player> observablePlayers) {
        this.observablePlayers = observablePlayers;
    }
    
}
