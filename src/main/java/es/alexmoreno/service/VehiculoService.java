package es.alexmoreno.service;

import es.alexmoreno.domain.Rol;
import es.alexmoreno.domain.Usuario;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.repository.VehiculoRepository;
import es.alexmoreno.security.UserContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private  UserContext userContext;
    
    public Vehiculo crearVehiculo(Vehiculo vehiculo){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
           return vehiculoRepository.save(vehiculo);
        }else{
            throw new RuntimeException("No tienes los privilegion necesarios para crear un vehiculo");
        }
    }
    
    //Es buscar por id
    public Vehiculo buscarVehiculo(long id){
        return vehiculoRepository.findById(id).orElseThrow(()->new RuntimeException("No se ha encontrado el vehiculo"));
    }
    
    public Vehiculo modificarVehiculo(long id,Vehiculo vehiculo){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            Vehiculo original=buscarVehiculo(id);
            original.setColor(vehiculo.getColor());
            original.setMatricula(vehiculo.getMatricula());
            original.setModelo(vehiculo.getModelo());
            original.setPeso(vehiculo.getPeso());
            original.setTransportista(vehiculo.getTransportista());
            return vehiculoRepository.save(original);
        }else{
            throw new RuntimeException("No tienes permisos suficiente para modificar un vehiculo");
        }
    }
    
    public List<Vehiculo> listarVehiculos(){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            return vehiculoRepository.findAll();
        }else{
            throw new RuntimeException("No tienes permisos para ver a todos los vehiculos");
        }
    }
    
    public Vehiculo mostrarVehiculo(){
        Usuario currentUser=userContext.getCurrentUser();
        
        if(currentUser.getRoles().contains(Rol.Transportista)){
            return currentUser.getTransportista().getVehiculo();
        }else{
            throw new RuntimeException("No tienes suficientes permisos");
        }
    }
}
