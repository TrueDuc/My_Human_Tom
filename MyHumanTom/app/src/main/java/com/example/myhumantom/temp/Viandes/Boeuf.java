package INFO.java.Projet.Viandes;

import java.io.ObjectInputStream.GetField;

public class Boeuf extends Viande {

    public Boeuf() {
        super(1,
                new Nutriments(new double[]{1, 2, 3, 4, 5, 6, 7}),
                R.drawable.ic_launcher_background);
    }

    @Override
    public String getNom() {
        return "Boeuf (Viande)";
    }

    @NonNull
    @Override
    public Nourriture clone() {
        return new Boeuf();
    }
}
