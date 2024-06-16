public class Concert {
    static int artist_who_plays_concert = -1;

    public static void artist_play_concert(){
        Fight.comparison();
        double min_budget_to_play_concert = 50000;
        double min_popularity_to_play_concert = 0.8;

        for (int i = 0; i < Swifties.list_of_artists_.length; i++){
            if (Swifties.list_of_artists_[i].budget > min_budget_to_play_concert &&
            Swifties.list_of_artists_[i].popularity > min_popularity_to_play_concert){
                min_budget_to_play_concert = Swifties.list_of_artists_[i].budget;
                min_popularity_to_play_concert = Swifties.list_of_artists_[i].popularity;
                artist_who_plays_concert = i;
            }
        }
    }

    public static void artists_fighting_more(){
        artist_play_concert();
        if (artist_who_plays_concert == -1){
            while (artist_who_plays_concert == -1){
                artist_play_concert();
            }
        }
        System.out.println(Swifties.list_of_artists_[artist_who_plays_concert].pseudonym +
                " gra na festynie w miescie!!!");
    }
}
