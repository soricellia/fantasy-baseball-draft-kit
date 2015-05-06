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
public class Team implements Comparable{
    private String coach;
    String teamName;
    private int budgetMoney;
    
    public Team(){
        coach = "";
        teamName = "";
        budgetMoney = 0;
    }
    /**
     * @return the coach
     */
    public String getCoach() {
        return coach;
    }

    /**
     * @param coach the coach to set
     */
    public void setCoach(String coach) {
        this.coach = coach;
    }

    /**
     * @return the budgetMoney
     */
    public int getBudgetMoney() {
        return budgetMoney;
    }

    /**
     * @param budgetMoney the budgetMoney to set
     */
    public void setBudgetMoney(int budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    /**
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String toString(){
        return teamName;
    }
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        Team team = (Team)o;
        if(team.getTeamName().equals(this.getTeamName())
                && team.getCoach().equals(this.getCoach())){
            return true;
        }
        return false;
        
    }
    @Override
    public int compareTo(Object o) {
        if(o.getClass()!=this.getClass()){
            //dont want you
            return -1;
        }
        Team team = (Team) o;
        return teamName.compareTo(team.teamName);
        
    }
    
}
