/*s
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import fbbdk.Comparators.BaComparator;
import fbbdk.Comparators.EraComparator;
import fbbdk.Comparators.HRComparator;
import fbbdk.Comparators.KComparator;
import fbbdk.Comparators.LastNameComparator;
import fbbdk.Comparators.RbiComparator;
import fbbdk.Comparators.RunsComparator;
import fbbdk.Comparators.SbComparator;
import fbbdk.Comparators.SvComparator;
import fbbdk.Comparators.WComparator;
import fbbdk.Comparators.WhipComparator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tony
 */
public class Draft {

    private ArrayList<BaseballPlayer> availablePlayers;
    private ArrayList<BaseballTeam> teams;
    private String draftName;

    private ObservableList<Pick> pickOrder;
    private ObservableList<BaseballPlayer> observablePlayers;
    private ObservableList<BaseballTeam> observableTeams;
    private ArrayList<BaseballTeam> mlbTeams;

    private static final String[] MLB_TEAMS = {"ATL", "AZ", "CHC", "CIN", "COL",
        "LAD", "MIA", "MIL", "NYM", "PHI", "PIT", "SD", "SF", "STL", "WSH"};
    //we need to make lists to decide who is in what place
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> rStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> hrStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> rbiStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> sbStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> baStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> wStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> svStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> kStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> eraStandingsList;
    TreeSet<ObjectValuePair<BaseballTeam, Integer>> whipStandingsList;

    public Draft() {
        availablePlayers = new ArrayList<>();
        teams = new ArrayList<>();
        pickOrder = FXCollections.observableArrayList();;
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
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getRuns());
            if (!rStandingsList.contains(ovp)) {
                rStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getHr());
            if (!hrStandingsList.contains(ovp)) {
                hrStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getRbi());
            if (!rbiStandingsList.contains(ovp)) {
                rbiStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getObvSb());
            if (!sbStandingsList.contains(ovp)) {
                sbStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getBa());
            if (!baStandingsList.contains(ovp)) {
                baStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getWins());
            if (!wStandingsList.contains(ovp)) {
                wStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getSv());
            if (!svStandingsList.contains(ovp)) {
                svStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getK());
            if (!kStandingsList.contains(ovp)) {
                kStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getEra());
            if (!eraStandingsList.contains(ovp)) {
                eraStandingsList.add(ovp);
            }
            ovp = new ObjectValuePair(teams.get(x), teams.get(x).getWhip());
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
        Iterator<ObjectValuePair<BaseballTeam, Integer>> i;
        BaseballTeam team;

