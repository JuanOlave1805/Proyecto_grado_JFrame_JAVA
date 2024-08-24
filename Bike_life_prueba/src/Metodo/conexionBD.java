package Metodo;

import java.sql.Connection; // Importa la clase Connection del paquete java.sql
import java.sql.DriverManager; // Importa la clase DriverManager del paquete java.sql
import java.sql.SQLException; // Importa la clase SQLException del paquete java.sql

/**
 * Clase que gestiona la conexión y desconexión a una base de datos MySQL.
 * author JUAN DAVID
 */
public class conexionBD {
    String bd = "db_bike_life"; // Nombre de la base de datos
    String url = "jdbc:mysql://localhost:3306/"; // URL de conexión a MySQL
    String user = "root"; // Usuario de la base de datos
    String password = ""; // Contraseña del usuario de la base de datos
    String driver = "com.mysql.cj.jdbc.Driver"; // Driver JDBC para MySQL
    Connection cx; // Objeto de tipo Connection para la conexión


    // Método para establecer la conexión a la base de datos
    public Connection conectar() {
        try {
            Class.forName(driver); // Carga dinámica del driver JDBC
            cx = DriverManager.getConnection(url + bd, user, password); // Establece la conexión a la base de datos
            System.out.println("Conexión exitosa a la base de datos " + bd); // Mensaje de conexión exitosa
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se pudo conectar a la base de datos " + bd); // Mensaje de error si no se puede conectar
            ex.printStackTrace(); // Imprime el stack trace de la excepción
        }
        return cx; // Devuelve el objeto Connection (puede ser null si la conexión falló)
    }
    
    // Método para desconectar la conexión a la base de datos
    public void desconectar() {
        try {
            cx.close(); // Cierra la conexión a la base de datos
            System.out.println("Conexión a la base de datos " + bd + " desconectada."); // Mensaje de desconexión exitosa
        } catch (SQLException ex) {
            System.out.println("Error al desconectar la base de datos " + bd); // Mensaje de error si no se puede desconectar
            ex.printStackTrace(); // Imprime el stack trace de la excepción
        }
    }
}

//Link de video de donde se hizo la conexion a base de datos https://www.youtube.com/watch?v=GCZmOfhyciY&t=258s