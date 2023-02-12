package mk.ukim.finki.library_vp.integration;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.repository.CategoryRepository;
import mk.ukim.finki.library_vp.service.CategoryService;
import mk.ukim.finki.library_vp.service.ReservationCartBooksService;
import mk.ukim.finki.library_vp.service.impl.ReservationCartServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReservationCartIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CategoryRepository categoryRepository;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private ReservationCartServiceImpl reservationCartService;

    @MockBean
    private ReservationCartBooksService reservationCartBooksService;

    @Test
    @Disabled
    public void getReservationCartPage_whenCalled_returnsReservationCartPage() throws Exception {
        Category category1 = new Category("C1");
        Category category2 = new Category("C2");
        List<Category> categories = Arrays.asList(category1, category2);
        categoryRepository.save(category1);
        categoryRepository.save(category2);
        when(categoryService.findAll()).thenReturn(categories);
        ReservationCart reservationCart = new ReservationCart();
        reservationCart.setId(1L);
//        List<ReservationCartBooks> reservationCartBooks = Arrays.asList(new ReservationCartBooks(), new ReservationCartBooks());

        List<Book> reservationCartBooks = Arrays.asList(new Book(), new Book());
        when(reservationCartService.listAllBooksInReservationCart(reservationCart.getId())).thenReturn(reservationCartBooks);
        when(reservationCartService.findCartByUser(any(User.class))).thenReturn(reservationCart);
        when(reservationCartService.findById(reservationCart.getId())).thenReturn(reservationCart);
        mockMvc.perform(get("/reservations/current"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("categories", categories))
                .andExpect(model().attribute("books", reservationCartBooks))
                .andExpect(model().attribute("reservationCart", reservationCart))
                .andExpect(model().attribute("bodyContent", "reservationCart"))
                .andExpect(view().name("master-template"));
    }

}
