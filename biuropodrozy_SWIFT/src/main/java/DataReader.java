import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Klasa, odpowiadająca za otwarcie pliku i zapisywanie danych z niego
 */
public class DataReader {
    /**
     * Zmienna, przechowująca listę artystów, pobranych z pliku
     */
    static List<String> artists_ = new ArrayList<>();

    /**
     * Metoda, odpowiadająca za tworzenie listy pseudonimów artystów za pomocą odczytu pliku
     * @param filename plik, z którego pobieramy pseudonimy artystów
     * @param number liczba artystów w symulacji, wprowadzona przez użytkownika
     * @return zwraca listę pseudonimów artystów
     * @throws IOException błąd, wypisywany przy nieznalezieniu pliku
     */
    protected static List<String> read_data(String filename, int number) throws IOException {

        //artists_.clear();
        if (TaylorSwift.taylors_in_play()) artists_.add("Taylor Swift");
        if (AzealiaBanks.azealias_in_play()) artists_.add("Azealia Banks");
        if (Amateur.amateurs_in_play()) artists_.add("Amateur");


        int number_of_exclusive_artists = artists_.size();
        try {
            File file = new File(filename);
            Scanner file_reader = new Scanner(file);
            for (int i = 0; i < number - number_of_exclusive_artists; i++) {
                if (file_reader.hasNextLine()) {
                    String one_artist = file_reader.nextLine();
                    artists_.add(one_artist);
                }

            }
            file_reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie znaleziono, sprobuj ponownie");
            e.printStackTrace();
        }

        return artists_;
    }
}
