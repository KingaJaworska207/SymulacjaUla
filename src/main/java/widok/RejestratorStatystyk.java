package widok;

import model.Owad;
import model.Robotnica;
import model.Strazniczka;
import model.Truten;
import model.Krolowa;
import model.Szerszen;
import model.Plansza;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiedzialna za gromadzenie, wyświetlanie i zapisywanie statystyk z przebiegu symulacji.
 */
public class RejestratorStatystyk {

    /** Zmienna przechowująca historię danych symualcjii */
    private List<String> historiaDanych;

    /** Łączna liczba owadów, które zginęły od poczatku trwania symulacji */
    private int liczbaZgonow;

    /** Łączna liczba pszczół, które narodziły się od początku trwania symulacji */
    private int liczbaNarodzin;

    /** Przechowuje stan populacji z ostatniej zarejestrowanej tury */
    private int ostatniaPopulacja;

    /**
     * Tworzy rejestrator do statystyk
     */
    public RejestratorStatystyk() {
        this.historiaDanych = new ArrayList<>();
        this.liczbaZgonow = 0;
        this.liczbaNarodzin = 0;
        this.ostatniaPopulacja = 0;

        // Nagłówki kolumn w pliku CSV
        this.historiaDanych.add("Tura;Miod;Populacja;Robotnice;Strazniczki;Trutnie;Krolowe;Szerszenie;NarodzinyPszczol;ZgonyPszczol");
    }

    /**
     * Zapisuje statystyki i bieżące dane z zakończonej tury.
     * Metoda przygotowana do współpracy z eksporterem danych do pliku CSV.
     *
     * @param tura aktualny numer tury
     * @param plansza plansza
     */
    public void rejestrujDaneTury(int tura, Plansza plansza) {
        int lRobotnic = 0, lStrazniczek = 0, lTrutni = 0, lKrolowych = 0, lSzerszeni = 0;

        // Zliczanie populacji z podziałem na role
        for (Owad owad : plansza.getAktywneOwady()) {
            if (owad instanceof Robotnica) lRobotnic++;
            else if (owad instanceof Strazniczka) lStrazniczek++;
            else if (owad instanceof Truten) lTrutni++;
            else if (owad instanceof Krolowa) lKrolowych++;
            else if (owad instanceof Szerszen) lSzerszeni++;
        }

        // Zliczanie ogólnej liczby zgonów/narodzin w całej symulacji
        this.liczbaNarodzin += plansza.getNarodzinyWTejTurze();
        this.liczbaZgonow += plansza.getZgonyWTejTurze();

        // Zapis obecnej populacji ula
        this.ostatniaPopulacja = plansza.getPopulacjaUla();

        System.out.println(" --- TURA " + tura + " ---");
        System.out.println("Szerszenie: " +  lSzerszeni);
        System.out.println("Poszczegolne pszczoly -> Robotnice: " + lRobotnic + " | Strazniczki: " + lStrazniczek + " | Trutnie: " + lTrutni + " | Krolowa: " + lKrolowych);
        System.out.println("Zasoby miodu: " + plansza.getPoziomMiodu());
        System.out.println("Calkowita populacja ula: " + ostatniaPopulacja);
        System.out.println("Zgony pszczol: " + plansza.getZgonyWTejTurze());
        System.out.println("Narodziny pszczol: " + plansza.getNarodzinyWTejTurze());
        System.out.println("-------------------------");

        String wiersz = tura + ";" + plansza.getPoziomMiodu() + ";" +
                ostatniaPopulacja + ";" + lRobotnic + ";" + lStrazniczek + ";" + lTrutni + ";" +
                lKrolowych + ";" + lSzerszeni + ";" +
                plansza.getNarodzinyWTejTurze() + ";" + plansza.getZgonyWTejTurze();

        this.historiaDanych.add(wiersz);
    }

    /**
     * Generuje podsumowanie symulacjii
     *
     * @param tura numer ostatniej tury
     */
    public void podsumowanieSymulacji(int tura) {

        System.out.println();
        System.out.println("--- PODSUMOWANIE SYMULACJI ---");
        System.out.println("Ilosc wykonanych tur: " + tura);
        System.out.println("Ilosc wszystkich zgonow pszczol: " + liczbaZgonow);
        System.out.println("Ilosc wszystkich narodzin pszczol: " + liczbaNarodzin);
        System.out.println("Ostateczna wielkosc populacji pszczol: " + ostatniaPopulacja );
        System.out.println();

    }

    /**
     * Zapisuje zebraną historię do pliku CSV przy użyciu EksporterCSV.
     * @param nazwaPliku nazwa docelowego pliku
     */
    public void zapiszDoPliku(String nazwaPliku) {
        EksporterCSV eksporter = new EksporterCSV();
        eksporter.eksportujDoPliku(this.historiaDanych, nazwaPliku);
    }
}
