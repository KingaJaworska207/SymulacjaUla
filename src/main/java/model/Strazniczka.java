package model;

import java.util.Random;

/**
 * Klasa reprezentująca strażniczkę.
 * Dziedziczy po klasie Pszczola.
 * Odpowiada za eliminowanie napotkanych szerszeni.
 */

public class Strazniczka extends Pszczola{

    /** Generator liczb losowych */
    private Random random;

    /** Szansa na wygraną strażniczki w starciu z szerszeniem (50% = 0.5) */
    private static final double szansaWygranej = 0.5;

    /**
     * Tworzy nową strażniczkę na podanych współrzędnych i przypisuje ją do ula.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param ul obiekt ula, do którego zostaje przypisana strażniczka
     * @param plansza referencja do planszy
     */
    public Strazniczka(int x, int y, Ul ul, Plansza plansza) {
        super(x, y, ul, plansza);
    }

    /**
     * Sprawdza czy na obecnej pozycji znajduje się szerszen.
     *
     * @return szerszen znajdujący się na tej samej pozycji lub null
     */
    public Szerszen napotkanySzerszen(){
        //sprawdzenie czy na polu na ktorym znajduje się strażniczka jest też szerszeń
        return null;
    }

    /**
     * W przypadku napotkania szerszenia, ma szanse na wyeliminowanie go.
     *
     * @param szerszen napotkany obiekt szerszenia
     */
    public void eliminuj(Szerszen szerszen){

        if (szerszen == null || !szerszen.czyZyje()) {
            return;
        }

        // Losowanie wyniku starcia - 50% szans na wygraną
        double los = random.nextDouble();
        if (los < szansaWygranej) {
            // Strażniczka wygrywa - eliminuje szerszenia
            szerszen.zgin();
        } else {
            // Strażniczka przegrywa - sama ginie
            this.zgin();
        }
    }

    /**
     * Wykonanie przypisanej dla strażniczki akcji w danej turze.
     */
    @Override
    public void wykonajAkcje(){
        // jeżeli napotka szerszenia to go eliminuje, jak nie to robi ruch
    }

    /**
     * Zmienia pozycję robotnicy na planszy w sposób losowy.
     *
     * @param x nowa współrzędna x, na którą ma przesunąć się owad
     * @param y nowa współrzędna y, na którą ma przesunąć się owad
     */
    @Override
    public void ruch(int x, int y){
        this.setX(x);
        this.setY(y);
    }

}
