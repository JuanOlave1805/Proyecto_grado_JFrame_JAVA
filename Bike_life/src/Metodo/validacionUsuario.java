package Metodo;
import Objetos.Usuario;
import VistaVenta.VentaAdmin;
import VistaVenta.VentaVendedor;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class validacionUsuario {

    private conexionBD cx;

    // Método para validar las credenciales de un usuario y abrir la ventana correspondiente según el rol
    public void metodoValidacion(Usuario objeto, JFrame frameActual) {
        try {
            // Crear una instancia de conexión a la base de datos
            cx = new conexionBD();
            // Establecer una conexión con la base de datos
            Connection conexion = cx.conectar();

            // Verificar si la conexión fue exitosa
            if (conexion == null) {
                // Lanzar una excepción si no se pudo conectar
                throw new SQLException("No se pudo establecer una conexión con la base de datos.");
            }

            // Consulta SQL para validar las credenciales del usuario y obtener el rol y el estado asociado
            String sql = "SELECT usuarios.identificacion_PK AS identificacion, usuarios.nombre AS nombre, rol.nombre AS rol, usuarios.estado AS estado " +
                         "FROM usuarios " +
                         "INNER JOIN rol ON usuarios.id_rol_FK = rol.id_PK " +
                         "WHERE usuarios.identificacion_PK = ? AND usuarios.contrasena = ? AND usuarios.estado = 'ACTIVO'";

            // Preparar la declaración SQL con parámetros
            PreparedStatement ps = conexion.prepareStatement(sql);
            // Establecer el identificador del usuario en el primer parámetro
            ps.setInt(1, objeto.getIdentificacion_Pk());
            // Establecer la contraseña del usuario en el segundo parámetro
            ps.setString(2, objeto.getContrasena());

            // Ejecutar la consulta y obtener el conjunto de resultados
            ResultSet rs = ps.executeQuery();

            // Verificar si se obtuvo un resultado válido
            if (rs.next()) {
                // Obtener el nombre del usuario desde el conjunto de resultados
                String nombreUsuario = rs.getString("nombre");
                // Obtener el rol del usuario desde el conjunto de resultados
                String rolUsuario = rs.getString("rol");
                // Obtener la identificación del usuario desde el conjunto de resultados
                int idUsuario = rs.getInt("identificacion");

                // Imprimir y mostrar un mensaje indicando que las credenciales son válidas y dar la bienvenida al usuario
                System.out.println("Credenciales válidas. Bienvenido " + nombreUsuario);

                // JOption mensaje
                JOptionPane.showMessageDialog(null, "Bienvenido: "+ nombreUsuario, "Bienvenido", JOptionPane.INFORMATION_MESSAGE);


    

                // Cerrar la ventana actual
                frameActual.dispose();

                // Abrir la ventana correspondiente según el rol del usuario
                if (rolUsuario.equals("ADMIN")) {
                    // Abrir la ventana de administración para el rol de administrador
                    VentaAdmin ventanaAdmin = new VentaAdmin();
                    ventanaAdmin.setVisible(true);
                    // Rellenar el campo textIdUsuario con la identificación del usuario
                    ventanaAdmin.rellenarIdUsuario(idUsuario);
                } else if (rolUsuario.equals("VENDEDOR")) {
                    // Abrir la ventana de ventas para el rol de vendedor
                    VentaVendedor ventanaVendedor = new VentaVendedor();
                    ventanaVendedor.setVisible(true);
                    // Rellenar el campo textIdUsuario con la identificación del usuario
                    ventanaVendedor.rellenarIdUsuario(idUsuario);
                }
            } else {
                // Imprimir y mostrar un mensaje indicando que las credenciales son inválidas
                System.out.println("Credenciales inválidas");
                JOptionPane.showMessageDialog(null, "La identificación o la contraseña son incorrectas");
            }

            // Cerrar recursos abiertos
            rs.close();
            ps.close();
            conexion.close();

        } catch (SQLException ex) {
            // Manejar excepciones SQL, imprimir el rastreo de la pila y mostrar un mensaje de error
            System.out.println("Error al intentar validar las credenciales.");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al intentar validar las credenciales.");
        } finally {
            // Asegurarse de desconectar la conexión a la base de datos en caso de cualquier excepción
            if (cx != null) {
                cx.desconectar();
            }
        }
    }
}

