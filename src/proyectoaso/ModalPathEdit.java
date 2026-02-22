
package proyectoaso;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;
public class ModalPathEdit extends javax.swing.JDialog {
    private String item_seleccionado;
   
    public ModalPathEdit(JFrame owner, String item_seleccionado) {
        super(owner, true);
        this.item_seleccionado=item_seleccionado;
        initComponents();
        this.setLocationRelativeTo(null);
        this.nombreOpcion.setText(item_seleccionado);
    }
    
    public void setPath(String path){
         this.inputPath.setText(path);
    }
    private boolean validarPath(){
        boolean res=true;
        Path path = Paths.get(inputPath.getText());

        if (Files.exists(path)) {
            res=true;
        } else {
            res=false;            
        }
        return res;
    }
    
    private boolean validarDirectory(){
       boolean res=true;
       Path path = Paths.get(inputPath.getText());
       if (Files.isDirectory(path)) {
            res=true;
        } else if (Files.isRegularFile(path)) {
            res=false;
        }       
       return res;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombreOpcion = new javax.swing.JLabel();
        inputPath = new javax.swing.JTextField();
        buscarPath = new javax.swing.JButton();
        ok = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel2.setText("Opcion Actual :");

        nombreOpcion.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        nombreOpcion.setText("############");

        inputPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputPathActionPerformed(evt);
            }
        });

        buscarPath.setText("Buscar");
        buscarPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarPathActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreOpcion))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputPath, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(buscarPath, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(199, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreOpcion))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buscarPath, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(inputPath))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(161, Short.MAX_VALUE)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputPathActionPerformed
       
    }//GEN-LAST:event_inputPathActionPerformed

    private void buscarPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarPathActionPerformed
         JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                inputPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
    }//GEN-LAST:event_buscarPathActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
      this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        if(validarDirectory()&&validarPath()){
          dispose();
          String valor_seleccionado=inputPath.getText();
          String[] res = {item_seleccionado,valor_seleccionado};
          EditarSeccion frame = (EditarSeccion)getOwner();
          frame.resultado(res);
          setVisible(false);
        }else{
          if(validarDirectory()==false){
            JOptionPane.showMessageDialog(this,"Por favor selecciona la ruta de  un directorio","Error de Validadcion",JOptionPane.ERROR_MESSAGE);
          }else{
           JOptionPane.showMessageDialog(this,"Por favor selecciona una ruta valida ","Error de Validadcion",JOptionPane.ERROR_MESSAGE);
          }       
        }
    }//GEN-LAST:event_okActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarPath;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField inputPath;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel nombreOpcion;
    private javax.swing.JButton ok;
    // End of variables declaration//GEN-END:variables
}
