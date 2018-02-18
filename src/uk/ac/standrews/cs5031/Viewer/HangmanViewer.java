package uk.ac.standrews.cs5031.Viewer;

import uk.ac.standrews.cs5031.Controller.HangmanGameplayController;
import uk.ac.standrews.cs5031.Controller.HangmanWordsController;
import uk.ac.standrews.cs5031.External.EasyIn;

public class HangmanViewer {
    public HangmanViewer() {
        boolean isCorrect;      //

        HangmanWordsController WController = new HangmanWordsController();
        HangmanGameplayController GController;

        WController.printWelcomeMessage();  //Print Welcome Message

        if (WController.isWordSourceDefault()) {
            GController = new HangmanGameplayController(WController.getWordFromCategory(WController.getCategoryChoice())); //Change game state with new chosen character
        }
        else{
            GController = new HangmanGameplayController(WController.getWordFromFile(WController.getWordSourceFileDirectory()));
        }

        while(!GController.isGameWon() && !GController.isGameLost()) {
            GController.showWord();

            System.out.println("Guesses remaining: " + GController.RemainingGuesses);

            isCorrect = GController.getNextGuess();       //change game/correct

            if (isCorrect) System.out.println("Good guess!"); //change correct
            if (!isCorrect) System.out.println("Wrong guess!");       //change correct
        }

        if (GController.isGameWon()) {
            System.out.println("Well done!");
            System.out.println("You took " + GController.MadeGuesses + " guesses");      //What is g? change to something legible when found
        } else {
            System.out.println("You lost! The word was " + GController.RandomWord);
        }
    }


}
