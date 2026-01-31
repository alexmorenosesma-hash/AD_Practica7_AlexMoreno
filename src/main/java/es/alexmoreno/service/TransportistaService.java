package es.alexmoreno.service;

import es.alexmoreno.domain.Rol;
import es.alexmoreno.domain.Ruta;
import es.alexmoreno.domain.Transportista;
import es.alexmoreno.domain.Transportista_Ruta;
import es.alexmoreno.domain.Usuario;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.repository.RutaRepository;
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
    private RutaRepository rutaRepository;
    @Autowired
    private  UserContext userContext;
    
    //Metodo para crear transportistas
    @Transactional
    public Transportista crearTransportista(Transportista transportista){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            return transportistaRepository.save(transportista);
        } else {
            throw new RuntimeException("No tienes permisos");
        }
    }
    //Metodo para buscar un transportista por su id
    public Transportista buscarId(long id){
        Usuario currentUser=userContext.getCurrentUser();
        long idUser=currentUser.getIdUsuario();
        Transportista transportista=transportistaRepository.findById(id).orElseThrow(()->new RuntimeException("El transportista buscado no existe"));
        if (currentUser.getRoles().contains(Rol.Admin)){
            return transportista;
        }else if (currentUser.getRoles().contains(Rol.Transportista) && idUser==transportista.getUsuario().getIdUsuario()){
            return transportista;
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    //Metodo para modificar un transportista
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
    //Metodo para eliminar un transportista
    @Transactional
    public void eliminarTransportista(Long id){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            transportistaRepository.deleteById(id);
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    //Metodo para ver los datos de un transportista
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
    
    //Metodo para asignar un vehiculo a un transportista
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
    
    //Metodo para quitar un vehiculo a un transportista
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

    //Metodo para asignar un usuario a un transportista
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
    //Metodo para asignar una ruta a un transportista
    @Transactional
    public Transportista asignarRuta(long id,long idRuta){
        Usuario currentUser=userContext.getCurrentUser();
        if(currentUser.getRoles().contains(Rol.Admin)){
            Transportista transportista=buscarId(id);
            Ruta ruta=rutaRepository.findById(idRuta).orElseThrow(()->new RuntimeException("Ruta no encontrada"));
            Transportista_Ruta tR=new Transportista_Ruta();
            tR.setRuta(ruta);
            tR.setTransportista(transportista);
            transportista.getTransportistaRutas().add(tR);
            return transportistaRepository.save(transportista);
        }else{
            throw new RuntimeException("No tienes permisos"); 
        }
    }
    //Metodo para quitar una ruta de un transportista
    @Transactional
    public Transportista eliminarRuta(long id,long idRuta){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            Transportista transportista=buscarId(id);
            transportista.getTransportistaRutas().removeIf(tr->tr.getRuta().getIdRuta()==idRuta);
            return transportistaRepository.save(transportista);
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    //Metodo para mostrar todos los transportistas
    public List<Transportista> listarTodos(){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            return transportistaRepository.findAll();
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
}
