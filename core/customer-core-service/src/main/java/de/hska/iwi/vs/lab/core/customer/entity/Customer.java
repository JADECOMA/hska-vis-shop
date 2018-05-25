package de.hska.iwi.vs.lab.core.customer.entity;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private String password;
    private String username;
    private int role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer [name=" + this.name + "," +
                " lastname=" + this.lastname + "," +
                " password=" + this.password + "," +
                " username=" + this.username + "," +
                " role=" + this.role + "]";
    }
}