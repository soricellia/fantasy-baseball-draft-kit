/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.text.DecimalFormat;

/**
 *
 * @author Tony
 */
public class Hitter{
    
    //these will go into our player table
    private String position;
    private String qp;
    private int hits;
    private int stolenBases;
    private int runsBattedIn;
    private int homeRunes;
    private int runs;
    private int atBat;
    private double ba;
    
    public Hitter(){
        position = "";
        qp = "";
        hits = 0;
        stolenBases = 0;
        runsBattedIn = 0;
        homeRunes = 0;
        runs = 0;
        ba = 0.0;
    
    }

    /**
     * @return the position
     */
    public String getPositon() {
        return position;
    }

    /**
     * @param positon the position to set
     */
    public void setPosition(String positon) {
        this.position = positon;
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
        this.qp=qp;
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
        this.hits=hits;
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
        this.stolenBases=stolenBases;
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
        this.runsBattedIn=runsBattedIn;
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
        this.homeRunes=homeRunes;
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
        this.atBat = atBat;
        this.hits = hits;
        if(atBat == 0){
            ba = 0.0;
        }else{
            ba = Double.parseDouble(""+hits)/Double.parseDouble(""+atBat);
            DecimalFormat df = new DecimalFormat(".###");
            ba = Double.parseDouble(df.format(ba));
            
        }
        
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
        this.runs=runs;
    }

    /**
     * @return the atBat
     */
    public int getAtBat() {
        return atBat;
    }

    /**
     * @param atBat the atBat to set
     */
    public void setAtBat(int atBat) {
        this.atBat = atBat;
    }
}
