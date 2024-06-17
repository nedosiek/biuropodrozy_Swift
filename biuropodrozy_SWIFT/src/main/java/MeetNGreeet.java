import java.util.Random;
import java.lang.Math;

public class MeetNGreeet {
    static final double MNG_chance = 10; // szansa na wydarzenie Meet&Greet, [%]

    public static boolean meet_and_greet_chance(){
        Random rand = new Random();
        int random_number = rand.nextInt(100);
        return random_number < MNG_chance;
    }
}
