package modele;

import java.util.Random;

public class Relation {

    public Relation(){}

    // Module de construction de pseudo pour DAP
    public String Pseudo(int taille) {
        Random rand = new Random();
        String pseudo = "";
        for (int i = 0; i < taille; i++) {
            char c = (char)(rand.nextInt(26)+97);
            pseudo += c;
        }
        return pseudo.toUpperCase();
    }
}
