/**
 * Klasa, odpowiadająca za artystę Azealia Banks, dziedzicząca po klasie Artist
 */
public class AzealiaBanks extends Artist {
    /**
     * Zmienna, opisująca szansę na pojawienie się artysty Azealia Banks
     */
    private final static double AB_chance = 0.1; //szansa na pojawienie sie agenta Azealia Banks, [%]

    /**
     * Konstruktor artysty Azealia Banks
     * @param budget budżet artysty
     * @param popularity popularność artysty
     */
    public AzealiaBanks(double budget, double popularity) {
        this.pseudonym = "Azealia Banks";
        this.budget = budget*0.7;
        this.popularity = popularity;
    }

    /**
     * Metoda, odpowiadająca za sprawdzenie, czy Azealia Banks pojawi się w projekcie
     * @return true/false w zależności, czy szansa na pojawienię się Azealia Banks się spełniła
     */
    public static boolean azealias_in_play(){
        return Math.random() <= AB_chance;
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości budżetu oraz popularności artystów w przypadku wygranej Azealia Banks
     * @param loser obiekt artysty, który przegrał w walce z artystą Azealia Banks
     */
    @Override
    public void updateStatsAfterWin(Artist loser) {
        this.budget += loser.getBudget() * (Artist.budget_coefficient - Fight.ab_budget_coeff);
        loser.budget -= loser.getBudget() * (Artist.budget_coefficient - Fight.ab_budget_coeff);
        this.popularity += loser.getPopularity() * (Artist.popularity_coefficient - Fight.ab_popularity_coeff);
        loser.popularity -= loser.getPopularity() * (Artist.popularity_coefficient - Fight.ab_popularity_coeff);
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości budżetu oraz popularności artystów w przypadku przegranej Azealia Banks
     * metoda jest pusta, iż Azealia nic nie traci w przypadku przegranej, a artysta, który wygrał, nic nie zyskuje
     * @param winner obiekt artysty, który wygrał w walce z artystą Azealia Banks
     */
    @Override
    public void updateStatsAfterLoss(Artist winner) {
    }
}
