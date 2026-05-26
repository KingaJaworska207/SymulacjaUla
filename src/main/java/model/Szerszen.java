package model;

import java.util.Random;

/**
 * Klasa reprezentująca szerszenia.
 * Dziedziczy po klasie Owad.
 * Odpowiada za eliminowanie napotkanych pszczół.
 */

public class Szerszen extends Owad{

    /** Generator liczb losowych */
    private Random random;

    /** Szansa na wygraną szerszenia w starciu ze strażniczką (50% = 0.5) */
    private static final double szansaWygranej = 0.5;

    /**
     * Tworzy nowego szersznia na podanych współrzędnych.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param plansza referencja do planszy
     */
    public Szerszen(int x, int y, Plansza plansza){
        super(x, y, plansza);
        this.random = new Random();
    }

    /**
     * Sprawdza czy na obecnej pozycji znajduje się pszczoła.
     *
     * @return pszczoła znajdująca się na tej samej pozycji lub null
     */
    public Pszczola napotkaniePszczoly(){
        if (getPlansza() == null) {
            return null;
        }

        for (Owad owad : getPlansza().getAktywneOwady()) {
            if (owad instanceof Pszczola && owad.czyZyje()) {
                if (this.getX() == owad.getX() && this.getY() == owad.getY()) {
                    return (Pszczola) owad;
                }
            }
        }
        return null;
    }

    /**
     * W przypadku napotkania pszczoły, wykonuje atak.
     * Logika ataku:
     * - Jeśli pszczoła to Robotnica -> szerszeń zawsze wygrywa
     * - Jeśli pszczoła to Strażniczka -> 50% szans na wygraną
     * - W przypadku przegranej ze Strażniczką -> szerszeń ginie
     */
    public void atakuj(Pszczola pszczola){
        if (pszczola == null) {
            return;
        }
        // Rozróżnienie typu pszczoły
        if (pszczola instanceof Robotnica) {
            // Atak na robotnicę - zawsze udany
            pszczola.zgin();
            getPlansza().usunObiekt(pszczola);
        }
        else if (pszczola instanceof Strazniczka) {
            // Atak na strażniczkę - 50% szans
            double los = random.nextDouble();
            if (los < szansaWygranej) {
                // Szerszeń wygrywa - strażniczka ginie
                pszczola.zgin();
                getPlansza().usunObiekt(pszczola);
            } else {
                // Szerszeń przegrywa - sam ginie
                this.zgin();
                getPlansza().usunObiekt(this);
            }
        }
    }

    /**
     * Zmienia pozycję robotnicy na planszy w sposób losowy.
     *
     * @param x nowa współrzędna x, na którą ma przesunąć się owad
     * @param y nowa współrzędna y, na którą ma przesunąć się owad
     */
    @Override
    public void ruch(int x, int y) {
        if (x >= 0 && x < getPlansza().getSzerokosc() && y >= 0 && y < getPlansza().getWysokosc()) {
            this.setX(x);
            this.setY(y);
        }
    }

    /**
     * Przeprowadza pełną turę szerszenia.
     * - jeżeli napotkał pszczołe to atakuje
     * - jeżeli jest na tym samym polu pszczoła to szuka robotnicy i idzie w jej kierunku
     * - jeżeli nie ma robotnic to robi losowy ruch     *
     */
    @Override
    public void wykonajTure(){
        Pszczola napotkanaPszczola = napotkaniePszczoly();

        if (napotkanaPszczola != null) {
            atakuj(napotkanaPszczola);
        } else {
            Robotnica cel = znajdzRobotnice();

            if (cel != null) {
                zrobKrokWStrone(cel.getX(), cel.getY());
            } else {
                ruchLosowy();
            }
        }
    }

    /**
     * Przesuwa szerszenia o jeden krok w kierunku celu.
     *
     * @param celX współrzędna x celu
     * @param celY współrzędna y celu
     */
    private void zrobKrokWStrone(int celX, int celY) {
        int noweX = this.getX();
        int noweY = this.getY();

        if (this.getX() < celX) noweX++;
        else if (this.getX() > celX) noweX--;

        if (this.getY() < celY) noweY++;
        else if (this.getY() > celY) noweY--;

        ruch(noweX, noweY);
    }

    /**
     * Znajduje najbliższą żywą robotnicę na planszy.
     *
     * @return najbliższa robotnica lub null
     */
    private Robotnica znajdzRobotnice() {
        Robotnica najblizsza = null;
        double minimalnaOdleglosc = Double.MAX_VALUE;

        for (Owad owad : getPlansza().getAktywneOwady()) {
            if (owad instanceof Robotnica && owad.czyZyje()) {
                double odleglosc = obliczOdleglosc(owad.getX(), owad.getY());
                if (odleglosc < minimalnaOdleglosc) {
                    minimalnaOdleglosc = odleglosc;
                    najblizsza = (Robotnica) owad;
                }
            }
        }
        return najblizsza;
    }

    /**
     * Oblicza odległość do podanych współrzędnych.
     *
     * @param cx współrzędna x celu
     * @param cy współrzędna y celu
     * @return odległość euklidesowa
     */
    private double obliczOdleglosc(int cx, int cy) {
        int dx = this.getX() - cx;
        int dy = this.getY() - cy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Wykonuje losowy ruch.
     */
    private void ruchLosowy() {
        int noweX = this.getX() + random.nextInt(3) - 1;
        int noweY = this.getY() + random.nextInt(3) - 1;
        ruch(noweX, noweY);
    }
}
