package es.alexmoreno.controller;

import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.service.VehiculoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {
    @Autowired
    VehiculoService vehiculoService;
    
    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public Vehiculo crearVehiculo(@RequestBody Vehiculo vehiculo){
        return vehiculoService.crearVehiculo(vehiculo);
    }
    
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public Vehiculo modificarVehiculo(@PathVariable long id,@RequestBody Vehiculo vehiculo){
        return vehiculoService.modificarVehiculo(id, vehiculo);
    }
    
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public void eliminarVehiculo(@PathVariable long id){
         vehiculoService.eliminarVehiculo(id);
    }
    
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/listar")
    public List<Vehiculo> listarVehiculos(){
        return vehiculoService.listarVehiculos();
    }
    @PreAuthorize("hasRole('Transportista')")
    @GetMapping("/verDatos")
    public Vehiculo verVehiculo(){
        return vehiculoService.mostrarVehiculo();
    }
}
