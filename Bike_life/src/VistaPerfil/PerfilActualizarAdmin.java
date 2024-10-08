package VistaIngreso;
import VistaCategoria.CategoriaProductoEliminarAdmin;
import VistaCategoria.CategoriaProductoActualizarAdmin;
import VistaProductos.ProductosAdmin;
import VistaUsuarios.UsuariosAdmin;
import VistaVenta.VentaAdmin;
import Metodo.metodoUsuario;
import Objetos.Usuario;
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
import javax.swing.JOptionPane;

public class PerfilActualizarAdmin extends javax.swing.JFrame {
    public PerfilActualizarAdmin() throws SQLException {
        initComponents();
        
        setSize(1300, 700);//Tamaño de la ventana 
        
        this.setLocationRelativeTo(this);//Centra la ventana en la mitad de la pantalla
        this.setResizable(false);// Se deshabilita el Botón Max del Form
        
        SetImageLabel(Image_logo, "src/images/logo.png");//Imagen del logo
        SetImageLabel(Image_usuario, "src/images/usuario.png");//Imagen del User]
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
        Image_usuario = new javax.swing.JLabel();
        textIdentificacion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textEdad = new javax.swing.JTextField();
        textApellido = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        textCorreo = new javax.swing.JTextField();
        textTelefono = new javax.swing.JTextField();
        textContrasena = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Boton_agregar1 = new javax.swing.JButton();
        Boton_cancelar = new javax.swing.JButton();
        Boton_carga = new javax.swing.JButton();
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

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
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
        jLabel3.setText("Perfil");

        Image_usuario.setBackground(new java.awt.Color(255, 255, 255));

        textIdentificacion.setEnabled(false);
        textIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdentificacionActionPerformed(evt);
            }
        });
        textIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textIdentificacionKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 22)); // NOI18N
        jLabel2.setText("N° identificación");

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 22)); // NOI18N
        jLabel4.setText("Nombre");

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 22)); // NOI18N
        jLabel5.setText("Apellido");

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 22)); // NOI18N
        jLabel7.setText("Edad");

        textEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textEdadActionPerformed(evt);
            }
        });
        textEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textEdadKeyTyped(evt);
            }
        });

        textApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textApellidoActionPerformed(evt);
            }
        });
        textApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textApellidoKeyTyped(evt);
            }
        });

        textNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNombreActionPerformed(evt);
            }
        });
        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNombreKeyTyped(evt);
            }
        });

        textCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCorreoActionPerformed(evt);
            }
        });
        textCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textCorreoKeyTyped(evt);
            }
        });

        textTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTelefonoActionPerformed(evt);
            }
        });
        textTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textTelefonoKeyTyped(evt);
            }
        });

        textContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textContrasenaActionPerformed(evt);
            }
        });
        textContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textContrasenaKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 22)); // NOI18N
        jLabel9.setText("Contraseña");

        jLabel10.setFont(new java.awt.Font("Segoe UI Historic", 0, 22)); // NOI18N
        jLabel10.setText("Telefono");

        jLabel11.setFont(new java.awt.Font("Segoe UI Historic", 0, 22)); // NOI18N
        jLabel11.setText("Correo");

        Boton_agregar1.setBackground(new java.awt.Color(102, 204, 255));
        Boton_agregar1.setText("Confirmar cambios");
        Boton_agregar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_agregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_agregar1ActionPerformed(evt);
            }
        });

        Boton_cancelar.setBackground(new java.awt.Color(255, 153, 153));
        Boton_cancelar.setText("Cancelar");
        Boton_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_cancelarActionPerformed(evt);
            }
        });

        Boton_carga.setBackground(new java.awt.Color(153, 153, 255));
        Boton_carga.setText("Cargar datos");
        Boton_carga.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_carga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_cargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(92, 92, 92)
                        .addComponent(Boton_carga, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Boton_agregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(Boton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textIdentificacion)
                                .addComponent(textEdad)
                                .addComponent(textApellido)
                                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(Image_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(Boton_carga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textNombre))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Boton_agregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Boton_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Image_usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(101, 101, 101))))
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

        Boton_usuarios.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void textIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIdentificacionActionPerformed

    private void textIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textIdentificacionKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numero = key >= 48 && key <= 57;

        if (!numero){
            evt.consume();
        }
        if (textIdentificacion.getText().length() >= 10){
            evt.consume();
        }
    }//GEN-LAST:event_textIdentificacionKeyTyped

    private void textEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textEdadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textEdadActionPerformed

    private void textEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEdadKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numero = key >= 48 && key <= 57;

        if (!numero){
            evt.consume();
        }
        if (textEdad.getText().length() >= 2){
            evt.consume();
        }
    }//GEN-LAST:event_textEdadKeyTyped

    private void textApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textApellidoActionPerformed

    private void textApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textApellidoKeyTyped
        // TODO add your handling code here:
        // Obtener el carácter que se ha escrito
        char c = evt.getKeyChar();

        // Verificar si el carácter es una letra o un espacio y si el texto tiene menos de 100 caracteres
        if (!Character.isLetter(c) && c != ' ' || textNombre.getText().length() >= 100) {
            evt.consume();  // Consumir el evento para evitar que el carácter se agregue al campo de texto
        }
    }//GEN-LAST:event_textApellidoKeyTyped

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNombreActionPerformed

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped
        // TODO add your handling code here:
        // Obtener el carácter que se ha escrito
        char c = evt.getKeyChar();

        // Verificar si el carácter es una letra o un espacio y si el texto tiene menos de 100 caracteres
        if (!Character.isLetter(c) && c != ' ' || textNombre.getText().length() >= 100) {
            evt.consume();  // Consumir el evento para evitar que el carácter se agregue al campo de texto
        }
    }//GEN-LAST:event_textNombreKeyTyped

    private void textCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCorreoActionPerformed

    private void textCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCorreoKeyTyped
        // TODO add your handling code here:
        if (textCorreo.getText().length() >= 100){
            evt.consume();
        }
    }//GEN-LAST:event_textCorreoKeyTyped

    private void textTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTelefonoActionPerformed

    private void textTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textTelefonoKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();

        boolean numero = key >= 48 && key <= 57;

        if (!numero){
            evt.consume();
        }
        if (textTelefono.getText().length() >= 10){
            evt.consume();
        }
    }//GEN-LAST:event_textTelefonoKeyTyped

    private void textContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textContrasenaActionPerformed

    private void textContrasenaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textContrasenaKeyTyped
        // TODO add your handling code here:
        if (textContrasena.getText().length() >= 100){
            evt.consume();
        }
    }//GEN-LAST:event_textContrasenaKeyTyped

    private void Boton_agregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_agregar1ActionPerformed
        try {
            // TODO add your handling code here:
            //Llamando al metodo //
            this.metodoAgregar();
        } catch (SQLException ex) {
            Logger.getLogger(PerfilActualizarAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Boton_agregar1ActionPerformed

    private void Boton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_cancelarActionPerformed
        try {
            // TODO add your handling code here:
            this.cancelar();
        } catch (SQLException ex) {
            Logger.getLogger(PerfilActualizarAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Boton_cancelarActionPerformed

    private void Boton_cargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_cargaActionPerformed
        try {
            // TODO add your handling code here:
            this.obtenerUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(PerfilAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Boton_cargaActionPerformed

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
            java.util.logging.Logger.getLogger(PerfilActualizarAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerfilActualizarAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerfilActualizarAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerfilActualizarAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                try {
                    new PerfilActualizarAdmin().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(PerfilActualizarAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_agregar1;
    private javax.swing.JButton Boton_cancelar;
    private javax.swing.JButton Boton_carga;
    private javax.swing.JButton Boton_inventario;
    private javax.swing.JButton Boton_proveedores;
    private javax.swing.JButton Boton_reparaciones;
    private javax.swing.JButton Boton_usuarios;
    private javax.swing.JButton Boton_ventas;
    private javax.swing.JLabel Image_logo;
    private javax.swing.JLabel Image_usuario;
    private javax.swing.JPanel fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textApellido;
    private javax.swing.JTextField textContrasena;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textEdad;
    private javax.swing.JTextField textIdUsuario;
    private javax.swing.JTextField textIdentificacion;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textTelefono;
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
    
    
    public void rellenarIdUsuario(int idUsuario) {
        textIdUsuario.setText(String.valueOf(idUsuario));
    }

    private void cancelar() throws SQLException {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Reparaciones
        PerfilAdmin ventana = new PerfilAdmin();
        ventana.setVisible(true);
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
    }
    
    public void metodo_boton_reparaciones() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Productos
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

    private void metodoAgregar() throws SQLException {
        String idUsuarioString = textIdUsuario.getText();
        
        Usuario usuarioObj = new Usuario();
        int idUsuario = parseInt(idUsuarioString);
        usuarioObj.setIdentificacion_Pk(idUsuario);
        usuarioObj.setNombre(textNombre.getText());
        usuarioObj.setApellido(textApellido.getText());
        usuarioObj.setEdad(Integer.parseInt(textEdad.getText()));
        usuarioObj.setTelefono(textTelefono.getText());
        usuarioObj.setCorreo(textCorreo.getText());
        usuarioObj.setContrasena(textContrasena.getText());
        
        
        
        metodoUsuario metodo = new metodoUsuario();
        metodo.actualizarUsuarioPerfil(usuarioObj);
        if(metodo.actualizarUsuarioPerfil(usuarioObj)){
            JOptionPane.showMessageDialog(null, "Perfil Actualizado exitosamente");
            PerfilAdmin ventana = new PerfilAdmin();
            ventana.setVisible(true);
            // Rellenar el campo textIdUsuario con la identificación del usuario
            ventana.rellenarIdUsuario(idUsuario);
            this.setVisible(false);
        }
    }
    
    private void obtenerUsuario() throws SQLException {
    String idUsuarioString = textIdUsuario.getText();
    int idUsuario = Integer.parseInt(idUsuarioString); // Corrección del método parseInt
    metodoUsuario metodo = new metodoUsuario();
    Usuario usuario = metodo.obtenerUsuarioPerfil(idUsuario); // Obtener el usuario desde el método

    if (usuario != null) {
        textIdUsuario.setText(String.valueOf(usuario.getIdentificacion_Pk()));
        textIdentificacion.setText(String.valueOf(usuario.getIdentificacion_Pk()));
        textNombre.setText(usuario.getNombre());
        textApellido.setText(usuario.getApellido());
        textEdad.setText(String.valueOf(usuario.getEdad()));
        textCorreo.setText(usuario.getCorreo());
        textTelefono.setText(usuario.getTelefono());
    } else {
        textIdUsuario.setText("");
        textIdentificacion.setText("");
        textNombre.setText("");
        textApellido.setText("");
        textEdad.setText("");
        textCorreo.setText("");
        textTelefono.setText("");
    }
    }
    
}
