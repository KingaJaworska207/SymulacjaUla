package model;

public class Truten extends Pszczola{

    public Truten(int x,int y){
        super(x,y);
    }

    public void jedz(Ul ul){
        ul.zjedzMiod(1);
    }

    @Override
    public void wykonajAkcje() {
        // wywołanie jedzenia
    }

    @Override
    public void ruch(int x, int y) {
        // Ta metoda pozostanie pusta, ponieważ truteń nie wykonuje żadnego ruchu (pozostaje w ulu)
    }
}
