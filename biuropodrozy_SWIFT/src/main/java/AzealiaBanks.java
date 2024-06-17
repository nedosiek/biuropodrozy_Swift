import java.util.Random;

public class AzealiaBanks extends Artist {
    public static double AB_chance = 7; //szansa na pojawienie sie agenta Azealia Banks, [%]

    public AzealiaBanks(String pseudonym, double budget, double popularity){
        super(pseudonym, budget, popularity);
        this.pseudonym = "Azealia Banks";
        this.budget = budget*0.7;
        this.popularity = popularity;
    }

    public static boolean azealias_in_play(){
        Random rand = new Random();
        int random_number = rand.nextInt(100);
        return random_number < AB_chance;
    }
}
