package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UlTest {

    private Ul ul;

    @BeforeEach
    public void setUp() {
        /* Tworzymy ul na wspolrzednych (5, 5) z 50 jednostkami miodu i 10 pszczolami w populacji */
        ul = new Ul(5, 5, 50, 10);
    }

    @Test
    public void testDziedziczeniaPoObiekt() {
        assertEquals(5, ul.getX(), "Wspolrzedna X ula powinna wynosic 5");
        assertEquals(5, ul.getY(), "Wspolrzedna Y ula powinna wynosic 5");

        ul.setX(10);
        ul.setY(15);

        assertEquals(10, ul.getX(), "Wspolrzedna X ula powinna ulec zmianie na 10");
        assertEquals(15, ul.getY(), "Wspolrzedna Y ula powinna ulec zmianie na 15");
    }

    @Test
    public void testInicjalizacjiWartosci() {
        assertEquals(50, ul.getIloscMiodu(), "Poczatkowa ilosc miodu powinna wynosic 50");
        assertEquals(10, ul.getLiczbaPopulacji(), "Poczatkowa populacja powinna wynosic 10");
    }

    @Test
    public void testZwiekszPopulacje() {
        ul.zwiekszPopulacje();
        assertEquals(11, ul.getLiczbaPopulacji(), "Populacja ula powinna wzrosnac o 1 po dodaniu pszczoly");
    }

    @Test
    public void testZmniejszPopulacje() {
        ul.zmniejszPopulacje();
        assertEquals(9, ul.getLiczbaPopulacji(), "Populacja ula powinna spasc o 1 po smierci pszczoly");
    }

    @Test
    public void testDodajMiod() {
        ul.dodajMiod(15);
        assertEquals(65, ul.getIloscMiodu(), "Ilosc miodu powinna wzrosnac o dodane 15 jednostek (50 + 15 = 65)");
    }

    @Test
    public void testZjedzMiodWystarczajaceZasoby() {
        ul.zjedzMiod(10);
        assertEquals(40, ul.getIloscMiodu(), "Ilosc miodu powinna spasc o zjedzone 10 jednostek (50 - 10 = 40)");
    }

    @Test
    public void testZjedzMiodBrakWystarczajacychZasobow() {
        /* Proba zjedzenia 100 jednostek, gdy w ulu jest tylko 50 */
        ul.zjedzMiod(100);

        /* Metoda zjedzMiod posiada warunek chroniacy przed ujemnymi zasobami, miod powinien pozostac bez zmian */
        assertEquals(50, ul.getIloscMiodu(), "Ilosc miodu nie powinna sie zmienic w przypadku proby zjedzenia wiekszej ilosci niz dostepna");
    }
}