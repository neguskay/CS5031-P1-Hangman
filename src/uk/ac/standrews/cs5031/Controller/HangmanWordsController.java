package uk.ac.standrews.cs5031.Controller;

import uk.ac.standrews.cs5031.External.EasyIn;
import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.HangmanModelInterface;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HangmanWordsController{

    String[] Countries;
    String[] Counties;
    String[] Cities;
    ArrayList<String> CustomWords = new ArrayList<String>();


    private HangmanModelInterface hangmanmodel = new HangmanModel();

    public HangmanWordsController(){
        this.Countries = hangmanmodel.getCountries();
        this.Counties = hangmanmodel.getCounties();
        this.Cities= hangmanmodel.getCities();
        this.CustomWords = hangmanmodel.getCustomWords();
    }

    public  String getWordFromCategory(int category) {
        System.out.println();
        System.out.println("START");
        System.out.println();

        if (category == 1)
            return Counties[(int)(Math.random()*9)];
        if (category == 2)
            return Countries[(int)(Math.random()*15)];
        return Cities[(int)(Math.random()*10)];
    }

    public String getWordFromFile(String FileName) {
        String line;

        try {
            FileReader file = new FileReader(FileName);
            BufferedReader reader = new BufferedReader(file);
            while((line = reader.readLine()) != null) {
                CustomWords.add(line);
            }
            return CustomWords.get((int)(Math.random()*CustomWords.size()));
        } catch(FileNotFoundException e) {
            System.out.println("File error");
            System.out.println("File Was Not Found");
            return "";
        } catch(IOException e) {
            System.out.println("IO error");
            System.out.println("File Invalid");
            return "";
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
        int ChosenCategory;
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
            default : System.out.println("Invalid Category");
                break;
        }
        while((ChosenCategory > 3)||(ChosenCategory < 1)){
            System.out.println("Please Enter 1,2 or 3 for Countries,Counties and cities Respectively");
            ChosenCategory = EasyIn.getInt();
        }
        return ChosenCategory;
    }

}
