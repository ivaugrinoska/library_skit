package mk.ukim.finki.library_vp.junit;

import mk.ukim.finki.library_vp.repository.ReservationCartBooksRepository;
import mk.ukim.finki.library_vp.service.impl.ReservationCartBooksServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationCartBooksServiceTest {

    @Mock
    private ReservationCartBooksRepository reservationCartBooksRepository;

    @InjectMocks
    private ReservationCartBooksServiceImpl reservationCartBooksService;

    @Test
    public void deleteAllByResCartId_shouldDeleteAllByResCartId() {
        Long id = 1L;
        reservationCartBooksService.deleteAllByResCartId(id);
        verify(reservationCartBooksRepository, times(1)).deleteReservationCartBooksByResCartId(id);
    }
}
