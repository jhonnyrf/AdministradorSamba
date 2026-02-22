
package proyectoaso;

import javax.swing.*;

public class ModalInputMaskEdit extends javax.swing.JDialog {
    private String octal;
    private String item_seleccionado;
    private boolean valido=false;

    public ModalInputMaskEdit(JDialog owner,String octal, String item_seleccionado) {
        super(owner,"", true);
        this.octal=octal;
        this.item_seleccionado=item_seleccionado;
        this.setLocationRelativeTo(null);
        initComponents();
        this.opcionSeleccionado.setText(item_seleccionado);
        this.inputMask.setText(octal);
    }
    
    public void ValidarIngresado(String numero){
       char primerDigito = numero.charAt(0);
       String numeroRestante=numero.substring(1, 4);
       try{
         int num = Integer.parseInt(numeroRestante);         
         int permiso_us=num/100;
         int permiso_gr=(num%100)/10;
         int permiso_ot=num%10;
         
         if(primerDigito!='0'){
            valido=false;
            JOptionPane.showMessageDialog(this, "valor ingresado incorrecto");
         }else{
             valido=true;
         }
         if(permiso_us<0||permiso_us>7||permiso_gr<0||permiso_gr>7||permiso_ot<0||permiso_ot>7){
           if(permiso_us<0||permiso_us>7){
               valido=false;
           JOptionPane.showMessageDialog(this,"El digito que representa al permiso usuario parese no estar dentro del rango 0 a 7");
           }
          if(permiso_gr<0||permiso_gr>7){
              valido=false;
           JOptionPane.showMessageDialog(this,"El digito que representa al permiso grupo parese no estar dentro del rango 0 a 7");
          }
          if(permiso_ot<0||permiso_ot>7){
           valido=false;
           JOptionPane.showMessageDialog(this,"El digito que representa al permiso otros parese no estar dentro del rango 0 a 7");
           
          }
        }else{
           valido=true;
         }
       }catch(Exception e){
            valido=false;
           JOptionPane.showMessageDialog(this,"Numero ingresado incorrecto");
       }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputMask = new javax.swing.JTextField();
        opcionSeleccionado = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel1.setText("Opcion Actual:");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel2.setText("Mask");

        inputMask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputMaskActionPerformed(evt);
            }
        });
        inputMask.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputMaskKeyTyped(evt);
            }
        });

        opcionSeleccionado.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        opcionSeleccionado.setText("#########");

        ok.setText("Ok");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        Cancelar.setText("Cancel");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(opcionSeleccionado))
                    .addComponent(inputMask, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(opcionSeleccionado))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(21, 21, 21)
                .addComponent(inputMask, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok)
                    .addComponent(Cancelar))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputMaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputMaskActionPerformed

    }//GEN-LAST:event_inputMaskActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
       ValidarIngresado(inputMask.getText());
       if(valido){
        this.dispose();
        String[] res = {item_seleccionado,inputMask.getText()};
        EditarSeccion frame = (EditarSeccion)getOwner().getOwner();
        frame.resultado(res);
        setVisible(false);
       }
       
    }//GEN-LAST:event_okActionPerformed

    private void inputMaskKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputMaskKeyTyped
        if (inputMask.getText().length() >= 4) {
         evt.consume();
        }
    }//GEN-LAST:event_inputMaskKeyTyped

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
      this.dispose();
    }//GEN-LAST:event_CancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JTextField inputMask;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton ok;
    private javax.swing.JLabel opcionSeleccionado;
    // End of variables declaration//GEN-END:variables
}
