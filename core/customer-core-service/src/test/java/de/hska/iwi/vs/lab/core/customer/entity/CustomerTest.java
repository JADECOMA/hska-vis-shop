package de.hska.iwi.vs.lab.core.customer.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {

    private String name;
    private String lastName;
    private String userName;
    private String password;
    private int role;

    Customer customer() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setLastname(lastName);
        customer.setUsername(userName);
        customer.setPassword(password);
        customer.setRole(role);
        return customer;
    }

    @Before
    public void setup() {
        name = "Name";
        lastName = "LastName";
        userName = "UserName";
        password = "Password";
        role = 1;
    }

    @Test
    public void getName() {
        assertThat(customer().getName(), is(name));
    }

    @Test
    public void setName() {
        name = "Test";
        Customer customer = customer();
        customer.setName(name);
        assertThat(customer().getName(), is(name));
    }

    @Test
    public void getLastname() {
        assertThat(customer().getLastname(), is(lastName));
    }

    @Test
    public void setLastname() {
        lastName = "Test";
        Customer customer = customer();
        customer.setLastname(lastName);
        assertThat(customer().getLastname(), is(lastName));
    }

    @Test
    public void getPassword() {
        assertThat(customer().getPassword(), is(password));
    }

    @Test
    public void setPassword() {
        password = "Test";
        Customer customer = customer();
        customer.setPassword(password);
        assertThat(customer().getPassword(), is(password));
    }


    @Test
    public void getUsername() {
        assertThat(customer().getUsername(), is(userName));
    }

    @Test
    public void setUsername() {
        userName = "Test";
        Customer customer = customer();
        customer.setPassword(userName);
        assertThat(customer().getUsername(), is(userName));
    }

    @Test
    public void getRole() {
        assertThat(customer().getRole(), is(role));
    }

    @Test
    public void setCategoryId() {
        role = 2;
        Customer customer = customer();
        customer.setRole(role);
        assertThat(customer().getRole(), is(role));
    }

    @Test
    public void stringify() {
        Customer customer = customer();
        String output = "Customer [name=Name," +
                " lastname=LastName," +
                " password=Password," +
                " username=UserName," +
                " role=1]";
        assertThat(customer.toString(), is(output));
    }
}
