package com.example.myhumantom.graphiques;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myhumantom.Joueur;
import com.example.myhumantom.R;
import com.example.myhumantom.utilitaires.StonksQuizz;

public class StonksFragment extends Fragment {

    private int cptBonneRep;

    private int cagnotte; // gère l'argent accumulé
    private TextView cagnotteTV;

    private int tempsRestant; // (en seconde) variable qui gère le temps restant pour répondre à la question
    private Handler handler;
    private Runnable runnable; // gestionneurs générals du timer
    private TextView timerTV;

    private TextView questionTV;
    private Button rep1Button, rep2Button, rep3Button, rep4Button;

    private void goHome() {
        Joueur joueur = Joueur.getInstance();
        joueur.travailler();
        joueur.avancerMomentDuJour();
        getParentFragmentManager().beginTransaction().replace(R.id.container,
                new HomeFragment()).commit();
    }

    private void updateCagnotte() {
        cagnotte += StonksQuizz.GAIN_BONNE_REP * cptBonneRep;
        cagnotteTV.setText("Cagnotte : " + cagnotte + "$");
        cptBonneRep++;
    }

    private void initialiseButton4Reps(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable); //stop le timer

                if (StonksQuizz.verif4Reps(button.getText().toString())) {
                    updateCagnotte();
                    if (cagnotte >= StonksQuizz.MAX_CAGNOTTE) {
                        StonksQuizz.ajouterArgentJoueur(StonksQuizz.MAX_CAGNOTTE);
                        Toast.makeText(getContext(), "Bravo ! Vous gagnez la somme maximale (" +
                                        StonksQuizz.MAX_CAGNOTTE + "$) !", Toast.LENGTH_SHORT).show();
                        goHome();
                    } else {
                        Toast.makeText(getContext(), "Bonne réponse ! Vous ajoutez " +
                                (StonksQuizz.GAIN_BONNE_REP * (cptBonneRep-1)) + "$ à votre cagnotte !",
                                Toast.LENGTH_SHORT).show();
                        continuerOuPas();
                    }
                } else {
                    Toast.makeText(getContext(), "Mauvaise réponse, dommage vous ne gagnez rien...",
                            Toast.LENGTH_SHORT).show();
                    goHome();
                }
            }
        });
    }

    private void startTimer() {
        tempsRestant = StonksQuizz.TEMPS_MAX;
        int delai = 1000;
        timerTV.setText(String.valueOf(tempsRestant));

        runnable = new Runnable() {
            @Override
            public void run() {
                tempsRestant--;
                timerTV.setText(String.valueOf(tempsRestant));
                if (tempsRestant == 0) {
                    handler.removeCallbacks(this);
                    Toast.makeText(getContext(),
                            "Le temps s'est écoulé, vous avez perdu et ne gagnez rien...",
                            Toast.LENGTH_SHORT).show();
                    goHome();
                }
                handler.postDelayed(this, delai);
            }
        };
        runnable.run();
    }

    private void startQuestion4Reps() {
        String[] question4Reps = StonksQuizz.getRandomQuestion4Reps();
        questionTV.setText(question4Reps[0]);
        rep1Button.setText(question4Reps[1]);
        rep2Button.setText(question4Reps[2]);
        rep3Button.setText(question4Reps[3]);
        rep4Button.setText(question4Reps[4]);
        startTimer();
    }

    private void continuerOuPas() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setCancelable(false);
        alertDialog.setTitle("Voulez-vous continuer ?");
        alertDialog.setMessage("Vous perdez toute votre cagnotte si vous répondez faux.");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startQuestion4Reps();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        StonksQuizz.ajouterArgentJoueur(cagnotte);
                        Toast.makeText(getContext(), "Bravo ! Vous gagnez " +
                                cagnotte + "$ !", Toast.LENGTH_SHORT).show();
                        goHome();
                    }
                });
        alertDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stonks, container, false);

        cptBonneRep = 0;
        cagnotte = 0;
        cagnotteTV = view.findViewById(R.id.cagnotte_textView);
        updateCagnotte();

        handler = new Handler();
        timerTV = view.findViewById(R.id.timer_textView);

        questionTV = view.findViewById(R.id.question_textView);
        rep1Button = view.findViewById(R.id.rep1_button);
        rep2Button = view.findViewById(R.id.rep2_button);
        rep3Button = view.findViewById(R.id.rep3_button);
        rep4Button = view.findViewById(R.id.rep4_button);
        initialiseButton4Reps(rep1Button);
        initialiseButton4Reps(rep2Button);
        initialiseButton4Reps(rep3Button);
        initialiseButton4Reps(rep4Button);

        startQuestion4Reps();

        return view;
    }
}
