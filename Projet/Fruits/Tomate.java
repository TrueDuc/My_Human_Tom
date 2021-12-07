package INFO.java.Projet.Fruits;

import javax.swing.plaf.FontUIResource;

import INFO.java.Projet.Composable;
import INFO.java.Projet.Cuisable;

public class Tomate extends Fruit implements Cuisable, Composable{
    
    public Tomate() {
        super(new Nutriments([1,2,3,4,5,6,7]), "tomate");
    }

    public void composer() {

    }
    
    public void cuire() {

    }
}
