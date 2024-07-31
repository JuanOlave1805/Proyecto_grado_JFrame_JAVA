/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import Objetos.Categoria;
import Objetos.Proveedor;
import Objetos.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author JUAN DAVID
 */
public class metodoProducto {
    
// Listar elementos de la base de datos
public String[][] listar() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de los productos
    String sql = "SELECT " +
    "p.id_producto_PK AS `id Producto`, " +
    "p.nombre AS `Nombre`, " +
    "p.precioCompra AS `Precio Compra`, " +
    "p.precioVenta AS `Precio Venta`, " +
    "p.stock AS `Cantidad disponibles`, " +
    "c.nombre AS `categoria_nombre`, " +
    "pr.nombre AS `proveedor_nombre` " +
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
            String[] datos = new String[7]; // Cambié a 7 porque hay 7 columnas en la consulta SQL
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString(1); // ID Producto
            datos[1] = res.getString(2); // Nombre del Producto
            datos[2] = res.getString(3); // Pecio Compra
            datos[3] = res.getString(4); // Precio Venta
            datos[4] = res.getString(5); // Cantidad Stock
            datos[5] = res.getString(6); // Nombre Categoria
            datos[6] = res.getString(7); // Nombre Proveedor

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


public boolean agregar(Producto producto, Categoria categoria, Proveedor proveedor) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();
    
    // Variables para almacenar los IDs
    int idCategoria = -1;
    int idProveedor = -1;

    // Consulta para obtener el ID de la categoría
    String sqlCategoria = "SELECT id_categoria_PK FROM categorias WHERE nombre = ?";
    // Consulta para obtener el ID del proveedor
    String sqlProveedor = "SELECT id_proveedor_PK FROM proveedores WHERE nombre = ?";
    
