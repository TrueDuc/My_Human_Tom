package INFO.java.Projet;
import INFO.java.Projet.Nourriture;

import java.util.ArrayList;

public class Plat extends Nourriture implements Composable {
    private ArrayList<Nourriture> plat;

    public Plat() { // constructeur de plat
        super(new Nutriments(), "plat à base de :", -1);
        plat = new ArrayList<>();
    }

    public Plat composer(Composable composable) {
        Plat res = new Plat();
        for (Nourriture nourriture : plat) {
            res.ajouter(nourriture, COEFF);
        } 
        res.ajouter(composable, COEFF);
        return res;
    }



    public String getNom() {
        String res = "Plat a base de :";
        for (Nourriture nourriture : plat) {
            res += " " + nourriture.getNom();
        }
        return res;
    }

    public void ajouter(Nourriture nourriture, double coeff) { // plat de 2 aliments 
        plat.add(nourriture);
        this.nutriments.addStates(nourriture.nutriments);
        this.nutriments.augmenterStates(coeff);
    }
    
}
