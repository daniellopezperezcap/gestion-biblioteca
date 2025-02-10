package biblioteca.gestion.repository;

import biblioteca.gestion.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}