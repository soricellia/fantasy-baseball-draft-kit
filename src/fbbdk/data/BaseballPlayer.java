/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TableColumn;

/**
 * this class is to hold all the player data as well as the hitter and pitcher stats
 * @author Tony
 */
public class BaseballPlayer extends Player{
    Hitter hitterStats;
    Pitcher pitcherStats;
    
    //these are the stats that need to be presented. 
    IntegerProperty winRun;
    IntegerProperty savesHR;
    IntegerProperty kRBI;
    DoubleProperty eraSB;
    DoubleProperty whipBA;
    
    //in fantasy baseball a player can only be a pitcher or hitter
    boolean isPitcher;
    
    public BaseballPlayer(){
        hitterStats = new Hitter();
        pitcherStats = new Pitcher();
        //this will only become false if we add hitter stats       
        isPitcher = true;
        //now init our properties
        winRun = new SimpleIntegerProperty();
        savesHR = new SimpleIntegerProperty();
        kRBI = new SimpleIntegerProperty();
        eraSB = new SimpleDoubleProperty();
        whipBA = new SimpleDoubleProperty();
    
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.hitterStats);
        hash = 29 * hash + Objects.hashCode(this.pitcherStats);
        return hash;
    }

    public int getWinRun(){
        return winRun.get();
    }
    public int getSavesHR(){
        return savesHR.get();
    }
    public int getKRBI(){
        return kRBI.get();
    }
    public double getEraSB(){
        return eraSB.get();
    }
    public double getWhipBA(){
        return whipBA.get();
    }
            
    public Hitter getHitterStats(){
        return hitterStats;
    }
    public Pitcher getPitcherStats(){
        return pitcherStats;
    }
    /**
     * this will set the hitter stats as well as 
     * @param hitter 
     */
    public void setHitterStats(Hitter hitter){
        hitterStats = hitter;
        
        winRun.set(hitter.getRuns());
        savesHR.set(hitter.getHomeRunes());
        kRBI.set(hitter.getRunsBattedIn());
        eraSB.set(hitter.getStolenBases());
        whipBA.set(hitter.getBa());
        
        isPitcher = false;
    }
    public void setPitcherStats(Pitcher pitcher){
        pitcherStats = pitcher;
        
        winRun.set(pitcher.getWins());
        savesHR.set(pitcher.getSaves());
        kRBI.set(pitcher.getStrikeOuts());
        eraSB.set(pitcher.getEra());
        whipBA.set(pitcher.getWhip());
        
        //make sure to set the positon of player to pitcher
        super.setPositions(pitcher.getPosition());
        
    }
    
    
}
