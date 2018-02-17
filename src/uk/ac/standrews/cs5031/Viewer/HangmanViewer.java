package uk.ac.standrews.cs5031.Viewer;

import uk.ac.standrews.cs5031.Controller.HangmanGameplayController;
import uk.ac.standrews.cs5031.Controller.HangmanWordsController;
import uk.ac.standrews.cs5031.EasyIn;

public class HangmanViewer {
    public HangmanViewer(String[] args) {
        boolean isCorrect;      //

        HangmanWordsController WController = new HangmanWordsController();
        HangmanGameplayController GController;

        printWelcomeMessage();  //Print Welcome Message

        if (isWordSourceDefault()) {
            GController = new HangmanGameplayController(WController.getWordFromCategory(getCategoryChoice())); //Change game state with new chosen character
        }
        else{
            GController = new HangmanGameplayController(WController.getWordFromFile(getWordSourceFileDirectory()));
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

    /**
     * Prints the initial welcome message for the game and instructions
     * --To be refactored to a more appropriate class
     */
    public void printWelcomeMessage(){
        System.out.println("WELCOME TO HANGMAN");
        System.out.println("The game in which your geographical knowledge is tested!");
        System.out.println();
        //Give better information to the game
    }

    public boolean isWordSourceDefault(){
        boolean isDefaultSource = true;
        System.out.println("Please Choose your Source of Words");

        System.out.println("Enter 'DEFAULT' for Game-Play default words");
        System.out.println("Enter 'CUSTOM' to upload a custom words' source");

        String WordSourceType= EasyIn.getString();

        if(WordSourceType.equalsIgnoreCase("d")){
            isDefaultSource = true;
            System.out.println("WORDS' SOURCE: GamePlay Default");
            System.out.println();
        }
        else if(WordSourceType.equalsIgnoreCase("c")){
            isDefaultSource = false;
            System.out.println("WORDS' SOURCE: Custom User File");
            System.out.println();
        }
        while(!((WordSourceType.equalsIgnoreCase("d"))||(WordSourceType.equalsIgnoreCase("c")))){
            System.out.println("Please Enter 'default' or 'custom!");
            WordSourceType = EasyIn.getString();
        }
        return isDefaultSource;
    }

    public String getWordSourceFileDirectory(){
        System.out.println("Enter the file path for your custom words' source");
        String CustomWordsFilePath = EasyIn.getString();

        while(CustomWordsFilePath.isEmpty()){
            System.out.println("Please enter a file path");
            CustomWordsFilePath = EasyIn.getString();
        }
        return CustomWordsFilePath;
    }

    public int getCategoryChoice(){
        int ChosenCategory = 1;
        System.out.println("Please choose your category of words, by entering one of the following integers");
        System.out.println("  '1' for Counties");
        System.out.println("  '2' for Countries");
        System.out.println("  '3' for Cities");
        System.out.println();

        ChosenCategory = EasyIn.getInt();

        switch (ChosenCategory){
            case 1: System.out.println("Chosen Category: Counties");
            break;
            case 2: System.out.println("Chosen Category: Countries");
            break;
            case 3: System.out.println("Chosen Category: Cities");
            break;
            default : System.out.println("Chosen Default Category: Countries");
            break;
        }
        while((ChosenCategory > 3)||(ChosenCategory < 1)){
            System.out.println("Please Enter 1,2 or 3 for Countries,Counties and cities Respectively");
            ChosenCategory = EasyIn.getInt();
        }
        return ChosenCategory;
    }
}
