package com.example.myhumantom.aliments;

import com.example.myhumantom.Nutriments;

public class Nourriture {
    private Nutriments nutriments;

    public Nutriments getNutriments() {
        return nutriments;
    }

    public String getNom() {
        return "";
    }

    public int getImage() {
        return 0;
    }

    public int getPrix() {
        return 1;
    }

    public Nourriture clone() {
        return new Nourriture();
    };
}
