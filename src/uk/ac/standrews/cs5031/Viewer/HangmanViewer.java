package uk.ac.standrews.cs5031.Viewer;

import uk.ac.standrews.cs5031.Controller.CommandOpts;
import uk.ac.standrews.cs5031.Controller.HangmanController;
import uk.ac.standrews.cs5031.Hangman;
import uk.ac.standrews.cs5031.Model.WordsModel;

import java.util.Scanner;

public class HangmanViewer {
    public HangmanViewer(String[] args) {

        String[] UserInput = args;      //Stores User Inputs

        boolean isCorrect;      //

        Scanner scanner = new Scanner(System.in);
        CommandOpts commandOpts = new CommandOpts(UserInput);       //Once User Input value is set, parse as variable
        WordsModel wordsModel = new WordsModel();
        HangmanController hangmanController;


        if (commandOpts.WordSource == "") {
            commandOpts.printWelcomeMessage();  //Print Welcome Message

            hangmanController = new HangmanController(wordsModel.getWordFromCategory(scanner.nextInt()), commandOpts.MaxGuesses, commandOpts.MaxHints); //Change game state with new chosen character
        }
        else {
            hangmanController = new HangmanController(wordsModel.getWordFromFile(commandOpts.WordSource), commandOpts.MaxGuesses, commandOpts.MaxHints);
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

}
