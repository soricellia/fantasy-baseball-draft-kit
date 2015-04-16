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
public class BaseballTeam extends Team{
    private ArrayList<Hitter> hitters;
    private ArrayList<Pitcher> pitchers;
    private ArrayList<Player> reserves;

    public BaseballTeam(){
        hitters = new ArrayList<>();
        pitchers = new ArrayList<>();
        reserves = new ArrayList<>();
    }
    /**
     * @return the hitters
     */
    public ArrayList<Hitter> getHitters() {
        return hitters;
    }

    /**
     * @param hitters the hitters to set
     */
    public void setHitters(ArrayList<Hitter> hitters) {
        this.hitters = hitters;
    }
    /**
     * this method adds a hitter to the hitters list
     * @param hitter 
     */
    public void addHitter(Hitter hitter){
        this.hitters.add(hitter);
    }
    /**
     * this method removes a hitter from the hitters list
     * @param hitter 
     */
    public void removeHitter(Hitter hitter){
        this.hitters.remove(hitter);
    }
    /**
     * @return the pitchers
     */
    public ArrayList<Pitcher> getPitchers() {
        return pitchers;
    }

    /**
     * @param pitchers the pitchers to set
     */
    public void setPitchers(ArrayList<Pitcher> pitchers) {
        this.pitchers = pitchers;
    }
    /**
     * this method adds a pitcher to the pitchers list
     * @param pitcher 
     */
    public void addPitcher(Pitcher pitcher){
        this.pitchers.add(pitcher);
    }
    /**
     * this method removes a pitcher from the pitchers list
     * @param pitcher 
     */
    public void removePitcher(Pitcher pitcher){
        this.pitchers.remove(pitcher);
    }
    
    /**
     * @return the reserves
     */
    public ArrayList<Player> getReserves() {
        return reserves;
    }

    /**
     * @param reserves the reserves to set
     */
    public void setReserves(ArrayList<Player> reserves) {
        this.reserves = reserves;
    }
    /**
     * this method adds a player to the reserves list
     * @param player 
     */
    public void addReserve(Player player){
        this.reserves.add(player);
    }
    /**
     * this method removes a player from the reserves list
     * @param player 
     */
    public void removeReserve(Player player){
        this.reserves.remove(player);
    }
}
