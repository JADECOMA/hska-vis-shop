package de.hska.iwi.vs.lab.core.category.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(MockitoJUnitRunner.class)
public class CategoryTest {

    private String name;

    Category category() {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    @Before
    public void setup() {
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
    public void stringify() {
        Category category = category();
        String output = "Category [name=Name]";
        assertThat(category.toString(), is(output));
    }
}
