/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author JUAN DAVID
 */
public class Usuario {
    int identificacion_Pk;
    String nombre;
    String apellido;
    int edad;
    String contrasena;
    String correo;
    String Telefono;
    int rol_Fk;

    public int getIdentificacion_Pk() {
        return identificacion_Pk;
    }

    public void setIdentificacion_Pk(int identificacion_Pk) {
        this.identificacion_Pk = identificacion_Pk;
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
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getRol_Fk() {
        return rol_Fk;
    }

    public void setRol_Fk(int rol_Fk) {
        this.rol_Fk = rol_Fk;
    }
}
