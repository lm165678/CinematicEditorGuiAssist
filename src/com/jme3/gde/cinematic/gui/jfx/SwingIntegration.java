/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.gde.cinematic.gui.jfx;

import com.jme3.gde.cinematic.CinematicEditorManager;
import com.jme3.gde.cinematic.core.CinematicClip;
import com.jme3.gde.cinematic.core.Event;
import com.jme3.gde.cinematic.core.Layer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MAYANK
 */
public class SwingIntegration extends javax.swing.JFrame {
Layer root;
    /**
     * Creates new form SwingIntegration
     */
    public SwingIntegration() {
        initComponents();
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                launchEditor();
            }
        
        });
    }

    public void launchEditor() {
        CinematicEditorUI cinematicEditor = new CinematicEditorUI();
        Scene scene = new Scene(cinematicEditor,660,220);
        scene.getStylesheets().add(getClass().getResource("layer_container.css").toExternalForm());
        cinematicFX.setScene(scene);
        cinematicFX.setVisible(true);
        initRoot();
        cinematicEditor.initCinematicEditorUI();
        cinematicEditor.getLayerContainer().setCinematicEditor(cinematicEditor);
        cinematicEditor.initView(root);
        
    
}
     public void initRoot() {
        CinematicClip clip = new CinematicClip("MyClip");
        clip.setDuration(30);
        CinematicEditorManager.getInstance().setCurrentClip(clip);
        root = new Layer("Root",null);
        clip.setRoot(root);
        
        Layer child = new Layer("Child",root);
        Layer sibling = new Layer("Sibling",root);
        Layer grandChild = new Layer("GrandChild",child);
        Layer grandChildCousin = new Layer("GrandChildCousin",sibling);
        
        Event event = new Event("E1", child, 5, 5);
        Event eventA = new Event("E2", sibling, 17, 7);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cinematicFX = new javafx.embed.swing.JFXPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(cinematicFX, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(cinematicFX, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

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
            java.util.logging.Logger.getLogger(SwingIntegration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SwingIntegration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SwingIntegration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SwingIntegration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SwingIntegration().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javafx.embed.swing.JFXPanel cinematicFX;
    // End of variables declaration//GEN-END:variables
}
