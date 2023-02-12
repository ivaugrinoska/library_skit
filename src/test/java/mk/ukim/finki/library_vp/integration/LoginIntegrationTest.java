package mk.ukim.finki.library_vp.integration;

import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.library_vp.repository.UserRepository;
import mk.ukim.finki.library_vp.service.AuthService;
import mk.ukim.finki.library_vp.utils.BaseTestData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Test
    void getLoginPage_shouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @Disabled
    void login_withValidCredentials_shouldRedirectToBooksPage() throws Exception {
        User user = BaseTestData.getUser();
        userRepository.save(user);
        when(authService.login(user.getUsername(), user.getPassword()))
                .thenReturn(user);

        mockMvc.perform(post("/login")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"));
    }

    @Test
    @Disabled
    void login_withInvalidCredentials_shouldShowErrorMessage() throws Exception {
        User user = BaseTestData.getUser();
        userRepository.save(user);
        when(authService.login("invalidUsername", "invalidPassword"))
                .thenThrow(new InvalidUserCredentialsException());

        mockMvc.perform(post("/login")
                        .param("username", "invalidUsername")
                        .param("password", "invalidPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("hasError", true))
                .andExpect(model().attribute("error", "Invalid credentials"));
    }
}
