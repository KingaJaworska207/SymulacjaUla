package model;

/*
* Klasa reprezentująca szerszenia.
* Dziedziczy po klasie Owad.
 */

public class Szerszen extends Owad{


    public Szerszen(int x, int y){
        super(x, y);
    }

    public void atakuj(){
        // atak na napotkaną pszczołę
    }
    @Override
    public void ruch(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void wykonajTure(){
        // losowy ruch
        // atak jeżeli napotka pszczołę
    }
}
