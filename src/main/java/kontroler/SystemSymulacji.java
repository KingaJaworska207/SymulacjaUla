package kontroler;

import model.Plansza;

/*
* Główna klasa zarządzająca pętlą czasową i przebiegiem symulacji.
*/

public class SystemSymulacji {
    private int tura;
    private double prawdopodobienstwoKwiatka;
    private int liczbaZgonow;
    private int liczbaNarodzin;

    private Plansza plansza;

    public SystemSymulacji(double prawdopodobienstwo){
        this.tura = 0;
        this.liczbaZgonow = 0;
        this.liczbaNarodzin = 0;
        this.prawdopodobienstwoKwiatka = prawdopodobienstwo;
        this.plansza = new Plansza(21, 21);
    }

    public void uruchom(){
        while(!czyKoniec()){
            aktualizujTure();
            rejestrujDaneTury();
        }
    }

    public void aktualizujTure(){
        // metoda wprawiająca owady w ruch i wywołująca zdarzenia w środowisku
        this.tura++;
    }

    public boolean czyKoniec(){
        // sprawdza czy nie wygineły wszystkie robotnice
        return false;
    }

    public void rejestrujDaneTury(){
        // rejestracja danych tury
    }
}
