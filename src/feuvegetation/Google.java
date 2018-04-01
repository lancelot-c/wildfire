package feuvegetation;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Google {
    
    // Permet de vérifier si l'utilisateur est connecté a internet
    //public static boolean WORKS;
    
    public static BufferedImage Maps(String ville, String type, int zoom) {
        ville = ville.replaceAll(" ", "%20"); // Regex préalable pour éviter des bugs d'url
        BufferedImage onlineMap = null;
        
        try {
            URL url = new URL("http://maps.googleapis.com/maps/api/staticmap?center="+ville+"&zoom="+zoom+"&size="+Carte.getHauteur()+"x"+Carte.getLargeur()+"&sensor=false&maptype="+type);
            URLConnection con = url.openConnection();
            onlineMap = ImageIO.read(con.getInputStream());
            return onlineMap;
        }
        catch (IOException ex) {
            noInternet(ville);
            System.out.println("Erreur lors de la récupération de la carte Google Maps : "+ex.getMessage());
            
            // Copie l'image
            ColorModel cm = Fenetre_Principale.offlineMap.getColorModel();
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            WritableRaster raster = Fenetre_Principale.offlineMap.copyData(null);
            
            return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        }
    }
    
    public static int[] Weather(String ville) {
        int[] infos = {1, 1, 1, 1, 1, 1};
        // infos[0->2] = valeurs exactes
        // infos[3->5] = valeurs approchées, compréhensibles par le programme
        // Exemple : pour 54% 11km/h 286deg on a infos={54, 11, 286, 2, 0, 2}
        
        // Requête Google et récupération du code source html
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.google.fr/search?q="+ville+"+meteo").userAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36").get();
        } catch (IOException ex) {
            System.out.println("Erreur lors de la récupération des données météorologiques de "+ville+" : "+ex.getMessage());
            return infos;
        }

        /*** Humidité ***/
        String stringHumidite = doc.select("#wob_hm").first().text();
        stringHumidite = stringHumidite.substring(0, stringHumidite.length()-1);
        int intHumidite = Integer.parseInt(stringHumidite);
        infos[0] = intHumidite;
        
        if (intHumidite <= 10)
            intHumidite = 0;
        else if (intHumidite <= 35)
            intHumidite = 1;
        else if (intHumidite <= 60)
            intHumidite = 2;
        else if (intHumidite <= 90)
            intHumidite = 3;
        else
            intHumidite = 3;
        infos[3] = intHumidite;
        
        
        /*** Vitesse du vent ***/
        String stringVitesse = doc.select("#wob_ws").first().text();
        stringVitesse = stringVitesse.substring(0, stringVitesse.length()-5);
        int intVitesse = Integer.parseInt(stringVitesse);
        infos[1] = intVitesse;
        
        if (intVitesse <= 5)
            infos[4] = 0;
        else if (intVitesse <= 20)
            infos[4] = 1;
        else if (intVitesse <= 60)
            infos[4] = 2;
        else// if (intVitesse > 60)
            infos[4] = 3;
        
        
        /*** Direction du vent ***/
        String stringDirection = doc.select("[src=//ssl.gstatic.com/m/images/weather/wind_unselected.svg]").first().attr("style");
        
        Pattern p;
        p = Pattern.compile("\\(.+?\\)");
        Matcher m = p.matcher(stringDirection);
        if(m.find())
            stringDirection = m.group();
        stringDirection = stringDirection.substring(0, stringDirection.length()-4).substring(1); // Capture de l'angle de la flèche
        int intDirection = Integer.parseInt(stringDirection);
        infos[2] = intDirection;
        
        intDirection = (Math.round((float)intDirection/90)%4);  // Directions Google :    0:Est 1:Sud   2:Ouest 3:Nord
        intDirection = (intDirection+3)%4;                      // Directions Programme : 0:Sud 1:Ouest 2:Nord  3:Est
        infos[5] = intDirection;
        
        
        // On retourne les infos
        return infos;
    }
    
    public static void noInternet(String ville) {
        JOptionPane.showMessageDialog(null, "Nous n'avons pas réussi à récupérer la carte de "+ville+".\nNéanmoins vous pouvez toujours vous amuser à cramer l'EPF. :)", "Impossible de se connecter à Google", JOptionPane.WARNING_MESSAGE);
    }
}