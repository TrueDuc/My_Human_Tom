package INFO.java.Projet.Fruits;

import INFO.java.Projet.Nourriture;
import INFO.java.Projet.Nutriments;

public abstract class Fruit extends Nourriture {
    protected Nutriments nutriments;
    private String nom;

    public Fruit(Nutriments nutriment, String nom) { // constructeur de fruit
        super(nutriment, nom, -1);
    }

}
