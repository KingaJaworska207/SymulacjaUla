package model;

/**
 * Klasa abstrakcyjna reprezentująca każdego owada.
 * Dziedziczy po klasie Obiekt, dzięki czemu każdy owad posiada pozycję (x, y).
 * Przechowuje również stan życia owada.
 */

public abstract class Owad extends Obiekt {
    /** Określa stan życia owada */
    protected boolean Zyje;

    /**
     * Inicjuje owada na podanych współrzędnych i ustala jego stan jako żywy.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public Owad(int x, int y){
        super(x, y);
        this.Zyje = true;
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
        return Zyje;
    }

    /**
     * Zmienia stan owada na martwy.
     */
    public void zgin(){
        this.Zyje = false;
    }
}
