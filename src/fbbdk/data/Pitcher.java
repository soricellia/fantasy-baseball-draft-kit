/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

/**
 *
 * @author Tony
 */
public class Pitcher extends Player{
    private int wins;
    private int strikeOuts;
    private double inningsPitched;
    private int hits;
    private int saves;
    private double whip;
    private double era;

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
        this.wins = wins;
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
        this.strikeOuts = strikeOuts;
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
        this.inningsPitched = inningsPitched;
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
     * @return the saves
     */
    public int getSaves() {
        return saves;
    }

    /**
     * @param saves the saves to set
     */
    public void setSaves(int saves) {
        this.saves = saves;
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
        this.whip = walks + hits/ip;
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
        this.inningsPitched = ip;
        
        era = er*9/ip;
    }
    
}
