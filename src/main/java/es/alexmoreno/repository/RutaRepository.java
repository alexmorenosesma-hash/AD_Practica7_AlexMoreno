package es.alexmoreno.repository;

import es.alexmoreno.domain.Ruta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RutaRepository extends JpaRepository<Ruta, Long>{
    //Para obtener todas las rutas
    Page <Ruta> findAll(Pageable pageable);
}
