import java.util.Random;
import java.util.Arrays;

public abstract class Fight {

    public static void stats_updating(int i, int j, double first_coeff, double second_coeff){
        Swifties.list_of_artists_[i].budget += Swifties.list_of_artists_[j].budget * first_coeff;
        Swifties.list_of_artists_[j].budget -= Swifties.list_of_artists_[j].budget * first_coeff;
        Swifties.list_of_artists_[i].popularity += Swifties.list_of_artists_[j].popularity * second_coeff;
        Swifties.list_of_artists_[j].popularity -= Swifties.list_of_artists_[j].popularity * second_coeff;
    }

    public static void round_stats(int i){
        if (Swifties.list_of_artists_[i].popularity > 1){
            Swifties.list_of_artists_[i].popularity = 1;
        }
        Swifties.list_of_artists_[i].popularity = Math.round(Swifties.list_of_artists_[i].popularity*100)/100.0;
        Swifties.list_of_artists_[i].budget = Math.round(Swifties.list_of_artists_[i].budget*100)/100.0;

        Random random_negative = new Random();
        if(Swifties.list_of_artists_[i].budget < 0) {
            Swifties.list_of_artists_[i].budget = Math.round(random_negative.nextDouble() * (8500 - 7000) + 7000);
        }
        if(Swifties.list_of_artists_[i].popularity < 0) {
            Swifties.list_of_artists_[i].popularity = Math.round((random_negative.nextDouble() * (0.25 - 0.2) + 0.2) * 100) / 100.0;
        }

    }

