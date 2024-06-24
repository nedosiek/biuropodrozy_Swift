/**
 * Klasa, odpowiadająca za wybór artysty, który wygra w walce za koncert w mieście
 */
public class Concert {
    /**
     * Zmienna, odpowiadająca za numer artysty, który wygrał
     */
    private static int artist_performing = -1;

    /**
     * Metoda, która wybiera artystę, który zagra koncert na festynie w mieście
     */
    protected static void artist_plays_concert(){
        double min_budget_to_play_concert = 10500 + (Swifties.list_of_artists_.length * 400); //minimalny budzet potrzebny do zagrania koncertu
        double min_popularity_to_play_concert = 0.30 + (Swifties.list_of_artists_.length * 0.01); //minimalna popularnosc potrzebna do zagrania koncertu

        for (int i = 0; i < Swifties.list_of_artists_.length; i++){
            if (Swifties.list_of_artists_[i].getBudget() >= min_budget_to_play_concert && Swifties.list_of_artists_[i].getPopularity() >= min_popularity_to_play_concert){
                min_budget_to_play_concert = Swifties.list_of_artists_[i].getBudget();
                min_popularity_to_play_concert = Swifties.list_of_artists_[i].getPopularity();
                artist_performing = i;
            }
        }
    }

    /**
     * Metoda, powtarzająca cykl walk, żeby wyłonić zwycięzcę oraz wypisująca obiekt artysty, który wygrał w
     */
    protected static void artists_fighting_more(){
        if (artist_performing == -1){
            while (artist_performing == -1){
                //Fight.comparison();
                artist_plays_concert();
            }
        }

        System.out.println(Swifties.list_of_artists_[artist_performing].getPseudonym() + " gra na festynie w miescie!!!");
        System.out.println("Budzet artysty wynosi " + Swifties.list_of_artists_[artist_performing].getBudget() + "$, a popularnosc wsrod mieszkancow miasta - "
                + Swifties.list_of_artists_[artist_performing].getPopularity()*100/100.0*100 + "%");
    }
}
