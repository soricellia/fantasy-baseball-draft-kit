/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbbdk.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Tony
 */
public class Pick {

    private IntegerProperty pickOrder;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty teamName;
    private StringProperty position;
    private StringProperty contract;
    private IntegerProperty salary;
    private IntegerProperty estimatedValue;
    
    public Pick(int pickOrder, String firstName, String lastName, String teamName,String position, String contract, int salary,int estimatedValue) {
        //init the fields
        this.pickOrder = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.teamName = new SimpleStringProperty();
        this.position = new SimpleStringProperty();
        this.contract = new SimpleStringProperty();
        this.salary = new SimpleIntegerProperty();
        this.estimatedValue = new SimpleIntegerProperty();
        //set the fields
        this.pickOrder.set(pickOrder);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.teamName.set(teamName);
        this.position.set(position);
        this.contract.set(contract);
        this.salary.set(salary);
        this.estimatedValue.set(estimatedValue);
    }
    public String getPosition(){
        return position.get();
    }
    public void setPosition(String position){
        this.position.set(position);
    }

    public int getPickOrder() {
        return pickOrder.get();
    }

    public void setPickOrder(int pickOrder) {
        this.pickOrder.set(pickOrder);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public String getContract() {
        return contract.get();
    }

    public void setContract(String contract) {
        this.contract.set(contract);
    }

    public int getSalary() {
        return salary.get();
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }
    public int getEstimatedValue(){
        return estimatedValue.get();
    }
    public void setEstimatedValue(int estimatedValue){
        this.estimatedValue.set(estimatedValue);
    }
    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(o.getClass() != this.getClass())
            return false;
        Pick p = (Pick) o;
        return (this.getFirstName().equals(p.getFirstName()) &&
                this.getLastName().equals(p.getLastName()));
    }
}