    public static Artist[] comparison() {
        //wspolczynniki dla artystow specjalnych
        double ts_popularity_coeff = Swifties.list_of_artists_.length * 0.005;
        double ts_budget_coeff = Swifties.list_of_artists_.length * 0.01;
        double ab_popularity_coeff = Artist.popularity_coefficient - (Swifties.list_of_artists_.length * 0.01);
        double ab_budget_coeff = Artist.budget_coefficient - (Swifties.list_of_artists_.length * 0.014);
        double amateur_popularity_coeff = Swifties.list_of_artists_.length * 0.05 + (Amateur.amateurs_streak * 0.1);
        double amateur_budget_coeff = Swifties.list_of_artists_.length * 0.07 + (Amateur.amateurs_streak * 0.1);
        int meet_n_greet_counter = 0;
        double first_artist; //artysta 1. w walce
        double second_artist; //artysta 2. w walce
        //tabele do walki remisowej (RPS)
        int[] RPS_sign = new int[2]; // to co artysci pokazuja podczas RPS
        int[] RPS_artist_counter = new int[2]; // ilosc wygranych przez artystow w RPS


        for (int m = 0; m < SplitIntoPairs.fight_pairs_table.length; m++) {
            for (int n = 0; n < SplitIntoPairs.fight_pairs_table[m].length; n++) {
                Random rand = new Random();
                int i = rand.nextInt(SplitIntoPairs.fight_pairs_table.length);
                int j = rand.nextInt(SplitIntoPairs.fight_pairs_table[m].length);
                //i = i_artist;
                //j = j_artist;
                if (TaylorSwift.TS_in_jet < 2 && (Swifties.list_of_artists_[j].pseudonym.equals("Taylor Swift") ||
                        Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) && (TaylorSwift.taylor_is_in_jet())) {
                    TaylorSwift.TS_in_jet++;
                    continue;
                } else if (TaylorSwift.TS_in_jet >= 2){
                    TaylorSwift.TS_in_jet = 0;
                    TaylorSwift.TS_losses = 0;
                }

                if (SplitIntoPairs.fight_pairs_table[i][j] == 0) {
                    if (MeetNGreeet.meet_and_greet_chance() && meet_n_greet_counter == 0) {
                        Swifties.list_of_artists_[j].budget *= 0.95;
                        Swifties.list_of_artists_[j].popularity *= 1.5;
                        meet_n_greet_counter++;
                        Swifties.list_of_artists_[j].popularity = Math.round(Swifties.list_of_artists_[j].popularity*100)/100.0;
                        Swifties.list_of_artists_[j].budget = Math.round(Swifties.list_of_artists_[j].budget*100)/100.0;
                        //wypisywanie danych na konsole - wizualizacja Meet&Greetow
                        System.out.format("%-17s | " + "|              Meet&Greet              | "+ "%9.2f" + "$ |" +"%6.2f" + "%% | ", Swifties.list_of_artists_[i].pseudonym, Swifties.list_of_artists_[i].budget,
                                Swifties.list_of_artists_[i].popularity);
                        continue;
                    }
                    //przypisywanie przelicznika walki dla obu artystow w walce
                    first_artist = (Swifties.list_of_artists_[i].budget * 0.02) + (Swifties.list_of_artists_[i].popularity * 1250);
                    second_artist = (Swifties.list_of_artists_[j].budget * 0.02) + (Swifties.list_of_artists_[j].popularity * 1250);

                    //MECHANIZM WALKI
                    if (first_artist > second_artist) {//jezeli pierwszy artysta wygra
                        if (Swifties.list_of_artists_[j].pseudonym.equals("Taylor Swift")) { //"taylor swift" przegrala
                            TaylorSwift.TS_losses++;//inkrementacja licznika przegranych
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks")) {//"azealia banks" wygrala
                                stats_updating(i, j, ab_budget_coeff, ab_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) { //amator wygral
                                Amateur.amateurs_streak++;//inkrementacja "passy" amatora
                                stats_updating(i, j, ts_budget_coeff + Artist.budget_coefficient, ts_popularity_coeff + Artist.popularity_coefficient);
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                //zwykly artysta wygral
                                stats_updating(i, j, ts_budget_coeff + Artist.budget_coefficient, ts_popularity_coeff + Artist.popularity_coefficient);
                            }
                            TaylorSwift.taylor_is_in_jet();//mechanizm private jet dla taylor swift

                        } else if (Swifties.list_of_artists_[j].pseudonym.equals("Amateur")) { //Amateur przegrywa
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) {//taylor swift wygrala
                                stats_updating(i, j, (Artist.budget_coefficient - ts_budget_coeff) * Amateur.amateurs_streak, (Artist.popularity_coefficient - ts_popularity_coeff) * Amateur.amateurs_streak);
                                TaylorSwift.TS_losses=0; //reset licznika przegranych taylor swift
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks")) {//azealia wygrala
                                stats_updating(i, j, ab_budget_coeff * Amateur.amateurs_streak, ab_popularity_coeff * Amateur.amateurs_streak);
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                //zwykly artysta wygral
                                stats_updating(i, j, Artist.budget_coefficient * Amateur.amateurs_streak, Artist.popularity_coefficient * Amateur.amateurs_streak);
                            }
                            Amateur.amateurs_streak=0;//zerowanie passy amatora
                        } else if (!Swifties.list_of_artists_[j].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[j].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[j].pseudonym.equals("Amateur")){ //zwykly artysta przegrywa
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) {
                                TaylorSwift.TS_losses=0;
                                stats_updating(i, j, Artist.budget_coefficient - ts_budget_coeff, Artist.popularity_coefficient - ts_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks")) {
                                stats_updating(i, j, ab_budget_coeff, ab_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                Amateur.amateurs_streak++;
                                stats_updating(i, j, Artist.budget_coefficient + amateur_budget_coeff, amateur_popularity_coeff + Artist.popularity_coefficient);
                            } else
                                stats_updating(i, j, Artist.budget_coefficient, Artist.popularity_coefficient); //inny zwykly artysta wygral
                        }
                        System.out.println();


                    } else if (first_artist < second_artist) { //drugi artysta wygral
                        if (Swifties.list_of_artists_[j].pseudonym.equals("Taylor Swift")) { // TS wygrywa
                            TaylorSwift.TS_losses = 0;
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) { //amator przegral
                                stats_updating(j, i, (Artist.budget_coefficient - ts_budget_coeff) * Amateur.amateurs_streak, (Artist.popularity_coefficient - ts_popularity_coeff) * Amateur.amateurs_streak);
                                Amateur.amateurs_streak=0;
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(j, i, Artist.budget_coefficient - ts_budget_coeff, ts_popularity_coeff + Artist.popularity_coefficient);
                            }

                        } else if (Swifties.list_of_artists_[j].pseudonym.equals("Amateur")) { //Amateur wygrywa
                            Amateur.amateurs_streak++;
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) {
                                TaylorSwift.TS_losses++;
                                stats_updating(j, i, Artist.budget_coefficient + ts_budget_coeff, Artist.popularity_coefficient + ts_popularity_coeff);
                                TaylorSwift.taylor_is_in_jet();
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(j, i, Artist.budget_coefficient + amateur_budget_coeff, Artist.popularity_coefficient + amateur_popularity_coeff);
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(j, i, Artist.budget_coefficient, Artist.popularity_coefficient);
                            }
                        } else if (Swifties.list_of_artists_[j].pseudonym.equals("Azealia Banks")) { //Azelia Banks wygrywa
                            stats_updating(j, i, ab_budget_coeff, ab_popularity_coeff);
                        } else { //zwykly artysta wygrywa
                            if (Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift")) {
                                TaylorSwift.TS_losses++;
                                stats_updating(j, i, Artist.budget_coefficient + ts_budget_coeff, Artist.popularity_coefficient + ts_popularity_coeff);
                                TaylorSwift.taylor_is_in_jet();
                            } else if (Swifties.list_of_artists_[i].pseudonym.equals("Amateur")) {
                                stats_updating(j, i, Artist.budget_coefficient * Amateur.amateurs_streak, Artist.popularity_coefficient * Amateur.amateurs_streak);
                            } else if (!Swifties.list_of_artists_[i].pseudonym.equals("Taylor Swift") && !Swifties.list_of_artists_[i].pseudonym.equals("Azealia Banks") && !Swifties.list_of_artists_[i].pseudonym.equals("Amateur"))
                                stats_updating(j, i, Artist.budget_coefficient, Artist.popularity_coefficient); //inny zwykly artysta przegral
                        }
                    } else {
                        while (RPS_artist_counter[0] + RPS_artist_counter[1] < 3) { //mechanizm RPS - walka w przypadku remisu
                            Random random_sign = new Random();
                            RPS_sign[0] = random_sign.nextInt(3); // 0 - papier, 1 - nozyce, 2 - kamien
                            RPS_sign[1] = random_sign.nextInt(3);
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
                            stats_updating(i, j, Artist.budget_coefficient * 2, Artist.popularity_coefficient * 2);
                        } else {
                            stats_updating(j, i, Artist.budget_coefficient * 2, Artist.popularity_coefficient * 2);
                        }
                    }
                    round_stats(i); //zaokraglanie statystyk do dwoch miejsc po przecinku
                    round_stats(j);
                    SplitIntoPairs.fight_pairs_table[i][j] = 1; //zapisywanie, ze dana para artystow walczyla ze soba

                    //wypisywanie danych na konsole - wizualizacja walk
                    System.out.format("%-17s" + " |" + "%9.2f" + "$ |" +"%6.2f" + "%% | ", Swifties.list_of_artists_[i].pseudonym, Swifties.list_of_artists_[i].budget,
                            Swifties.list_of_artists_[i].popularity);
                    if(first_artist>second_artist) System.out.print(" Wygral walke z  | "); else
                        if (first_artist<second_artist) System.out.print("Przegral walke z | "); else
                            if (RPS_artist_counter[0] > RPS_artist_counter[1]) System.out.print(" Wygral walke z  | "); else
                                System.out.print("Przegral walke z | ");
                    System.out.format("%-17s" + " |" + "%9.2f" + "$ |" +"%6.2f" + "%% | ", Swifties.list_of_artists_[j].pseudonym, Swifties.list_of_artists_[j].budget,
                            Swifties.list_of_artists_[j].popularity);
                    System.out.println();
                }

                boolean artists_with_no_fight_found = Arrays.stream(SplitIntoPairs.fight_pairs_table)
                        .flatMapToInt(Arrays::stream).anyMatch(e -> e == 0);
                if (artists_with_no_fight_found && i == SplitIntoPairs.fight_pairs_table.length - 1) {
                    n = 0;
                    m = 0;
                }

            }

        }
        return Swifties.list_of_artists_;
    }
}
