package es.alexmoreno.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "Transportista")
public class Transportista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTransportista;
    @Column(name = "nombre",nullable = false,length = 50)
    private String nombre;
    @Column(name = "apellido",nullable = false,length = 50)
    private String apellido;
    @Column(name = "dni",nullable = false,length = 9)
    private String dni;
    //Un transportista tiene un vehiculo y un vehiculo pertenece a un transportista (supongo)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idVehiculo")
    private Vehiculo vehiculo;
    //Un transportista tiene varias rutas y una ruta puede pertenecer a varios transportista 
    //(entiendo que la idea es que hagamos lo de la tabla extra )
    @OneToMany(mappedBy = "transportista",cascade = CascadeType.ALL)
    private List<Transportista_Ruta> transportistaRutas=new ArrayList<>();
    //Un transportista tiene un usuario, y un usuario pertenece a un transportista
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    Usuario usuario;
    
    public Transportista() {
    }

    public Transportista(long idTransportista, String nombre, String apellido, String dni) {
        this.idTransportista = idTransportista;
        this.nombre = nombre;   
        this.apellido = apellido;
        this.dni = dni;
    }

    public Transportista(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Transportista(long idTransportista, String nombre, String apellido, String dni, Vehiculo vehiculo, Usuario usuario) {
        this.idTransportista = idTransportista;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
    }
    

    public long getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(long idTransportista) {
        this.idTransportista = idTransportista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public List<Transportista_Ruta> getTransportistaRutas() {
        return transportistaRutas;
    }

    public void setTransportistaRutas(List<Transportista_Ruta> transportistaRutas) {
        this.transportistaRutas = transportistaRutas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString(){
        return "Id:"+idTransportista
                +"\nNombre:"+nombre
                +"\nApellido:"+apellido
                +"\nDNI:"+dni
                +"\nVehiculo:"+vehiculo
                +"\nRuta:"+transportistaRutas
                +"\nUsuario:"+usuario;
    }
    
    
}
