package mk.ukim.finki.library_vp.integration;

import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.library_vp.service.UserService;
import mk.ukim.finki.library_vp.utils.BaseTestData;
import mk.ukim.finki.library_vp.web.RegisterController;
import org.h2.engine.Role;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegisterController.class)
public class RegisterIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

//    @Test
//    @Disabled
//    public void getRegisterPage_shouldRenderRegisterPageWithoutError() throws Exception {
//        mockMvc.perform(get("/register"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("register"))
//                .andExpect(model().attributeDoesNotExist("hasError"));
//    }
//
//    @Test
//    @Disabled
//    public void getRegisterPage_shouldRenderRegisterPageWithError() throws Exception {
//        mockMvc.perform(get("/register").param("error", "Some error message"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("register"))
//                .andExpect(model().attribute("hasError", true))
//                .andExpect(model().attribute("error", "Some error message"));
//    }
//
//    @Test
//    @Disabled
//    public void register_withValidData_shouldRedirectToLoginPage() throws Exception {
//        User user = BaseTestData.getUser();
//        when(userService.register(
//                user.getUsername(), user.getPassword(), user.getPassword(),
//                user.getName(), user.getSurname(), user.getRole()))
//                .thenReturn(user);
//
//        mockMvc.perform(post("/register")
//                        .param("username", user.getUsername())
//                        .param("password", user.getPassword())
//                        .param("repeatedPassword", user.getPassword())
//                        .param("name", user.getName())
//                        .param("surname", user.getSurname())
//                        .param("role", user.getRole().toString()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
//
//    @Test
//    public void register_withInvalidData_shouldRedirectToRegisterPageWithError() throws Exception {
//        doThrow(new InvalidArgumentsException())
//                .when(userService).register(anyString(), anyString(), anyString(), anyString(), anyString(), any());
//
//        mockMvc.perform(post("/register")
//                        .param("username", "username")
//                        .param("password", "password")
//                        .param("repeatedPassword", "password")
//                        .param("name", "name")
//                        .param("surname", "surname")
//                        .param("role", String.valueOf(Role.USER)))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/register?error=Invalid arguments exception"));
//    }
}





