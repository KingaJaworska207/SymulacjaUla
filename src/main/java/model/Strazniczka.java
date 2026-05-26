package model;

import java.util.Random;

/**
 * Klasa reprezentująca strażniczkę.
 * Dziedziczy po klasie Pszczola.
 * Odpowiada za eliminowanie napotkanych szerszeni.
 */

public class Strazniczka extends Pszczola{

    /** Generator liczb losowych */
    private Random random;

    /** Szansa na wygraną strażniczki w starciu z szerszeniem (50% = 0.5) */
    private static final double szansaWygranej = 0.5;

    /**
     * Tworzy nową strażniczkę na podanych współrzędnych i przypisuje ją do ula.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param ul obiekt ula, do którego zostaje przypisana strażniczka
     * @param plansza referencja do planszy
     */
    public Strazniczka(int x, int y, Ul ul, Plansza plansza) {
        super(x, y, ul, plansza);
        this.random = new Random();
    }

    /**
     * Sprawdza czy na obecnej pozycji znajduje się szerszen.
     *
     * @return szerszen znajdujący się na tej samej pozycji lub null
     */
    public Szerszen napotkanieSzerszenia(){
        for (Owad owad: getPlansza().getAktywneOwady()){
            // Sprawdzamy czy owad to szerszeń, czy żyje i czy ma te same koordynaty
            if (owad instanceof Szerszen && owad.czyZyje()){
                if (this.getX() == owad.getX() && this.getY() == owad.getY()){
                    return (Szerszen) owad;
                }
            }
        }

        return null;
    }

    /**
     * W przypadku napotkania szerszenia, ma szanse na wyeliminowanie go.
     *
     * @param szerszen napotkany obiekt szerszenia
     */
    public void eliminuj(Szerszen szerszen){

        if (szerszen == null || !szerszen.czyZyje()) {
            return;
        }

        // Losowanie wyniku starcia - 50% szans na wygraną
        double los = random.nextDouble();
        if (los < szansaWygranej) {
            // Strażniczka wygrywa - eliminuje szerszenia
            szerszen.zgin();
            getPlansza().usunObiekt(szerszen);
        } else {
            // Strażniczka przegrywa - sama ginie
            this.zgin();
            getPlansza().usunObiekt(this);
        }
    }

    /**
     * Wykonanie przypisanej dla strażniczki akcji w danej turze.
     * - jeżeli napotkała szerszenia eliminuje go
     * - jeżeli nie jest na tej samej pozycji co szerszeń to szuka go i idzie w jego stronę
     * - jeżeli nie ma szerszeni na planszy wykonuje losowy ruch
     */
    @Override
    public void wykonajAkcje(){
        Szerszen napotkanySzerszen = napotkanieSzerszenia();

        if(napotkanySzerszen != null){
            eliminuj(napotkanySzerszen);
        } else {
            Szerszen cel = znajdzSzerszenia();

            if (cel != null) {
                zrobKrokWStrone(cel.getX(), cel.getY());
            } else {
                ruchLosowy();
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
    public void ruch(int x, int y){
        if (x >= 0 && x < getPlansza().getSzerokosc() && y >= 0 && y < getPlansza().getWysokosc()) {
            this.setX(x);
            this.setY(y);
        }
    }

    /**
     * Przesuwa strażniczkę o jeden krok w kierunku celu.
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
     * Znajduje najbliższego żywego szerszenia na planszy.
     *
     * @return najbliższy szerszeń lub null
     */
    private Szerszen znajdzSzerszenia(){
        if (getPlansza() == null) {
            return null;
        }

        Szerszen najblizszy = null;
        double minimalnaOdleglosc = Double.MAX_VALUE;

        for (Owad owad : getPlansza().getAktywneOwady()) {
            if (owad instanceof Szerszen && owad.czyZyje()) {
                double odleglosc = obliczOdleglosc(owad.getX(), owad.getY());
                if (odleglosc < minimalnaOdleglosc) {
                    minimalnaOdleglosc = odleglosc;
                    najblizszy = (Szerszen) owad;
                }
            }
        }
        return najblizszy;
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
