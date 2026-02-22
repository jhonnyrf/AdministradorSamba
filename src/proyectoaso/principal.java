
package proyectoaso;

import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

public class principal extends JFrame {
    private int estadoReinicio;
    DefaultTableModel modeloTabla;
    private List<SambaConfigSection> secciones;
    public principal(List<SambaConfigSection> secciones , int pestana ,int estadoReinicio) {
        this.secciones=secciones; 
        this.estadoReinicio=estadoReinicio;
        initComponents();
        this.setLocationRelativeTo(null);
        jPanel4jPanel4.setSelectedIndex(pestana);
        estadoArranque();
        EstadoReinicio();
        llenarTablaSecciones();
        llenarDatosIdentity();
    }

//Metodo para el estado 
    private void EstadoReinicio(){
       if(estadoReinicio==0){
       opcionesReinicio.setSelectedIndex(0);
       }else{
       opcionesReinicio.setSelectedIndex(1);
       }
    
    }
    private void setEstado(){
       int indice= opcionesReinicio.getSelectedIndex();
        if(indice==0){
          estadoReinicio=0;
        }else{
          estadoReinicio=1;
        }
    }
    
    private void Reiniciar(){
        try{
        String commando = "service smb restart";
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", commando);
            builder.redirectErrorStream(true); 
            Process process = builder.start();
       } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al ejecutar el comando: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }

//Metodos para ver controlar el inicio y reinicio del servicio cuando arranque la computadora   
private void estadoArranque() {
    try {
        boolean estadoHabilitado = esServicioHabilitado();
        if (estadoHabilitado) {
            this.opcionesArranque.setSelectedIndex(0);
        } else {
            this.opcionesArranque.setSelectedIndex(1);
        }
    } catch (IOException | InterruptedException ex) {
        System.err.println(ex);
    }
}
    
private static boolean esServicioHabilitado() throws IOException, InterruptedException {
    ProcessBuilder constructorProceso = new ProcessBuilder("systemctl", "is-enabled", "smb.service");
    Process proceso = constructorProceso.start();
    BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
    String salida = lector.readLine();
    lector.close();
    proceso.waitFor();
    return "enabled".equals(salida);
}

private static void habilitarServicio() throws IOException, InterruptedException {
    ejecutarComando("sudo", "systemctl", "enable", "smb.service");
}

private static void deshabilitarServicio() throws IOException, InterruptedException {
    ejecutarComando("sudo", "systemctl", "disable", "smb.service");
}

private static void ejecutarComando(String... comando) throws IOException, InterruptedException {
    ProcessBuilder constructorProceso = new ProcessBuilder(comando);
    Process proceso = constructorProceso.start();
    BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
    String linea;
    while ((linea = lector.readLine()) != null) {
        System.out.println(linea);
    }
    lector.close();
    proceso.waitFor();
}
    
// Metodos para lleer el archivo smb.conf
private List<String> leerComentarios() {
    String rutaArchivo = "/etc/samba/smb.conf";
    List<String> comentarios = new ArrayList<>();
    try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        while ((linea = lector.readLine()) != null) {
            linea = linea.trim(); // Eliminar espacios en blanco al principio y al final
            if (linea.startsWith("#")) { // Verificar si la línea es un comentario
                comentarios.add(linea); // Agregar el comentario a la lista
            }
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo smb.conf: " + e.getMessage());
    }
    return comentarios;
}   

private void leerConfiguracionSamba() {
    String rutaArchivo = "/etc/samba/smb.conf"; // Ruta al archivo smb.conf
    List<String> comentarios = leerComentarios();
    try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        SambaConfigSection seccionActual = null;

        while ((linea = lector.readLine()) != null) {
            linea = linea.trim(); // Eliminar espacios en blanco al principio y al final
            if (linea.startsWith("[") && linea.endsWith("]")) {
                // Nueva sección
                if (seccionActual != null) {
                    secciones.add(seccionActual); // Guardar la sección anterior
                }
                String nombreSeccion = linea.substring(1, linea.length() - 1).trim();
                seccionActual = new SambaConfigSection(nombreSeccion);
            } else if (linea.contains("=") && seccionActual != null) {
                // Configuración dentro de una sección
                String[] partes = linea.split("=", 2);
                if (partes.length == 2) {
                    String clave = partes[0].trim();
                    String valor = partes[1].trim();
                    seccionActual.addSetting(clave, valor);
                }
            }
        }
        if (seccionActual != null) {
            secciones.add(seccionActual);
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo smb.conf: " + e.getMessage());
    }
}

private void escribirConfiguracionSamba(List<SambaConfigSection> secciones) {
    String rutaArchivo = "/etc/samba/smb.conf"; // Ruta al archivo smb.conf
    List<String> comentarios = leerComentarios();

    try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
        for (String comentario : comentarios) {
            escritor.write(comentario + "\n");
        }

        for (SambaConfigSection seccion : secciones) {
            // Escribir el nombre de la sección
            escritor.write("[" + seccion.getSectionName()+ "]\n");

            // Escribir las configuraciones de la sección
            for (Map.Entry<String, String> entrada : seccion.getSettings().entrySet()) {
                escritor.write("      " + entrada.getKey() + " = " + entrada.getValue() + "\n");
            }
            // Añadir una línea en blanco después de cada sección para mejor legibilidad
            escritor.write("\n");
        }
    } catch (IOException e) {
        System.out.println("Error al escribir en el archivo smb.conf: " + e.getMessage());
    }
}

