//Add java docs
//Add comments where appropriate
//Change names on lines commented //**
package uk.ac.standrews.cs5031;

public class CommandOpts {
    public int MaxGuesses;      //Maximum number of guesses
    public int MaxHints;        //Maximum number of hints

    String WordSource;          //Format name

    CommandOpts(String[] args) {
        MaxGuesses = 10;        //**
        MaxHints = 2;      //**

        WordSource = "";    //**

        for(int i = 0; i < args.length; ++i) {      //**
            if (args[i].equals("--guesses")) {      //**
                MaxGuesses = Integer.parseInt(args[i+1]);       //**
                i++;        //**

            }
            else if (args[i].equals("--hints")) {       //**
                MaxHints = Integer.parseInt(args[i+1]);     //**
                i++;

            }
            else WordSource = args[i];//**


        }
    }

    /**
     * Prints the initial welcome message for the game and instructions
     * To be refactored to a more appropriate class
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
