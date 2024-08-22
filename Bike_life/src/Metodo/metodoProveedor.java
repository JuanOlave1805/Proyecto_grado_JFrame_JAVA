/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import Objetos.Proveedor;
import VistaProveedor.ProveedorAdmin;
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
public class metodoProveedor {
    
// Listar elementos de la base de datos
public String[][] listar() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de los proveedores
    String sql = "SELECT pro.id_proveedor_PK, pro.nombre, pro.contacto_nombre, pro.telefono, pro.email FROM proveedores AS pro;";

    Statement st;
    // Lista para almacenar los datos de los proveedores
    List<String[]> datosLista = new ArrayList<>();

    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Crear un array de Strings para almacenar los datos de cada proveedor
            String[] datos = new String[5];
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString(1); // Identificador
            datos[1] = res.getString(2); // Nombre del Proveedor
            datos[2] = res.getString(3); // Nombre del Encargado
            datos[3] = res.getString(4); // Teléfono
            datos[4] = res.getString(5); // Correo

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

    // Crear una matriz bidimensional de Strings para almacenar los datos de los proveedores
    String[][] datosMatriz = new String[datosLista.size()][5];

    // Recorrer la lista de datos obtenida
    for (int i = 0; i < datosLista.size(); i++) {
        // Obtener cada array de datos de proveedor de la lista y asignarlo a la matriz de datos
        datosMatriz[i] = datosLista.get(i);
    }

    // Devolver la matriz de datos que contiene la información de los proveedores
    return datosMatriz;
}

