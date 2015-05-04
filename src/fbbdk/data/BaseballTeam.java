/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public class BaseballTeam extends Team {

    private ArrayList<BaseballPlayer> taxiPlayers;
    private ArrayList<BaseballPlayer> players;

    private ObservableList<BaseballPlayer> observablePlayers;
    private ObservableList<BaseballPlayer> observableTaxiPlayers;

    private int teamSize;
    public static final int MAX_TEAM_SIZE = 23;

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

    public BaseballTeam() {
        super();
        teamSize = 0;
        players = new ArrayList<>();
        taxiPlayers = new ArrayList<>();
        observablePlayers = FXCollections.observableArrayList();
        observableTaxiPlayers = FXCollections.observableArrayList();
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
        players.add(player);
        observablePlayers.add(player);
        teamSize++;
        addPlayerPositionSlot(player);
        sortTeam();
    }

    public void removePlayer(BaseballPlayer player) {
        players.remove(player);
        observablePlayers.remove(player);
        removePlayerPositionSlot(player);
        teamSize--;
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
            }
            else{
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
            if(posArray[x].equals(OF)){
                if(of < 5){
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
