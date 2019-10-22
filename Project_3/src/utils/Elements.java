/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Random;

/**
 *
 * @author Marco Gamboa
 */
public class Elements {

    private int[] percentages;
    private static Random r;

    public Elements() {
        if (r == null) {
            r = new Random();
        }
        percentages = new int[10];
        fillPercentages();
    }

    private void fillPercentages() {
        for (int p : percentages) {
            int random = (r.nextInt(80 + 1) + 20);
            p = random;
        }
    }

    public int[] getPercentages() {
        return percentages;
    }

    public void setPercentages(int[] percentages) {
        this.percentages = percentages;
    }

    public static enum Types {
        Fuego,
        Aire,
        Agua,
        Magia_Blanca,
        Magia_Negra,
        Electricidad,
        Hielo,
        Acid,
        Espiritualidad,
        Hierro
    }

}
