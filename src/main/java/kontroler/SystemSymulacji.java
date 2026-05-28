package kontroler;

import model.Plansza;
import model.Owad;
import widok.RejestratorStatystyk;

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

    /** Szansa na pojawienie się nowego kwiatka na planszy w danej turze (wartość od 0.0 do 1.0) */
    private double prawdopodobienstwoKwiatka;

    /** Określa co ile tur pojawia się nowy szerszeń */
    private int czestotliwoscSzerszeni;

    /** Maksymalna liczba tur, po której symulacja automatycznie się kończy */
    private int maksymalnaLiczbaTur;

    /** Obiekt planszy, na której toczy się cała symulacja */
    private Plansza plansza;

    /** Obekt rejestratora potrzebny do zapisu statystyk */
    private RejestratorStatystyk rejestrator;

    /** Random do losowania */
    private Random random;


    /**
     *  Inicjalizuje system symulacji.
     *  Ustawia początkowe statystyki na zero.
     *  Tworzy nową planszę o domyślnych wymairach
     *
     * @param prawdopodobienstwo prawdopodobieństwo pojawienia się kwaitka
     * @param czestotliwoscSzerszeni co ile tur pojawia się nowy szerszeń
     * @param maksymalnaLiczbaTur czas trwania symulacji (w turach)
     * @param poczRobotnice ilość robotnic
     * @param poczTrutnie ilość trutni
     * @param poczStrazniczki ilość starżniczek
     * @param poczSzerszenie ilość szerszeni
     * @param poczMiod ilość miodu w ulu
     */
    public SystemSymulacji(double prawdopodobienstwo,int czestotliwoscSzerszeni, int maksymalnaLiczbaTur, int poczRobotnice, int poczTrutnie, int poczStrazniczki, int poczSzerszenie, int poczMiod){
        this.tura = 0;
        this.prawdopodobienstwoKwiatka = prawdopodobienstwo;
        this.czestotliwoscSzerszeni = czestotliwoscSzerszeni;
        this.maksymalnaLiczbaTur = maksymalnaLiczbaTur;
        this.plansza = new Plansza(15, 15);
        this.rejestrator = new RejestratorStatystyk();
        this.random = new Random();
        this.plansza.generujPoczatkowaPlansze(poczRobotnice, poczTrutnie, poczStrazniczki, poczSzerszenie, poczMiod);
        this.plansza.resetujLicznikiTury();
    }

    /**
     * Uruchamia główną pętle symualcji.
     * Działa nieprzerwanie, aktualizując stan i rejestrując dane, dopóki nie zostanie spełniony warunek końcowy.
     */
    public void uruchom(){
        while(!czyKoniec()){
            aktualizujTure();
            rejestrator.rejestrujDaneTury(tura, plansza);
        }

        if(!plansza.czySaRobotnice()) {
            System.out.println("KONIEC SYMULACJI. - Wszystkie robotnice wyginely :(");
        } else {
            System.out.println("KONIEC SYMULACJI. - Pszczolom udalo sie przezyc :)");
        }

        rejestrator.podsumowanieSymulacji(tura);

        rejestrator.zapiszDoPliku("wyniki_symulacji.csv");

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

}
