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

    /** Określa czy robotnica jest w ulu */
    private boolean wUlu;


    /**
     * Tworzy nową robotnicę na podanych współrzędnych planszy i przypisuje ją do ula.
     * Domyślnie nowa robotnica nie ma przy sobie nektaru.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param ul obiekt ula, do którego zostaje przypisana robotnica
     * @param plansza referencja do planszy
     */
    public Robotnica(int x,int y, Ul ul, Plansza plansza){
        super(x, y, ul, plansza);
        this.maNektar = false;
        this.iloscNektaru = 0;
        this.wUlu = true;
    }

    /**
     * Zebranie nektaru z docelowego kwiatu.
     * Robotnica może zebrać nektar tylko jeśli:
     * - nie ma już nektaru
     * - kwiat nie jest zebrany
     * - robotnica stoi na tym samym polu co kwiat
     *
     * @param kwiat obiekt kwiatu, z którego zbierany jest nektar
     * @return ilość zebranego nektaru
     */
    public int zbierzNektar(Kwiat kwiat){
        // Sprawdzenie czy można zebrać nektar
        if (kwiat == null || maNektar || kwiat.czyZebrany()) {
            return 0;
        }

        // Sprawdzenie czy robotnica jest na pozycji kwiatu
        if (this.getX() != kwiat.getX() || this.getY() != kwiat.getY()) {
            return 0;
        }

        // Pobranie nektaru z kwiatu
        int zebranyNektar = kwiat.pobierzNektar();

        this.iloscNektaru = zebranyNektar;
        this.maNektar = (zebranyNektar > 0);

        return zebranyNektar;
    }

    /**
     * Przekazanie nektaru do ula.
     * Robotnica może przekazać nektar tylko jeśli:
     * - ma nektar
     * - znajduje się w ulu
     *
     * @return ilość przekazanego nektaru
     */
    public int przekazNektar() {
        Ul ul = getMojUl();

        // Sprawdzenie czy robotnica może przekazać nektar
        if (ul == null || !maNektar || iloscNektaru <= 0) {
            return 0;
        }

        // Sprawdzenie czy robotnica jest w ulu
        if (this.getX() != ul.getX() || this.getY() != ul.getY()) {
            return 0;
        }

        // Przekazanie nektaru do ula
        ul.dodajMiod(iloscNektaru);


        // Resetowanie stanu robotnicy
        this.iloscNektaru = 0;
        this.maNektar = false;

        return iloscNektaru;
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
