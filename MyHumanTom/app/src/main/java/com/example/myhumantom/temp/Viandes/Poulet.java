package INFO.java.Projet.Viandes;

public class Poulet extends Viande {

    public Poulet() {
        super(1,
                new Nutriments(new double[]{1, 2, 3, 4, 5, 6, 7}),
                R.drawable.ic_launcher_background);
    }

    @Override
    public String getNom() {
        return "Poulet (Viande)";
    }

    @NonNull
    @Override
    public Nourriture clone() {
        return new Poulet();
    }
}
