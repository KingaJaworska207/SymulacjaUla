package model;

import java.util.Random;

/**
 * Klasa reprezentująca szerszenia.
 * Dziedziczy po klasie Owad.
 * Odpowiada za eliminowanie napotkanych pszczół.
 */

public class Szerszen extends Owad{

    /** Generator liczb losowych */
    private Random random;

    /** Szansa na wygraną szerszenia w starciu ze strażniczką (50% = 0.5) */
    private static final double szansaWygranej = 0.5;

    /**
     * Tworzy nowego szersznia na podanych współrzędnych.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param plansza referencja do planszy
     */
    public Szerszen(int x, int y, Plansza plansza){
        super(x, y, plansza);
    }

    /**
     * Sprawdza czy na obecnej pozycji znajduje się pszczoła.
     *
     * @return pszczoła znajdująca się na tej samej pozycji lub null
     */
    public Pszczola napotkaniePszczoly(){
        //sprawdzenie czy na polu na ktorym znajduje się szerszeń jest też pszczoła
        return null;
    }

    /**
     * W przypadku napotkania pszczoły, wykonuje atak.
     * Logika ataku:
     * - Jeśli pszczoła to Robotnica -> szerszeń zawsze wygrywa
     * - Jeśli pszczoła to Strażniczka -> 50% szans na wygraną
     * - W przypadku przegranej ze Strażniczką -> szerszeń ginie
     */
    public void atakuj(Pszczola pszczola){
        if (pszczola == null) {
            return;
        }
        // Rozróżnienie typu pszczoły
        if (pszczola instanceof Robotnica) {
            // Atak na robotnicę - zawsze udany
            pszczola.zgin();
        }
        else if (pszczola instanceof Strazniczka) {
            // Atak na strażniczkę - 50% szans
            double los = random.nextDouble();
            if (los < szansaWygranej) {
                // Szerszeń wygrywa - strażniczka ginie
                pszczola.zgin();
            } else {
                // Szerszeń przegrywa - sam ginie
                this.zgin();
            }
        }
    }

    /**
     * Zmienia pozycję robotnicy na planszy w sposób losowy.
     *
     * @param x nowa współrzędna x, na którą ma przesunąć się owad
     * @param y nowa współrzędna y, na którą ma przesunąć się owad
     */
    @Override
    public void ruch(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Przeprowadza pełną turę szerszenia.
     * Obejmuje to wykonanie ruchu oraz specyficznej akcji.
     */
    @Override
    public void wykonajTure(){
        // losowy ruch
        // atak jeżeli napotka pszczołę
    }
}
