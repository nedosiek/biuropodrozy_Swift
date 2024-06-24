import java.util.Random;
import java.lang.Math;

/**
 * Klasa, odpowiadająca za stworzenie wydarzenia MeetAndGreet
 */
public class MeetNGreeet {
    /**
     * Zmienna, opisująca szansę na pojawienie się MeetAndGreet w rundzie
     */
    static final double MNG_chance = 10;

    /**
     * Metoda, odpowiadająca za sprawdzenie, czy MeetAndGreet pojawi się w rundzie
     * @return true/false w zależności, czy szansa na pojawienię się MeetAndGreet się spełniła
     */
    public static boolean meet_and_greet_chance(){
        Random rand = new Random();
        int random_number = rand.nextInt(100);
        return random_number < MNG_chance;
    }
}
