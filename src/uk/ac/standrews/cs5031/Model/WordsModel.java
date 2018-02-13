package uk.ac.standrews.cs5031.Model;

//Add java docs
//Add comments where appropriate
//Change names on lines commented //**

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.io.*;
import java.util.ArrayList;

public class WordsModel {

    static String[] Counties = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
    static String[] Countries = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands", "Spain", "Portugal",
            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
    static String[] Cities = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };

    static ArrayList<String> CustomWords;

    /**
     *
     * @param category User-chosen category i.e Countries, Counties or Cities
     * @return Array of Words
     */
    public static String getWordFromCategory(int category) {
        if (category == 1)
            return Counties[(int)(Math.random()*9)];
        if (category == 2)
            return Countries[(int)(Math.random()*15)];
        return Cities[(int)(Math.random()*10)];
    }

    /**
     *
     * @param FileName filename for new Custom word sources
     * @return CustomWords - Array List
     */
    public static String getWordFromFile(String FileName) {
        String line;
        CustomWords = new ArrayList<String>();

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