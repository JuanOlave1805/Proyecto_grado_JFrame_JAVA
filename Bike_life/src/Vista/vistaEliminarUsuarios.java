/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.ctrlUsuario;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JUAN DAVID
 */
public class vistaEliminarUsuarios extends javax.swing.JFrame {

    /**
     * Creates new form vistaEliminarUsuarios
     */
    public vistaEliminarUsuarios() {
        initComponents();
        
        setSize(1300, 700);//Tamaño de la ventana 
        
        this.setLocationRelativeTo(this);//Centra la ventana en la mitad de la pantalla
        this.setResizable(false);// Se deshabilita el Botón Max del Form
        
        SetImageLabel(Image_logo, "src/images/usuario.png");//Imagen del logo
        
        // Instancio el método de validación de usuario
        ctrlUsuario obtener = new ctrlUsuario();
        
        // Instancia el controlador de usuarios
        obtener = new ctrlUsuario();

        // Carga los datos en la tabla
        String[][] datosMatriz = obtener.obtenerDatos();
        cargarTabla(datosMatriz);
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
        jPanel1 = new javax.swing.JPanel();
        botonCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_usuarios = new javax.swing.JTable();
        botonConfirmar = new javax.swing.JButton();
        comboBoxRol = new javax.swing.JComboBox<>();
        textIdentificacion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Image_logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textEdad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textCorreo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textContrasena = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setBackground(new java.awt.Color(2, 2, 2));
        fondo.setPreferredSize(new java.awt.Dimension(1300, 700));
        fondo.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        botonCancelar.setBackground(new java.awt.Color(255, 153, 153));
        botonCancelar.setText("Cancelar");
        botonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        tabla_usuarios.setAutoCreateRowSorter(true);
        tabla_usuarios.setBackground(new java.awt.Color(204, 204, 204));
        tabla_usuarios.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tabla_usuarios.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tabla_usuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_usuarios.setRowHeight(30);
        tabla_usuarios.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tabla_usuariosComponentAdded(evt);
            }
        });
        tabla_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_usuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_usuarios);

        botonConfirmar.setBackground(new java.awt.Color(102, 204, 255));
        botonConfirmar.setText("Confirmar ");
        botonConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarActionPerformed(evt);
            }
        });

        comboBoxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Vendedor" }));

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

        jLabel2.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel2.setText("N° identificación");

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

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel4.setText("Nombre");

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

        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel5.setText("Apellido");

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel6.setText("Rol");

        Image_logo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        jLabel1.setText("Eliminar Usuario");

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

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel7.setText("Edad");

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel8.setText("Correo");

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

        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel9.setText("Contraseña");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboBoxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(Image_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Image_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        fondo.add(jPanel1);
        jPanel1.setBounds(20, 20, 1250, 620);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // TODO add your handling code here:
        this.cancelarEliminacion();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void tabla_usuariosComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabla_usuariosComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_usuariosComponentAdded

    private void tabla_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_usuariosMouseClicked
        // TODO add your handling code here:
        ctrlUsuario controlador = new ctrlUsuario();

        controlador.seleccionUsuario(tabla_usuarios, textIdentificacion, textNombre, textApellido, textEdad, textCorreo, textContrasena);
    }//GEN-LAST:event_tabla_usuariosMouseClicked

    private void botonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarActionPerformed
        // TODO add your handling code here:
        this.metodoConfirmarEliminacion();
    }//GEN-LAST:event_botonConfirmarActionPerformed

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

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNombreActionPerformed

    private void textApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textApellidoActionPerformed

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

    private void textCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCorreoActionPerformed

    private void textContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textContrasenaActionPerformed

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped
        // TODO add your handling code here:
        if (textNombre.getText().length() >= 100){
            evt.consume();
        }
    }//GEN-LAST:event_textNombreKeyTyped

    private void textApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textApellidoKeyTyped
        // TODO add your handling code here:
        if (textApellido.getText().length() >= 100){
            evt.consume();
        }
    }//GEN-LAST:event_textApellidoKeyTyped

    private void textCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCorreoKeyTyped
        // TODO add your handling code here:
        if (textCorreo.getText().length() >= 100){
            evt.consume();
        }
    }//GEN-LAST:event_textCorreoKeyTyped

    private void textContrasenaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textContrasenaKeyTyped
        // TODO add your handling code here:
        if (textContrasena.getText().length() >= 100){
            evt.consume();
        }
    }//GEN-LAST:event_textContrasenaKeyTyped

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
            java.util.logging.Logger.getLogger(vistaEliminarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaEliminarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaEliminarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaEliminarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaEliminarUsuarios().setVisible(true);
            }
        });
    }

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
    
    private void cargarTabla(String[][] datosMatriz) {
        // Definir nombres de columnas
        String[] nombresColumnas = {"Identificación", "Nombre", "Apellido", "Edad", "Correo",  "Contraseña", "Rol"};

        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(datosMatriz, nombresColumnas);

        // Establecer el modelo en la tabla
        tabla_usuarios.setModel(model);
        
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(model);
        tabla_usuarios.setRowSorter(ordenarTabla);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Image_logo;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JComboBox<String> comboBoxRol;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_usuarios;
    private javax.swing.JTextField textApellido;
    private javax.swing.JTextField textContrasena;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textEdad;
    private javax.swing.JTextField textIdentificacion;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables

    private void cancelarEliminacion() {
        //Cambio a la vista de inicio
        vistaUsuariosAdmin ventas = new vistaUsuariosAdmin();  
        ventas.setVisible(true);
        this.setVisible(false);
    }

    private void metodoConfirmarEliminacion() {
        String identificacionStr = textIdentificacion.getText();
        int identificacion;
        try {
            identificacion = Integer.parseInt(identificacionStr);
            // Verificar que la identificación sea mayor que cero
            if (identificacion <= 0) {
                JOptionPane.showMessageDialog(this, "La identificación debe ser mayor que cero.");
                return; // Salir del método si la identificación no es válida
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La identificación debe ser un número entero válido.");
            return; // Salir del método si la conversión falla
        }
        ctrlUsuario controlador = new ctrlUsuario();
        controlador.eliminarUsuario(identificacion);
        if (controlador.eliminarUsuario(identificacion)) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.");
            //Cambio a la vista de inicio
            vistaUsuariosAdmin ventas = new vistaUsuariosAdmin();  
            ventas.setVisible(true);
            this.setVisible(false);
            
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el usuario.");
        }
    }
}
