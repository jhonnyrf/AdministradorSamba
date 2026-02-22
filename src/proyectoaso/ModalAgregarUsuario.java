
package proyectoaso;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class ModalAgregarUsuario extends javax.swing.JDialog {

    public ModalAgregarUsuario(JFrame owner, String nombre) {
        super(owner, nombre,true);
        initComponents();
        this.setLocationRelativeTo(null);
    }
 
    // Comprobar si el usuario existe en el sistema
    private static boolean usuarioExisteSistema(String nombreUsuario) throws IOException, InterruptedException {
        String[] comandoParaComprobar = { "id", nombreUsuario };
        Process process = ejecutarComando(comandoParaComprobar);
        return process.waitFor() == 0;
    }

    // Comprobar si el usuario existe en Samba
    private static boolean usuarioExisteSamba(String nombreUsuario)throws IOException, InterruptedException {
        String[] ComprobarUsrSamba = { "sudo", "pdbedit", "-L", "-u", nombreUsuario };
        Process process = ejecutarComando(ComprobarUsrSamba);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        boolean existe = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains(nombreUsuario)) {
                existe = true;
                break;
            }
        }
        reader.close();
        return existe;
    }
        
     //agregar usuarios si no existen en el sistema a samba
     private static void agregarUsuariosSistema(String nombreUsuario,String password) throws IOException, InterruptedException {
        //Agregar usuario al sistema 
        String[] comandoAgregarUsr = { "sudo", "useradd", "-m", nombreUsuario };
        ejecutarComando(comandoAgregarUsr);

        //Establecer la contraseña del usuario 
        String[] comandoEstablecesCont = { "sudo", "sh", "-c", "echo " + nombreUsuario + ":" + password + " | chpasswd" };
        ejecutarComando(comandoEstablecesCont);

        //Agregar usuario a Samba
        agregarSambaUsuario(nombreUsuario, password);
    }
     
    //Agregar usuario que ya exite en el sistema  a Samba
    private static void agregarSambaUsuario(String nombreUsuario,String password)  throws IOException, InterruptedException {
        String[] comandoAgregarUsuario = { "sudo", "smbpasswd", "-a", nombreUsuario};
        Process process = ejecutarComando(comandoAgregarUsuario);

      // Escribir la contraseña dos veces para confirmar
       BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
       writer.write(password + "\n");
       writer.write(password + "\n");
       writer.flush();
       writer.close();
        // Verificar el resultado del proceso
        int codigoSalida = process.waitFor();
        if (codigoSalida != 0) {
            throw new RuntimeException("Error al agregar el usuario Samba. Código de salida: " + codigoSalida);
        }
    }
    private static Process ejecutarComando(String[] command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        // Leer la salida del comando
       BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        return process;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inputUsuario = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        inputPassd = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel1.setText("Agregar Usuario Samba");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña:");

        jLabel4.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jLabel4.setText("Nombre de Usuario :");

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inputUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(inputPassd)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addComponent(cancelar)
                .addGap(62, 62, 62)
                .addComponent(agregar)
                .addGap(117, 117, 117))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPassd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
      String nombreUsuario = inputUsuario.getText();
      String password = new String(inputPassd.getPassword());
      if(nombreUsuario.isEmpty()||password.isEmpty()){
          JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
      }else{
          try{
           boolean usuarioExisteSistema = usuarioExisteSistema(nombreUsuario);
           boolean usuarioExisteSamba = usuarioExisteSamba(nombreUsuario);
           if(usuarioExisteSistema&&usuarioExisteSamba){
                  JOptionPane.showMessageDialog(this, "El usuario ya existe en el sistema y en Samba.", "Error", JOptionPane.ERROR_MESSAGE);     
           }else if(usuarioExisteSamba){
                  agregarSambaUsuario(nombreUsuario,password);
                  JOptionPane.showMessageDialog(this, "Usuario Samba agregado exitosamente.");
                  this.dispose();
           }else{
                  agregarUsuariosSistema(nombreUsuario,password);
                  JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente.");
                  this.dispose();
           }

          }catch(Exception ex){
           JOptionPane.showMessageDialog(this, "Error al agregar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          }
      }
    }//GEN-LAST:event_agregarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cancelar?", "Confirmar eliminación",
                       JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
        if(response == JOptionPane.YES_OPTION){        
              this.dispose();
        }
    }//GEN-LAST:event_cancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton cancelar;
    private javax.swing.JPasswordField inputPassd;
    private javax.swing.JTextField inputUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables




    
}
