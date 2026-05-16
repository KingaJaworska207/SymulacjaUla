package model;

import java.util.ArrayList;
import java.util.List;

/*
* Klasa reprezentująca ul.
* Dziedziczy po klasie Obiekt.
* Przechowuje zasoby miodu, informacje o populacji oraz listę robotnic.
 */

public class Ul extends Obiekt{
    private int iloscMiodu;
    private int populacja;

    private List<Robotnica> listaRobotnic;

    public Ul(int x, int y, int poczatkowyMiod, int poczatkowaPopulacja){
        super(x, y);
        this.iloscMiodu = poczatkowyMiod;
        this.populacja = poczatkowaPopulacja;
        this.listaRobotnic = new ArrayList<>();
    }

    public void dodajMiod(int ilosc){
        this.iloscMiodu += ilosc;
    }

    public void zjedzMiod(int ilosc){
        if (this.iloscMiodu >= ilosc) {
            this.iloscMiodu -= ilosc;
        }
    }

    public int getIloscMiodu() {
        return iloscMiodu;
    }

    public int getLiczbaPopulacji() {
        return populacja;
    }
}
