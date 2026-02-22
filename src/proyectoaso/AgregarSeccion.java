
package proyectoaso;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class AgregarSeccion extends javax.swing.JFrame {
    private boolean radioPrinterSeleccion;
    private List<SambaConfigSection> secciones;
    private int pestaña;
    private int estadoReinicio;
    public AgregarSeccion(List<SambaConfigSection> secciones,int pestaña ,int estado) {
        this.pestaña=pestaña;
        this.estadoReinicio=estado;
        this.secciones=secciones;
        radioPrinterSeleccion=true;
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void seleccion(){
       if(printerSeleccion.isSelected()){
          inputRuta.setEnabled(false);
          buscarRuta.setEnabled(false);
          radioPrinterSeleccion=false;
          readonlySeleccion.setEnabled(false);
          inheritSeleccion.setEnabled(false);
       }else{
          radioPrinterSeleccion=true;
          inputRuta.setEnabled(true);
          buscarRuta.setEnabled(true);
          readonlySeleccion.setEnabled(true);
          inheritSeleccion.setEnabled(true);
       }
    }
    private boolean validarPath(){
        boolean res=true;// Reemplaza la cadena por el path que quieres verificar
        Path ruta = Paths.get(inputRuta.getText());

        if (Files.exists(ruta)) {
            res=true;
        } else {
            res=false;
        }
        return res;
    }
    
    private boolean validarDirectory(){
       boolean res=true;
       Path ruta = Paths.get(inputRuta.getText());
       if (Files.isDirectory(ruta)) {
            res=true;
        } else if (Files.isRegularFile(ruta)) {
            res=false;
        }       
       return res;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        inputNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        inputDescripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        printerSeleccion = new javax.swing.JRadioButton();
        directorySeleccion = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        inputRuta = new javax.swing.JTextField();
        readonlySeleccion = new javax.swing.JCheckBox();
        inheritSeleccion = new javax.swing.JCheckBox();
        buscarRuta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Ok_añadir = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Nombre");

        jLabel2.setText("Descripcion");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(inputNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                        .addComponent(inputDescripcion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(inputDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        grupo.add(printerSeleccion);
        printerSeleccion.setText("Printer");
        printerSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printerSeleccionActionPerformed(evt);
            }
        });

        grupo.add(directorySeleccion);
        directorySeleccion.setSelected(true);
        directorySeleccion.setText("Directory");
        directorySeleccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                directorySeleccionMouseClicked(evt);
            }
        });
        directorySeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directorySeleccionActionPerformed(evt);
            }
        });

        jLabel3.setText("Ruta");

        readonlySeleccion.setText("Read Only");

        inheritSeleccion.setSelected(true);
        inheritSeleccion.setText("Inherit ACLs");

        buscarRuta.setText("Buscar");
        buscarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarRutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(inputRuta, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(printerSeleccion)
                            .addComponent(directorySeleccion)
                            .addComponent(jLabel3)
                            .addComponent(readonlySeleccion)
                            .addComponent(inheritSeleccion))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printerSeleccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(directorySeleccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarRuta, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(readonlySeleccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inheritSeleccion)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel4.setText("Identificacion");

        jLabel5.setText("Tipo de acción");

        Ok_añadir.setText("Ok");
        Ok_añadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ok_añadirActionPerformed(evt);
            }
        });

        jButton2.setText("Atrás");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(236, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(Ok_añadir)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ok_añadir)
                    .addComponent(jButton2))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarRutaActionPerformed
       JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                inputRuta.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
    }//GEN-LAST:event_buscarRutaActionPerformed

    private void printerSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printerSeleccionActionPerformed
        inputRuta.setText("");
        seleccion();  
    }//GEN-LAST:event_printerSeleccionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       this.dispose();
       principal frame = new principal(new ArrayList<SambaConfigSection>(),pestaña,estadoReinicio);
       frame.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Ok_añadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ok_añadirActionPerformed
       validarPath();
  if (radioPrinterSeleccion) {
    if (!(inputNombre.getText().isEmpty()) && !(inputRuta.getText().isEmpty()) && validarPath() && validarDirectory()) {
        SambaConfigSection seccion = new SambaConfigSection(inputNombre.getText());
        seccion.addSetting("comment", inputDescripcion.getText());
        seccion.addSetting("path", inputRuta.getText());
        seccion.addSetting("read only", readonlySeleccion.isSelected() ? "Yes" : "No");
        seccion.addSetting("inherit acls", inheritSeleccion.isSelected() ? "Yes" : "No");
        secciones.add(seccion);
        dispose();
        principal frame = new principal(secciones, pestaña, estadoReinicio);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(this, "Configuración añadida", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        if (inputNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } else if (!validarPath()) {
            JOptionPane.showMessageDialog(this, "Ruta seleccionada no existe", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } else if (!validarDirectory()) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione la ruta de un directorio", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una ruta de directorio", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }
  } else {
    if (!(inputNombre.getText().isEmpty())) {
        SambaConfigSection seccion = new SambaConfigSection(inputNombre.getText());
        seccion.addSetting("path", "/var/tmp");
        seccion.addSetting("printable", "Yes");
        seccion.addSetting("comment", inputDescripcion.getText());
        secciones.add(seccion);
        dispose();
        principal frame = new principal(secciones, pestaña, estadoReinicio);
        frame.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre", "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }
  }               
    }//GEN-LAST:event_Ok_añadirActionPerformed

    private void directorySeleccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_directorySeleccionMouseClicked
        
    }//GEN-LAST:event_directorySeleccionMouseClicked

    private void directorySeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directorySeleccionActionPerformed
         seleccion();
    }//GEN-LAST:event_directorySeleccionActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ok_añadir;
    private javax.swing.JButton buscarRuta;
    private javax.swing.JRadioButton directorySeleccion;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JCheckBox inheritSeleccion;
    private javax.swing.JTextField inputDescripcion;
    private javax.swing.JTextField inputNombre;
    private javax.swing.JTextField inputRuta;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton printerSeleccion;
    private javax.swing.JCheckBox readonlySeleccion;
    // End of variables declaration//GEN-END:variables
}
