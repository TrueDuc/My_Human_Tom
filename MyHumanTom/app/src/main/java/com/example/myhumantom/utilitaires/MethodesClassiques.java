package com.example.myhumantom.utilitaires;

public class MethodesClassiques {

    private MethodesClassiques() {}

    public static boolean estDansTab(int val, int[] tab) {
        for (int v : tab) {
            if (val == v) {
                return true;
            }
        }
        return false;
    }

}
