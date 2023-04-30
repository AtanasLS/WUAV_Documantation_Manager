package main.java.be;

public class LogIns {

    private String username;
    private String password;
    private String project;






    public LogIns(String username, String password) {
        this.username = username;
        this.password = password;
        this.project = "project";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
