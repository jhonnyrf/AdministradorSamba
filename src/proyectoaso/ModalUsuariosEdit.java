
package proyectoaso;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ModalUsuariosEdit extends javax.swing.JDialog {
    private String item_seleccionado;
    public ModalUsuariosEdit(JFrame owner,String item_seleccionado) {
        super(owner,true);
        this.item_seleccionado=item_seleccionado;
        initComponents();
        this.nombreSeleccionado.setText(item_seleccionado);
        llenarUsuarios();
    }
    
    public  void setUsuarios(String usuarios){
         this.inputUsuarios.setText(usuarios);
    }
    private static List<String> getSambaUsers() {
        List<String> userList = new ArrayList<>();
        
        try {
            // Ejecutar el comando "pdbedit -L" para obtener la lista de usuarios de Samba
            ProcessBuilder processBuilder = new ProcessBuilder("pdbedit", "-L");
            Process process = processBuilder.start();
            
            // Capturar la salida del comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // El formato de salida es "nombre_usuario:xxx:xxx:..."
                String[] parts = line.split(":");
                if (parts.length > 0) {
                    userList.add(parts[0]);
                }
            }
            
            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Error al ejecutar el comando: pdbedit -L");
                return null;
            }
            
            return userList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void llenarUsuarios(){
       List<String> usuarios= new ArrayList<>();
       usuarios=this.getSambaUsers();
       if(!usuarios.isEmpty()){
         for(String usuario:usuarios){
             this.opcionUsuarios.addItem(usuario);
         }
       }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        inputUsuarios = new javax.swing.JTextField();
        opcionUsuarios = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        nombreSeleccionado = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        opcionUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "usuarios" }));
        opcionUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcionUsuariosMouseClicked(evt);
            }
        });
        opcionUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionUsuariosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel1.setText("Opcion Actual");

        nombreSeleccionado.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        nombreSeleccionado.setText("##########");

        ok.setText("Ok");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombreSeleccionado))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(ok))
                            .addComponent(inputUsuarios)
                            .addComponent(opcionUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombreSeleccionado))
                .addGap(31, 31, 31)
                .addComponent(inputUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(opcionUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcionUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcionUsuariosMouseClicked

    }//GEN-LAST:event_opcionUsuariosMouseClicked

    private void opcionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionUsuariosActionPerformed
       
        if(!(opcionUsuarios.getSelectedItem().equals("usuarios"))){
          String seleccionado = (String)opcionUsuarios.getSelectedItem();
          if(!(inputUsuarios.getText().equals(""))){
            String escrito = inputUsuarios.getText()+","+seleccionado;
            this.inputUsuarios.setText(escrito);
          }else{
            this.inputUsuarios.setText(seleccionado);
          }
        }
    }//GEN-LAST:event_opcionUsuariosActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
      dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
       dispose();
       String valor_seleccionado=inputUsuarios.getText();
       String[] res = {item_seleccionado,valor_seleccionado};
       EditarSeccion frame = (EditarSeccion)getOwner();
       frame.resultado(res);
       setVisible(false);
    }//GEN-LAST:event_okActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField inputUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nombreSeleccionado;
    private javax.swing.JButton ok;
    private javax.swing.JComboBox<String> opcionUsuarios;
    // End of variables declaration//GEN-END:variables
}
