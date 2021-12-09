package INFO.java.Projet.Legumes;

public class Salade {
    public Salade() { 
        super(1, new Nutriments(new double[]{1, 2, 3, 4, 5, 6, 7}),
        R.drawable.ic_launcher_background);
    }

    @Override
    public String getNom() {
        return "Salade (Legume)";
    }

    @NonNull
    @Override
    public Nourriture clone() {
        return new Salade();
    }
    
}
