
package proyectoaso;

import javax.swing.*;


public class ModalMaskAdd extends JDialog {
    private int num_usuario;
    private int num_grupo;
    private int num_otros;
    private String itemSeleccionado;
    private String octal;
    public ModalMaskAdd(JDialog owner,String itemSeleccionado) {   
        super(owner,true);
        this.setLocationRelativeTo(null);
        initComponents();
        this.itemSeleccionado=itemSeleccionado;
        opcionSeleccionado.setText(itemSeleccionado);
    }
    
    private void initializeValues() {
        num_usuario = 0;
        num_grupo = 0;
        num_otros = 0;
    }
   
void OpcionesSeleccionadosUsuario() {
    if(u_r.isSelected()&&u_w.isSelected()&&u_x.isSelected()){
              num_usuario=7;
    }else if(u_r.isSelected()&&u_w.isSelected()){
              num_usuario=6;
    }else if(u_w.isSelected()&&u_x.isSelected()){
              num_usuario=3;
    }else if(u_r.isSelected()&&u_x.isSelected()){
              num_usuario=5;
    }else{
        if(u_r.isSelected()){
              num_usuario=4;
        }else if(u_w.isSelected()){
              num_usuario=2;
        }else if(u_x.isSelected()){
              num_usuario=1;
        }
    }
}
void OpcionesSeleccionadosGrupo() {
    if(g_r.isSelected()&&g_w.isSelected()&&g_x.isSelected()){
              num_grupo=7;
    }else if(g_r.isSelected()&&g_w.isSelected()){
              num_grupo=6;
    }else if(g_w.isSelected()&&g_x.isSelected()){
              num_grupo=3;
    }else if(g_r.isSelected()&&g_x.isSelected()){
              num_grupo=5;
    }else{
        if(g_r.isSelected()){
              num_grupo=4;
        }else if(g_w.isSelected()){
              num_grupo=2;
        }else if(g_x.isSelected()){
              num_grupo=1;
        }
    }
}
void OpcionesSeleccionadosOtros() {
    if(o_r.isSelected()&&o_w.isSelected()&&o_x.isSelected()){
              num_otros=7;
    }else if(o_r.isSelected()&&o_w.isSelected()){
              num_otros=6;
    }else if(o_w.isSelected()&&o_x.isSelected()){
              num_otros=3;
    }else if(o_r.isSelected()&&o_x.isSelected()){
              num_otros=5;
    }else{
        if(o_r.isSelected()){
              num_otros=4;
        }else if(o_w.isSelected()){
              num_otros=2;
        }else if(o_x.isSelected()){
              num_otros=1;
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        u_r = new javax.swing.JCheckBox();
        g_x = new javax.swing.JCheckBox();
        u_w = new javax.swing.JCheckBox();
        g_r = new javax.swing.JCheckBox();
        g_w = new javax.swing.JCheckBox();
        o_x = new javax.swing.JCheckBox();
        o_w = new javax.swing.JCheckBox();
        o_r = new javax.swing.JCheckBox();
        u_x = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        opcionSeleccionado = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        g_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                g_xActionPerformed(evt);
            }
        });

        o_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o_rActionPerformed(evt);
            }
        });

        jLabel1.setText(" x");

        jLabel2.setText(" r");

        jLabel3.setText(" w");

        jLabel4.setText("Grupo :");

        jLabel5.setText("Usuario :");

        jLabel6.setText("otros :");

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        opcionSeleccionado.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        opcionSeleccionado.setText("######");

        jLabel8.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel8.setText("Opcion Actual:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(g_r)
                                    .addComponent(o_r)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(u_r)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(g_w)
                                            .addComponent(o_w)
                                            .addComponent(u_w))
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(o_x, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(g_x))
                                            .addComponent(u_x)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 88, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(opcionSeleccionado)))
                .addGap(73, 73, 73))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jLabel5)
                    .addContainerGap(279, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(u_w)
                .addGap(18, 18, 18)
                .addComponent(g_w)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(o_w)
                    .addComponent(o_x))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opcionSeleccionado)
                            .addComponent(jLabel8))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(u_r)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(g_r))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(o_r))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(u_x)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(g_x)
                        .addGap(65, 65, 65)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(73, 73, 73)
                    .addComponent(jLabel5)
                    .addContainerGap(154, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void o_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o_rActionPerformed
    
    }//GEN-LAST:event_o_rActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.dispose();
    initializeValues();
    OpcionesSeleccionadosUsuario();
    OpcionesSeleccionadosGrupo();
    OpcionesSeleccionadosOtros();
    octal="0"+num_usuario+""+num_grupo+""+num_otros+"";
    ModalMaskInputAdd modal = new ModalMaskInputAdd(this,octal,itemSeleccionado);
    modal.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void g_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_g_xActionPerformed
      
    }//GEN-LAST:event_g_xActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox g_r;
    private javax.swing.JCheckBox g_w;
    private javax.swing.JCheckBox g_x;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox o_r;
    private javax.swing.JCheckBox o_w;
    private javax.swing.JCheckBox o_x;
    private javax.swing.JLabel opcionSeleccionado;
    private javax.swing.JCheckBox u_r;
    private javax.swing.JCheckBox u_w;
    private javax.swing.JCheckBox u_x;
    // End of variables declaration//GEN-END:variables
}
