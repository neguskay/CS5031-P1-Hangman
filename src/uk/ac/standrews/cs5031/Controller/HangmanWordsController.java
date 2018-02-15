package uk.ac.standrews.cs5031.Controller;

import uk.ac.standrews.cs5031.Model.HangmanModel;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HangmanWordsController {

    String[] Countries;
    String[] Counties;
    String[] Cities;
    ArrayList<String> CustomWords = new ArrayList<String>();


    HangmanModel hangmanmodel = new HangmanModel();

    public HangmanWordsController(){
        this.Countries = hangmanmodel.getCountries();
        this.Counties = hangmanmodel.getCounties();
        this.Cities= hangmanmodel.getCities();
        this.CustomWords = hangmanmodel.getCustomWords();
    }

    public  String getWordFromCategory(int category) {
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
            return "";
        } catch(IOException e) {
            System.out.println("IO error");
            return "";
        }
    }

}
