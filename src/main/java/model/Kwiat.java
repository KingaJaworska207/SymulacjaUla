package model;

/**
 * Klasa reprezentująca kwaity.
 * Dziedziczy po klasie Obiekt.
 * Służy za punkt z którego robotnica może pobrać nektar.
 */

public class Kwiat extends Obiekt{
    /** Ilość możliwego do zebrania nektaru z danego kwiatu */
    private int iloscNektaru;
    /** Określna czy kwait jest zebrany */
    private boolean zebrany;

    /**
     * Tworzy nowy kwiat na podanych współrzędnych oraz z konkretną ilościa nektaru.
     * Domyślnie kwait nie jest zebrany.
     *
     * @param x
     * @param y
     * @param poczatkowyNektar
     */
    public Kwiat(int x,int y, int poczatkowyNektar){
        super(x,y);
        this.iloscNektaru = poczatkowyNektar;
        this.zebrany = false;
    }

    /**
     * Pobiera nektar z kwiatu i oznacza go jako zebrany.
     *
     * @return ilość pobranego nektaru
     */
    public int pobierzNektar() {

        if (this.czyZebrany()) {
            return 0;
        }

        // Zapisujemy ilość nektaru do zwrócenia
        int pobranyNektar = this.iloscNektaru;

        // Zerujemy zapas nektaru w kwiecie i trwale oznaczamy go jako zebrany
        this.iloscNektaru = 0;
        this.setZebrany();

        // Zwracamy pobraną wartość
        return pobranyNektar;
    }

    /**
     * Sprawdza, czy kwait jest zebrany.
     * @return true jeżeli jest zebrany, false jeżeli nie jest jeszcze zebrany
     */
    public boolean czyZebrany(){
        return zebrany;
    }

    /**
     * Zmienia stan zebrany.
     */
    public void setZebrany(){
        this.zebrany = true;
    }
}
