package com.company.mi_api.controller;

import com.company.mi_api.dto.ActualizarUsuarioRequest;
import com.company.mi_api.dto.CrearUsuarioRequest;
import com.company.mi_api.dto.UsuarioResponse;
import com.company.mi_api.model.Usuario;
import com.company.mi_api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    /**
     * Obtener todos los usuarios
     * GET /api/usuarios
     *
     * @return lista de usuarios con código 200 OK
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerTodos() {
        log.info("GET /api/usuarios - Obteniendo todos los usuarios");
        List<UsuarioResponse> usuarios = usuarioService.obtenerTodos().stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Obtener usuario por ID
     * GET /api/usuarios/{id}
     *
     * @param id del usuario
     * @return usuario encontrado con código 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/usuarios/{} - Obteniendo usuario por ID", id);
        Usuario usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(UsuarioResponse.fromEntity(usuario));
    }

    /**
     * Crear nuevo usuario
     * POST /api/usuarios
     *
     * @param request datos del nuevo usuario
     * @return usuario creado con código 201 CREATED
     */
    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@Valid @RequestBody CrearUsuarioRequest request) {
        log.info("POST /api/usuarios - Creando nuevo usuario con email: {}", request.getEmail());

        // Convertir DTO a Entity
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());

        Usuario usuarioGuardado = usuarioService.crear(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.fromEntity(usuarioGuardado));
    }

    /**
     * Actualizar usuario existente
     * PUT /api/usuarios/{id}
     *
     * @param id del usuario a actualizar
     * @param usuario datos actualizados
     * @return usuario actualizado con código 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarUsuarioRequest request) {
        log.info("PUT /api/usuarios/{} - Actualizando usuario", id);

        // Convertir DTO a Entity
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());

        Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
        return ResponseEntity.ok(UsuarioResponse.fromEntity(usuarioActualizado));
    }

    /**
     * Eliminar usuario por ID
     * DELETE /api/usuarios/{id}
     *
     * @param id del usuario a eliminar
     * @return código 204 NO CONTENT (sin body)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/usuarios/{} - Eliminando usuario", id);
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Buscar usuarios por nombre
     * GET /api/usuarios/buscar?nombre=Juan
     *
     * @param nombre texto a buscar
     * @return lista de usuarios encontrados
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioResponse>> buscarPorNombre(@RequestParam String nombre) {
        log.info("GET /api/usuarios/buscar - Buscando usuarios con nombre: {}", nombre);
        List<UsuarioResponse> usuarios = usuarioService.buscarPorNombre(nombre).stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(usuarios);
    }
}