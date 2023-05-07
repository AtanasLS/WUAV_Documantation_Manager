package main.java.be;

import javafx.scene.image.Image;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

public class Document {


    private int id;
    private String name;
    private int project;
    private int loginId;


    private int customer;
    private int user;

    private String layoutDrawing;
    private String description;
    private LocalDate date;

    public Document(String layoutDrawing, String description, int loginId , String name,int user, int customer, int project, LocalDate date) {
        this.name = name;
        this.project = project;
        this.loginId = loginId;
        this.customer = customer;
        this.user = user;
        this.layoutDrawing = layoutDrawing;
        this.description = description;
        this.date = date;
        //layoutDrawing,description, loginID, name, userID, customerID, projectID, date
    }


    public Document(Image layoutDrawing, String description, Date date){
        this.name="name";

    public Document(int id,String layoutDrawing, String description, int loginId , String name,int user, int customer, int project, LocalDate date) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.customer = customer;
        this.user = user;
        this.layoutDrawing = layoutDrawing;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Image getLayoutDrawing() {}

    private int type;

    public Document(String layoutDrawing, String description, int loginId , String name,int user, int customer, int project, LocalDate date) {
        this.name = name;
        this.project = project;
        this.loginId = loginId;
        this.customer = customer;
        this.user = user;
        this.layoutDrawing = layoutDrawing;
        this.description = description;
        this.date = date;
        this.type=0;
        //layoutDrawing,description, loginID, name, userID, customerID, projectID, date
    }

    public Document(int id,String layoutDrawing, String description, int loginId , String name,int user, int customer, int project, LocalDate date, int type) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.customer = customer;
        this.user = user;
        this.layoutDrawing = layoutDrawing;
        this.description = description;
        this.date = date;
        this.type=type;

    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
    }

    public String getLayoutDrawing() {

        return layoutDrawing;
    }

    public void setLayoutDrawing(String layoutDrawing) {
        this.layoutDrawing = layoutDrawing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public int getCreator() {
        return user;
    }

    public void setCreator(int creator) {
        this.user = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return name;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
