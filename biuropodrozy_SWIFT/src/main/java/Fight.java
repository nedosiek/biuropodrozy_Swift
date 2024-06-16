import java.util.Random;

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
                        //j++;
                    }
                    first_artist = (Swifties.list_of_artists_[i].budget * 0.85) + (Swifties.list_of_artists_[i].popularity * 1.2);
                    second_artist = (Swifties.list_of_artists_[j].budget * 0.85) + (Swifties.list_of_artists_[j].popularity * 1.2);

                    if (first_artist > second_artist) {
                        if (Swifties.list_of_artists_[j].pseudonym.equals("Taylor Swift")) { // TS przegrywa
                            TaylorSwift.TS_losses++;
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks")) {
                                stats_updating(i, j, AzealiaBanks.ab_budget_coeff, AzealiaBanks.ab_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, TaylorSwift.ts_budget_coeff + Artist.budget_coefficient, TaylorSwift.ts_popularity_coeff + Artist.popularity_coefficient);
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, TaylorSwift.ts_budget_coeff + Artist.budget_coefficient, TaylorSwift.ts_popularity_coeff + Artist.popularity_coefficient);
                            }

                        } else if (Swifties.list_of_artists_[j].pseudonym.equals("Amateur")) { //Amateur przegrywa
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) {
                                stats_updating(i, j, Artist.budget_coefficient - TaylorSwift.ts_budget_coeff, Artist.popularity_coefficient - TaylorSwift.ts_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks")) {
                                stats_updating(i, j, AzealiaBanks.ab_budget_coeff, AzealiaBanks.ab_popularity_coeff);
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, Artist.budget_coefficient - Amateur.amateur_budget_coeff, Artist.popularity_coefficient - Amateur.amateur_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient);
                            }
                        } else { //zwykly artysta przegrywa
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) {
                                stats_updating(i, j, Artist.budget_coefficient - TaylorSwift.ts_budget_coeff, Artist.popularity_coefficient - TaylorSwift.ts_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks")) {
                                stats_updating(i, j, AzealiaBanks.ab_budget_coeff, AzealiaBanks.ab_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, Artist.budget_coefficient + Amateur.amateur_budget_coeff, Amateur.amateur_popularity_coeff + Artist.popularity_coefficient);
                            } else
                                stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient); //inny zwykly artysta wygral
                        }

                    } else if (first_artist < second_artist){
                        if (Swifties.list_of_artists_[j].pseudonym.equals("Taylor Swift")) { // TS wygrywa
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, Artist.budget_coefficient - TaylorSwift.ts_budget_coeff, Artist.popularity_coefficient - TaylorSwift.ts_popularity_coeff);
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, Artist.budget_coefficient - TaylorSwift.ts_budget_coeff, TaylorSwift.ts_popularity_coeff + Artist.popularity_coefficient);
                            }

                        } else if (Swifties.list_of_artists_[j].pseudonym.equals("Amateur")) { //Amateur wygrywa
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) {
                                TaylorSwift.TS_losses++;
                                stats_updating(i, j, Artist.budget_coefficient + TaylorSwift.ts_budget_coeff, Artist.popularity_coefficient + TaylorSwift.ts_popularity_coeff);
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, Artist.budget_coefficient + Amateur.amateur_budget_coeff, Artist.popularity_coefficient + Amateur.amateur_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient);
                            }
                        } else if (Swifties.list_of_artists_[j].pseudonym.equals("Azealia Banks")) { //Azelia Banks wygrywa
                            stats_updating(i, j, AzealiaBanks.ab_budget_coeff, AzealiaBanks.ab_popularity_coeff);
                        } else { //zwykly artysta wygrywa
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) {
                                TaylorSwift.TS_losses++;
                                stats_updating(i, j, Artist.budget_coefficient + TaylorSwift.ts_budget_coeff, Artist.popularity_coefficient + TaylorSwift.ts_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(i, j, Artist.budget_coefficient + Amateur.amateur_budget_coeff, Amateur.amateur_popularity_coeff + Artist.popularity_coefficient);
                            } else
                                stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient); //inny zwykly artysta przegral
                        }
                    } else {
                        int[] RPS_sign = new int[2]; // to co artysci pokazuja podczas gry
                        int[] RPS_artist_counter = new int[2]; // ilosc wygranych przez artystow
                        while (RPS_artist_counter[0]+RPS_artist_counter[1] < 3){
                            Random rand = new Random();
                            RPS_sign[0] = rand.nextInt(3); // 0 - papier, 1 - nozyce, 2 - kamien
                            RPS_sign[1] = rand.nextInt(3);
                            if ((RPS_sign[0] == 0 && RPS_sign[1] == 0) || (RPS_sign[0] == 1 && RPS_sign[1] == 1)
                                    || (RPS_sign[0] == 2 && RPS_sign[1] == 2)) {
                                continue;
                            } else if ((RPS_sign[0] == 0 && RPS_sign[1] == 1) || (RPS_sign[0] == 1 && RPS_sign[1] == 2)
                                || (RPS_sign[0] == 2 && RPS_sign[1] == 0)) {
                                RPS_artist_counter[1]++;
                            } else {
                                RPS_artist_counter[0]++;
                            }

                        }
                        if (RPS_artist_counter[0] > RPS_artist_counter[1]) {
                            stats_updating(i, j, Artist.budget_coefficient*2, Artist.popularity_coefficient*2);
                        } else {
                            stats_updating(j, i, Artist.budget_coefficient*2, Artist.popularity_coefficient*2);
                        }
                    }
                    SplitIntoPairs.fight_pairs_table[i][j] = 1;
                }
            }
        }
        return Swifties.list_of_artists_;
    }
}
