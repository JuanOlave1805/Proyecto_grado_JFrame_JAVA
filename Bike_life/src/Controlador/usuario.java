package Controlador;

import Metodo.obtenerUsuarios;
import Metodo.validacionUsuario;
import javax.swing.JFrame;

/**
 * Controlador para la gestión de usuarios.
 */
public class usuario {
   
    /**
     * Método para realizar el inicio de sesión.
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @param frameActual Referencia al JFrame actual desde donde se llama el método.
     */
    public void metodoLogin(String usuario, String contrasena, JFrame frameActual) {
        // Instancio el método de validación de usuario
        validacionUsuario validacion = new validacionUsuario();
        
        // Ejecuto la validación del usuario
        validacion.metodo_validacion(usuario, contrasena, frameActual);
    }
    
    
}
//https://www.youtube.com/watch?v=gHXQ9rhM-Q4&t=55s