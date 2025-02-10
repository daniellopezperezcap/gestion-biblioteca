package biblioteca.gestion.controller;

import biblioteca.gestion.model.Prestamo;
import biblioteca.gestion.service.PrestamoService;
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
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public List<Prestamo> getAllPrestamos() {
        return prestamoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable Long id) {
        Optional<Prestamo> prestamo = prestamoService.findById(id);
        return prestamo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prestamo createPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.save(prestamo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamoDetails) {
        Optional<Prestamo> prestamo = prestamoService.findById(id);
        if (prestamo.isPresent()) {
            Prestamo updatedPrestamo = prestamo.get();
            updatedPrestamo.setLibro(prestamoDetails.getLibro());
            updatedPrestamo.setUsuario(prestamoDetails.getUsuario());
            updatedPrestamo.setFechaPrestamo(prestamoDetails.getFechaPrestamo());
            updatedPrestamo.setFechaDevolucion(prestamoDetails.getFechaDevolucion());
            return ResponseEntity.ok(prestamoService.save(updatedPrestamo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Prestamo> partialUpdatePrestamo(@PathVariable Long id, @RequestBody Prestamo prestamoDetails) {
        Optional<Prestamo> prestamo = prestamoService.findById(id);
        if (prestamo.isPresent()) {
            Prestamo updatedPrestamo = prestamo.get();
            if (prestamoDetails.getLibro() != null) updatedPrestamo.setLibro(prestamoDetails.getLibro());
            if (prestamoDetails.getUsuario() != null) updatedPrestamo.setUsuario(prestamoDetails.getUsuario());
            if (prestamoDetails.getFechaPrestamo() != null) updatedPrestamo.setFechaPrestamo(prestamoDetails.getFechaPrestamo());
            if (prestamoDetails.getFechaDevolucion() != null) updatedPrestamo.setFechaDevolucion(prestamoDetails.getFechaDevolucion());
            return ResponseEntity.ok(prestamoService.save(updatedPrestamo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable Long id) {
        prestamoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}