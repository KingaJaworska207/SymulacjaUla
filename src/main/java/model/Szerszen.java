package model;

/**
 * Klasa reprezentująca szerszenia.
 * Dziedziczy po klasie Owad.
 * Odpowiada za eliminowanie napotkanych pszczół.
 */

public class Szerszen extends Owad{

    /**
     * Tworzy nowego szersznia na podanych współrzędnych.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public Szerszen(int x, int y){
        super(x, y);
    }

    /**
     * W przypadku napotkania pszczoły, wykonuje atak.
     */
    public void atakuj(){
        // atak na napotkaną pszczołę
        // (w przypadku napotkania strażniczki 50/50 szansa na wygrannie starcia?)
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
