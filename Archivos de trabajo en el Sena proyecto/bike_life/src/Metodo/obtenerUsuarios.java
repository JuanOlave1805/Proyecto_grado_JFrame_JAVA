/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class obtenerUsuarios {

    // Método público para obtener la lista de usuarios desde la base de datos
    public List<String[]> obtener_usuarios() {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        java.sql.Connection conexion = cx.conectar();

        // Consulta SQL para obtener los datos de usuarios y roles
        String sql = "SELECT users.PK_identificacion, users.nombre, users.contrasena, rol.nombre_rol\n" +
                     "FROM tb_usuarios AS users\n" +
                     "INNER JOIN tb_rol AS rol ON users.FK_rol = rol.PK_rol;";

        Statement st;
        // Lista para almacenar los datos de los usuarios
        List<String[]> datosLista = new ArrayList<>();

        try {
            // Crear un objeto Statement para ejecutar la consulta SQL
            st = conexion.createStatement();
            // Ejecutar la consulta y obtener el conjunto de resultados
            ResultSet res = st.executeQuery(sql);

            // Recorrer el conjunto de resultados
            while(res.next()) {
                // Crear un array de Strings para almacenar los datos de cada usuario
                String[] datos = new String[4];
                // Obtener y almacenar cada campo de datos en el array
                datos[0] = res.getString(1); // PK_identificacion
                datos[1] = res.getString(2); // nombre
                datos[2] = res.getString(3); // contrasena
                datos[3] = res.getString(4); // nombre_rol

                // Agregar el array de datos a la lista
                datosLista.add(datos);
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
        }

        // Desconectar la conexión a la base de datos
        cx.desconectar();
        
        // Devolver la lista de arrays de datos de usuarios
        return datosLista;
    }
}