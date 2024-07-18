package Controlador;

import Metodo.metodosUsuarios;
import java.util.List; // Importa java.util.List para usar List correctamente
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ctrlUsuario {

    public String[][] obtenerDatos() {
        // Crear una instancia del objeto obtenerUsuarios para interactuar con la lógica de obtener usuarios
        metodosUsuarios obtener = new metodosUsuarios();
        
        // Llamar al método obtener_usuarios() de la clase obtenerUsuarios para obtener una lista de datos de usuarios
        List<String[]> datosLista = obtener.obtener_usuarios();
        
        // Crear una matriz bidimensional de Strings para almacenar los datos de usuarios
        String[][] datosMatriz = new String[datosLista.size()][7];
        
        // Recorrer la lista de datos obtenida
        for (int i = 0; i < datosLista.size(); i++) {
            // Obtener cada array de datos de usuario de la lista y asignarlo a la matriz de datos
            datosMatriz[i] = datosLista.get(i);
        }

        // Devolver la matriz de datos que contiene la información de los usuarios
        return datosMatriz;
    }
    
    /**
     *
     * @param identificacion
     * @param nombre
     * @param apellido
     * @param edad
     * @param contrasena
     * @param correo
     * @param rol
     */
    
    public void AgregarUsuario(int identificacion, String nombre, String apellido, int edad, String correo, String contrasena, int rol, JFrame frameActual){
        // Instancio el método de agregar usuarios
        metodosUsuarios agregar = new metodosUsuarios();
        // Ejecuto el metodo de agregar usuario
        agregar.agregar_usuario(identificacion, nombre, apellido, edad, correo, contrasena, rol, frameActual);
    }
    public void seleccionUsuario(JTable tabla_usuarios, JTextField textIdentificacion, JTextField textNombre, JTextField textApellido, JTextField textEdad, JTextField textCorreo, JTextField textContrasena){
        metodosUsuarios seleccionar = new metodosUsuarios();
        seleccionar.seleccionUsuario(tabla_usuarios, textIdentificacion, textNombre, textApellido, textEdad, textCorreo, textContrasena);
    }
    
    public boolean actualizarUsuario(int identificacion, String nombre, String apellido, int edad, String correo, String contrasena, int rol) {
        metodosUsuarios actualizar = new metodosUsuarios();
        return actualizar.modificar_usuario(identificacion, nombre, apellido, edad, correo, contrasena, rol);
    }
    public boolean eliminarUsuario(int identificacion){
        metodosUsuarios eliminar = new metodosUsuarios();
        eliminar.eliminar_usuario(identificacion);
        return true;
    }
}
//https://www.youtube.com/watch?v=gHXQ9rhM-Q4&t=55s