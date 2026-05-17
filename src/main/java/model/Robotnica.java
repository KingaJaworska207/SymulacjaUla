package model;

/**
 * Klasa reprezentująca robotnice.
 * Dziedziczy po klasie Pszczola.
 * Odpowiada za zbieranie nektaru z kwiatów i zanoszenie go do ula.
 */

public class Robotnica extends Pszczola{
    /** Określa czy robotnica ma zebrany nektar */
    private boolean maNektar;

    /** Ilość zebranego nektaru, który robotnica ma aktualnie przy sobie */
    private int iloscNektaru;

    /**
     * Tworzy nową robotnicę na podanych współrzędnych planszy i przypisuje ją do ula.
     * Domyślnie nowa robotnica nie ma przy sobie nektaru.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param ul obiekt ula, do którego zostaje przypisana robotnica
     */
    public Robotnica(int x,int y, Ul ul){
        super(x, y, ul);
        this.maNektar = false;
        this.iloscNektaru = 0;
    }

    /**
     * Zebranie nektaru z docelowego kwiatu.
     *
     * @param kwiat obiekt kwaitu, z którego zbierany jest nektar
     * @return ilość zebranego nektaru
     */
    public int zbierzNektar(Kwiat kwiat){
        // logika zebrania nektaru
        return 0;
    }

    /**
     * Przekazanie nektaru do ula.
     *
     * @return ilość przekazanego nektaru
     */
    public int przekazNektar(){
        Ul ul = getMojUl();
        // logika przekazania nektaru przez metodę dodajMiod
        return 0;
    }

    /**
     * Wykonanie przypisanej dla robotnicy akcji w danej turze.
     */
    @Override
    public void wykonajAkcje(){
        // działania robotnicy
    }

    /**
     * Zmienia pozycję robotnicy na planszy.
     *
     * @param x nowa współrzędna x, na którą ma przesunąć się owad
     * @param y nowa współrzędna y, na którą ma przesunąć się owad
     */
    @Override
    public void ruch(int x, int y){
        this.setX(x);
        this.setY(y);
    }
}
