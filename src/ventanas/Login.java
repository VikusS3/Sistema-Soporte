/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import clases.Conexcion;
import javax.swing.JOptionPane;

/**
 *
 * @author SAUL
 */
public class Login extends javax.swing.JFrame {

    public static String user = "";
    String pass = "";

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setSize(400, 500);
        setResizable(false);
        setTitle("Acceso al sistema");
        setLocationRelativeTo(null);

        /*ImageIcon Fondo= new ImageIcon("src/imagenes/fondo6.jpg");
        Icon icono = new ImageIcon(Fondo.getImage().getScaledInstance(jLabel_Fondo.getWidth(), 
                jLabel_Fondo.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Fondo.setIcon(icono);
        this.repaint();*/
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/soporte.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_user = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        jButton_Acceder = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(null);

        txt_user.setBackground(new java.awt.Color(0, 0, 0));
        txt_user.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txt_user.setForeground(new java.awt.Color(255, 255, 255));
        txt_user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_user.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_user);
        txt_user.setBounds(110, 230, 190, 28);

        txt_password.setBackground(new java.awt.Color(0, 0, 0));
        txt_password.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txt_password.setForeground(new java.awt.Color(255, 255, 255));
        txt_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_password.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_password);
        txt_password.setBounds(110, 280, 190, 30);

        jButton_Acceder.setBackground(new java.awt.Color(203, 25, 91));
        jButton_Acceder.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_Acceder.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Acceder.setText("Acceder");
        jButton_Acceder.setBorder(null);
        jButton_Acceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AccederActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Acceder);
        jButton_Acceder.setBounds(120, 340, 170, 40);

        jLabel_footer.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_footer.setText("Sistema de Soporte ");
        getContentPane().add(jLabel_footer);
        jLabel_footer.setBounds(120, 420, 160, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AccederActionPerformed

        user = txt_user.getText().trim();
        pass = txt_password.getText().trim();

        if (!user.equals("") || !pass.equals("")) {

            try {
                Connection cn = Conexcion.abrir();
                PreparedStatement pst = cn.prepareStatement(
                        "select tipo_nivel, estatus from usuarios where username ='" + user
                        + "' and password= '" + pass + "'");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    String tipo_nivel = rs.getString("tipo_nivel");
                    String estatus = rs.getString("estatus");
                    
                  if(tipo_nivel.equalsIgnoreCase("Administrador")&& estatus.equalsIgnoreCase("Activo")){
                      dispose();
                      new Administrador().setVisible(true);
                      
                  }else if(tipo_nivel.equalsIgnoreCase("Capturista")&& estatus.equalsIgnoreCase("Activo")){
                        dispose();
                      new Capturador().setVisible(true);
                      
                  }else if(tipo_nivel.equalsIgnoreCase("Tecnico")&& estatus.equalsIgnoreCase("Activo")){
                        dispose();
                      new Tecnico().setVisible(true);
                  }

                } else {
                    JOptionPane.showMessageDialog(null, "Datos de acceso Incorrectos");
                    txt_user.setText("");
                    txt_password.setText("");
                }

            } catch (SQLException e) {
                System.err.println("Error en el boton acceder. " + e);
                JOptionPane.showMessageDialog(null, "Error al Inciar sesion contactar con el administrador");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes de rellanar todos los campos");
        }


    }//GEN-LAST:event_jButton_AccederActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Acceder;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}