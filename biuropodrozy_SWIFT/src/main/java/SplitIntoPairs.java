import java.util.Arrays;
import java.util.Random;
import java.lang.Math;

public class SplitIntoPairs {
    static int[][] fight_field;
    static int[] fight_pairs;
    static int[][] fight_pairs_table;

    // tworzymy pole, na ktorym znajduja sie artysci
    public static int[][] get_fight_field() {
        Swifties.creating_list_of_artists();
        fight_field = new int[Swifties.list_of_artists_.length + 3][Swifties.list_of_artists_.length + 3];
        fight_pairs = new int[Swifties.list_of_artists_.length];
        fight_pairs_table = new int[Swifties.list_of_artists_.length][Swifties.list_of_artists_.length];

        for (int i = 0; i < fight_field.length; i++) {
                Arrays.fill(fight_field[i], -1);
        }

        for (int i = 0; i < fight_pairs_table.length; i++) {
            Arrays.fill(fight_pairs_table[i], 0); //-1 czyli nie moga ze soba zagrac; 0 - nie grali; 1 - już zagrali
        }

        // wrzucamy artystow na pole
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

        return fight_field;
    }

    // znajdujemy pary najblizszych ze soba artystow, zaczynajac od (x, y) = (0, 0)
    public static int[] distance() {
        get_fight_field();
        Arrays.fill(fight_pairs, -1);

        for (int i = 0; i < fight_field.length; i++) {
            for (int j = 0; j < fight_field[i].length; j++) {
                double min_distance = Double.MAX_VALUE;
                int min_element = Integer.MAX_VALUE;
                int x_of_min_element = -1;
                int y_of_min_element = -1;
                if (fight_field[i][j] != -1) {
                    if (fight_pairs[fight_field[i][j]] == -1){

                        for (int i2 = 0; i2 < fight_field.length; i2++){
                            for (int j2 = 0; j2 < fight_field[i2].length; j2++){
                                if (fight_field[i2][j2] != -1 && fight_field[i2][j2] != fight_field[i][j]){
                                    if (fight_pairs[fight_field[i2][j2]] == -1){
                                        double distance_between_two_artists = Math.sqrt(Math.pow((i2 - i), 2) + Math.pow((j2 - j), 2));
                                        if (distance_between_two_artists < min_distance){
                                            min_distance = distance_between_two_artists;
                                            min_element = fight_field[i2][j2];
                                            x_of_min_element = i2;
                                            y_of_min_element = j2;
                                        }
                                    }
                                }
                            }
                        }
                        if (min_element != Integer.MAX_VALUE){
                            fight_pairs[fight_field[x_of_min_element][y_of_min_element]] = fight_field[i][j];
                            fight_pairs[fight_field[i][j]] = min_element;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < fight_pairs.length; i++) {
            if (fight_pairs[i] == -1){
                fight_pairs[i] = i;
            }
        }
        System.out.println();

        for (int i = 0; i < fight_pairs_table.length; i++) {
            for (int j = 0; j < i+1; j++) {
                fight_pairs_table[i][j] = -1;
            }
        }
    return fight_pairs;
    }
}
