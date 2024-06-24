/**
 * Klasa, odpowiadająca za artystę Amateur, dziedzicząca po klasie Artist
 */
public class Amateur extends Artist {
    /**
     * Zmienna, odpowiadająca za ilość wygranych z rzędu walk artysty Amateur
     */
    public static int amateurs_streak = 0;
    /**
     * Zmienna, opisująca szansę na pojawienie się artysty Amateur
     */
    private final static double amateur_chance = 0.2;

    /**
     * Konstruktor artysty Amateur
     * @param budget budżet artysty
     * @param popularity popularność artysty
     */
    public Amateur(double budget, double popularity){
        this.pseudonym = "Amateur";
        this.budget = budget*0.85;
        this.popularity = popularity*0.8;
    }

    /**
     * Metoda, odpowiadająca za sprawdzenie, czy Amateur pojawi się w projekcie
     * @return true/false w zależności, czy szansa na pojawienię się Amateur'a się spełniła
     */
    public static boolean amateurs_in_play(){
        return Math.random() <= amateur_chance;
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości budżetu oraz popularności artystów w przypadku wygranej Amateur'a
     * @param loser obiekt artysty, który przegrał w walce z Amateur'em
     */
    @Override
    public void updateStatsAfterWin(Artist loser) {
        amateurs_streak++;
        this.budget += loser.getBudget() * (budget_coefficient + Fight.amateur_budget_coeff);
        loser.budget -= loser.getBudget() * (budget_coefficient + Fight.amateur_budget_coeff);
        this.popularity += loser.getPopularity() * (popularity_coefficient + Fight.amateur_popularity_coeff);
        loser.popularity -= loser.getPopularity() * (popularity_coefficient + Fight.amateur_popularity_coeff);
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości budżetu oraz popularności artystów w przypadku przegranej Amateur'a
     * @param winner obiekt artysty, który wygrał w walce z Amateur'em
     */
    @Override
    public void updateStatsAfterLoss(Artist winner) {
        winner.budget += this.getBudget() * (budget_coefficient - Fight.amateur_budget_coeff);
        this.budget -= this.getBudget() * (budget_coefficient - Fight.amateur_budget_coeff);
        winner.popularity += this.getPopularity() * (popularity_coefficient - Fight.amateur_popularity_coeff);
        this.popularity -= this.getPopularity() * (popularity_coefficient - Fight.amateur_popularity_coeff);
    }
}
