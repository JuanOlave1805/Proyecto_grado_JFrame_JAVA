/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VistaCategoria;

import Metodo.metodoCategoria;
import Objetos.Categoria;
import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author JUAN DAVID
 */
public class CategoriaProductoActualizarAdmin extends javax.swing.JFrame {

    /**
     * Creates new form vistaEditarCategoriaProductoAdmin
     */
    public CategoriaProductoActualizarAdmin() {
        initComponents();
        
        setSize(597, 530);//Tamaño de la ventana 
        
        this.setLocationRelativeTo(this);//Centra la ventana en la mitad de la pantalla
        this.setResizable(false);// Se deshabilita el Botón Max del Form
        
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
        jPanel1 = new javax.swing.JPanel();
        botonCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_categoria = new javax.swing.JTable();
        botonConfirmar = new javax.swing.JButton();
        textNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textid = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textIdUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

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
        tabla_categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_categoriaMouseClicked(evt);
            }
        });
        tabla_categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tabla_categoriaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_categoria);

        botonConfirmar.setBackground(new java.awt.Color(102, 204, 255));
        botonConfirmar.setText("Confirmar");
        botonConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarActionPerformed(evt);
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

        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel4.setText("Nombre");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 37)); // NOI18N
        jLabel1.setText("Actualizar Categoria");

        textid.setEnabled(false);
        textid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textidActionPerformed(evt);
            }
        });
        textid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textidKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel6.setText("Id");

        textIdUsuario.setEnabled(false);
        textIdUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdUsuarioActionPerformed(evt);
            }
        });
        textIdUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textIdUsuarioKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel7.setText("ID User");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(26, 26, 26)
                                .addComponent(textIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textid, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        fondo.add(jPanel1);
        jPanel1.setBounds(30, 20, 510, 460);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        // TODO add your handling code here:
        this.cancelarModificacion();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void tabla_categoriaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabla_categoriaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_categoriaComponentAdded

    private void tabla_categoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_categoriaMouseClicked
        // TODO add your handling code here:
        metodoCategoria metodo = new metodoCategoria();
        Categoria objeto = new Categoria();
        metodo.seleccionCategoria(tabla_categoria, objeto);
        textid.setText(String.valueOf(objeto.getIdentificador_Pk()));
        textNombre.setText(objeto.getnombre());
        
        
    }//GEN-LAST:event_tabla_categoriaMouseClicked

    private void tabla_categoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_categoriaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_categoriaKeyTyped

    private void botonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarActionPerformed
        // TODO add your handling code here:
        this.metodoBtnConfirmar();
    }//GEN-LAST:event_botonConfirmarActionPerformed

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

    private void textidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textidActionPerformed

    private void textidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_textidKeyTyped

    private void textIdUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIdUsuarioActionPerformed

    private void textIdUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textIdUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_textIdUsuarioKeyTyped

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
            java.util.logging.Logger.getLogger(CategoriaProductoActualizarAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoriaProductoActualizarAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoriaProductoActualizarAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoriaProductoActualizarAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoriaProductoActualizarAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_categoria;
    private javax.swing.JTextField textIdUsuario;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textid;
    // End of variables declaration//GEN-END:variables

    private void cargarTabla() {
        // Instancio el método de validación de usuario
        metodoCategoria metodo = new metodoCategoria();

        // Carga los datos en la tabla
        String[][] datosMatriz = metodo.obtenerCategorias();
        
        // Definir nombres de columnas
        String[] nombresColumnas = {"Id", "Nombre"};

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

    private void metodoBtnConfirmar() {
    Categoria categoriaObj = new Categoria();
    
    // Obtener valores de los campos
    String idStr = textid.getText();
    
    // Verifica si los campos no están vacíos
    if (idStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Los campos de identificación y RIN no pueden estar vacíos.");
        return;
    }

    int id;
    
    try {
        // Convierte las cadenas a enteros
        id = Integer.parseInt(idStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Formato incorrecto para identificación o RIN: " + e.getMessage());
        return;
    }

    // Asigna valores a los atributos del objeto categoria
    categoriaObj.setIdentificador_Pk(id);
    categoriaObj.setnombre(textNombre.getText().toUpperCase());

    // Instanciar el método y ejecutar el método para modificar el usuario
    metodoCategoria metodo = new metodoCategoria();
    boolean exito = metodo.actualizarCategoria(categoriaObj);

    if (exito = true) {
        JOptionPane.showMessageDialog(this, "Categoría modificada satisfactoriamente");
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Reparaciones
        CategoriaProductosAdmin ventana = new CategoriaProductosAdmin();
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.setVisible(true);

        ventana.rellenarIdUsuario(idUsuario);
        this.setVisible(false);
        
    } else {
        JOptionPane.showMessageDialog(this, "Error al modificar la categoría.");
    }
    }

    private void cancelarModificacion() {
        String idUsuarioString = textIdUsuario.getText();
        int idUsuario = parseInt(idUsuarioString);
        // Abrir la ventana de Reparaciones
        CategoriaProductosAdmin ventana = new CategoriaProductosAdmin();
        // Rellenar el campo textIdUsuario con la identificación del usuario
        ventana.rellenarIdUsuario(idUsuario);
        ventana.setVisible(true);
        this.setVisible(false);
    }
}
