package com.example.myhumantom;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.aliments.Plat;
import com.example.myhumantom.exceptions.DejaCuitException;
import com.example.myhumantom.exceptions.NonComposableException;
import com.example.myhumantom.exceptions.NonCuisinableException;
import com.example.myhumantom.utilitaires.Composable;
import com.example.myhumantom.utilitaires.ConstantesJoueur;
import com.example.myhumantom.utilitaires.Cuisinable;
import com.example.myhumantom.utilitaires.Inventaire;
import com.example.myhumantom.utilitaires.Sauvegardable;
import com.example.myhumantom.utilitaires.FichiersJoueur;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Joueur implements Sauvegardable {
    private static final Joueur INSTANCE = new Joueur();

    private int level;
    private int exp;
    private int argent;
    private int momentDuJour; // 0 = Matin, 1 = Midi, 2 = Soir
    private Nutriments nutriments;
    private Inventaire inventaire;

    public static Joueur getInstance() {
        return INSTANCE;
    }

    private Joueur() {}

    public void init(int level, int exp, int argent, int momentDuJour,
                     Nutriments nutriments, Inventaire inventaire) {
        this.level = level;
        this.exp = exp;
        this.argent = argent;
        this.momentDuJour = momentDuJour;
        this.nutriments = nutriments;
        this.inventaire = inventaire;
    }

    public void reset() {
        init(1,
            0,
            ConstantesJoueur.BASE_ARGENT,
            0,
            new Nutriments(ConstantesJoueur.BASE_TAB_NUTRI_VALEURS.clone()),
            new Inventaire());
    }

    public void manger(int position) { //position est la position de la nourriture dans l'inventaire
        Nourriture nourriture = inventaire.getNourritureFromPosition(position);
        inventaire.remove(position);
        nutriments.addNutriments(nourriture.getNutriments());
        addExp(ConstantesJoueur.GAIN_EXP_MANGER);
    }

    public void consommeNutriments(double coeff) {
        nutriments.enleveNutrimentsCoeff(coeff);
    }

    public void travailler() {
        addExp(ConstantesJoueur.GAIN_EXP_TRAVAIL);
        consommeNutriments(ConstantesJoueur.COEFF_PERTE_NUTRI_TRAVAIL);
    }

    public void cuisiner(int position) throws NonCuisinableException, DejaCuitException {
        Nourriture nourriture = inventaire.getNourritureFromPosition(position);

        if (nourriture != null) {
            if (nourriture instanceof Cuisinable) {
                Cuisinable cuisinable = (Cuisinable) nourriture;
                Nourriture nourritureCuite = cuisinable.cuire();

                inventaire.remove(position);
                inventaire.add(nourritureCuite);

                addExp(ConstantesJoueur.GAIN_EXP_CUISINE);
                consommeNutriments(ConstantesJoueur.COEFF_PERTE_NUTRI_CUISINE);
            } else {
                throw new NonCuisinableException();
            }
        }
    }

    public void composer(int position1, int position2) throws NonComposableException {
        Nourriture nourriture1 = inventaire.getNourritureFromPosition(position1);
        Nourriture nourriture2 = inventaire.getNourritureFromPosition(position2);

        if (nourriture1 != null && nourriture2 != null) {
            if (!(nourriture1 instanceof Composable || nourriture2 instanceof Composable)) {
                throw new NonComposableException(nourriture1, nourriture2);
            } else if (!(nourriture1 instanceof Composable)) {
                throw new NonComposableException(nourriture1);
            } else if (!(nourriture2 instanceof Composable)) {
                throw new NonComposableException(nourriture2);
            } else {
                Composable composable1 = (Composable) nourriture1;
                Composable composable2 = (Composable) nourriture2;
                Plat plat = composable1.composer(composable2);

                inventaire.remove(position1);
                inventaire.remove(position2);
                inventaire.add(plat);

                addExp(ConstantesJoueur.GAIN_EXP_COMPOSE);
                consommeNutriments(ConstantesJoueur.COEFF_PERTE_NUTRI_COMPOSE);
            }
        }
    }

    public void levelUp() {
        level++;
    }

    public void addExp(int valeur) {
        exp += valeur;
        if (exp >= level * ConstantesJoueur.COEFF_LEVEL) {
            levelUp();
            exp = exp - (level * ConstantesJoueur.COEFF_LEVEL);
        }
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getArgent() {
        return argent;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public void avancerMomentDuJour() {
        momentDuJour = (momentDuJour + 1) % 3;
    }

    public String getMomentDuJourString() {
        String[] temp = {"Matin", "Midi", "Soir"};
        return temp[momentDuJour];
    }

    @Override
    @NonNull
    public String toString() {
        String res = "";
        res += "Niveau : " + level + "\n";
        res += "Expérience : " + exp + "/" + (level * ConstantesJoueur.COEFF_LEVEL) + "\n";
        res += "Argent : " + argent + "$" + "\n";
        res += "Moment : " + getMomentDuJourString() + "\n";
        res += "Nutriments (énergie) : " + nutriments.toString() + "\n";
        res += "Inventaire : " + inventaire.toString();
        return res;
    }

    @Override
    public void sauvegarder(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(FichiersJoueur.NOM_FICHIER_JOUEUR, Context.MODE_PRIVATE);
        DataOutputStream writer = new DataOutputStream(fos);
        writer.writeInt(level);
        writer.writeInt(exp);
        writer.writeInt(argent);
        writer.writeInt(momentDuJour);
        writer.close();
    }
}
