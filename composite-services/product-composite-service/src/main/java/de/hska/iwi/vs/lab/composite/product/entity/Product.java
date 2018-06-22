package de.hska.iwi.vs.lab.composite.product.entity;

public class Product {

    private int id;
    private String details;
    private double price;
    private String name;
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + this.id + "\"," +
                "\"name\":\"" + this.name + "\"," +
                "\"details\":\"" + this.details + "\"," +
                "\"price\":\"" + this.price + "\"," +
                "\"category\": {" +
                "\"id\":" + this.category.getId() + "\"," +
                "\"name\":" + this.category.getName() + "\"" +
                "}" +
                "}";
    }
}