package de.hska.iwi.vs.lab.composite.user.entity;

public class Customer implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public Role getRole() {
        return role;
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
                "\"role\": {" +
                    "\"id\":\"" + this.role.getId() + "\"" +
                    "\"typ\":\"" + this.role.getTyp() + "\"" +
                    "\"level\":\"" + this.role.getLevel() + "\"" +
                    "}" +
                "}";
    }
}