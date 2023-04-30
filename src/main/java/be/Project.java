package main.java.be;

public class Project {

    private String type;
    private double price;

    private String customer;

    public Project(String type, double price){
        this.type = type;
        this.price = price;
        this.customer="customer";
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

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
