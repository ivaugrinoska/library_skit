package mk.ukim.finki.library_vp.integration;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.service.impl.BookServiceImpl;
import mk.ukim.finki.library_vp.service.impl.CategoryServiceImpl;
import mk.ukim.finki.library_vp.utils.BaseTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;

    @MockBean
    private BookServiceImpl bookService;

    @Test
    public void testGetHomePage() throws Exception {
        // Given
        List<Category> categories = Arrays.asList(new Category("Category 1"), new Category("Category 2"));
        List<Book> books = Arrays.asList(BaseTestData.getBook(), new Book("Book2", "Author2", 10, 3.4F, "description", "url2", BaseTestData.getCategory()));

        given(categoryService.findAll()).willReturn(categories);
        given(bookService.findAll()).willReturn(books);

        // When
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("categories", categories))
                .andExpect(model().attribute("books", books))
                .andExpect(model().attribute("bodyContent", "allBooks"))
                .andExpect(view().name("master-template"))
                .andReturn();

        // Then
        verify(categoryService, times(1)).findAll();
        verify(bookService, times(1)).findAll();
    }

    @Test
    public void testHelpPage() throws Exception {
        // Given
        List<Category> categories = Arrays.asList(new Category("Category 1"), new Category("Category 2"));

        given(categoryService.findAll()).willReturn(categories);

        // When
        mockMvc.perform(get("/books/help"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("categories", categories))
                .andExpect(model().attribute("bodyContent", "contact"))
                .andExpect(view().name("master-template"))
                .andReturn();

        // Then
        verify(categoryService, times(1)).findAll();
    }
}

