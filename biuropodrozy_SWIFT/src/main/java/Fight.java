import java.util.Random;
import java.util.Arrays;

/**
 * Klasa, odpowiadająca za walkę artystów między sobą
 */
public abstract class Fight {
    /**
     * Zmienna, odpowiadająca za ilość MeetAndreet w danej rundzie
     */
    private static int meet_n_greet_counter = 0;
    /**
     * Tablica, który odpowiada za zagrane walki
     */
    private final static int[][] fight_pairs_table = new int[Swifties.list_of_artists_.length][Swifties.list_of_artists_.length];
    /**
     * Zmienna dla losowania róznych liczb w danej klasie
     */
    private final static Random random = new Random();

    /**
     * Zmienna, odpowiadająca za współczynnik, który ma wpływ na strate/zdobycie popularności przez Taylor Swift
     */
    protected static double ts_popularity_coeff = Swifties.list_of_artists_.length * 0.005;
    /**
     * Zmienna, odpowiadająca za współczynnik, który ma wpływ na strate/zdobycie budżetu przez Taylor Swift
     */
    protected static double ts_budget_coeff = Swifties.list_of_artists_.length * 0.01;
    /**
     * Zmienna, odpowiadająca za współczynnik, który ma wpływ na strate/zdobycie popularności przez Azealia Banks
     */
    protected static double ab_popularity_coeff = Artist.popularity_coefficient - (Swifties.list_of_artists_.length * 0.01);
    /**
     * Zmienna, odpowiadająca za współczynnik, który ma wpływ na strate/zdobycie budżetu przez Azealia Banks
     */
    protected static double ab_budget_coeff = Artist.budget_coefficient - (Swifties.list_of_artists_.length * 0.014);
    /**
     * Zmienna, odpowiadająca za współczynnik, który ma wpływ na strate/zdobycie popularności przez Amateur'a
     */
    protected static double amateur_popularity_coeff = Swifties.list_of_artists_.length * 0.05 + (Amateur.amateurs_streak * 0.1);
    /**
     * Zmienna, odpowiadająca za współczynnik, który ma wpływ na strate/zdobycie budżetu przez Amateur'a
     */
    protected static double amateur_budget_coeff = Swifties.list_of_artists_.length * 0.07 + (Amateur.amateurs_streak * 0.1);

    /**
     * Pusty konstruktor walki
     */
    private Fight(){
    }

    /**
     * Metoda, odpowiadająca za losowanie, którzy artyści grają ze sobą
     * @return zwraca listę obiektów artystów
     */
    public static Artist[] comparison() {
        initialize_fight_pairs_table();

        for (int m = 0; m < fight_pairs_table.length; m++) {
            for (int n = 0; n < fight_pairs_table[m].length; n++) {
                int i = random.nextInt(fight_pairs_table.length);
                int j = random.nextInt(fight_pairs_table[m].length);

                handle_fight(i, j, meet_n_greet_counter);

                boolean artists_with_no_fight_found = Arrays.stream(fight_pairs_table)
                        .flatMapToInt(Arrays::stream).anyMatch(e -> e == 0);
                if (artists_with_no_fight_found && i == fight_pairs_table.length - 1) {
                    n = 0;
                    m = 0;
                }
            }
        }
        return Swifties.list_of_artists_;
    }


    /**
     * Metoda, odpowiadająca za wypełnianie tabeli zagranych walk
     */
    private static void initialize_fight_pairs_table() {
        for (int[] ints : fight_pairs_table) {
            Arrays.fill(ints, 0); //-1 czyli nie moga ze soba zagrac; 0 - nie grali; 1 - już zagrali
        }

        for (int i = 0; i < fight_pairs_table.length; i++) {
            for (int j = 0; j < i+1; j++) {
                fight_pairs_table[i][j] = -1;
            }
        }
    }

