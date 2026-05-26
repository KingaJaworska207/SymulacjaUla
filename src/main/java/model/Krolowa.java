package model;

/**
 * Klasa reprezentująca Królową ula.
 * Dziedziczy po klasie Pszczola.
 * Odpowiada za reprodukcję - tworzenie nowych obiektów pszczół
 * Przez cały czas trwania symulacji przebywa ona wewnątrz ula i nie zmienia swojej pozycji.
 */

public class Krolowa extends Pszczola{

    /**
     * Tworzy królową na konkretnych współrzędnych (pokrywających się z pozycją ula) i przypisuje ją do ula.
     *
     * @param x współrzędna x ula
     * @param y współrzędna y ula
     * @param ul obiekt ula, do którego należy królowa
     * @param plansza referencja do planszy
     */
    public Krolowa(int x, int y, Ul ul, Plansza plansza) {
        super(x, y, ul, plansza);
    }

    /**
     * Proces tworzenia nowej pszczoły.
     * Nowa pszczoła powstaje jeżeli ilość miodu w ulu jest większa niż wielkość populacji ula
     *
     * @return nowo utworzony obiekt pszczoły, jeśli warunki są spełnione, lub null w przypadku niewystarczających zasobów
     */
    public Pszczola reprodukcja(){
        // pobranie ula
        Ul ul = getMojUl();

        // logika reprodukcji
        if(ul !=null && ul.getLiczbaPopulacji() < ul.getIloscMiodu()){

            // stworzenie nowej pszczoły (Robotnica/Strażniczka)
            Pszczola nowaPszczola = stworzNowaPszczole();

            ul.zwiekszPopulacje();

            if (getPlansza() != null && nowaPszczola != null) {
                getPlansza().dodajOwada(nowaPszczola);
            }

            return nowaPszczola;
        }
        // Gdy warunki nie zostały spełnione, reprodukcja się nie powiodła
        return null;
    }

    /**
     * Tworzy nową pszczołę określonego typu.
     * 70% szans na Robotnicę, 30% szans na Strażniczkę.
     *
     * @return nowo utworzony obiekt pszczoły
     */
    private Pszczola stworzNowaPszczole() {
        Ul ul = getMojUl();
        double los = Math.random();

        // Pobranie pozycji ula
        int ulX = ul.getX();
        int ulY = ul.getY();

        // 70% szans na robotnicę, 30% na strażniczkę
        if (los < 0.7) {
             new Robotnica(ulX, ulY, ul, getPlansza());
            return new Robotnica(ulX, ulY, ul, getPlansza());
        } else {
            return new Strazniczka(ulX, ulY, ul, getPlansza());
        }
    }

    /**
     * Wykonuje przypisaną dla królowej akcję w danej turze.
     */
    @Override
    public void wykonajAkcje(){
        reprodukcja();
    }

    /**
     * Zmienia pozycję pszczoły na planszy.
     * Metoda celowo zostaje pusta, ponieważ królowa nigdy nie opuszcza ula.
     *
     * @param x nowa współrzędna x, na którą ma przesunąć się owad (ignorowana)
     * @param y nowa współrzędna y, na którą ma przesunąć się owad (ignorowana)
     */
    @Override
    public void ruch(int x, int y){
        // pozostaje pusta (Królowa nie rusza się nigdy z ula)
    }
}
