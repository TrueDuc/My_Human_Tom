package com.example.myhumantom.utilitaires;

import com.example.myhumantom.Nutriments;

public class ConstantesJoueur {

    public static final int BASE_ARGENT = 100;
    public static final double[] BASE_TAB_NUTRI_VALEURS = {
        0, 0, 0, 0, 0, 0, 0
    };

    public static final int COEFF_LEVEL = 4; //nombre d'exp qu'il faut par rapport au niveau
    //i.e : il faut (level * COEFF_LEVEL) pour monter d'un niveau.

    public static final int GAIN_EXP_TRAVAIL = 0;
    public static final int GAIN_EXP_MANGER = 0;
    public static final int GAIN_EXP_CUISINE = 0;
    public static final int GAIN_EXP_COMPOSE = 0;

    public static final double COEFF_PERTE_NUTRI_TRAVAIL = 0;
    public static final double COEFF_PERTE_NUTRI_CUISINE = 0;
    public static final double COEFF_PERTE_NUTRI_COMPOSE = 0;

    public static final int[][] INTERVALLE_NUTRIMENTS =
            { {1 , 2} };

    private ConstantesJoueur() {}
}
