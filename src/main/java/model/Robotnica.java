package model;

public class Robotnica extends Pszczola{
    private boolean maNektar;
    private int iloscNektaru;

    public Robotnica(int x,int y){
        super(x,y);
        this.maNektar = false;
        this.iloscNektaru = 0;
    }

    public int zbierzNektar(Kwiat kwiat){
        // logika zebrania nektaru
        return 0;
    }

    public int przekazNektar(Ul ul){
        // logika przekazania nektaru przez metodę dodajMiod
        return 0;
    }

    @Override
    public void wykonajAkcje(){
        // działania robotnicy
    }

    @Override
    public void ruch(int x, int y){
        this.setX(x);
        this.setY(y);
    }
}
