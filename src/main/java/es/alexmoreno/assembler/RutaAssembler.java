package es.alexmoreno.assembler;

import es.alexmoreno.domain.Ruta;
import es.alexmoreno.dto.RutaDTO;
import java.time.LocalDate;
import java.time.LocalTime;


public class RutaAssembler {
    public RutaDTO toDTO(Ruta ruta){
        RutaDTO rutaDTO=new RutaDTO();
        rutaDTO.setIdRuta(ruta.getIdRuta());
        rutaDTO.setPuntoInicio(ruta.getPuntoInicio());
        rutaDTO.setPuntoFinal(ruta.getPuntoFinal());
        rutaDTO.setHoraInicio(ruta.getHoraInicio().toString());
        rutaDTO.setHoraFinal(ruta.getHoraFinal().toString());
        return rutaDTO;
    }
    public Ruta toEntity(RutaDTO rutaDTO){
        Ruta ruta=new Ruta();
        ruta.setIdRuta(rutaDTO.getIdRuta());
        ruta.setPuntoInicio(rutaDTO.getPuntoInicio());
        ruta.setPuntoFinal(rutaDTO.getPuntoFinal());
        ruta.setHoraInicio(LocalTime.parse(rutaDTO.getHoraInicio()));
        ruta.setHoraFinal(LocalTime.parse(rutaDTO.getHoraFinal().toString()));
        return ruta;
    }
    
}
