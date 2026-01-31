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
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    public RutaDTO buscarId(long id){
        Usuario currentUser=userContext.getCurrentUser();
        long idUsuario=currentUser.getIdUsuario();
        Ruta ruta=rutaRepository.findById(id).orElseThrow(()->new RuntimeException("No se ha encontrado la ruta"));
        if (currentUser.getRoles().contains(Rol.Admin)){
            return rutaAssembler.toDTO(ruta);
        }else if (currentUser.getRoles().contains(Rol.Transportista)){
            for (Transportista_Ruta tR:ruta.getTransportistaRuta()){
                if (tR.getTransportista().getUsuario().getIdUsuario() ==idUsuario){
                    return rutaAssembler.toDTO(ruta);
                }
            }
            throw new RuntimeException("No tienes permisos");
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    @Transactional
    public RutaDTO modificarRuta(long id, RutaDTO rutaDTO){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            RutaDTO original=buscarId(id);
            original.setPuntoInicio(rutaDTO.getPuntoInicio());
            original.setPuntoFinal(rutaDTO.getPuntoFinal());
            original.setHoraInicio(rutaDTO.getHoraInicio());
            original.setHoraFinal(rutaDTO.getHoraFinal());
            original.setTransportistaRuta(rutaDTO.getTransportistaRuta());
            rutaRepository.save(rutaAssembler.toEntity(original));
            return original;
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    @Transactional
    public void eliminarRuta(long id){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            rutaRepository.deleteById(id);
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    public List<RutaDTO> listarRuta(){
        Usuario currentUser=userContext.getCurrentUser();
        if (currentUser.getRoles().contains(Rol.Admin)){
            return rutaRepository.findAll().stream().map(rutaAssembler::toDTO).collect(Collectors.toList());
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
    
    public List<Transportista_Ruta> mostrarRuta(){
        Usuario currentUser=userContext.getCurrentUser();
        
        if(currentUser.getRoles().contains(Rol.Transportista)){
            return currentUser.getTransportista().getTransportistaRutas();
        }else{
            throw new RuntimeException("No tienes permisos");
        }
    }
}
