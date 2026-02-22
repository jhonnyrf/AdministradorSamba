
package proyectoaso;

import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.*;
public class ModalAñadirParametro extends javax.swing.JDialog {

    private ArrayList<String> parametros= new ArrayList<String>();
    private String itemSeleccionado;
    public ModalAñadirParametro(Frame owner) {
        super(owner,true);
        this.setLocationRelativeTo(null);
        initComponents();
        llenarOpcionParametros();
    }
    
    public void llenarOpciones(ArrayList<String> opciones){
       this.parametros=opciones;
       llenarOpcionParametros();
       this.setVisible(true);
    }
    public void llenarOpcionParametros(){
        for (String parametro : parametros) {
            opcionesParametro.addItem(parametro.trim());
        }
    }
    private boolean isInArray(String item, String[] array) {
        for (String element : array) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcionesParametro = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        opcionesParametro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        opcionesParametro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcionesParametroMouseClicked(evt);
            }
        });

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ok");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(26, 26, 26)
                        .addComponent(jButton1))
                    .addComponent(opcionesParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(opcionesParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        itemSeleccionado = (String) opcionesParametro.getSelectedItem();
        String[] seleccionMask = {"directory mask", "security mask", "create mask"};
        String[] seleccionUsuarios = {"write list", "admin users", "valid users"};
        
        if (isInArray(itemSeleccionado, seleccionMask)) {
            this.dispose();
            ModalMaskAdd  modal = new ModalMaskAdd(this, itemSeleccionado);
            modal.setVisible(true);
        } else if (isInArray(itemSeleccionado, seleccionUsuarios)) {
            this.dispose();
            ModalUsuarioAdd mod = new ModalUsuarioAdd(this, itemSeleccionado);
            mod.setVisible(true);
        } else {
            this.dispose();
            ModalBinarioAdd mod = new ModalBinarioAdd(this, itemSeleccionado);
            mod.setVisible(true);
        }
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void opcionesParametroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcionesParametroMouseClicked
       
    }//GEN-LAST:event_opcionesParametroMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     dispose();     
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> opcionesParametro;
    // End of variables declaration//GEN-END:variables
}
