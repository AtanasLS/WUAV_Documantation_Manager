package main.java.be;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class Document {


    private int id;
    private String name;
    private int project;
    private int loginId;


    private int customer;
    private int user;

    private byte[] drawing;
    private String description;
    private LocalDate date;

    public void setImage(String imgPAth) throws IOException {
        BufferedImage bImage = ImageIO.read(new File(imgPAth));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        this.drawing = bos.toByteArray();
    }


    public Document(String layoutDrawing, String description, int loginId , String name,int user, int customer, int project, LocalDate date) throws IOException {
        this.name = name;
        this.project = project;
        this.loginId = loginId;
        this.customer = customer;
        this.user = user;
        this.description = description;
        this.date = date;
        this.setImage(layoutDrawing);
        //layoutDrawing,description, loginID, name, userID, customerID, projectID, date
    }

    public Document(int id,String layoutDrawing, String description, int loginId , String name,int user, int customer, int project, LocalDate date) throws IOException {
        this.id = id;
        this.name = name;
        this.project = project;
        this.customer = customer;
        this.user = user;
        this.description = description;
        this.date = date;
        this.setImage(layoutDrawing);

    }

    public int getId() {
        return id;
    }

    public int getUser() {
        return user;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public byte[] getDrawing() {
        return drawing;
    }

    public void setDrawing(byte[] drawing) {
        this.drawing = drawing;
    }

    @Override
    public String toString() {
        return name;
    }
}