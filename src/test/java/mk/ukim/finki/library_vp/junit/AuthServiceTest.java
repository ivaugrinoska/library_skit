package mk.ukim.finki.library_vp.junit;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.enumerations.Role;
import mk.ukim.finki.library_vp.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.library_vp.model.exceptions.UserNotFoundException;
import mk.ukim.finki.library_vp.repository.UserRepository;
import mk.ukim.finki.library_vp.service.impl.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    private AuthServiceImpl authService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        authService = new AuthServiceImpl(userRepository);
    }

    @Test
    public void login_validCredentials_returnsUser() {
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        when(userRepository.findByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(java.util.Optional.of(user));

        User result = authService.login("username", "password");

        assertEquals(user, result);
    }

    @Test(expected = InvalidUserCredentialsException.class)
    public void login_invalidCredentials_throwsException() {
        when(userRepository.findByUsernameAndPassword(anyString(), anyString()))
                .thenReturn(java.util.Optional.empty());

        authService.login("username", "password");
    }

    @Test
    public void editProfile_validUsername_updatesUser() {
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));

        User updatedUser = authService.editProfile("username", "newname", "newsurname");

        assertEquals("newname", updatedUser.getName());
        assertEquals("newsurname", updatedUser.getSurname());
    }

    @Test(expected = UserNotFoundException.class)
    public void editProfile_invalidUsername_throwsException() {
        when(userRepository.findByUsername(anyString()))
                .thenReturn(java.util.Optional.empty());

        authService.editProfile("username", "new name", "new surname");
    }
}
