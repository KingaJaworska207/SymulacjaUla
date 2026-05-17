package kontroler;

import model.Plansza;

/**
 * Główna klasa zarządzająca pętlą czasową i przebiegiem symulacji.
 * Odpowiada za kontrolowanie upływu czasu (tur) oraz wywoływanie akcji obiektów na planszy.
 */

public class SystemSymulacji {
    /** Aktualny numer tury symulacjii */
    private int tura;

    /** Szansa na pojawienie się nowego kwiatka na planszy w danej turze (wartość od 0.0 do 0.1) */
    private double prawdopodobienstwoKwiatka;

    /** Łączna liczba owadów, które zginęły od poczatku trwania symulacji */
    private int liczbaZgonow;

    /** Łączna liczba pszczół, które narodziły się od początku trwania symulacji */
    private int liczbaNarodzin;

    /** Obiekt planszy, na której toczy się cała symulacja */
    private Plansza plansza;

    /**
     * Inicjalizuje system symulacji.
     * Ustawia początkowe statystyki na zero.
     * Tworzy nową planszę o domyślnych wymairach (np. 21x21)
     *
     * @param prawdopodobienstwo szansa na pojawienie się nowego kwiatka na planszy w danej turze
     */
    public SystemSymulacji(double prawdopodobienstwo){
        this.tura = 0;
        this.liczbaZgonow = 0;
        this.liczbaNarodzin = 0;
        this.prawdopodobienstwoKwiatka = prawdopodobienstwo;
        this.plansza = new Plansza(21, 21);
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
    }

    /**
     * Przeprowadza pojedynczą turę symulacji.
     * Odpowiada za zwiększenie licznika tur, wprawienie owadów w ruch oraz wywołanie zdarzeń środowiskowych (np. generowanie kwiatów).
     */
    public void aktualizujTure(){
        // metoda wprawiająca owady w ruch i wywołująca zdarzenia w środowisku
        this.tura++;
    }

    /**
     * Sprawdza, czy nastąpiły warunki do zakończenia symulacji. (wyginęły wszystkie robotnice)
     *
     * @return true, jeśli symulacja powinna się zakończyć, w przeciwnym razie false
     */
    public boolean czyKoniec(){
        // sprawdza czy nie wygineły wszystkie robotnice
        return false;
    }

    /**
     * Zapisuje statystyki i bieżące dane z zakończonej tury.
     * Metoda przygotowana do współpracy z eksporterem danych do pliku CSV.
     */
    public void rejestrujDaneTury(){
        // rejestracja danych tury
    }
}
