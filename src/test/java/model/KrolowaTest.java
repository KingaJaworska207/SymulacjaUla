package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KrolowaTest {

    private Plansza plansza;
    private Ul ul;
    private Krolowa krolowa;

    @BeforeEach
    public void setUp() {
        plansza = new Plansza(10, 10);

        ul = new Ul(5, 5, 10, 5);

        krolowa = new Krolowa(5, 5, ul, plansza);
    }

    @Test
    public void testDziedziczeniaPoObiektIOwad() {
        assertEquals(5, krolowa.getX(), "Poczatkowa wspolrzedna X powinna wynosic 5");
        assertEquals(5, krolowa.getY(), "Poczatkowa wspolrzedna Y powinna wynosic 5");
        assertTrue(krolowa.czyZyje(), "Nowo stworzona krolowa powinna zyc");
    }

    @Test
    public void testKrolowaNieWykonujeRuchu() {
        krolowa.ruch(2, 3);

        assertEquals(5, krolowa.getX(), "Wspolrzedna X krolowej nie powinna ulec zmianie");
        assertEquals(5, krolowa.getY(), "Wspolrzedna Y krolowej nie powinna ulec zmianie");
    }

    @Test
    public void testReprodukcjaBrakZasobow() {
        Ul ulBrakZasobow = new Ul(5, 5, 5, 10);
        Krolowa biednaKrolowa = new Krolowa(5, 5, ulBrakZasobow, plansza);

        Pszczola potomek = biednaKrolowa.reprodukcja();

        assertNull(potomek, "Reprodukcja nie powinna sie udac przy braku miodu w stosunku do populacji");
        assertEquals(10, ulBrakZasobow.getLiczbaPopulacji(), "Populacja nie powinna wzrosnac");
    }

    @Test
    public void testReprodukcjaSukces() {
        Pszczola potomek = krolowa.reprodukcja();

        assertNotNull(potomek, "Reprodukcja powinna sie udac przy wystarczajacej ilosci miodu");
        assertEquals(6, ul.getLiczbaPopulacji(), "Populacja w ulu powinna wzrosnac do 6 po narodzinach");
        assertTrue(plansza.getAktywneOwady().contains(potomek), "Nowa pszczola powinna zostac dodana do aktywnej listy na planszy");
    }

}