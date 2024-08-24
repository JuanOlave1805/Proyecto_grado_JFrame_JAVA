package Metodo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Facturacion {

public void generarFacturaPDFVenta(JTable tabla, String directorio, float totalCompra, int idCliente, int idUsuario) {
    // Crear una instancia del documento PDF
    Document documento = new Document();
    
    // Formatear la fecha actual en el formato "yyyy-MM-dd"
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(new Date());
    
    // Construir la ruta completa del archivo PDF
    String rutaArchivo = directorio + "\\Factura_" + fecha + "-" + idCliente + ".pdf";

    try {
        // Crear un escritor PDF que escribirá el documento en la ruta especificada
        PdfWriter.getInstance(documento, new FileOutputStream(new File(rutaArchivo)));
        // Abrir el documento para agregar contenido
        documento.open();

        // Crear una fuente para el título en negrita y tamaño 20
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        // Crear un párrafo con el título y la fuente especificada
        Paragraph titulo = new Paragraph("Factura de Compra BIKE LIFE", fuenteTitulo);
        // Alinear el título al centro
        titulo.setAlignment(Element.ALIGN_CENTER);
        // Agregar el título al documento
        documento.add(titulo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n"));

        // Crear una fuente para la fecha en tamaño 12
        Font fuenteFecha = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Crear un párrafo con la fecha actual y la fuente especificada
        Paragraph fechaParrafo = new Paragraph("Fecha: " + new Date().toString(), fuenteFecha);
        // Alinear el párrafo de la fecha a la derecha
        fechaParrafo.setAlignment(Element.ALIGN_LEFT);
        // Agregar la fecha al documento
        documento.add(fechaParrafo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n")); 

        // Crear una fuente para el documento del usuario en tamaño 12
        Font fuenteUsuario = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Crear un párrafo con el ID del cliente y la fuente especificada
        Paragraph usuarioParrafo = new Paragraph("Documento del Usuario que realizo la venta: " + idUsuario, fuenteUsuario);
        // Alinear el párrafo del cliente a la derecha
        usuarioParrafo.setAlignment(Element.ALIGN_LEFT);
        // Agregar el párrafo del cliente al documento
        documento.add(usuarioParrafo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n")); 
        
        // Crear una fuente para el documento del cliente en tamaño 12
        Font fuenteCliente = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Crear un párrafo con el ID del cliente y la fuente especificada
        Paragraph clienteParrafo = new Paragraph("Documento del Cliente: " + idCliente, fuenteCliente);
        // Alinear el párrafo del cliente a la derecha
        clienteParrafo.setAlignment(Element.ALIGN_LEFT);
        // Agregar el párrafo del cliente al documento
        documento.add(clienteParrafo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n")); 

        // Crear una fuente para los detalles en tamaño 12
        Font fuenteDetalle = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Agregar un párrafo con el título de los detalles de la compra
        documento.add(new Paragraph("\nDetalles de la Compra:\n"));

        // Obtener el modelo de datos de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        // Obtener el número de columnas en la tabla
        int columnas = modelo.getColumnCount();

        // Iterar sobre cada fila de la tabla
        for (int i = 0; i < modelo.getRowCount(); i++) {
            // Iterar sobre cada columna de la fila
            for (int j = 0; j < columnas; j++) {
                // Obtener el nombre de la columna y el valor de la celda
                String campo = modelo.getColumnName(j) + ":";
                String valor = modelo.getValueAt(i, j).toString();
                // Crear un párrafo para cada campo con el nombre de la columna y el valor
                Paragraph parrafo = new Paragraph(campo + " " + valor, fuenteDetalle);
                // Agregar el párrafo al documento
                documento.add(parrafo);
            }
            // Agregar un salto de línea para espaciar el contenido entre filas
            documento.add(new Paragraph("\n")); 
        }

        // Crear un párrafo con el total de la compra en negrita y tamaño 12
        Paragraph total = new Paragraph("\nTotal de la Compra: $" + totalCompra, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        // Alinear el párrafo del total a la derecha
        total.setAlignment(Element.ALIGN_LEFT);
        // Agregar el total al documento
        documento.add(total);

    } catch (FileNotFoundException | DocumentException e) {
        // Capturar y mostrar cualquier error ocurrido al crear el archivo PDF
        e.printStackTrace();
        System.err.println("Error al crear el archivo PDF: " + e.getMessage());
    } finally {
        // Cerrar el documento para finalizar la escritura
        documento.close();
    }
}


public void generarFacturaPDFReparacion(JTable tabla, String directorio, float totalCompra, int idCliente, int idUsuario, String descripcion, float manoObra) {
    // Crear una instancia del documento PDF
    Document documento = new Document();

    // Formatear la fecha actual en el formato "yyyy-MM-dd"
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(new Date());

    // Construir la ruta completa del archivo PDF
    String rutaArchivo = directorio + "\\Factura_" + fecha + "-" + idCliente + ".pdf";

    try {
        // Verificar si el directorio existe, si no, crearlo
        File directorioFile = new File(directorio);
        if (!directorioFile.exists()) {
            directorioFile.mkdirs();
        }

        // Crear un escritor PDF que escribirá el documento en la ruta especificada
        PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
        // Abrir el documento para agregar contenido
        documento.open();

        // Crear una fuente para el título en negrita y tamaño 20
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        // Crear un párrafo con el título y la fuente especificada
        Paragraph titulo = new Paragraph("Factura de Compra BIKE LIFE", fuenteTitulo);
        // Alinear el título al centro
        titulo.setAlignment(Element.ALIGN_CENTER);
        // Agregar el título al documento
        documento.add(titulo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n"));

        // Crear una fuente para la fecha en tamaño 12
        Font fuenteFecha = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Crear un párrafo con la fecha actual y la fuente especificada
        Paragraph fechaParrafo = new Paragraph("Fecha: " + new Date().toString(), fuenteFecha);
        // Alinear el párrafo de la fecha a la derecha
        fechaParrafo.setAlignment(Element.ALIGN_LEFT);
        // Agregar la fecha al documento
        documento.add(fechaParrafo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n"));

        // Crear una fuente para el documento del usuario en tamaño 12
        Font fuenteUsuario = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Crear un párrafo con el ID del cliente y la fuente especificada
        Paragraph usuarioParrafo = new Paragraph("Documento del Usuario que realizó la venta: " + idUsuario, fuenteUsuario);
        // Alinear el párrafo del cliente a la derecha
        usuarioParrafo.setAlignment(Element.ALIGN_LEFT);
        // Agregar el párrafo del cliente al documento
        documento.add(usuarioParrafo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n"));

        // Crear una fuente para el documento del cliente en tamaño 12
        Font fuenteCliente = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Crear un párrafo con el ID del cliente y la fuente especificada
        Paragraph clienteParrafo = new Paragraph("Documento del Cliente: " + idCliente, fuenteCliente);
        // Alinear el párrafo del cliente a la derecha
        clienteParrafo.setAlignment(Element.ALIGN_LEFT);
        // Agregar el párrafo del cliente al documento
        documento.add(clienteParrafo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n"));

        // Crear una fuente para los detalles en tamaño 12
        Font fuenteDetalle = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Agregar un párrafo con el título de los detalles de la compra
        documento.add(new Paragraph("\nDetalles de la Compra:\n"));

        // Obtener el modelo de datos de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        // Obtener el número de columnas en la tabla
        int columnas = modelo.getColumnCount();

        // Iterar sobre cada fila de la tabla
        for (int i = 0; i < modelo.getRowCount(); i++) {
            // Iterar sobre cada columna de la fila
            for (int j = 0; j < columnas; j++) {
                // Obtener el nombre de la columna y el valor de la celda
                String campo = modelo.getColumnName(j) + ":";
                String valor = modelo.getValueAt(i, j).toString();
                // Crear un párrafo para cada campo con el nombre de la columna y el valor
                Paragraph parrafo = new Paragraph(campo + " " + valor, fuenteDetalle);
                // Agregar el párrafo al documento
                documento.add(parrafo);
            }
            // Agregar un salto de línea para espaciar el contenido entre filas
            documento.add(new Paragraph("\n"));
        }

        // Crear una fuente para la descripción de la reparación en tamaño 12
        Font fuenteDescripcion = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Verificar si la descripción es nula o está vacía y proporcionar un valor predeterminado si es necesario
        String textoDescripcion = (descripcion != null && !descripcion.trim().isEmpty()) ? descripcion.trim() : "No hay descripción proporcionada.";

        // Crear un párrafo con la descripción de la reparación y la fuente especificada
        Paragraph descripcionParrafo = new Paragraph("Descripción de reparación: \n" + textoDescripcion, fuenteDescripcion);

        // Alinear el párrafo a la izquierda
        descripcionParrafo.setAlignment(Element.ALIGN_LEFT);

        // Agregar el párrafo de la descripción al documento
        documento.add(descripcionParrafo);

        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n"));

        // Crear una fuente para el valor de la mano de obra en tamaño 12
        Font fuenteManoObra = FontFactory.getFont(FontFactory.HELVETICA, 12);
        // Crear un párrafo con el valor de la mano de obra y la fuente especificada
        Paragraph ManoObraParrafo = new Paragraph("Mano de obra: $" + manoObra, fuenteManoObra);
        // Alinear el párrafo a la izquierda
        ManoObraParrafo.setAlignment(Element.ALIGN_LEFT);
        // Agregar el párrafo al documento
        documento.add(ManoObraParrafo);
        // Agregar un salto de línea para espaciar el contenido
        documento.add(new Paragraph("\n"));

        // Crear un párrafo con el total de la compra en negrita y tamaño 12
        Paragraph totalProductos = new Paragraph("\nTotal de la Compra Productos: $" + totalCompra, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        // Alinear el párrafo del total a la derecha
        totalProductos.setAlignment(Element.ALIGN_LEFT);
        // Agregar el total al documento
        documento.add(totalProductos);

        // Crear un párrafo con el total de la mano de obra en negrita y tamaño 12
        Paragraph totalManoObra = new Paragraph("\nTotal de la Mano de obra: $" + manoObra, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        // Alinear el párrafo del total a la derecha
        totalManoObra.setAlignment(Element.ALIGN_LEFT);
        // Agregar el total al documento
        documento.add(totalManoObra);

        float totalFin = manoObra + totalCompra;
        // Crear un párrafo con el total a pagar en negrita y tamaño 12
        Paragraph totalFinal = new Paragraph("\nTotal a pagar: $" + totalFin, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        // Alinear el párrafo del total a la derecha
        totalFinal.setAlignment(Element.ALIGN_LEFT);
        // Agregar el total al documento
        documento.add(totalFinal);

        System.out.println("Factura PDF generada correctamente en: " + rutaArchivo);

    } catch (FileNotFoundException | DocumentException e) {
        // Capturar y mostrar cualquier error ocurrido al crear el archivo PDF
        e.printStackTrace();
        System.err.println("Error al crear el archivo PDF: " + e.getMessage());
    } finally {
        // Cerrar el documento para finalizar la escritura
        documento.close();
    }
}

}
