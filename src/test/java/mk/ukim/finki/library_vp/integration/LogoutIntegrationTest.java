package mk.ukim.finki.library_vp.integration;

import mk.ukim.finki.library_vp.web.LogoutController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LogoutController.class)
public class LogoutIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @Disabled
//    public void logout_shouldInvalidateSessionAndRedirectToLoginPage() throws Exception {
//        mockMvc.perform(get("/logout"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/login"));
//    }
}
