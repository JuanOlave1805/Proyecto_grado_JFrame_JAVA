package Controlador;

import Metodo.obtenerUsuarios;
import java.util.List; // Importa java.util.List para usar List correctamente

public class usuarioAdmin {

    public String[][] obtenerDatos() {
        // Crear una instancia del objeto obtenerUsuarios para interactuar con la lógica de obtener usuarios
        obtenerUsuarios obtener = new obtenerUsuarios();
        
        // Llamar al método obtener_usuarios() de la clase obtenerUsuarios para obtener una lista de datos de usuarios
        List<String[]> datosLista = obtener.obtener_usuarios();
        
        // Crear una matriz bidimensional de Strings para almacenar los datos de usuarios
        String[][] datosMatriz = new String[datosLista.size()][4];
        
        // Recorrer la lista de datos obtenida
        for (int i = 0; i < datosLista.size(); i++) {
            // Obtener cada array de datos de usuario de la lista y asignarlo a la matriz de datos
            datosMatriz[i] = datosLista.get(i);
        }

        // Devolver la matriz de datos que contiene la información de los usuarios
        return datosMatriz;
    }
}
//https://www.youtube.com/watch?v=gHXQ9rhM-Q4&t=55s