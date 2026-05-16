package model;
/*
* Klasa reprezentująca Strażniczkę
* Dziedziczy po klasie Pszczoła
 */

public class Strazniczka extends Pszczola{

    public Strazniczka(int x, int y){
        super(x, y);
    }

    public void eliminuj(Szerszen szerszen){
        // eliminacja napotkanego szerszenia
    }

    @Override
    public void wykonajAkcje(){
        // jeżeli napotka szerszenia to go eliminuje
    }

    @Override
    public void ruch(int x, int y){
        this.setX(x);
        this.setY(y);
    }

}
