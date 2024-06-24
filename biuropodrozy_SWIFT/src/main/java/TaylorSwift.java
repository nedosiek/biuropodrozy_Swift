/**
 * Klasa, odpowiadająca za artystę Taylor Swift, dziedzicząca po klasie Artist
 */
public class TaylorSwift extends Artist {
    /**
     * Zmienna, opisująca szansę na pojawienie się artysty Taylor Swift
     */
    private static final double TS_chance = Swifties.number_of_artists_/100.0;
    /**
     * Zmienna, odpowiadająca za ilość przegranych z rzędu walk Taylor Swift
     */
    protected static int TS_losses = 0;
    /**
     * Zmienna, odpowiadająca za ilość tur, spędzonych przez Taylor Swift w odrzutowcu
     */
    protected static int TS_in_jet = 0;

    /**
     * Konstruktor artysty Taylor Swift
     * @param budget budżet artysty
     * @param popularity popularność artysty
     */
    public TaylorSwift(double budget, double popularity){
        this.pseudonym = "Taylor Swift";
        this.budget = budget*2.5;
        this.popularity = popularity*2;
    }

    /**
     * Metoda, odpowiadająca za sprawdzenie, czy Taylor Swift pojawi się w projekcie
     * @return true/false w zależności, czy szansa na pojawienię się Taylor Swift się spełniła
     */
    protected static boolean taylors_in_play(){
        return Math.random() <= TS_chance;
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości budżetu oraz popularności artystów w przypadku wygranej Taylor Swift
     * @param loser obiekt artysty, który przegrał w walce z Taylor Swift
     */
    @Override
    public void updateStatsAfterWin(Artist loser) {
        TS_losses = 0;
        if (loser instanceof Amateur) {
            this.budget += loser.getBudget() * (budget_coefficient - Fight.ts_budget_coeff) * (Amateur.amateurs_streak+1);
            loser.budget -= loser.getBudget() * (budget_coefficient - Fight.ts_budget_coeff) * (Amateur.amateurs_streak+1);
            this.popularity += loser.getPopularity() * (popularity_coefficient - Fight.ts_popularity_coeff) * (Amateur.amateurs_streak+1);
            loser.popularity -= loser.getPopularity() * (popularity_coefficient - Fight.ts_popularity_coeff) * (Amateur.amateurs_streak+1);
        } else {
            this.budget += loser.getBudget() * (budget_coefficient - Fight.ts_budget_coeff);
            loser.budget -= loser.getBudget() * (budget_coefficient - Fight.ts_budget_coeff);
            this.popularity += loser.getPopularity() * (popularity_coefficient - Fight.ts_popularity_coeff);
            loser.popularity -= loser.getPopularity() * (popularity_coefficient - Fight.ts_popularity_coeff);
        }
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości budżetu oraz popularności artystów w przypadku przegranej Taylor Swift
     * @param winner obiekt artyst, który wygrał w walce z Taylor Swift
     */
    @Override
    public void updateStatsAfterLoss(Artist winner) {
        TS_losses++;
        winner.budget += this.budget * (budget_coefficient + Fight.ts_budget_coeff);
        this.budget -= this.budget * (budget_coefficient + Fight.ts_budget_coeff);
        winner.popularity += this.popularity * (popularity_coefficient + Fight.ts_popularity_coeff);
        this.popularity -= this.popularity * (popularity_coefficient + Fight.ts_popularity_coeff);
        TaylorSwift.taylor_is_in_jet();
    }

    /**
     * Metoda, odpowiadająca za przypisanie wartościom Taylor Swift części wartości innych artystów w przypadku znalezienia się danego artysty w odrzutowcu
     * @return
     */
    protected static boolean taylor_is_in_jet(){
        if (TS_losses==2){
            TS_losses = 0;
            int min_artist = 0;
            int max_artist = 0;
            for (int i = 0; i < Swifties.list_of_artists_.length; i++){
                //zapisywanie artysty o najmniejszym i największym budzecie
                if (Swifties.list_of_artists_[i].budget < Swifties.list_of_artists_[min_artist].budget){
                    min_artist = i;
                } else if (Swifties.list_of_artists_[i].budget > Swifties.list_of_artists_[max_artist].budget) {
                    max_artist = i;
                }
            }
            for (int i = 0; i < Swifties.list_of_artists_.length; i++){
                //przypisywanie budzetu i popularnosci taylor na podstawie zapisanych artystów
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
