import java.io.IOException;
import java.util.*;

/**
 * Klasa, tworząca listę artystów
 */
public class Swifties {
    /**
     * Zmienna, przychowująca liczbę artystów, którą wprowadza użytkownik
     */
   protected static int number_of_artists_;
    /**
     * Tablica artystów wraz z ich budżetem i popularnością
     */
   protected static Artist[] list_of_artists_;
    /**
     * Zmienna, odpowiadająca za wprowadzenie liczby artystów
     */
   private static final Scanner our_object = new Scanner(System.in);

    /**
     * Metoda, tworząca tablicę artystów z artystów, podanych w pliku
     */
   public static void how_many_artists() {
       System.out.print("Podaj ilosc artystow w symulacji [2-60]: ");
       number_of_artists_ = our_object.nextInt();

       /**
        * Tworzenie tablicy obiektów (artystów) z ich pseudonimami, pobranymi z pliku
        */
       try {
           List<String> artistNames = DataReader.read_data("ListOfArtists.txt", number_of_artists_);
           list_of_artists_ = new Artist[artistNames.size()];
           for (int i = 0; i < list_of_artists_.length; i++) {
               list_of_artists_[i] = new Artist(artistNames.get(i));
           }
       } catch (IOException e) {
           System.out.println("Wystapil blad podczas odczytu pliku.");
           e.printStackTrace();
       }


       Random random_budget = new Random();
       Random random_popularity = new Random();

       /**
        * Przypisanie każdemu obiektu budżetu i popularności w zależności od klasy obiektu
        */
       for (int i = 0; i < list_of_artists_.length; i++) {
           double budget = Math.round(random_budget.nextDouble() * (10500 - 10000) + 10000);
           double popularity = Math.round((random_popularity.nextDouble() * (0.40 - 0.37) + 0.37) * 100) / 100.0;
           switch (list_of_artists_[i].getPseudonym()) {
               case "Taylor Swift":
                   list_of_artists_[i] = new TaylorSwift(budget, popularity);
                   break;
               case "Azealia Banks":
                   list_of_artists_[i] = new AzealiaBanks(budget, popularity);
                   break;
               case "Amateur":
                   list_of_artists_[i] = new Amateur(budget, popularity);
                   break;
               default:
                   list_of_artists_[i].setPseudonym(list_of_artists_[i].getPseudonym());
                   list_of_artists_[i].setBudget(budget);
                   list_of_artists_[i].setPopularity(popularity);
                   break;
           }
       }

       /**
        * Wypisywanie obiektów artystów wraz z ich budżetem i popularnością
        */
       System.out.println("\nLista artystow wraz z poczatkowymi budzetem oraz popularnoscia:");
       System.out.format("%-16s | " + "%11.6s | " +  "%13.11s |\n", "Pseudonim artysty", "Budzet", "Popularnosc");
       for (Artist artist_name : list_of_artists_) {
           System.out.format("%-17s | " + "%10.2f" + "$ | " + "%12.2f" + "%% |\n", artist_name.getPseudonym(), artist_name.getBudget(), artist_name.getPopularity());
       }
       System.out.println("\nArtysci wraz z ich budzetem i popularnoscia tuz po walce:");

   }
}