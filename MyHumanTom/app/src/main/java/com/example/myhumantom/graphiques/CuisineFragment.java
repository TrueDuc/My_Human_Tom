package com.example.myhumantom.graphiques;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myhumantom.Joueur;
import com.example.myhumantom.R;
import com.example.myhumantom.exceptions.DejaCuitException;
import com.example.myhumantom.exceptions.NonComposableException;
import com.example.myhumantom.exceptions.NonCuisinableException;

public class CuisineFragment extends Fragment {

    private int lastPosition = -1;

    private ListViewAdapterImageText adapter;

    private void choixComposer(int position) {
        lastPosition = position;
        Toast.makeText(getContext(), "Choisissez un autre aliment pour le composer avec celui-là",
                Toast.LENGTH_SHORT).show();
    }

    private void essayerComposer(int position) {
        if (position == lastPosition) {
            Toast.makeText(getContext(), "Impossible de composer un aliment avec lui-même !",
                    Toast.LENGTH_SHORT).show();
        } else {
            try {
                Joueur.getInstance().composer(lastPosition, position);
                Toast.makeText(getContext(), "Vous avez composé ces aliments avec merveille !",
                        Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            } catch (NonComposableException e) {
                Toast.makeText(getContext(), String.valueOf(e),
                        Toast.LENGTH_SHORT).show();
            } finally {
                lastPosition = -1;
            }
        }
    }

    private void essayerCuisiner(int position) {
        try {
            Joueur.getInstance().cuisiner(position);
            Toast.makeText(getContext(), "Vous avez cuisiné cet aliment avec merveille !",
                    Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        } catch (NonCuisinableException | DejaCuitException e) {
            Toast.makeText(getContext(), String.valueOf(e),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void cuisinerOuComposer(int position) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Voulez-vous cuisiner ou composer cet alilment ?");
        alertDialog.setMessage("Ces deux procédés augmentent les nutriments que donnent vos aliments !");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Cuisiner",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        essayerCuisiner(position);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Composer",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choixComposer(position);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuisine, container, false);

        ListView cuisineLV = view.findViewById(R.id.cuisine_listView);
        adapter = new ListViewAdapterImageText(getContext(),
                Joueur.getInstance().getInventaire().getTabImage(),
                Joueur.getInstance().getInventaire().getTabNom());
        cuisineLV.setAdapter(adapter);

        cuisineLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lastPosition == -1) {
                    cuisinerOuComposer(position);
                } else {
                    essayerComposer(position);
                }
            }
        });

        return view;
    }
}
