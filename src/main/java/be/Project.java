package main.java.be;

public class Project {

    private String type;
    private double price;

    private String customer;
    private int customerId;



    public Project(String type, double price, String customer, int customerId){
        this.type = type;
        this.price = price;
        this.customer=customer;
        this.customerId=customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
