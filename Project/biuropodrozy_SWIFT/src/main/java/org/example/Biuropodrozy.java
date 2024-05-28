package org.example;

import java.util.Scanner;

public class Biuropodrozy {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj liczbe artystow w symulacji: ");
        int NumberOfArtists = sc.nextInt();

        Artist sanah = new Artist("Sanah", 150000, 101);
        System.out.println(sanah.pseudonym);
    }
}