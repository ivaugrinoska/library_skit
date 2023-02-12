package mk.ukim.finki.library_vp.junit;

import mk.ukim.finki.library_vp.model.Book;
import mk.ukim.finki.library_vp.model.Review;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.repository.BookRepository;
import mk.ukim.finki.library_vp.repository.ReviewRepository;
import mk.ukim.finki.library_vp.service.impl.ReviewServiceImpl;
import mk.ukim.finki.library_vp.utils.BaseTestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository repository;
    @Mock
    private BookRepository bookRepository;
    private ReviewServiceImpl reviewService;

    @Before
    public void setUp() {
        reviewService = new ReviewServiceImpl(repository, bookRepository);
    }

    @Test
    public void testFindReviewsByBook() {
        Book book = BaseTestData.getBook();
        Review review1 = new Review("Review 1", null, book);
        Review review2 = new Review("Review 2", null, book);
        when(repository.findAllByBook(book)).thenReturn(Arrays.asList(review1, review2));

        List<Review> reviews = reviewService.findReviewsByBook(book);
        assertEquals(2, reviews.size());
        assertEquals(review1, reviews.get(0));
        assertEquals(review2, reviews.get(1));
    }

    @Test
    public void testAddNewReview() {
        Book book = BaseTestData.getBook();
        when(bookRepository.getById(1L)).thenReturn(book);
        User user = BaseTestData.getUser();
        Review review = new Review("Review", user, book);
        when(repository.save(review)).thenReturn(review);

        Review result = reviewService.addNewReview("Review", 1L, user);
        assertEquals(review, result);
    }
}

