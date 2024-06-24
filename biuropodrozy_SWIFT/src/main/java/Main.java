import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Glówna klasa, która odpowiada za wywołanie metod
 */
public class Main {
    /**
     * Główna metoda, odpowiadająca za wywołanie innych metod w programie
     * @param args argument, przekazywany programowi
     */
    public static void main(String[] args) {
        Swifties.how_many_artists();
        Fight.comparison();
        Concert.artist_plays_concert();
        Concert.artists_fighting_more();
        //researches();
    }

    /*public static void researches(){
        try {
            FileWriter our_researches = new FileWriter("Badanie_TaylorSwift.txt");
            FileWriter did_TS = new FileWriter("did_TS.txt");
            FileWriter did_AB = new FileWriter("did_AB.txt");
            FileWriter did_Am = new FileWriter("did_Am.txt");
            for (int i=0; i<5; i++){
                for(Swifties.number_of_artists_=2; Swifties.number_of_artists_<31; Swifties.number_of_artists_+=2){
                    SplitIntoPairs.distance();
                    Concert.artists_fighting_more();
                    if(DataReader.artists_.contains("Taylor Swift")){
                        did_TS.write("1\n");
                    }else did_TS.write("0\n");

                    if(DataReader.artists_.contains("Azealia Banks")){
                        did_AB.write("1\n");
                    }else did_AB.write("0\n");

                    if(DataReader.artists_.contains("Amateur")){
                        did_Am.write("1\n");
                    } else did_Am.write("0\n");

                    if (Concert.artists_fighting_more().equals("Taylor Swift")){
                        our_researches.write("Taylor Swift\n");
                    } else if(Concert.artists_fighting_more().equals("Azealia Banks")){
                        our_researches.write("Azealia Banks\n");
                    }else if(Concert.artists_fighting_more().equals("Amateur")){
                        our_researches.write("Amateur\n");
                    }else our_researches.write("Zwykly artysta\n");
                }
            }

            our_researches.close();
            did_TS.close();
            did_AB.close();
            did_Am.close();
        } catch (Exception e) {
            System.out.println("Blad przy zapisywaniu danych");
            e.getStackTrace();
        }
    }*/
}