        //now we start the calculations 
        i = rStandingsList.iterator();
        for (int y = 1; y < rStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
        }
        i = hrStandingsList.iterator();
        for (int y = 1; y < hrStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);

        }
        i = rbiStandingsList.iterator();
        for (int y = 1; y < rbiStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
        }
        i = sbStandingsList.iterator();
        for (int y = 1; y < sbStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
        }
        i = baStandingsList.iterator();
        for (int y = 1; y < baStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
        }
        i = wStandingsList.iterator();
        for (int y = 1; y < wStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
        }
        i = svStandingsList.iterator();
        for (int y = 1; y < svStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
        }
        i = kStandingsList.iterator();
        for (int y = 1; y < kStandingsList.size() + 1; y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + y);
        }
        i = eraStandingsList.iterator();
        for (int y = 0; y < eraStandingsList.size(); y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + eraStandingsList.size() - y);
        }
        i = whipStandingsList.iterator();
        for (int y = 0; y < whipStandingsList.size(); y++) {
            team = i.next().getObject();
            team.setTotalPoints(team.getTotalPoints()
                    + whipStandingsList.size() - y);
        }
    }

    /**
     * . In order to do this first calculate the total money remaining in the
     * draft (remaining money for all fantasy teams with spots to fill). Then,
     * calculate the rank among players in all 5 of their categories, and
     * determine the average rank of each player in their 5 categories. Once
     * we've done this we'll know the top X hitters and top Y pitchers where X
     * is the number of hitters needing to be drafted (total of all teams) and Y
     * is the number of pitchers needing to be drafted (total of all teams).
     * Finally, we can calculate the median salary as (total $ remaining)/(2 *
     * X) for hitters and a similar calculation for pitchers. And so we can then
     * calculate the estimated value for each player as the median hitter (or
     * pithcher) salary * (X * 2/player rank).
     */
    public void calculateEstimatedValue() {
        //first we will calculate remaning money
        int money = 0;
        for (BaseballTeam team : teams) {
            money += team.getObvMoneyLeft();
        }
        //ok now we calculate rank of a hitter
        int x = 0;
        for (int i = 0; i < teams.size(); i++) {
            x += teams.get(i).getHittersNeeded();
        }
        int medianSalary;
        if (x != 0) {
            medianSalary = money / (2 * x);
        } else {
            medianSalary = 0;
        }

        //ok now we calculate median salary of a pitcher
        int y = 0;
        for (int i = 0; i < teams.size(); i++) {
            y += teams.get(i).getPitchersNeeded();
        }
        int medianPitcherSalary;
        if (y != 0) {
            medianPitcherSalary = money / (2 * y);
        } else {
            medianPitcherSalary = 0;
        }

        //now we put each guy into a standings
        ArrayList<BaseballPlayer> rstandingsList = new ArrayList<>();
        ArrayList<BaseballPlayer> rbistandingsList = new ArrayList<>();
        ArrayList<BaseballPlayer> sbstandingsList = new ArrayList<>();
        ArrayList<BaseballPlayer> bastandingsList = new ArrayList<>();
        ArrayList<BaseballPlayer> hrstandingsList = new ArrayList<>();

        ArrayList<BaseballPlayer> wstandingsList = new ArrayList<>();
        ArrayList<BaseballPlayer> svstandingsList = new ArrayList<>();
        ArrayList<BaseballPlayer> kstandingsList = new ArrayList<>();
        ArrayList<BaseballPlayer> erastandingsList = new ArrayList<>();
        ArrayList<BaseballPlayer> whipstandingsList = new ArrayList<>();
        for (int i = 0; i < availablePlayers.size(); i++) {
            if (!availablePlayers.get(i).isPitcher) {
                rstandingsList.add(availablePlayers.get(i));
                hrstandingsList.add(availablePlayers.get(i));
                rbistandingsList.add(availablePlayers.get(i));
                sbstandingsList.add(availablePlayers.get(i));
                bastandingsList.add(availablePlayers.get(i));
            } else {
                wstandingsList.add(availablePlayers.get(i));
                svstandingsList.add(availablePlayers.get(i));
                kstandingsList.add(availablePlayers.get(i));
                erastandingsList.add(availablePlayers.get(i));
                whipstandingsList.add(availablePlayers.get(i));
            }
        }
        //now we sort the list by each thing
        rstandingsList.sort(new RunsComparator());
        hrstandingsList.sort(new HRComparator());
        rbistandingsList.sort(new RbiComparator());
        sbstandingsList.sort(new SbComparator());
        bastandingsList.sort(new BaComparator());

        wstandingsList.sort(new WComparator());
        svstandingsList.sort(new SvComparator());
        kstandingsList.sort(new KComparator());
        erastandingsList.sort(new EraComparator());
        whipstandingsList.sort(new WhipComparator());

        //now we loop through and calculate the players average standing
        //and thus giving an estimated value
        BaseballPlayer bbPlayer;
        for (int i = 0; i < availablePlayers.size(); i++) {
            bbPlayer = availablePlayers.get(i);
            if (!bbPlayer.isPitcher) {
                int bbPlayerStanding = 0;
                bbPlayerStanding = (rstandingsList.indexOf(bbPlayer)
                        + hrstandingsList.indexOf(bbPlayer) + rbistandingsList.indexOf(bbPlayer)
                        + sbstandingsList.indexOf(bbPlayer) + bastandingsList.indexOf(bbPlayer) + 5)
                        / 5;
                if (medianSalary * ((x * 2) / bbPlayerStanding) == 0) {
                    bbPlayer.setEstimatedValue(1);
                } else {
                    bbPlayer.setEstimatedValue(medianSalary * ((x * 2) / bbPlayerStanding));
                }
            } else {
                int bbPlayerStanding = 0;
                bbPlayerStanding = (wstandingsList.indexOf(bbPlayer)
                        + svstandingsList.indexOf(bbPlayer) + kstandingsList.indexOf(bbPlayer)
                        + erastandingsList.indexOf(bbPlayer) + whipstandingsList.indexOf(bbPlayer) + 5)
                        / 5;

                if (medianPitcherSalary * ((y * 2) / bbPlayerStanding) == 0) {
                    bbPlayer.setEstimatedValue(1);
                } else {
                    bbPlayer.setEstimatedValue(medianPitcherSalary * ((y * 2) / bbPlayerStanding));
                }
            }
        }
        observablePlayers.clear();
        observablePlayers.addAll(availablePlayers);

    }

    public BaseballPlayer getHighestHitterEstValue(BaseballTeam team) {
        int highest = 0;
        int index = 0;
        int value = team.maxSalaryAllowance();

        BaseballPlayer player;
        for (int x = 0; x < availablePlayers.size(); x++) {
            player = availablePlayers.get(x);
            if (!player.isPitcher) {
                if (player.getEstimatedValue() > highest) {
                    if (player.getEstimatedValue() <= value) {
                        if (team.canUsePlayer(player)) {
                            index = x;
                            highest = player.getEstimatedValue();
                        }
                    }
                }
            }
        }
        return availablePlayers.get(index);
    }

    public boolean playerDraftEnded() {
        for (int x = 0; x < teams.size(); x++) {
            if (teams.get(x).needsMorePlayers()) {
                return false;
            }
        }
        return true;
    }

    public BaseballPlayer getHighestPitcherEstValue(BaseballTeam team) {
        int highest = 0;
        int index = 0;
        int value = team.maxSalaryAllowance();
        BaseballPlayer player;
        for (int x = 0; x < availablePlayers.size(); x++) {
            player = availablePlayers.get(x);
            if (player.isPitcher) {
                if (player.getEstimatedValue() > highest) {
                    if (player.getEstimatedValue() <= value) {
                        if (team.canUsePlayer(player)) {
                            index = x;
                            highest = player.getEstimatedValue();
                        }
                    }
                }
            }
        }
        return availablePlayers.get(index);
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
                mlbTeams.get(x).getObservablePlayers().sort(new LastNameComparator());
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
    public ObservableList getPickOrder() {
        return pickOrder;
    }

    public void addPick(Pick pick) {
        if(pickOrder.contains(pick))
            removePick(pick);
        
        if (pick.getContract().equals("S2")) {
            this.pickOrder.add(pick);
        }
        else if(playerDraftEnded()){
            this.pickOrder.add(pick);
        }
    }

    public void removePick(Pick pick) {
        this.pickOrder.remove(pick);
        //ok so we need to make sure the pick orders arnt all skrewd up
        for (int x = 0; x < pickOrder.size(); x++) {
            Pick p = pickOrder.get(x);
            p.setPickOrder(x + 1);
        }
    }

    /**
     * @param pickOrder the pickOrder to set
     */
    public void setPickOrder(ArrayList<Pick> pickOrder) {
        this.pickOrder.setAll(pickOrder);
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
