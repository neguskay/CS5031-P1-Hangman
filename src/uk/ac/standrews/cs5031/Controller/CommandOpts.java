package uk.ac.standrews.cs5031.Controller;


public class CommandOpts {
    public int MaxGuesses;      //Maximum number of guesses
    public int MaxHints;        //Maximum number of hints

    public String WordSource= "";    //**;          //File Name

    public CommandOpts(String[] args) {
        MaxGuesses = 10;        //**
        MaxHints = 2;      //**



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


}
