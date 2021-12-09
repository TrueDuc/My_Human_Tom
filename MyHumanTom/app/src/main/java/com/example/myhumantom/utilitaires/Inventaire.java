package com.example.myhumantom.utilitaires;

import android.content.Context;

import com.example.myhumantom.aliments.Nourriture;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Inventaire implements Sauvegardable, Serializable {

    public static final int TAILLE_MAX_INV = 10;
    private final Nourriture[] tabNourriture;
    private int cpt = 0;

    public Inventaire() {
        tabNourriture = new Nourriture[TAILLE_MAX_INV];
    }

    public boolean add(Nourriture nourriture) {
        if (cpt > tabNourriture.length) {
            return false;
        } else {
            tabNourriture[cpt] = nourriture;
            cpt++;
            return true;
        }
    }

    public void remove(int index) {
        if (0 <= index && index < tabNourriture.length) {
            for (int i = index; i < tabNourriture.length - 1; i++) {
                tabNourriture[i] = tabNourriture[i + 1];
            }
            tabNourriture[tabNourriture.length - 1] = null;
            cpt--;
        }
    }

    public Nourriture getNourritureFromPosition(int position) {
        try {
            return tabNourriture[position];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public String[] getTabNom() {
        String[] res = new String[cpt+1];
        for (int i = 0; i < cpt+1; i++) {
            res[i] = tabNourriture[i].getNom();
        }
        return res;
    }

    public int[] getTabImage() {
        int[] res = new int[cpt+1];
        for (int i = 0; i < cpt+1; i++) {
            res[i] = tabNourriture[i].getImage();
        }
        return res;
    }

    @Override
    public void sauvegarder(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(FichiersJoueur.NOM_FICHIER_INVENTAIRE, Context.MODE_PRIVATE);
        ObjectOutputStream writer = new ObjectOutputStream(fos);
        writer.writeObject(this);
        writer.close();
    }

}
