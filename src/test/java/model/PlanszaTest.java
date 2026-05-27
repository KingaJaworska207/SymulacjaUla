package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlanszaTest {

    private Plansza plansza;

    @BeforeEach
    public void setUp() {
        plansza = new Plansza(10, 10);
    }

    @Test
    public void testInicjalizacjiPlanszy() {
        assertEquals(10, plansza.getSzerokosc(), "Szerokosc planszy powinna wynosic 10");
        assertEquals(10, plansza.getWysokosc(), "Wysokosc planszy powinna wynosic 10");
        assertEquals(0, plansza.getAktywneOwady().size(), "Nowa plansza nie powinna miec zadnych owadow");
        assertEquals(0, plansza.getKwiaty().size(), "Nowa plansza nie powinna miec zadnych kwiatow");
    }

    @Test
    public void testGenerujPoczatkowaPlansze() {
        plansza.generujPoczatkowaPlansze(2, 1, 1, 2, 20);

        assertEquals(5, plansza.getPopulacjaUla(), "Populacja ula powinna wynosic 5");
        assertEquals(20, plansza.getPoziomMiodu(), "Poczatkowy poziom miodu powinien wynosic 20");
        assertEquals(7, plansza.getAktywneOwady().size(), "Na planszy powinno byc 7 aktywnych owadow");
        assertEquals(45, plansza.getKwiaty().size(), "Na planszy powinno byc 45 kwiatow");
    }

    @Test
    public void testCzyPoleZajetePozaGranicami() {
        assertTrue(plansza.czyPoleZajete(-1, 5), "Pole o ujemnej wspolrzednej X powinno byc zgloszone jako zajete");
        assertTrue(plansza.czyPoleZajete(5, -1), "Pole o ujemnej wspolrzednej Y powinno byc zgloszone jako zajete");
        assertTrue(plansza.czyPoleZajete(10, 5), "Pole poza szerokoscia powinno byc zgloszone jako zajete");
        assertTrue(plansza.czyPoleZajete(5, 10), "Pole poza wysokoscia powinno byc zgloszone jako zajete");
    }

    @Test
    public void testStworzKwiatICzyPoleZajete() {
        plansza.stworzKwiat();

        assertEquals(1, plansza.getKwiaty().size(), "Na planszy powinien pojawic sie jeden kwiat");

        Kwiat stworzonyKwiat = plansza.getKwiaty().get(0);
        int x = stworzonyKwiat.getX();
        int y = stworzonyKwiat.getY();

        assertTrue(plansza.czyPoleZajete(x, y), "Pole, na ktorym wygenerowano kwiat, powinno byc zajete");
        assertEquals(stworzonyKwiat, plansza.getObiekt(x, y), "Obiekt pobrany z siatki powinien byc tym samym kwiatem");
    }

    @Test
    public void testDodajOwadaIStatystykiNarodzin() {
        plansza.generujPoczatkowaPlansze(0, 0, 0, 0, 0);
        plansza.resetujLicznikiTury();

        Robotnica nowaRobotnica = new Robotnica(1, 1, (Ul) plansza.getObiekt(5, 5), plansza);
        plansza.dodajOwada(nowaRobotnica);

        assertTrue(plansza.getAktywneOwady().contains(nowaRobotnica), "Lista aktywnych owadow powinna zawierac nowa robotnice");
        assertEquals(1, plansza.getNarodzinyWTejTurze(), "Licznik narodzin powinien wzrosnac o 1 po dodaniu pszczoly");
    }

    @Test
    public void testUsunObiektIStatystykiZgonow() {
        plansza.generujPoczatkowaPlansze(0, 0, 0, 0, 0);
        plansza.resetujLicznikiTury();

        Robotnica robotnica = new Robotnica(1, 1, (Ul) plansza.getObiekt(5, 5), plansza);
        plansza.dodajOwada(robotnica);

        plansza.resetujLicznikiTury();

        plansza.usunObiekt(robotnica);

        assertFalse(plansza.getAktywneOwady().contains(robotnica), "Lista aktywnych owadow nie powinna zawierac usunietej robotnicy");
        assertEquals(1, plansza.getZgonyWTejTurze(), "Licznik zgonow powinien wzrosnac o 1 po usunieciu pszczoly");
    }

    @Test
    public void testUsunKwiataZPlanszy() {
        plansza.stworzKwiat();
        Kwiat kwiat = plansza.getKwiaty().get(0);
        int x = kwiat.getX();
        int y = kwiat.getY();

        plansza.usunObiekt(kwiat);

        assertFalse(plansza.getKwiaty().contains(kwiat), "Usuniety kwiat nie powinien znajdowac sie na liscie kwiatow");
        assertNull(plansza.getObiekt(x, y), "Pole na siatce po usunieciu kwiata powinno byc puste");
        assertFalse(plansza.czyPoleZajete(x, y), "Pole po usunieciu kwiata powinno byc znowu wolne");
    }

    @Test
    public void testCzySaRobotnice() {
        assertFalse(plansza.czySaRobotnice(), "Pusta plansza nie powinna zglaszac obecnosci robotnic");

        plansza.generujPoczatkowaPlansze(1, 0, 0, 0, 0);

        assertTrue(plansza.czySaRobotnice(), "Plansza z wygenerowana robotnica powinna zwrocic true");
    }

    @Test
    public void testResetujLicznikiTury() {
        plansza.generujPoczatkowaPlansze(0, 0, 0, 0, 0);
        Robotnica robotnica = new Robotnica(1, 1, (Ul) plansza.getObiekt(5, 5), plansza);

        plansza.dodajOwada(robotnica);
        plansza.usunObiekt(robotnica);

        assertTrue(plansza.getNarodzinyWTejTurze() > 0, "Licznik narodzin powinien byc wiekszy od zera");
        assertTrue(plansza.getZgonyWTejTurze() > 0, "Licznik zgonow powinien byc wiekszy od zera");

        plansza.resetujLicznikiTury();

        assertEquals(0, plansza.getNarodzinyWTejTurze(), "Licznik narodzin powinien wynosic 0 po restarcie");
        assertEquals(0, plansza.getZgonyWTejTurze(), "Licznik zgonow powinien wynosic 0 po restarcie");
    }
}