package com.example.myhumantom.graphiques;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myhumantom.Joueur;
import com.example.myhumantom.R;
import com.example.myhumantom.graphiques.HomeFragment;
import com.example.myhumantom.utilitaires.FichiersJoueur;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private void goHome() {
        findViewById(R.id.testss).setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                new HomeFragment()).commit();
    }

    private void jouer() {
        try {
            FichiersJoueur.initJoueur(getApplicationContext());
            goHome();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Il y a eu un problème lors du lancement du jeu, veuillez réessayer.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private int cptRecommence = 0;
    private void recommencer() {
        if (cptRecommence == 0) {
            Toast.makeText(getApplicationContext(),
                    "Êtes-vous sûr de vouloir recommencer ? Réappuyez sur le bouton si oui",
                    Toast.LENGTH_SHORT).show();
            cptRecommence++;
        } else {
            Joueur.getInstance().reset();
            Toast.makeText(getApplicationContext(),
                    "Toutes vos données ont bien été réinitialisées.",
                    Toast.LENGTH_SHORT).show();
            goHome();
        }
    }

    private void showRegles() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Règles");
        alertDialog.setMessage("TODO"); //TODO
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jouerButton = findViewById(R.id.jouer_button);
        jouerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jouer();
            }
        });

        Button recommencerButton = findViewById(R.id.nouvellePartie_button);
        recommencerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommencer();
            }
        });

        Button reglesButton = findViewById(R.id.regles_button);
        reglesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegles();
            }
        });
    }

    private long backPressTime;
    @Override
    public void onBackPressed() { //gestion du bouton de retour android
        if (System.currentTimeMillis() - backPressTime < 2000) { //fermer si 2 clicks en moins de 2 secondes
            finish();
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Réappuyez pour fermer.", Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }
}