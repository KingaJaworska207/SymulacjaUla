package kontroler;

import model.Plansza;
import model.Owad;
import model.Robotnica;
import model.Strazniczka;
import model.Truten;
import model.Krolowa;
import model.Szerszen;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Główna klasa zarządzająca pętlą czasową i przebiegiem symulacji.
 * Odpowiada za kontrolowanie upływu czasu (tur) oraz wywoływanie akcji obiektów na planszy.
 */

public class SystemSymulacji {

    /** Aktualny numer tury symulacjii */
    private int tura;

    /** Szansa na pojawienie się nowego kwiatka na planszy w danej turze (wartość od 0.0 do 0.1) */
    private double prawdopodobienstwoKwiatka;

    /** Określa co ile tur pojawia się nowy szerszeń */
    private int czestotliwoscSzerszeni;

    /** Łączna liczba owadów, które zginęły od poczatku trwania symulacji */
    private int liczbaZgonow;

    /** Łączna liczba pszczół, które narodziły się od początku trwania symulacji */
    private int liczbaNarodzin;

    /** Obiekt planszy, na której toczy się cała symulacja */
    private Plansza plansza;

    /** Maksymalna liczba tur, po której symulacja automatycznie się kończy */
    private int maksymalnaLiczbaTur;

    /** Random do losowania */
    private Random random;

    /** Zmienna przechowująca historię danych symualcjii */
    private List<String> historiaDanych;

    /**
     * Inicjalizuje system symulacji.
     * Ustawia początkowe statystyki na zero.
     * Tworzy nową planszę o domyślnych wymairach (np. 21x21)
     *
     * @param prawdopodobienstwo szansa na pojawienie się nowego kwiatka na planszy w danej turze
     */
    public SystemSymulacji(double prawdopodobienstwo,int czestotliwoscSzerszeni, int maksymalnaLiczbaTur, int poczRobotnice, int poczTrutnie, int poczStrazniczki, int poczSzerszenie, int poczMiod){
        this.tura = 0;
        this.liczbaZgonow = 0;
        this.liczbaNarodzin = 0;
        this.prawdopodobienstwoKwiatka = prawdopodobienstwo;
        this.czestotliwoscSzerszeni = czestotliwoscSzerszeni;
        this.maksymalnaLiczbaTur = maksymalnaLiczbaTur;
        this.random = new Random();
        this.historiaDanych = new ArrayList<>();
        // Nagłówki kolumn w pliku CSV
        this.historiaDanych.add("Tura;Miod;Robotnice;Strazniczki;Trutnie;Krolowe;Szerszenie;NarodzinyPszczol;ZgonyPszczol");

        this.plansza = new Plansza(15, 15);
        this.plansza.generujPoczatkowaPlansze(poczRobotnice, poczTrutnie, poczStrazniczki, poczSzerszenie, poczMiod);
        this.plansza.resetujLicznikiTury(); // resetujemy liczniki, aby początkowo wygenerowane owady nie liczyły się do narodzin
    }

    /**
     * Uruchamia główną pętle symualcji.
     * Działa nieprzerwanie, aktualizując stan i rejestrując dane, dopóki nie zostanie spełniony warunek końcowy.
     */
    public void uruchom(){
        while(!czyKoniec()){
            aktualizujTure();
            rejestrujDaneTury();
        }

        if(!plansza.czySaRobotnice()) {
            System.out.println("KONIEC SYMULACJI. - Wszystkie robotnice wyginely :(");
        } else {
            System.out.println("KONIEC SYMULACJI. - Pszczolom udalo sie przezyc :)");
        }

        EksporterCSV eksporter = new EksporterCSV();
        eksporter.eksportujDoPliku(this.historiaDanych, "wyniki_symulacji.csv");
    }

    /**
     * Przeprowadza pojedynczą turę symulacji.
     * Odpowiada za zwiększenie licznika tur, wprawienie owadów w ruch oraz wywołanie zdarzeń środowiskowych (np. generowanie kwiatów).
     */
    public void aktualizujTure(){
        // zwiększenie numeru aktualnej tury
        this.tura++;

        // resetujemy liczniki zgonów i narodzin
        plansza.resetujLicznikiTury();

        // Tworzymy kopię listy aktywnych owadów na daną turę.
        // Zapobiega to błędom, gdy owad zginie (zostanie usunięty z listy) w trakcie iteracji.
        List<Owad> owadyWTejTurze = new ArrayList<>(plansza.getAktywneOwady());

        // wywołanie tury owada
        for (Owad owad : owadyWTejTurze) {
            if (owad.czyZyje()) {
                owad.wykonajTure();
            }
        }
        // generowanie nowych kwiatów na planszy
        if (random.nextDouble() <= prawdopodobienstwoKwiatka){
            plansza.stworzKwiat();
        }

        // generowanie nowego szerszenia
        if (this.tura > 0 && this.tura % czestotliwoscSzerszeni == 0) {
            plansza.stworzSzerszenia();
        }

        // Pobieramy podsumowanie tury z planszy i dodajemy do statystyk systemu
        this.liczbaNarodzin += plansza.getNarodzinyWTejTurze();
        this.liczbaZgonow += plansza.getZgonyWTejTurze();
    }

    /**
     * Sprawdza, czy nastąpiły warunki do zakończenia symulacji. (wyginęły wszystkie robotnice/ osiągnięto maksymalną liczbę tur)
     *
     * @return true, jeśli symulacja powinna się zakończyć, w przeciwnym razie false
     */
    public boolean czyKoniec(){
        // zakończenie po określonej liczbie tur
        if (tura >= maksymalnaLiczbaTur){
            return true;
        }

        if (!plansza.czySaRobotnice()){
            return true;
        }
        return false;
    }

    /**
     * Zapisuje statystyki i bieżące dane z zakończonej tury.
     * Metoda przygotowana do współpracy z eksporterem danych do pliku CSV.
     */
    public void rejestrujDaneTury(){
        int lRobotnic = 0, lStrazniczek = 0, lTrutni = 0, lKrolowych = 0, lSzerszeni = 0;

        // Zliczanie populacji z podziałem na role
        for (Owad owad : plansza.getAktywneOwady()) {
            if (owad instanceof Robotnica) lRobotnic++;
            else if (owad instanceof Strazniczka) lStrazniczek++;
            else if (owad instanceof Truten) lTrutni++;
            else if (owad instanceof Krolowa) lKrolowych++;
            else if (owad instanceof Szerszen) lSzerszeni++;
        }

        System.out.println(" --- TURA " + tura + " ---");
        System.out.println("Szerszenie: " +  lSzerszeni);
        System.out.println("Zasoby miodu: " + plansza.getPoziomMiodu());
        System.out.println("Populacja ula -> Robotnice: " + lRobotnic + " | Strazniczki: " + lStrazniczek + " | Trutnie: " + lTrutni + " | Krolowa: " + lKrolowych);
        System.out.println("Zgony pszczol: " + liczbaZgonow);
        System.out.println("Narodziny pszczol: " + liczbaNarodzin);
        System.out.println("-------------------------");

        String wiersz = tura + ";" + plansza.getPoziomMiodu() + ";" +
                lRobotnic + ";" + lStrazniczek + ";" + lTrutni + ";" +
                lKrolowych + ";" + lSzerszeni + ";" +
                liczbaNarodzin + ";" + liczbaZgonow;

        this.historiaDanych.add(wiersz);
    }
}
