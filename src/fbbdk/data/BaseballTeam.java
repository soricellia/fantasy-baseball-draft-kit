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
public class BaseballTeam extends Team {

    
    private ArrayList<BaseballPlayer> taxiPlayers;
    private ArrayList<BaseballPlayer> players;
    
    public BaseballTeam() {
        super();
        players = new ArrayList<>();
        taxiPlayers = new ArrayList<>();
    }

    public ArrayList<BaseballPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<BaseballPlayer> players) {
        this.players = players;
    }
    public void addPlayer(BaseballPlayer player){
        players.add(player);
    }
    public void removePlayer(BaseballPlayer player){
        players.remove(player);
    }
    /**
     * @return the reserves
     */
    public ArrayList<BaseballPlayer> getTaxiPlayers() {
        return taxiPlayers;
    }

    /**
     * @param reserves the reserves to set
     */
    public void setTaxiPlayers(ArrayList<BaseballPlayer> reserves) {
        this.taxiPlayers = reserves;
    }

    /**
     * this method adds a player to the reserves list
     *
     * @param player
     */
    public void addTaxiPlayer(BaseballPlayer player) {
        this.taxiPlayers.add(player);
    }

    /**
     * this method removes a player from the reserves list
     *
     * @param player
     */
    public void removeTaxiPlayer(BaseballPlayer player) {
        this.taxiPlayers.remove(player);
    }
}
