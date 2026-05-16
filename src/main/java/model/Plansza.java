package model;

/*
* Klasa reprezentująca dwuwymiarową przestrzeń symulacji.
* Zarządza siatka obiektów i ich rozmieszczeniem.
 */

public class Plansza {

    private int szerokosc;
    private int wysokosc;

    // Tablica dwuwymiarowa przechowująca obiekty na konkretnych koordynatach
    private Obiekt[][] siatka;

    public Plansza(int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.siatka = new Obiekt[szerokosc][wysokosc];
    }

    public void generujPoczatkowaPlansze(){
        // stworzenie poczatkowego ustawienia obiektow na planszy
    }

    public boolean czyPoleZajete(int x, int y){
        // sprawdzamy czy coś zanjduje się na danej pozycji
        return false;
    }

    public void stworzKwiat(){
        // stworzenie kwiatka
    }

    public void usunObiekt(Obiekt obiekt){
        // usuniecie z planszy np. zebranego kwaitka/wyeliminowanego owada
    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public int getWysokosc() {
        return wysokosc;
    }


}
