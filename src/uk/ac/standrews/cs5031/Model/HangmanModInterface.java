package uk.ac.standrews.cs5031.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public interface HangmanModInterface {
    HangmanModel hangmanmodel = new HangmanModel();

    String[] Countries = hangmanmodel.getCountries();
    String[] Counties = hangmanmodel.getCounties();
    String[] Cities = hangmanmodel.getCities();
    ArrayList<String> CustomWords = hangmanmodel.getCustomWords();

    /**
     *
     * @param category User-chosen category i.e Countries, Counties or Cities
     * @return Array of Words
     */
    static String getWordFromCategory(int category) {
        if (category == 1)
            return Counties[(int)(Math.random()*9)];
        if (category == 2)
            return Countries[(int)(Math.random()*15)];
        return Cities[(int)(Math.random()*10)];
    }

    static String getWordFromFile(String FileName) {
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
