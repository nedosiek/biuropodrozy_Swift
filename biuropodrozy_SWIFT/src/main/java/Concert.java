public class Concert {
    static int artist_performing = -1; //wewnetrzne id artysty grajacego koncert

    public static void artist_plays_concert(){
        Fight.comparison();
        double min_budget_to_play_concert = 10500 + (Swifties.list_of_artists_.length * 400); //minimalny budzet potrzebny do zagrania koncertu
        double min_popularity_to_play_concert = 0.30 + (Swifties.list_of_artists_.length * 0.01); //minimalna popularnosc potrzebna do zagrania koncertu

        for (int i = 0; i < Swifties.list_of_artists_.length; i++){ //szukanie artysty z najwieksza popularnoscia i odpowiednim budzetem
            if (Swifties.list_of_artists_[i].budget >= min_budget_to_play_concert && Swifties.list_of_artists_[i].popularity >= min_popularity_to_play_concert){
                min_budget_to_play_concert = Swifties.list_of_artists_[i].budget;
                min_popularity_to_play_concert = Swifties.list_of_artists_[i].popularity;
                artist_performing = i;
            }
        }
    }

    public static String artists_fighting_more(){ //uruchamianie symulacji
        artist_plays_concert();
        if (artist_performing == -1){
            while (artist_performing == -1){
                artist_plays_concert();
            }
        }

        System.out.println(Swifties.list_of_artists_[artist_performing].pseudonym +
                " gra na festynie w miescie!!!"); //wypisanie kto wygral symulacje + jego dane
        System.out.println("Jego budzet wynosi " + Swifties.list_of_artists_[artist_performing].budget + ", a popularnosc - " + Swifties.list_of_artists_[artist_performing].popularity);
        return Swifties.list_of_artists_[artist_performing].pseudonym;
    }
}
