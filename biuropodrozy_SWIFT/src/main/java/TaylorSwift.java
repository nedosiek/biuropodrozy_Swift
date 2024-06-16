import java.util.Random;

public class TaylorSwift extends Artist {
    public final double TS_chance = 0.01; //szansa na pojawienie sie agenta TS
    public static int TS_losses = 0; //licznik przegranych walk TS
    public static int TS_in_jet = 0;

    // nie są to zmienne ostateczne. zmieniamy je w zaleznosci od wygrania/przegrania Artystki (+/-)
    public static double ts_popularity_coeff = Swifties.list_of_artists_.length * 0.005;
    public static double ts_budget_coeff = Swifties.list_of_artists_.length * 0.014;


    public TaylorSwift(String pseudonym, double budget, double popularity){
        super(pseudonym, budget, popularity);
        this.pseudonym = "Taylor Swift";
        this.budget = budget*2.5;
        this.popularity = popularity*2;
    }

    public static boolean taylors_in_play(){
        Random rand = new Random();
        int random_number = rand.nextInt(5);
        //System.out.println(random_number);
        return random_number == 2;
    }

    public static boolean taylor_is_in_jet(){
        if (TS_losses==2){
            TS_losses = 0;
            int min_artist = 0;
            int max_artist = 0;
            for (int i = 0; i < Swifties.list_of_artists_.length; i++){
                if (Swifties.list_of_artists_[i].budget < Swifties.list_of_artists_[min_artist].budget){
                    min_artist = i;
                } else if (Swifties.list_of_artists_[i].budget > Swifties.list_of_artists_[max_artist].budget) {
                    max_artist = i;
                }
            }
            for (int i = 0; i < Swifties.list_of_artists_.length; i++){
                if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")){
                    Swifties.list_of_artists_[i].budget = 0.5*Swifties.list_of_artists_[min_artist].budget
                    + 0.6*Swifties.list_of_artists_[max_artist].budget;
                    Swifties.list_of_artists_[i].popularity = 0.6*Swifties.list_of_artists_[min_artist].popularity
                    + 0.7*Swifties.list_of_artists_[max_artist].popularity;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
