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
     * @param plansza referencja do planszy
     */
    public Robotnica(int x,int y, Ul ul, Plansza plansza){
        super(x, y, ul, plansza);
        this.maNektar = false;
        this.iloscNektaru = 0;
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
        int przekazanaIlosc = iloscNektaru;
        ul.dodajMiod(przekazanaIlosc);


        // Resetowanie stanu robotnicy
        this.iloscNektaru = 0;
        this.maNektar = false;

        return przekazanaIlosc;
    }

    /**
     * Wykonanie przypisanej dla robotnicy akcji w danej turze.
     *  - jeżeli ma nektar i jest w ulu to przekazuje nektar
     *  - jeżeli ma nektar ale nie jest w ulu to robi krok w stronę ula
     *  - jeżeli nie ma nektaru szuka kwiatka
     *  - jak znajdzie kwiat zbiera nektar
     *  - jeżeli nie ma nektaru a na planszy nie ma kwiatów wykonuje losowy ruch     *
     */
    @Override
    public void wykonajAkcje(){
        if(maNektar){
            Ul ul = getMojUl();
            if (this.getX() ==  ul.getX() || this.getY() == ul.getY()) {
                przekazNektar();
            } else {
                zrobKrokWStrone(ul.getX(), ul.getY());
            }
        } else {
            Kwiat cel = znajdzKwiat();

            if (cel != null) {
                if (this.getX() == cel.getX() || this.getY() == cel.getY()) {
                    zbierzNektar(cel);
                    getPlansza().usunObiekt(cel); // usuwamy zebranhy kwiat z planszy
                } else {
                    zrobKrokWStrone(cel.getX(), cel.getY());
                }
            } else {
                ruchLosowy();
            }
        }
    }

    /**
     * Zmienia pozycję robotnicy na planszy.
     *
     * @param x nowa współrzędna x, na którą ma przesunąć się owad
     * @param y nowa współrzędna y, na którą ma przesunąć się owad
     */
    @Override
    public void ruch(int x, int y){
        if (x >= 0 && x < getPlansza().getSzerokosc() && y >= 0 && y < getPlansza().getWysokosc()) {}
        this.setX(x);
        this.setY(y);
    }
    /**
     * Przesuwa robotnicę o jeden krok w kierunku celu.
     *
     * @param celX współrzędna x celu
     * @param celY współrzędna y celu
     */
    private void zrobKrokWStrone(int celX, int celY){
        int nowyX = this.getX();
        int nowyY = this.getY();

        if (nowyX < celX) nowyX++;
        else if (nowyX > celX) nowyX--;

        if (nowyY < celY) nowyY++;
        else if (nowyY > celY) nowyY--;

        ruch(nowyX, nowyY);
    }

    /**
     * Znajduje najbliższy niezebrany kwiat na planszy.
     *
     * @return najbliższy kwiat lub null
     */
    private Kwiat znajdzKwiat(){
        if (getPlansza() == null) {
            return null;
        }

        Kwiat najblizszyKwiat = null;
        double minimalnaOdleglosc = Double.MAX_VALUE;

        for (Kwiat kwiat : getPlansza().getKwiaty()) {
            if (kwiat != null && !kwiat.czyZebrany()) {
                double odleglosc = obliczOdleglosc(kwiat.getX(), kwiat.getY());
                if (odleglosc < minimalnaOdleglosc) {
                    minimalnaOdleglosc = odleglosc;
                    najblizszyKwiat = kwiat;
                }
            }
        }
        return najblizszyKwiat;
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
        // umożliwia losowy ruch w każdą stronę (też na skosy) lub pozostanie na miejscu
        int noweX = this.getX() + (int)(Math.random() * 3) - 1;
        int noweY = this.getY() + (int)(Math.random() * 3) - 1;
        ruch(noweX, noweY);
    }
}
