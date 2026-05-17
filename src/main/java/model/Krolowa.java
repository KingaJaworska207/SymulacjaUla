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
     */
    public Krolowa(int x, int y, Ul ul) {
        super(x, y, ul);
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

            ul.zwiekszPopulacje();

            //return nowaPszczola;
        }
        // Gdy warunki nie zostały spełnione, reprodukcja się nie powiodła
        return null;
    }

    /**
     * Wykonuje przypisaną dla królowej akcję w danej turze.
     */
    @Override
    public void wykonajAkcje(){
        // wywołanie reprodukcji
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
