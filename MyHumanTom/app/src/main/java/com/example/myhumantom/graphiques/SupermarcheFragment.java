package com.example.myhumantom.graphiques;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myhumantom.Joueur;
import com.example.myhumantom.R;
import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.utilitaires.Inventaire;
import com.example.myhumantom.utilitaires.Supermarche;

public class SupermarcheFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_supermarche, container, false);

        ListView supermarcheLV = view.findViewById(R.id.supermarche_listView);
        ListViewAdapterImageText adapter = new ListViewAdapterImageText(getContext(),
                Joueur.getInstance().getInventaire().getTabImage(),
                Joueur.getInstance().getInventaire().getTabNom());
        supermarcheLV.setAdapter(adapter);

        supermarcheLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nourriture res = Supermarche.vendre(position);
                if (res != null) {
                    Toast.makeText(getContext(),
                            "Vous avez bien acheté cet aliment (" + res.getNom() + ") !",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),
                            "Impossible, votre inventaire est rempli (" + Inventaire.TAILLE_MAX_INV + " emplacements max).",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