// Método seleccionar para obtener los datos de la casilla seleccionada en la tabla
public void seleccionar(JTable tabla, JTextField textIdentificacion, JTextField textNombreProveedor, JTextField textNombreEncargado, JTextField textTelefono, JTextField textCorreo) {
    try {
        // Obtener la fila seleccionada en la tabla
        int fila = tabla.getSelectedRow();

        // Verificar que una fila haya sido seleccionada
        if (fila >= 0) {
            // Obtener y establecer el valor del identificador
            Object identificacionObj = tabla.getValueAt(fila, 0);
            if (identificacionObj != null) {
                textIdentificacion.setText(identificacionObj.toString());
            }

            // Obtener y establecer el valor del nombre
            Object nombreObj = tabla.getValueAt(fila, 1);
            if (nombreObj != null) {
                textNombreProveedor.setText(nombreObj.toString());
            }

            // Obtener y establecer el valor del nombre del encargado
            Object apellidoObj = tabla.getValueAt(fila, 2);
            if (apellidoObj != null) {
                textNombreEncargado.setText(apellidoObj.toString());
            }

            // Obtener y establecer el valor del teléfono
            Object telefonoObj = tabla.getValueAt(fila, 3);
            if (telefonoObj != null) {
                textTelefono.setText(telefonoObj.toString());
            }

            // Obtener y establecer el valor del correo
            Object correoObj = tabla.getValueAt(fila, 4);
            if (correoObj != null) {
                textCorreo.setText(correoObj.toString());
            }
        } else {
            // Mostrar un mensaje si no se seleccionó ninguna fila
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        // Mostrar un mensaje si ocurre un error durante la selección
        JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
    }
}

// Eliminar proveedor de la base de datos
public boolean eliminar(Proveedor objeto) {
    // Obtener la identificación del objeto Proveedor
    int identificacion = objeto.getIdentificador_PK();

    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consultas SQL
    String sqlActualizarProductos = "UPDATE productos SET Proveedor_FK = NULL WHERE Proveedor_FK = ?";
    String sqlEliminarProveedor = "DELETE FROM proveedores WHERE id_proveedor_PK = ?";

    try {
        // Desactivar el modo de autocommit para manejar las transacciones manualmente
        conexion.setAutoCommit(false);

        // Actualizar las referencias en productos
        try (var psActualizar = conexion.prepareStatement(sqlActualizarProductos)) {
            psActualizar.setInt(1, identificacion);
            psActualizar.executeUpdate();
        }

        // Eliminar el proveedor
        try (var psEliminar = conexion.prepareStatement(sqlEliminarProveedor)) {
            psEliminar.setInt(1, identificacion);
            int filasAfectadas = psEliminar.executeUpdate();

            // Verificar si se eliminó correctamente (al menos una fila afectada)
            if (filasAfectadas > 0) {
                // Confirmar la transacción
                conexion.commit();
                return true; // Eliminación exitosa
            } else {
                // Revertir la transacción si no se eliminó el proveedor
                conexion.rollback();
                return false; // No se pudo eliminar
            }
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        try {
            // Revertir la transacción en caso de error
            if (conexion != null) {
                conexion.rollback();
            }
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        return false; // Indicar que no se pudo eliminar
    } finally {
        try {
            // Asegurarse de desconectar la conexión a la base de datos
            if (conexion != null) {
                conexion.setAutoCommit(true); // Restaurar el modo de autocommit
                cx.desconectar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Agregar proveedor en la base de datos
public boolean agregarProveedor(Proveedor objeto, JFrame frameActual) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    if (conexion == null) {
        // Mostrar mensaje de error si la conexión falla
        JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
        return false;
    }

    // Consulta SQL para insertar proveedor
    String sqlInsertar = "INSERT INTO proveedores (nombre, contacto_nombre, telefono, email) VALUES (?, ?, ?, ?)";

    try {
        // Preparar la declaración SQL para insertar un nuevo usuario
        var psInsertar = conexion.prepareStatement(sqlInsertar);
        psInsertar.setString(1, objeto.getNombre());
        psInsertar.setString(2, objeto.getNombreContacto());
        psInsertar.setString(3, objeto.getTelefono());
        psInsertar.setString(4, objeto.getEmail());
        // Ejecutar la inserción
        int filasAfectadas = psInsertar.executeUpdate();

        if (filasAfectadas > 0) {
            // Mostrar mensaje de éxito si la inserción es exitosa
            JOptionPane.showMessageDialog(frameActual, "Proveedor agregado satisfactoriamente");
            ProveedorAdmin ventana = new ProveedorAdmin();
            ventana.setVisible(true);
            this.setVisible(false);
        } else {
            // Mostrar mensaje de error si la inserción falla
            JOptionPane.showMessageDialog(frameActual, "No se pudo agregar al proveedor.");
        }

        return filasAfectadas > 0;

    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        JOptionPane.showMessageDialog(frameActual, "Error al agregar al proveedor.");
        return false;
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}

// Actualizar proveedor en la base de datos
public boolean actualizarProveedor(Proveedor objeto) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para modificar un usuario por su identificación primaria
    String sql = "UPDATE proveedores SET nombre = ?, contacto_nombre = ?, telefono = ?, email = ? WHERE id_proveedor_PK = ?";

    try {
        // Preparar la declaración SQL con parámetros
        var ps = conexion.prepareStatement(sql);
        ps.setString(1, objeto.getNombre());
        ps.setString(2, objeto.getNombreContacto());
        ps.setString(3, objeto.getTelefono());
        ps.setString(4, objeto.getEmail());
        ps.setInt(5, objeto.getIdentificador_PK());

        // Ejecutar la actualización (modificar usuario)
        int filasAfectadas = ps.executeUpdate();

        // Verificar si se modificó correctamente (al menos una fila afectada)
        return filasAfectadas > 0;

    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        return false; // Indicar que no se pudo modificar
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        if (conexion != null) {
            cx.desconectar();
        }
    }
}

public List<String> obtenerNombresProveedores() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los nombres de los proveedores
    String sql = "SELECT nombre FROM proveedores;";

    Statement st;
    // Lista para almacenar los nombres de los proveedores
    List<String> nombresLista = new ArrayList<>();

    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Obtener el nombre del proveedor y agregarlo a la lista
            String nombre = res.getString(1); // La columna con el nombre del proveedor
            nombresLista.add(nombre);
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }

    // Devolver la lista de nombres de proveedores
    return nombresLista;
}

    private void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
