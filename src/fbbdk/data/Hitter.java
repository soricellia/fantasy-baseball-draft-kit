/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Tony
 */
public class Hitter extends Player{
    
    //these will go into our player table
    private StringProperty positon;
    private StringProperty qp;
    private IntegerProperty hits;
    private IntegerProperty stolenBases;
    private IntegerProperty runsBattedIn;
    private IntegerProperty homeRunes;
    private IntegerProperty runs;
    private DoubleProperty ba;
    
    public Hitter(){
        positon = new SimpleStringProperty();
        qp = new SimpleStringProperty();
        hits = new SimpleIntegerProperty();
        stolenBases = new SimpleIntegerProperty();
        runsBattedIn = new SimpleIntegerProperty();
        homeRunes = new SimpleIntegerProperty();
        runs = new SimpleIntegerProperty();
        ba = new SimpleDoubleProperty();
    
    }

    /**
     * @return the position
     */
    public String getPositon() {
        return positon.get();
    }

    /**
     * @param positon the position to set
     */
    public void setPositon(String positon) {
        this.positon.set(positon);
    }

    /**
     * @return the qualifyingPositions
     */
    public String getQP() {
        return qp.get();
    }

    /**
     * @param qp
     
     */
    public void setQP(String qp) {
        this.qp.set(qp);
    }

    /**
     * @return the hits
     */
    public int getHits() {
        return hits.get();
    }

    /**
     * @param hits the hits to set
     */
    public void setHits(int hits) {
        this.hits.set(hits);
    }

    /**
     * @return the stolenBases
     */
    public int getStolenBases() {
        return stolenBases.get();
    }

    /**
     * @param stolenBases the stolenBases to set
     */
    public void setStolenBases(int stolenBases) {
        this.stolenBases.set(stolenBases);
    }

    /**
     * @return the runsBattedIn
     */
    public int getRunsBattedIn() {
        return runsBattedIn.get();
    }

    /**
     * @param runsBattedIn the runsBattedIn to set
     */
    public void setRunsBattedIn(int runsBattedIn) {
        this.runsBattedIn.set(runsBattedIn);
    }

    /**
     * @return the homeRunes
     */
    public int getHomeRunes() {
        return homeRunes.get();
    }

    /**
     * @param homeRunes the homeRunes to set
     */
    public void setHomeRunes(int homeRunes) {
        this.homeRunes.set(homeRunes);
    }

    /**
     * @return the ba
     */
    public double getBa() {
        return ba.get();
    }

    /**
     * @param atBat number of times at bat
     * @param hits number of hits
     */
    public void setBa(int atBat,int hits) {
        if(atBat == 0){
            ba.set(0.0);
        }else{
            ba.set(hits/atBat);
        }
    }

    /**
     * @return the runs
     */
    public int getRuns() {
        return runs.get();
    }

    /**
     * @param runs the runs to set
     */
    public void setRuns(int runs) {
        this.runs.set(runs);
    }
}
