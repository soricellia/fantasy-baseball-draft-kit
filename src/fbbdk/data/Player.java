/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class is intended to provide the abstraction needed to represent 
 * a player on any team.
 * 
 * @author Tony
 */
public abstract class Player {
    
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty notes;
    private Contract contract;
    private IntegerProperty salary;
    private StringProperty mlbTeam;
    private IntegerProperty birthDate;
    private StringProperty countryOfBirth;
    private StringProperty positions;
    private StringProperty position;
    private IntegerProperty estimatedValue;
    
    private transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    public Player(){
         firstName = new SimpleStringProperty();
         lastName = new SimpleStringProperty();
         notes = new SimpleStringProperty();
         salary = new SimpleIntegerProperty();
         estimatedValue = new SimpleIntegerProperty();
         mlbTeam = new SimpleStringProperty();
         birthDate = new SimpleIntegerProperty();
         countryOfBirth = new SimpleStringProperty();
         positions = new SimpleStringProperty();
         position = new SimpleStringProperty();
    }
    public int getEstimatedValue(){
        return -1;
    }
    public void setEstimatedValue(int value){
        this.estimatedValue.set(value);
    }
    public String getPositions(){
        return positions.get();
    }
    
    public String getPosition(){
        return position.get();
    }
    
    public void setPosition(String position){
        this.position.set(position);
    }
    
    public void setPositions(String positions){
        this.positions.set(positions);
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes.get();
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes.set(notes);
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
        return salary.get();
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(int salary) {
        this.salary.set(salary);
    }

    /**
     * @return the mlbTeam
     */
    public String getMlbTeam() {
        return mlbTeam.get();
    }

    /**
     * @param mlbTeam the mlbTeam to set
     */
    public void setMlbTeam(String mlbTeam) {
        this.mlbTeam.set(mlbTeam);
    }

    /**
     * @return the birthDate
     */
    public int getBirthDate() {
        return birthDate.get();
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(int birthDate) {
        this.birthDate.set(birthDate);
    }

    /**
     * @return the countryOfBirth
     */
    public String getCountryOfBirth() {
        return countryOfBirth.get();
    }

    /**
     * @param countryOfBirth the countryOfBirth to set
     */
    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth.set(countryOfBirth);
    }

    /**
     * @return the propertyChangeSupport
     */
    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.firstName);
        hash = 53 * hash + Objects.hashCode(this.lastName);
        return hash;
    }

    
    public String toString(){
        return firstName+" "+lastName;
    }

    /**
     * @param propertyChangeSupport the propertyChangeSupport to set
     */
    public void setPropertyChangeSupport(PropertyChangeSupport propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

}
