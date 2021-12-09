package INFO.java.Projet.Fruits;

public class Pomme extends Fruit {
    
    public Pomme() {
        super(1, new Nutriments(new double[]{1, 2, 3, 4, 5, 6, 7}),
        R.drawable.ic_launcher_background);
    }

    @Override
    public String getNom() {
        return "Pomme (Fruit)";
    }

    @NonNull
    @Override
    public Nourriture clone() {
        return new Pomme();
    }

}
