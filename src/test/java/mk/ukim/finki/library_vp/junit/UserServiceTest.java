package mk.ukim.finki.library_vp.junit;

import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.enumerations.Role;
import mk.ukim.finki.library_vp.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.library_vp.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.library_vp.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.library_vp.repository.UserRepository;
import mk.ukim.finki.library_vp.service.impl.UserServiceImpl;
import mk.ukim.finki.library_vp.utils.BaseTestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    @Spy
    UserServiceImpl userService;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        userService = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister() {
        User user = BaseTestData.getUser();
        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("name do not match", "name", user.getName());
        Assert.assertEquals("role do not match", Role.ROLE_USER, user.getRole());
        Assert.assertEquals("surname do not match", "surname", user.getSurname());
        Assert.assertEquals("password do not match", "password", user.getPassword());
        Assert.assertEquals("username do not match", "username", user.getUsername());
    }

    @Test
    public void shouldReturnUsernameAlreadyExistsException() {
        User user = BaseTestData.getUser();
        given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));
        assertThrows(UsernameAlreadyExistsException.class, () -> userService.register(user.getUsername(), user.getPassword(), user.getPassword(), user.getName(), user.getSurname(), user.getRole()));
    }

    @Test
    public void testFindUserByUsername() {
        User expected = new User("username", "password", "name", "surname", Role.ROLE_USER);
        userRepository.save(expected);
        User actual = userService.findUserByUsername("username1");
        assertNotEquals(expected, actual);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testRegisterWithNullUsername() {
        userService.register(null, "password", "password", "John", "Doe", Role.ROLE_USER);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testRegisterWithEmptyUsername() {
        userService.register("", "password", "password", "John", "Doe", Role.ROLE_USER);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testRegisterWithNullPassword() {
        userService.register("username", null, "password", "John", "Doe", Role.ROLE_USER);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void testRegisterWithEmptyPassword() {
        userService.register("username", "", "password", "John", "Doe", Role.ROLE_USER);
    }

    @Test(expected = PasswordsDoNotMatchException.class)
    public void testRegisterWithDifferentPasswords() {
        userService.register("username", "password", "not_password", "Iva", "Ugrinoska", Role.ROLE_USER);
    }

    @Test
    public void testSuccessfulRegister() {
        // mock the password encoder to return the plain password when encode is called
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        when(passwordEncoder.encode("password")).thenReturn("password");

        // mock the user repository to return the saved user when save is called
        UserRepository userRepository = mock(UserRepository.class);
        User expected = new User("username", "password", "Iva", "Ugrinoska", Role.ROLE_USER);
        when(userRepository.save(any(User.class))).thenReturn(expected);

        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, passwordEncoder);

        User actual = userServiceImpl.register("username", "password", "password", "Iva", "Ugrinoska", Role.ROLE_USER);
        assertEquals(expected, actual);
    }

}
