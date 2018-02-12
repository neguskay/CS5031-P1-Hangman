//Add java docs
//Add comments where appropriate
//Change names on lines commented //**

//Changed variable names
//Changed scanner

package uk.ac.standrews.cs5031;

import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {

        String[] UserInput = args;      //Stores User Inputs

        boolean isCorrect;      //

        Scanner scanner = new Scanner(System.in);
        CommandOpts commandOpts = new CommandOpts(UserInput);       //Once User Input value is set, parse as variable
        GameState gameState;


        if (commandOpts.WordSource == "") {
            commandOpts.printWelcomeMessage();  //Print Welcome Message

            gameState = new GameState(Words.randomWord(scanner.nextInt()), commandOpts.MaxGuesses, commandOpts.MaxHints); //Change game state with new chosen character
        }
        else {
            gameState = new GameState(Words.randomWord(commandOpts.WordSource), commandOpts.MaxGuesses, commandOpts.MaxHints);
        }

        while(!gameState.won() && !gameState.lost()) {
            gameState.showWord();

            System.out.println("Guesses remaining: " + gameState.wrong);

            isCorrect = gameState.guessLetter();       //change game/correct

            if (isCorrect) System.out.println("Good guess!"); //change correct
            if (!isCorrect) System.out.println("Wrong guess!");       //change correct
        }

        if (gameState.won()) {
            System.out.println("Well done!");
            System.out.println("You took " + gameState.g + " guesses");      //What is g? change to something legible when found
        } else {
            System.out.println("You lost! The word was " + gameState.word);
        }
    }


}
