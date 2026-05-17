package model;

/**
 * Klasa abstrakcyjna reprezentująca dowolną pszczołe.
 * Dziedziczy po klasie Owad. Rozszerza ją o specyficzne dla pszczół akcje.
 */

public abstract class Pszczola extends Owad{
    /** Referencja do ula, do którego należy dana pszczoła. */
    private Ul mojUl;

    /**
     * Inicjalizuje pszczołe na konkretnych współrzędnych i przypisuje ją do ula.
     *
     * @param x współrzędna x
     * @param y współrzędna y
     * @param ul obiekt ula, do którego należy dana pszczoła
     */
    public Pszczola(int x, int y, Ul ul){
        super(x, y);
        this.mojUl = ul;
    }

    /**
     * Pobiera obiekt ula,do którego należy dana pszczoła.
     * @return obiekt ula,do którego należy dana pszczoła
     */
    public Ul getMojUl(){
        return this.mojUl;
    }

    @Override
    public void zgin(){
        super.zgin();
        if(mojUl != null){
            mojUl.zmniejszPopulacje();
        }
    }

    /**
     * Wykonuje akcje specyficzne dla danej pszczoły.
     */
    public abstract void wykonajAkcje();

    /**
     * Przeprowadza pełną turę pszczoły.
     * Obejmuje to wykonanie ruchu oraz specyficznej akcji.
     */
    @Override
    public void wykonajTure(){
        /*
        ruch
        wykonanie akcji
        */
    }

}
