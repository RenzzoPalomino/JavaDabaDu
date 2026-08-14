package com.company.mi_api.repository;

import com.company.mi_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Buscar usuario por email
     * @param email el email a buscar
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Buscar usuarios cuyo nombre contenga el texto
     * (case-insensitive)
     * @param nombre texto a buscar
     * @return lista de usuarios encontrados
     */
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Query personalizada - obtener usuarios ordenados por nombre
     * @return lista de usuarios ordenados alfabéticamente
     */
    @Query("SELECT u FROM Usuario u ORDER BY u.nombre ASC")
    List<Usuario> obtenerUsuariosOrdenados();

    /**
     * Query personalizada - buscar por email con Query JPQL
     * @param email el email a buscar
     * @return Optional con el usuario
     */
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> buscarPorEmailCustom(@Param("email") String email);
}