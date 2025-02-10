package biblioteca.gestion.service;

import biblioteca.gestion.exception.ResourceNotFoundException;
import biblioteca.gestion.model.Usuario;
import biblioteca.gestion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UsuarioService {

    private static final Logger logger = Logger.getLogger(UsuarioService.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        logger.info("Fetching all users");
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        logger.log(Level.FINE, "Fetching user with id: {0}", id);
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent()) {
            logger.log(Level.SEVERE, "User not found with id: {0}", id);
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        return usuario;
    }

    public Usuario save(Usuario usuario) {
        logger.info("Saving user with id: " + usuario.getId());
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        logger.log(Level.FINE, "Deleting user with id: {0}", id);
        if (!usuarioRepository.existsById(id)) {
            logger.log(Level.SEVERE, "User not found with id: {0}", id);
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}