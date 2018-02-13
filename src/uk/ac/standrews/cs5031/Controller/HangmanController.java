package uk.ac.standrews.cs5031.Controller;

import java.util.ArrayList;
import java.util.Scanner;

public class HangmanController {
    public String RandomWord;

    public int MadeGuesses;       //what is g? change to suitable name
    public int RemainingGuesses;       //**
    public int RemainingHints;       //**

    ArrayList<Character> GuessedChars;
    ArrayList<Character> MissGuessedChars;

    public Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public HangmanController(String ChosenRandomWord, int RemGuesses, int RemHints) {
        this.RandomWord = ChosenRandomWord;
        MissGuessedChars = new ArrayList<Character>();
        GuessedChars = new ArrayList<Character>();

        for(int i = 0; i < ChosenRandomWord.length(); ++i) {
            if (!MissGuessedChars.contains(Character.toLowerCase(ChosenRandomWord.charAt(i))))
                MissGuessedChars.add(Character.toLowerCase(ChosenRandomWord.charAt(i)));
        }


        this.MadeGuesses = 0;
        RemainingGuesses = RemGuesses;
        this.RemainingHints = RemHints;
    }

    public void showWord() {
        for (int i = 0; i < RandomWord.length(); ++i) {
            if (GuessedChars.contains(RandomWord.charAt(i))) {
                System.out.print(RandomWord.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
        // System.out.println(missing);
    }

    public boolean getNextGuess() {
        //int i;
        char GuessedChar;

        System.out.print("Guess a letter or word (? for a hint): ");

        String GuessedStream = scanner.next().toLowerCase();      //Potential bug, will only check for Lower Case characters

        //System.out.println(GuessedStream);

        if (GuessedStream.length() > 1) {
            if (GuessedStream==RandomWord) {
                MissGuessedChars.clear();
                return true;
            } else return false;
        }

        GuessedChar = GuessedStream.charAt(0);

        if (GuessedChar == '?') {
            getHint();
            return false;
        }

        for(int i = 0; i < MissGuessedChars.size(); ++i) { // Loop over the not got
            if (MissGuessedChars.get(i) == GuessedChar) {
                MissGuessedChars.remove(i);
                GuessedChars.add(GuessedChar);
                MadeGuesses++;
                return true;
            }
        }

        MadeGuesses++; // One more guess - Increment guesses made
        RemainingGuesses--;    // Decrease guesses remaining
        return false;
    }

    public boolean isGameWon() {
        if (MissGuessedChars.size() == 0) return true; else return false;
    }

    public boolean isGameLost() {
        if (MissGuessedChars.size() > 0 && RemainingGuesses == 0) return true; else return false;
    }

    public void getHint() {
        if (RemainingHints == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(MissGuessedChars.get((int)(Math.random()*MissGuessedChars.size())));
    }



}
