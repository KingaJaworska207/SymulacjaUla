# Symulacja ekosystemu pszczół
Projekt wykonany na zajęcia laboratoryjne z Programowania Obiektowego.

## Członkowie zespołu
- Kinga Jaworska - lider
- Khrystyna Polyanchuk

## Opis projektu
Tematem projektu jest symulacja agentowa modelująca życie i przetrwanie kolonii pszczół w dynamicznym środowisku. Symulacja odbywa się na dwuwymiarowej planszy, na której agenci (podzieleni na role: Królowa, Robotnice, Trutnie i Strażniczki oraz wrogie Szerszenie) wchodzą w interakcje ze sobą i z otoczeniem, w którym losowo pojawiają się kwiaty dające nektar. Przebieg programu opiera się na podanych przez użytkownika parametrach początkowych oraz losowo generowanych zdarzeniach (wynik symulacji nie będzie zawsze taki sam, nawet dla takich samych parametrów). System w każdej turze rejestruje statystyki zasobów oraz populacji ula, a sama symulacja dobiega końca w przypadku wymarcia wszystkich robotnic lub osiągnięcia wyznaczonego limitu tur.

## Specyfikacja
Projekt zrealizowany został przy użyciu języka Java (wersja JDK 21).

Do budowania projektu wykorzystano Gradle (Kotlin DSL).

Do testów jednostkowych oraz testów pokrycia wykorzystane zostały biblioteki JUnit oraz JaCoCo.

## Jak uruchomić?
### Opcja 1: Uruchomienie w środowisku IntelliJ IDEA
1. Otwórz [IntelliJ IDEA](https://www.jetbrains.com/idea/). Wybierz opcję `Clone Repository` w prawym górnym rogu okna.
2. W miejscu na URL wklej adres repozytorium:
```
https://github.com/KingaJaworska207/SymulacjaUla.git
```
3. Wciśnij przycisk `Clone` i poczekaj, aż środowisko pobierze pliki oraz zsynchronizuje projekt.
4. W oknie projektu odszukaj klasę Main (ścieżka: `src/main/java/Main.java`).
5. Wciśnij przycisk zielonego trójkąta widoczny na samej górze okna, aby uruchomić program.

### Opcja 2: Uruchomienie z poziomu terminala
1. Sklonuj repozytorium z serwisu [GitHub](https://github.com):
```
git clone https://github.com/KingaJaworska207/SymulacjaUla.git
```
2. Przejdź do nowo utworzonego katalogu:
```
cd SymulacjaUla
```
3. Uruchom program za pomocą polecenia:
- Dla systemu Windows:
```
.\gradlew.bat run
```
- Dla systemu Linux/macOS:
Najpierw należy dodać uprawnienia do uruchamiania skryptów:
```
chmod +x gradlew
```
Następnie uruchomić program:
```
./gradlew run
```
> Program testowany był tylko na systemie Windows.

## Dokumentacja JavaDoc
Dokumantacja w formie plików `*.html`, wygenerowana za pomocą Javadoc, znajduje się w folderze `docs`.

## Diagramy UML

## Przykładowe wykresy statystyk
