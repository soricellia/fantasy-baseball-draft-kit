/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.util.Date;

/**
 * This class is intended to provide the abstraction needed to represent 
 * a player on any team.
 * 
 * @author Tony
 */
public abstract class Player {
    private String firstName;
    private String lastName;
    private String notes;
    private Contract contract;
    private int salary;
    private String mlbTeam;
    private int birthDate;
    private String countryOfBirth;

    public Player(){
        
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
    public String getMlbTeam() {
        return mlbTeam;
    }

    /**
     * @param mlbTeam the mlbTeam to set
     */
    public void setMlbTeam(String mlbTeam) {
        this.mlbTeam = mlbTeam;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the year
     */
    public int getBirthDate() {
        return birthDate;
    }

    /**
     * @param year the year to set
     */
    public void setBirthDate(int year) {
        this.birthDate = year;
    }

    /**
     * @return the countryOfBirth
     */
    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    /**
     * @param countryOfBirth the countryOfBirth to set
     */
    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }
}
