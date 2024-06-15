import java.util.Random;
import java.lang.Math;

public class MeetNGreeet {
    static final double MNG_chance = 0.1;

    public static boolean meet_and_greet_chance(){
        Random rand = new Random();
        int random_number = rand.nextInt(10);
        if (random_number == 5){
            return true;
        } else {
            return false;
        }
    }
}
