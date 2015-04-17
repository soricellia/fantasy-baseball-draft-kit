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
    private String positon;
    private String qp;
    private int hits;
    private int stolenBases;
    private int runsBattedIn;
    private int homeRunes;
    private int runs;
    private double ba;
    
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
    public String getPositon() {
        return positon;
    }

    /**
     * @param positon the positon to set
     */
    public void setPositon(String positon) {
        this.positon = positon;
    }

    /**
     * @return the qualifyingPositions
     */
    public String getQP() {
        return qp;
    }

    /**
     * @param qp
     
     */
    public void setQP(String qp) {
        this.qp = qp;
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
     * @return the ba
     */
    public double getBa() {
        return ba;
    }

    /**
     * @param atBat number of times at bat
     * @param hits number of hits
     */
    public void setBa(int atBat,int hits) {
        if(atBat == 0)
            ba = 0.0;
        else
            ba = hits/atBat;
    }

    /**
     * @return the runs
     */
    public int getRuns() {
        return runs;
    }

    /**
     * @param runs the runs to set
     */
    public void setRuns(int runs) {
        this.runs = runs;
    }
}
