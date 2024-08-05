/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import Objetos.Usuario;
import VistaUsuarios.UsuariosAdmin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class metodoUsuario {
    
// Listar usuarios de la base de datos
public String[][] obtenerUsuariosMatriz() {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de usuarios y roles
    String sql = "SELECT users.identificacion_PK, users.nombre, users.apellido, users.edad, users.correo, users.telefono, users.contrasena, rol.nombre, users.estado FROM usuarios AS users INNER JOIN rol AS rol ON users.id_rol_FK = rol.id_PK";

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
            String[] datos = new String[9];
            // Obtener y almacenar cada campo de datos en el array
            datos[0] = res.getString(1); // PK_identificacion
            datos[1] = res.getString(2); // nombre
            datos[2] = res.getString(3); // apellido
            datos[3] = res.getString(4); // edad
            datos[4] = res.getString(5); // correo
            datos[5] = res.getString(6); // telefono
            datos[6] = res.getString(7); // contrasena
            datos[7] = res.getString(8); // nombre_rol
            datos[8] = res.getString(9); // Estado

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
    String[][] datosMatriz = new String[datosLista.size()][8];

    // Recorrer la lista de datos obtenida
    for (int i = 0; i < datosLista.size(); i++) {
        // Obtener cada array de datos de usuario de la lista y asignarlo a la matriz de datos
        datosMatriz[i] = datosLista.get(i);
    }

    // Devolver la matriz de datos que contiene la información de los usuarios
    return datosMatriz;
}

// Eliminar usuario de la base de datos
public boolean eliminarUsuario(Usuario objeto) {
    int identificacion = objeto.getIdentificacion_Pk(); // Obtener la identificación del objeto Usuario

    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para eliminar un usuario por su identificación primaria
    String sql = "DELETE FROM usuarios WHERE identificacion_PK = ?";

    try {
        // Preparar la declaración SQL con parámetros
        var ps = conexion.prepareStatement(sql);
        ps.setInt(1, identificacion);

        // Ejecutar la actualización (eliminar usuario)
        int filasAfectadas = ps.executeUpdate();

        // Verificar si se eliminó correctamente (al menos una fila afectada)
        return filasAfectadas > 0;

    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        return false; // Indicar que no se pudo eliminar
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}

// Método seleccionar para obtener los datos de la casilla seleccionada en la tabla
public void seleccionUsuario(JTable tabla_usuarios, JTextField textIdentificacion, JTextField textNombre, JTextField textApellido, JTextField textEdad, JTextField textCorreo, JTextField textTelefono, JTextField textContrasena) {
    try {
        int fila = tabla_usuarios.getSelectedRow(); // Obtener la fila seleccionada

        if (fila >= 0) {
            // Obtener y establecer el valor del identificador
            Object identificacionObj = tabla_usuarios.getValueAt(fila, 0);
            if (identificacionObj != null) {
                textIdentificacion.setText(identificacionObj.toString());
            }

            // Obtener y establecer el valor del nombre
            Object nombreObj = tabla_usuarios.getValueAt(fila, 1);
            if (nombreObj != null) {
                textNombre.setText(nombreObj.toString());
            }

            // Obtener y establecer el valor del apellido
            Object apellidoObj = tabla_usuarios.getValueAt(fila, 2);
            if (apellidoObj != null) {
                textApellido.setText(apellidoObj.toString());
            }

            // Obtener y establecer el valor de la edad
            Object edadObj = tabla_usuarios.getValueAt(fila, 3);
            if (edadObj != null) {
                textEdad.setText(edadObj.toString());
            }

            // Obtener y establecer el valor del correo
            Object correoObj = tabla_usuarios.getValueAt(fila, 4);
            if (correoObj != null) {
                textCorreo.setText(correoObj.toString());
            }

            // Obtener y establecer el valor del teléfono
            Object telefonoObj = tabla_usuarios.getValueAt(fila, 5);
            if (telefonoObj != null) {
                textTelefono.setText(telefonoObj.toString());
            }

            // Obtener y establecer el valor de la contraseña
            Object contrasenaObj = tabla_usuarios.getValueAt(fila, 6);
            if (contrasenaObj != null) {
                textContrasena.setText(contrasenaObj.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
    }
}

// Actualizar dato en la base de datos
public boolean actualizarUsuario(Usuario objeto) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para modificar un usuario por su identificación primaria
    String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, edad = ?, correo = ?, telefono = ?, contrasena = ?, id_rol_FK = ?, estado = ?  WHERE identificacion_PK = ?";

    try {
        // Preparar la declaración SQL con parámetros
        var ps = conexion.prepareStatement(sql);
        ps.setString(1, objeto.getNombre());
        ps.setString(2, objeto.getApellido());
        ps.setInt(3, objeto.getEdad());
        ps.setString(4, objeto.getCorreo());
        ps.setString(5, objeto.getTelefono());
        ps.setString(6, objeto.getContrasena());
        ps.setInt(7, objeto.getRol_Fk());
        ps.setString (8, objeto.getEstado());
        ps.setInt(9, objeto.getIdentificacion_Pk());

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

// Agregar usuario en la base de datos
public boolean agregarUsuario(Usuario objeto, JFrame frameActual) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    if (conexion == null) {
        // Mostrar mensaje de error si la conexión falla
        JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
        return false;
    }

    // Consultas SQL para verificar existencia e insertar usuario
    String sqlVerificar = "SELECT COUNT(*) FROM usuarios WHERE identificacion_PK = ?";
    String sqlInsertar = "INSERT INTO usuarios (identificacion_PK, nombre, apellido, edad, contrasena, correo, telefono, id_rol_FK, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'ACTIVO')";

    try {
        // Verificar si ya existe un usuario con la misma identificación
        var psVerificar = conexion.prepareStatement(sqlVerificar);
        psVerificar.setInt(1, objeto.getIdentificacion_Pk());
        ResultSet rs = psVerificar.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            // Mostrar mensaje si el usuario ya existe
            JOptionPane.showMessageDialog(frameActual, "Ya existe un usuario con la misma identificación.");
            return false;
        }

        // Preparar la declaración SQL para insertar un nuevo usuario
        var psInsertar = conexion.prepareStatement(sqlInsertar);
        psInsertar.setInt(1, objeto.getIdentificacion_Pk());
        psInsertar.setString(2, objeto.getNombre());
        psInsertar.setString(3, objeto.getApellido());
        psInsertar.setInt(4, objeto.getEdad());
        psInsertar.setString(5, objeto.getContrasena());
        psInsertar.setString(6, objeto.getCorreo());
        psInsertar.setString(7, objeto.getTelefono());
        psInsertar.setInt(8, objeto.getRol_Fk());

        // Ejecutar la inserción
        int filasAfectadas = psInsertar.executeUpdate();

        if (filasAfectadas > 0) {
            // Mostrar mensaje de éxito si la inserción es exitosa
            JOptionPane.showMessageDialog(frameActual, "Usuario agregado satisfactoriamente");
            UsuariosAdmin ventanaAdmin = new UsuariosAdmin();
            ventanaAdmin.setVisible(true);
            frameActual.dispose(); // Cerrar la ventana actual
        } else {
            // Mostrar mensaje de error si la inserción falla
            JOptionPane.showMessageDialog(frameActual, "No se pudo agregar el usuario.");
        }

        return filasAfectadas > 0;

    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        JOptionPane.showMessageDialog(frameActual, "Error al agregar el usuario.");
        return false;
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}

// Método privado no implementado
private void setVisible(boolean b) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
}

// Cargar roles desde la base de datos en un JComboBox
public void cargarRol(String tabla, String valor, JComboBox comboBoxRol) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los roles
    String sql = "SELECT rol.nombre FROM rol";

    Statement st;

    try {
        // Crear un objeto Statement para ejecutar la consulta SQL
        st = conexion.createStatement();
        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet res = st.executeQuery(sql);

        // Recorrer el conjunto de resultados
        while (res.next()) {
            // Agregar cada rol al JComboBox
            comboBoxRol.addItem(res.getString(valor));
        }
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}





//Perfil
    public void obtenerUsuarioPorId(int idUsuario, JTextField txtIdentificacion, JTextField txtNombre, JTextField txtApellido, JTextField txtEdad, JTextField txtCorreo, JTextField txtTelefono, JTextField txtContrasena) throws SQLException {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        // Consulta SQL para obtener los datos del usuario por ID
        String sql = "SELECT identificacion_PK, nombre, apellido, edad, correo, telefono, contrasena FROM usuarios WHERE identificacion_PK = ?";

        // Crear un objeto PreparedStatement para ejecutar la consulta SQL
        var ps = conexion.prepareStatement(sql);
        try {
            
            // Establecer el valor del parámetro idUsuario
            ps.setInt(1, idUsuario);
            // Ejecutar la consulta y obtener el conjunto de resultados
            ResultSet res = ps.executeQuery();

            // Recorrer el conjunto de resultados
            if (res.next()) {
                // Obtener y almacenar cada campo de datos en los JTextFields
                txtIdentificacion.setText(res.getString(1)); // identificacion
                txtNombre.setText(res.getString(2)); // nombre
                txtApellido.setText(res.getString(3)); // apellido
                txtEdad.setText(res.getString(4)); // edad
                txtCorreo.setText(res.getString(5)); // correo
                txtTelefono.setText(res.getString(6)); // telefono
                txtContrasena.setText(res.getString(7)); // contrasena
            } else {
                // Manejar el caso donde no se encontró un usuario con el id proporcionado
                txtIdentificacion.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtEdad.setText("");
                txtCorreo.setText("");
                txtTelefono.setText("");
                txtContrasena.setText("");
                System.out.println("No se encontró un usuario con el id proporcionado.");
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
            cx.desconectar();
        }
    }
    public void obtenerUsuarioPerfil(int idUsuario, JTextField txtIdentificacion, JTextField txtNombre, JTextField txtApellido, JTextField txtEdad, JTextField txtCorreo, JTextField txtTelefono) throws SQLException {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        // Consulta SQL para obtener los datos del usuario por ID
        String sql = "SELECT identificacion_PK, nombre, apellido, edad, correo, telefono FROM usuarios WHERE identificacion_PK = ?";

        // Crear un objeto PreparedStatement para ejecutar la consulta SQL
        var ps = conexion.prepareStatement(sql);
        try {
            
            // Establecer el valor del parámetro idUsuario
            ps.setInt(1, idUsuario);
            // Ejecutar la consulta y obtener el conjunto de resultados
            ResultSet res = ps.executeQuery();

            // Recorrer el conjunto de resultados
            if (res.next()) {
                // Obtener y almacenar cada campo de datos en los JTextFields
                txtIdentificacion.setText(res.getString(1)); // identificacion
                txtNombre.setText(res.getString(2)); // nombre
                txtApellido.setText(res.getString(3)); // apellido
                txtEdad.setText(res.getString(4)); // edad
                txtCorreo.setText(res.getString(5)); // correo
                txtTelefono.setText(res.getString(6)); // telefono
            } else {
                // Manejar el caso donde no se encontró un usuario con el id proporcionado
                txtIdentificacion.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtEdad.setText("");
                txtCorreo.setText("");
                txtTelefono.setText("");
                System.out.println("No se encontró un usuario con el id proporcionado.");
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
            e.printStackTrace();
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
            cx.desconectar();
        }
    }
    
    public boolean actualizarUsuario(int idUsuario, JTextField txtIdentificacion, JTextField txtNombre, JTextField txtApellido, JTextField txtEdad, JTextField txtCorreo, JTextField txtTelefono, JTextField txtContrasena) throws SQLException {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para actualizar los datos del usuario
    String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, edad = ?, correo = ?, telefono = ?, contrasena = ? WHERE identificacion_PK = ?";

    // Crear un objeto PreparedStatement para ejecutar la consulta SQL
    var ps = conexion.prepareStatement(sql);
    try {
        // Establecer los valores de los parámetros
        ps.setString(1, txtNombre.getText()); // nombre
        ps.setString(2, txtApellido.getText()); // apellido
        ps.setString(3, txtEdad.getText()); // edad
        ps.setString(4, txtCorreo.getText()); // correo
        ps.setString(5, txtTelefono.getText()); // telefono
        ps.setString(6, txtContrasena.getText()); // contrasena
        ps.setString(7, txtIdentificacion.getText()); // identificacion_PK

        // Ejecutar la actualización
        int filasAfectadas = ps.executeUpdate();

        // Verificar si se actualizó alguna fila
        return filasAfectadas > 0;
    } catch (SQLException e) {
        // Manejar cualquier excepción SQL imprimiendo el rastreo de la pila
        e.printStackTrace();
        return false;
    } finally {
        // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
        cx.desconectar();
    }
}


}