//metodo para llenar la tabla de secciones
private void llenarTablaSecciones() {
    if (secciones.isEmpty()) {
        leerConfiguracionSamba();
    }

    String[] fila = new String[6];
    String[] titulos =  {"status", "Read-Only", "Name", "Path", "Guest Acces","Comment"};
    modeloTabla = new DefaultTableModel(null, titulos);
    for (SambaConfigSection seccion : secciones) {
        try{
        if (!(seccion.getSectionName().equals("global"))) {
            Map<String, String> configuraciones = seccion.getSettings();
            if(esServicioHabilitado()){
            fila[0]="Enable";
            }else{
            fila[0]="disabled";
            }
            
            if (configuraciones.containsKey("read only")) {
                fila[1] = configuraciones.get("read only");
            } else {
                fila[1] = "Yes";
            }
            fila[2] = seccion.getSectionName();
            if (configuraciones.containsKey("path")) {
                fila[3] = configuraciones.get("path");
            } else if (configuraciones.containsKey("logon path")) {
                fila[3] = configuraciones.get("logon path");
            } else {
                fila[3] = "";
            }
            if (configuraciones.containsKey("guest ok")) {
               fila[4] = configuraciones.get("guest ok");
            } else {
               fila[4] = "Yes";
            }
            fila[5] = configuraciones.get("comment");
            modeloTabla.addRow(fila);
        }

         }catch(Exception e){

            System.err.println(e);

         }

    }
    tablaSecciones.setModel(modeloTabla);
}


private List<String> getSambaUsers() {
    List<String> users = new ArrayList<>();
    try {
        ProcessBuilder builder = new ProcessBuilder("bash", "-c", "pdbedit -L");
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length > 0) {
                users.add(parts[0].trim());
            }
        }
        process.waitFor();
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al obtener usuarios de Samba: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return users;
}

private void llenarTablaUsuarios() {
    List<String> usuarios = getSambaUsers();
    DefaultTableModel modeloUsuarios = new DefaultTableModel();
    modeloUsuarios.addColumn("Usuarios de Samba");

    for (String usuario : usuarios) {
        modeloUsuarios.addRow(new Object[]{usuario});
    }

    jTable1.setModel(modeloUsuarios);
}

