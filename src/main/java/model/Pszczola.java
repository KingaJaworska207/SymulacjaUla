package model;
/*
* Klasa abstrakcyjna reprezentująca dowolną pszczołe.
* Dziedziczy po klasie Owad.
 */

public abstract class Pszczola extends Owad{

    public Pszczola(int x, int y){
        super(x, y);
    }

    public abstract void wykonajAkcje();

    @Override
    public void wykonajTure(){
        /*
        * ruch
        * wykonanie akcji
        */
    }

}
