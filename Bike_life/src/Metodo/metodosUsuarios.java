/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Metodo;

import Vista.vistaUsuariosAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class metodosUsuarios {

    // Listar elementos de la BD
    public List<String[]> obtener_usuarios() {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        // Consulta SQL para obtener los datos de usuarios y roles
        String sql = "SELECT users.identificacion_PK, users.nombre, users.apellido, users.edad, users.correo, " +
                     "users.contrasena, rol.nombre " +
                     "FROM tb_usuarios AS users " +
                     "INNER JOIN tb_rol AS rol ON users.id_rol_FK = rol.id_PK";

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
                String[] datos = new String[7];
                // Obtener y almacenar cada campo de datos en el array
                datos[0] = res.getString(1); // PK_identificacion
                datos[1] = res.getString(2); // nombre
                datos[2] = res.getString(3); // apellido
                datos[3] = res.getString(4); // edad
                datos[4] = res.getString(5); // correo
                datos[5] = res.getString(6); // contrasena
                datos[6] = res.getString(7); // nombre_rol

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

        // Devolver la lista de arrays de datos de usuarios
        return datosLista;
    }
    
    //Eliminar elemento de la BD
    public boolean eliminar_usuario(int identificacion) {
    // Crear una instancia de conexión a la base de datos
    conexionBD cx = new conexionBD();
    // Establecer una conexión a la base de datos
    Connection conexion = cx.conectar();

    // Consulta SQL para eliminar un usuario por su identificación primaria
    String sql = "DELETE FROM tb_usuarios WHERE identificacion_PK = ?";
    
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
    
    //Metodo seleccionar para obtener los datos de la casilla seleccionada en la tabla
    public void seleccionUsuario(JTable tabla_usuarios, JTextField textIdentificacion, JTextField textNombre, JTextField textApellido, JTextField textEdad, JTextField textCorreo, JTextField textContrasena) {
        try {
            int fila = tabla_usuarios.getSelectedRow();
        
            if (fila >= 0) {
                // Obtener y establecer el valor del identificador
                Object identificacionObj = tabla_usuarios.getValueAt(fila, 0);
                if (identificacionObj != null) {
                    textIdentificacion.setText(identificacionObj.toString());
                }
            
                Object nombreObj = tabla_usuarios.getValueAt(fila, 1);
                if (nombreObj != null) {
                    textNombre.setText(nombreObj.toString());
                }
            
                Object apellidoObj = tabla_usuarios.getValueAt(fila, 2);
                if (apellidoObj != null) {
                textApellido.setText(apellidoObj.toString());
                }
            
                Object edadObj = tabla_usuarios.getValueAt(fila, 3);
                if (edadObj != null) {
                    textEdad.setText(edadObj.toString());
                }
            
                Object correoObj = tabla_usuarios.getValueAt(fila, 4);
                if (correoObj != null) {
                    textCorreo.setText(correoObj.toString());
                }
            
                Object contrasenaObj = tabla_usuarios.getValueAt(fila, 5);
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
    
    //Modificar dato en la BD
    public boolean modificar_usuario(int identificacion, String nuevoNombre, String nuevoApellido, int nuevaEdad, String nuevoCorreo, String nuevaContrasena, int nuevoIdRol) {
        // Crear una instancia de conexión a la base de datos
        conexionBD cx = new conexionBD();
        // Establecer una conexión a la base de datos
        Connection conexion = cx.conectar();

        // Consulta SQL para modificar un usuario por su identificación primaria
        String sql = "UPDATE tb_usuarios SET nombre = ?, apellido = ?, edad = ?, correo = ?, contrasena = ?, id_rol_FK = ? WHERE identificacion_PK = ?";

        try {
            // Preparar la declaración SQL con parámetros
            var ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoApellido);
            ps.setInt(3, nuevaEdad);
            ps.setString(4, nuevoCorreo);
            ps.setString(5, nuevaContrasena);
            ps.setInt(6, nuevoIdRol);
            ps.setInt(7, identificacion); 

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
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    
    //Agregar usuario en la BD
    public boolean agregar_usuario(int identificacion, String nombre, String apellido, int edad, String correo, String contrasena, int id_rol, JFrame frameActual) {
        conexionBD cx = new conexionBD();
        Connection conexion = cx.conectar();

        if (conexion == null) {
            JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
            return false;
        }

        String sqlVerificar = "SELECT COUNT(*) FROM tb_usuarios WHERE identificacion_PK = ?";
        String sqlInsertar = "INSERT INTO tb_usuarios (identificacion_PK, nombre, apellido, edad, correo, contrasena, id_rol_FK) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // Verificar si ya existe un usuario con la misma identificación
            var psVerificar = conexion.prepareStatement(sqlVerificar);
            psVerificar.setInt(1, identificacion);
            ResultSet rs = psVerificar.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                JOptionPane.showMessageDialog(frameActual, "Ya existe un usuario con la misma identificación.");
                return false;
            }

            // Preparar la declaración SQL para insertar un nuevo usuario
            var psInsertar = conexion.prepareStatement(sqlInsertar);
            psInsertar.setInt(1, identificacion);
            psInsertar.setString(2, nombre);
            psInsertar.setString(3, apellido);
            psInsertar.setInt(4, edad);
            psInsertar.setString(5, correo);
            psInsertar.setString(6, contrasena);
            psInsertar.setInt(7, id_rol);

            // Ejecutar la inserción
            int filasAfectadas = psInsertar.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(frameActual, "Usuario agregado satisfactoriamente");
                vistaUsuariosAdmin ventanaAdmin = new vistaUsuariosAdmin();
                ventanaAdmin.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frameActual, "No se pudo agregar el usuario.");
            }

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frameActual, "Error al agregar el usuario.");
            return false;
        } finally {
            cx.desconectar();
        }
    }
}