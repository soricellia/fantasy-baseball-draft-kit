/*s
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.DynamicAny.NameValuePair;

/**
 *
 * @author Tony
 */
public class Draft {

    private ArrayList<BaseballPlayer> availablePlayers;
    private ArrayList<BaseballTeam> teams;
    private ArrayList<BaseballPlayer> pickOrder;
    private String draftName;

    private ObservableList<BaseballPlayer> observablePlayers;
    private ObservableList<BaseballTeam> observableTeams;
    private ArrayList<BaseballTeam> mlbTeams;

    private static final String[] MLB_TEAMS = {"ATL", "AZ", "CHC", "CIN", "COL",
        "LAD", "MIA", "MIL", "NYM", "PHI", "PIT", "SD", "SF", "STL", "WSH"};
    //we need to make lists to decide who is in what place
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> rStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> hrStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> rbiStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> sbStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> baStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> wStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> svStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> kStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> eraStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam,Integer>> whipStandingsList;

    public Draft() {
        availablePlayers = new ArrayList<>();
        teams = new ArrayList<>();
        pickOrder = new ArrayList<>();
        observablePlayers = FXCollections.observableArrayList();
        observableTeams = FXCollections.observableArrayList();
        mlbTeams = new ArrayList<>();
        constructMLBTeams();
        rStandingsList = new TreeSet<>();
        hrStandingsList = new TreeSet<>();
        rbiStandingsList = new TreeSet<>();
        sbStandingsList = new TreeSet<>();
        baStandingsList = new TreeSet<>();
        wStandingsList = new TreeSet<>();
        svStandingsList = new TreeSet<>();
        kStandingsList = new TreeSet<>();
        eraStandingsList = new TreeSet<>();
        whipStandingsList = new TreeSet<>();
    }

