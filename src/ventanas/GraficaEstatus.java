/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.sql.*;
import clases.Conexcion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author SAUL
 */
public class GraficaEstatus extends javax.swing.JFrame {

    String user = "";
    int nuevoIngreso, NoReparado, EnRevision, Reparado, Entregado;

    String[] vector_estatus_nombre = new String[5];
    int[] vector_estatus_cantidad = new int[5];

    /**
     * Creates new form GraficaEstatus
     */
    public GraficaEstatus() {
        initComponents();

        user = Login.user;

        setSize(550, 450);
        setResizable(false);
        setTitle("Técnico - Sesion de " + user);
        setResizable(false);
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
                    "select estatus, count(estatus) as Cantidad from equipos group by estatus");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int posicion = 0;
                do {
                    vector_estatus_nombre[posicion] = rs.getString(1);
                    vector_estatus_cantidad[posicion] = rs.getInt(2);

                    if (vector_estatus_nombre[posicion].equalsIgnoreCase("En revision")) {
                        EnRevision = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Entregado")) {
                        Entregado = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("No reparado")) {
                        NoReparado = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Nuevo Ingreso")) {
                        nuevoIngreso = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Reparado")) {
                        Reparado = vector_estatus_cantidad[posicion];
                    }

                    posicion++;

                } while (rs.next());
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos " + e);
            JOptionPane.showMessageDialog(null, "!!Error contacte al administrador");
        }

        repaint();
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
        jLabel_Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Grafica de Estatus");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        getContentPane().add(jLabel_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 450));

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
            java.util.logging.Logger.getLogger(GraficaEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraficaEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraficaEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraficaEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficaEstatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Fondo;
    // End of variables declaration//GEN-END:variables

    public int EstatusMasRepetido(int nuevoIngreso, int NoReparado, int EnRevision, int Reparado, int Entregado) {
        if (nuevoIngreso > NoReparado && nuevoIngreso > EnRevision && nuevoIngreso > Reparado && nuevoIngreso > Entregado) {
            return nuevoIngreso;
        } else if (NoReparado > EnRevision && NoReparado > Reparado && NoReparado > Entregado) {
            return NoReparado;
        } else if (EnRevision > Reparado && EnRevision > Entregado) {
            return EnRevision;
        } else if (Reparado > Entregado) {
            return Reparado;
        } else {
            return Entregado;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int Estatus_mas_repetido = EstatusMasRepetido(nuevoIngreso, NoReparado, EnRevision, Reparado, Entregado);
        int largo_nuevoIngreso = nuevoIngreso * 400 / Estatus_mas_repetido;
        int largo_noReparado = NoReparado * 400 / Estatus_mas_repetido;
        int largo_enRevision = EnRevision * 400 / Estatus_mas_repetido;
        int largo_Reparado = Reparado * 400 / Estatus_mas_repetido;
        int largo_Entregado = Entregado * 400 / Estatus_mas_repetido;
        
        g.setColor(new Color(240, 248, 0));//amarillo
        g.fillRect(100, 100, largo_nuevoIngreso,40);
        g.drawString("Nuevo Ingreso", 10, 118);
        g.drawString("Cantidad:"+nuevoIngreso, 10, 133 );
        
        g.setColor(new Color(255, 0, 0));//rojo
        g.fillRect(100, 150, largo_noReparado,40);
        g.drawString("No Reparado", 10, 168);
        g.drawString("Cantidad:"+NoReparado, 10, 183 );
        
        g.setColor(new Color(0, 0, 0));//negro
        g.fillRect(100, 200, largo_enRevision,40);
        g.drawString("En Revision", 10, 218);
        g.drawString("Cantidad:"+EnRevision, 10, 233 );
        
        g.setColor(new Color(255, 255, 255));//blanco
        g.fillRect(100, 250, largo_Reparado,40);
        g.drawString("Reparado", 10, 268);
        g.drawString("Cantidad:"+Reparado, 10, 283 );
        
        g.setColor(new Color(0, 85, 0));//verder
        g.fillRect(100, 300, largo_Entregado,40);
        g.drawString("Entregado", 10, 318);
        g.drawString("Cantidad:"+Entregado, 10, 333 );
        
        
    }

}