package model;

/**
 * Klasa abstrakcyjna reprezentująca każdy z elementów, który może pojawić się na planszy.
 * Przechowuje podstawowe informacje o położeniu.
 */
public abstract class Obiekt {
    /** Współrzędna pozioma obiektu na planszy */
    private int x;
    /** Współrzędna pionowa obiektu na planszy */
    private int y;

    /**
     * Nadaje współrzędne nowemu obiektowi na planszy
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public Obiekt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Pobiera aktualną współrzędną x obiektu.
     * @return wartość współrzędnej x
     */
    public int getX() {
        return x;
    }

    /**
     * Pobiera aktualną współrzędną y obiektu.
     * @return wartość współrzędnej y
     */
    public int getY() {
        return y;
    }

    /**
     * Ustala nową współrzędną x obiektu.
     * @param x nowa wartość współrzędnej x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Ustala nową współrzędną y obiektu.
     * @param y nowa wartość współrzędnej y
     */
    public void setY(int y) {
        this.y = y;
    }

}
