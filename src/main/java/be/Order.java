package main.java.be;

public class Order {
    private int userID;
    private int projectID;

    public Order (int userID, int projectID){
        this.userID = userID;
        this.projectID = projectID;

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
}
