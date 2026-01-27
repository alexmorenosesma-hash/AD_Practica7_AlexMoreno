package es.alexmoreno.repository;

import es.alexmoreno.domain.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>{
    Page<Vehiculo> findAll(Pageable pageable);
}
