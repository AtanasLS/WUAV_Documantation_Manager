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
}
