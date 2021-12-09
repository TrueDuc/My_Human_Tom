package com.example.myhumantom.utilitaires;

import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.aliments.Plat;

public interface Composable {
    public Plat composer(Composable composable);
}
