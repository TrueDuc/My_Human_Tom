package com.example.myhumantom.utilitaires;

import android.content.Context;

import java.io.IOException;
import java.io.Serializable;

public interface Sauvegardable extends Serializable {
    public void sauvegarder(Context context) throws IOException;
}
