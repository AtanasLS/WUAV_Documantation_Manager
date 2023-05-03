package main.java.be;

import java.awt.*;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String address2;
    private String  phone;
    private int consumptionNumber;

    public Customer(String firstName, String lastName, String email, String address, String address2, String phone, int consumptionNumber){

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.address2 = address2;
        this.phone = phone;
        this.consumptionNumber = consumptionNumber;

    }

    public Customer(int id, String firstName, String lastName, String email, String address, String address2, String phone, int consumptionNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.address2 = address2;
        this.phone = phone;
        this.consumptionNumber = consumptionNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getConsumptionNumber() {
        return consumptionNumber;
    }

    public void setConsumptionNumber(int consumptionNumber) {
        this.consumptionNumber = consumptionNumber;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;

    }
}
