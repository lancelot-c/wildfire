/*
 * Projet FEU DE VEGETATION - Rapport du 3 avril 2014
 * VIGUIER Benoit et CHARDONNET Lancelot
 * EPF - 2ème année - Groupe Ab
 * 
 * Carte.java
 */
package feuvegetation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/*
 * DEFINITIONS
 * 
 * Case existante :
 * une case existe si ses coordonnées (x;y) repectent 0<=x<HAUTEUR et 0<=y<LARGEUR
 * 
 * Case potentiellement inflammable :
 * une case est potentiellement inflammable si elle est contenue dans un rayon de Proba.RAYON autour d'une case enflammée
 * 
 * Case inflammable : 
 * une case est inflammable si son terrain peut s'enflammer
 * 
 */
public class Carte {
    /*** CONSTANTES ***/
        // Mode 2D
    public final static int TAILLE_ICONES = 32;
    public final static int NB_ICONES_HAUTEUR = 19;
    public final static int NB_ICONES_LARGEUR = 19;
    public final static int NB_ICONES = NB_ICONES_HAUTEUR*NB_ICONES_LARGEUR;
    
        // Mode Google Maps
    public final static int NB_PIXELS_HAUTEUR = NB_ICONES_HAUTEUR*TAILLE_ICONES;
    public final static int NB_PIXELS_LARGEUR = NB_ICONES_LARGEUR*TAILLE_ICONES;
    public final static int NB_PIXELS = NB_PIXELS_HAUTEUR*NB_PIXELS_LARGEUR;
    
    
    
    /*** ATTRIBUTS ***/
    private int type;           // 0=Clairsemée 1=Espacée   2=Touffue   3=Continue
    private int intensite_vent; // 0=Nul        1=Modéré    2=Fort      3=Violent
    private int direction_vent; // 0=Sud        1=Ouest     2=Nord      3=Est
    private int humidite;       // 0=Très_sec   1=Sec       2=Normal    3=Humide
    private Case[][] cases;
    private LinkedList<Case> casesEnFeu; // cases enflammées, brulées chaud ou brulées froid
    private double[][] probPropFeu;
    public static boolean simulationEnCours = false;
    public static boolean carteGoogle;
    private int dureeSimulation;
    private BufferedImage image; // Uniquement en mode Google Maps
    private int[] infosMeteo;    // Uniquement en mode Google Maps
    public static Timer timer;
    
    
    
