
package proyectoaso;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class EditarSeccion extends javax.swing.JFrame {
    private SambaConfigSection seccion;
    private List<SambaConfigSection> secciones;
    private List<SambaConfigSection> seccionNoModificado;
    private ArrayList<String> parametros;
    private boolean printeables;
    private int pestana;
    private String[] resultado_seleccionado=new String[2];
    private int estadoReinicio;
    
    public EditarSeccion(List<SambaConfigSection> secciones,SambaConfigSection seccion ,int pestana,int estadoReinicio) {
        this.seccionNoModificado = new ArrayList<>();
        for (SambaConfigSection sec : secciones) {
            seccionNoModificado.add(new SambaConfigSection(sec));
        }
        this.secciones = secciones;
        this.seccion = seccion;
        this.pestana = pestana;
        this.estadoReinicio=estadoReinicio;
        initComponents();
        this.setLocationRelativeTo(null);
        this.shareNombre.setText("compartir "+seccion.getSectionName());
        llenarTablaParametros();
        
    }
    // metodo para llenar parametros ala lista de parametros
    public void llenarParametros(){
       Map<String,String> setings=seccion.getSettings();
       setings.forEach((key, value) -> {
           boolean res =parametros.remove(key);
       });       
    }
  
    public void crearParametros() {
        String[] option = {"inherit acls", "admin users", "printable", "writeable", "guest ok", "write list", "public", "read only", "directory mask", "create mask", "browseable", "valid users"};
        parametros = new ArrayList<>();
        for (String opt : option) {
            parametros.add(opt);
        }
    }
    
    public void resultado(String[] data){
           this.resultado_seleccionado=data;
    }
     public static boolean isArrayEmpty(String[] array) {
        for (String item : array) {
            if (item != null) {
                return false;  
            }
        }
        return true;  
    }
   
    private void llenarTablaParametros(){
        String[] fila = new String[2];
        String[] titulos = {"Opción", "Valor"};
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        Map<String, String> settings = seccion.getSettings();
        settings.forEach((key, value) -> {
            fila[0] = key;
            fila[1] = value;
            model.addRow(fila);
        });
        tablaParametros.setModel(model);
    }
    private boolean EsMask(String parametro){
        String[] seleccionMask = {"directory mask", "create mask"};
        for (String option : seleccionMask) {
            if (option.equals(parametro)) {
                return true;
            }
        }
        return false;
    }
    private boolean EsUsuarios(String parametro){
        String[] seleccionUsuarios = {"write list", "admin users", "valid users"};
        for (String option : seleccionUsuarios) {
            if (option.equals(parametro)) {
                return true;
            }
        }
        return false;
    }
    public boolean EsValorTexto(String parametro){
           boolean res = false;
           String[] opsiones = {"veto files","force group","comment"};
           for(String opsion:opsiones){
               if(opsion.equals(parametro)){
                  res=true;
                  break;
               }
           }
           return res;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaParametros = new javax.swing.JTable();
        editarParametro = new javax.swing.JButton();
        eiminarParametro = new javax.swing.JButton();
        anadirParametro = new javax.swing.JButton();
        ok_editarSeccion = new javax.swing.JButton();
        atras = new javax.swing.JButton();
        shareNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaParametros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Option", "Value"
            }
        ));
        jScrollPane1.setViewportView(tablaParametros);

        editarParametro.setText("Editar");
        editarParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarParametroActionPerformed(evt);
            }
        });

        eiminarParametro.setText("Eliminar");
        eiminarParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eiminarParametroActionPerformed(evt);
            }
        });

        anadirParametro.setText("Añadir");
        anadirParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirParametroActionPerformed(evt);
            }
        });

        ok_editarSeccion.setText("Ok");
        ok_editarSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ok_editarSeccionActionPerformed(evt);
            }
        });

        atras.setText("Atrás");
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });

        shareNombre.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        shareNombre.setText("####");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(anadirParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editarParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eiminarParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ok_editarSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shareNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(shareNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(editarParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(eiminarParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(anadirParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ok_editarSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(atras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))))
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

    private void editarParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarParametroActionPerformed
try {
    int fila = tablaParametros.getSelectedRow();
    String parametro = (String) tablaParametros.getValueAt(fila, 0);
    Map<String, String> parametros = seccion.getSettings();
    String valor = parametros.get(parametro);
    System.out.println(parametro);
    if (EsMask(parametro)) {
        ModalMaskEdit modal = new ModalMaskEdit(this, parametro);
        modal.OpsionesSeleccionados(valor);
        modal.setVisible(true);
        if (!(isArrayEmpty(resultado_seleccionado)) && !(resultado_seleccionado[1].equals(valor))) {
            parametros.put(resultado_seleccionado[0], resultado_seleccionado[1]);
            seccion.setSettings(parametros);
            llenarTablaParametros();
        }
    } else if (EsUsuarios(parametro)) {
        ModalUsuariosEdit modal = new ModalUsuariosEdit(this, parametro);
        modal.setUsuarios(valor);
        modal.setVisible(true);
        if (!(isArrayEmpty(resultado_seleccionado)) && !(resultado_seleccionado[1].equals(valor))) {
            parametros.put(resultado_seleccionado[0], resultado_seleccionado[1]);
            seccion.setSettings(parametros);
            llenarTablaParametros();
        }
    } else if ("path".equals(parametro)) {
        ModalPathEdit modal = new ModalPathEdit(this, parametro);
        modal.setPath(valor);
        modal.setVisible(true);
        if (!(isArrayEmpty(resultado_seleccionado)) && !(resultado_seleccionado[1].equals(valor))) {
            parametros.put(resultado_seleccionado[0], resultado_seleccionado[1]);
            seccion.setSettings(parametros);
            llenarTablaParametros();
        }
    } else if (EsValorTexto(parametro)) {
        ModalTextInputEdit modal = new ModalTextInputEdit(this, parametro);
        modal.setComentario(valor);
        modal.setVisible(true);
        if (!(isArrayEmpty(resultado_seleccionado)) && !(resultado_seleccionado[1].equals(valor))) {
            parametros.put(resultado_seleccionado[0], resultado_seleccionado[1]);
            seccion.setSettings(parametros);
            llenarTablaParametros();
        }
    } else {
        ModalBinarioEdit modal = new ModalBinarioEdit(this, parametro);
        modal.setSelecciondo(valor);
        modal.setVisible(true);
        if (!(isArrayEmpty(resultado_seleccionado)) && !(resultado_seleccionado[1].equals(valor))) {
            parametros.put(resultado_seleccionado[0], resultado_seleccionado[1]);
            seccion.setSettings(parametros);
            llenarTablaParametros();
        }
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Por favor seleccione una fila", "Error", JOptionPane.ERROR_MESSAGE);
}             
    }//GEN-LAST:event_editarParametroActionPerformed

    private void eiminarParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eiminarParametroActionPerformed
    try{
        int response = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este parametro?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
        if (response == JOptionPane.YES_OPTION){
            int fila = tablaParametros.getSelectedRow();
            String parametro = (String)tablaParametros.getValueAt(fila, 0);
            Map<String,String> parametros = seccion.getSettings();
            parametros.remove(parametro);
            seccion.setSettings(parametros);
            llenarTablaParametros();
        }
    }catch(Exception e){
       JOptionPane.showMessageDialog(this, "Por favor seleccione el parametro que desea eliminar","Error",JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_eiminarParametroActionPerformed

    private void ok_editarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ok_editarSeccionActionPerformed
    this.dispose();
    principal frame = new principal(secciones,pestana,estadoReinicio);
    frame.setVisible(true);     
    }//GEN-LAST:event_ok_editarSeccionActionPerformed

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
    this.dispose();
     // Restaurar el estado inicial
     secciones.clear();
     for (SambaConfigSection s : seccionNoModificado) {
          secciones.add(new SambaConfigSection(s));
     }
    principal frame = new principal(secciones,pestana,estadoReinicio);
    frame.setVisible(true);
    }//GEN-LAST:event_atrasActionPerformed

    private void anadirParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirParametroActionPerformed
        crearParametros();
        llenarParametros();
        ModalAñadirParametro modal = new ModalAñadirParametro(this);
        modal.llenarOpciones(parametros);
        if(!isArrayEmpty(resultado_seleccionado)){
         seccion.addSetting(resultado_seleccionado[0], resultado_seleccionado[1]);
         llenarTablaParametros();
        }
       
    }//GEN-LAST:event_anadirParametroActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anadirParametro;
    private javax.swing.JButton atras;
    private javax.swing.JButton editarParametro;
    private javax.swing.JButton eiminarParametro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ok_editarSeccion;
    private javax.swing.JLabel shareNombre;
    private javax.swing.JTable tablaParametros;
    // End of variables declaration//GEN-END:variables
}
