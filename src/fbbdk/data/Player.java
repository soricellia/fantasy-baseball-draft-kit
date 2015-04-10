/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

/**
 * This class is intended to provide the abstraction needed to represent 
 * a player on any team.
 * 
 * @author Tony
 */
public abstract class Player {
    private String name;
    private Contract contract;
    private int salary;
    private MLBTeam mlbTeam;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contract
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * @param contract the contract to set
     */
    public void setContract(Contract contract) {
        this.contract = contract;
    }

    /**
     * @return the salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * @return the mlbTeam
     */
    public MLBTeam getMlbTeam() {
        return mlbTeam;
    }

    /**
     * @param mlbTeam the mlbTeam to set
     */
    public void setMlbTeam(MLBTeam mlbTeam) {
        this.mlbTeam = mlbTeam;
    }
}
