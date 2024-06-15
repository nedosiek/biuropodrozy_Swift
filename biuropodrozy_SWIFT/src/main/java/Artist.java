public class Artist {
    public static double popularity_coefficient = 0.1;
    public static double budget_coefficient = 0.15;

    public String pseudonym;
    double popularity;
    double budget;
    public Artist(String pseudonym, double budget, double popularity) {
        this.pseudonym = pseudonym;
        this.popularity = popularity;
        this.budget = budget;
    }
}
