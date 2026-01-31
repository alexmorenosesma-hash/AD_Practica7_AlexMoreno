package es.alexmoreno.controller;

import es.alexmoreno.domain.Transportista;
import es.alexmoreno.domain.Usuario;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.service.TransportistaService;
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
@RequestMapping("/api/transportistas")
@CrossOrigin(origins = "*")
public class TransportistaController {
    @Autowired
    private TransportistaService transportistaService;
    
    //Peticion http de tipo post para crear un nuevo transportista
    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public Transportista crearTransportista(@RequestBody Transportista transportista){
        return transportistaService.crearTransportista(transportista);
    }
    //Peticion http de tipo put para modificar las propiedades de un transportista ya existente
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public Transportista modificarTransportista(@PathVariable long id,@RequestBody Transportista transportista){
        return transportistaService.modificarTransportista(id, transportista);
    }
    //Peticion http de tipo delete para eliminar un transportista de la base de datos
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public void eliminarTransportista(@PathVariable long id){
        transportistaService.eliminarTransportista(id);
    }
    //Peticion http de tipo get para mostrar transportistas
    @PreAuthorize("hasRole('Transportista') or hasRole('Admin')")
    @GetMapping
    public List<Transportista> mostrarDatos(){
        return transportistaService.verDatos();
    }
    
    //Peticion http de tipo put para añadir vehiculos a un transportista
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}/asignarVehiculo/{idVehiculo}")
    public Transportista asignarVehiculo(@PathVariable long id,@PathVariable long idVehiculo){
        return transportistaService.asignarVehiculo(id, idVehiculo);
    }
    //Peticion http de tipo put para eliminar vehiculo a un transportista
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}/desasignarVehiculo")
    public Transportista desasignarVehiculo(@PathVariable long id){
        return transportistaService.eliminarVehiculo(id);
    }
    //Peticion http de tipo put para añadir un usuario a un transportista
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}/asignarUsuario/{idUsuario}")
    public Transportista asignarUsuario(@PathVariable long id, @PathVariable long idUsuario){
        return transportistaService.asignarUsuario(id, idUsuario);
    }
    //Peticion http de tipo put para añadir rutas a un transportista
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}/asignarRuta/{idRuta}")
    public Transportista asignarRuta(@PathVariable long id, @PathVariable long idRuta){
        return transportistaService.asignarRuta(id, idRuta);
    }
    //Peticion http de tipo put para quitar rutas a un transportista
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}/desasignarRuta/{idRuta}")
    public Transportista quitarRuta(@PathVariable long id, @PathVariable long idRuta){
        return transportistaService.eliminarRuta(id, idRuta);
    }
    //Peticion http de tipo get para buscar un transportista por su id
    @PreAuthorize("hasRole('Admin') or hasRole('Transportista')")
    @GetMapping("{id}")
    public Transportista buscarIDTransportista(@PathVariable long id){
        return transportistaService.buscarId(id);
    }
}
