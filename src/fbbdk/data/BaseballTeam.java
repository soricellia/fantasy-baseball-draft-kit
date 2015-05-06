/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public class BaseballTeam extends Team {

    //this is the data 
    private ArrayList<BaseballPlayer> taxiPlayers;
    private ArrayList<BaseballPlayer> players;

    //this is the observable data
    private ObservableList<BaseballPlayer> observablePlayers;
    private ObservableList<BaseballPlayer> observableTaxiPlayers;
    private StringProperty observableTeamName;
    private IntegerProperty playersNeeded;
    private IntegerProperty obvMoneyLeft;
    private IntegerProperty moneyPerPlayer;
    private IntegerProperty runs;
    private IntegerProperty hr;
    private IntegerProperty rbi;
    private IntegerProperty obvSb;
    private DoubleProperty ba;
    private IntegerProperty wins;
    private IntegerProperty sv;
    private IntegerProperty k;
    private DoubleProperty era;
    private DoubleProperty whip;
    private IntegerProperty totalPoints;

    //this will be our team size
    private int teamSize;
    //and this is the max team size 
    public static final int MAX_TEAM_SIZE = 23;

    //this is to see which posiiton a player is as well as to tell
    //which positions the team needs
    private static final String C = "C";
    private static final String FB = "1B";
    private static final String SB = "2B";
    private static final String TB = "3B";
    private static final String CI = "CI";
    private static final String SS = "SS";
    private static final String MI = "MI";
    private static final String OF = "OF";
    private static final String U = "U";
    private static final String P = "P";
    private static final String UNDER_SCORE = "_";

    //this is to keep track of what positons are on the team
    private int c = 0;
    private int fb = 0;
    private int ci = 0;
    private int tb = 0;
    private int sb = 0;
    private int mi = 0;
    private int ss = 0;
    private int of = 0;
    private int u = 0;
    private int p = 0;

    private int moneySpent;
    private static final int MAX_MONEY = 260;

    public BaseballTeam() {
        super();
        teamSize = 0;
        players = new ArrayList<>();
        taxiPlayers = new ArrayList<>();
        observablePlayers = FXCollections.observableArrayList();
        observableTaxiPlayers = FXCollections.observableArrayList();
        observableTeamName = new SimpleStringProperty();
        playersNeeded = new SimpleIntegerProperty();
        obvMoneyLeft = new SimpleIntegerProperty();
        moneyPerPlayer = new SimpleIntegerProperty();
        runs = new SimpleIntegerProperty();
        hr = new SimpleIntegerProperty();
        rbi = new SimpleIntegerProperty();
        obvSb = new SimpleIntegerProperty();
        ba = new SimpleDoubleProperty();
        wins = new SimpleIntegerProperty();
        sv = new SimpleIntegerProperty();
        k = new SimpleIntegerProperty();
        era = new SimpleDoubleProperty();
        whip = new SimpleDoubleProperty();
        totalPoints = new SimpleIntegerProperty();
    }

    @Override
    public void setTeamName(String name) {
        teamName = name;
        observableTeamName.set(name);

    }

    public String getObservableTeamName() {
        return observableTeamName.get();
    }

    /**
     *
     * @param teamName
     */
    public void setObservableTeamName(String teamName) {
        observableTeamName.set(teamName);
    }

    public int getPlayersNeeded() {
        return playersNeeded.get();
    }

    public void setPlayersNeeded(int playersNeeded) {
        this.playersNeeded.set(playersNeeded);
    }

    public int getObvMoneyLeft() {
        return obvMoneyLeft.get();
    }

    public void setObvMoneyLeft(int money) {
        obvMoneyLeft.set(money);
    }

    /**
     * this method is to calculate the maximum amount of money allowed to spend
     * on a player in order to never have a negative balance
     *
     * @return the maximum amount of money allowed to spend on a player in order
     * to never have a negative balance
     */
    public int maxSalaryAllowance() {
        return (MAX_MONEY - moneySpent) - (MAX_TEAM_SIZE - teamSize);
    }

    /**
     * @return the money per player for starting players still required
     */
    public int getMoneyPerPlayer() {
        return moneyPerPlayer.get();
    }

    public void setMoneyPerPlayer(int moneyPerPlayer) {
        this.moneyPerPlayer.set(moneyPerPlayer);
    }

    /**
     *
     * @return the total runs on this team
     */
    public int getRuns() {
        return runs.get();
    }

    public void setRuns(int runs) {
        this.runs.set(runs);
    }

    public int getHr() {
        return hr.get();
    }

    public void setHr(int hr) {
        this.hr.set(hr);
    }

    public int getRbi() {
        return rbi.get();
    }

    public void setRbi(int rbi) {
        this.rbi.set(rbi);
    }

    public int getObvSb() {
        return obvSb.get();
    }

    public void setObvSb(int sb) {
        this.obvSb.set(sb);
    }

    public double getBa() {
        DecimalFormat df = new DecimalFormat(".###");
        return Double.parseDouble(df.format(ba.get()));
    }

    public void setBa(double ba) {
        this.ba.set(ba);
    }

    public int getWins() {
        return wins.get();
    }

    public void setWins(int wins) {
        this.wins.set(wins);
    }

    public int getSv() {
        return sv.get();
    }

    public void setSv(int sv) {
        this.sv.set(sv);
    }

    public int getK() {
        return k.get();
    }

    public void setK(int k) {
        this.k.set(k);
    }

    public double getEra() {
      
        DecimalFormat df = new DecimalFormat("#.##");

        //we want the format nice and pretty
        return Double.parseDouble(df.format(era.get()));
    }

    public void setEra(double era) {
        this.era.set(era);
    }

    public double getWhip() {
    
        DecimalFormat df = new DecimalFormat("#.##");
        //we want the whip to be formated nie and pretty
        return Double.parseDouble(df.format(whip.get()));
    }

    public void setWhip(double whip) {
        this.whip.set(whip);
    }

    public int getTotalPoints() {
        return totalPoints.get();
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints.set(totalPoints);
    }

    public ObservableList<BaseballPlayer> getObservablePlayers() {
        return observablePlayers;
    }

    public ObservableList<BaseballPlayer> getObservableTaxiPlayers() {
        return observableTaxiPlayers;
    }

    public ArrayList<BaseballPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<BaseballPlayer> players) {
        this.players = players;
        observablePlayers.addAll(players);
    }

    public void addPlayer(BaseballPlayer player) {
        //this is for duplicates
        if (players.contains(player)) {
            removePlayer(player);
        }

        //now lets add the player
        players.add(player);
        observablePlayers.add(player);
        teamSize++;

        //we need to update the team statistics
        //so we check what type of player we are dealing with
        if (player.isPitcher()) {
            //we need to add the pitcher stats
            wins.set(wins.get() + player.getPitcherStats().getWins());
            sv.set(sv.get() + player.getPitcherStats().getSaves());
            k.set(k.get() + player.getPitcherStats().getStrikeOuts());

            //we need to set the era and the whip to an average
            if(p !=  0){
            era.set((era.get() * (p - 1) + player.getPitcherStats().getEra()) / p);
            whip.set((whip.get() * (p - 1) + player.getPitcherStats().getWhip()) / p);
            }else{
                era.set(player.getPitcherStats().getEra());
                whip.set(player.getPitcherStats().getWhip());
            }
        } else {
            //we need to add the hitter stats
            runs.set(runs.get() + player.getHitterStats().getRuns());
            hr.set(hr.get() + player.getHitterStats().getHomeRunes());
            rbi.set(rbi.get() + player.getHitterStats().getRunsBattedIn());
            obvSb.set(obvSb.get() + player.getHitterStats().getStolenBases());
            if (teamSize != 0) {
                //this will set the ba to the average BA for the team statistics 
                ba.set((ba.get() * (teamSize - p - 1) + player.getHitterStats().getBa()) / (teamSize - p));
            } else {
                ba.set(ba.get() + player.getHitterStats().getBa());

            }
        }

        //set the amount of money left
        moneySpent += player.getSalary();
        obvMoneyLeft.set(MAX_MONEY - moneySpent);

        //now we set players needed
        playersNeeded.set(MAX_TEAM_SIZE - teamSize);
        //now we set the money per player
        if (playersNeeded.get() == 0) {
            moneyPerPlayer.set(0);
        } else {
            moneyPerPlayer.set(obvMoneyLeft.get() / playersNeeded.get());
        }
        //and now we can set the position slot
        addPlayerPositionSlot(player);

        //the team needs to be sorted in the correct order, so lets sort it
        sortTeam();
    }

    public void removePlayer(BaseballPlayer player) {
        //lets remove the player
        players.remove(player);
        observablePlayers.remove(player);
        removePlayerPositionSlot(player);
        teamSize--;

        //so we check what type of player we are dealing with
        if (player.isPitcher()) {
            //we need to add the pitcher stats
            wins.set(wins.get() - player.getPitcherStats().getWins());
            sv.set(sv.get() - player.getPitcherStats().getSaves());
            k.set(k.get() - player.getPitcherStats().getStrikeOuts());
            era.set(era.get() - player.getPitcherStats().getEra());
            whip.set(whip.get() - player.getPitcherStats().getWhip());
        } else {
            //we need to add the hitter stats
            runs.set(runs.get() - player.getHitterStats().getRuns());
            hr.set(hr.get() - player.getHitterStats().getHomeRunes());
            rbi.set(rbi.get() - player.getHitterStats().getRunsBattedIn());
            obvSb.set(obvSb.get() - player.getHitterStats().getStolenBases());
            ba.set(ba.get() - player.getHitterStats().getBa());
        }

        //set the amount of money left
        moneySpent -= player.getSalary();
        obvMoneyLeft.set(MAX_MONEY - moneySpent);

        //now we set players needed
        playersNeeded.set(MAX_TEAM_SIZE - teamSize);

        //now we set the money per player
        moneyPerPlayer.set(obvMoneyLeft.get() / playersNeeded.get());

        sortTeam();
    }

    private void addPlayerPositionSlot(BaseballPlayer player) {

        if (player.getPosition().equals(C)) {
            c++;
        }
        if (player.getPosition().equals(FB)) {
            fb++;
        }
        if (player.getPosition().equals(CI)) {
            ci++;
        }
        if (player.getPosition().equals(TB)) {
            tb++;
        }
        if (player.getPosition().equals(SB)) {
            sb++;
        }
        if (player.getPosition().equals(MI)) {
            mi++;
        }
        if (player.getPosition().equals(SS)) {
            ss++;
        }
        if (player.getPosition().equals(OF)) {
            of++;
        }
        if (player.getPosition().equals(U)) {
            u++;
        }
        if (player.getPosition().equals(P)) {
            p++;
        }

    }

    private void removePlayerPositionSlot(BaseballPlayer player) {
        if (player.getPosition().equals(C)) {
            c--;
        }
        if (player.getPosition().equals(FB)) {
            fb--;
        }
        if (player.getPosition().equals(CI)) {
            ci--;
        }
        if (player.getPosition().equals(TB)) {
            tb--;
        }
        if (player.getPosition().equals(SB)) {
            sb--;
        }
        if (player.getPosition().equals(MI)) {
            mi--;
        }
        if (player.getPosition().equals(SS)) {
            ss--;
        }
        if (player.getPosition().equals(U)) {
            u--;
        }
        if (player.getPosition().equals(OF)) {
            of--;
        }
        if (player.getPosition().equals(P)) {
            p--;
        }

    }

    /**
     * @return the reserves
     */
    public ArrayList<BaseballPlayer> getTaxiPlayers() {
        return taxiPlayers;
    }

    /**
     * @param reserves the reserves to set
     */
    public void setTaxiPlayers(ArrayList<BaseballPlayer> reserves) {
        this.taxiPlayers = reserves;
    }

    /**
     * this method adds a player to the reserves list
     *
     * @param player
     */
    public void addTaxiPlayer(BaseballPlayer player) {
        this.taxiPlayers.add(player);
    }

    /**
     * this method removes a player from the reserves list
     *
     * @param player
     */
    public void removeTaxiPlayer(BaseballPlayer player) {
        this.taxiPlayers.remove(player);
    }

    private void countPositions() {
        c = fb = ci = tb = sb = mi = ss = of = u = p = 0;
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(C)) {
                c++;
            }
            if (players.get(x).getPosition().equals(FB)) {
                fb++;
            }
            if (players.get(x).getPosition().equals(CI)) {
                ci++;
            }
            if (players.get(x).getPosition().equals(TB)) {
                tb++;
            }
            if (players.get(x).getPosition().equals(SB)) {
                sb++;
            }
            if (players.get(x).getPosition().equals(MI)) {
                mi++;
            }
            if (players.get(x).getPosition().equals(SS)) {
                ss++;
            }
            if (players.get(x).getPosition().equals(OF)) {
                of++;
            }
            if (players.get(x).getPosition().equals(U)) {
                u++;
            }
            if (players.get(x).getPosition().equals(P)) {
                p++;
            }
        }
    }

    /**
     * this will calculate the positions needed for each team there are exactly
     * 23 starting players and each of these aforementioned positions has a
     * specific allotment per team 2 for C; 1 each for 1B, CI, 3B, 2B, MI, SS, &
     * U; 5 for OF; 9 for P
     *
     * @return the positions needed for this team ; null if no position is
     * needed.
     */
    public String getNeededPlayerPositions() {
        //first we check the teams size
        if (teamSize == MAX_TEAM_SIZE) {
            return null;
        }
        String positionsNeeded = "";
        //utility is a special case so we check this first

        if (c < 2) {
            positionsNeeded = C + UNDER_SCORE;
        }
        if (ci < 1) {
            positionsNeeded += (CI + UNDER_SCORE);
        }
        if (fb < 1) {
            positionsNeeded += (FB + UNDER_SCORE);
        }
        if (tb < 1) {
            positionsNeeded += (TB + UNDER_SCORE);
        }
        if (mi < 1) {
            positionsNeeded += (MI + UNDER_SCORE);
        }
        if (sb < 1) {
            positionsNeeded += (SB + UNDER_SCORE);
        }
        if (ss < 1) {
            positionsNeeded += (SS + UNDER_SCORE);
        }
        if (p < 9) {
            positionsNeeded += (P + UNDER_SCORE);
        }
        if (of < 5) {
            positionsNeeded += (OF + UNDER_SCORE);
        }
        if (u < 1) {
            positionsNeeded += U;
        }

        if (positionsNeeded.endsWith(UNDER_SCORE)) {
            positionsNeeded = positionsNeeded.substring(0, positionsNeeded.length() - 1);
        }
        return positionsNeeded;
    }

    /**
     * positions has a specific allotment per team 2 for C; 1 each for 1B, CI,
     * 3B, 2B, MI, SS, & U; 5 for OF; 9 for P
     *
     * @param player
     * @return returns true if the player can be used
     */
    public Boolean canUsePlayer(BaseballPlayer player) {

        if (teamSize == 23) {
            return false;
        }

        if (player.getPositions().contains(P)) {
            if (p < 9) {
                return true;
            } else {
                return false;
            }
        }

        if (u < 1) {
            return true;
        }

        String positions = player.getPositions();
        String[] posArray = positions.split(UNDER_SCORE);
        for (int x = 0; x < posArray.length; x++) {
            if (posArray[x].equals(C)) {
                if (c < 2) {
                    return true;
                }
            }
            if (posArray[x].equals(FB)) {
                if (fb < 1) {
                    return true;
                }
                if (ci < 1) {
                    return true;
                }
            }
            if (posArray[x].equals(TB)) {
                if (ci < 1) {
                    return true;
                }
                if (tb < 1) {
                    return true;
                }
            }
            if (posArray[x].equals(SB)) {
                if (sb < 1) {
                    return true;
                }
                if (mi < 1) {
                    return true;
                }
            }
            if (posArray[x].equals(SS)) {
                if (ss < 1) {
                    return true;
                }
                if (mi < 1) {
                    return true;
                }
            }
            if (posArray[x].equals(OF)) {
                if (of < 5) {
                    return true;
                }
            }
        }//end for loop

        return false;
    }

    /**
     * this method sorts the team. The order of players must be C, 1B, CI, 3B,
     * 2B, MI, SS, OF, U, P
     */
    public void sortTeam() {
        ArrayList<BaseballPlayer> correctList = new ArrayList<>();
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(C)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(FB)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(CI)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(TB)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(SB)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(MI)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(SS)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(OF)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(U)) {
                correctList.add(players.get(x));
            }
        }
        for (int x = 0; x < players.size(); x++) {
            if (players.get(x).getPosition().equals(P)) {
                correctList.add(players.get(x));
            }
        }
        players = correctList;
        observablePlayers.clear();
        observablePlayers.setAll(correctList);
        countPositions();
    }

}
