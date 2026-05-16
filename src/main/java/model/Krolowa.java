package model;
/*
* Klasa reprezentująca Królową ula.
* Dziedziczy po klasie Pszczoła.
 */

public class Krolowa extends Pszczola{

    public Krolowa(int x, int y) {
        super(x, y);
    }

    public Pszczola reprodukcja(Ul ul){
        // logika reprodukcji
        return null; // null do zmiany
    }

    @Override
    public void wykonajAkcje(){
        // wywołanie reprodukcji
    }

    @Override
    public void ruch(int x, int y){
        // pozostaje pusta (Królowa nie rusza się nigdy z ula)
    }
}
