import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReader {
    static List<String> artists_ = new ArrayList<>();


    public static List<String> read_data(String filename, int number) throws IOException {  //tu pobieramy dane artystow z pliku
        List<String> artists = new ArrayList<>();
        artists = artists_;

        try {
            File file = new File(filename);
            Scanner file_reader = new Scanner(file);
            for (int i=0; i<number; i++) {
                if (file_reader.hasNextLine()) {
                    String one_artist = file_reader.nextLine();
                    artists.add(one_artist);
                }
            }
            file_reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie znaleziono, sprobuj ponownie");
            e.printStackTrace();
        }

        if (TaylorSwift.taylors_in_play()){
            artists.add("Taylor Swift");
        }

        if (AzealiaBanks.azealias_in_play()){
            artists.add("Azealia Banks");
        }

        if (Amateur.amateurs_in_play()){
            artists.add("Amateur");
        }

        artists_ = artists;
        System.out.println(artists_.size());

        return artists_;
    }
}
