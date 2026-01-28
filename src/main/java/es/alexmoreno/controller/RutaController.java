/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alexmoreno.controller;

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
    
    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public RutaDTO crearRuta(@RequestBody RutaDTO ruta){
        return rutaService.crearRuta(ruta);
    }
    
    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public RutaDTO modificarRuta(@PathVariable long id,@RequestBody RutaDTO rutaDTO){
        return rutaService.modificarRuta(id, rutaDTO);
    }
    
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public void eliminarRuta(@PathVariable long id){
         rutaService.eliminarRuta(id);
    }
    
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/listar")
    public List<RutaDTO> listarVehiculos(){
        return rutaService.listarRuta();
    }
    
    @PreAuthorize("hasRole('Transportista')")
    @GetMapping("/verDatos")
    public List<Transportista_Ruta> verVehiculo(){
        return rutaService.mostrarRuta();
    }
}

