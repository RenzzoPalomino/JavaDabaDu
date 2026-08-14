package com.company.mi_api.service;

import com.company.mi_api.exception.UsuarioNoEncontradoException;
import com.company.mi_api.model.Usuario;
import com.company.mi_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository repository;

    /**
     * Obtener todos los usuarios
     * @return lista de todos los usuarios
     */
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        log.info("Obteniendo todos los usuarios");
        return repository.findAll();
    }

    /**
     * Obtener usuario por ID
     * @param id del usuario
     * @return Usuario encontrado
     * @throws UsuarioNoEncontradoException si no existe
     */
    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {
        log.info("Buscando usuario con ID: {}", id);

        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuario con ID {} no encontrado", id);
                    return new UsuarioNoEncontradoException(
                            "Usuario con ID " + id + " no encontrado"
                    );
                });
    }

    /**
     * Buscar usuario por email
     * @param email del usuario
     * @return Usuario encontrado
     * @throws UsuarioNoEncontradoException si no existe
     */
    @Transactional(readOnly = true)
    public Usuario obtenerPorEmail(String email) {
        log.info("Buscando usuario con email: {}", email);

        return repository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Usuario con email {} no encontrado", email);
                    return new UsuarioNoEncontradoException(
                            "Usuario con email " + email + " no encontrado"
                    );
                });
    }

    /**
     * Crear nuevo usuario
     * @param usuario objeto con datos del nuevo usuario
     * @return Usuario guardado
     * @throws IllegalArgumentException si email ya existe
     */
    public Usuario crear(Usuario usuario) {
        log.info("Creando nuevo usuario con email: {}", usuario.getEmail());

        // Validar que no exista usuario con ese email
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            log.warn("Intento de crear usuario con email duplicado: {}", usuario.getEmail());
            throw new IllegalArgumentException(
                    "Ya existe un usuario con el email: " + usuario.getEmail()
            );
        }

        // Asignar fecha de creación
        usuario.setFechaCreacion(LocalDateTime.now());

        Usuario guardado = repository.save(usuario);
        log.info("Usuario creado exitosamente con ID: {}", guardado.getId());

        return guardado;
    }

    /**
     * Actualizar usuario existente
     * @param id del usuario a actualizar
     * @param usuarioActualizado datos nuevos
     * @return Usuario actualizado
     * @throws UsuarioNoEncontradoException si no existe
     */
    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        log.info("Actualizando usuario con ID: {}", id);

        Usuario usuario = obtenerPorId(id);  // Lanza excepción si no existe

        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());

        Usuario guardado = repository.save(usuario);
        log.info("Usuario con ID {} actualizado exitosamente", id);

        return guardado;
    }

    /**
     * Eliminar usuario por ID
     * @param id del usuario a eliminar
     * @throws UsuarioNoEncontradoException si no existe
     */
    public void eliminar(Long id) {
        log.info("Eliminando usuario con ID: {}", id);

        if (!repository.existsById(id)) {
            log.warn("Intento de eliminar usuario inexistente con ID: {}", id);
            throw new UsuarioNoEncontradoException(
                    "Usuario con ID " + id + " no encontrado"
            );
        }

        repository.deleteById(id);
        log.info("Usuario con ID {} eliminado exitosamente", id);
    }

    /**
     * Buscar usuarios cuyo nombre contenga el texto
     * @param nombre texto a buscar
     * @return lista de usuarios encontrados
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNombre(String nombre) {
        log.info("Buscando usuarios con nombre que contenga: {}", nombre);
        return repository.findByNombreContainingIgnoreCase(nombre);
    }
}