package model;

/**
 * Klasa reprezentująca trutnia.
 * Dziedziczy po klasie Pszczola.
 * Odpowiada za naturalne zmniejszanie się zasobów miodu w ulu.
 * Przez cały czas trwania symulacji przebywa wewnątrz ula i nie zmienia swojej pozycji.
 */

public class Truten extends Pszczola{

    /**
     * Tworzy obiekt trutnia na konkretnych współrzędnych (pokrywających się z pozycją ula) i przypisuje go do ula.
     *
     * @param x współrzędna x ula
     * @param y współrzędna y ula
     * @param ul obiekt ula, do którego należy trutreń
     */
    public Truten(int x,int y, Ul ul){
        super(x, y, ul);
    }

    /**
     * Konsumpcja miodu z zasobów ula.
     */
    public void jedz(){
        getMojUl().zjedzMiod(1);
    }

    /**
     * ykonuje przypisaną dla trutnia akcję w danej turze.
     */
    @Override
    public void wykonajAkcje() {
        // wywołanie jedzenia
    }

    /**
     * Zmienia pozycję pszczoły na planszy.
     * Metoda celowo zostaje pusta, ponieważ trutrń nigdy nie opuszcza ula.
     *
     * @param x nowa współrzędna x, na którą ma przesunąć się owad (ignorowana)
     * @param y nowa współrzędna y, na którą ma przesunąć się owad (ignorowana)
     */
    @Override
    public void ruch(int x, int y) {
        // Ta metoda pozostanie pusta, ponieważ truteń nie wykonuje żadnego ruchu (pozostaje w ulu)
    }
}
