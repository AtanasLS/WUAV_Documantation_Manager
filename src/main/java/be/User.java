package main.java.be;

import javafx.beans.property.SimpleStringProperty;

public class User {

    private int id;
    private SimpleStringProperty username;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty email;
    private SimpleStringProperty password;
    private SimpleStringProperty type;

    private SimpleStringProperty img;

    public User(String username, String firstName, String lastName, String email, String password, String type, String img){

        this.username = new SimpleStringProperty(username);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.type = new SimpleStringProperty(type);
        this.img=new SimpleStringProperty(img);

    }

    public User(int id, String username, String firstName, String lastName, String email, String password, String type, String img) {
        this.id = id;
        this.username = new SimpleStringProperty(username);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.type = new SimpleStringProperty(type);
        this.img=new SimpleStringProperty(img);
    }

    public String getImg() {
        return img.get();
    }

    public void setImg(String img) {
        this.img = new SimpleStringProperty(img);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName = new SimpleStringProperty(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    @Override
    public String toString() {
        return username + " Type: " + type;
    }
}