private void eliminarUsuarioSamba(String usuario) {
    try {
        ProcessBuilder constructorProceso = new ProcessBuilder("bash", "-c", "sudo smbpasswd -x " + usuario);
        Process proceso = constructorProceso.start();
        proceso.waitFor();
        if (proceso.exitValue() == 0) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            llenarTablaUsuarios(); // Actualiza la lista de usuarios después de eliminar
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public void llenarDatosIdentity() {
    SambaConfigSection seccionGlobal = new SambaConfigSection("");
    for (SambaConfigSection seccion : secciones) {
        if (seccion.getSectionName().equals("global")) {
            seccionGlobal = seccion;
            break;
        }
    }
    Map<String, String> configuraciones = seccionGlobal.getSettings();

    String valorGrupoTrabajo = configuraciones.get("workgroup");
    InputGrupoTrabajo.setText(valorGrupoTrabajo);

    // Verificar y establecer valor predeterminado para "wins support"
    if (!configuraciones.containsKey("wins support")) {
       configuraciones.put("wins support", "Yes");
    }
       String valorSoporteWINS = configuraciones.get("wins support");

    // Verificar y establecer valor predeterminado para "wins server"
    if (!configuraciones.containsKey("wins server")) {
        configuraciones.put("wins server", "");
    }
         String valorServidorWINS = configuraciones.get("wins server");

    // Verificar y establecer valor predeterminado para "usershare allow guests"
    if (!configuraciones.containsKey("usershare allow guests")) {
          configuraciones.put("usershare allow guests", "No");
    }
        String valorCompartirUsuario = configuraciones.get("usershare allow guests");
        inputServidorWINS.setText(valorServidorWINS);

    if (valorSoporteWINS.equals("Yes")) {
        Yes_soporteWINS.setSelected(true);
        inputServidorWINS.setEditable(false);
    } else {
        No_soporteWINS.setSelected(true);
    }

    if (valorCompartirUsuario.equals("Yes")) {
         Yes_compartirUsuario.setSelected(true);
    } else {
         No_compartirUsuario.setSelected(true);
    }
      
}

public void setValoresGlobal(SambaConfigSection seccion) {
    Map<String, String> configuraciones = seccion.getSettings();
    String valorGrupoTrabajo = InputGrupoTrabajo.getText();
    configuraciones.put("workgroup", valorGrupoTrabajo);
    if (Yes_soporteWINS.isSelected()) {
        configuraciones.put("wins support", "Yes");
        configuraciones.remove("wins server");
    } else {
        configuraciones.put("wins support", "No");
        configuraciones.put("wins server", inputServidorWINS.getText());
    }
    if (Yes_compartirUsuario.isSelected()) {
        configuraciones.put("usershare allow guests", "Yes");
    } else {
        configuraciones.put("usershare allow guests", "No");
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel4jPanel4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        Cancelar_inicio = new javax.swing.JButton();
        Ok_inicio = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        opcionesReinicio = new javax.swing.JComboBox<>();
        opcionesArranque = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        reiniciar = new javax.swing.JButton();
        iniciar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSecciones = new javax.swing.JTable();
        eleminar = new javax.swing.JButton();
        add = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Ok_shares = new javax.swing.JButton();
        Cancelar_shares = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        InputGrupoTrabajo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Yes_soporteWINS = new javax.swing.JRadioButton();
        No_soporteWINS = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        inputServidorWINS = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Yes_compartirUsuario = new javax.swing.JRadioButton();
        No_compartirUsuario = new javax.swing.JRadioButton();
        Cancel_identity = new javax.swing.JButton();
        oK_identity = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Agregar_usuarios = new javax.swing.JButton();
        Listar_usuarios = new javax.swing.JButton();
        Eliminar_Usuarios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("\n");

        jPanel4jPanel4.setToolTipText("star");

        Cancelar_inicio.setText("Cancelar");
        Cancelar_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_inicioActionPerformed(evt);
            }
        });

        Ok_inicio.setBackground(new java.awt.Color(153, 204, 255));
        Ok_inicio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Ok_inicio.setForeground(new java.awt.Color(255, 255, 255));
        Ok_inicio.setText("Ok ");
        Ok_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ok_inicioActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        opcionesReinicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Restart", "Keep current state" }));
        opcionesReinicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcionesReinicioMouseClicked(evt);
            }
        });
        opcionesReinicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesReinicioActionPerformed(evt);
            }
        });

        opcionesArranque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Start on boot", "Do not start" }));
        opcionesArranque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesArranqueActionPerformed(evt);
            }
        });

        jLabel1.setText("Despues de Reiniciar");

        jLabel2.setText("Después de escribir la configuración");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel3.setText("Configuracion de servicio");

        reiniciar.setText("Reiniciar");
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });

        iniciar.setText("Iniciar");
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 139, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(opcionesArranque, 0, 144, Short.MAX_VALUE)
                                    .addComponent(opcionesReinicio, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addComponent(reiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(opcionesReinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(opcionesArranque, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(iniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cancelar_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(Ok_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ok_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancelar_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel4jPanel4.addTab("Inicio", jPanel1);

        tablaSecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "status", "Read-Only", "Name", "Path", "Guest Acces", "Comeent"
            }
        ));
        tablaSecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSeccionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaSecciones);

        eleminar.setText("Eliminar");
        eleminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eleminarActionPerformed(evt);
            }
        });

        add.setText("Añadir");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        editar.setText("Editar");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        jLabel4.setText("Configuraciones del servicio");

        Ok_shares.setBackground(new java.awt.Color(153, 204, 255));
        Ok_shares.setForeground(new java.awt.Color(255, 255, 255));
        Ok_shares.setText("Ok");
        Ok_shares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ok_sharesActionPerformed(evt);
            }
        });

        Cancelar_shares.setText("Cancelar");
        Cancelar_shares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar_sharesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(eleminar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Cancelar_shares, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(Ok_shares, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eleminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancelar_shares, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ok_shares, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel4jPanel4.addTab("Compartidos", jPanel2);

        jLabel5.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel5.setText("Workgroup:");

        InputGrupoTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputGrupoTrabajoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel6.setText("Wins support:");

        buttonGroup1.add(Yes_soporteWINS);
        Yes_soporteWINS.setText("Yes");
        Yes_soporteWINS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Yes_soporteWINSActionPerformed(evt);
            }
        });

        buttonGroup1.add(No_soporteWINS);
        No_soporteWINS.setText("No");
        No_soporteWINS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                No_soporteWINSActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel7.setText("Wins server:");

        inputServidorWINS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputServidorWINSActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel8.setText("usershare allow guests:");

        buttonGroup2.add(Yes_compartirUsuario);
        Yes_compartirUsuario.setText("Yes");
        Yes_compartirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Yes_compartirUsuarioActionPerformed(evt);
            }
        });

        buttonGroup2.add(No_compartirUsuario);
        No_compartirUsuario.setText("No");
        No_compartirUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                No_compartirUsuarioActionPerformed(evt);
            }
        });

        Cancel_identity.setText("Cancelar");
        Cancel_identity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancel_identityActionPerformed(evt);
            }
        });

        oK_identity.setBackground(new java.awt.Color(153, 204, 255));
        oK_identity.setForeground(new java.awt.Color(255, 255, 255));
        oK_identity.setText("Ok");
        oK_identity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oK_identityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(30, 30, 30)
                        .addComponent(InputGrupoTrabajo))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addComponent(Yes_compartirUsuario)
                        .addGap(39, 39, 39)
                        .addComponent(No_compartirUsuario))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(Yes_soporteWINS)
                                .addGap(37, 37, 37)
                                .addComponent(No_soporteWINS))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(inputServidorWINS, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(185, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cancel_identity, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(oK_identity, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(InputGrupoTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Yes_soporteWINS)
                    .addComponent(No_soporteWINS))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(inputServidorWINS, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Yes_compartirUsuario)
                    .addComponent(No_compartirUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel_identity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oK_identity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel4jPanel4.addTab("identity", jPanel4);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Usuarios Samba"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        Agregar_usuarios.setText("Agregar");
        Agregar_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Agregar_usuariosActionPerformed(evt);
            }
        });

        Listar_usuarios.setText("Listar");
        Listar_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Listar_usuariosActionPerformed(evt);
            }
        });

        Eliminar_Usuarios.setText("Eliminar");
        Eliminar_Usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Eliminar_UsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Listar_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(Agregar_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Eliminar_Usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Listar_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Agregar_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Eliminar_Usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jPanel4jPanel4.addTab("Usuarios", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4jPanel4)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4jPanel4)
        );

        jPanel4jPanel4.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcionesArranqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesArranqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionesArranqueActionPerformed

    private void opcionesReinicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesReinicioActionPerformed

    }//GEN-LAST:event_opcionesReinicioActionPerformed

    private void Cancelar_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_inicioActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cancelar?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
        if(respuesta == JOptionPane.YES_OPTION){        
              this.dispose();
        }
    }//GEN-LAST:event_Cancelar_inicioActionPerformed

    private void eleminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eleminarActionPerformed
                
  try {
    int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar esta configuración?", "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (respuesta == JOptionPane.YES_OPTION) {
        int filaSeleccionada = tablaSecciones.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nombreSeccion = (String) tablaSecciones.getValueAt(filaSeleccionada, 2);
            SambaConfigSection seccionEliminar = null;
            for (SambaConfigSection seccion : secciones) {
                if (seccion.getSectionName().equals(nombreSeccion)) {
                    seccionEliminar = seccion;
                    break;
                }
            }
            if (seccionEliminar != null) {
                secciones.remove(seccionEliminar);
                llenarTablaSecciones();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione la fila que desea eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
  } catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Ha ocurrido un error al eliminar la configuración: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }
    }//GEN-LAST:event_eleminarActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
    this.dispose();
    int pestaña=jPanel4jPanel4.getSelectedIndex();
    AgregarSeccion frame = new AgregarSeccion(secciones,pestaña,estadoReinicio);
    frame.setVisible(true);
    }//GEN-LAST:event_addActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        try {
           int filaSeleccionada = tablaSecciones.getSelectedRow();
           String dato = (String)tablaSecciones.getValueAt(filaSeleccionada, 2);
           SambaConfigSection encontrado = new SambaConfigSection("");
           for (SambaConfigSection seccion:secciones){
              if(seccion.getSectionName().equals(dato)){
                  encontrado=seccion;
              }
           }
           int pestana=jPanel4jPanel4.getSelectedIndex();
           this.dispose();
           EditarSeccion frame = new EditarSeccion(secciones,encontrado,pestana,estadoReinicio);
          frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una fila","Error",JOptionPane.ERROR_MESSAGE);
        }
 
    }//GEN-LAST:event_editarActionPerformed

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        String commando = "service smb start";
        try {
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", commando);
            builder.redirectErrorStream(true); 
            Process process = builder.start();
           JOptionPane.showMessageDialog(this, "Tarea realizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al ejecutar el comando: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_iniciarActionPerformed

    private void opcionesReinicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcionesReinicioMouseClicked
       
    }//GEN-LAST:event_opcionesReinicioMouseClicked

    private void Ok_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ok_inicioActionPerformed
        escribirConfiguracionSamba(secciones);
        String inicio=(String) opcionesArranque.getSelectedItem();
        try {            
           if(inicio.equals("Start on boot")){
                  habilitarServicio();
           }else{
                  deshabilitarServicio();
           }
           setEstado();
           EstadoReinicio();
           estadoArranque();
           llenarTablaSecciones();
           JOptionPane.showMessageDialog(this, "Tarea realizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        
        }
       
    }//GEN-LAST:event_Ok_inicioActionPerformed

    private void tablaSeccionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSeccionesMouseClicked
 
   
    }//GEN-LAST:event_tablaSeccionesMouseClicked

    private void Cancelar_sharesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar_sharesActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cancelar?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
        if(response == JOptionPane.YES_OPTION){        
              this.dispose();
        }
    }//GEN-LAST:event_Cancelar_sharesActionPerformed

    private void Ok_sharesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ok_sharesActionPerformed
         escribirConfiguracionSamba(secciones);
        llenarTablaSecciones();
        llenarDatosIdentity();
         if(estadoReinicio==0){
            Reiniciar();
         } 
         EstadoReinicio();
         JOptionPane.showMessageDialog(this, "Tarea realizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_Ok_sharesActionPerformed

    private void Listar_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Listar_usuariosActionPerformed
        llenarTablaUsuarios();  
        JOptionPane.showMessageDialog(this, "La lista de usuarios se ha actualizado exitosamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);// TODO add your handling code here:
    }//GEN-LAST:event_Listar_usuariosActionPerformed

    private void Eliminar_UsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Eliminar_UsuariosActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String usuario = (String) jTable1.getValueAt(filaSeleccionada, 0);
            int confirmar = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar el usuario " + usuario + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                eliminarUsuarioSamba(usuario);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_Eliminar_UsuariosActionPerformed

    private void InputGrupoTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputGrupoTrabajoActionPerformed
       
    }//GEN-LAST:event_InputGrupoTrabajoActionPerformed

    private void inputServidorWINSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputServidorWINSActionPerformed
       
    }//GEN-LAST:event_inputServidorWINSActionPerformed

    private void No_soporteWINSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_No_soporteWINSActionPerformed
             inputServidorWINS.setEditable(true);
    }//GEN-LAST:event_No_soporteWINSActionPerformed

    private void Yes_soporteWINSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Yes_soporteWINSActionPerformed
             inputServidorWINS.setEditable(false);
    }//GEN-LAST:event_Yes_soporteWINSActionPerformed

    private void Yes_compartirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Yes_compartirUsuarioActionPerformed
      
    }//GEN-LAST:event_Yes_compartirUsuarioActionPerformed

    private void No_compartirUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_No_compartirUsuarioActionPerformed
        
    }//GEN-LAST:event_No_compartirUsuarioActionPerformed

    private void Cancel_identityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel_identityActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cancelar?", "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
        if(respuesta == JOptionPane.YES_OPTION){        
              this.dispose();
        }
    }//GEN-LAST:event_Cancel_identityActionPerformed

    private void oK_identityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oK_identityActionPerformed
        
        SambaConfigSection Seccionglobal = new SambaConfigSection("");
           for (SambaConfigSection seccion:secciones){
              if(seccion.getSectionName().equals("global")){
                  Seccionglobal=seccion;
              }
           }
           setValoresGlobal(Seccionglobal);
           escribirConfiguracionSamba(secciones);
           llenarTablaSecciones();
           llenarDatosIdentity();
           if(estadoReinicio==0){
              Reiniciar();
            } 
        JOptionPane.showMessageDialog(this, "Tarea realizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
           
    }//GEN-LAST:event_oK_identityActionPerformed

    private void Agregar_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Agregar_usuariosActionPerformed
        ModalAgregarUsuario modal = new ModalAgregarUsuario(this,"Agregar Usuario Samba");
        modal.setVisible(true);
        llenarTablaUsuarios();
    }//GEN-LAST:event_Agregar_usuariosActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
        Reiniciar();
        JOptionPane.showMessageDialog(this, "Tarea realizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_reiniciarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar_usuarios;
    private javax.swing.JButton Cancel_identity;
    private javax.swing.JButton Cancelar_inicio;
    private javax.swing.JButton Cancelar_shares;
    private javax.swing.JButton Eliminar_Usuarios;
    private javax.swing.JTextField InputGrupoTrabajo;
    private javax.swing.JButton Listar_usuarios;
    private javax.swing.JRadioButton No_compartirUsuario;
    private javax.swing.JRadioButton No_soporteWINS;
    private javax.swing.JButton Ok_inicio;
    private javax.swing.JButton Ok_shares;
    private javax.swing.JRadioButton Yes_compartirUsuario;
    private javax.swing.JRadioButton Yes_soporteWINS;
    private javax.swing.JButton add;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton editar;
    private javax.swing.JButton eleminar;
    private javax.swing.JButton iniciar;
    private javax.swing.JTextField inputServidorWINS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jPanel4jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton oK_identity;
    private javax.swing.JComboBox<String> opcionesArranque;
    private javax.swing.JComboBox<String> opcionesReinicio;
    private javax.swing.JButton reiniciar;
    private javax.swing.JTable tablaSecciones;
    // End of variables declaration//GEN-END:variables
}
