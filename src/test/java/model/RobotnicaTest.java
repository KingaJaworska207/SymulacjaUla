package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RobotnicaTest {

    private Plansza plansza;
    private Ul ul;
    private Robotnica robotnica;

    @BeforeEach
    public void setUp() {

        plansza = new Plansza(10, 10);

        ul = new Ul(5, 5, 0, 100);

        robotnica = new Robotnica(0, 0, ul, plansza);
    }


    @Test
    public void testDziedziczeniaPoObiektIOwad() {
        assertEquals(0, robotnica.getX(), "Poczatkowa wspolrzedna X powinna wynosic 0");
        assertEquals(0, robotnica.getY(), "Poczatkowa wspolrzedna Y powinna wynosic 0");
        assertTrue(robotnica.czyZyje(), "Nowo stworzona robotnica powinna zyc");
    }

    @Test
    public void testMetodyZginDziedziczonejPoPszczola() {

        assertEquals(100, ul.getLiczbaPopulacji(), "Przed smiercia populacja powinna wynosic 100");

        robotnica.zgin();

        assertFalse(robotnica.czyZyje(), "Robotnica po smierci nie powinna zyc");

        assertEquals(99, ul.getLiczbaPopulacji(), "Populacja w ulu powinna spasc do 99 po smierci pszczoly");
    }


    @Test
    public void testRuchuZmieniaWspolrzedne() {
        robotnica.ruch(2, 3);

        assertEquals(2, robotnica.getX());
        assertEquals(3, robotnica.getY());
    }

    @Test
    public void testZbierzNektarSukces() {

        Kwiat kwiat = new Kwiat(0, 0, 2);


        int zebrany = robotnica.zbierzNektar(kwiat);


        assertEquals(2, zebrany, "Robotnica powinna zebrac 2 jednostki nektaru");


        assertTrue(kwiat.czyZebrany(), "Kwiat powinien zostac oznaczony jako zebrany po akcji robotnicy");
    }

    @Test
    public void testZbierzNektarNiepoprawnaPozycja() {

        Kwiat kwiat = new Kwiat(1, 1, 2);


        int zebrany = robotnica.zbierzNektar(kwiat);


        assertEquals(0, zebrany, "Robotnica nie powinna zebrac nektaru, jesli nie stoi na kwiacie");
        assertFalse(kwiat.czyZebrany(), "Kwiat nie powinien byc oznaczony jako zebrany, jesli akcja się nie powiodla");
    }

    @Test
    public void testPrzekazNektarDoUla() {

        Kwiat kwiat = new Kwiat(0, 0, 2);
        robotnica.zbierzNektar(kwiat);

        robotnica.ruch(5, 5);


        assertEquals(0, ul.getIloscMiodu(), "Ul przed otrzymaniem miodu powinien miec go 0");


        int przekazany = robotnica.przekazNektar();


        assertEquals(2, przekazany, "Robotnica powinna przekazac caly zebrany nektar (2)");


        assertEquals(2, ul.getIloscMiodu(), "Ul powinien posiadac 2 jednostki miodu po przekazaniu");
    }
}