    /**
     * this calculates the total number of fantasy league points the team would
     * earn if the season were compiled using the players currently on each
     * team's roster using last year's stats. Note that a team gets points equal
     * to the number of fantasy teams in the league for winning one of the 10
     * categories. So, if there are 12 teams, and one were to win every
     * category, that team would earn 10 X 12 = 120 points. If one were to
     * finish 2nd in each category it would be 10 X 11 = 110 points. If one were
     * to finish last in each category it would be 10 X 1 = 12 points. Of
     * course, each category is scored separately.
     */
    public void calculateTotalPoints() {
        //first i am going to put each of the teams into a standing list
        rStandingsList = new TreeSet<>();
        hrStandingsList = new TreeSet<>();
        rbiStandingsList = new TreeSet<>();
        sbStandingsList = new TreeSet<>();
        baStandingsList = new TreeSet<>();
        wStandingsList = new TreeSet<>();
        svStandingsList = new TreeSet<>();
        kStandingsList = new TreeSet<>();
        eraStandingsList = new TreeSet<>();
        whipStandingsList = new TreeSet<>();
        ObjectValuePair ovp;
        for (int x = 0; x < teams.size(); x++) {
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getRuns());
            if (!rStandingsList.contains(ovp)) {
                rStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getHr());
            if (!hrStandingsList.contains(ovp)) {
                hrStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getRbi());
            if (!rbiStandingsList.contains(ovp)) {
                rbiStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getObvSb());
            if (!sbStandingsList.contains(ovp)) {
                sbStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getBa());
            if (!baStandingsList.contains(ovp)) {
                baStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getWins());
            if (!wStandingsList.contains(ovp)) {
                wStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getSv());
            if (!svStandingsList.contains(ovp)) {
                svStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getK());
            if (!kStandingsList.contains(ovp)) {
                kStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getEra());
            if (!eraStandingsList.contains(ovp)) {
                eraStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x),teams.get(x).getWhip());
            if (!whipStandingsList.contains(ovp)) {
                whipStandingsList.add(ovp);
            }
        }
        //now that i have the teams standing i need to set the correct value
        //for the total points

        //we are going to need to clear the teams points for the calculation
        for (int x = 0; x < teams.size(); x++) {
            teams.get(x).setTotalPoints(0);
        }

        //these guys will help us iterate through the standings lists
        Iterator<ObjectValuePair<BaseballTeam,Integer>> i;
        BaseballTeam team;

        //now we start the calculations 
        i = rStandingsList.iterator();
        for (int y = 1; y < rStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
            System.out.println(rStandingsList.size() - y);
        }
        i = hrStandingsList.iterator();
        for (int y = 1; y < hrStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
            System.out.println(hrStandingsList.size() - y);
        }
        i = rbiStandingsList.iterator();
        for (int y = 1; y < rbiStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
            System.out.println(rbiStandingsList.size() - y);
        }
        i = sbStandingsList.iterator();
        for (int y = 1; y < sbStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
            System.out.println(sbStandingsList.size() - y);
        }
        i = baStandingsList.iterator();
        for (int y = 1; y < baStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
            System.out.println(baStandingsList.size() - y);
        }
        i = wStandingsList.iterator();
        for (int y = 1; y < wStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
            System.out.println(wStandingsList.size() - y);
        }
        i = svStandingsList.iterator();
        for (int y = 1; y < svStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
            System.out.println(svStandingsList.size() - y);
        }
        i = kStandingsList.iterator();
        for (int y = 1; y < kStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
            System.out.println(kStandingsList.size() - y);
        }
        i = eraStandingsList.iterator();
        for (int y = 0; y < eraStandingsList.size(); y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + eraStandingsList.size() - y);
            System.out.println(eraStandingsList.size() - y);
        }
        i = whipStandingsList.iterator();
        for (int y = 0; y < whipStandingsList.size(); y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + whipStandingsList.size() - y);
            System.out.println(whipStandingsList.size() - y);
        }
    }

    public void addPlayer(BaseballPlayer player) {
        availablePlayers.add(player);
        observablePlayers.add(player);
        addPlayerToMLBTeam(player);
        calculateTotalPoints();
    }

    /**
     * this method gets the MLB team corresponding to the team name this method
     * will return null if the mlbTeam doesn't exit
     *
     * @param name name of the mlb team
     * @return the MLB team
     */
    public BaseballTeam getMlbTeam(String name) {
        for (int x = 0; x < mlbTeams.size(); x++) {
            if (mlbTeams.get(x).teamName.equals(name)) {
                return mlbTeams.get(x);
            }
        }
        return null;
    }

    public ArrayList<BaseballTeam> getMlbTeams() {
        return mlbTeams;
    }

    /**
     * this will construct an empty team with team name of the mlb team name
     */
    private void constructMLBTeams() {
        for (int x = 0; x < MLB_TEAMS.length; x++) {
            BaseballTeam team = new BaseballTeam();
            team.teamName = MLB_TEAMS[x];
            mlbTeams.add(team);
        }
    }

    /**
     * this will add a player to his MLB team
     *
     * @param player the player who needs to be added to the MLB team
     */
    private void addPlayerToMLBTeam(BaseballPlayer player) {
        for (int x = 0; x < mlbTeams.size(); x++) {
            if (mlbTeams.get(x).teamName.equals(player.getMlbTeam())) {
                mlbTeams.get(x).getObservablePlayers().add(player);
            }
        }
    }

    /**
     * @return the availablePlayers
     */
    public ArrayList<BaseballPlayer> getAvailablePlayers() {
        return availablePlayers;
    }

    public void setObservablePlayers() {
        getObservablePlayers().addAll(availablePlayers);
    }

    /**
     * @param availablePlayers the availablePlayers to set
     */
    public void setAvailablePlayers(ArrayList<BaseballPlayer> availablePlayers) {
        this.availablePlayers = availablePlayers;
    }

    /**
     * @return the teams
     */
    public ArrayList<BaseballTeam> getTeams() {
        return teams;
    }

    /**
     * @param teams the teams to set
     */
    public void setTeams(ArrayList<BaseballTeam> teams) {
        this.teams = teams;
    }

    /**
     * @return the pickOrder
     */
    public ArrayList<BaseballPlayer> getPickOrder() {
        return pickOrder;
    }

    /**
     * @param pickOrder the pickOrder to set
     */
    public void setPickOrder(ArrayList<BaseballPlayer> pickOrder) {
        this.pickOrder = pickOrder;
    }

    public void sortTeams() {
        //@TODO
    }

    /**
     * @return the draftName
     */
    public String getDraftName() {
        return draftName;
    }

    /**
     * @param draftName the draftName to set
     */
    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }

    public void removeTeam(BaseballTeam teamToRemove) {
        //first we want to get all the players out of the team and into the pool
        int size = teamToRemove.getPlayers().size();
        BaseballPlayer player;
        for (int x = 0; x < size; x++) {
            player = teamToRemove.getPlayers().get(0);
            teamToRemove.removePlayer(player);
            addPlayer(player);
        }
        //we are done now, remove the team
        teams.remove(teamToRemove);
        observableTeams.remove(teamToRemove);
        calculateTotalPoints();
    }

    public void addTeam(BaseballTeam teamToAdd) {
        teams.add(teamToAdd);
        observableTeams.add(teamToAdd);
        calculateTotalPoints();
    }

    public void removePlayer(BaseballPlayer player) {
        availablePlayers.remove(player);
        //MAKE SURE THIS IS REFLECTED IN THE OBSERVABLE PLAYERS LIST
        observablePlayers.remove(player);
    }

    /**
     * @return the observablePlayers
     */
    public ObservableList<BaseballPlayer> getObservablePlayers() {
        return observablePlayers;
    }

    public ObservableList<BaseballTeam> getObservableTeams() {
        return observableTeams;
    }

    public void setObservableTeams(ObservableList<BaseballTeam> observableList) {
        this.observableTeams = observableList;
    }

    /**
     * @param observablePlayers the observablePlayers to set
     */
    public void setObservablePlayers(ObservableList<BaseballPlayer> observablePlayers) {
        this.observablePlayers = observablePlayers;
    }

}
