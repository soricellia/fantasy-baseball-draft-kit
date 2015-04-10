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
    private int inningsPitched;
    private int hits;
    private int saves;

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
    public int getInningsPitched() {
        return inningsPitched;
    }

    /**
     * @param inningsPitched the inningsPitched to set
     */
    public void setInningsPitched(int inningsPitched) {
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
     * this method calculates and returns the walks plus
     * hits per innings pitched or WHIP stat of the pitcher
     * 
     * @return the walks + hits per innings pitched 
     */
    public Double whip(){
        return -1.0;
    }
    /**
     * NEEDS TO BE DONE STILL
     * 
     * this method calculates and returns the
     * earned run average of the pitcher
     * 
     * @return the earned run average of the pitcher 
     */
    public Double era(){
        return -1.0;
    }
}
