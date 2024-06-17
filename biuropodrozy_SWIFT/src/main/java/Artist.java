public class Artist {
    //wspolczynniki do walki
    public static double popularity_coefficient = 0.1;
    public static double budget_coefficient = 0.15;
    //zmienne dla kazdego artysty
    public String pseudonym;
    double popularity;
    double budget;

    public Artist(String pseudonym, double budget, double popularity) {//maska wprowadzania zwyklych artystow
        this.pseudonym = pseudonym;
        this.popularity = popularity;
        this.budget = budget;
    }
}
