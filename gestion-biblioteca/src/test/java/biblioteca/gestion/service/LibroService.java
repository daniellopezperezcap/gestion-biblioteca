package biblioteca.gestion.service;

import biblioteca.gestion.model.Libro;
import biblioteca.gestion.repository.LibroRepository;
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
class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Libro libro1 = new Libro();
        libro1.setId(1L);
        libro1.setTitulo("Libro 1");

        Libro libro2 = new Libro();
        libro2.setId(2L);
        libro2.setTitulo("Libro 2");

        when(libroRepository.findAll()).thenReturn(Arrays.asList(libro1, libro2));

        List<Libro> libros = libroService.findAll();
        assertEquals(2, libros.size());
        verify(libroRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Libro libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("Libro 1");

        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));

        Optional<Libro> foundLibro = libroService.findById(1L);
        assertTrue(foundLibro.isPresent());
        assertEquals("Libro 1", foundLibro.get().getTitulo());
        verify(libroRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        Libro libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("Nuevo Libro");

        when(libroRepository.save(libro)).thenReturn(libro);

        Libro savedLibro = libroService.save(libro);
        assertEquals("Nuevo Libro", savedLibro.getTitulo());
        verify(libroRepository, times(1)).save(libro);
    }

    @Test
    void testDeleteById() {
        when(libroRepository.existsById(1L)).thenReturn(true);
        libroService.deleteById(1L);
        verify(libroRepository, times(1)).deleteById(1L);
    }
}