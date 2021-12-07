package INFO.java.Projet;

public abstract class Nourriture {
    protected Nutriments nutriments;
    private String nom;
    private int prix;

    public Nourriture(Nutriments nutriments, String nom, int prix) { // constructeur de nourriture
        this.nutriments = nutriments;
        this.nom = nom;
        this.prix = prix;
    }

    

    
}
