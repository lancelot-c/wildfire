/*
 * Projet FEU DE VEGETATION - Rapport du 3 avril 2014
 * VIGUIER Benoit et CHARDONNET Lancelot
 * EPF - 2ème année - Groupe Ab
 * 
 * Proba.java
 */
package feuvegetation;

import java.util.Arrays;


/*
 * La classe Proba a pour unique but de gérer les probabilités de notre programme.
 * 
 * La méthode principale, getProbabiliteesDePropagationDuFeu([...]), renvoie le tableau
 * des probabilitées de propagation du feu autour d'une case enflammée en fonction des
 * données météorologie de la carte (intensité du vent, direction du vent, humidité)
 */
public class Proba {
    public final static int TAILLE = 7; // Doit être un nombre impair
    public final static int RAYON = (TAILLE-1)/2;
    
    public final static double[][] VentNordNul =   {{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.20, 0.30, 0.20, 0.00, 0.00},
                                                    {0.00, 0.00, 0.30, 1.00, 0.30, 0.00, 0.00},
                                                    {0.00, 0.00, 0.20, 0.30, 0.20, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00}};
    
    public final static double[][] VentNordModere ={{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.10, 0.20, 0.10, 0.00, 0.00},
                                                    {0.00, 0.00, 0.30, 1.00, 0.30, 0.00, 0.00},
                                                    {0.00, 0.00, 0.30, 0.40, 0.30, 0.00, 0.00},
                                                    {0.00, 0.00, 0.02, 0.05, 0.02, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00}};
    
    public final static double[][] VentNordFort =  {{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.05, 0.10, 0.05, 0.00, 0.00},
                                                    {0.00, 0.00, 0.25, 1.00, 0.25, 0.00, 0.00},
                                                    {0.00, 0.00, 0.40, 0.50, 0.40, 0.00, 0.00},
                                                    {0.00, 0.00, 0.05, 0.10, 0.05, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.01, 0.00, 0.00, 0.00}};
        
    public final static double[][] VentNordViolent={{0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00},
                                                    {0.00, 0.00, 0.10, 1.00, 0.10, 0.00, 0.00},
                                                    {0.00, 0.00, 0.50, 0.70, 0.50, 0.00, 0.00},
                                                    {0.00, 0.00, 0.20, 0.30, 0.20, 0.00, 0.00},
                                                    {0.00, 0.00, 0.01, 0.05, 0.01, 0.00, 0.00}};
    
    // Tourne un tableau de double d'un quart de tour dans le sens horaire
    public static double[][] rotationHoraire(double[][] tab) {
            double[][] newTab = new double[TAILLE][TAILLE];

            for (int i = 0; i < TAILLE; i++)
                for (int j = 0; j < TAILLE; j++)
                    newTab[i][j] = tab[TAILLE-1-j][i];

            return newTab;
   }

    // Renvoie le tableau des probabilitées finales, après prise en compte de toutes les données météorologiques
    public static double [][] getProbabiliteesDePropagationDuFeu(int intensite_vent, int direction_vent, int humidite){
        double [][] tab = new double[TAILLE][TAILLE];

        // Prise en compte de l'intensité du vent
        switch (intensite_vent)
        {
          case 0:
            tab = Arrays.copyOf(VentNordNul, TAILLE);
            break;
          case 1:
            tab = Arrays.copyOf(VentNordModere, TAILLE);
            break;
          case 2:
            tab = Arrays.copyOf(VentNordFort, TAILLE);
            break;
          case 3 :
            tab = Arrays.copyOf(VentNordViolent, TAILLE);
            break;
        }

        
        // Prise en compte de la direction du vent
        for (int i=0; i<direction_vent; i++)
            tab = rotationHoraire(tab);   
        
        
        // Prise en compte de l'humidité
        tab = multiplicationTabHumidite(humidite, tab);
        return tab;
    }

    // Multiplie toutes les probabilitées du tableau par un nombre en fonction de l'humidité
    public static double [][] multiplicationTabHumidite(int humidite, double tab[][]){
        double x = 1.0;
        switch (humidite)
        {
          case 0:   x = 0.90;   break;
          case 1:   x = 0.60;   break;
          case 2:   x = 0.35;   break;
          case 3:   x = 0.10;   break;
        }
        
        for (int i=0; i<TAILLE; i++){
            for (int j=0; j<TAILLE; j++)
                tab[i][j] *= x;
        }
        return tab;
    }
}

