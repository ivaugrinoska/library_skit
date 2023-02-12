package mk.ukim.finki.library_vp.integration;

import mk.ukim.finki.library_vp.service.UserReadBooksService;
import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import mk.ukim.finki.library_vp.web.UserController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private UserReadBooksService userReadBooksService;

//    @Test
//    public void userProfile_whenUserIsAuthenticated_returnsProfilePage() throws Exception {
//        // Given
//        UserDetails userDetails = User.builder()
//                .username("test_user")
//                .password("test_password")
//                .authorities(Collections.emptyList())
//                .build();
//        when(userService.loadUserByUsername("test_user")).thenReturn(userDetails);
//        when(userReadBooksService.listAllBooksInProfile("test_user")).thenReturn(Collections.emptyList());
//
//        // When
//        Authentication authentication = new TestingAuthenticationToken(userDetails, null);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        mockMvc.perform(get("/users/profile"))
//
//                // Then
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("loggedUser", userDetails))
//                .andExpect(model().attribute("books", Collections.emptyList()))
//                .andExpect(view().name("profile"));
//    }
//
//    @Test
//    public void addBookToProfile_whenRequestIsValid_redirectsToProfilePage() throws Exception {
//        // Given
//        UserDetails userDetails = User.builder()
//                .username("test_user")
//                .password("test_password")
//                .authorities(Collections.emptyList())
//                .build();
//        when(userService.loadUserByUsername("test_user")).thenReturn(userDetails);
//        Authentication authentication = new TestingAuthenticationToken(userDetails, null);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // When
//        mockMvc.perform(post("/users/add-book/1"))
//
//                // Then
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/users/profile"));
//    }
}
