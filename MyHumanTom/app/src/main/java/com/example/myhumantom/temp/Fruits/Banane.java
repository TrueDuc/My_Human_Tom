package INFO.java.Projet.Fruits;

public class Banane extends Fruit {

    public Banane() {
        super(1,
                new Nutriments(new double[]{1, 2, 3, 4, 5, 6, 7}),
                R.drawable.ic_launcher_background);
    }

    @Override
    public String getNom() {
        return "Banane (Fruit)";
    }

    @NonNull
    @Override
    public Nourriture clone() {
        return new Banane();
    }
}
