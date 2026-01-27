package es.alexmoreno.controller;

import es.alexmoreno.domain.Transportista;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.service.TransportistaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PostMapping
    public Transportista crearTransportista(@RequestBody Transportista transportista){
        return transportistaService.crearTransportista(transportista);
    }
    
    @PutMapping("/{id}")
    public Transportista modificarTransportista(@PathVariable long id,@RequestBody Transportista transportista){
        return transportistaService.modificarTransportista(id, transportista);
    }
    
    @DeleteMapping("/{id}")
    public void eliminarTransportista(@PathVariable long id){
        transportistaService.eliminarTransportista(id);
    }
    
    @GetMapping
    public List<Transportista> mostrarDatos(){
        return transportistaService.verDatos();
    }
    
    @PutMapping("/{id}/asignarVehiculo")
    public Transportista asignarVehiculo(@PathVariable long id,@RequestBody Vehiculo vehiculo){
        return transportistaService.asignarVehiculo(id, vehiculo);
    }
    
    @PutMapping("/{id}/desasignarVehiculo")
    public Transportista desasignarVehiculo(@PathVariable long id){
        return transportistaService.eliminarVehiculo(id);
    }
}
