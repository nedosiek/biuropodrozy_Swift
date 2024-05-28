package org.example;

public abstract class Fight {
    public static boolean comparison(double xbudget, double ybudget, double xpopularity, double ypopularity) {
        double x_potential = (xbudget * 0.85) + (xpopularity * 1.2);
        double y_potential = (ybudget * 0.85) + (ypopularity * 1.2);
        if(x_potential > y_potential){
            return true;
        }
        else return false;
    }
}
