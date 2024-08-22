package Metodo;

import com.itextpdf.text.DocumentException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class metodoReporte {

    // Formato de fecha para convertir cadenas en objetos Date
private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

public boolean agregarReporte(String fechaInicioReporteStr, String fechaFinReporteStr, int usuarioGenerador, String tipoReporte, String rutaArchivo, JFrame frameActual) throws ParseException {
    conexionBD cx = new conexionBD();
    Connection conexion = cx.conectar();

    if (conexion == null) {
        JOptionPane.showMessageDialog(frameActual, "Error al conectar a la base de datos.");
        return false;
    }

    // Convertir las cadenas de fecha a objetos Date
    java.sql.Date fechaInicioReporte = convertirStringAFecha(fechaInicioReporteStr);
    java.sql.Date fechaFinReporte = convertirStringAFecha(fechaFinReporteStr);

    if (fechaInicioReporte == null || fechaFinReporte == null) {
        JOptionPane.showMessageDialog(frameActual, "Las fechas no tienen el formato correcto. Deben ser YYYY/MM/DD.");
        return false;
    }

    // Insertar el reporte en la base de datos
    String sqlInsertar = "INSERT INTO reportes (fechaReporte, fechaInicioReporte, fechaFinReporte, usuarioGenerador, tipoReporte) " +
                         "VALUES (CURRENT_DATE, ?, ?, ?, ?)";

    try (var psInsertar = conexion.prepareStatement(sqlInsertar)) {
        psInsertar.setDate(1, fechaInicioReporte);
        psInsertar.setDate(2, fechaFinReporte);
        psInsertar.setInt(3, usuarioGenerador);
        psInsertar.setString(4, tipoReporte);

        int filasAfectadas = psInsertar.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(frameActual, "Reporte generado satisfactoriamente");
            // Generar el PDF basado en el tipo de reporte
            generarReportePDF(tipoReporte, fechaInicioReporte, fechaFinReporte, usuarioGenerador, rutaArchivo);
            return true;
        } else {
            JOptionPane.showMessageDialog(frameActual, "No se pudo crear el reporte.");
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(frameActual, "Error al agregar el reporte: " + e.getMessage());
        return false;
    } finally {
        cx.desconectar();
    }
}

public void generarReportePDF(String tipoReporte, java.sql.Date fechaInicio, java.sql.Date fechaFin, int usuarioGenerador, String rutaDirectorio) {
    // Crear una instancia del documento PDF
    Document documento = new Document();

    // Formato para la fecha en el nombre del archivo
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fecha = sdf.format(new java.util.Date());
    String nombreArchivo = "Reporte_" + fecha + "_" + usuarioGenerador + ".pdf";
        
    // Ruta completa del archivo
    String rutaArchivo = rutaDirectorio + File.separator + nombreArchivo;

    // Verificar si el archivo ya existe, y agregar un contador si es necesario
    File archivo = new File(rutaArchivo);
    int contador = 1;
    while (archivo.exists()) {
        nombreArchivo = "Reporte_" + fecha + "_" + usuarioGenerador + "_" + tipoReporte + "_" + contador + ".pdf";
        rutaArchivo = rutaDirectorio + File.separator + nombreArchivo;
        archivo = new File(rutaArchivo);
        contador++;
    }

    try {
        PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
        documento.open();

        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph titulo = new Paragraph("Reporte de " + tipoReporte, fuenteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        documento.add(titulo);
        documento.add(new Paragraph("\n"));

        Font fuenteFecha = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Paragraph fechaParrafo = new Paragraph("Fecha: " + new java.util.Date().toString(), fuenteFecha);
        fechaParrafo.setAlignment(Element.ALIGN_LEFT);
        documento.add(fechaParrafo);
        documento.add(new Paragraph("\n"));
        
        Font fuenteUsuarioCabecera = FontFactory.getFont(FontFactory.HELVETICA, 12);
        Paragraph UsuarioCabecera = new Paragraph("Usuario que realizó el reporte: " + usuarioGenerador, fuenteUsuarioCabecera);
        fechaParrafo.setAlignment(Element.ALIGN_LEFT);
        documento.add(UsuarioCabecera);
        documento.add(new Paragraph("\n"));

        // Agregar detalles del reporte según el tipo
        agregarDetallesReporte(documento, tipoReporte, fechaInicio, fechaFin);

        documento.close();
        System.out.println("Reporte PDF generado correctamente en: " + rutaArchivo);

    } catch (FileNotFoundException | DocumentException e) {
        e.printStackTrace();
        System.err.println("Error al crear el archivo PDF: " + e.getMessage());
    }
}

private void agregarDetallesReporte(Document documento, String tipoReporte, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws DocumentException {
    // Definir la fuente que se usará en el reporte
    Font fuenteDetalle = FontFactory.getFont(FontFactory.HELVETICA, 12);
    Font fuenteTabla = FontFactory.getFont(FontFactory.HELVETICA, 10);
    PdfPTable tabla;  // Crear una tabla PDF para mostrar los datos
    String sqlConsulta;  // Variable para almacenar la consulta SQL que se ejecutará

    // Condición para verificar si el reporte es de "VENTA"
    if ("VENTA".equalsIgnoreCase(tipoReporte)) {
        // Crear una tabla con 3 columnas para los reportes de ventas
        tabla = new PdfPTable(3);
        tabla.addCell(new Paragraph("ID Usuario", fuenteTabla));  // Encabezado de la columna ID Usuario
        tabla.addCell(new Paragraph("Cantidad de Ventas", fuenteTabla));  // Encabezado de la columna Cantidad de Ventas
        tabla.addCell(new Paragraph("Total Vendido", fuenteTabla));  // Encabezado de la columna Total Vendido

        // Consulta SQL para obtener la cantidad de ventas y el total vendido por usuario
        sqlConsulta = """
                        SELECT u.identificacion_PK AS usuarioGenerador, 
                          COUNT(p.id_pedido_PK) AS cantidadVentas, 
                          SUM(p.total) AS totalVendido
                          FROM usuarios u
                          JOIN pedidos p ON u.identificacion_PK = p.id_usuario_FK
                          WHERE p.fecha_pedido BETWEEN ? AND ? 
                          AND p.tipo_pedido = 'venta'
                          AND u.estado = 'ACTIVO'
                          GROUP BY u.identificacion_PK;""";

    } else if ("REPARACION".equalsIgnoreCase(tipoReporte)) {
        // Crear una tabla con 5 columnas para los reportes de reparaciones
        tabla = new PdfPTable(5);
        tabla.addCell(new Paragraph("ID Usuario", fuenteTabla));  // Encabezado de la columna ID Usuario
        tabla.addCell(new Paragraph("Cantidad de Reparaciones", fuenteTabla));  // Encabezado de la columna Cantidad de Reparaciones
        tabla.addCell(new Paragraph("Total de Ventas", fuenteTabla));  // Encabezado de la columna Total Reparaciones
        tabla.addCell(new Paragraph("Mano de Obra Total", fuenteTabla));  // Encabezado de la columna Mano de Obra Total
        tabla.addCell(new Paragraph("Total Pagado", fuenteTabla));  // Encabezado de la columna Total Pagado

        // Consulta SQL para obtener la cantidad de reparaciones, total de reparaciones, mano de obra y total pagado por usuario
        sqlConsulta = """
                      SELECT p.id_usuario_FK AS usuarioGenerador, 
                             COUNT(*) AS cantidadReparaciones, 
                             SUM(p.total) AS totalReparacion, 
                             SUM(p.mano_obra) AS manoObraTotal,
                             SUM(p.total + p.mano_obra) AS totalPagado
                      FROM pedidos p
                      JOIN usuarios u ON p.id_usuario_FK = u.identificacion_PK
                      WHERE p.fecha_pedido BETWEEN ? AND ?
                        AND p.tipo_pedido = 'reparacion'
                        AND u.estado = 'ACTIVO'
                      GROUP BY p.id_usuario_FK;""";

    } else {
        // Mostrar un mensaje de error si el tipo de reporte no es válido
        JOptionPane.showMessageDialog(null, "Tipo de reporte no válido.");
        return;  // Salir del método si el tipo de reporte no es válido
    }

    // Establecer la conexión a la base de datos
    conexionBD cx = new conexionBD();
    Connection conexion = cx.conectar();

    try (var ps = conexion.prepareStatement(sqlConsulta)) {
        // Asignar las fechas de inicio y fin a la consulta SQL
        ps.setDate(1, fechaInicio);
        ps.setDate(2, fechaFin);

        // Ejecutar la consulta SQL y obtener los resultados
        ResultSet rs = ps.executeQuery();

        // Iterar sobre los resultados y agregar cada fila a la tabla PDF
        while (rs.next()) {
            tabla.addCell(new Paragraph(rs.getString("usuarioGenerador"), fuenteTabla));  // Agregar el ID del usuario a la tabla

            if ("VENTA".equalsIgnoreCase(tipoReporte)) {
                tabla.addCell(new Paragraph(rs.getString("cantidadVentas"), fuenteTabla));  // Agregar la cantidad de ventas a la tabla
                tabla.addCell(new Paragraph(rs.getString("totalVendido"), fuenteTabla));  // Agregar el total vendido a la tabla
            } else if ("REPARACION".equalsIgnoreCase(tipoReporte)) {
                tabla.addCell(new Paragraph(rs.getString("cantidadReparaciones"), fuenteTabla));  // Agregar la cantidad de reparaciones a la tabla
                tabla.addCell(new Paragraph(rs.getString("totalReparacion"), fuenteTabla));  // Agregar el total de reparaciones a la tabla
                tabla.addCell(new Paragraph(rs.getString("manoObraTotal"), fuenteTabla));  // Agregar el total de mano de obra a la tabla
                tabla.addCell(new Paragraph(rs.getString("totalPagado"), fuenteTabla));  // Agregar el total pagado (mano de obra + total) a la tabla
            }
        }

        // Agregar la tabla de detalles al documento PDF
        documento.add(new Paragraph("Detalles de " + tipoReporte + ":", fuenteDetalle));
        documento.add(new Paragraph("\n"));  // Agregar un espacio antes de la tabla
        documento.add(tabla);  // Agregar la tabla al documento

    } catch (SQLException e) {
        // Manejar cualquier error SQL que ocurra durante la ejecución
        e.printStackTrace();
    } finally {
        // Desconectar de la base de datos
        cx.desconectar();
    }
}



private java.sql.Date convertirStringAFecha(String fechaStr) throws ParseException {
    // Parsear la cadena a un objeto java.util.Date
    java.util.Date parsedDate = DATE_FORMAT.parse(fechaStr);
    // Convertir java.util.Date a java.sql.Date
    return new java.sql.Date(parsedDate.getTime());
}

    
   public String[][] obtenerReportesMatriz(String tipoReporte) {
    conexionBD cx = new conexionBD();
    Connection conexion = cx.conectar();

    // Consulta SQL para obtener los datos de la tabla reportes, filtrando por tipo de reporte
    String sql = "SELECT fechaReporte, fechaInicioReporte, fechaFinReporte, usuarioGenerador, tipoReporte " +
                 "FROM reportes WHERE tipoReporte = ?";

    List<String[]> datosLista = new ArrayList<>();

    try (var ps = conexion.prepareStatement(sql)) { // Usar try-with-resources para PreparedStatement
        ps.setString(1, tipoReporte); // Establecer el parámetro tipoReporte

        try (var res = ps.executeQuery()) { // Usar try-with-resources para ResultSet
            while (res.next()) {
                String[] datos = new String[5]; // Actualizado a 5 campos
                datos[0] = res.getString(1); // fechaReporte
                datos[1] = res.getString(2); // fechaInicioReporte
                datos[2] = res.getString(3); // fechaFinReporte
                datos[3] = res.getString(4); // usuarioGenerador
                datos[4] = res.getString(5); // tipoReporte (venta o contraseña)

                datosLista.add(datos);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cx.desconectar();
    }

    String[][] datosMatriz = new String[datosLista.size()][5];

    for (int i = 0; i < datosLista.size(); i++) {
        datosMatriz[i] = datosLista.get(i);
    }

    return datosMatriz;
    }

}
