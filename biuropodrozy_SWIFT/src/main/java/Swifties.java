import java.io.IOException;
import java.util.*;

public class Swifties {
   static int number_of_artists_;
   static Artist[] list_of_artists_;
   static Scanner our_object = new Scanner(System.in);

   public static void how_many_artists(int number_of_artists_) {
       System.out.print("Podaj w przyblizeniu ilosc artystow w symulacji [1-60]: ");
       number_of_artists_ = our_object.nextInt();

       try {
           List<String> artistNames = DataReader.read_data("ListOfArtists.txt", number_of_artists_);
           list_of_artists_ = new Artist[artistNames.size()];
           for (int i = 0; i < artistNames.size(); i++) {
               list_of_artists_[i] = new Artist(artistNames.get(i), 0, 0);
           }
           System.out.println(artistNames);
           //our_object.close();
       } catch (IOException e) {
           System.out.println("Wystapil blad podczas odczytu pliku.");
           e.printStackTrace();
       }
   }


   public static Artist[] creating_list_of_artists() {//tu tworzymy liste artystow (obiektow) z ich danymi
       how_many_artists(Swifties.number_of_artists_);
       Random random_budget = new Random();
       Random random_popularity = new Random();


       for (int i = 0; i < DataReader.artists_.size(); i++) {
           String artistName = DataReader.artists_.get(i);
           double budget = Math.round(random_budget.nextDouble() * (10500 - 10000) + 10000);
           double popularity = Math.round((random_popularity.nextDouble() * (0.40 - 0.37) + 0.37) * 100) / 100.0;
           switch (artistName) {
               case "Taylor Swift":
                   list_of_artists_[i] = new TaylorSwift(artistName, budget, popularity);
                   break;
               case "Azealia Banks":
                   list_of_artists_[i] = new AzealiaBanks(artistName, budget, popularity);
                   break;
               case "Amateur":
                   list_of_artists_[i] = new Amateur(artistName, budget, popularity);
                   break;
               default:
                   list_of_artists_[i] = new Artist(artistName, budget, popularity);
                   break;
           }
       }

       for (Artist artist_name : list_of_artists_) {
           System.out.println(artist_name.pseudonym + " | " + artist_name.popularity + " | " + artist_name.budget);
       }

       return list_of_artists_;
   }

}