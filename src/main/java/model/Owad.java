package model;
/*
* Klasa abstrakcyjna reprezentująca każdego owada.
* Dziedziczy po klasie Obiekt, dzięki czemu każdy owad posiada pozycję (x, y).
*/

public abstract class Owad extends Obiekt {
    protected boolean Zyje;

    public Owad(int x, int y){
        super(x, y);
        this.Zyje = true;
    }


    public abstract void ruch(int x, int y);

    public abstract void wykonajTure();


    public boolean czyZyje() {
        return Zyje;
    }

    public void zgin(){
        this.Zyje = false;
    }
}
