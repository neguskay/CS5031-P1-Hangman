package uk.ac.standrews.cs5031.Controller;

import java.util.ArrayList;
import java.util.Scanner;

public class HangmanGameplayController implements GameplayControllerInterface{
    public String RandomWord;

    public int MadeGuesses;       //what is g? change to suitable name
    public int RemainingGuesses;       //**
    public int RemainingHints;       //**

    public int MadeGuessesGUI;       //what is g? change to suitable name
    public int RemainingGuessesGUI;       //**
    public int RemainingHintsGUI;       //**

    ArrayList<Character> GuessedChars;
    ArrayList<Character> MissGuessedChars;

    ArrayList<Character> GuessedCharsGUI;
    ArrayList<Character> MissGuessedCharsGUI;

    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public HangmanGameplayController(String ChosenRandomWord) {
        this.RandomWord = ChosenRandomWord;

        MissGuessedChars = new ArrayList<Character>();
        GuessedChars = new ArrayList<Character>();

        MissGuessedCharsGUI = new ArrayList<Character>();
        GuessedCharsGUI = new ArrayList<Character>();

        for(int i = 0; i < ChosenRandomWord.length(); ++i) {
            if (!MissGuessedChars.contains(Character.toLowerCase(ChosenRandomWord.charAt(i))))
                MissGuessedChars.add(Character.toLowerCase(ChosenRandomWord.charAt(i)));
        }

        for(int i = 0; i < ChosenRandomWord.length(); ++i) {
            if (!MissGuessedCharsGUI.contains(Character.toLowerCase(ChosenRandomWord.charAt(i))))
                MissGuessedCharsGUI.add(Character.toLowerCase(ChosenRandomWord.charAt(i)));
        }


        this.MadeGuesses = 0;
        RemainingGuesses = 5;
        this.RemainingHints = 3;

        this.MadeGuessesGUI = 0;
        RemainingGuessesGUI = 5;
        this.RemainingHintsGUI = 3;
    }




    public String showWordGUI() {
        String retWord = "";
        for (int i = 0; i < RandomWord.length(); ++i) {
            if (GuessedCharsGUI.contains(RandomWord.charAt(i))) {
                //System.out.print(RandomWord.charAt(i));
                retWord += RandomWord.charAt(i);
            } else {
                //System.out.print("-");
                retWord +="-";
            }
        }
        System.out.println("");
        return retWord;
    }

    public boolean getNextGuessGUI(char UserChar) {///inspect and refactor, makes no sense
        System.out.print("Guess a letter or word (? for a hint): ");
        //String GuessedStream =       //Potential bug, will only check for Lower Case characters
        //UserChar = GuessedStream.charAt(0);
        boolean retVal = false;
        for(int i = 0; i < MissGuessedCharsGUI.size(); ++i) { // Loop over the not got
            if (MissGuessedCharsGUI.get(i) == UserChar) {
                MissGuessedCharsGUI.remove(i);
                GuessedCharsGUI.add(UserChar);
                MadeGuessesGUI++;
                retVal = true;
            }
        }

        MadeGuessesGUI++; // One more guess - Increment guesses made
        RemainingGuessesGUI--;    // Decrease guesses remaining
        return retVal;
    }

    public boolean isGameWonGUI() {
        if (MissGuessedCharsGUI.size() == 0) return true; else return false;
    }

    public boolean isGameLostGUI() {
        if (MissGuessedCharsGUI.size() > 0 && RemainingGuessesGUI == 0) return true; else return false;
    }

    public void getHintGUI() {
        if (RemainingHintsGUI == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(MissGuessedCharsGUI.get((int)(Math.random()*MissGuessedCharsGUI.size())));
    }




//FOR VIEWER == DO NOT TOUCH

    public void showWord() {
        for (int i = 0; i < RandomWord.length(); ++i) {
            if (GuessedChars.contains(RandomWord.charAt(i))) {
                System.out.print(RandomWord.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");

    }

    public boolean getNextGuess() {///inspect and refactor, makes no sense
        char GuessedChar;
        System.out.print("Guess a letter or word (? for a hint): ");
        String GuessedStream = scanner.next().toLowerCase();      //Potential bug, will only check for Lower Case characters

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

    public int getRemainingGuessesGUI(){
        return RemainingGuessesGUI;
    }
}
