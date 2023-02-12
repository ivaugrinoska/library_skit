package mk.ukim.finki.library_vp.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Category;
import mk.ukim.finki.library_vp.model.exceptions.BookNotFoundException;
import mk.ukim.finki.library_vp.repository.BookRepository;
import mk.ukim.finki.library_vp.repository.CategoryRepository;
import mk.ukim.finki.library_vp.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private List<Book> books;
    private Book book;
    private Category category;

    @BeforeEach
    void setUp() {
        books = new ArrayList<>();
        book = new Book("Book1", "Author1", 10, 5.0f, "Description1", "url1", new Category("Category1"));
        books.add(book);
        category = new Category("Category1");
    }

    @Test
    void testFindAll() {
        Mockito.when(bookRepository.findAllByOrderByCategoryAsc()).thenReturn(books);
        List<Book> result = bookService.findAll();
        assertEquals(books, result);
    }

    @Test
    void testFindBookById() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
        Book result = bookService.findBookById(1L);
        assertEquals(book, result);
    }

    @Test
    void testFindBookByIdNotFound() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.findBookById(1L));
    }

    @Test
    void testFindById() {
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
        Optional<Book> result = bookService.findById(1L);
        assertEquals(Optional.of(book), result);
    }

    @Test
    void testSearchByTitleOrAuthor() {
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        List<Book> result = bookService.searchByTitleOrAuthor("Book");
        assertEquals(books, result);
    }

    @Test
    void testSearchByCategory() {
        Mockito.when(bookRepository.findAllByCategory(Mockito.any(Category.class))).thenReturn(books);
        List<Book> result = bookService.searchByCategory(category);
        assertEquals(books, result);
    }
}
