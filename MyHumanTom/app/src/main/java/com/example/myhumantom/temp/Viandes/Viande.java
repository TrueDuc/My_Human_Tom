package INFO.java.Projet.Viandes;

import com.example.myhumantom.exceptions.DejaCuitException;

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

    public Viande cuire() { // cuire 1 viande, cuisable 1 fois max
        if (cuit == false) {
            nutriments.augmenterStates(3.0);
            return this.clone();
        } else {
        throw new DejaCuitException();
        }
        // a voir
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
