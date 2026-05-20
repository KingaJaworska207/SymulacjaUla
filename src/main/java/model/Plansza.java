package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca dwuwymiarową przestrzeń symulacji.
 * Zarządza siatka obiektów i ich rozmieszczeniem.
 */

public class Plansza {
    /** Szerokość planszy. */
    private int szerokosc;
    /** Wysokośc planszy. */
    private int wysokosc;

    /** Tablica dwuwymiarowa przechowująca obiekty stacjonarne (ul, kwiaty) na konkretnych koordynatach */
    private Obiekt[][] siatka;

    /** Lista przechowująca aktywne owady */
    private List<Owad> aktywneOwady;

    /**
     * Tworzy nową planszę o konkretnych wymiarach.
     * Inicjuje pustą siatke obiektów stacjonarnych (ul, kwiaty) i pustą listę aktywnych owadów.
     *
     * @param szerokosc ustalona szerokośc planszy
     * @param wysokosc ustalona wysokość planszy
     */
    public Plansza(int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.siatka = new Obiekt[szerokosc][wysokosc];
        this.aktywneOwady = new ArrayList<Owad>();
    }

    /**
     * Generuje początkowe ustawienie elementów na planszy.
     * Rozmieszcza startowe obiekty, takie jak ul, pierwsze kwiaty i początkową populację owadów.
     */
    public void generujPoczatkowaPlansze(){
        // stworzenie poczatkowego ustawienia obiektow na planszy
    }

    /**
     * Sprawdza, czy na podanych współrzędnych siatki znajduje się już jakiś obiekt.
     *
     * @param x współrzędna x do sparwdzenia
     * @param y współrzędna y do sparwdzenia
     * @return true jeśli pole jest zajęte, false jeżeli jest wolne
     */
    public boolean czyPoleZajete(int x, int y){
        // sprawdzamy czy coś zanjduje się na danej pozycji
        return false;
    }

    /**
     * Tworzy nowy obiekt kwiatu i umieszcza go na wolnym polu planszy.
     */
    public void stworzKwiat(){
        // stworzenie kwiatka
    }

    /**
     * Dodaje nowego owada do listy aktywnych owadów na planszy.
     * * @param owad obiekt owada (np. nowo narodzona pszczoła), który ma zostać dodany do symulacji
     */
    public void dodajOwada(Owad owad) {
        // Zabezpieczenie przed dodaniem "pustego" obiektu
        if (owad != null) {
            // Dodajemy owada do naszej listy
            this.aktywneOwady.add(owad);
        }
    }

    /**
     * Usuwa wskazany obiekt z siatki planszy.
     * Metoda wywoływana w przypadku zebrania nektaru z kwiatu lub wyeliminowania owada z symulacji.
     *
     * @param obiekt obiekt, który ma zostać trwale usunięty z planszy
     */
    public void usunObiekt(Obiekt obiekt){
        // usuniecie z planszy np. zebranego kwaitka/wyeliminowanego owada
    }

    /**
     * Pobranie szerokości planszy.
     *
     * @return wartość szerokości
     */
    public int getSzerokosc() {
        return szerokosc;
    }

    /**
     * Pobranie wysokości planszy.
     *
     * @return wartość wysokości planszy.
     */
    public int getWysokosc() {
        return wysokosc;
    }


}
