package com.nextgen.bankingapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        final List<String> names = Arrays.asList("Jeroen", "Joost", "Jan", "Marlies", "Marloes", "Marleen");

        //TODO filter names on the letter J using a Lambda

        System.out.println("Komkommer is not a palindrome: " + Main.isPalinDrome("Komkommer"));
        System.out.println("Radar is a palindrome: " + Main.isPalinDrome("Radar"));
        System.out.println("Lepel is a palindrome: " + Main.isPalinDrome("Lepel"));
        System.out.println("Meetsysteem is a palindrome: " + Main.isPalinDrome("Meetsysteem"));
    }

    private static boolean isPalinDrome(String word) {
        return false; //TODO implement me
    }


}
