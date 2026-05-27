package widok;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Klasa odpowiedzialna za eksportowanie danych symulacji do pliku CSV.
 */

public class EksporterCSV {

    /**
     * Zapisuje przekazaną listę danych jako nowe wiersze w określonym pliku CSV.
     *
     * @param wierszeDanych lista ciągów znaków (String), gdzie każdy element reprezentuje jeden wiersz danych do zapisu
     * @param nazwaPliku nazwa pliku docelowego, w którym zostaną zapisane dane
     */
    public void eksportujDoPliku(List<String> wierszeDanych, String nazwaPliku){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nazwaPliku))) {

            for (String wiersz : wierszeDanych) {
                writer.write(wiersz);
                writer.newLine();
            }
            System.out.println("Dane z symulacji zostaly pomyslnie zapisane do pliku: " + nazwaPliku);

        } catch (IOException e) {
            System.err.println("Wystapil blad podczas zapisu pliku CSV: " + e.getMessage());
        }
    }
}
