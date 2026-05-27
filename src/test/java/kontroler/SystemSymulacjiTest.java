package kontroler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import model.Plansza;
import model.Owad;
import model.Szerszen;

public class SystemSymulacjiTest {

    private SystemSymulacji symulacja;

    @BeforeEach
    public void setUp() {
        symulacja = new SystemSymulacji(0.0, 2, 5, 2, 0, 0, 0, 10);
    }

    private int getTuraZSymulacji() throws Exception {
        Field poleTury = SystemSymulacji.class.getDeclaredField("tura");
        poleTury.setAccessible(true);
        return (int) poleTury.get(symulacja);
    }

    private Plansza getPlanszaZSymulacji() throws Exception {
        Field polePlanszy = SystemSymulacji.class.getDeclaredField("plansza");
        polePlanszy.setAccessible(true);
        return (Plansza) polePlanszy.get(symulacja);
    }

    @Test
    public void testInicjalizacjiWartosciPoczatkowych() throws Exception {
        int tura = getTuraZSymulacji();
        Plansza plansza = getPlanszaZSymulacji();

        assertEquals(0, tura, "Poczatkowa tura powinna wynosic 0");
        assertNotNull(plansza, "Plansza w symulacji nie powinna byc nullem");
        assertTrue(plansza.czySaRobotnice(), "Na poczatku powinny byc obecne wygenerowane robotnice");
    }

    @Test
    public void testCzyKoniecGdyBrakRobotnic() {
        SystemSymulacji krotkaSymulacja = new SystemSymulacji(0.0, 5, 10, 0, 0, 0, 0, 10);

        assertTrue(krotkaSymulacja.czyKoniec(), "Symulacja powinna zglosic koniec od razu, poniewaz nie ma robotnic");
    }

    @Test
    public void testCzyKoniecWTrwajacejSymulacji() {
        assertFalse(symulacja.czyKoniec(), "Symulacja nie powinna sie konczyc, jesli limit tur nie minal i sa robotnice");
    }

    @Test
    public void testAktualizujTureZwiekszaLicznik() throws Exception {
        symulacja.aktualizujTure();

        int tura = getTuraZSymulacji();
        assertEquals(1, tura, "Licznik tury powinien wzrosnac do 1 po pierwszym wywolaniu aktualizujTure");
    }

    @Test
    public void testAktualizujTureKonczyPoOsiagnieciuLimitu() throws Exception {
        symulacja.aktualizujTure();
        symulacja.aktualizujTure();
        symulacja.aktualizujTure();
        symulacja.aktualizujTure();
        symulacja.aktualizujTure();

        int tura = getTuraZSymulacji();
        assertEquals(5, tura, "Tura powinna wynosic 5");
        assertTrue(symulacja.czyKoniec(), "Symulacja powinna zglosic koniec ze wzgledu na osiagniecie maksymalnej liczby tur");
    }

    @Test
    public void testGenerowaniaSzerszeniaZgodnieZCzestotliwoscia() throws Exception {
        Plansza plansza = getPlanszaZSymulacji();

        symulacja.aktualizujTure();

        symulacja.aktualizujTure();

        boolean czyZnalezionoSzerszenia = false;
        for (Owad owad : plansza.getAktywneOwady()) {
            if (owad instanceof Szerszen) {
                czyZnalezionoSzerszenia = true;
                break;
            }
        }

        assertTrue(czyZnalezionoSzerszenia, "Na planszy powinien pojawic sie nowy szerszen wygenerowany w parzystej turze");
    }
}