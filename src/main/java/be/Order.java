package main.java.be;

import java.util.Date;

public class Order {
    private int userID;
    private int projectID;

    private String name;
    private String project;
    private String customer;
    private Date date;
    private double price;



    public Order (int userID, int projectID){
        this.userID = userID;
        this.projectID = projectID;
        this.project="project";
        this.price=0.00;
        this.name="name";
        this.customer="customer";
        this.date=new Date();

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
