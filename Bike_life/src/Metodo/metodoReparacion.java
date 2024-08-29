/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Propietario
 */
public class metodoReparacion {
    //Metodo de seleccionar la tabla
public void seleccionar(JTable tabla, JTextField textIdProducto, JTextField textNombre) {
    try {
        int fila = tabla.getSelectedRow(); // Obtener la fila seleccionada

        if (fila >= 0) { // Verificar que se ha seleccionado una fila válida
            int columnaIdentificacion = 0; // Índice de columna para identificación
            int columnaNombre = 1; // Índice de columna para nombre

            Object identificacionObj = tabla.getValueAt(fila, columnaIdentificacion);
            if (identificacionObj != null) {
                textIdProducto.setText(identificacionObj.toString());
            } else {
                textIdProducto.setText(""); // Limpiar el campo si no hay valor
            }

            Object nombreObj = tabla.getValueAt(fila, columnaNombre);
            if (nombreObj != null) {
                textNombre.setText(nombreObj.toString());
            } else {
                textNombre.setText(""); // Limpiar el campo si no hay valor
            }

        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada. Índice de fila: " + fila);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
    }
}




public void busqueda(String query, JTable tablaProducto) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

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
        "c.nombre LIKE ?";

    try (var preparedStatement = conexion.prepareStatement(sql)) {
        preparedStatement.setString(1, "%" + query + "%");

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
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

            tablaProducto.setModel(tableModel);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cx.desconectar();
    }
}





public void agregarProductoPedido(JTable tabla, Producto objeto, int cantidad) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de los productos según el ID del producto
    String sql = "SELECT " +
        "p.id_producto_PK AS `id Producto`, " +
        "p.nombre AS `Nombre`, " +
        "c.nombre AS `categoria_nombre`, " +
        "p.precioVenta AS `Precio Venta`, " +
        "p.stock AS `Cantidad Disponible` " +
        "FROM " +
        "productos p " +
        "LEFT JOIN " +
        "categorias c ON p.Categoria_FK = c.id_categoria_PK " +
        "LEFT JOIN " +
        "proveedores pr ON p.Proveedor_FK = pr.id_proveedor_PK " +
        "WHERE p.id_producto_PK = ?";

    // Lista para almacenar los datos de los productos
    List<String[]> datosLista = new ArrayList<>();

