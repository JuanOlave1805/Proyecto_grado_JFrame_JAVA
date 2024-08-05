/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import Objetos.Categoria;
import VistaCategoria.CategoriaProductosAdmin;
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
public class metodoCategoria {
    
    
// Listar las categorías de la base de datos
public String[][] obtenerCategorias() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de las categorías
    String sql = "SELECT id_categoria_PK, nombre, Rin FROM categorias";
    
    // Lista para almacenar los datos de las categorías
    List<String[]> datosLista = new ArrayList<>();
    
    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        Statement st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Crear un array de Strings para almacenar los datos de cada categoría
            String[] datos = new String[3];
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString(1); // id_categoria_PK
            datos[1] = res.getString(2); // nombre
            datos[2] = res.getString(3); // Rin

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

    // Crear una matriz bidimensional de Strings para almacenar los datos de categorías
    String[][] datosMatriz = new String[datosLista.size()][3];
    
    // Recorrer la lista de datos obtenida y asignarla a la matriz
    for (int i = 0; i < datosLista.size(); i++) {
        datosMatriz[i] = datosLista.get(i);
    }

    // Devolver la matriz de datos que contiene la información de las categorías
    return datosMatriz;
}

// Agregar una categoría
public boolean agregarCategoria(Categoria objeto, JFrame frameActual) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Verificar si la conexión a la base de datos es nula
    if (conexion == null) {
        // Mostrar mensaje de error si la conexión falla
        JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
        return false;
    }

    // Consulta SQL para insertar una nueva categoría
    String sql = "INSERT INTO categorias (nombre, Rin) VALUES (?, ?)";

    try {
        // Preparar la declaración SQL para insertar una nueva categoría
        var ps = conexion.prepareStatement(sql);
        ps.setString(1, objeto.getnombre());
        ps.setInt(2, objeto.getrin());
        
        // Ejecutar la inserción
        int filasAfectadas = ps.executeUpdate();

        // Verificar si la inserción fue exitosa
        if (filasAfectadas > 0) {
            // Mostrar mensaje de éxito si la inserción es exitosa
            JOptionPane.showMessageDialog(frameActual, "Categoría agregada satisfactoriamente");
            CategoriaProductosAdmin ventana = new CategoriaProductosAdmin();
            ventana.setVisible(true);
            frameActual.dispose(); // Cerrar la ventana actual
        } else {
            // Mostrar mensaje de error si la inserción falla
            JOptionPane.showMessageDialog(frameActual, "No se pudo agregar la categoría.");
        }

        // Devolver true si la inserción fue exitosa
        return filasAfectadas > 0;

    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        // Mostrar mensaje de error si hay una excepción
        JOptionPane.showMessageDialog(frameActual, "Error al agregar la categoría.");
        return false;
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}

// Método seleccionar para obtener los datos de la casilla seleccionada en la tabla categoría
public void seleccionCategoria(JTable tabla_categoria, JTextField textNombre, JTextField textRin, JTextField textid) {
    try {
        // Obtener la fila seleccionada de la tabla
        int fila = tabla_categoria.getSelectedRow();

        // Verificar si una fila está seleccionada
        if (fila >= 0) {
            // Obtener y establecer el valor del identificador
            Object idObj = tabla_categoria.getValueAt(fila, 0);
            if (idObj != null) {
                textid.setText(idObj.toString());
            }
            
            // Obtener y establecer el valor del nombre
            Object nombreObj = tabla_categoria.getValueAt(fila, 1);
            if (nombreObj != null) {
                textNombre.setText(nombreObj.toString());
            }
        
            // Obtener y establecer el valor del Rin
            Object rinObj = tabla_categoria.getValueAt(fila, 2);
            if (rinObj != null) {
                textRin.setText(rinObj.toString());
            }
        
        } else {
            // Mostrar mensaje de error si no hay fila seleccionada
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        // Manejar cualquier excepción mostrando un mensaje de error
        JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
    }
}

// Eliminar una categoría
public boolean eliminarCategoria(Categoria objeto) {
    // Obtener la identificación del objeto Categoria
    int identificacion = objeto.getIdentificador_Pk();

    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consultas SQL
    String sqlActualizarProductos = "UPDATE productos SET Categoria_FK = NULL WHERE Categoria_FK = ?";
    String sqlEliminarCategoria = "DELETE FROM categorias WHERE id_categoria_PK = ?";
    
    try {
        // Desactivar el modo de autocommit para manejar las transacciones manualmente
        conexion.setAutoCommit(false);

        // Actualizar las referencias en productos
        try (var psActualizar = conexion.prepareStatement(sqlActualizarProductos)) {
            psActualizar.setInt(1, identificacion);
            psActualizar.executeUpdate();
        }

        // Eliminar la categoría
        try (var psEliminar = conexion.prepareStatement(sqlEliminarCategoria)) {
            psEliminar.setInt(1, identificacion);
            int filasAfectadas = psEliminar.executeUpdate();

            // Verificar si se eliminó correctamente (al menos una fila afectada)
            if (filasAfectadas > 0) {
                // Confirmar la transacción
                conexion.commit();
                return true; // Eliminación exitosa
            } else {
                // Revertir la transacción si no se eliminó la categoría
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

// Actualizar una categoría
public boolean actualizarCategoria(Categoria objeto) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para modificar una categoría por su identificación primaria
    String sql = "UPDATE categorias SET nombre = ?, Rin = ? WHERE id_categoria_PK = ?";

    try {
        // Preparar la declaración SQL con parámetros
        var ps = conexion.prepareStatement(sql);
        ps.setString(1, objeto.getnombre());
        ps.setInt(2, objeto.getrin());
        ps.setInt(3, objeto.getIdentificador_Pk());

        // Ejecutar la actualización (modificar categoría)
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

//Metodo de listar el combo box de los 
public List<String> listarComboProveedores() {
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

public List<String> obtenerNombresCategorias() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los nombres de las categorias
    String sql = "SELECT nombre FROM categorias;";

    Statement st;
    // Lista para almacenar los nombres de las categorias
    List<String> nombresLista = new ArrayList<>();

    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Obtener el nombre del proveedor y agregarlo a la lista
            String nombre = res.getString(1); // La columna con el nombre de la categoria
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

}


