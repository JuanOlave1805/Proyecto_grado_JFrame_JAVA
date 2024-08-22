package Metodo;

import Objetos.Usuario;
import VistaUsuarios.UsuariosAdmin;
import org.mindrot.jbcrypt.BCrypt;
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
    
// Método para obtener una matriz de usuarios de la base de datos
    public String[][] obtenerUsuariosMatriz() {
        conexionBD cx = new conexionBD();
        Connection conexion = cx.conectar();

        // Consulta SQL para obtener los datos de usuarios y roles sin contraseña
        String sql = "SELECT users.identificacion_PK, users.nombre, users.apellido, users.edad, users.correo, users.telefono, rol.nombre, users.estado " +
                     "FROM usuarios AS users INNER JOIN rol AS rol ON users.id_rol_FK = rol.id_PK";

        Statement st;
        List<String[]> datosLista = new ArrayList<>();

        try {
            st = conexion.createStatement();
            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                String[] datos = new String[8]; // Reducido a 8 campos
                datos[0] = res.getString(1); // PK_identificacion
                datos[1] = res.getString(2); // nombre
                datos[2] = res.getString(3); // apellido
                datos[3] = res.getString(4); // edad
                datos[4] = res.getString(5); // correo
                datos[5] = res.getString(6); // telefono
                datos[6] = res.getString(7); // nombre_rol
                datos[7] = res.getString(8); // estado

                datosLista.add(datos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cx.desconectar();
        }

        String[][] datosMatriz = new String[datosLista.size()][8];

        for (int i = 0; i < datosLista.size(); i++) {
            datosMatriz[i] = datosLista.get(i);
        }

        return datosMatriz;
    }

    // Eliminar usuario de la base de datos
    public boolean eliminarUsuario(Usuario objeto) {
        int identificacion = objeto.getIdentificacion_Pk();
        conexionBD cx = new conexionBD();
        Connection conexion = cx.conectar();

        String sql = "DELETE FROM usuarios WHERE identificacion_PK = ?";

        try {
            var ps = conexion.prepareStatement(sql);
            ps.setInt(1, identificacion);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            cx.desconectar();
        }
    }

    // Seleccionar usuario para mostrar en los campos de texto
    public void seleccionUsuario(JTable tabla_usuarios, JTextField textIdentificacion, JTextField textNombre, JTextField textApellido, JTextField textEdad, JTextField textCorreo, JTextField textTelefono) {
        try {
            int fila = tabla_usuarios.getSelectedRow();

            if (fila >= 0) {
                textIdentificacion.setText(tabla_usuarios.getValueAt(fila, 0).toString());
                textNombre.setText(tabla_usuarios.getValueAt(fila, 1).toString());
                textApellido.setText(tabla_usuarios.getValueAt(fila, 2).toString());
                textEdad.setText(tabla_usuarios.getValueAt(fila, 3).toString());
                textCorreo.setText(tabla_usuarios.getValueAt(fila, 4).toString());
                textTelefono.setText(tabla_usuarios.getValueAt(fila, 5).toString());
                // No se establece la contraseña aquí
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
        }
    }

    public boolean actualizarUsuario(Usuario objeto) {
    conexionBD cx = new conexionBD();
    Connection conexion = cx.conectar();

    // Consulta SQL básica sin la columna de contraseña
    String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, edad = ?, correo = ?, telefono = ?, id_rol_FK = ?, estado = ? WHERE identificacion_PK = ?";

    // Ajustar la consulta si se proporciona una nueva contraseña
    if (objeto.getContrasena() != null && !objeto.getContrasena().isEmpty()) {
        sql = "UPDATE usuarios SET nombre = ?, apellido = ?, edad = ?, correo = ?, telefono = ?, contrasena = ?, id_rol_FK = ?, estado = ? WHERE identificacion_PK = ?";
    }

    try {
        var ps = conexion.prepareStatement(sql);
        ps.setString(1, objeto.getNombre());
        ps.setString(2, objeto.getApellido());
        ps.setInt(3, objeto.getEdad());
        ps.setString(4, objeto.getCorreo());
        ps.setString(5, objeto.getTelefono());

        int parameterIndex = 6;
        
        // Setear la contraseña solo si se proporciona una nueva
        if (objeto.getContrasena() != null && !objeto.getContrasena().isEmpty()) {
            ps.setString(parameterIndex++, hashPassword(objeto.getContrasena()));
        }

        ps.setInt(parameterIndex++, objeto.getRol_Fk());
        ps.setString(parameterIndex++, objeto.getEstado());
        ps.setInt(parameterIndex, objeto.getIdentificacion_Pk());

        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        cx.desconectar();
    }
}


    // Método para encriptar la contraseña utilizando BCrypt
    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Agregar un usuario a la base de datos
    public boolean agregarUsuario(Usuario objeto, JFrame frameActual, int idUser) {
        conexionBD cx = new conexionBD();
        Connection conexion = cx.conectar();

        if (conexion == null) {
            JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
            return false;
        }

        String sqlVerificar = "SELECT COUNT(*) FROM usuarios WHERE identificacion_PK = ?";
        String sqlInsertar = "INSERT INTO usuarios (identificacion_PK, nombre, apellido, edad, contrasena, correo, telefono, id_rol_FK, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'ACTIVO')";

        try {
            var psVerificar = conexion.prepareStatement(sqlVerificar);
            psVerificar.setInt(1, objeto.getIdentificacion_Pk());
            ResultSet rs = psVerificar.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(frameActual, "Ya existe un usuario con la misma identificación.");
                return false;
            }

            var psInsertar = conexion.prepareStatement(sqlInsertar);
            psInsertar.setInt(1, objeto.getIdentificacion_Pk());
            psInsertar.setString(2, objeto.getNombre());
            psInsertar.setString(3, objeto.getApellido());
            psInsertar.setInt(4, objeto.getEdad());

            // Encriptar la contraseña antes de guardarla
            psInsertar.setString(5, hashPassword(objeto.getContrasena()));

            psInsertar.setString(6, objeto.getCorreo());
            psInsertar.setString(7, objeto.getTelefono());
            psInsertar.setInt(8, objeto.getRol_Fk());

            int filasAfectadas = psInsertar.executeUpdate();

            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(frameActual, "Usuario agregado satisfactoriamente");
                UsuariosAdmin ventana = new UsuariosAdmin();
                ventana.rellenarIdUsuario(idUser);
                ventana.setVisible(true);
                frameActual.dispose();
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

    // Cargar roles desde la base de datos en un JComboBox
    public void cargarRol(String tabla, String valor, JComboBox comboBoxRol) {
        conexionBD cx = new conexionBD();
        Connection conexion = cx.conectar();

        String sql = "SELECT rol.nombre FROM rol";

        Statement st;

        try {
            st = conexion.createStatement();
            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                comboBoxRol.addItem(res.getString(valor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cx.desconectar();
        }
    }




//Perfil


    public Usuario obtenerUsuarioPerfil(int idUsuario) throws SQLException {
        conexionBD cx = new conexionBD();
        Connection conexion = cx.conectar();

        String sql = "SELECT identificacion_PK, nombre, apellido, edad, correo, telefono FROM usuarios WHERE identificacion_PK = ?";

        Usuario usuario = null;

        try (var ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    usuario = new Usuario();
                    usuario.setIdentificacion_Pk(res.getInt(1));
                    usuario.setNombre(res.getString(2));
                    usuario.setApellido(res.getString(3));
                    usuario.setEdad(res.getInt(4));
                    usuario.setCorreo(res.getString(5));
                    usuario.setTelefono(res.getString(6));
                } else {
                    System.out.println("No se encontró un usuario con el id proporcionado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cx.desconectar();
        }
        return usuario;
    }

    public boolean actualizarUsuarioPerfil(Usuario usuario) throws SQLException {
    conexionBD cx = new conexionBD();
    Connection conexion = cx.conectar();

    // Construir la consulta SQL base
    String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, edad = ?, correo = ?, telefono = ?";

    // Añadir la cláusula para actualizar la contraseña si se proporciona
    if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
        sql += ", contrasena = ?";
    }

    // Añadir la cláusula WHERE
    sql += " WHERE identificacion_PK = ?";

    try (var ps = conexion.prepareStatement(sql)) {
        // Establecer los valores de los parámetros
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setInt(3, usuario.getEdad());
        ps.setString(4, usuario.getCorreo());
        ps.setString(5, usuario.getTelefono());

        // Establecer el valor de la contraseña si se proporciona
        int index = 6; // Índice para el parámetro de contraseña
        if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
            ps.setString(index++, BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt())); // Encriptar la contraseña
        }

        // Establecer el valor del identificador del usuario
        ps.setInt(index, usuario.getIdentificacion_Pk());

        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        cx.desconectar();
    }
}
}