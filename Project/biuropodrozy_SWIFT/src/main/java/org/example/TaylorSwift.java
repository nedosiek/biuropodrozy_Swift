package org.example;

public class TaylorSwift extends Artist{
    public final double TS_chance = 0.01; //szansa na pojawienie sie agenta TS
    public int TS_losses = 0; //licznik przegranych walk TS

    public TaylorSwift(String pseudonym, double budget, double popularity){
        super(pseudonym, budget, popularity);
        this.pseudonym = "Taylor Swift";
    }
}
