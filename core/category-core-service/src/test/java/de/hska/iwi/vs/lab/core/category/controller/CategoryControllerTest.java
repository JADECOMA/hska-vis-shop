package de.hska.iwi.vs.lab.core.category.controller;

import de.hska.iwi.vs.lab.core.category.entity.Category;
import de.hska.iwi.vs.lab.core.category.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

import java.nio.charset.Charset;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Value("classpath:category.json")
    private Resource json;

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
    public void addCategory() throws Exception {
        when(categoryService.addCategory(any(Category.class))).thenReturn(HttpStatus.CREATED);
        String content = StreamUtils.copyToString(json.getInputStream(), Charset.forName("UTF-8"));
        mockMvc.perform(put("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void addCategoryWithoutBody() throws Exception {
        mockMvc.perform(put("/")).andExpect(status().isBadRequest());
        verify(categoryService, never()).addCategory(any(Category.class));
        assertTrue(true);
    }

    @Test
    public void deleteCategory() throws Exception {
        given(categoryService.deleteCategory(1)).willReturn(HttpStatus.ACCEPTED);

        mockMvc.perform(delete("/{categoryId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/test/work")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .equals("work");
    }
}
