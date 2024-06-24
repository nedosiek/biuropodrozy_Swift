/**
 * Klasa, odpowiadająca za klase zwykłego artysty, po którym dziedziczą inne typy artystów
 */
public class Artist {
    /**
     * Zmienna, odpowiadająca za pseudonim artysty
     */
    public String pseudonym;
    /**
     * Zmienna, odpowiadająca za popularność artysty
     */
    double popularity;
    /**
     * Zmienna, odpowiadająca za budżet artysty
     */
    double budget;

    /**
     * Zmienna, odpowiadająca za to, ile popularności dostanie/straci artysta po walce
     */
    public static double popularity_coefficient = 0.1;
    /**
     * Zmienna, odpowiadająca za to, ile budżetu dostanie/straci artysta po walce
     */
    public static double budget_coefficient = 0.15;

    /**
     * Konstruktor artysty zwykłego, odpowiadający za popularność i budżet artysty
     */
    public Artist() {
        this.popularity = 0;
        this.budget = 0;
    }

    /**
     * Konstruktor, odpowiadający za pseudonim artysty
     * @param pseudonym pseudonim artysty
     */
    public Artist(String pseudonym){
        this.pseudonym = pseudonym;
    }

    /**
     * Getter, który zwraca pseudonim
     * @return pseudonim artysty
     */
    protected String getPseudonym(){
        return pseudonym;
    }

    /**
     * Getter, który zwraca budżet
     * @return budżet artysty
     */
    protected double getBudget(){
        return budget;
    }

    /**
     * Getter, który zwraca popularność
     * @return popularność artysty
     */
    protected double getPopularity(){
        return popularity;
    }

    /**
     * Setter, który przypisuje pseudonim artyście
     * @param pseudonym pseudonim, który ma być przypisany
     */
    protected void setPseudonym(String pseudonym){
        this.pseudonym = pseudonym;
    }

    /**
     * Setter, który przypisuje budżet artyście
     * @param budget budżet, który ma być przypisany
     */
    protected void setBudget(double budget){
        this.budget = budget;
    }

    /**
     * Setter, który przypisuje popularność artyście
     * @param popularity popularność, która ma być przypisana
     */
    protected void setPopularity(double popularity){
        this.popularity = popularity;
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości budżetu oraz popularności artystów w przypadku wygranej zwykłego artysty
     * @param loser obiekt artysty, który przegrał w walce ze zwykłym artystą
     */
    public void updateStatsAfterWin(Artist loser) {
        this.budget += loser.getBudget() * budget_coefficient;
        loser.budget -= loser.getBudget() * budget_coefficient;
        this.popularity += loser.getPopularity() * popularity_coefficient;
        loser.popularity -= loser.getPopularity() * popularity_coefficient;
    }

    /**
     * Metoda, odpowiadająca za zmianę wartości budżetu oraz popularności artystów w przypadku przegranej zwykłego artysty
     * @param winner obiekt artysty, który wygrał w walce ze zwykłym artystą
     */
    public void updateStatsAfterLoss(Artist winner) {
        winner.budget += this.getBudget() * budget_coefficient;
        this.budget -= this.getBudget() * budget_coefficient;
        winner.popularity += this.getPopularity() * popularity_coefficient;
        this.popularity -= this.getPopularity() * popularity_coefficient;
    }
}
