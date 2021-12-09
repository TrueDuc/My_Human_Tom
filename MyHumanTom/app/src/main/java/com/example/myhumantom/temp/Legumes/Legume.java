package INFO.java.Projet.Legumes;

import INFO.java.Projet.Composable;
import INFO.java.Projet.Nourriture;
import INFO.java.Projet.Nutriments;
import INFO.java.Projet.Plat;

public abstract class Legume extends Nourriture implements Composable {
    protected Nutriments nutriments;
    private String nom;

    public Legume(Nutriments nutriment, String nom) { // constructeur de legume
        super(nutriment, nom, -1);
        cuit = false;
    }

    public Plat composer(Composable composable) {
        Plat plat = new Plat();
        plat.ajouter(this, 1);
        if (composable instanceof Viande) {
            plat.ajouter(composable, COEFF);
        } else {
            plat.ajouter(composable, COEFF);
        }
        return plat; // a voir
    }
}
