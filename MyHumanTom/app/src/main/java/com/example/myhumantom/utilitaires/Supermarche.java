package com.example.myhumantom.utilitaires;

import com.example.myhumantom.Joueur;
import com.example.myhumantom.aliments.Nourriture;

public class Supermarche {

    public static final Nourriture[] STOCK = {
        new Nourriture(),
        new Nourriture(),
    };

    public static Nourriture vendre(int position) {
        Joueur joueur = Joueur.getInstance();
        Nourriture nourriture = STOCK[position].clone();
        enleverArgentJouer(nourriture.getPrix());
        if (joueur.getInventaire().add(nourriture)) {
            return nourriture.clone();
        }
        return null;
    }

    public static void enleverArgentJouer(int valeur) {
        Joueur joueur = Joueur.getInstance();
        joueur.setArgent(joueur.getArgent() - valeur);
    }

    public static String[] getTabStockNom() {
        String[] res = new String[STOCK.length];
        for (int i = 0; i < STOCK.length; i++) {
            res[i] = STOCK[i].getNom();
        }
        return res;
    }

    public static int[] getTabStockImage() {
        int[] res = new int[STOCK.length];
        for (int i = 0; i < STOCK.length; i++) {
            res[i] = STOCK[i].getImage();
        }
        return res;
    }

}
