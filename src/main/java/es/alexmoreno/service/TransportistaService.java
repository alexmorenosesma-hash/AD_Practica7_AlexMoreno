package es.alexmoreno.service;

import es.alexmoreno.domain.Rol;
import es.alexmoreno.domain.Transportista;
import es.alexmoreno.domain.Usuario;
import es.alexmoreno.repository.TransportistaRepository;
import es.alexmoreno.security.UserContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportistaService {
    @Autowired
    private  TransportistaRepository transportistaRepository;
    @Autowired
    private  UserContext userContext;
    
    @Transactional
    public Transportista crearTransportista(Transportista transportista){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            return transportistaRepository.save(transportista);
        } else {
            throw new RuntimeException("No tienes permisos para crear transportistas");
        }
    }
    
    public Transportista buscarId(long id){
        return transportistaRepository.findById(id).orElseThrow(()->new RuntimeException("El transportista buscado no existe"));
    }
    
    @Transactional
    public Transportista modificarTransportista(Long id,Transportista transportista){
        Usuario currentUser=userContext.getCurrentUser();
        
        if(currentUser.getRoles().contains(Rol.Admin)){
            Transportista original = buscarId(id);
            original.setNombre(transportista.getNombre());
            original.setApellido(transportista.getApellido());
            original.setDni(transportista.getDni());
            original.setTransportistaRutas(transportista.getTransportistaRutas());
            original.setVehiculo(transportista.getVehiculo());
            original.setUsuario(transportista.getUsuario());
            return transportistaRepository.save(original);
        }else{
            throw new RuntimeException("No tienes permisos para crear transportistas");
        }
    }
}
