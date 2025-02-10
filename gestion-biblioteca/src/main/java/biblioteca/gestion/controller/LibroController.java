package biblioteca.gestion.controller;

import biblioteca.gestion.model.Libro;
import biblioteca.gestion.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> getAllLibros() {
        return libroService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Optional<Libro> libro = libroService.findById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroService.save(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        Optional<Libro> libro = libroService.findById(id);
        if (libro.isPresent()) {
            Libro updatedLibro = libro.get();
            updatedLibro.setTitulo(libroDetails .getTitulo());
            updatedLibro.setAutor(libroDetails.getAutor());
            updatedLibro.setIsbn(libroDetails.getIsbn());
            updatedLibro.setFechaPublicacion(libroDetails.getFechaPublicacion());
            return ResponseEntity.ok(libroService.save(updatedLibro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Libro> partialUpdateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        Optional<Libro> libro = libroService.findById(id);
        if (libro.isPresent()) {
            Libro updatedLibro = libro.get();
            if (libroDetails.getTitulo() != null) updatedLibro.setTitulo(libroDetails.getTitulo());
            if (libroDetails.getAutor() != null) updatedLibro.setAutor(libroDetails.getAutor());
            if (libroDetails.getIsbn() != null) updatedLibro.setIsbn(libroDetails.getIsbn());
            if (libroDetails.getFechaPublicacion() != null) updatedLibro.setFechaPublicacion(libroDetails.getFechaPublicacion());
            return ResponseEntity.ok(libroService.save(updatedLibro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}