package uk.ac.standrews.cs5031.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanModel {
    private static String[] Counties = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
    private static String[] Countries = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands", "Spain", "Portugal",
            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
    private static String[] Cities = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };

    private static ArrayList<String> CustomWords = new ArrayList<String>();


    public String[] getCountries() {
        return Countries;
    }
    public String[] getCounties() {
        return Counties;
    }
    public String[] getCities() {
        return Cities;
    }

    public ArrayList<String> getCustomWords() {
        return CustomWords;
    }


}