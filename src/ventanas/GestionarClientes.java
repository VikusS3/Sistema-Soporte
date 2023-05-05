/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.sql.*;
import clases.Conexcion;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SAUL
 */
public class GestionarClientes extends javax.swing.JFrame {

    String user;
    public static int IDcliente_update = 0;
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form GestionarClientes
     */
    public GestionarClientes() {
        initComponents();
        user = Login.user;

        setSize(650, 330);
        setResizable(false);
        setTitle("Capturista - Sesion de " + user);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ImageIcon Fondo = new ImageIcon("src/imagenes/fondo6.jpg");
        Icon icono = new ImageIcon(Fondo.getImage().getScaledInstance(jLabel_Fondo.getWidth(),
                jLabel_Fondo.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Fondo.setIcon(icono);
        this.repaint();

        try {
            Connection cn = Conexcion.abrir();
            PreparedStatement pst = cn.prepareStatement(
                    "select id_clientes, nombre_cliente, email_cliente , tel_cliente, ultima_modificacion from clientes");
            ResultSet rs = pst.executeQuery();

            jTable_clientes = new JTable(model);
            jScrollPane1.setViewportView(jTable_clientes);
            
            model.addColumn(" ");
            model.addColumn("Nombre");
            model.addColumn("Email");
            model.addColumn("Telefono");
            model.addColumn("Modificado por");
            
            while(rs.next()){
                Object[] fila =new Object[5];
                for (int i = 0; i < 5; i++) {
                    fila[i]=rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            
            cn.close();
            
        } catch (SQLException e) {
            System.err.println("Hubo en un error al rellenar la tabla " + e);
        }
        
        jTable_clientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int fila_point = jTable_clientes.rowAtPoint(e.getPoint());
                int columna_point = 0;
                
                if(fila_point>-1){
                   IDcliente_update=(int)model.getValueAt(fila_point,columna_point);
                   Informacion_cliente informacionCliente = new Informacion_cliente();
                   informacionCliente.setVisible(true);
                   
                }
            }
        });
        

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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_clientes = new javax.swing.JTable();
        jLabel_Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Clientes Registrados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jTable_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_clientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 650, 250));
        getContentPane().add(jLabel_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GestionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Fondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_clientes;
    // End of variables declaration//GEN-END:variables
}