    /*** CONSTRUCTEURS ***/
        // Constructeur de chargement d'une carte
    public Carte(String chemin) {
        this(0);
        
        try {
              FileInputStream fichier = new FileInputStream(new File(chemin));
              ObjectInputStream ois = new ObjectInputStream(fichier);
              cases = (Case[][])ois.readObject();
            } catch (IOException ex) {
                System.out.println("Erreur lors du chargement de la carte "+chemin+" : "+ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("Erreur lors de la récupération du contenu du fichier : "+chemin+" : "+ex.getMessage());
            }
    }
        
        // Constructeur mode 2D
    public Carte(int type) {
        carteGoogle = false;
        
        this.type = type;
           
        casesEnFeu = new LinkedList();
        cases = new Case[NB_ICONES_HAUTEUR][NB_ICONES_LARGEUR];
        for (int ligne = 0; ligne < NB_ICONES_HAUTEUR; ligne++)
            for (int colonne = 0; colonne < NB_ICONES_LARGEUR; colonne++)
                cases[ligne][colonne] = new Case(ligne, colonne);
    }
    
        // Constructeur mode Google Maps
    public Carte(String ville, int zoom) {
        carteGoogle = true;
        casesEnFeu = new LinkedList();
        infosMeteo = Google.Weather(ville);
        
        humidite = infosMeteo[3];
        intensite_vent = infosMeteo[4];
        direction_vent = infosMeteo[5];
        
        image = Google.Maps(ville, "satellite", zoom);
        
        casesEnFeu = new LinkedList();
        cases = new Case[NB_PIXELS_HAUTEUR][NB_PIXELS_LARGEUR];
        for (int ligne = 0; ligne < NB_PIXELS_HAUTEUR; ligne++)
            for (int colonne = 0; colonne < NB_PIXELS_LARGEUR; colonne++)
                cases[ligne][colonne] = new Case(ligne, colonne, image.getRGB(colonne, ligne));
    }
    
    public Carte()
    {
    
    }
    
    
    
    /*** ACCESSEURS ***/
    public static int getHauteur() {
        return (carteGoogle) ? NB_PIXELS_HAUTEUR : NB_ICONES_HAUTEUR;
    }
    
    public static int getLargeur() {
        return (carteGoogle) ? NB_PIXELS_LARGEUR : NB_ICONES_LARGEUR;
    }
    
    public static int getTaille() {
        return (carteGoogle) ? NB_PIXELS : NB_ICONES;
    }
    
    public Case[][] getCases() {
        return cases;
    }
    
        // Retourne la case de coordonnées (ligne, colonne)
    public Case getCase(int ligne, int colonne) {
        return cases[ligne][colonne];
    }
    
    public LinkedList<Case> getCasesEnFeu() {
        return casesEnFeu;
    }
     
    public void addCasesEnFeu(Case c) {
        casesEnFeu.add(c);
    }
    
    public void removeCasesEnFeu(Case c) {
        casesEnFeu.remove(c);
    }
    
    public double getProbPropFeu(int ligne, int colonne) {
        return probPropFeu[ligne][colonne];
    }

    public int getIntensiteVent() {
        return intensite_vent;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public int[] getInfosMeteo() {
        return infosMeteo;
    }
    
    
    /*** MUTATEURS ***/
    public void setCase(int ligne, int colonne, Case c) {
        cases[ligne][colonne] = c;
    }
    
    public void setCases(Case cases[][]) {
        this.cases = cases;
    }
    
    public void setImage(int colonne, int ligne, int nouvelleCouleur) {
        image.setRGB(colonne, ligne, nouvelleCouleur);
    }
    
    
    
    /*** METHODES ***/
    // Appelé lors du clic sur une case de la carte
    public void debuterSimulation(Case caseDepart, int intensite_vent, int direction_vent, int humidite, int dureeSimulation) {
        // Si la case cliquée ne peut être enflammée, on ne fait rien
        if (!caseDepart.inflammable() || caseDepart.getEtat() != 0) // Rajouter "|| simulationEnCours" si l'on ne souhaite avoir qu'une simulation à la fois
            return;
        
        
        // Récupération des données climatiques
        this.intensite_vent = intensite_vent;
        this.direction_vent = direction_vent;
        this.humidite = humidite;
        this.dureeSimulation = dureeSimulation;
        this.probPropFeu = Proba.getProbabiliteesDePropagationDuFeu(intensite_vent, direction_vent, humidite);
        
        // Allumage du feu à la case cliquée
        caseDepart.incrementerEtat();
        
        // Propagation du feu
        if (simulationEnCours) // Pour éviter d'avoir plusieurs tours de simulation en même temps
            return;
        
        simulationEnCours = true;
        timer = new Timer();
        timer.schedule(new TimerTask()
            {
              @Override
              public void run()
              {
                TourDeSimulation();
                Fenetre_Principale.actualiserCartePendantSimulation();
                
                if(simulationTerminee()) {
                    stopperSimulation();
                }
              }
            }, 0, dureeSimulation);

    }

    // Cette fonction réalise un tour de simulation, elle est donc répétée jusqu'à ce que le feu cesse
    private void TourDeSimulation() {
        int l1, c1;
        double probFeu;
        
        
        
        LinkedList<Case> copieCasesEnFeu = new LinkedList();
        copieCasesEnFeu.addAll(casesEnFeu);
        for (Case caseEnFeu : copieCasesEnFeu) {
            
            if (caseEnFeu.getEtat() != 3) {
                l1 = caseEnFeu.getLigne();
                c1 = caseEnFeu.getColonne();
                
                // On parcourt donc chaque case alentour dans un rayon de Proba.RAYON, ce sont les cases potentiellement inflammables
                for (int l2 = l1-Proba.RAYON; l2 <= l1+Proba.RAYON; l2++) {
                    for (int c2 = c1-Proba.RAYON; c2 <= c1+Proba.RAYON; c2++) {
                        
                        // Si la case potentiellement inflammable existe, est inflammable
                        // et n'a pas encore été enflammée, alors on tente de l'enflammer
                        if (Case.existe(l2, c2)) { 
                            if (cases[l2][c2].inflammable() && cases[l2][c2].getEtat() == 0) {
                         
                                // On récupère la probabilité qu'a cette case de s'enflammer
                                probFeu = probPropFeu[Proba.RAYON+(l2-l1)][Proba.RAYON+(c2-c1)];

                                // Si la case propagant le feu est dans un état brulé chaud,
                                // la probabilité de propagation du feu est modifiée suivant une formule précise
                                if (caseEnFeu.getEtat() == 2)
                                    probFeu *= 0.005*(1+2*intensite_vent);

                                // Enfin si la probabilité tirée au sort est favorable, on enflamme la case
                                if (Math.random() <= probFeu)
                                    cases[l2][c2].incrementerEtat();
                            }
                        }
                    }
                } 
            }  
            caseEnFeu.incrementerDepuis();
        }          
    }
    
    // La simulation est terminée lorsque chaque case est soit intacte soit en cendres
    public boolean simulationTerminee() {        
        return casesEnFeu.isEmpty();
    }
    
    public static void stopperSimulation() {        
        if (!simulationEnCours)
            return;
        
        timer.cancel();
        simulationEnCours = false;
        System.out.println("Simulation terminée !");
    }
        
    public void detruire() {        
        cases = null;
        casesEnFeu = null;
        probPropFeu = null;
    }
}