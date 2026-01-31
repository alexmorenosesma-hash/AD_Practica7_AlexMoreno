package es.alexmoreno.repository;

import es.alexmoreno.domain.Transportista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransportistaRepository extends JpaRepository<Transportista,Long>{
    //Para obtener todas los transportistas
    Page<Transportista> findAll(Pageable pageable);
}
