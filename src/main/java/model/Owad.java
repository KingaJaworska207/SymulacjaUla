package model;

/**
 * Klasa abstrakcyjna reprezentująca każdego owada.
 * Dziedziczy po klasie Obiekt, dzięki czemu każdy owad posiada pozycję (x, y).
 * Przechowuje również stan życia owada.
 */
public abstract class Owad extends Obiekt {
    /** Określa stan życia owada */
    private boolean zyje;

    /** Referencja do planszy */
    private Plansza plansza;

    /**
     * Inicjuje owada na podanych współrzędnych i ustala jego stan jako żywy.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param plansza referencja do planszy
     */
    public Owad(int x, int y, Plansza plansza){
        super(x, y);
        this.zyje = true;
        this.plansza = plansza;
    }

    /**
     * Zwraca referencję do planszy.
     *
     * @return obiekt planszy
     */
    public Plansza getPlansza() {
        return plansza;
    }

    /**
     * Ustawia referencję do planszy.
     *
     * @param plansza obiekt planszy
     */
    public void setPlansza(Plansza plansza) {
        this.plansza = plansza;
    }

    /**
     * Odpowiada za przemieszczenie się owada na planszy.
     * Metoda do implementacji w klasach pochodnych.
     *
     * @param x nowa współrzędna x, na którą ma przesunąć się owad
     * @param y nowa współrzędna y, na którą ma przesunąć się owad
     */
    public abstract void ruch(int x, int y);

    /**
     * Wykonuje wszystkie akcje przewidziane dla owada w danej turze.
     * Metoda do implementacji w klasach pochodnych.
     */
    public abstract void wykonajTure();

    /**
     * Sprawdza, czy owad zyje.
     * @return true jeśli owad jest żywy, false jeżeli jest martwy
     */
    public boolean czyZyje() {
        return zyje;
    }

    /**
     * Zmienia stan owada na martwy.
     */
    public void zgin(){
        this.zyje = false;
    }
}
