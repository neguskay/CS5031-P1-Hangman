package uk.ac.standrews.cs5031.Controller;

import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.HangmanModelInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanGUIController {
    private String GuideErrorMessage;
    private String CurrentPredictedChars;
    private String ChosenWord;

    private int RemainingGuesses;
    private int TotalMadeGuesses;

    private static int CategoryNumber;
    private static String FileDirectory;

    String[] Countries;
    String[] Counties;
    String[] Cities;
    ArrayList<String> CustomWords = new ArrayList<String>();

    private HangmanModelInterface hangmanmodel = new HangmanModelInterface() {
        @Override
        public String[] getCountries() {
            return new String[];
        }

        @Override
        public String[] getCounties() {
            return new String[0];
        }

        @Override
        public String[] getCities() {
            return new String[0];
        }

        @Override
        public ArrayList<String> getCustomWords() {
            return null;
        }
    };

    public HangmanGUIController(int ComboBoxIndex, String FileDirectory){
        this.Countries = hangmanmodel.getCountries();
        this.Counties = hangmanmodel.getCounties();
        this.Cities= hangmanmodel.getCities();
        this.CustomWords = hangmanmodel.getCustomWords();

        this.CategoryNumber = ComboBoxIndex;
        this.FileDirectory = FileDirectory;

        this.ChosenWord = setChosenWord(CategoryNumber);




    }

    private String getWordFromCategory(int Category) {
        System.out.println();
        System.out.println("Getting word from set category");
        System.out.println();
        String ChosenWord = "";
        switch (Category){
            case 1: ChosenWord = Counties[(int)(Math.random()*9)];
                break;
            case 2: ChosenWord = Countries[(int)(Math.random()*15)];
                break;
            case 3: ChosenWord = Cities[(int)(Math.random()*10)];
                break;

        }
        return ChosenWord;
    }

    private String getWordFromFile(String FileName) {
        String line;//change line to a suitable name

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

    private String setChosenWord(int ComboBoxIndex){
        String Word = "";
        if(ComboBoxIndex == 0){
            Word = getWordFromFile(FileDirectory);
            System.out.println("Retrieving Chosen Word from file: "+ FileDirectory);
        }else if((ComboBoxIndex > 0)&&(ComboBoxIndex<4)){
            Word = getWordFromCategory(CategoryNumber);
            System.out.println("Retrieving Chosen Word From: "+ getCategoryName(CategoryNumber));
        }
        System.out.println("Setting Chosen Word");
        return Word;
    }

    private String getCategoryName(int categoryNumber) {
        String CategoryName = "";
        switch (categoryNumber){
            case 1: CategoryName = "Counties";
                break;
            case 2: CategoryName = "Countries";
                break;
            case 3: CategoryName = "Cities";
                break;
        }
        return CategoryName;
    }

}
