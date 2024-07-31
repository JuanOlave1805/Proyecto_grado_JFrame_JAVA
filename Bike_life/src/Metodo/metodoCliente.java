/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import Objetos.Cliente;
import Objetos.Usuario;
import Vista.ClientesAdmin;
import Vista.UsuariosAdmin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author JUAN DAVID
 */
public class metodoCliente {
    
// Listar clientes de la base de datos
public String[][] obtenerClientesMatriz() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de usuarios y roles
    String sql = "SELECT cliente.identificacion_PK, cliente.nombre, cliente.apellido, cliente.telefono FROM clientes AS cliente ";

    Statement st;
    // Lista para almacenar los datos de los usuarios
    List<String[]> datosLista = new ArrayList<>();

    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Crear un array de Strings para almacenar los datos de cada usuario
            String[] datos = new String[4];
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString(1); // PK_identificacion
            datos[1] = res.getString(2); // nombre
            datos[2] = res.getString(3); // apellido
            datos[3] = res.getString(4); // telefono

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

    // Crear una matriz bidimensional de Strings para almacenar los datos de usuarios
    String[][] datosMatriz = new String[datosLista.size()][4];

    // Recorrer la lista de datos obtenida
    for (int i = 0; i < datosLista.size(); i++) {
        // Obtener cada array de datos de usuario de la lista y asignarlo a la matriz de datos
        datosMatriz[i] = datosLista.get(i);
    }

    // Devolver la matriz de datos que contiene la información de los usuarios
    return datosMatriz;
}

    //Metodo agregar Cliente
    public boolean agregarCliente(Cliente objeto, JFrame frameActual) {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        if (conexion == null) {
            // Mostrar mensaje de error si la conexión falla
            JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
            return false;
        }

        // Consultas SQL para verificar existencia e insertar cliente
        String sqlVerificar = "SELECT COUNT(*) FROM clientes WHERE identificacion_PK = ?";
        String sqlInsertar = "INSERT INTO clientes (identificacion_PK, nombre, apellido, telefono) VALUES (?, ?, ?, ?)";

        try {
            // Verificar si ya existe un cliente con la misma identificación
            var psVerificar = conexion.prepareStatement(sqlVerificar);
            psVerificar.setInt(1, objeto.getIdentificacion());
            ResultSet rs = psVerificar.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                // Mostrar mensaje si el cliente ya existe
                JOptionPane.showMessageDialog(frameActual, "Ya existe un cliente con la misma identificación.");
                return false;
            }

            // Preparar la declaración SQL para insertar un nuevo cliente
            var psInsertar = conexion.prepareStatement(sqlInsertar);
            psInsertar.setInt(1, objeto.getIdentificacion());
            psInsertar.setString(2, objeto.getNombre());
            psInsertar.setString(3, objeto.getApellido());
            psInsertar.setString(4, objeto.getTelefono());

            // Ejecutar la inserción
            int filasAfectadas = psInsertar.executeUpdate();

            if (filasAfectadas > 0) {
                // Mostrar mensaje de éxito si la inserción es exitosa
                JOptionPane.showMessageDialog(frameActual, "Cliente agregado satisfactoriamente.");
                ClientesAdmin vista = new ClientesAdmin();
                vista.setVisible(true);
                frameActual.dispose(); // Cerrar la ventana actual
            } else {
                // Mostrar mensaje de error si la inserción falla
                JOptionPane.showMessageDialog(frameActual, "No se pudo agregar el cliente.");
            }

            return filasAfectadas > 0;

        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
            JOptionPane.showMessageDialog(frameActual, "Error al agregar el cliente.");
            return false;
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
            cx.desconectar();
        }
    }

// Método seleccionar para obtener los datos de la casilla seleccionada en la tabla
public void seleccionUsuario(JTable tabla, JTextField textIdentificacion, JTextField textNombre, JTextField textApellido, JTextField textTelefono) {
    try {
        int fila = tabla.getSelectedRow(); // Obtener la fila seleccionada

        if (fila >= 0) {
            // Obtener y establecer el valor del identificador
            Object identificacionObj = tabla.getValueAt(fila, 0);
            if (identificacionObj != null) {
                textIdentificacion.setText(identificacionObj.toString());
            }

            // Obtener y establecer el valor del nombre
            Object nombreObj = tabla.getValueAt(fila, 1);
            if (nombreObj != null) {
                textNombre.setText(nombreObj.toString());
            }

            // Obtener y establecer el valor del apellido
            Object apellidoObj = tabla.getValueAt(fila, 2);
            if (apellidoObj != null) {
                textApellido.setText(apellidoObj.toString());
            }

            // Obtener y establecer el valor del teléfono
            Object telefonoObj = tabla.getValueAt(fila, 3);
            if (telefonoObj != null) {
                textTelefono.setText(telefonoObj.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
    }
}

    public boolean actualizarCliente(Cliente objeto, JFrame frameActual) {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        if (conexion == null) {
            // Mostrar mensaje de error si la conexión falla
            JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
            return false;
        }

        // Consulta SQL para actualizar cliente
        String sqlActualizar = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ? WHERE identificacion_PK = ?";

        try {
            // Preparar la declaración SQL para actualizar el cliente
            var psActualizar = conexion.prepareStatement(sqlActualizar);
            psActualizar.setString(1, objeto.getNombre());
            psActualizar.setString(2, objeto.getApellido());
            psActualizar.setString(3, objeto.getTelefono());
            psActualizar.setInt(4, objeto.getIdentificacion());

            // Ejecutar la actualización
            int filasAfectadas = psActualizar.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
            JOptionPane.showMessageDialog(frameActual, "Error al actualizar el cliente.");
            return false;
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
            cx.desconectar();
        }
    }

    public boolean eliminarCliente(int identificacion, JFrame frameActual) {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        if (conexion == null) {
            // Mostrar mensaje de error si la conexión falla
            JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
            return false;
        }

        // Consulta SQL para eliminar cliente
        String sqlEliminar = "DELETE FROM clientes WHERE identificacion_PK = ?";

        try {
            // Preparar la declaración SQL para eliminar el cliente
            var psEliminar = conexion.prepareStatement(sqlEliminar);
            psEliminar.setInt(1, identificacion);

            // Ejecutar la eliminación
            int filasAfectadas = psEliminar.executeUpdate();

            

            return filasAfectadas > 0;

        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
            JOptionPane.showMessageDialog(frameActual, "Error al eliminar el cliente.");
            return false;
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
            cx.desconectar();
        }
    }
}
