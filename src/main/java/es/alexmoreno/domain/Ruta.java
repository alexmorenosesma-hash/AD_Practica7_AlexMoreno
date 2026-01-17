package es.alexmoreno.domain;

import java.time.LocalDate;


public class Ruta {
    long idRuta;
    String puntoInicio;
    //No podia llamar simplemente final a la variable asi que se llama puntoFinal y la de inicio paga por eso.
    String puntoFinal;
    LocalDate horaInicio;
    LocalDate horaFinal;

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
