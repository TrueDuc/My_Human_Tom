package INFO.java.Projet;

import java.util.ArrayList;

public class Plat extends Nourriture {
    private ArrayList<Nourriture> plat;

    public Plat() { // constructeur de plat
        super(new Nutriments(), "plat à base de :", -1);
        plat = new ArrayList<>();
    }

    public void ajouter(Nourriture nourriture) { // plat de 2 aliments 
        plat.add(nourriture);
        this.nutriments.addStates(nourriture.nutriments);
        this.nutriments.augmenterStates(1.1);
    }

    public void ajouter(Nourriture nourriture1, Nourriture nourriture2) { // plat de 3 aliments MAX
        plat.add(nourriture1);
        this.nutriments.addStates(nourriture1.nutriments);
        plat.add(nourriture2);
        this.nutriments.addStates(nourriture2.nutriments);
        this.nutriments.augmenterStates(1.1);
    }
    
}
