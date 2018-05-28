package de.hska.iwi.vs.lab.core.category.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(MockitoJUnitRunner.class)
public class CategoryTest {

    private int id;
    private String name;

    Category category() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    @Before
    public void setup() {
        id = 15;
        name = "Name";
    }

    @Test
    public void getName() {
        assertThat(category().getName(), is(name));
    }

    @Test
    public void setName() {
        name = "Test";
        Category category = category();
        category.setName(name);
        assertThat(category().getName(), is(name));
    }

    @Test
    public void getId() {
        assertThat(category().getId(), is(id));
    }

    @Test
    public void setId() {
        id = 19735;
        Category category = category();
        category.setId(id);
        assertThat(category().getId(), is(id));
    }

    @Test
    public void stringify() {
        Category category = category();
        String output = "{\"id\":\"15\",\"name\":\"Name\"}";
        assertThat(category.toString(), is(output));
    }
}
