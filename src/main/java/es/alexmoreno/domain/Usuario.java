package es.alexmoreno.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;
    
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
    
}