    try (var st = conexion.prepareStatement(sql)) {
        // Asignar el valor del ID del producto al parámetro de la consulta
        st.setInt(1, objeto.getId());
        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet res = st.executeQuery();

        // Recorrer el conjunto de resultados
        while (res.next()) {
            int stockDisponible = res.getInt("Cantidad Disponible"); // Obtener la cantidad disponible en stock

            if (cantidad > stockDisponible) {
                // Si la cantidad solicitada es mayor que la cantidad disponible, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Cantidad no disponible");
                return; // Salir del método sin agregar el producto
            }

            int iva = 5; // Definir el valor del IVA
            float precioVenta = res.getFloat("Precio Venta"); // Obtener el precio de venta como float
            float totalPrecio = cantidad * precioVenta; // Calcular el precio total sin IVA
            float operacionIva = (totalPrecio * iva) / 100; // Calcular el IVA
            float totalPrecioConIva = totalPrecio + operacionIva; // Calcular el precio total con IVA

            // Crear un array de Strings para almacenar los datos de cada producto
            String[] datos = new String[9];
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString("id Producto"); // ID Producto
            datos[1] = res.getString("Nombre"); // Nombre del Producto
            datos[2] = res.getString("categoria_nombre"); // Nombre categoría
            datos[3] = String.valueOf(cantidad); // Cantidad pedido
            datos[4] = res.getString("Precio Venta"); // Precio Venta
            datos[5] = String.valueOf(iva); // % IVA
            datos[6] = String.valueOf(totalPrecio); // Precio total del producto sin IVA
            datos[7] = String.valueOf(operacionIva); // Precio del IVA
            datos[8] = String.valueOf(totalPrecioConIva); // Precio total con IVA

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

    // Obtener el modelo de la tabla
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();

    // Agregar los datos obtenidos a la tabla sin limpiar las filas existentes
    for (String[] datos : datosLista) {
        model.addRow(datos);
    }
}


public void eliminarProductoPedido(JTable tabla) {
    // Obtener el modelo de la tabla
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    
    // Obtener la fila seleccionada
    int filaSeleccionada = tabla.getSelectedRow();
    
    // Verificar si se ha seleccionado una fila
    if (filaSeleccionada != -1) {
        // Eliminar la fila seleccionada del modelo de la tabla
        model.removeRow(filaSeleccionada);
        JOptionPane.showMessageDialog(null, "Producto eliminado del pedido");
    } else {
        // Mostrar un mensaje de error si no se ha seleccionado ninguna fila
        JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
    }
}

public boolean finalizarVenta(JTable tablaProductos, int idCliente, int idUsuario, float valorManoObra, String descripcion) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    Connection conexion = cx.conectar();

    // Consultas SQL para insertar en la tabla pedidos y detallepedidos, y actualizar el stock
    String sqlInsertPedido = "INSERT INTO pedidos (id_cliente_FK, id_usuario_FK, fecha_pedido, total, tipo_pedido, mano_obra, descripcion) VALUES (?, ?, CURRENT_DATE, ?, 'reparacion', ?, ?)";
    String sqlLastPedidoId = "SELECT LAST_INSERT_ID() AS lastId"; // Cambiado a LAST_INSERT_ID() para obtener el último id insertado
    String sqlInsertDetallePedido = "INSERT INTO detallepedidos (id_pedido_FK, id_producto_FK, cantidad, precio_sin_iva, precio_con_iva, precio_iva, porcentaje_IVA) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String sqlStock = "SELECT stock FROM productos WHERE id_producto_PK = ?";
    String sqlActualizarStock = "UPDATE productos SET stock = ? WHERE id_producto_PK = ?";

    try {
        // Iniciar una transacción
        conexion.setAutoCommit(false);

        // Preparar las consultas
        var psInsertPedido = conexion.prepareStatement(sqlInsertPedido);
        var psInsertDetallePedido = conexion.prepareStatement(sqlInsertDetallePedido);
        var psStock = conexion.prepareStatement(sqlStock);
        var psActualizarStock = conexion.prepareStatement(sqlActualizarStock);

        // Calcular el total del pedido sumando la columna Total con IVA
        float totalPedido = 0;
        for (int i = 0; i < tablaProductos.getRowCount(); i++) {
            String totalConIvaStr = tablaProductos.getValueAt(i, 8).toString(); // Total con IVA como String
            float totalConIva = Float.parseFloat(totalConIvaStr); // Convertir a Float
            totalPedido += totalConIva;
        }

        // Insertar en la tabla pedidos
        psInsertPedido.setInt(1, idCliente);
        psInsertPedido.setInt(2, idUsuario);
        psInsertPedido.setFloat(3, totalPedido);
        psInsertPedido.setFloat(4, valorManoObra);
        psInsertPedido.setString(5, descripcion);
        psInsertPedido.executeUpdate();

        // Obtener el último id_pedido registrado
        var psLastPedidoId = conexion.prepareStatement(sqlLastPedidoId);
        var rsLastPedidoId = psLastPedidoId.executeQuery();
        if (!rsLastPedidoId.next()) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID del pedido", "Error", JOptionPane.ERROR_MESSAGE);
            conexion.rollback();
            return false;
        }
        int idPedido = rsLastPedidoId.getInt("lastId");

        // Iterar sobre la tabla de productos
        for (int i = 0; i < tablaProductos.getRowCount(); i++) {
            String idProductoStr = tablaProductos.getValueAt(i, 0).toString(); // ID Producto como String
            int idProducto = Integer.parseInt(idProductoStr); // Convertir a Integer
            String cantidadPedidoStr = tablaProductos.getValueAt(i, 3).toString(); // Cantidad como String
            int cantidadPedido = Integer.parseInt(cantidadPedidoStr); // Convertir a Integer

            // Consultar el stock actual del producto
            psStock.setInt(1, idProducto);
            var rsStock = psStock.executeQuery();

            // Verificar si el producto existe en la base de datos
            if (rsStock.next()) {
                int stockActual = rsStock.getInt("stock");

                // Validar que la cantidad a pedir no exceda el stock disponible
                if (cantidadPedido > stockActual) {
                    JOptionPane.showMessageDialog(null, "Cantidad pedida excede el stock disponible para el producto ID: " + idProducto, "Error", JOptionPane.ERROR_MESSAGE);
                    conexion.rollback();
                    return false; // Error en la inserción
                }

                // Convertir valores de la JTable a Float
                String precioUnidadStr = tablaProductos.getValueAt(i, 4).toString();
                String porcentajeIvaStr = tablaProductos.getValueAt(i, 5).toString();
                String totalSinIvaStr = tablaProductos.getValueAt(i, 6).toString();
                String valorIvaStr = tablaProductos.getValueAt(i, 7).toString();
                String totalConIvaStr = tablaProductos.getValueAt(i, 8).toString();

                float precioUnidad = Float.parseFloat(precioUnidadStr);
                int porcentajeIva = Integer.parseInt(porcentajeIvaStr);
                float totalSinIva = Float.parseFloat(totalSinIvaStr);
                float valorIva = Float.parseFloat(valorIvaStr);
                float totalConIva = Float.parseFloat(totalConIvaStr);

                // Insertar en la tabla detallepedidos
                psInsertDetallePedido.setInt(1, idPedido);
                psInsertDetallePedido.setInt(2, idProducto);
                psInsertDetallePedido.setInt(3, cantidadPedido);
                psInsertDetallePedido.setFloat(4, totalSinIva);
                psInsertDetallePedido.setFloat(5, totalConIva);
                psInsertDetallePedido.setFloat(6, valorIva);
                psInsertDetallePedido.setFloat(7, porcentajeIva);
                psInsertDetallePedido.addBatch();

                // Actualizar el stock del producto
                psActualizarStock.setInt(1, stockActual - cantidadPedido);
                psActualizarStock.setInt(2, idProducto);
                psActualizarStock.addBatch();
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado en la base de datos ID: " + idProducto, "Error", JOptionPane.ERROR_MESSAGE);
                conexion.rollback();
                return false; // Error en la inserción
            }
        }

        // Ejecutar todas las inserciones y actualizaciones en batch
        psInsertDetallePedido.executeBatch();
        psActualizarStock.executeBatch();

        // Confirmar la transacción
        conexion.commit();
        return true; // Inserción exitosa
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        try {
            // Deshacer la transacción en caso de error
            conexion.rollback();
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        return false; // Error en la inserción
    } finally {
        try {
            // Asegurarse de desconectar la conexión a la base de datos
            if (conexion != null && !conexion.isClosed()) {
                conexion.setAutoCommit(true); // Restaurar el modo de autocommit
                cx.desconectar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


public String[][] listarReparaciones() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de reparaciones
    String sql = "SELECT "
               + "p.fecha_pedido, "
               + "CONCAT(c.nombre, ' ', c.apellido) AS cliente_nombre, "
               + "CONCAT(u.nombre, ' ', u.apellido) AS usuario_nombre, "
               + "(p.total + p.mano_obra) AS total, "
               + "p.fecha_entrega_pedido "
               + "FROM pedidos AS p "
               + "INNER JOIN clientes AS c ON p.id_cliente_FK = c.documento "
               + "INNER JOIN usuarios AS u ON p.id_usuario_FK = u.identificacion_PK "
               + "WHERE p.tipo_pedido = 'reparacion'";

    // Lista para almacenar los datos de las reparaciones
    List<String[]> datosLista = new ArrayList<>();

    // Declarar Statement y ResultSet
    Statement st = null;
    ResultSet res = null;

    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Crear un array de Strings para almacenar los datos de cada reparación
            String[] datos = new String[5];
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString("fecha_pedido"); // Fecha del pedido
            datos[1] = res.getString("cliente_nombre"); // Nombre del cliente
            datos[2] = res.getString("usuario_nombre"); // Nombre del usuario (vendedor)
            datos[3] = res.getString("total"); // Total de la reparación (total + mano de obra)
            datos[4] = res.getString("fecha_entrega_pedido"); // Fecha de entrega

            // Agregar el array de datos a la lista
            datosLista.add(datos);
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
    } finally {
        // Asegurarse de cerrar ResultSet y Statement
        try {
            if (res != null) res.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Asegurarse de desconectar la conexión a la base de datos
        cx.desconectar();
    }

    // Crear una matriz bidimensional de Strings para almacenar los datos de reparaciones
    String[][] datosMatriz = new String[datosLista.size()][5];

    // Recorrer la lista de datos obtenida
    for (int i = 0; i < datosLista.size(); i++) {
        // Obtener cada array de datos de reparación de la lista y asignarlo a la matriz de datos
        datosMatriz[i] = datosLista.get(i);
    }

    // Devolver la matriz de datos que contiene la información de las reparaciones
    return datosMatriz;
}


public String[][] listarReparacionesSinTerminar() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de pedidos sin terminar
    String sql = "SELECT p.id_pedido_PK, " +
                          "CONCAT(c.nombre, ' ', c.apellido) AS nombreCliente, " +
                          "CONCAT(u.nombre, ' ', u.apellido) AS nombreUsuario, " +
                          "p.fecha_pedido, " +
                          "p.total " +
                          "FROM pedidos AS p " +
                          "INNER JOIN clientes AS c ON p.id_cliente_FK = c.documento " +
                          "INNER JOIN usuarios AS u ON p.id_usuario_FK = u.identificacion_PK " +
                          "WHERE p.tipo_pedido = 'reparacion' AND p.fecha_entrega_pedido IS NULL;";

    Statement st = null;
    ResultSet res = null;
    // Lista para almacenar los datos de las reparaciones
    List<String[]> datosLista = new ArrayList<>();

    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Crear un array de Strings para almacenar los datos de cada reparación
            String[] datos = new String[5];
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString("id_pedido_PK"); // ID del pedido
            datos[1] = res.getString("nombreUsuario"); // Nombre del usuario
            datos[2] = res.getString("nombreCliente"); // Nombre del cliente
            datos[3] = res.getString("fecha_pedido"); // Fecha del pedido
            datos[4] = res.getString("total"); // Total de la reparación

            // Agregar el array de datos a la lista
            datosLista.add(datos);
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
    } finally {
        // Asegurarse de cerrar el ResultSet, Statement y Connection
        try {
            if (res != null) res.close();
            if (st != null) st.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Asegurarse de desconectar la conexión a la base de datos
        cx.desconectar();
    }

    // Crear una matriz bidimensional de Strings para almacenar los datos de reparaciones
    String[][] datosMatriz = new String[datosLista.size()][5];

    // Recorrer la lista de datos obtenida
    for (int i = 0; i < datosLista.size(); i++) {
        // Obtener cada array de datos de reparación de la lista y asignarlo a la matriz de datos
        datosMatriz[i] = datosLista.get(i);
    }

    // Devolver la matriz de datos que contiene la información de las reparaciones
    return datosMatriz;
}


// Método seleccionar para obtener los datos de la casilla seleccionada en la tabla
public void seleccionReparacion(JTable tabla,JTextField textIdPedido, JTextField textIdUsuario, JTextField textIdCliente, JTextField textFechaPedido, JTextField textTotalFinal) {
    try {
        int fila = tabla.getSelectedRow(); // Obtener la fila seleccionada

        if (fila >= 0) {
            
            // Obtener y establecer el valor del total final (total_final)
            Object IdPedidoObj = tabla.getValueAt(fila, 0);
            if (IdPedidoObj != null) {
                textIdPedido.setText(IdPedidoObj.toString());
            }
            // Obtener y establecer el valor del identificador del usuario (id_usuario_FK)
            Object idUsuarioObj = tabla.getValueAt(fila, 1);
            if (idUsuarioObj != null) {
                textIdUsuario.setText(idUsuarioObj.toString());
            }

            // Obtener y establecer el valor del identificador del cliente (id_cliente)
            Object idClienteObj = tabla.getValueAt(fila, 2);
            if (idClienteObj != null) {
                textIdCliente.setText(idClienteObj.toString());
            }

            // Obtener y establecer el valor de la fecha del pedido (fecha_pedido)
            Object fechaPedidoObj = tabla.getValueAt(fila, 3);
            if (fechaPedidoObj != null) {
                textFechaPedido.setText(fechaPedidoObj.toString());
            }
            
            // Obtener y establecer el valor del total final (total_final)
            Object totalFinalObj = tabla.getValueAt(fila, 4);
            if (totalFinalObj != null) {
                textTotalFinal.setText(totalFinalObj.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
    }
}

// Listar pedidos de reparación de la base de datos
public String[][] obtenerPedidosReparacionMatriz() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de pedidos de reparación
    String sql = "SELECT p.id_pedido_PK, c.nombre, p.fecha_pedido, (p.total + p.mano_obra) AS total_final FROM pedidos AS p INNER JOIN clientes as c on p.id_cliente_FK = c.documento WHERE p.tipo_pedido = 'reparacion'";

    Statement st;
    // Lista para almacenar los datos de los pedidos de reparación
    List<String[]> datosLista = new ArrayList<>();

    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Crear un array de Strings para almacenar los datos de cada pedido
            String[] datos = new String[5];
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString(1); // id_pedido_PK
            datos[1] = res.getString(2); // id_usuario_FK
            datos[2] = res.getString(3); // id_cliente
            datos[3] = res.getString(4); // fecha_pedido
            datos[4] = res.getString(5); // total_final

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

    // Crear una matriz bidimensional de Strings para almacenar los datos de los pedidos de reparación
    String[][] datosMatriz = new String[datosLista.size()][4];

    // Recorrer la lista de datos obtenida
    for (int i = 0; i < datosLista.size(); i++) {
        // Obtener cada array de datos de pedido de la lista y asignarlo a la matriz de datos
        datosMatriz[i] = datosLista.get(i);
    }

    // Devolver la matriz de datos que contiene la información de los pedidos de reparación
    return datosMatriz;
}


public boolean actualizarFechaEntregaPedido(int idPedido) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    if (conexion == null) {
        // Mostrar mensaje de error si la conexión falla
        JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos.");
        return false;
    }

    // Consulta SQL para actualizar la fecha de entrega del pedido
    String sqlActualizar = "UPDATE pedidos SET fecha_entrega_pedido = CURRENT_DATE WHERE id_pedido_PK = ?";

    try {
        // Preparar la declaración SQL para actualizar la fecha de entrega del pedido
        var psActualizar = conexion.prepareStatement(sqlActualizar);
        psActualizar.setInt(1, idPedido);

        // Ejecutar la actualización
        int filasAfectadas = psActualizar.executeUpdate();

        if (filasAfectadas > 0) {
            // Mostrar mensaje de éxito si la actualización es exitosa
            JOptionPane.showMessageDialog(null, "Fecha de entrega del pedido actualizada satisfactoriamente.");
        } else {
            // Mostrar mensaje de error si la actualización falla
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la fecha de entrega del pedido.");
        }

        return filasAfectadas > 0;

    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar la fecha de entrega del pedido.");
        return false;
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}

}
