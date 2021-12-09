package INFO.java.Projet.Legumes;

public class Carotte implements Cuisable {
    private boolean cuit;
    
    public Carotte() { 
        super(1, new Nutriments(new double[]{1, 2, 3, 4, 5, 6, 7}),
        R.drawable.ic_launcher_background);
    }

    @Override
    public String getNom() {
        return "Carotte (Legume)";
    }

    @NonNull
    @Override
    public Nourriture clone() {
        return new Carotte();
    }

    public Nourriture cuire() { 
        if (cuit == false) {
            nutriments.augmenterStates(STATE);
            return this.clone();
        } else {
        throw new DejaCuitException();
        }
        // a voir
    }
}

