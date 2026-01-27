package es.alexmoreno.repository;

import es.alexmoreno.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    Optional<Usuario> findByUsuarioNombre(String usuarioNombre);
    Optional<Usuario>findByEmail(String email);
    boolean existsByUsuarioNombre(String usuarioNombre);
    boolean existsByEmail(String email);
}
