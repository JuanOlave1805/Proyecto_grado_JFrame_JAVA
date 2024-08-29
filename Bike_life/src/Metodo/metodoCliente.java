/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import Objetos.Usuario;
import VistaVenta.VentaAdmin;
import VistaVenta.VentaVendedor;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Juan
 */
public class metodoCliente {
private conexionBD cx;

    public boolean validarCliente(int cliente) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Crear una instancia de conexión a la base de datos
            cx = new conexionBD();
            // Establecer una conexión con la base de datos
            conexion = cx.conectar();

            // Verificar si la conexión fue exitosa
            if (conexion == null) {
                throw new SQLException("No se pudo establecer una conexión con la base de datos.");
            }

            // Consulta SQL para validar la existencia del cliente
            String sql = "SELECT c.documento FROM clientes AS c WHERE c.documento = ?";

            // Preparar la declaración SQL con parámetros
            ps = conexion.prepareStatement(sql);
            // Establecer el identificador del cliente en el primer parámetro
            ps.setInt(1, cliente);

            // Ejecutar la consulta y obtener el conjunto de resultados
            rs = ps.executeQuery();

            // Verificar si se obtuvo un resultado válido
            return rs.next(); // Retorna true si hay al menos una fila en el ResultSet

        } catch (SQLException ex) {
            // Manejar excepciones SQL, imprimir el rastreo de la pila y mostrar un mensaje de error
            System.out.println("Error al intentar validar las credenciales.");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al intentar validar las credenciales.");
            return false; // En caso de error, retornamos false
        } finally {
            // Asegurarse de cerrar todos los recursos abiertos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar los recursos.");
                e.printStackTrace();
            }
            // Asegurarse de desconectar la conexión a la base de datos
            if (cx != null) {
                cx.desconectar();
            }
        }
    }
    
    // Agregar un cliente a la base de datos
    public boolean agregarCliente(Usuario objeto) {
        conexionBD cx = new conexionBD();
        Connection conexion = cx.conectar();

        if (conexion == null) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.");
            return false;
        }

        String sqlVerificar = "SELECT COUNT(*) FROM clientes WHERE documento = ?";
        String sqlInsertar = "INSERT INTO clientes (documento, nombre, apellido, telefono) VALUES (?, ?, ?, ?)";

        try {
            // Verificar si ya existe un cliente con el mismo documento
            var psVerificar = conexion.prepareStatement(sqlVerificar);
            psVerificar.setInt(1, objeto.getIdentificacion_Pk());
            ResultSet rs = psVerificar.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Ya existe un cliente con el mismo documento.");
                return false;
            }

            // Insertar el nuevo cliente
            var psInsertar = conexion.prepareStatement(sqlInsertar);
            psInsertar.setInt(1, objeto.getIdentificacion_Pk());
            psInsertar.setString(2, objeto.getNombre());
            psInsertar.setString(3, objeto.getApellido());
            psInsertar.setString(4, objeto.getTelefono());


            int filasAfectadas = psInsertar.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente agregado satisfactoriamente");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar el cliente.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente.");
            return false;
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Listar los clientes de la base de datos
    public String[][] obtenerClientes() {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        // Consulta SQL para obtener los datos de los clientes
        String sql = "SELECT documento, nombre, apellido, telefono FROM clientes";

        // Lista para almacenar los datos de los clientes
        List<String[]> datosLista = new ArrayList<>();

        try {
            // Crear un objeto Statement para ejecutar la consulta SQL
            var st = conexion.createStatement();
            // Ejecutar la consulta y obtener el conjunto de resultados
            ResultSet res = st.executeQuery(sql);

            // Recorrer el conjunto de resultados
            while (res.next()) {
                // Crear un array de Strings para almacenar los datos de cada cliente
                String[] datos = new String[4];
                // Obtener y almacenar cada campo de datos en el array
                datos[0] = res.getString("documento"); // documento
                datos[1] = res.getString("nombre");   // nombre
                datos[2] = res.getString("apellido"); // apellido
                datos[3] = res.getString("telefono"); // telefono


                // Agregar el array de datos a la lista
                datosLista.add(datos);
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
            cx.desconectar();
        }

        // Crear una matriz bidimensional de Strings para almacenar los datos de clientes
        String[][] datosMatriz = new String[datosLista.size()][4];

        // Recorrer la lista de datos obtenida y asignarla a la matriz
        for (int i = 0; i < datosLista.size(); i++) {
            datosMatriz[i] = datosLista.get(i);
        }

        // Devolver la matriz de datos que contiene la información de los clientes
        return datosMatriz;
    }
}
