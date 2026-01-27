package es.alexmoreno.service;

import es.alexmoreno.domain.Rol;
import es.alexmoreno.domain.Ruta;
import es.alexmoreno.domain.Transportista;
import es.alexmoreno.domain.Transportista_Ruta;
import es.alexmoreno.domain.Usuario;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.repository.TransportistaRepository;
import es.alexmoreno.security.UserContext;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
            throw new RuntimeException("No tienes permisos para modificar transportistas");
        }
    }
    
    @Transactional
    public void eliminarTransportista(Long id){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            transportistaRepository.deleteById(id);
        }else{
            throw new RuntimeException("No tienes permisos para eliminar transportistas");
        }
    }
    
    public List<Transportista> verDatos(){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            return listarTodos();
        }else{
            List<Transportista> lista=new ArrayList<>();
            lista.add(currentUser.getTransportista());
            return lista;
        }
    }
    
    @Transactional
    public Transportista asignarVehiculo(long id,Vehiculo vehiculo){
        Usuario currentUser=userContext.getCurrentUser();
        
        if(currentUser.getRoles().contains(Rol.Admin)){
            Transportista transportista=buscarId(id);
            transportista.setVehiculo(vehiculo);
            return transportistaRepository.save(transportista);
        }else{
            throw new RuntimeException("No tienes permisos para asignar vehiculos a transportistas");
        }
    }
    
    @Transactional
    public Transportista eliminarVehiculo(long id){
        Usuario currentUser=userContext.getCurrentUser();
        
        if(currentUser.getRoles().contains(Rol.Admin)){
            Transportista transportista=buscarId(id);
            transportista.setVehiculo(null);
            return transportistaRepository.save(transportista);
        }else{
            throw new RuntimeException("No tienes permisos para eliminar vehiculos a transportistas");
        }
    }
    
    public List<Transportista> listarTodos(){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            return transportistaRepository.findAll();
        }else{
            throw new RuntimeException("No tienes permisos para ver todos los  transportistas");
        }
    }
}
