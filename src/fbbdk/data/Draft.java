/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.util.ArrayList;

/**
 *
 * @author Tony
 */
public class Draft {
    private ArrayList<Player> availablePlayers;
    private ArrayList<Team> teams;
    private ArrayList<Player> pickOrder;
    private String draftName;
    /**
     * @return the availablePlayers
     */
    public ArrayList<Player> getAvailablePlayers() {
        return availablePlayers;
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
    
}
