package main.java.be;

public class LogIns {

    private int id;
    private String username;
    private String password;
    private String project;

    private int projectId;



    public LogIns(String username, String password, String project , int projectId) {
        this.username = username;
        this.password = password;
        this.project = project;
        this.projectId=projectId;
    }

    public LogIns(int id, String username, String password, String project, int projectId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.project = project;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
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
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return   username +
                 " " + password ;
    }
}
