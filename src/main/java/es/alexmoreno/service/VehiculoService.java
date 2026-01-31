package es.alexmoreno.service;

import es.alexmoreno.domain.Rol;
import es.alexmoreno.domain.Usuario;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.repository.VehiculoRepository;
import es.alexmoreno.security.UserContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private  UserContext userContext;
    
    //Metodo para crear un vehiculo
    @Transactional
    public Vehiculo crearVehiculo(Vehiculo vehiculo){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
           return vehiculoRepository.save(vehiculo);
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    //Metodo para buscar por medio de una id
    public Vehiculo buscarId (long id){
        Usuario currentUser=userContext.getCurrentUser();
        long idUsuario=currentUser.getIdUsuario();
        Vehiculo vehiculo=vehiculoRepository.findById(id).orElseThrow(()->new RuntimeException("No se ha encontrado el vehiculo"));
        if (currentUser.getRoles().contains(Rol.Admin)){
            return vehiculo;
        }else if(currentUser.getRoles().contains(Rol.Transportista) && idUsuario==vehiculo.getTransportista().getUsuario().getIdUsuario()){
            return vehiculo;
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    //Metodo para modificar las propiedades de un vehiculo
    @Transactional
    public Vehiculo modificarVehiculo(long id,Vehiculo vehiculo){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            Vehiculo original=buscarId(id);
            original.setColor(vehiculo.getColor());
            original.setMatricula(vehiculo.getMatricula());
            original.setModelo(vehiculo.getModelo());
            original.setPeso(vehiculo.getPeso());
            original.setTransportista(vehiculo.getTransportista());
            return vehiculoRepository.save(original);
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    //Metodo para eliminar un vehiculo de la base de datos
    @Transactional
    public void eliminarVehiculo(long id){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            vehiculoRepository.deleteById(id);
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    //Metodo para mostrar todos los vehiculos existentes
    public List<Vehiculo> listarVehiculos(){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            return vehiculoRepository.findAll();
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    //Metodo para mostrar los datos del vehiculo actual del transportista
    public Vehiculo mostrarVehiculo(){
        Usuario currentUser=userContext.getCurrentUser();
        
        if(currentUser.getRoles().contains(Rol.Transportista)){
            return currentUser.getTransportista().getVehiculo();
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
}
