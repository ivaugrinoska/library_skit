package mk.ukim.finki.library_vp.junit;

import mk.ukim.finki.library_vp.model.ReservationCart;
import mk.ukim.finki.library_vp.model.User;
import mk.ukim.finki.library_vp.repository.ReservationCartRepository;
import mk.ukim.finki.library_vp.repository.UserRepository;
import mk.ukim.finki.library_vp.service.impl.ReservationCartServiceImpl;
import mk.ukim.finki.library_vp.utils.BaseTestData;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ReservationCartServiceTest {

    @Mock
    private ReservationCartRepository reservationCartRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    @Spy
    private ReservationCartServiceImpl reservationCartService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findCartByUserTest() {
        User user = BaseTestData.getUser();
        ReservationCart reservationCart = new ReservationCart();
        reservationCart.setUser(user);
        reservationCart.setId(1L);
        reservationCartRepository.save(reservationCart);
        when(reservationCartRepository.findReservationCartByUser(user)).thenReturn(reservationCart);

        ReservationCart result = reservationCartService.findCartByUser(user);

        assertNotNull(result);
        assertEquals(reservationCart, result);
    }

    @Test
    public void findByIdTest() {
        Long id = 1L;
        ReservationCart reservationCart = new ReservationCart();
        when(reservationCartRepository.findById(id)).thenReturn(Optional.of(reservationCart));

        ReservationCart result = reservationCartService.findById(id);

        verify(reservationCartRepository, times(1)).findById(id);
        assertNotNull(result);
        assertEquals(reservationCart, result);
    }

    @Test
    public void getActiveReservationCartTest() {
        String username = "username";
        User user = new User();
        ReservationCart reservationCart = new ReservationCart();
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(reservationCartRepository.findReservationCartByUser(user)).thenReturn(reservationCart);

        ReservationCart result = reservationCartService.getActiveReservationCart(username);

        verify(userRepository, times(1)).findByUsername(username);
        verify(reservationCartRepository, times(1)).findReservationCartByUser(user);
        assertNotNull(result);
        assertEquals(reservationCart, result);
    }
}