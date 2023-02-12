package mk.ukim.finki.library_vp.web;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.library_vp.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request,
                        @RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        User user;
        try{
            user = this.authService.login(username, password);
            request.getSession().setAttribute("user", user);
            return "redirect:/books";
        }
        catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
