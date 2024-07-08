package Metodo;

import Vista.vistaVentaAdmin;
import Vista.vistaVentaVendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class validacionUsuario {

    private conexionBD cx;

    public void metodo_validacion(String usuario, String contrasena, JFrame frameActual) {
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa tu usuario y contraseña.");
            return; // Salir del método si falta usuario o contraseña
        }

        try {
            cx = new conexionBD();
            Connection conexion = cx.conectar();

            if (conexion == null) {
                throw new SQLException("No se pudo establecer una conexión con la base de datos.");
            }

            String sql = "SELECT tb_usuarios.identificacion_PK AS identificacion, " +
                         "tb_usuarios.nombre AS nombre, " +
                         "tb_rol.nombre AS rol " +
                         "FROM tb_usuarios " +
                         "INNER JOIN tb_rol ON tb_usuarios.id_rol_FK = tb_rol.id_PK " +
                         "WHERE tb_usuarios.identificacion_PK = ? " +
                         "AND tb_usuarios.contrasena = ?";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String rolUsuario = rs.getString("rol");
                System.out.println("Credenciales válidas. Rol: " + rolUsuario);
                JOptionPane.showMessageDialog(null, "Credenciales válidas. Rol: " + rolUsuario);

                // Cerrar la ventana actual
                frameActual.dispose();

                // Abrir la ventana correspondiente según el rol
                if (rolUsuario.equals("ADMIN")) {
                    vistaVentaAdmin ventanaAdmin = new vistaVentaAdmin();
                    ventanaAdmin.setVisible(true);
                } else if (rolUsuario.equals("VENDEDOR")) {
                    vistaVentaVendedor ventanaVendedor = new vistaVentaVendedor();
                    ventanaVendedor.setVisible(true);
                } else {
                    // Implementar lógica para otros roles si es necesario
                }
            } else {
                System.out.println("Credenciales inválidas");
                JOptionPane.showMessageDialog(null, "Credenciales inválidas");
            }

            // Cerrar recursos
            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException ex) {
            System.out.println("Error al intentar validar las credenciales.");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al intentar validar las credenciales.");
        } finally {
            // Desconectar la conexión a la base de datos
            if (cx != null) {
                cx.desconectar();
            }
        }
    }
}
