package es.alexmoreno.controller;

import es.alexmoreno.domain.Ruta;
import es.alexmoreno.domain.Transportista_Ruta;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.dto.RutaDTO;
import es.alexmoreno.service.RutaService;
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
@RequestMapping("/api/ruta")
@CrossOrigin(origins = "*")
public class RutaController {
    @Autowired
    RutaService rutaService;
    
    //Peticion http de tipo post para añadir una ruta nueva
    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public RutaDTO crearRuta(@RequestBody RutaDTO ruta){
        return rutaService.crearRuta(ruta);
    }
    //Peticion http de tipo de put para modificar los datos de una ruta ya existente
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public RutaDTO modificarRuta(@PathVariable long id,@RequestBody RutaDTO rutaDTO){
        return rutaService.modificarRuta(id, rutaDTO);
    }
    //Peticion http de tipo delete para eliminar una ruta de la base de datos 
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public void eliminarRuta(@PathVariable long id){
         rutaService.eliminarRuta(id);
    }
    //Peticion http de tipo get para mostrar todas las rutas
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/listar")
        public List<RutaDTO> listarRuta(){
        return rutaService.listarRuta();
    }
    //Peticion http de tipo get para ver las rutas del usuario actual
    @PreAuthorize("hasRole('Transportista')")
    @GetMapping("/verDatos")
    public List<Transportista_Ruta> verRuta(){
        return rutaService.mostrarRuta();
    }
    //Peticion http de tipo get para buscar una ruta por su ip
    @PreAuthorize("hasRole('Admin') or hasRole('Transportista')")
    @GetMapping("{id}")
    public RutaDTO buscarIdRuta(@PathVariable long id){
        return rutaService.buscarId(id);
    }
    
}

