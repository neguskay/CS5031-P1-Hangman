package uk.ac.standrews.cs5031.Viewer;

import java.util.Scanner;

public class HangmanViewer {
    public static void main(String[] args) {

        String[] UserInput = args;      //Stores User Inputs

        boolean isCorrect;      //

        Scanner scanner = new Scanner(System.in);
        CommandOpts commandOpts = new CommandOpts(UserInput);       //Once User Input value is set, parse as variable
        GameState gameState;


        if (commandOpts.WordSource == "") {
            commandOpts.printWelcomeMessage();  //Print Welcome Message

            gameState = new GameState(Words.getWordFromCategory(scanner.nextInt()), commandOpts.MaxGuesses, commandOpts.MaxHints); //Change game state with new chosen character
        }
        else {
            gameState = new GameState(Words.getWordFromFile(commandOpts.WordSource), commandOpts.MaxGuesses, commandOpts.MaxHints);
        }

        while(!gameState.isGameWon() && !gameState.isGameLost()) {
            gameState.showWord();

            System.out.println("Guesses remaining: " + gameState.RemainingGuesses);

            isCorrect = gameState.getNextGuess();       //change game/correct

            if (isCorrect) System.out.println("Good guess!"); //change correct
            if (!isCorrect) System.out.println("Wrong guess!");       //change correct
        }

        if (gameState.isGameWon()) {
            System.out.println("Well done!");
            System.out.println("You took " + gameState.MadeGuesses + " guesses");      //What is g? change to something legible when found
        } else {
            System.out.println("You lost! The word was " + gameState.RandomWord);
        }
    }

}
