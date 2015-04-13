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
public abstract class Team {
    private String coach;
    private String teamName;
    private int budgetMoney;
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
}
