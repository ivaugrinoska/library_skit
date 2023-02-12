package mk.ukim.finki.library_vp.junit;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.model.exceptions.BookAlreadyInProfileException;
import mk.ukim.finki.library_vp.model.exceptions.BookNotFoundException;
import mk.ukim.finki.library_vp.model.exceptions.UserNotFoundException;
import mk.ukim.finki.library_vp.repository.BookRepository;
import mk.ukim.finki.library_vp.repository.UserRepository;
import mk.ukim.finki.library_vp.service.impl.UserReadBooksServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserReadBooksServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    @Spy
    private UserReadBooksServiceImpl userReadBooksServiceImpl;

    @Before
    public void setUp() {
        userReadBooksServiceImpl = new UserReadBooksServiceImpl(userRepository, bookRepository);
    }

    @Test
    public void addBookToProfile_ShouldReturnUserWithBookAddedToProfile() {
        User user = new User();
        user.setUsername("testuser");
        user.setReadBooks(new ArrayList<>());

        Book book = new Book();
        book.setId(1L);

        when(userRepository.findUserByUsername("testuser")).thenReturn(user);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(userRepository.save(user)).thenReturn(user);

        User result = userReadBooksServiceImpl.addBookToProfile("testuser", 1L);

        assertThat(result).isNotNull();
        assertThat(result.getReadBooks().size()).isEqualTo(1);
        assertThat(result.getReadBooks().get(0).getId()).isEqualTo(1L);
    }


    @Test(expected = BookNotFoundException.class)
    public void addBookToProfile_ShouldThrowBookNotFoundException_WhenBookNotFound() {
        User user = new User();
        user.setUsername("testuser");
        user.setReadBooks(Arrays.asList());

        when(userRepository.findUserByUsername("testuser")).thenReturn(user);
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        userReadBooksServiceImpl.addBookToProfile("testuser", 1L);
    }

    @Test(expected = BookAlreadyInProfileException.class)
    public void addBookToProfile_ShouldThrowBookAlreadyInProfileException_WhenBookAlreadyInProfile() {
        User user = new User();
        user.setUsername("testuser");
        Book book = new Book();
        book.setId(1L);
        user.setReadBooks(Arrays.asList(book));

        when(userRepository.findUserByUsername("testuser")).thenReturn(user);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        userReadBooksServiceImpl.addBookToProfile("testuser", 1L);
    }

    @Test
    public void listAllBooksInProfile_ShouldReturnListOfBooksInProfile() {
        User user = new User();
        user.setUsername("testuser");
        Book book1 = new Book();
        book1.setId(1L);
        Book book2 = new Book();
        book2.setId(2L);
        user.setReadBooks(Arrays.asList(book1, book2));

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        List<Book> result = userReadBooksServiceImpl.listAllBooksInProfile("testuser");

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(1).getId()).isEqualTo(2L);
    }

    @Test(expected = UserNotFoundException.class)
    public void listAllBooksInProfile_ShouldThrowUserNotFoundException_WhenUserNotFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        userReadBooksServiceImpl.listAllBooksInProfile("testuser");
    }
}
