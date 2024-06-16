import java.util.Random;

public class Amateur extends Artist {
//    public static double amateur_popularity_coeff = Swifties.list_of_artists_.length * 0.05;
//    public static double amateur_budget_coeff = Swifties.list_of_artists_.length * 0.07;
    public static int amateurs_streak = 0;


    public Amateur(String pseudonym, double budget, double popularity){
        super(pseudonym, budget, popularity);
        this.budget = budget*0.65;
        this.popularity = popularity*0.8;
        this.pseudonym = "Amateur";

    }

    public static boolean amateurs_in_play(){
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
