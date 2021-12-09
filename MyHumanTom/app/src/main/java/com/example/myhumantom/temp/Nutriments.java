package INFO.java.Projet;

public class Nutriments {
    private int[] tableau;
    
    // calories, matieresGrasses, sucre, proteines, sel, vitamines, mineraux

    public Nutriments(int[] tableau) { // constructeur de nutriments
        this.tableau = new int[7];
        for(int i = 0; i < 7; i++) {
            this.tableau[i] = tableau[i];
        }
    }

    public void augmenterStates(double multiplicateur) { // permet de multiplier les states des nutriments (quand on cuit/compose)
        for(int i = 0; i < 7; i++) {
            tableau[i] *= multiplicateur;
        }
    }

    public void addStates(Nutriments nutriments) { // permet d'additionner les states de 2 nourritures
        for(int i = 0; i < 7; i++) {
            tableau[i] += nutriments.tableau[i];
        }
    }
}
