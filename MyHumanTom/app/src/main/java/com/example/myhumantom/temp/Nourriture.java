package INFO.java.Projet;


public abstract class Nourriture {
    protected int prix;
    protected Nutriments nutriments;
    protected int image;

    public Nourriture(int prix, Nutriments nutriments, int image) {
        this.prix = prix;
        this.nutriments = nutriments;
        this.image = image;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public abstract String getNom();

    public int getImage() {
        return image;
    }

    public int getPrix() {
        return prix;
    }

    @NonNull
    public abstract Nourriture clone();
}