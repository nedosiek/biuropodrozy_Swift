import java.util.Random;

public class TaylorSwift extends Artist {
    public final double TS_chance = 0.01; //szansa na pojawienie sie agenta TS
    public static int TS_losses = 0; //licznik przegranych walk TS

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

}
