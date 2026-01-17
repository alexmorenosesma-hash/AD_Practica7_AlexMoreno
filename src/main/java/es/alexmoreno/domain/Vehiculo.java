package es.alexmoreno.domain;


public class Vehiculo {
    long idVehiculo;
    String modelo;
    double peso;
    String color;
    String matricula;

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
