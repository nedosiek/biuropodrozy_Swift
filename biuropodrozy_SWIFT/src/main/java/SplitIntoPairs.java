import java.util.Arrays;
import java.util.Random;
import java.lang.Math;

public class SplitIntoPairs {
    //static int figth_pairs_just_artists; Swifties.list_of_artists_.length/2;

    static int[][] fight_field = new int[Swifties.list_of_artists_.length + 3][Swifties.list_of_artists_.length + 3];
    //static int[] fight_pairs_copy = new int[Swifties.list_of_artists_.length];
    static int[] fight_pairs = new int[Swifties.list_of_artists_.length];
    static int[][] fight_pairs_table = new int[Swifties.list_of_artists_.length][Swifties.list_of_artists_.length];

    public static int[][] get_fight_field(/*double[][] fight_field*/) {
        for (int i = 0; i < fight_field.length; i++) {
                Arrays.fill(fight_field[i], -1);
        }

        Swifties.creating_list_of_artists();

        for (int i = 0; i < fight_pairs_table.length; i++) {
            Arrays.fill(fight_pairs_table[i], 0); //-1 czyli nie moga ze soba zagrac; 0 - nie grali; 1 - już zagrali
        }


        for (int i = 0; i < Swifties.list_of_artists_.length; i++){
            Random x = new Random();
            Random y = new Random();
            int x_field = x.nextInt(Swifties.list_of_artists_.length + 3);
            int y_field = y.nextInt(Swifties.list_of_artists_.length + 3);
            if (fight_field[x_field][y_field] == -1){
                fight_field[x_field][y_field] = i;
            } else {
                i--;
            }
        }

        /*for (int i = 0; i < fight_field.length; i++) {
            for (int j = 0; j < fight_field[i].length; j++) {
                System.out.print(fight_field[i][j] + " ");
            }
            System.out.println();
        }*/
        return fight_field;
    }

    public static int[] distance() {
        //double[] fight_pairs = new double[Swifties.list_of_artists_.length];
        get_fight_field();
        //calculate_number_of_pairs();

        //System.out.println(length_of_figth_pairs + " liczba par");

        Arrays.fill(fight_pairs, -1);
        //Arrays.fill(fight_pairs_copy, -1);

        //int pair_index = 0;

        for (int i = 0; i < fight_field.length; i++) {
            for (int j = 0; j < fight_field[i].length; j++) {
                double min_distance = Double.MAX_VALUE;
                int min_element = Integer.MAX_VALUE;
                int x_of_min_element = -1;
                int y_of_min_element = -1;
                if (fight_field[i][j] != -1) {
                    if (fight_pairs[fight_field[i][j]] == -1){
                        //System.out.println(fight_field[i][j] + " - tu mamy element pierwszy dla ktorego szukamy parę");

                        for (int i2 = 0; i2 < fight_field.length; i2++){
                            for (int j2 = 0; j2 < fight_field[i2].length; j2++){
                                if (fight_field[i2][j2] != -1 && fight_field[i2][j2] != fight_field[i][j]){
                                    //System.out.println(fight_field[i2][j2] + " - elementy szukane dla poprzedniego elementu");
                                    if (fight_pairs[fight_field[i2][j2]] == -1){
                                        //System.out.println("Sie zgadza");
                                        double distance_between_two_artists = Math.sqrt(Math.pow((i2 - i), 2) + Math.pow((j2 - j), 2));
                                        if (distance_between_two_artists < min_distance){
                                            min_distance = distance_between_two_artists;
                                            min_element = fight_field[i2][j2];
                                            x_of_min_element = i2;
                                            y_of_min_element = j2;
                                            //System.out.println("Element " + fight_field[i2][j2] + " jest najblizej teraz");
                                        }
                                    }
                                }
                            }
                        }
                        if (min_element != Integer.MAX_VALUE){
                            fight_pairs[fight_field[x_of_min_element][y_of_min_element]] = fight_field[i][j];
                            fight_pairs[fight_field[i][j]] = min_element;

                            /*System.out.println(fight_field[x_of_min_element][y_of_min_element] + "; " + fight_field[i][j]
                                    + "; " + fight_pairs[fight_field[x_of_min_element][y_of_min_element]] + "; "
                                    + fight_pairs[fight_field[i][j]]);*/
                            //fight_pairs[fight_field[i][j]] = -2;
                            //fight_pairs[fight_field[i2][j2]] = -2;

                            //System.out.println(fight_pairs[fight_field[i][j]] + " w parze z " + min_element);
                            //System.out.println(fight_pairs[fight_field[x_of_min_element][y_of_min_element]] + " w parze z " + fight_field[i][j]);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < fight_pairs.length; i++) {
            if (fight_pairs[i] == -1){
                fight_pairs[i] = i;
            }
            //System.out.print(fight_pairs[i] + " - " );
        }
        System.out.println();

        for (int i = 0; i < fight_pairs_table.length; i++) {
            for (int j = 0; j < i+1; j++) {
                fight_pairs_table[i][j] = -1;
            }
        }

        /*for (int i = 0; i < fight_pairs_table.length; i++) {
            for (int j = 0; j < fight_pairs_table[i].length; j++) {
                System.out.print(fight_pairs_table[i][j] + " ");
            }
            System.out.println();
        }*/

        /*for (int i = 0; i < fight_pairs_copy.length; i++){
            double min_distance = Double.MAX_VALUE;
            int min_element = -1;
            //if (fight_pairs_copy[i] == -1) continue;
            for (int j = 0; j < fight_field.length; j++){
                for (int k = 0; k < fight_field[j].length; k++){
                    if (fight_field[j][k] == i) {
                        //System.out.println(fight_field[j][k] + " tutaj jest element " + i + " - (" +j + "," + k + ")");
                        if (fight_pairs_copy[i] == -1) {

                            //System.out.println(fight_pairs_copy[i] + "copy");


                            for (int i2 = i + 1; i2 < Swifties.list_of_artists_.length; i2++) {
                                //System.out.println(i2 + " artysta");
                                //if (fight_pairs_copy[i2] == -1) {
                                    for (int j2 = 0; j2 < fight_field.length; j2++) {
                                        for (int k2 = 0; k2 < fight_field[j2].length; k2++) {
                                            if (fight_field[j2][k2] == i2) {
                                                if (fight_pairs_copy[i2] == -1){
                                                //System.out.println(fight_field[j2][k2] + "hmmm" + i2);
                                                double distance_between_two_artists = Math.sqrt(Math.pow((j2 - j), 2) + Math.pow((k2 - k), 2));
                                                if (distance_between_two_artists < min_distance) {
                                                    min_distance = distance_between_two_artists;
                                                    min_element = i2;
                                                    //System.out.println(min_distance + " odleglosc");
                                                    //System.out.println(min_element + " element/artysta");
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (min_element != -1) {
                fight_pairs_copy[min_element] = -2;
                fight_pairs_copy[i] = -2;
                fight_pairs[i] = min_element;

                System.out.println(i + " w parze z " + fight_pairs[i]);

            }
        }*/

    return fight_pairs;
    }
}
