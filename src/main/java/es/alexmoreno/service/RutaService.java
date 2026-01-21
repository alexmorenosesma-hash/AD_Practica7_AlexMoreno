package es.alexmoreno.service;

import es.alexmoreno.assembler.RutaAssembler;
import es.alexmoreno.domain.Rol;
import es.alexmoreno.domain.Ruta;
import es.alexmoreno.domain.Transportista_Ruta;
import es.alexmoreno.domain.Usuario;
import es.alexmoreno.domain.Vehiculo;
import es.alexmoreno.dto.RutaDTO;
import es.alexmoreno.repository.RutaRepository;
import es.alexmoreno.security.UserContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaService {
    @Autowired
    RutaRepository rutaRepository;
    @Autowired
    UserContext userContext;
    @Autowired
    RutaAssembler rutaAssembler;
    
    @Transactional
    public RutaDTO crearRuta(RutaDTO rutaDTO){
        Usuario currentUser=userContext.getCurrentUser();
        
        if (currentUser.getRoles().contains(Rol.Admin)){
            Ruta ruta=rutaAssembler.toEntity(rutaDTO);
            rutaRepository.save(ruta);
            return rutaAssembler.toDTO(ruta);
        }else{
            throw new RuntimeException("No tienes privilegios suficientes para modificar la ruta");
        }
    }
    
    public Ruta buscarRuta(long id){
        return rutaRepository.findById(id).orElseThrow(()->new RuntimeException("No se ha encontrado la ruta"));
    }
    
    @Transactional
    public RutaDTO modificarRuta(long id, RutaDTO rutaDTO){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            Ruta original=buscarRuta(id);
            Ruta dto=rutaAssembler.toEntity(rutaDTO);
            original.setPuntoInicio(dto.getPuntoInicio());
            original.setPuntoFinal(dto.getPuntoFinal());
            original.setHoraInicio(dto.getHoraInicio());
            original.setHoraFinal(dto.getHoraFinal());
            original.setTransportistaRuta(dto.getTransportistaRuta());
            rutaRepository.save(original);
            return rutaAssembler.toDTO(original);
        }else{
            throw new RuntimeException("No tienes permisos suficentes para modificar una ruta");
        }
    }
    
    @Transactional
    public void eliminarRuta(long id){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            rutaRepository.deleteById(id);
        }else{
            throw new RuntimeException("No tienes permisos para eliminar rutas");
        }
    }
    
    public List<RutaDTO> listarRuta(){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            return rutaRepository.findAll().stream().map(ruta->new RutaAssembler().toDTO(ruta)).collect(Collectors.toList());
        }else{
            throw new RuntimeException("No tienes permisos para ver a todos las rutas");
        }
    }
    
    public List<Transportista_Ruta> mostrarRuta(){
        Usuario currentUser=userContext.getCurrentUser();
        
        if(currentUser.getRoles().contains(Rol.Transportista)){
            return currentUser.getTransportista().getTransportistaRutas();
        }else{
            throw new RuntimeException("No tienes suficientes permisos");
        }
    }
}
