/*
 * Projet FEU DE VEGETATION - Rapport du 3 avril 2014
 * VIGUIER Benoit et CHARDONNET Lancelot
 * EPF - 2ème année - Groupe Ab
 * 
 * Case.java
 */
package feuvegetation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Case implements java.io.Serializable {
    /*** ATTRIBUTS ***/
    private int ligne, colonne;         
    private int rgb;                // Uniquement en mode Google Maps
    private JButton bouton;         // Uniquement en mode 2D
    private int type; // 0=forêt 1=prairie 2=terre 3=eau 4=maison -1=vide
    private int etat; // 0=intacte 1=enflammée 2=brulé_chaud 3=brulé_froid 4=cendres
    private int depuis;
    
    
    
    /*** CONSTRUCTEURS ***/
        // Constructeur mode 2D
    public Case(int ligne, int colonne) {
        this.colonne = colonne;
        this.ligne = ligne;
        type = -1;
        etat = 0;
        depuis = 0;
        
        bouton = new JButton();
        bouton.setOpaque(false);
        bouton.setContentAreaFilled(false);
        bouton.setBorderPainted(false);
        
        // Clic sur le bouton
        final int l = ligne;
        final int c = colonne;
        bouton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Fenetre_Principale.jAuClic.getSelectedIndex())
                {
                    case 0:
                        Fenetre_Principale.carte.debuterSimulation(Fenetre_Principale.carte.getCase(l, c), Fenetre_Principale.jIntensiteVent.getSelectedIndex(), Fenetre_Principale.jDirectionVent.getSelectedIndex(), Fenetre_Principale.jTauxHumidite.getSelectedIndex(), (int)Fenetre_Principale.jDuree.getValue());    
                        break;        
                    case 1:
                        setType(4);   
                        break;
                    case 2:
                        setType(2);
                        break;
                }
            }
        });
    }
    
        // Constructeur mode Google Maps
    public Case(int ligne, int colonne, int rgb) {
        this(ligne, colonne);
        
        this.rgb = rgb;
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        
        if (red >= green && red >= blue)
            type = 2;
        else if (green >= red && green >= blue)
            type = 0;
        else if (blue >= red && blue >= green)
            type = 3;
        else
            type = 1; // mettre des maisons sinon, càd type = 4, sans oublier de créer les images nécessaires
    }
    
    
    
    /*** ACCESSEURS ***/
    public int getLigne() {
        return ligne;
    }
    
    public int getColonne() {
        return colonne;
    }
    
    public JButton getBouton() {
        return bouton;
    }
    
    public int getType() {
        return type;
    }
    
    public int getEtat() {
        return etat;
    }
    
    public int getDepuis() {
        return depuis;
    }
    
    
    
    /*** MUTATEURS ***/
    public void setBouton(JButton bouton) {
        this.bouton = bouton;
    }
    
    public void setType(int type) {
        this.type = type;
        etat = 0;
        depuis = 0;
        
        ImageIcon img = new ImageIcon(getClass().getResource("/images/case-type"+type+".png"));
        bouton.setIcon(img);
    }
    
    // Met à jour "l'âge" de la case et change son état si nécessaire
    public void incrementerDepuis() {
        depuis++;
        
        if ((etat == 1 && type == 1 && depuis == 2)
        ||  (etat == 1 && type == 0 && depuis == 4)
        ||  (etat == 1 && type == 4 && depuis == 8)
        ||  (etat == 2 && Math.random() <= 0.4)
        ||  (etat == 3 && depuis == 3))
            incrementerEtat();
    }
    
    public void incrementerEtat() {
        etat++;
        depuis = 0;
        
        // Mise à jour des cases en feu
        if (etat == 1) { // Si on arrive dans un état de feu
            Fenetre_Principale.carte.addCasesEnFeu(this);
        }
        else if (etat == 4) { // Si on sort d'un état de feu
            Fenetre_Principale.carte.removeCasesEnFeu(this);
        }
                
        // Affichage graphique de l'état
        if (Carte.carteGoogle) {
            Fenetre_Principale.carte.setImage(colonne, ligne, (new Color(rgb)).darker().darker().darker().darker().getRGB()); // (new Color(rgb)).brighter().brighter().brighter().getRGB()
        }
        else {
            ImageIcon img = new ImageIcon(getClass().getResource("/images/case-type"+type+"-etat"+etat+".png"));
            bouton.setIcon(img);
        }
    }
    
    
    
    /*** METHODES ***/
    public boolean sansType() {
        if (type == -1)
            return true;
        else
            return false;
    }
    
    // Renvoie vrai si la case existe, càd que ses coordonnées sont contenues dans la carte
    public static boolean existe(int ligne, int colonne) {
        if (ligne>=0 && colonne>=0 && ligne<Carte.getHauteur() && colonne<Carte.getLargeur())
            return true;
        else
            return false;
    }

    // Renvoie vrai si la case peut prendre feu, càd si elle n'est pas de l'eau ni de la terre
    public boolean inflammable() {
        if (type != 2 && type != 3)
            return true;
        else
            return false;
    }
}
