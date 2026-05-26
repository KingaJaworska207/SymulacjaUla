package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    /** Lista przechowująca niezebrane kwiaty */
    private List<Kwiat> kwiaty;

    /** Licznik narodzin w aktualnej turze */
    private int narodzinyWTejTurze = 0;

    /** Licznik zgonów w aktualnej turze */
    private int zgonyWTejTurze = 0;

    /** Dostęp do ula */
    private Ul ul;

    private Random random;

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
        this.aktywneOwady = new ArrayList<>();
        this.kwiaty = new ArrayList<>();
        this.random = new Random();
    }

    /**
     * Generuje początkowe ustawienie elementów na planszy.
     * Rozmieszcza startowe obiekty, takie jak ul, pierwsze kwiaty i początkową populację owadów.
     */
    public void generujPoczatkowaPlansze(int robotnice, int trutnie, int strazniczki, int szerszenie, int miod){
        // Wyznaczenie środka planszy i stworzenie Ula
        int srodekX = this.szerokosc / 2;
        int srodekY = this.wysokosc / 2;

        // Populacja ula to suma pszczół + 1 (Królowa)
        int poczatkowaPopulacja = robotnice + trutnie + strazniczki + 1;

        this.ul = new Ul(srodekX, srodekY, miod, poczatkowaPopulacja);
        this.siatka[srodekX][srodekY] = this.ul; // Umieszczenie ula na siatce obiektów stacjonarnych

        // Powołanie do życia Królowej (zawsze 1 i zawsze w ulu)
        Krolowa krolowa = new Krolowa(srodekX, srodekY, this.ul, this);
        this.aktywneOwady.add(krolowa);

        // Generowanie startowej populacji pszczół (wszystkie rodzą się w ulu)
        for (int i = 0; i < robotnice; i++) {
            this.aktywneOwady.add(new Robotnica(srodekX, srodekY, this.ul, this));
        }
        for (int i = 0; i < trutnie; i++) {
            this.aktywneOwady.add(new Truten(srodekX, srodekY, this.ul, this));
        }
        for (int i = 0; i < strazniczki; i++) {
            this.aktywneOwady.add(new Strazniczka(srodekX, srodekY, this.ul, this));
        }

        // Generowanie szerszeni (pojawiają się w losowych miejscach na mapie)
        for (int i = 0; i < szerszenie; i++) {
            int losowyX = random.nextInt(szerokosc);
            int losowyY = random.nextInt(wysokosc);
            this.aktywneOwady.add(new Szerszen(losowyX, losowyY, this));
        }

        // Rozsianie startowych kwiatów (pojawiają się w losowych miejscach na mapie)
        for (int i = 0; i < 45; i++) {
            stworzKwiat();
        }
    }

    /**
     * Sprawdza, czy na podanych współrzędnych siatki znajduje się już jakiś obiekt.
     *
     * @param x współrzędna x do sparwdzenia
     * @param y współrzędna y do sparwdzenia
     * @return true jeśli pole jest zajęte, false jeżeli jest wolne
     */
    public boolean czyPoleZajete(int x, int y){

        // zabezpieczenie przed wyjściem poza granice tablicy
        if (x < 0 || x >= szerokosc || y < 0 || y >= wysokosc){
            return true;
        }

        // sprawdzamy czy coś zanjduje się na danej pozycji
        if (siatka[x][y] != null){
            return true;
        }
        return false;
    }

    /**
     * Tworzy nowy obiekt kwiatu i umieszcza go na wolnym polu planszy.
     */
    public void stworzKwiat(){
        int x, y;
        int proby =0;

        // szukane jest losowe wolne pole ( zabezpieczenie przed nieskończonyą pętlną jeżeli na planszy nie ma miejsca)
        do{
            x = random.nextInt(szerokosc);
            y = random.nextInt(wysokosc);
            proby++;
        } while (czyPoleZajete(x, y) && proby < 100);

        if (!czyPoleZajete(x, y)){
            Kwiat nowyKwiat = new Kwiat(x, y, 1);
            siatka[x][y] = nowyKwiat;
            this.kwiaty.add(nowyKwiat);
        }
    }

    /**
     * Tworzy nowego szerszenia w losowym miejscu na planszy i dodaje go do symulacji.
     */
    public void stworzSzerszenia() {
        int losowyX = random.nextInt(szerokosc);
        int losowyY = random.nextInt(wysokosc);

        Szerszen nowySzerszen = new Szerszen(losowyX, losowyY, this);

        dodajOwada(nowySzerszen);
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
            if( owad instanceof Pszczola ){}
            this.narodzinyWTejTurze++;
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

        if (obiekt == null) return;

        // Jeśli to owad, wyrzucamy go z głównej listy aktywnych owadów
        if (obiekt instanceof Owad) {
            if(aktywneOwady.remove(obiekt)){
                if(obiekt instanceof Pszczola ){
                    this.zgonyWTejTurze++;
                    this.ul.zmniejszPopulacje();
                }
            }
        } else if (obiekt instanceof Kwiat) {
            // Jeśli to kwiat, wyrzucamy go z głównej listy niezebranych kwiatów
            kwiaty.remove(obiekt);
            siatka[obiekt.getX()][obiekt.getY()] = null; // czyszczenie polna na siatce
        }
    }

    /**
     * Sprawdzenie czy wśród aktywnych owadów są jeszcze robotnice.
     *
     * @return true jeżeli są robotnice, false jeżeli nie ma już robotnic
     */
    public boolean czySaRobotnice() {
        for (Owad owad : aktywneOwady) {
             if (owad instanceof Robotnica) {
                 return true;
             }
        }
        return false;
    }

    /**
     * Pobranie aktualnej ilosci miodu
     *
     * @return aktualna ilosc miodu
     */
    public int getPoziomMiodu() {
         return ul.getIloscMiodu();
    }

    /**
     * Pobranie aktywnej listy owadów
     *
     * @return lista aktywnych owadów
     */
    public List<Owad> getAktywneOwady() {
        return aktywneOwady;
    }

    /**
     * Zwraca obiekt znajdujący się na podanych współrzędnych.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @return Obiekt na danym polu lub null, jeśli pole jest puste / poza planszą
     */
    public Obiekt getObiekt(int x, int y) {
        if (x >= 0 && x < szerokosc && y >= 0 && y < wysokosc) {
            return siatka[x][y];
        }
        return null;
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
     * Pobiera listę wszystkich aktywnych kwiatów na planszy.
     *
     * @return lista niezebranych kwiatów
     */
    public List<Kwiat> getKwiaty() {
        return kwiaty;
    }

    /**
     * Pobranie wysokości planszy.
     *
     * @return wartość wysokości planszy.
     */
    public int getWysokosc() {
        return wysokosc;
    }

    /**
     * Resetuje liczniki narodzin i zgonów dla bieżącej tury.
     */
    public void resetujLicznikiTury() {
        this.narodzinyWTejTurze = 0;
        this.zgonyWTejTurze = 0;
    }

    public int getNarodzinyWTejTurze() {
        return narodzinyWTejTurze;
    }

    public int getZgonyWTejTurze() {
        return zgonyWTejTurze;
    }


}
