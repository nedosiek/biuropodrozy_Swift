import java.util.Random;

public class Amateur extends Artist {
    public static int amateurs_streak = 0; //passa amatora
    public static int amateur_chance = 20; // szansa na pojawienie sie amatora, [%]

    public Amateur(String pseudonym, double budget, double popularity){ //maska wprowadzania dla amatora
        super(pseudonym, budget, popularity);
        this.budget = budget*0.85;
        this.popularity = popularity*0.8;
        this.pseudonym = "Amateur";

    }

    public static boolean amateurs_in_play(int amateur_chance){ //szansa na pojawienie sie amatora
        Random rand = new Random();
        int random_number = rand.nextInt(100);
        return random_number < amateur_chance;
    }
}
