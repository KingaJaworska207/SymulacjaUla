package model;

/**
 * Klasa reprezentująca strażniczkę.
 * Dziedziczy po klasie Pszczola.
 * Odpowiada za eliminowanie napotkanych szerszeni.
 */

public class Strazniczka extends Pszczola{

    /**
     * Tworzy nową strażniczkę na podanych współrzędnych i przypisuje ją do ula.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param ul obiekt ula, do którego zostaje przypisana strażniczka
     */
    public Strazniczka(int x, int y, Ul ul){
        super(x, y, ul);
    }

    /**
     * W przypadku napotkania szerszenia, eliminuje go.
     *
     * @param szerszen napotkany obiekt szerszenia
     */
    public void eliminuj(Szerszen szerszen){
        // eliminacja napotkanego szerszenia
        // (50/50 szansa na wygraną starcie?)
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
