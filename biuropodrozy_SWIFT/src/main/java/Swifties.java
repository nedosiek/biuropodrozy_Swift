import java.io.IOException;
import java.util.*;

public class Swifties {
   static int number_of_artists_ = 0;
   //static Artist[] list_of_artists_ = new Artist[DataReader.artists_.size()];
   static Artist[] list_of_artists_;
   static Scanner our_object = new Scanner(System.in);

   public static int how_many_artists() {
       System.out.print("Podaj w przyblizeniu ilosc artystow w symulacji [1-100]: ");
       number_of_artists_ = our_object.nextInt();


       try {
           List<String> artistNames = DataReader.read_data("ListOfArtists.txt", number_of_artists_);
           list_of_artists_ = new Artist[artistNames.size()];
           for (int i = 0; i < artistNames.size(); i++) {
               list_of_artists_[i] = new Artist(artistNames.get(i), 0, 0);
           }
           System.out.println(artistNames);

       } catch (IOException e) {
           System.out.println("Wystapil blad podczas odczytu pliku.");
           e.printStackTrace();
       }
       //our_object.close();

       //return number_of_artists_ = number_of_artists;
       return number_of_artists_;
   }


   public static Artist[] creating_list_of_artists() { //tu tworzymy liste artystow (obiektow) z ich danymi
       //Swifties.how_many_artists();
       Random random_budget = new Random();
       Random random_popularity = new Random();


       for (int i = 0; i < DataReader.artists_.size(); i++) {
           String artistName = DataReader.artists_.get(i);
           double budget = Math.round(random_budget.nextDouble() * (14000 - 10000) + 10000);
           double popularity = Math.round((random_popularity.nextDouble() * (0.47 - 0.35) + 0.35) * 100) / 100.0;
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
           System.out.println(artist_name.pseudonym + " /// " + artist_name.popularity + " /// " +
                       artist_name.budget);
       }

       return list_of_artists_;
   }

}