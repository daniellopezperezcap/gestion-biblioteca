package biblioteca.gestion.service;

import biblioteca.gestion.exception.ResourceNotFoundException;
import biblioteca.gestion.model.Libro;
import biblioteca.gestion.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LibroService {

    private static final Logger logger = Logger.getLogger(LibroService.class.getName());

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> findAll() {
        logger.info("Fetching all books");
        return libroRepository.findAll();
    }

    public Optional<Libro> findById(Long id) {
        logger.log(Level.FINE, "Fetching book with id: {0}", id);
        Optional<Libro> libro = libroRepository.findById(id);
        if (!libro.isPresent()) {
            logger.log(Level.SEVERE, "Book not found with id: {0}", id);
            throw new ResourceNotFoundException("Libro no encontrado con id: " + id);
        }
        return libro;
    }

    public Libro save(Libro libro) {
        logger.info("Saving book with title: " + libro.getTitulo());
        return libroRepository.save(libro);
    }

    public void deleteById(Long id) {
        logger.log(Level.FINE, "Deleting book with id: {0}", id);
        if (!libroRepository.existsById(id)) {
            logger.log(Level.SEVERE, "Book not found with id: {0}", id);
            throw new ResourceNotFoundException("Libro no encontrado con id: " + id);
        }
        libroRepository.deleteById(id);
    }
}