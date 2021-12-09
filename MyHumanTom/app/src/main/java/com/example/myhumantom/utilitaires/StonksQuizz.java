package com.example.myhumantom.utilitaires;

import com.example.myhumantom.Joueur;

public class StonksQuizz {
    public static final int MAX_CAGNOTTE = 300;
    public static final int GAIN_BONNE_REP = 20;
    public static final int TEMPS_MAX = 30;

    private static String[] questionEnCours = new String[10];

    public static final String[][] QUESTIONS_4REPONSES = {
    //Format : {Question, BonneRep, FausseRep, FausseRep, FausseRep
            {"testquest", "rep1", "rep2", "rep3", "rep4"}
    };

    private StonksQuizz() {}

    public static void ajouterArgentJoueur(int valeur) {
        Joueur joueur = Joueur.getInstance();
        joueur.setArgent(joueur.getArgent() + valeur);
    }

    private static String[] getQuestionMelange4Reps(int n) {
        String[] res = new String[QUESTIONS_4REPONSES[n].length];
        questionEnCours = QUESTIONS_4REPONSES[n].clone();
        res[0] = questionEnCours[0];

        int rand;
        int[] done = new int[res.length - 1];

        for (int i = 1; i < res.length; i++) {
            do {
                rand = (int) (Math.random() * (res.length - 1)) + 1;
            } while (MethodesClassiques.estDansTab(rand, done));

            done[i-1] = rand;
            res[i] = questionEnCours[rand];
        }
        return res;
    }

    public static String[] getRandomQuestion4Reps() {
        int rand = (int) (Math.random() * StonksQuizz.QUESTIONS_4REPONSES.length);
        return getQuestionMelange4Reps(rand);
    }


    public static boolean verif4Reps(String rep) {
        return questionEnCours[1].equals(rep);
    }

}
