//package de.hska.iwi.vs.lab.Entity;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class LoginDataTest {
//
//    private String userName;
//    private String password;
//
//    LoginData loginData() {
//        LoginData loginData = new LoginData();
//        loginData.setUsername(userName);
//        loginData.setPassword(password);
//        return loginData;
//    }
//
//    @Before
//    public void setup() {
//        userName = "Name";
//        password = "Password";
//    }
//
//    @Test
//    public void getPassword() {
//        assertThat(loginData().getPassword(), is(password));
//    }
//
//    @Test
//    public void setPassword() {
//        password = "Test";
//        LoginData loginData = loginData();
//        loginData.setPassword(password);
//        assertThat(loginData().getPassword(), is(password));
//    }
//
//    @Test
//    public void getUsername() {
//        assertThat(loginData().getUsername(), is(userName));
//    }
//
//    @Test
//    public void setUsername() {
//        userName = "Test";
//        LoginData loginData = loginData();
//        loginData.setPassword(userName);
//        assertThat(loginData().getUsername(), is(userName));
//    }
//}
