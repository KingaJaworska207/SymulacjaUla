package widok;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;

import model.Plansza;
import model.Ul;
import model.Robotnica;
import model.Strazniczka;
import model.Szerszen;

public class RejestratorStatystykTest {

    private RejestratorStatystyk rejestrator;
    private Plansza plansza;
    private Ul ul;

    @BeforeEach
    public void setUp() {
        rejestrator = new RejestratorStatystyk();
        plansza = new Plansza(10, 10);

        ul = new Ul(5, 5, 20, 5);

        try {
            Field poleSiatka = Plansza.class.getDeclaredField("siatka");
            poleSiatka.setAccessible(true);
            Object[][] siatka = (Object[][]) poleSiatka.get(plansza);
            siatka[5][5] = ul;

            Field poleUl = Plansza.class.getDeclaredField("ul");
            poleUl.setAccessible(true);
            poleUl.set(plansza, ul);
        } catch (Exception e) {
            fail("Nie udalo sie ustawic ula na planszy przy uzyciu refleksji");
        }
    }

    // Metoda pomocnicza do pobierania prywatnej listy z historia danych
    @SuppressWarnings("unchecked")
    private List<String> getHistoriaDanych() throws Exception {
        Field poleHistorii = RejestratorStatystyk.class.getDeclaredField("historiaDanych");
        poleHistorii.setAccessible(true);
        return (List<String>) poleHistorii.get(rejestrator);
    }

    @Test
    public void testInicjalizacjiNaglowkow() throws Exception {
        List<String> historia = getHistoriaDanych();

        assertEquals(1, historia.size(), "Lista powinna zawierac dokladnie jeden element po inicjalizacji (naglowki)");

        String oczekiwanyNaglowek = "Tura;Miod;Populacja;Robotnice;Strazniczki;Trutnie;Krolowe;Szerszenie;NarodzinyPszczol;ZgonyPszczol";
        assertEquals(oczekiwanyNaglowek, historia.get(0), "Naglowki kolumn nie zgadzaja sie z oczekiwanym ukladem");
    }

    @Test
    public void testRejestrujDaneTuryPoprawnoscWiersza() throws Exception {
        Robotnica r1 = new Robotnica(5, 5, ul, plansza);
        Robotnica r2 = new Robotnica(5, 5, ul, plansza);
        Strazniczka s1 = new Strazniczka(5, 5, ul, plansza);
        Szerszen sz1 = new Szerszen(2, 2, plansza);

        plansza.dodajOwada(r1);
        plansza.dodajOwada(r2);
        plansza.dodajOwada(s1);
        plansza.dodajOwada(sz1);

        rejestrator.rejestrujDaneTury(1, plansza);

        List<String> historia = getHistoriaDanych();


        assertEquals(2, historia.size(), "Historia powinna zawierac naglowek oraz jeden wiersz z danymi tury");

        String oczekiwanyWiersz = "1;20;5;2;1;0;0;1;3;0";
        assertEquals(oczekiwanyWiersz, historia.get(1), "Zarejestrowany wiersz z danymi ma nieprawidlowy format badz wartosci");
    }

    @Test
    public void testRejestrujDaneTuryZgonyINarodziny() throws Exception {
        Robotnica r1 = new Robotnica(5, 5, ul, plansza);
        plansza.dodajOwada(r1);
        plansza.usunObiekt(r1);

        rejestrator.rejestrujDaneTury(2, plansza);

        List<String> historia = getHistoriaDanych();

        String oczekiwanyWiersz = "2;20;5;0;0;0;0;0;1;1";
        assertEquals(oczekiwanyWiersz, historia.get(1), "Zarejestrowany wiersz niepoprawnie zlicza narodziny i zgony");
    }
}