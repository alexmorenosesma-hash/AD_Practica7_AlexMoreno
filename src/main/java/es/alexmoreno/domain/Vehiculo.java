package es.alexmoreno.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVehiculo;
    @Column(name ="modelo",nullable = false,length = 50)
    private String modelo;
    @Column(name = "peso",nullable = true)
    private double peso;
    @Column(name = "color",nullable = true, length = 20)
    private String color;
    @Column(name = "matricula",nullable = false,length = 7)
    private String matricula;
    @OneToOne(mappedBy = "vehiculo")
    private Transportista transportista;
    public Vehiculo() {
    }

    public Vehiculo(long idVehiculo, String modelo, double peso, String color, String matricula) {
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.peso = peso;
        this.color = color;
        this.matricula = matricula;
    }

    public Vehiculo(String modelo, double peso, String color, String matricula) {
        this.modelo = modelo;
        this.peso = peso;
        this.color = color;
        this.matricula = matricula;
    }

    public long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
}
