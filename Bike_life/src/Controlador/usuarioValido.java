/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Metodo.validacionUsuario;

/**
 *
 * @author JUAN DAVID
 */
public class usuarioValido {
   
    //Validacion de login
    public void metodoLogin(String usuario, String contrasena) {
        //Instancio el metodo del controlador validacionLogin
        validacionUsuario validacion=new validacionUsuario();
        //Ejecuto la validacion
        validacion.metodo_validacion(usuario, contrasena);
    }
    
    
}
