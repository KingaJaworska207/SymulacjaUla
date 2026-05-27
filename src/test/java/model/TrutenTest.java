package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrutenTest {

    private Plansza plansza;
    private Ul ul;
    private Truten truten;

    @BeforeEach
    public void setUp() {
        plansza = new Plansza(10, 10);

        ul = new Ul(5, 5, 10, 100);

        truten = new Truten(5, 5, ul, plansza);
    }

    @Test
    public void testDziedziczeniaPoObiektIOwad() {
        assertEquals(5, truten.getX(), "Poczatkowa wspolrzedna X powinna wynosic 5");
        assertEquals(5, truten.getY(), "Poczatkowa wspolrzedna Y powinna wynosic 5");
        assertTrue(truten.czyZyje(), "Nowo stworzony truten powinien zyc");
    }


    @Test
    public void testTrutenNieWykonujeRuchu() {
        truten.ruch(2, 3);

        assertEquals(5, truten.getX(), "Wspolrzedna X trutnia nie powinna ulec zmianie");
        assertEquals(5, truten.getY(), "Wspolrzedna Y trutnia nie powinna ulec zmianie");
    }

    @Test
    public void testKonsumpcjiMiodu() {
        assertEquals(10, ul.getIloscMiodu(), "Poczatkowa ilosc miodu powinna wynosic 10");

        truten.jedz();

        assertEquals(9, ul.getIloscMiodu(), "Ilosc miodu w ulu powinna spasc o 1 po zjedzeniu przez trutnia");
    }

}