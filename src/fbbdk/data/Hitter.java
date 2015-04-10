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
public class Hitter extends Player{
    private Position positon;
    private ArrayList<Position> qualifyingPositions;
    private int hits;
    private int stolenBases;
    private int runsBattedIn;
    private int homeRunes;
    private int walks;
    
    
    /**
     * NEEDS TO BE DONE
     * 
     * calculates and returns the batting average 
     * of the hitter
     * 
     * @return the batting average of the hitter 
     */
    private Double battingAverage(){
        return -1.0;
    }

    /**
     * @return the positon
     */
    public Position getPositon() {
        return positon;
    }

    /**
     * @param positon the positon to set
     */
    public void setPositon(Position positon) {
        this.positon = positon;
    }

    /**
     * @return the qualifyingPositions
     */
    public ArrayList<Position> getQualifyingPositions() {
        return qualifyingPositions;
    }

    /**
     * @param qualifyingPositions the qualifyingPositions to set
     */
    public void setQualifyingPositions(ArrayList<Position> qualifyingPositions) {
        this.qualifyingPositions = qualifyingPositions;
    }

    /**
     * @return the hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * @param hits the hits to set
     */
    public void setHits(int hits) {
        this.hits = hits;
    }

    /**
     * @return the stolenBases
     */
    public int getStolenBases() {
        return stolenBases;
    }

    /**
     * @param stolenBases the stolenBases to set
     */
    public void setStolenBases(int stolenBases) {
        this.stolenBases = stolenBases;
    }

    /**
     * @return the runsBattedIn
     */
    public int getRunsBattedIn() {
        return runsBattedIn;
    }

    /**
     * @param runsBattedIn the runsBattedIn to set
     */
    public void setRunsBattedIn(int runsBattedIn) {
        this.runsBattedIn = runsBattedIn;
    }

    /**
     * @return the homeRunes
     */
    public int getHomeRunes() {
        return homeRunes;
    }

    /**
     * @param homeRunes the homeRunes to set
     */
    public void setHomeRunes(int homeRunes) {
        this.homeRunes = homeRunes;
    }

    /**
     * @return the walks
     */
    public int getWalks() {
        return walks;
    }

    /**
     * @param walks the walks to set
     */
    public void setWalks(int walks) {
        this.walks = walks;
    }
}
