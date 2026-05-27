package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KwiatTest {

    @Test
    void pobierzNektarZNiezebranego() {
        Kwiat kwiat = new Kwiat(0, 0 , 2);

        int pobranyNektar = kwiat.pobierzNektar();

        assertEquals(2,pobranyNektar, "Metoda powinna zwrócić całą początkową ilość nektaru." );

        assertTrue(kwiat.czyZebrany(), "Po pobraniu nektaru kwiat powinien być oznaczony jako zebrany.");
    }

    @Test
    void testPobierzNektarZZebranego() {
        Kwiat kwiat = new Kwiat(3, 3, 20);

        kwiat.pobierzNektar();

        int pobranyNektarDrugiRaz = kwiat.pobierzNektar();


        assertEquals(0, pobranyNektarDrugiRaz, "Z zebranego kwiatu nie można pobrać więcej nektaru.");
        assertTrue(kwiat.czyZebrany(), "Kwiat powinien nadal posiadać status zebranego.");
    }
}