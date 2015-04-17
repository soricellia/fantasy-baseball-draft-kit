/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

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
public class Pitcher extends Player{
    
    //all of these things will go into our player table
    
    //position and qp are to match hitter stringProperties
    private StringProperty position;
    private StringProperty qp;
    private IntegerProperty wins;
    private IntegerProperty strikeOuts;
    private DoubleProperty inningsPitched;
    private IntegerProperty hits;
    private IntegerProperty saves;
    private DoubleProperty whip;
    private DoubleProperty era;

    //this is the value of the position and qp string property we want to set 
    private final String PITCHER_POSITION = "P";
    
    public Pitcher(){
        
        //need to set the position and qp of every pitcher
        //in order to match hitter data
        position = new SimpleStringProperty();
        position.set(PITCHER_POSITION);
        qp = new SimpleStringProperty();
        qp.set(PITCHER_POSITION);
        //now we init everything else
        wins = new SimpleIntegerProperty();
        strikeOuts = new SimpleIntegerProperty();
        inningsPitched = new SimpleDoubleProperty();
        hits = new SimpleIntegerProperty();
        saves = new SimpleIntegerProperty();
        whip = new SimpleDoubleProperty();
        era = new SimpleDoubleProperty();

    }
    /**
     * @return the wins
     */
    public int getWins() {
        return wins.get();
    }

    /**
     * @param wins the wins to set
     */
    public void setWins(int wins) {
        this.wins.set(wins);
    }

    /**
     * @return the strikeOuts
     */
    public int getStrikeOuts() {
        return strikeOuts.get();
    }

    /**
     * @param strikeOuts the strikeOuts to set
     */
    public void setStrikeOuts(int strikeOuts) {
        this.strikeOuts.set(strikeOuts);
    }

    /**
     * @return the inningsPitched
     */
    public double getInningsPitched() {
        return inningsPitched.get();
    }

    /**
     * @param inningsPitched the inningsPitched to set
     */
    public void setInningsPitched(double inningsPitched) {
        this.inningsPitched.set(inningsPitched);
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
     * @return the saves
     */
    public int getSaves() {
        return saves.get();
    }

    /**
     * @param saves the saves to set
     */
    public void setSaves(int saves) {
        this.saves.set(saves);
    }
    

    /**
     * @return the whip
     */
    public double getWhip() {
        return whip.get();
    }

    /**
     * calculated as walks + hits/(innings pitched)
     * @param walks
     * @param hits
     * @param ip
     */
    public void setWhip(int walks, int hits, double ip) {
        this.hits.set(hits);
        this.inningsPitched.set(ip);
        this.whip.set(walks + hits/ip);
    }

    /**
     * @return the era
     */
    public double getEra() {
        return era.get();
    }

    /**
     * @param er
     * @param ip
     */
    public void setEra(int er,double ip) {
        this.inningsPitched.set(ip);
        
        era.set(er*9/ip);
    }
    
}
