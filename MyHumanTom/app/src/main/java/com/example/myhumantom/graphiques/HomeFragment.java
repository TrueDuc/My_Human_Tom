package com.example.myhumantom.graphiques;

import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myhumantom.Joueur;
import com.example.myhumantom.R;
import com.example.myhumantom.utilitaires.FichiersJoueur;

import java.io.IOException;

public class HomeFragment extends Fragment {

    private void setOnClick(Button button, Fragment fragment) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.container,
                        fragment).commit();
            }
        });
    }

    private void afficherStats(Joueur joueur) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Vos Statistiques");
        alertDialog.setMessage(joueur.toString());
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Fermer",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void sauvegarder() {
        try {
            FichiersJoueur.sauvegardeAll(getContext());
            Toast.makeText(getContext(),
                    "Vos données ont bien été sauvegardées.",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(),
                    "Il y a eu un problème lors de la sauvegarde des données, veuillez réessayer.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Joueur joueur = Joueur.getInstance();

        TextView argentTV = view.findViewById(R.id.argent_textView);
        TextView momentTV = view.findViewById(R.id.moment_textView);
        TextView niveauTV = view.findViewById(R.id.niveau_textView);

        argentTV.setText("Argent : " + joueur.getArgent() + "$");
        momentTV.setText(joueur.getMomentDuJourString());
        niveauTV.setText("Level : " + joueur.getLevel() + "\n" + "Exp : " + joueur.getExp());

        setOnClick(view.findViewById(R.id.manger_button), new MangerFragment());
        setOnClick(view.findViewById(R.id.cuisiner_button), new CuisineFragment());
        setOnClick(view.findViewById(R.id.acheter_button), new SupermarcheFragment());
        setOnClick(view.findViewById(R.id.stonks_button), new StonksFragment());

        Button afficheStatsButton = view.findViewById(R.id.afficheStats_button);
        afficheStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherStats(joueur);
            }
        });

        Button sauvegarderButton = view.findViewById(R.id.sauvegarder_button);
        sauvegarderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sauvegarder();
            }
        });

        return view;
    }
}
