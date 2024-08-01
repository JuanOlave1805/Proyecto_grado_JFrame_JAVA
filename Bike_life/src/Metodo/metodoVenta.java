/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Propietario
 */
public class metodoVenta {
    //Metodo de seleccionar la tabla
public void seleccionar(JTable tabla, JTextField textIdentificacion, JTextField textNombre, JTextField textVenta, JTextField textCantidad ) {
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

            // Obtener y establecer el valor de la edad
            Object ventaObj = tabla.getValueAt(fila, 2);
            if (ventaObj != null) {
                textVenta.setText(ventaObj.toString());
            }

            // Obtener y establecer el valor del correo
            Object cantidadObj = tabla.getValueAt(fila, 3);
            if (cantidadObj != null) {
                textCantidad.setText(cantidadObj.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
    }
}

public String[][] listar() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de los productos
    String sql = "SELECT " +
    "p.id_producto_PK AS `id`, " +
    "p.nombre AS `nombre`, " +
    "p.precioVenta AS `precio`, " +
    "p.stock AS `cantidad`, " +
    "c.nombre AS `categoria`, " +
    "pr.nombre AS `proveedor` " +
    "FROM " +
    "productos p " +
    "LEFT JOIN " +
    "categorias c ON p.Categoria_FK = c.id_categoria_PK " +
    "LEFT JOIN " +
    "proveedores pr ON p.Proveedor_FK = pr.id_proveedor_PK;";


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
            String[] datos = new String[7]; 
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString(1); // ID Producto
            datos[1] = res.getString(2); // Nombre del Producto
            datos[2] = res.getString(3); // Pecio Compra
            datos[3] = res.getString(4); // Cantidad Stock
            datos[4] = res.getString(5); // Nombre Categoria
            datos[5] = res.getString(6); // Nombre Proveedor

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
    String[][] datosMatriz = new String[datosLista.size()][5]; // Cambié a 5 porque hay 5 columnas en la consulta SQL

    // Recorrer la lista de datos obtenida
    for (int i = 0; i < datosLista.size(); i++) {
        // Obtener cada array de datos de proveedor de la lista y asignarlo a la matriz de datos
        datosMatriz[i] = datosLista.get(i);
    }

    // Devolver la matriz de datos que contiene la información de los proveedores
    return datosMatriz;
}

private void busqueda(String query, JTable tablaProducto) {
    // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        try {
            String sql = "SELECT " +
                    "p.id_producto_PK AS id, " +
                    "p.nombre AS nombre, " +
                    "p.precioVenta AS precio, " +
                    "p.stock AS cantidad, " +
                    "c.nombre AS categoria, " +
                    "pr.nombre AS proveedor " +
                    "FROM " +
                    "productos p " +
                    "LEFT JOIN " +
                    "categorias c ON p.Categoria_FK = c.id_categoria_PK " +
                    "LEFT JOIN " +
                    "proveedores pr ON p.Proveedor_FK = pr.id_proveedor_PK " +
                    "WHERE " +
                    "p.nombre LIKE ? OR " +
                    "c.nombre LIKE ? OR " +
                    "pr.nombre LIKE ?";

            var preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            preparedStatement.setString(3, "%" + query + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel tableModel = (DefaultTableModel) tablaProducto.getModel();
            tableModel.setRowCount(0); // Limpiar filas existentes

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio"),
                        resultSet.getInt("cantidad"),
                        resultSet.getString("categoria"),
                        resultSet.getString("proveedor")
                };
                tableModel.addRow(row);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cx.desconectar();
        }
}
}