    try {
        // Preparar la consulta para obtener el ID de la categoría
        var psCategoria = conexion.prepareStatement(sqlCategoria);
        psCategoria.setString(1, categoria.getnombre()); // Nombre Categoria desde el objeto categoria
        var rsCategoria = psCategoria.executeQuery();
        if (rsCategoria.next()) {
            idCategoria = rsCategoria.getInt("id_categoria_PK");
        }

        // Preparar la consulta para obtener el ID del proveedor
        var psProveedor = conexion.prepareStatement(sqlProveedor);
        psProveedor.setString(1, proveedor.getNombre()); // Nombre Proveedor desde el objeto proveedor
        var rsProveedor = psProveedor.executeQuery();
        if (rsProveedor.next()) {
            idProveedor = rsProveedor.getInt("id_proveedor_PK");
        }

        // Consulta SQL para insertar datos en la tabla productos
        String sqlProducto = "INSERT INTO productos (id_producto_PK, nombre, precioCompra, precioVenta, stock, Categoria_FK, Proveedor_FK) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Crear un objeto PreparedStatement para ejecutar la consulta SQL
        var psProducto = conexion.prepareStatement(sqlProducto);

        // Establecer los valores para la inserción
        psProducto.setInt(1, producto.getId()); // ID Producto desde el objeto producto
        psProducto.setString(2, producto.getNombre()); // Nombre del Producto desde el objeto producto
        psProducto.setDouble(3, producto.getPrecio_compra()); // Precio Compra desde el objeto producto
        psProducto.setDouble(4, producto.getPrecio_venta()); // Precio Venta desde el objeto producto
        psProducto.setInt(5, producto.getStock()); // Cantidad Stock desde el objeto producto
        psProducto.setInt(6, idCategoria); // ID Categoria desde la consulta
        psProducto.setInt(7, idProveedor); // ID Proveedor desde la consulta

        // Ejecutar la inserción
        psProducto.executeUpdate();
        return true; // Inserción exitosa
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        return false; // Error en la inserción
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}

//Metodo de seleccionar la tabla
public void seleccionar(JTable tabla, JTextField textIdentificacion, JTextField textNombre, JTextField textCompra, JTextField textVenta, JTextField textCantidad ) {
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
            Object compraObj = tabla.getValueAt(fila, 2);
            if (compraObj != null) {
                textCompra.setText(compraObj.toString());
            }

            // Obtener y establecer el valor de la edad
            Object ventaObj = tabla.getValueAt(fila, 3);
            if (ventaObj != null) {
                textVenta.setText(ventaObj.toString());
            }

            // Obtener y establecer el valor del correo
            Object cantidadObj = tabla.getValueAt(fila, 4);
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

//Actualizar producto
public boolean actualizarProducto(Producto producto, Categoria categoria, Proveedor proveedor) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();
    
    // Variables para almacenar los IDs
    int idCategoria = -1;
    int idProveedor = -1;

    // Consulta para obtener el ID de la categoría
    String sqlCategoria = "SELECT id_categoria_PK FROM categorias WHERE nombre = ?";
    // Consulta para obtener el ID del proveedor
    String sqlProveedor = "SELECT id_proveedor_PK FROM proveedores WHERE nombre = ?";
    
    try {
        // Preparar la consulta para obtener el ID de la categoría
        var psCategoria = conexion.prepareStatement(sqlCategoria);
        psCategoria.setString(1, categoria.getnombre()); // Nombre Categoria desde el objeto categoria
        var rsCategoria = psCategoria.executeQuery();
        if (rsCategoria.next()) {
            idCategoria = rsCategoria.getInt("id_categoria_PK");
        }

        // Preparar la consulta para obtener el ID del proveedor
        var psProveedor = conexion.prepareStatement(sqlProveedor);
        psProveedor.setString(1, proveedor.getNombre()); // Nombre Proveedor desde el objeto proveedor
        var rsProveedor = psProveedor.executeQuery();
        if (rsProveedor.next()) {
            idProveedor = rsProveedor.getInt("id_proveedor_PK");
        }

        // Consulta SQL para actualizar datos en la tabla productos
        String sqlProducto = "UPDATE productos SET nombre = ?, precioCompra = ?, precioVenta = ?, stock = ?, Categoria_FK = ?, Proveedor_FK = ? " +
                             "WHERE id_producto_PK = ?";

        // Crear un objeto PreparedStatement para ejecutar la consulta SQL
        var psProducto = conexion.prepareStatement(sqlProducto);

        // Establecer los valores para la actualización
        psProducto.setString(1, producto.getNombre()); // Nombre del Producto desde el objeto producto
        psProducto.setFloat(2, producto.getPrecio_compra()); // Precio Compra desde el objeto producto
        psProducto.setFloat(3, producto.getPrecio_venta()); // Precio Venta desde el objeto producto
        psProducto.setInt(4, producto.getStock()); // Cantidad Stock desde el objeto producto
        psProducto.setInt(5, idCategoria); // ID Categoria desde la consulta
        psProducto.setInt(6, idProveedor); // ID Proveedor desde la consulta
        psProducto.setInt(7, producto.getId()); // ID Producto desde el objeto producto para la cláusula WHERE

        // Ejecutar la actualización
        psProducto.executeUpdate();
        return true; // Actualización exitosa
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        return false; // Error en la actualización
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}

public boolean eliminarProducto(Producto objeto) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    try {
        // Consulta SQL para eliminar un producto por su ID
        String sqlProducto = "DELETE FROM productos WHERE id_producto_PK = ?";

        // Crear un objeto PreparedStatement para ejecutar la consulta SQL
        var psProducto = conexion.prepareStatement(sqlProducto);

        // Establecer el ID del producto a eliminar
        psProducto.setInt(1, objeto.getId());

        // Ejecutar la eliminación
        int rowsAffected = psProducto.executeUpdate();
        
        // Verificar si se eliminó alguna fila
        if (rowsAffected > 0) {
            return true; // Eliminación exitosa
        } else {
            return false; // No se encontró el producto con el ID especificado
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        return false; // Error en la eliminación
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}


}
