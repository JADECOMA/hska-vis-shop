package de.hska.iwi.vs.lab.core.customer.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;


    @Column(name = "username", unique = true, nullable = false)
    private String username;


    @Column(name = "name", nullable = false)
    private String firstname;


    @Column(name = "lastname", nullable = false)
    private String lastname;


    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne()
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    public Customer() {
    }

    public Customer(String username, String firstname, String lastname,
                String password, Role role) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + this.firstname + "\"," +
                "\"lastname\":\"" + this.lastname + "\"," +
                "\"password\":\"" + this.password + "\"," +
                "\"username\":\"" + this.username + "\"," +
                "\"role\":\"" + this.role.getId() + "\"" +
                "}";
    }
}