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
public class Pitcher{
    
    //all of these things will go into our player table
    
    //position and qp are to match hitter stringProperties
    private String position;
    private String qp;
    private int wins;
    private int strikeOuts;
    private double inningsPitched;
    private int hits;
    private int saves;
    private double whip;
    private double era;
    private int walks;
    private int er;
    //this is the value of the position and qp string property we want to set 
    private final String PITCHER_POSITION = "P";
    
    public Pitcher(){
        
        //need to set the position and qp of every pitcher
        //in order to match hitter data
        position = PITCHER_POSITION;
        qp = PITCHER_POSITION;
        //now we init everything else
       wins = 0;
       strikeOuts = 0;
       inningsPitched = 0;
       hits = 0;
       saves = 0;
       whip = 0.0;
       era = 0.0;
       walks = 0;
       er = 0;
    }
    public int getWalks(){
        return walks;
    }
    public String getPosition(){
        return position;
    }
    public int getEr(){
        return er;
    }
    /**
     * @return the wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * @param wins the wins to set
     */
    public void setWins(int wins) {
        this.wins=wins;
    }

    /**
     * @return the strikeOuts
     */
    public int getStrikeOuts() {
        return strikeOuts;
    }

    /**
     * @param strikeOuts the strikeOuts to set
     */
    public void setStrikeOuts(int strikeOuts) {
        this.strikeOuts=strikeOuts;
    }

    /**
     * @return the inningsPitched
     */
    public double getInningsPitched() {
        return inningsPitched;
    }

    /**
     * @param inningsPitched the inningsPitched to set
     */
    public void setInningsPitched(double inningsPitched) {
        this.inningsPitched=inningsPitched;
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
     * @return the saves
     */
    public int getSaves() {
        return saves;
    }

    /**
     * @param saves the saves to set
     */
    public void setSaves(int saves) {
        this.saves=saves;
    }
    

    /**
     * @return the whip
     */
    public double getWhip() {
        return whip;
    }

    /**
     * calculated as walks + hits/(innings pitched)
     * @param walks
     * @param hits
     * @param ip
     */
    public void setWhip(int walks, int hits, double ip) {
        this.walks = walks;
        this.hits=hits;
        this.inningsPitched=ip;
        if(ip == 0)
            whip = 0.0;
        else{
            this.whip= (Double.parseDouble(""+walks) + (Double.parseDouble(""+hits)/Double.parseDouble(""+ip)));
            DecimalFormat df = new DecimalFormat("##.#");
            whip = Double.parseDouble(df.format(whip));
        }
    }

    /**
     * @return the era
     */
    public double getEra() {
        return era;
    }

    /**
     * @param er
     * @param ip
     */
    public void setEra(int er,double ip) {
        this.er = er;
        this.inningsPitched=ip;
        DecimalFormat df = new DecimalFormat(".###");
        if(ip == 0){
            era = 0;
        }else{
            int number = er*9;
            era=Double.parseDouble(""+number)/Double.parseDouble(""+ip);
            era = Double.parseDouble(df.format(era));
        }
    }
    
}
