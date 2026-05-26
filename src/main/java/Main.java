import kontroler.SystemSymulacji;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- KONFIGURACJA SYMULACJI ---");

        System.out.println("Podaj poczatkowa ilosc miodu w ulu: ");
        int poczMiod = scanner.nextInt();

        System.out.println("Podaj liczbe trutni: ");
        int poczTrutnie = scanner.nextInt();

        System.out.println("Podaj poczatkowa liczbe robotnic: ");
        int poczRobotnice = scanner.nextInt();

        System.out.println("Podaj poczatkowa liczbe strazniczek: ");
        int poczStrazniczki = scanner.nextInt();

        System.out.println("Podaj poczatkowa liczbe szerszeni: ");
        int poczSzerszenie = scanner.nextInt();

        System.out.println("Podaj co ile tur ma sie pojawiac nowy szerszen: ");
        int czestotliwoscSzerszeni = scanner.nextInt();

        System.out.print("Podaj prawdopodobienstwo pojawienia sie kwiatka (np. 0,3): ");
        double prawdopodobienstwoKwiatka = scanner.nextDouble();

        System.out.println("Podaj maksymalna liczbe tur (czas trwania symulacji): ");
        int maksymalnaLiczbaTur = scanner.nextInt();

        System.out.println("--- ROZPOCZECIE SYMULACJI ---");

        SystemSymulacji system = new SystemSymulacji(
                prawdopodobienstwoKwiatka,
                czestotliwoscSzerszeni,
                maksymalnaLiczbaTur,
                poczRobotnice, poczTrutnie,
                poczStrazniczki,
                poczSzerszenie,
                poczMiod
        );

        // Uruchomienie głównej pętli symulacji
        system.uruchom();

        scanner.close();
    }
}
