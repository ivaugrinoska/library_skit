package mk.ukim.finki.library_vp.integration;

import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.service.impl.CategoryServiceImpl;
import mk.ukim.finki.library_vp.web.CategoryController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;

//    @Test
//    @Disabled
//    public void allCategories_shouldReturnCategoriesAndRenderContactPage() throws Exception {
//        List<Category> categories = Arrays.asList(new Category(), new Category());
//        when(categoryService.findAll()).thenReturn(categories);
//
//        mockMvc.perform(get("/categories"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("contact"))
//                .andExpect(model().attribute("categories", categories));
//    }
}

