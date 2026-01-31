package es.alexmoreno.repository;

import es.alexmoreno.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    //Encontrar usuario por nombre
    Optional<Usuario> findByUsuarioNombre(String usuarioNombre);
    //Encontrar usuario por email
    Optional<Usuario>findByEmail(String email);
    //Comprobar si existe el usuario
    boolean existsByUsuarioNombre(String usuarioNombre);
    //Comprobar si existe el email
    boolean existsByEmail(String email);
}
