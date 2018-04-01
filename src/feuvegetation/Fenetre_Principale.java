/*
 * Projet FEU DE VEGETATION - Rapport du 3 avril 2014
 * VIGUIER Benoit et CHARDONNET Lancelot
 * EPF - 2ème année - Groupe Ab 
 * 
 * Fenetre_Principale.java
 */
package feuvegetation;

import static feuvegetation.Fenetre_Principale.carte;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane; 
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Fenetre_Principale extends javax.swing.JFrame {
    
    public static Carte carte;
    public static BufferedImage offlineMap;

    public Fenetre_Principale() throws IOException
    {
        // Initialisation des composants
        initComponents();
        setTitle("Feu de Végétation");
        carte = new Carte();
        jCarte.setLayout(new GridLayout(Carte.getHauteur(), Carte.getLargeur()));
        offlineMap = ImageIO.read(getClass().getResource("/images/offline-map.png"));

        // Raccourcis clavier pour les menu
        jMenuOuvrir.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
        jMenuEnregistrer.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        jMenuQuitter.setAccelerator(KeyStroke.getKeyStroke('Q', KeyEvent.CTRL_DOWN_MASK));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jDirectionVent = new javax.swing.JComboBox();
        jIntensiteVent = new javax.swing.JComboBox();
        jTauxHumidite = new javax.swing.JComboBox();
        jLDirectVent = new javax.swing.JLabel();
        jLIntensVent = new javax.swing.JLabel();
        jLTauxHumid = new javax.swing.JLabel();
        jLTempsIt = new javax.swing.JLabel();
        jDuree = new javax.swing.JSpinner();
        jCarte = new javax.swing.JLabel();
        jLabelVille = new javax.swing.JLabel();
        jVille = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jZoom = new javax.swing.JSlider();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jGenererCarteAleatoire = new javax.swing.JButton();
        jGenererCarteGoogle = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jAuClic = new javax.swing.JComboBox();
        jBarreMenu = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        jMenuOuvrir = new javax.swing.JMenuItem();
        jMenuEnregistrer = new javax.swing.JMenuItem();
        jMenuQuitter = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jDirectionVent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sud", "Ouest", "Nord", "Est" }));
        jDirectionVent.setSelectedIndex(3);

        jIntensiteVent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nul", "Modéré", "Fort", "Violent" }));
        jIntensiteVent.setSelectedIndex(3);

        jTauxHumidite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Très sec", "Sec", "Normal", "Humide" }));

        jLDirectVent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/compass.png"))); // NOI18N
        jLDirectVent.setText("Direction du vent");

        jLIntensVent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/speed.png"))); // NOI18N
        jLIntensVent.setText("Intensité du vent");

        jLTauxHumid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/humidity.png"))); // NOI18N
        jLTauxHumid.setText("Taux d'humidité");

        jLTempsIt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/timer.png"))); // NOI18N
        jLTempsIt.setText("Durée d'une itération");

        jDuree.setModel(new javax.swing.SpinnerNumberModel(50, 10, 10000, 10));

        jCarte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCarte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/earth-from-space.jpg"))); // NOI18N
        jCarte.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jCarte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCarteMouseClicked(evt);
            }
        });

        jLabelVille.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/location.png"))); // NOI18N
        jLabelVille.setText("Ville");

        jVille.setText("Sceaux");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/zoom.png"))); // NOI18N
        jLabel1.setText("Zoom");

        jZoom.setMaximum(21);
        jZoom.setValue(5);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Yuppy TC", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        jLabel2.setText("Parametres de la simulation ");

        jLabel3.setFont(new java.awt.Font("Yuppy TC", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Feu de Vegetation");

        jLabel4.setFont(new java.awt.Font("Yuppy TC", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tree.png"))); // NOI18N
        jLabel4.setText("Carte aleatoire");

        jLabel6.setFont(new java.awt.Font("Yuppy TC", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/google_maps.png"))); // NOI18N
        jLabel6.setText("Carte Google Maps");

        jGenererCarteAleatoire.setBackground(new java.awt.Color(0, 102, 0));
        jGenererCarteAleatoire.setFont(new java.awt.Font("Yuppy TC", 1, 14)); // NOI18N
        jGenererCarteAleatoire.setForeground(new java.awt.Color(255, 255, 255));
        jGenererCarteAleatoire.setText("Generer une carte");
        jGenererCarteAleatoire.setFocusPainted(false);
        jGenererCarteAleatoire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGenererCarteAleatoireActionPerformed(evt);
            }
        });

        jGenererCarteGoogle.setBackground(new java.awt.Color(0, 102, 0));
        jGenererCarteGoogle.setFont(new java.awt.Font("Yuppy TC", 1, 14)); // NOI18N
        jGenererCarteGoogle.setForeground(new java.awt.Color(255, 255, 255));
        jGenererCarteGoogle.setText("Generer une carte");
        jGenererCarteGoogle.setFocusPainted(false);
        jGenererCarteGoogle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jGenererCarteGoogleMousePressed(evt);
            }
        });
        jGenererCarteGoogle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGenererCarteGoogleActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Yuppy TC", 1, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Generez une carte puis cliquez dessus pour");

        jLabel7.setFont(new java.awt.Font("Yuppy TC", 1, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("commencer la simulation");

        jLabel8.setText("Au clic sur une case :");

        jAuClic.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lancer la simulation", "Ajouter une maison", "Ajouter un chemin" }));

        jMenuFichier.setText("Fichier");

        jMenuOuvrir.setMnemonic(65);
        jMenuOuvrir.setText("Ouvrir");
        jMenuOuvrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOuvrirActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuOuvrir);

        jMenuEnregistrer.setText("Enregistrer");
        jMenuEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEnregistrerActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuEnregistrer);

        jMenuQuitter.setText("Quitter");
        jMenuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuQuitterActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuQuitter);

        jBarreMenu.add(jMenuFichier);

        setJMenuBar(jBarreMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCarte, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLIntensVent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jIntensiteVent, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLTauxHumid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTauxHumidite, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLDirectVent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDirectionVent, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelVille)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jVille, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLTempsIt)
                                .addGap(37, 37, 37)
                                .addComponent(jDuree, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jGenererCarteAleatoire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jGenererCarteGoogle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jAuClic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCarte, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jVille, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVille, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jZoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jGenererCarteGoogle)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jAuClic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jGenererCarteAleatoire)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jDirectionVent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLDirectVent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jIntensiteVent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLIntensVent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTauxHumidite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLTauxHumid))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLTempsIt)
                            .addComponent(jDuree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuOuvrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOuvrirActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Cartes pour Feu de Végétation (*.feu)", "feu");
        chooser.setFileFilter(filter);
        
        int retour = chooser.showOpenDialog(this);
        if(retour == JFileChooser.APPROVE_OPTION) {
            carte = new Carte(chooser.getSelectedFile().getAbsolutePath());    
            actualiserCarte();
        }
    }//GEN-LAST:event_jMenuOuvrirActionPerformed

    // Clic sur la carte en mode Google Maps
    private void jCarteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCarteMouseClicked
        //JOptionPane.showMessageDialog(this, "Vous avez cliqué aux coordonnées x="+x+" et y="+y, "Erreur", JOptionPane.ERROR_MESSAGE);
        int ligne = (int)MouseInfo.getPointerInfo().getLocation().getY() - (int)jCarte.getLocationOnScreen().getY();
        int colonne = (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)jCarte.getLocationOnScreen().getX();
        
        carte.debuterSimulation(carte.getCase(ligne, colonne), jIntensiteVent.getSelectedIndex(), jDirectionVent.getSelectedIndex(), jTauxHumidite.getSelectedIndex(), (int)jDuree.getValue());
    }//GEN-LAST:event_jCarteMouseClicked

    private void jMenuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuQuitterActionPerformed

    private void jMenuEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEnregistrerActionPerformed
        if (Carte.carteGoogle) {
            JOptionPane.showMessageDialog(null, "Vous ne pouvez enregistrer que les cartes générées aléatoirement.", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Cartes pour Feu de Végétation (*.feu)", "feu");
        chooser.setFileFilter(filter);
        
        int retour = chooser.showOpenDialog(this);
        if(retour == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();
                    
            try {
                FileOutputStream saveFile = new FileOutputStream(chooser.getSelectedFile());
                ObjectOutputStream save = new ObjectOutputStream(saveFile);
                save.writeObject(carte.getCases());
                save.close();
            } catch (IOException ex) {
                System.out.println("Erreur lors de l'écriture dans le fichier "+filename+" : "+ex.getMessage());
            }
        }
    }//GEN-LAST:event_jMenuEnregistrerActionPerformed

    private void jGenererCarteAleatoireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGenererCarteAleatoireActionPerformed
        generationCarteAleatoire();
    }//GEN-LAST:event_jGenererCarteAleatoireActionPerformed

    private void jGenererCarteGoogleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGenererCarteGoogleActionPerformed
        generationCarteGoogle();
    }//GEN-LAST:event_jGenererCarteGoogleActionPerformed

    private void jGenererCarteGoogleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jGenererCarteGoogleMousePressed
        jCarte.removeAll();
        jCarte.setIcon(new ImageIcon(getClass().getResource("/images/loading.jpg")));
    }//GEN-LAST:event_jGenererCarteGoogleMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        // Si on ouvre le programme avec un fichier, on charge la carte
        if (args.length > 0)
            carte = new Carte(args[0]);
        
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fenetre_Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fenetre_Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fenetre_Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fenetre_Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Fenetre_Principale().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Fenetre_Principale.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JComboBox jAuClic;
    private javax.swing.JMenuBar jBarreMenu;
    private static javax.swing.JLabel jCarte;
    public static javax.swing.JComboBox jDirectionVent;
    public static javax.swing.JSpinner jDuree;
    private javax.swing.JButton jGenererCarteAleatoire;
    private javax.swing.JButton jGenererCarteGoogle;
    public static javax.swing.JComboBox jIntensiteVent;
    private javax.swing.JLabel jLDirectVent;
    private javax.swing.JLabel jLIntensVent;
    private javax.swing.JLabel jLTauxHumid;
    private javax.swing.JLabel jLTempsIt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelVille;
    private javax.swing.JMenuItem jMenuEnregistrer;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JMenuItem jMenuOuvrir;
    private javax.swing.JMenuItem jMenuQuitter;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JComboBox jTauxHumidite;
    private javax.swing.JTextField jVille;
    private javax.swing.JSlider jZoom;
    // End of variables declaration//GEN-END:variables

    public static void actualiserCarte() {
        jCarte.removeAll();
        
        if (Carte.carteGoogle)
        {
            jCarte.setIcon(new ImageIcon(carte.getImage())); // Actualiser le label revient à mettre à jour son image
        }
        else
        {
            jCarte.setIcon(new ImageIcon());
            
            for (int ligne = 0; ligne < Carte.getHauteur(); ligne++)
                for (int colonne = 0; colonne < Carte.getLargeur(); colonne++)
                    jCarte.add(carte.getCase(ligne, colonne).getBouton()); // Ajout d'une case
            jCarte.revalidate(); // La méthode revalidate() permet d'actualiser l'affichage d'un JPanel
        }
    }
    
    public static void actualiserCartePendantSimulation() {
        if (Carte.carteGoogle)
            jCarte.setIcon(new ImageIcon(carte.getImage())); // Actualiser le label revient à mettre à jour son image
        else
            jCarte.revalidate();
    }

    public void generationCarteAleatoire() {
        // Tirage au sort des proportions de végétation, de foret et de terre
        Random gen = new Random();
        int typeCarte = gen.nextInt(4);
        double pourcentageForet = Math.random();
        double pourcentageTerre = Math.random();
        
        
        // Si une simulation est en cours, on l'arrête
        if (Carte.simulationEnCours)
            Carte.stopperSimulation();
        
        // Destruction de l'ancienne carte
        detruireCarte();
        
        
        // Création carte
        carte = new Carte(typeCarte);
        
        
        // Attribution d'un nombre de cases à chaque proportion
        int nbCasesVegetation = 0, nbCasesSansVegetation = 0, nbCasesForet = 0, nbCasesPrairie = 0, nbCasesTerre = 0, nbCasesEau = 0;
        int caseVideAleatoire, caseVideCourante;
        
        switch (typeCarte)
        {
          case 0:   nbCasesVegetation = (int)(0.50*(double)Carte.getTaille());    break;
          case 1:   nbCasesVegetation = (int)(0.75*(double)Carte.getTaille());    break;
          case 2:   nbCasesVegetation = (int)(0.90*(double)Carte.getTaille());    break;
          case 3:   nbCasesVegetation = (int)(1.00*(double)Carte.getTaille());    break;
        }
        
        nbCasesSansVegetation = Carte.getTaille() - nbCasesVegetation;
        nbCasesForet = (int)((double)nbCasesVegetation*pourcentageForet);
        nbCasesPrairie = nbCasesVegetation - nbCasesForet;
        nbCasesTerre = (int)((double)nbCasesSansVegetation*pourcentageTerre);
        nbCasesEau = nbCasesSansVegetation - nbCasesTerre;
        
        
        // Tant que la carte possède des cases vides
        for (int nbCasesVides = Carte.getTaille(); nbCasesVides != 0; nbCasesVides--)
        {
            // Tirage au sort d'une case vide de la carte
            caseVideAleatoire = gen.nextInt(nbCasesVides);
            caseVideCourante = 0;
            
            boucle :
            for (int ligne = 0; ligne < Carte.getHauteur(); ligne++) {
                for (int colonne = 0; colonne < Carte.getLargeur(); colonne++) {
                    // On cherche à parcourir uniquement les cases vides donc si la case n'est pas vide, on passe à l'itération suivante
                    if (!carte.getCase(ligne, colonne).sansType())
                        continue;

                    // Si la case vide courante est égale à la case vide tirée au sort, on lui attribue un type de terrain
                    if (caseVideCourante == caseVideAleatoire) {
                        if (nbCasesForet != 0) {
                            carte.getCase(ligne, colonne).setType(0);
                            nbCasesForet--;
                        }
                        else if (nbCasesPrairie != 0) {
                            carte.getCase(ligne, colonne).setType(1);
                            nbCasesPrairie--;
                        }
                        else if (nbCasesTerre != 0) {
                            carte.getCase(ligne, colonne).setType(2);
                            nbCasesTerre--;
                        }
                        else if (nbCasesEau != 0) {
                            carte.getCase(ligne, colonne).setType(3);
                            nbCasesEau--;
                        }
                        
                        break boucle; // Equivaut à un double break
                    }
                    else {
                        caseVideCourante++;
                    }
                }
            }
        }
        
        // Actualisation de la carte
        actualiserCarte();
    }

    @SuppressWarnings("empty-statement")
    public void generationCarteGoogle() { 
        // Si une simulation est en cours, on l'arrête
        if (Carte.simulationEnCours)
            Carte.stopperSimulation();
        
        if (jVille.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer le nom d'une ville !", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        // Destruction de l'ancienne carte
        detruireCarte();
        
        
        // Création carte
        carte = new Carte(jVille.getText(), jZoom.getValue());
        
        // Affichage des conditions climatiques du lieu choisi
        int infos[] = carte.getInfosMeteo();
        jDirectionVent.setSelectedIndex(infos[5]);
        jIntensiteVent.setSelectedIndex(infos[4]);
        jTauxHumidite.setSelectedIndex(infos[3]);
        
        // Actualisation de la carte
        actualiserCarte();
    }
    
    public void detruireCarte() {
        carte.detruire();
        carte = null;
        System.gc(); // Libération de la mémoire
    }
}