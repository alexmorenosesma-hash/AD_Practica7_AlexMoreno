/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.alexmoreno.domain;

/**
 *
 * @author alexm
 */
public class Usuario {
    long idUsuario;
    String usuarioNombre;
    String contrasena;
    String email;

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
