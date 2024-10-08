package VistaCliente;
import VistaCategoria.*;
import VistaCategoria.CategoriaProductoEliminarAdmin;
import VistaCategoria.CategoriaProductoAgregarAdmin;
import VistaCategoria.CategoriaProductoActualizarAdmin;
import VistaProductos.ProductosAdmin;
import VistaUsuarios.UsuariosAdmin;
import VistaVenta.VentaAdmin;
import Metodo.metodoCategoria;
import Metodo.metodoCliente;
import VistaIngreso.PerfilAdmin;
import VistaProveedor.ProveedorAdmin;
import VistaReparaciones.ReparacionAdmin;
import java.awt.Image;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Clientes extends javax.swing.JFrame {
    public Clientes() {
        initComponents();
        
        setSize(1300, 700);//Tamaño de la ventana 
        
        this.setLocationRelativeTo(this);//Centra la ventana en la mitad de la pantalla
        this.setResizable(false);// Se deshabilita el Botón Max del Form
        
        SetImageLabel(Image_logo, "src/images/logo.png");//Imagen del logo
        
        this.cargarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        Image_logo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_categoria = new javax.swing.JTable();
        Boton_agregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textIdUsuario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Boton_usuarios = new javax.swing.JButton();
        Boton_inventario = new javax.swing.JButton();
        Boton_reparaciones = new javax.swing.JButton();
        Boton_ventas = new javax.swing.JButton();
        Boton_proveedores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(2, 2, 2));
        fondo.setPreferredSize(new java.awt.Dimension(1300, 700));
        fondo.setLayout(null);

        Image_logo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.add(Image_logo);
        Image_logo.setBounds(42, 14, 110, 110);

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton1.setText("Perfil");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        fondo.add(jButton1);
        jButton1.setBounds(30, 600, 130, 45);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel3.setText("Clientes");

        tabla_categoria.setAutoCreateRowSorter(true);
        tabla_categoria.setBackground(new java.awt.Color(204, 204, 204));
        tabla_categoria.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabla_categoria.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tabla_categoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_categoria.setRowHeight(30);
        tabla_categoria.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tabla_categoriaComponentAdded(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_categoria);

        Boton_agregar.setBackground(new java.awt.Color(102, 204, 255));
        Boton_agregar.setText("Agregar");
        Boton_agregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_agregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Boton_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Boton_agregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        fondo.add(jPanel1);
        jPanel1.setBounds(201, 101, 1050, 540);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 62)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BIKE LIFE");
        fondo.add(jLabel1);
        jLabel1.setBounds(200, 10, 317, 72);

        textIdUsuario.setBackground(new java.awt.Color(0, 0, 0));
        textIdUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textIdUsuario.setForeground(new java.awt.Color(255, 255, 255));
        textIdUsuario.setEnabled(false);
        fondo.add(textIdUsuario);
        textIdUsuario.setBounds(1130, 30, 117, 41);

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("ID Usuario");
        fondo.add(jLabel8);
        jLabel8.setBounds(941, 30, 150, 40);

        Boton_usuarios.setBackground(new java.awt.Color(0, 0, 0));
        Boton_usuarios.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Boton_usuarios.setForeground(new java.awt.Color(255, 255, 255));
        Boton_usuarios.setText("Usuarios");
        Boton_usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_usuariosActionPerformed(evt);
            }
        });
        fondo.add(Boton_usuarios);
        Boton_usuarios.setBounds(30, 320, 130, 45);

        Boton_inventario.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Boton_inventario.setText("Productos");
        Boton_inventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_inventarioActionPerformed(evt);
            }
        });
        fondo.add(Boton_inventario);
        Boton_inventario.setBounds(30, 260, 130, 45);

        Boton_reparaciones.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Boton_reparaciones.setText("Reparaciones");
        Boton_reparaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_reparaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_reparacionesActionPerformed(evt);
            }
        });
        fondo.add(Boton_reparaciones);
        Boton_reparaciones.setBounds(30, 200, 130, 45);

        Boton_ventas.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Boton_ventas.setText("Ventas");
        Boton_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_ventasActionPerformed(evt);
            }
        });
        fondo.add(Boton_ventas);
        Boton_ventas.setBounds(30, 140, 130, 45);

        Boton_proveedores.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        Boton_proveedores.setText("Proveedores");
        Boton_proveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_proveedoresActionPerformed(evt);
            }
        });
        fondo.add(Boton_proveedores);
        Boton_proveedores.setBounds(30, 380, 130, 45);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            this.BtnPerfil();
        } catch (SQLException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabla_categoriaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabla_categoriaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_categoriaComponentAdded

    private void Boton_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_agregarActionPerformed
        // TODO add your handling code here:
        this.metodoAgregar();
    }//GEN-LAST:event_Boton_agregarActionPerformed

    private void Boton_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_usuariosActionPerformed
        // TODO add your handling code here:
        //Llamando al metodo
        this.metodo_boton_usuario();
    }//GEN-LAST:event_Boton_usuariosActionPerformed

    private void Boton_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_inventarioActionPerformed
        // TODO add your handling code here:
        //Llamando al metodo //
        this.metodoBtnProductos();
    }//GEN-LAST:event_Boton_inventarioActionPerformed

    private void Boton_reparacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_reparacionesActionPerformed
        // TODO add your handling code here:
        //Llamando al metodo
        this.metodo_boton_reparaciones();
    }//GEN-LAST:event_Boton_reparacionesActionPerformed

    private void Boton_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_ventasActionPerformed
        // TODO add your handling code here:
        //Llamando al metodo//
        this.metodoBtnVentas();
    }//GEN-LAST:event_Boton_ventasActionPerformed

    private void Boton_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_proveedoresActionPerformed
        // TODO add your handling code here:
        this.metodoProveedor();
    }//GEN-LAST:event_Boton_proveedoresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_agregar;
    private javax.swing.JButton Boton_inventario;
    private javax.swing.JButton Boton_proveedores;
    private javax.swing.JButton Boton_reparaciones;
    private javax.swing.JButton Boton_usuarios;
    private javax.swing.JButton Boton_ventas;
    private javax.swing.JLabel Image_logo;
    private javax.swing.JPanel fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_categoria;
    private javax.swing.JTextField textIdUsuario;
    // End of variables declaration//GEN-END:variables
    
    //Metodo donde da el tamaño de la imagen dependiendo el contenedor que tenga
    private void SetImageLabel(JLabel labelName, String root){
        //Agregando imagen de logo//
        ImageIcon image = new ImageIcon(root);
        // Se crea un ImageIcon usando la ruta especificada en 'root'

        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        // Se escala la imagen para que se ajuste al tamaño del contenedor 'labelName'
        // 'labelName.getWidth()' y 'labelName.getHeight()' obtienen el ancho y alto del JLabel
        // 'Image.SCALE_DEFAULT' indica el método de escalamiento predeterminado

        labelName.setIcon(icon);
        // Se establece el ícono escalado como el ícono del JLabel 'labelName'

        this.repaint();
        // Se repinta el contenedor para reflejar los cambios visuales
    }
    
    private void cargarTabla() {
        // Instancio el método de validación de usuario
        metodoCliente metodo = new metodoCliente();

        // Carga los datos en la tabla
        String[][] datosMatriz = metodo.obtenerClientes();
        
        // Definir nombres de columnas
        String[] nombresColumnas = {"Documento", "Nombre", "Apellido", "Telefono"};

        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(datosMatriz, nombresColumnas);

        // Establecer el modelo en la tabla
        tabla_categoria.setModel(model);
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(model);
        tabla_categoria.setRowSorter(ordenarTabla);
    }
    
    public void rellenarIdUsuario(int idUsuario) {
        textIdUsuario.setText(String.valueOf(idUsuario));
    }
    
    private void metodo_boton_editar() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Reparaciones
        ClientesActualizar ventana = new ClientesActualizar();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }

    private void metodoAgregar() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Reparaciones
        ClientesAgregar ventana = new ClientesAgregar();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }
    
    private void BtnPerfil() throws SQLException {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Usuario
        PerfilAdmin ventana = new PerfilAdmin();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }

    public void metodo_boton_reparaciones() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Reparaciones
        ReparacionAdmin ventana = new ReparacionAdmin();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }
    
    private void metodoBtnProductos() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Productos
        ProductosAdmin ventana = new ProductosAdmin();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }
    
    private void metodo_boton_usuario() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Usuario
        UsuariosAdmin ventana = new UsuariosAdmin();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }

    private void metodoBtnVentas() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Ventas
        VentaAdmin ventana = new VentaAdmin();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }

    private void metodoProveedor() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Usuario
        ProveedorAdmin ventana = new ProveedorAdmin();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }
    

    
}
