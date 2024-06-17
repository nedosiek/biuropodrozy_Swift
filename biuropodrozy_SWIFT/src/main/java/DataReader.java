import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReader {
    static List<String> artists_ = new ArrayList<>(); //tabela do ktorej sczytujemy pseudonimy artystow


    public static List<String> read_data(String filename, int number) throws IOException {  //tu pobieramy pseudonimy artystow z pliku
        artists_.clear();

        try {
            File file = new File(filename); //z
            Scanner file_reader = new Scanner(file);
            for (int i=0; i<number; i++) {
                if (file_reader.hasNextLine()) {
                    String one_artist = file_reader.nextLine();
                    artists_.add(one_artist);
                }
            }
            file_reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie znaleziono, sprobuj ponownie"); //wiadomosc o nieznalezieniu pliku z pseudonimami
            e.printStackTrace();
        }

        //dodawania artystow "specjalnych" na podstawie ilosci zadeklarowanych zwyklych artystow
        if (TaylorSwift.taylors_in_play()) artists_.add("Taylor Swift");
        if (AzealiaBanks.azealias_in_play()) artists_.add("Azealia Banks");
        if (Amateur.amateurs_in_play(Amateur.amateur_chance)) artists_.add("Amateur");

        System.out.println(artists_.size()); //wypisanie poczatkowych danych artystow

        return artists_;
    }
}
