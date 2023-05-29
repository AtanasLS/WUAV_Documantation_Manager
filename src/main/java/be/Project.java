package main.java.be;

public class Project {
    private int projectId;

    private String type;

    private String customer;
    private int customerId;

    private int salesCount;



    public Project(int projectId, String type, String customer, int customerId){
        this.projectId=projectId;
        this.type = type;
        this.customer=customer;
        this.customerId=customerId;
    }

    public Project(String type, int salesCount) {
        this.type = type;
        this.salesCount = salesCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getCustomer() {
        return customer;
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Type: " + type + " To customer: " + customer;
    }
}
