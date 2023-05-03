package main.java.be;

import java.util.Date;

public class Order {

    private int id;
    private int userID;
    private int projectID;

    private String name;
    private String userName;

    private String project;
    private String customer;

    private int customerID;



    private Date date;
    private double price;


    public Order(int userID, int projectID, String name, String userName, String project, String customer, int customerID, Date date, double price) {
        this.userID = userID;
        this.projectID = projectID;
        this.name = name;
        this.userName = userName;
        this.project = project;
        this.customer = customer;
        this.customerID = customerID;
        this.date = date;
        this.price = price;
    }

    public Order(int id, int userID, int projectID, String name, String userName,
                 String project, String customer, int customerID, Date date, double price) {
        this.id = id;
        this.userID = userID;
        this.projectID = projectID;
        this.name = name;
        this.userName = userName;
        this.project = project;
        this.customer = customer;
        this.customerID = customerID;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
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
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return name + " " + project;
    }
}