    /**
     * Metoda, odpowiadająca za prowadzenie walk
     * @param i - indeks pierwszego artysty
     * @param j - indeks drugiego artysty
     * @param meet_n_greet_counter - ilość MeetAndGreet'ów w rundzie
     */
    private static void handle_fight(int i, int j, int meet_n_greet_counter) {
        if (Swifties.list_of_artists_[j].getPseudonym().equals("Taylor Swift") ||
        Swifties.list_of_artists_[i].getPseudonym().equals("Taylor Swift")) {
            handle_TaylorSwift_jet();
        }

        if (fight_pairs_table[i][j] == 0) {
            if (MeetNGreeet.meet_and_greet_chance() && meet_n_greet_counter == 0){
                handle_meetNGreet(j);
            } else {
                handle_regular_fight(i, j);
            }
            round_stats(i);
            round_stats(j);
            fight_pairs_table[i][j] = 1;
        }
    }

    /**
     * Metoda, odpowiadająca za sprawdzenie, czy Taylor Swift jest w odrzutowcu
     */
    private static void handle_TaylorSwift_jet() {
        if (TaylorSwift.TS_in_jet < 2){
            TaylorSwift.TS_in_jet++;
        } else {
            TaylorSwift.TS_in_jet = 0;
            TaylorSwift.TS_losses = 0;
        }
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości artysty, który bierze udział w wydarzeniu MeetAndGreet
     * @param j - indeks danego artysty
     */
    private static void handle_meetNGreet(int j) {
        Swifties.list_of_artists_[j].setBudget(Swifties.list_of_artists_[j].getBudget() * 0.95);
        Swifties.list_of_artists_[j].setPopularity(Swifties.list_of_artists_[j].getPopularity() * 1.5);
        meet_n_greet_counter++;
        round_stats(j);
        System.out.format("%-17s | " + "              Meet&Greet                | " + "%30.2f" + "$ |" + "%6.2f" + "%% |       \n",
                Swifties.list_of_artists_[j].getPseudonym(), Swifties.list_of_artists_[j].getBudget(), Swifties.list_of_artists_[j].getPopularity());
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości po walce
     * @param i - indeks pierwszego artysty
     * @param j - indeks drugiego artysty
     */
    private static void handle_regular_fight(int i, int j) {
        double first_artist = (Swifties.list_of_artists_[i].getBudget() * 0.02) + (Swifties.list_of_artists_[i].getPopularity() * 1250);
        double second_artist = (Swifties.list_of_artists_[j].getBudget() * 0.02) + (Swifties.list_of_artists_[j].getPopularity() * 1250);

        if (first_artist > second_artist) {
            handle_win(i, j);
            round_stats(i);
            round_stats(j);
            print_fight_visual(i, j);
        } else if (first_artist < second_artist) {
            handle_win(j, i);
            round_stats(i);
            round_stats(j);
            print_fight_visual(j, i);
        } else {
            handle_tie(i, j);
        }
    }


    /**
     * Metoda, odpowiadająca za wybranie artysty, który wygrał w walce
     * @param winner_index - indeks artysty, który wygrał
     * @param loser_index - indeks artysty, który przegrał
     */
    private static void handle_win (int winner_index, int loser_index){
        Artist winner = Swifties.list_of_artists_[winner_index];
        Artist loser = Swifties.list_of_artists_[loser_index];
        if (!(loser instanceof AzealiaBanks)){
            winner.updateStatsAfterWin(loser);
            loser.updateStatsAfterLoss(winner);
        }

    }

    /**
     * Metoda, odpowiadająca za zmianę wartości artystów w przypadku remisu
     * @param i - indeks pierwszego artysty
     * @param j - indeks drugiego artysty
     */
    private static void handle_tie(int i, int j){
        boolean winner = RPS_fight();
        if (!winner) {
            stats_updating(i, j, Artist.budget_coefficient * 2, Artist.popularity_coefficient * 2);
        } else {
            stats_updating(j, i, Artist.budget_coefficient * 2, Artist.popularity_coefficient * 2);
        }
    }

    /**
     * Metoda, odpowiadająca za zagranie z Papier, kamień, nożyce
     * @return true/false, w zależności który artysta wygrał
     */
    private static boolean RPS_fight(){
        int[] RPS_sign = new int[2]; // 0 - papier, 1 - nozyce, 2 - kamien
        int[] RPS_artists_counter = new int[2];

        while (RPS_artists_counter[0] < 3 && RPS_artists_counter[1] < 3) {
            RPS_sign[0] = random.nextInt(3);
            RPS_sign[1] = random.nextInt(3);

            if ((RPS_sign[0] == 0 && RPS_sign[1] == 1) || (RPS_sign[0] == 1 && RPS_sign[1] == 2) || (RPS_sign[0] == 2 && RPS_sign[1] == 0)) {
                RPS_artists_counter[1]++;
            } else if ((RPS_sign[0] == 1 && RPS_sign[1] == 0) || (RPS_sign[0] == 2 && RPS_sign[1] == 1) || (RPS_sign[0] == 0 && RPS_sign[1] == 2)){
                RPS_artists_counter[0]++;
            }
        }
        return RPS_artists_counter[0] < RPS_artists_counter[1];
    }

    /**
     * Metoda, odpowiadająca za przypisanie zmienionych wartości w przypadku ogólnym
     * @param winner_index - indeks wygranego artysty
     * @param loser_index - indeks przegranego artysty
     * @param budget_coeff - współczynnik straty/zdobycia budżetu
     * @param popularity_coeff - współczynnik straty/zdobycia popularności
     */
    private static void stats_updating(int winner_index, int loser_index, double budget_coeff, double popularity_coeff){
        Swifties.list_of_artists_[winner_index].setBudget(Swifties.list_of_artists_[winner_index].getBudget() + Swifties.list_of_artists_[loser_index].getBudget() * budget_coeff);
        Swifties.list_of_artists_[loser_index].setBudget(Swifties.list_of_artists_[loser_index].getBudget() - Swifties.list_of_artists_[loser_index].getBudget() * budget_coeff);
        Swifties.list_of_artists_[winner_index].setPopularity(Swifties.list_of_artists_[winner_index].getPopularity() + Swifties.list_of_artists_[loser_index].getPopularity() * popularity_coeff);
        Swifties.list_of_artists_[loser_index].setPopularity(Swifties.list_of_artists_[loser_index].getPopularity() - Swifties.list_of_artists_[loser_index].getPopularity() * popularity_coeff);
    }

    /**
     * Metoda, odpowiadająca za zaokrąglenie wartości oraz za usunięcie "niemożliwych" sytuacji
     * @param i - indeks artysty
     */
    private static void round_stats(int i){
        if (Swifties.list_of_artists_[i].getPopularity() > 1){
            Swifties.list_of_artists_[i].setPopularity(1);
        }

        Swifties.list_of_artists_[i].setPopularity(Math.round(Swifties.list_of_artists_[i].getPopularity() * 100) / 100.0);
        Swifties.list_of_artists_[i].setBudget(Math.round(Swifties.list_of_artists_[i].getBudget() * 100) / 100.0);

        if(Swifties.list_of_artists_[i].getBudget() < 0) {
            Swifties.list_of_artists_[i].setBudget(Math.round(random.nextDouble() * (8500 - 7000) + 7000));
        }
        if(Swifties.list_of_artists_[i].getPopularity() < 0) {
            Swifties.list_of_artists_[i].setPopularity(Math.round((random.nextDouble() * (0.25 - 0.2) + 0.2) * 100) / 100.0);
        }

    }

    /**
     * Metoda, odpowiadająca za wypisywanie stanu walk
     * @param i - indeks pierwszego artysty
     * @param j - indeks drugiego artysty
     */
    private static void print_fight_visual(int i, int j){
        System.out.format("%-17s | " + "%10.2f" + "$ |" + "%6.2f" + "%% | ", Swifties.list_of_artists_[i].getPseudonym(),
                Swifties.list_of_artists_[i].getBudget(), Swifties.list_of_artists_[i].getPopularity());
        System.out.print(" Wygral walke z  | ");
        System.out.format("%-17s | " + "%10.2f" + "$ |" + "%6.2f" + "%% | \n", Swifties.list_of_artists_[j].getPseudonym(),
                Swifties.list_of_artists_[j].getBudget(), Swifties.list_of_artists_[j].getPopularity());
    }
}
