package biblioteca.gestion.service;

import biblioteca.gestion.exception.ResourceNotFoundException;
import biblioteca.gestion.model.Prestamo;
import biblioteca.gestion.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PrestamoService {

    private static final Logger logger = Logger.getLogger(PrestamoService.class.getName());

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Prestamo> findAll() {
        logger.info("Fetching all loans");
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> findById(Long id) {
        logger.log(Level.FINE, "Fetching loan with id: {0}", id);
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if (!prestamo.isPresent()) {
            logger.log(Level.SEVERE, "Loan not found with id: {0}", id);
            throw new ResourceNotFoundException("Préstamo no encontrado con id: " + id);
        }
        return prestamo;
    }

    public Prestamo save(Prestamo prestamo) {
        logger.info("Saving loan with id: " + prestamo.getId());
        return prestamoRepository.save(prestamo);
    }

    public void deleteById(Long id) {
        logger.log(Level.FINE, "Deleting loan with id: {0}", id);
        if (!prestamoRepository.existsById(id)) {
            logger.log(Level.SEVERE, "Loan not found with id: {0}", id);
            throw new ResourceNotFoundException("Préstamo no encontrado con id: " + id);
        }
        prestamoRepository.deleteById(id);
    }
}