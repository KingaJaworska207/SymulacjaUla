package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrazniczkaTest {

    private Plansza plansza;
    private Ul ul;
    private Strazniczka strazniczka;

    @BeforeEach
    public void setUp() {
        plansza = new Plansza(10, 10);
        ul = new Ul(5, 5, 0, 100);
        strazniczka = new Strazniczka(5, 5, ul, plansza);

        plansza.dodajOwada(strazniczka);
    }

    @Test
    public void testDziedziczeniaPoObiektIOwad() {
        assertEquals(5, strazniczka.getX(), "Poczatkowa wspolrzedna X powinna wynosic 5");
        assertEquals(5, strazniczka.getY(), "Poczatkowa wspolrzedna Y powinna wynosic 5");
        assertTrue(strazniczka.czyZyje(), "Nowo stworzona strazniczka powinna zyc");
    }

    @Test
    public void testMetodyZginDziedziczonejPoPszczola() {
        assertEquals(100, ul.getLiczbaPopulacji(), "Przed smiercia populacja ula powinna wynosic 100");

        strazniczka.zgin();

        assertFalse(strazniczka.czyZyje(), "Strazniczka po smierci nie powinna zyc");
        assertEquals(99, ul.getLiczbaPopulacji(), "Populacja w ulu powinna spasc do 99 po smierci pszczoly");
    }

    @Test
    public void testRuchuZmieniaWspolrzedne() {
        strazniczka.ruch(6, 7);

        assertEquals(6, strazniczka.getX(), "Wspolrzedna X powinna ulec zmianie na 6");
        assertEquals(7, strazniczka.getY(), "Wspolrzedna Y powinna ulec zmianie na 7");
    }

    @Test
    public void testNapotkanieSzerszeniaSukces() {
        Szerszen szerszen = new Szerszen(5, 5, plansza);
        plansza.dodajOwada(szerszen);

        Szerszen napotkany = strazniczka.napotkanieSzerszenia();

        assertNotNull(napotkany, "Strazniczka powinna zauwazyc szerszenia na swojej pozycji");
        assertEquals(szerszen, napotkany, "Zauwazony owad powinien byc tym samym obiektem szerszenia");
    }

    @Test
    public void testNapotkanieSzerszeniaGdyJestDaleko() {
        Szerszen szerszen = new Szerszen(8, 8, plansza);
        plansza.dodajOwada(szerszen);

        Szerszen napotkany = strazniczka.napotkanieSzerszenia();

        assertNull(napotkany, "Strazniczka nie powinna zauwazyc szerszenia, ktory jest na innym polu");
    }

    @Test
    public void testEliminujLosowyWynikStarcia() {
        Szerszen szerszen = new Szerszen(5, 5, plansza);
        plansza.dodajOwada(szerszen);

        strazniczka.eliminuj(szerszen);

        boolean zycieStrazniczki = strazniczka.czyZyje();
        boolean zycieSzerszenia = szerszen.czyZyje();

        assertTrue(zycieStrazniczki != zycieSzerszenia, "Tylko jeden owad powinien przezyc starcie");

        if (zycieStrazniczki) {
            assertFalse(plansza.getAktywneOwady().contains(szerszen), "Martwy szerszen powinien zostac usuniety z planszy");
        } else {
            assertFalse(plansza.getAktywneOwady().contains(strazniczka), "Martwa strazniczka powinna zostac usunieta z planszy");
        }
    }

    @Test
    public void testWykonajAkcjeKrokWStroneSzerszenia() {
        Szerszen szerszen = new Szerszen(7, 7, plansza);
        plansza.dodajOwada(szerszen);

        strazniczka.wykonajAkcje();

        assertEquals(6, strazniczka.getX(), "Strazniczka powinna zrobic krok w strone X szerszenia");
        assertEquals(6, strazniczka.getY(), "Strazniczka powinna zrobic krok w strone Y szerszenia");
    }

}