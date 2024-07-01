/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Esta clase realiza operaciones de consulta relacionadas con usuarios en la base de datos.
 */
public class consultasUsuarios {

    /**
     * Método para registrar un nuevo usuario en la base de datos.
     * @param user El objeto usuario que contiene los datos a insertar.
     * @return true si el registro fue exitoso, false si hubo algún error.
     */
    public boolean registrar(usuario user) {
        PreparedStatement ps = null;

        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        java.sql.Connection conexion = cx.conectar();

        // Consulta SQL para insertar un nuevo usuario
        String sql = "INSERT INTO tb_usuarios(PK_identificacion, contrasena, nombre, FK_rol) VALUES(?,?,?,?)";
        try {
            ps = conexion.prepareStatement(sql);
            // Establecer los parámetros de la consulta
            ps.setInt(1, user.getId()); // Se asume que getId() devuelve un entero para la PK_identificacion
            ps.setString(3, user.getContrasena()); // Se asume que getContrasena() devuelve la contraseña del usuario
            ps.setString(2, user.getNombre()); // Se asume que getNombre() devuelve el nombre del usuario
            ps.setInt(4, user.getRol()); // Se asume que getRol() devuelve el rol del usuario

            // Ejecutar la consulta
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
            return false;
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos
            cx.desconectar();
        }
    }
    
    public boolean modificar(usuario user) {
        PreparedStatement ps = null;

        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        java.sql.Connection conexion = cx.conectar();

        // Consulta SQL para insertar un nuevo usuario
        String sql = "UPDATE tb_usuarios SET PK_identificacion=?, contrasena=?, nombre=?, FK_rol=? WHERE PK_identificacion=? ";
        try {
            ps = conexion.prepareStatement(sql);
            // Establecer los parámetros de la consulta
            ps.setInt(1, user.getId()); // Se asume que getId() devuelve un entero para la PK_identificacion
            ps.setString(3, user.getContrasena()); // Se asume que getContrasena() devuelve la contraseña del usuario
            ps.setString(2, user.getNombre()); // Se asume que getNombre() devuelve el nombre del usuario
            ps.setInt(4, user.getRol()); // Se asume que getRol() devuelve el rol del usuario

            
            // Ejecutar la consulta
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
            return false;
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos
            cx.desconectar();
        }
    }
    
    public boolean eliminar(usuario user) {
        PreparedStatement ps = null;

        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        java.sql.Connection conexion = cx.conectar();

        // Consulta SQL para insertar un nuevo usuario
        String sql = "DELETE FROM tb_usuarios WHERE PK_identificacion=? ";
        try {
            ps = conexion.prepareStatement(sql);
            // Establecer los parámetros de la consulta
            ps.setInt(1, user.getId()); // Se asume que getId() devuelve un entero para la PK_identificacion
            
            // Ejecutar la consulta
            ps.execute();
            return true;
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
            return false;
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos
            cx.desconectar();
        }
    }
    
    public boolean buscar(usuario user) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        java.sql.Connection conexion = cx.conectar();

        // Consulta SQL para insertar un nuevo usuario
        String sql = "SELECT * FROM tb_usuarios WHERE PK_identificacion=? ";
        try {
            ps = conexion.prepareStatement(sql);
            // Establecer los parámetros de la consulta
            ps.setInt(1, user.getId()); // Se asume que getId() devuelve un entero para la PK_identificacion
            
            // Ejecutar la consulta
            rs = ps.executeQuery();
            
            if (rs.next()) {
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setNombre(rs.getString("nombre"));
                user.setContrasena(rs.getString("contrasena"));
                user.setRol(Integer.parseInt(rs.getString("rol")));
                return true;
            }
            return false;
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
            return false;
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos
            cx.desconectar();
        }
    }
    
    //https://www.youtube.com/watch?v=0w-2Ik-VPwU
}

