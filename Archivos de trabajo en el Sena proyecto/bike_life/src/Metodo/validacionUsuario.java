/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase para validar el login del usuario.
 * 
 */
public class validacionUsuario {
    conexionBD cx;
    
    public void metodo_validacion(String usuario, String contrasena) {
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa tu usuario y contraseña.");
            return; // Salir del método si falta usuario o contraseña
        }
        
        try {
            // Instanciar la conexión a la base de datos
            cx = new conexionBD();
            Connection conexion = cx.conectar();
            
            if (conexion == null) {
                throw new SQLException("No se pudo establecer una conexión con la base de datos.");
            }
            
            // Consulta SQL parametrizada
            String query = "SELECT * FROM tb_usuarios WHERE PK_identificacion = ? AND contrasena = ?";
            
            // Crear PreparedStatement para la consulta parametrizada
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            // Ejecutar la consulta y obtener el ResultSet
            ResultSet rs = ps.executeQuery();

            // Verificar si se encontraron resultados
            if (rs.next()) {
                // Si hay resultados, las credenciales son válidas
                System.out.println("Credenciales válidas");
                JOptionPane.showMessageDialog(null, "Credenciales válidas");
            } else {
                // Si no hay resultados, las credenciales no son válidas
                System.out.println("Credenciales inválidas");
                JOptionPane.showMessageDialog(null, "Credenciales inválidas");
            }

            // Cerrar recursos
            rs.close();
            ps.close();
            conexion.close(); // Cerrar la conexión después de usarla

        } catch (SQLException ex) {
            // Manejar excepciones SQL
            System.out.println("Error al intentar validar las credenciales.");
            ex.printStackTrace(); // Imprimir el stack trace para depuración
            
            JOptionPane.showMessageDialog(null, "Error al intentar validar las credenciales.");
        } finally {
            // Asegurarse de desconectar la conexión después de usarla
            if (cx != null) {
                cx.desconectar();
            }
        }
    }
}