package INFO.java.Projet.Viandes;

import INFO.java.Projet.Composable;
import INFO.java.Projet.Cuisable;
import INFO.java.Projet.Nourriture;
import INFO.java.Projet.Nutriments;
import INFO.java.Projet.Plat;

public abstract class Viande extends Nourriture implements Cuisable, Composable {
    protected Nutriments nutriments;
    private String nom;
    private boolean cuit;

    public Viande(Nutriments nutriment, String nom) { // constructeur de viande
        super(nutriment, nom, -1);
        cuit = false;
    }

    public void cuire() { // cuire 1 viande, cuisable 1 fois max
        if (cuit == false) {
            nutriments.augmenterStates(3.0);
        } else {
            // System.out.println("Impossible, la viande est déjà cuite");
        }
    }

    public void composer(Plat plat) {
        
        
    }


}
