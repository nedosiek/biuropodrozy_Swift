package org.example;

public class AzealiaBanks extends Artist{
    public final double AB_chance = 0.0075; //szansa na pojawienie sie agenta Azealia Banks

    public AzealiaBanks(String pseudonym, double budget, double popularity){
        super(pseudonym, budget, popularity);
        this.pseudonym = "Azealia Banks";
    }
}
