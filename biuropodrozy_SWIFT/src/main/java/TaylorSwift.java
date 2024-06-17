import java.util.Random;

public class TaylorSwift extends Artist {
    public static int TS_chance = Swifties.number_of_artists_; //szansa na pojawienie sie agenta TS
    public static int TS_losses = 0; //licznik przegranych walk TS
    public static int TS_in_jet = 0; //licznik run kiedy taylor jest w odrzutowcu

    public TaylorSwift(String pseudonym, double budget, double popularity){ //maska wprowadzania dla taylor swift
        super(pseudonym, budget, popularity);
        this.pseudonym = "Taylor Swift";
        this.budget = budget*2.5;
        this.popularity = popularity*2;
    }


    public static boolean taylors_in_play(){ //szansa na pojawienie sie taylor
        Random rand = new Random();
        int random_number = rand.nextInt(100);
        return random_number <= TS_chance;
    }

    public static boolean taylor_is_in_jet(){ //mechanizm TS w odrzutowcu
        if (TS_losses==2){
            TS_losses = 0;
            int min_artist = 0;
            int max_artist = 0;
            for (int i = 0; i < Swifties.list_of_artists_.length; i++){ //zapisywanie artysty o najmniejszym i najw. budzecie
                if (Swifties.list_of_artists_[i].budget < Swifties.list_of_artists_[min_artist].budget){
                    min_artist = i;
                } else if (Swifties.list_of_artists_[i].budget > Swifties.list_of_artists_[max_artist].budget) {
                    max_artist = i;
                }
            }
            for (int i = 0; i < Swifties.list_of_artists_.length; i++){ //przypisywanie budzetu i popularnosci taylor na podst.
                if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")){
                    Swifties.list_of_artists_[i].budget = 0.5*Swifties.list_of_artists_[min_artist].budget
                    + 0.4*Swifties.list_of_artists_[max_artist].budget;
                    Swifties.list_of_artists_[i].popularity = 0.5*Swifties.list_of_artists_[min_artist].popularity
                    + 0.4*Swifties.list_of_artists_[max_artist].popularity;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
