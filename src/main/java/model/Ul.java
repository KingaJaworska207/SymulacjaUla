package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca ul.
 * Dziedziczy po klasie Obiekt.
 * Przechowuje zasoby miodu, informacje o wielkości populacji.
 */

public class Ul extends Obiekt{
    /** Aktualna ilośc zgromadzonego w ulu miodu. */
    private int iloscMiodu;
    /** Aktualna ilość pszczół w populacjii. */
    private int populacja;

    /**
     * Inicjalizuje ul na planszy wraz ze startowymi zapasami miodu i populacją.
     *
     * @param x współrzędna x ula
     * @param y współrzędna y ula
     * @param poczatkowyMiod początkowa ilość miodu w ulu
     * @param poczatkowaPopulacja początkowa wielkość populacji pszczół
     */
    public Ul(int x, int y, int poczatkowyMiod, int poczatkowaPopulacja){
        super(x, y);
        this.iloscMiodu = poczatkowyMiod;
        this.populacja = poczatkowaPopulacja;
    }

    /**
     * Zwiększa populację ula o jeden.
     * Wykorzystywana w wyniku reprodukcji.
     */
    public void zwiekszPopulacje(){
        this.populacja++;
    }

    /**
     * Zmniejsza populację ula o jeden.
     * Wykorzystywana w momencie śmierci pszczoły.
     */
    public void zmniejszPopulacje(){
        this.populacja--;
    }

    /**
     * Powiększa zasoby miodu.
     * Wykorzystywana, gdy robotnica przynosi nektar do ula
     *
     * @param ilosc ilość nektaru przekazanego przez Robotnicę do ula
     */
    public void dodajMiod(int ilosc){

        this.iloscMiodu += ilosc;
    }

    /**
     * Zmniejsza zasób miodu o jeden.
     * Wykorzystywana przez Trutnia w momencie jedzenia.
     *
     * @param ilosc ilość miodu do skonsumowania
     */
    public void zjedzMiod(int ilosc){
        if (this.iloscMiodu >= ilosc) {
            this.iloscMiodu -= ilosc;
        }
    }

    /**
     * Pobiera stan zasobów miodu.
     * @return aktualna ilość miodu
     */
    public int getIloscMiodu() {
        return iloscMiodu;
    }

    /**
     * Pobiera wielkość populacji ula.
     * @return aktualna liczba pszczół w populacjii
     */
    public int getLiczbaPopulacji() {
        return populacja;
    }
}
