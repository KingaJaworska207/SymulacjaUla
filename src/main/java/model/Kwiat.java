package model;
/*
* Klasa reprezentująca kwaity.
* Dziedziczy po klasie Obiekt.
 */

public class Kwiat extends Obiekt{
    private int iloscNektaru;
    private boolean Zebrany;

    public Kwiat(int x,int y, int poczatkowyNektar){
        super(x,y);
        this.iloscNektaru = poczatkowyNektar;
        this.Zebrany = false;
    }

    public boolean czyZebrany(){
        return Zebrany;
    }

    public void setZebrany(){
        this.Zebrany = true;
    }
}
