package de.hska.iwi.vs.lab.composite.product.entity;

public class Product {
    private int id;
    private String details;
    private String name;
    private double price;
    private int categoryId;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product [name=" + this.name + "," +
                " details=" + this.details + "," +
                " price=" + this.price + "," +
                " category_id=" + this.categoryId + "]";
    }
}