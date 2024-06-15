public abstract class Fight {

    public static void stats_updating(int i, int j, double first_coeff, double second_coeff){
        Swifties.list_of_artists_[i].budget += Swifties.list_of_artists_[j].budget * (1 + first_coeff);
        Swifties.list_of_artists_[j].budget += Swifties.list_of_artists_[j].budget * (1 - first_coeff);
        Swifties.list_of_artists_[i].popularity += Swifties.list_of_artists_[j].popularity * (1 + second_coeff);
        Swifties.list_of_artists_[j].popularity += Swifties.list_of_artists_[j].popularity * (1 - second_coeff);
    }

    public static Artist[] comparison() {
        SplitIntoPairs.distance();
        int meet_n_greet_counter = 0;
        double first_artist; //artysta 1. w walce
        double second_artist; //artysta 2. w walce

        for (int i = 0; i < SplitIntoPairs.fight_pairs_table.length; i++) {
            for (int j = 0; j < SplitIntoPairs.fight_pairs_table[i].length; j++) {
                if (SplitIntoPairs.fight_pairs_table[i][j] == 0) {
                    if (MeetNGreeet.meet_and_greet_chance() && meet_n_greet_counter == 0) {
                        Swifties.list_of_artists_[j].budget *= 0.95;
                        Swifties.list_of_artists_[j].popularity *= 1.5;
                        meet_n_greet_counter++;
                        j++;
                    }
                    first_artist = (Swifties.list_of_artists_[i].budget * 0.85) + (Swifties.list_of_artists_[i].popularity * 1.2);
                    second_artist = (Swifties.list_of_artists_[j].budget * 0.85) + (Swifties.list_of_artists_[j].popularity * 1.2);
                    if (first_artist > second_artist) {
                        //stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient);
                        if (Swifties.list_of_artists_[j].pseudonym == "Taylor Swift") {
                            TaylorSwift.TS_losses++;
                            if (Swifties.list_of_artists_[i].pseudonym == "Azealia Banks") {
                                stats_updating(i, j, AzealiaBanks.ab_budget_coeff, AzealiaBanks.ab_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym == "Amateur") {
                                stats_updating(i, j, Amateur.amateur_budget_coeff, Amateur.amateur_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym != "Taylor Swift" && Swifties.list_of_artists_[i].pseudonym != "Azealia Banks" && Swifties.list_of_artists_[i].pseudonym != "Amateur") {
                                stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient);
                            }
                        } else if (Swifties.list_of_artists_[j].pseudonym == "Amateur") {
                            if (Swifties.list_of_artists_[i].pseudonym == "Taylor Swift") {

                            } else if (Swifties.list_of_artists_[i].pseudonym == "Azealia Banks") {
                                stats_updating(i, j, AzealiaBanks.ab_budget_coeff, AzealiaBanks.ab_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym != "Taylor Swift" && Swifties.list_of_artists_[i].pseudonym != "Azealia Banks" && Swifties.list_of_artists_[i].pseudonym != "Amateur") {
                                stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient);
                            } else if (Swifties.list_of_artists_[i].pseudonym == "Amateur") {
                                stats_updating(i, j, Amateur.amateur_budget_coeff, Amateur.amateur_popularity_coeff);
                            }
                        } else { //zwykly artysta przegral
                            if (Swifties.list_of_artists_[j].pseudonym == "Taylor Swift") {

                            } else if (Swifties.list_of_artists_[i].pseudonym == "Azealia Banks") {
                                stats_updating(i, j, AzealiaBanks.ab_budget_coeff, AzealiaBanks.ab_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym == "Amateur") {
                                stats_updating(i, j, Amateur.amateur_budget_coeff, Amateur.amateur_popularity_coeff);
                            } else
                                stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient); //zwykly artysta wygral
                        }
                    }
                }
            }


        /*for (int i = 0; i < SplitIntoPairs.fight_pairs.length; i++) {
            if (SplitIntoPairs.fight_pairs[i] != -1){
                double first_artist = (Swifties.list_of_artists_[i].budget * 0.85) + (Swifties.list_of_artists_[i].popularity * 1.2);
                double second_artist = (Swifties.list_of_artists_[SplitIntoPairs.fight_pairs[i]].budget * 0.85) + (Swifties.list_of_artists_[SplitIntoPairs.fight_pairs[i]].popularity * 1.2);
                if (first_artist > second_artist) {
                    Swifties.list_of_artists_[i].budget += Swifties.list_of_artists_[SplitIntoPairs.fight_pairs[i]].budget * Artist.budget_coefficient;
                    Swifties.list_of_artists_[SplitIntoPairs.fight_pairs[i]].budget *= (1 - Artist.budget_coefficient);
                } else {
                    Swifties.list_of_artists_[i].budget *= (1 - Artist.budget_coefficient);
                    Swifties.list_of_artists_[SplitIntoPairs.fight_pairs[i]].budget += Swifties.list_of_artists_[i].budget * Artist.budget_coefficient;
                }
            }
        }*/


            for (int i = 0; i < Swifties.list_of_artists_.length; i++) {
                System.out.println(Swifties.list_of_artists_[i].budget);
            }


            return Swifties.list_of_artists_;
        }}
