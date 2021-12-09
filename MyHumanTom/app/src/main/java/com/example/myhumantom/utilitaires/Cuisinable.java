package com.example.myhumantom.utilitaires;

import com.example.myhumantom.aliments.Nourriture;
import com.example.myhumantom.exceptions.DejaCuitException;

public interface Cuisinable {
    public Nourriture cuire() throws DejaCuitException;
}
