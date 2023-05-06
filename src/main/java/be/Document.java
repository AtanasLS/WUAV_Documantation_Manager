package main.java.be;

import java.awt.*;
import java.util.Date;

public class Document {

    private String name;
    private String project;



    private String customer;
    private String creator;

    private Image layoutDrawing;
    private String description;
    private Date date;

<<<<<<< Updated upstream
    public Document(Image layoutDrawing, String description, Date date){
        this.name="name";
        this.layoutDrawing = layoutDrawing;
        this.description = description;
        this.date = date;
        this.creator="srs";
        this.project="project";
        this.customer="customer";

    }

    public Image getLayoutDrawing() {
=======
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
>>>>>>> Stashed changes
        return layoutDrawing;
    }

    public void setLayoutDrawing(Image layoutDrawing) {
        this.layoutDrawing = layoutDrawing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
<<<<<<< Updated upstream
=======

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
>>>>>>> Stashed changes
}
