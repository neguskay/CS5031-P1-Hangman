package uk.ac.standrews.cs5031.Viewer;

import uk.ac.standrews.cs5031.Controller.CommandOpts;
import uk.ac.standrews.cs5031.Controller.HangmanController;
import uk.ac.standrews.cs5031.Model.HangmanModInterface;
import uk.ac.standrews.cs5031.Model.HangmanModel;

import java.util.Scanner;

public class HangmanViewer {
    public HangmanViewer(String[] args) {

        String[] UserInput = args;      //Stores User Inputs

        boolean isCorrect;      //

        Scanner scanner = new Scanner(System.in);

        CommandOpts commandOpts = new CommandOpts(UserInput);       //Once User Input value is set, parse as variable

        HangmanController hangmanController;



        if (commandOpts.WordSource == "") {
            printWelcomeMessage();  //Print Welcome Message

            hangmanController = new HangmanController(HangmanModInterface.getWordFromCategory(scanner.nextInt()), commandOpts.MaxGuesses, commandOpts.MaxHints); //Change game state with new chosen character
        }
        else {
            hangmanController = new HangmanController(HangmanModInterface.getWordFromFile(commandOpts.WordSource), commandOpts.MaxGuesses, commandOpts.MaxHints);
        }

        while(!hangmanController.isGameWon() && !hangmanController.isGameLost()) {
            hangmanController.showWord();

            System.out.println("Guesses remaining: " + hangmanController.RemainingGuesses);

            isCorrect = hangmanController.getNextGuess();       //change game/correct

            if (isCorrect) System.out.println("Good guess!"); //change correct
            if (!isCorrect) System.out.println("Wrong guess!");       //change correct
        }

        if (hangmanController.isGameWon()) {
            System.out.println("Well done!");
            System.out.println("You took " + hangmanController.MadeGuesses + " guesses");      //What is g? change to something legible when found
        } else {
            System.out.println("You lost! The word was " + hangmanController.RandomWord);
        }
    }

    /**
     * Prints the initial welcome message for the game and instructions
     * --To be refactored to a more appropriate class
     */
    public void printWelcomeMessage(){
        System.out.println("WELCOME TO HANGMAN");
        System.out.println("The game in which your geographical knowledge is tested!");
        System.out.println();

        System.out.println("  1. Counties");
        System.out.println("  2. Countries");
        System.out.println("  3. Cities");
        System.out.println();

        System.out.print("Please enter a category number(1-3):");   //Give better information to the game
    }

}
