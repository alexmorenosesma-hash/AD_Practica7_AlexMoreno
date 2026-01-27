package es.alexmoreno.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    @Column(name = "usuarioNombre",nullable = false,length = 20)
    private String usuarioNombre;
    @Column(name ="contraseña",nullable = false,length = 50)
    private String contrasena;
    @Column(name = "email",nullable = false,length = 100)
    private String email;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles",joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private Set<Rol> roles=new HashSet<>();
    @Column(name="activo",nullable = false)
    private boolean activo=true;
    
    @OneToOne(mappedBy = "usuario")
    private Transportista transportista;

    public Usuario() {
    }

    public Usuario(long idUsuario, String usuarioNombre, String contrasena, String email) {
        this.idUsuario = idUsuario;
        this.usuarioNombre = usuarioNombre;
        this.contrasena = contrasena;
        this.email = email;
    }

    public Usuario(String usuarioNombre, String contrasena, String email) {
        this.usuarioNombre = usuarioNombre;
        this.contrasena = contrasena;
        this.email = email;
    }

    public Usuario(long idUsuario, String usuarioNombre, String contrasena, String email, Set<Rol> roles, Transportista transportista) {
        this.idUsuario = idUsuario;
        this.usuarioNombre = usuarioNombre;
        this.contrasena = contrasena;
        this.email = email;
        this.roles = roles;
        this.transportista = transportista;
    }
    

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRol(Set<Rol> roles) {
        this.roles = roles;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    @Override
    public String toString() {
        return "IdUsuario:"+idUsuario
            +"\nNombre de Usuario:"+usuarioNombre
            +"\nContraseña:"+contrasena
            +"\nEmail:"+email
            +"\nRol:"+roles
            +"\nActivo:"+activo
            +"\nTransportistaId:"+transportista;
    }    
}
