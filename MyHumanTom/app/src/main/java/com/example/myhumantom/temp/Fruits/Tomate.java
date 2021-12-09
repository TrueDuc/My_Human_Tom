package INFO.java.Projet.Fruits;

import javax.swing.plaf.FontUIResource;

import INFO.java.Projet.Composable;
import INFO.java.Projet.Cuisable;
import INFO.java.Projet.Nourriture;

public class Tomate extends Fruit implements Cuisable, Composable{
    private boolean cuit;
    
    public Tomate() {
        super(1, new Nutriments(new double[]{1, 2, 3, 4, 5, 6, 7}),
        R.drawable.ic_launcher_background);
    }

    @Override
    public String getNom() {
        return "Tomate (Fruit)";
    }

    @NonNull
    @Override
    public Nourriture clone() {
        return new Tomate();
    }

    public Nourriture cuire() { // cuire 1 viande, cuisable 1 fois max
        if (cuit == false) {
            nutriments.augmenterStates(STATE);
            return this.clone();
        } else {
        throw new DejaCuitException();
        }
        // a voir
    }

    public Plat composer(Composable composable) {
        Plat plat = new Plat();
        plat.ajouter(this, 1);
        plat.ajouter(composable, COEFF);
        return plat; // a voir
    }
}
