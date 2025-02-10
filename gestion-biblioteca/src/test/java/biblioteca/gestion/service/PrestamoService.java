package biblioteca.gestion.service;

import biblioteca.gestion.model.Prestamo;
import biblioteca.gestion.repository.PrestamoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestamoServiceTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoService prestamoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setId(1L);

        Prestamo prestamo2 = new Prestamo();
        prestamo2.setId(2L);

        when(prestamoRepository.findAll()).thenReturn(Arrays.asList(prestamo1, prestamo2));

        List<Prestamo> prestamos = prestamoService.findAll();
        assertEquals(2, prestamos.size());
        verify(prestamoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1L);

        when(prestamoRepository.findById(1L)).thenReturn(Optional.of(prestamo));

        Optional<Prestamo> foundPrestamo = prestamoService.findById(1L);
        assertTrue(foundPrestamo.isPresent());
        assertEquals(1L, foundPrestamo.get().getId());
        verify(prestamoRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1L);

        when(prestamoRepository.save(prestamo)).thenReturn(prestamo);

        Prestamo savedPrestamo = prestamoService.save(prestamo);
        assertEquals(1L, savedPrestamo.getId());
        verify(prestamoRepository, times(1)).save(prestamo);
    }

    @Test
    void testDeleteById() {
        when(prestamoRepository.existsById(1L)).thenReturn(true);
        prestamoService.deleteById(1L);
        verify(prestamoRepository, times(1)).deleteById(1L);
    }
}