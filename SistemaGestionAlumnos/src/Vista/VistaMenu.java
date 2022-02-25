package Vista;

import javax.swing.JButton;

/**
 *
 * @author fernando
 */
public class VistaMenu extends javax.swing.JFrame {

    public JButton getBtnAlumno() {
        return btnAlumno;
    }

    public JButton getBtnCarrera() {
        return btnCarrera;
    }

    public JButton getBtnCursado() {
        return btnCursado;
    }

    public JButton getBtnInscripcion() {
        return btnInscripcion;
    }

    public JButton getBtnMateria() {
        return btnMateria;
    }

    public JButton getBtnProfesor() {
        return btnProfesor;
    }

    public VistaMenu() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAlumno = new javax.swing.JButton();
        btnProfesor = new javax.swing.JButton();
        btnMateria = new javax.swing.JButton();
        btnCursado = new javax.swing.JButton();
        btnInscripcion = new javax.swing.JButton();
        btnCarrera = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAlumno.setBackground(new java.awt.Color(255, 255, 255));
        btnAlumno.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnAlumno.setForeground(new java.awt.Color(51, 51, 51));
        btnAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/alum_ menu.png"))); // NOI18N
        btnAlumno.setText("ALUMNOS");
        jPanel1.add(btnAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 180, 54));

        btnProfesor.setBackground(new java.awt.Color(255, 255, 255));
        btnProfesor.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnProfesor.setForeground(new java.awt.Color(51, 51, 51));
        btnProfesor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prof_menu.png"))); // NOI18N
        btnProfesor.setText("PROFESOR");
        jPanel1.add(btnProfesor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 180, 54));

        btnMateria.setBackground(new java.awt.Color(255, 255, 255));
        btnMateria.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnMateria.setForeground(new java.awt.Color(51, 51, 51));
        btnMateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mate_menu.png"))); // NOI18N
        btnMateria.setText("MATERIA");
        jPanel1.add(btnMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 510, 180, 54));

        btnCursado.setBackground(new java.awt.Color(255, 255, 255));
        btnCursado.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnCursado.setForeground(new java.awt.Color(51, 51, 51));
        btnCursado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/curs_menu.png"))); // NOI18N
        btnCursado.setText("CURSADO");
        jPanel1.add(btnCursado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 180, 54));

        btnInscripcion.setBackground(new java.awt.Color(255, 255, 255));
        btnInscripcion.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnInscripcion.setForeground(new java.awt.Color(51, 51, 51));
        btnInscripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/insc_menu.png"))); // NOI18N
        btnInscripcion.setText("INSCRIPCIÓN");
        jPanel1.add(btnInscripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 180, 54));

        btnCarrera.setBackground(new java.awt.Color(255, 255, 255));
        btnCarrera.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        btnCarrera.setForeground(new java.awt.Color(51, 51, 51));
        btnCarrera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/carre_menu.png"))); // NOI18N
        btnCarrera.setText("CARRERA");
        jPanel1.add(btnCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, 180, 54));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo-utn.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 470, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo_menu.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VistaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlumno;
    private javax.swing.JButton btnCarrera;
    private javax.swing.JButton btnCursado;
    private javax.swing.JButton btnInscripcion;
    private javax.swing.JButton btnMateria;
    private javax.swing.JButton btnProfesor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
