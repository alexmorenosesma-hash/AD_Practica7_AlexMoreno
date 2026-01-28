package es.alexmoreno.service;

import es.alexmoreno.domain.Rol;
import es.alexmoreno.domain.Ruta;
import es.alexmoreno.domain.Transportista;
import es.alexmoreno.domain.Transportista_Ruta;
import es.alexmoreno.domain.Usuario;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.repository.TransportistaRepository;
import es.alexmoreno.repository.UsuarioRepository;
import es.alexmoreno.repository.VehiculoRepository;
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
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private  UserContext userContext;
    
    @Transactional
    public Transportista crearTransportista(Transportista transportista){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            return transportistaRepository.save(transportista);
        } else {
            throw new RuntimeException("No tienes permisos");
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
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    @Transactional
    public void eliminarTransportista(Long id){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            transportistaRepository.deleteById(id);
        }else{
            throw new RuntimeException("No tienes permisos");
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
    public Transportista asignarVehiculo(long id,long idVehiculo){
        Usuario currentUser=userContext.getCurrentUser();
        
        if(currentUser.getRoles().contains(Rol.Admin)){
            Transportista transportista=buscarId(id);
            Vehiculo vehiculo=vehiculoRepository.findById(idVehiculo).orElseThrow(()->new RuntimeException("Vehiculo no encontrado"));
            transportista.setVehiculo(vehiculo);
            return transportistaRepository.save(transportista);
        }else{
            throw new RuntimeException("No tienes permisos");
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
            throw new RuntimeException("No tienes permisos");
        }
    }

    @Transactional
    public Transportista asignarUsuario(long id,long idUsuario){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            Transportista transportista=buscarId(id);
            Usuario usuario=usuarioRepository.findById(idUsuario).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
            transportista.setUsuario(usuario);
            return transportistaRepository.save(transportista);
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    public List<Transportista> listarTodos(){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            return transportistaRepository.findAll();
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
}
