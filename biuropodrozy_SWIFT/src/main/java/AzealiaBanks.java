import java.util.Random;

public class AzealiaBanks extends Artist {
    public final double AB_chance = 0.0075; //szansa na pojawienie sie agenta Azealia Banks
    public static double ab_popularity_coeff = Artist.popularity_coefficient - (Swifties.list_of_artists_.length * 0.01);
    public static double ab_budget_coeff = Artist.budget_coefficient - (Swifties.list_of_artists_.length * 0.014);

    public AzealiaBanks(String pseudonym, double budget, double popularity){
        super(pseudonym, budget, popularity);
        this.pseudonym = "Azealia Banks";
        this.budget = budget*0.7;
        this.popularity = popularity;
    }

    public static boolean azealias_in_play(){
        Random rand = new Random();
        int random_number = rand.nextInt(5);
        //System.out.println(random_number);
        if (random_number==2){
            return true;
        } else{
            return false;
        }
    }
}
