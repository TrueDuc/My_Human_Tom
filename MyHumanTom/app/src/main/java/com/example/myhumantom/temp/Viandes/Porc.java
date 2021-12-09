package INFO.java.Projet.Viandes;

import INFO.java.Projet.Cuisable;
import INFO.java.Projet.Nutriments;

public class Porc extends Viande {

    public Porc() {
        super(1,
                new Nutriments(new double[]{1, 2, 3, 4, 5, 6, 7}),
                R.drawable.ic_launcher_background);
    }

    @Override
    public String getNom() {
        return "Porc (Viande)";
    }

    @NonNull
    @Override
    public Nourriture clone() {
        return new Porc();
    }
}
