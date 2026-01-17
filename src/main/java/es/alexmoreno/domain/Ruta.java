package es.alexmoreno.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Ruta")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idRuta;
    @Column(name = "puntoInicio",nullable = false,length = 75)
    String puntoInicio;
    //No podia llamar simplemente final a la variable asi que se llama puntoFinal y la de inicio paga por eso.
    @Column(name = "puntoFinal",nullable = false,length = 75)
    String puntoFinal;
    @Column(name = "horaInicio",nullable = false)
    LocalDate horaInicio;
    @Column(name = "horaFinal",nullable = false)
    LocalDate horaFinal;
    @OneToMany(mappedBy = "ruta",cascade = CascadeType.ALL)
    private List<Transportista_Ruta> transportistaRuta=new ArrayList<>();

    public Ruta() {
    }

    public Ruta(long idRuta, String puntoInicio, String puntoFinal, LocalDate horaInicio, LocalDate horaFinal) {
        this.idRuta = idRuta;
        this.puntoInicio = puntoInicio;
        this.puntoFinal = puntoFinal;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public Ruta(String puntoInicio, String puntoFinal, LocalDate horaInicio, LocalDate horaFinal) {
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

    public LocalDate getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDate horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDate horaFinal) {
        this.horaFinal = horaFinal;
    }
}
