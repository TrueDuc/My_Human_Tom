package com.example.myhumantom.graphiques;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myhumantom.Joueur;
import com.example.myhumantom.R;

public class MangerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manger, container, false);

        ListView mangerLV = view.findViewById(R.id.manger_listView);
        ListViewAdapterImageText adapter = new ListViewAdapterImageText(getContext(),
                Joueur.getInstance().getInventaire().getTabImage(),
                Joueur.getInstance().getInventaire().getTabNom());
        mangerLV.setAdapter(adapter);

        mangerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Joueur.getInstance().manger(position);
            }
        });

        return view;
    }
}
