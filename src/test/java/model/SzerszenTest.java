package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SzerszenTest {

    private Plansza plansza;
    private Ul ul;
    private Szerszen szerszen;

    @BeforeEach
    public void setUp() {
        plansza = new Plansza(10, 10);
        ul = new Ul(5, 5, 0, 100);
        szerszen = new Szerszen(5, 5, plansza);

        plansza.dodajOwada(szerszen);
    }

    @Test
    public void testDziedziczeniaPoObiektIOwad() {
        assertEquals(5, szerszen.getX(), "Poczatkowa wspolrzedna X powinna wynosic 5");
        assertEquals(5, szerszen.getY(), "Poczatkowa wspolrzedna Y powinna wynosic 5");
        assertTrue(szerszen.czyZyje(), "Nowo stworzony szerszen powinien zyc");
    }

    @Test
    public void testRuchuZmieniaWspolrzedne() {
        szerszen.ruch(6, 7);

        assertEquals(6, szerszen.getX(), "Wspolrzedna X powinna ulec zmianie na 6");
        assertEquals(7, szerszen.getY(), "Wspolrzedna Y powinna ulec zmianie na 7");
    }

    @Test
    public void testNapotkaniePszczolySukces() {
        Robotnica robotnica = new Robotnica(5, 5, ul, plansza);
        plansza.dodajOwada(robotnica);

        Pszczola napotkana = szerszen.napotkaniePszczoly();

        assertNotNull(napotkana, "Szerszen powinien zauwazyc pszczole na swojej pozycji");
        assertEquals(robotnica, napotkana, "Zauwazony owad powinien byc tym samym obiektem pszczoly");
    }

    @Test
    public void testNapotkaniePszczolyGdyJestDaleko() {
        Robotnica robotnica = new Robotnica(8, 8, ul, plansza);
        plansza.dodajOwada(robotnica);

        Pszczola napotkana = szerszen.napotkaniePszczoly();

        assertNull(napotkana, "Szerszen nie powinien zauwazyc pszczoly, ktora jest na innym polu");
    }

    @Test
    public void testAtakNaRobotnice() {
        Robotnica robotnica = new Robotnica(5, 5, ul, plansza);
        plansza.dodajOwada(robotnica);

        szerszen.atakuj(robotnica);

        assertFalse(robotnica.czyZyje(), "Robotnica nie powinna przezyc ataku szerszenia");
        assertTrue(szerszen.czyZyje(), "Szerszen powinien przezyc atak na robotnice");
        assertFalse(plansza.getAktywneOwady().contains(robotnica), "Martwa robotnica powinna zostac usunieta z planszy");
    }

    @Test
    public void testAtakNaStrazniczkeLosowyWynik() {
        Strazniczka strazniczka = new Strazniczka(5, 5, ul, plansza);
        plansza.dodajOwada(strazniczka);

        szerszen.atakuj(strazniczka);

        boolean zycieSzerszenia = szerszen.czyZyje();
        boolean zycieStrazniczki = strazniczka.czyZyje();

        assertTrue(zycieSzerszenia != zycieStrazniczki, "Tylko jeden owad powinien przezyc starcie");

        if (zycieSzerszenia) {
            assertFalse(plansza.getAktywneOwady().contains(strazniczka), "Martwa strazniczka powinna zostac usunieta z planszy");
        } else {
            assertFalse(plansza.getAktywneOwady().contains(szerszen), "Martwy szerszen powinien zostac usuniety z planszy");
        }
    }

    @Test
    public void testWykonajTureKrokWStroneRobotnicy() {
        Robotnica robotnica = new Robotnica(7, 7, ul, plansza);
        plansza.dodajOwada(robotnica);

        szerszen.wykonajTure();

        assertEquals(6, szerszen.getX(), "Szerszen powinien zrobic krok w strone X robotnicy");
        assertEquals(6, szerszen.getY(), "Szerszen powinien zrobic krok w strone Y robotnicy");
    }
}