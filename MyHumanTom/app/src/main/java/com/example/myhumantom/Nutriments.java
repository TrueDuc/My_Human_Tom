package com.example.myhumantom;

import android.content.Context;

import com.example.myhumantom.utilitaires.FichiersJoueur;
import com.example.myhumantom.utilitaires.Sauvegardable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Nutriments implements Sauvegardable {

    public static final int NB_NUTRIMENTS = 6;

    private final double[] tabNutri;

    public Nutriments(double[] tabNutri) {
        this.tabNutri = tabNutri;
    }

    public void addNutriments(Nutriments nutriments) {

    }

    public void enleveNutrimentsCoeff(double coeff) {

    }

    public double[] getTabNutri() {
        return tabNutri;
    }

    @Override
    public void sauvegarder(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(FichiersJoueur.NOM_FICHIER_NUTRIMENTS, Context.MODE_PRIVATE);
        ObjectOutputStream writer = new ObjectOutputStream(fos);
        writer.writeObject(this);
        writer.close();
    }
}
