package es.alexmoreno.dto;

import es.alexmoreno.domain.Transportista_Ruta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RutaDTO {
    long idRuta;
    String puntoInicio;
    String puntoFinal;
    String horaInicio;
    String horaFinal;
    private List<Transportista_Ruta> transportistaRuta=new ArrayList<>();

    public RutaDTO() {
    }

    public RutaDTO(long idRuta, String puntoInicio, String puntoFinal, String horaInicio, String horaFinal) {
        this.idRuta = idRuta;
        this.puntoInicio = puntoInicio;
        this.puntoFinal = puntoFinal;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
    public long getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(long idRuta) {
        this.idRuta = idRuta;
    }

    public String getPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(String puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public String getPuntoFinal() {
        return puntoFinal;
    }

    public void setPuntoFinal(String puntoFinal) {
        this.puntoFinal = puntoFinal;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public List<Transportista_Ruta> getTransportistaRuta() {
        return transportistaRuta;
    }

    public void setTransportistaRuta(List<Transportista_Ruta> transportistaRuta) {
        this.transportistaRuta = transportistaRuta;
    }
    
    @Override
    public String toString() {
        return "IdRuta:"+idRuta
                +"\nPunto de Inicio:"+puntoInicio
                +"\nPunto Final:"+puntoFinal
                +"\nHora Inicio:"+horaInicio
                +"\nHora Final:"+horaFinal
                +"\nTransportistas IDs:"+transportistaRuta;
    }
}
