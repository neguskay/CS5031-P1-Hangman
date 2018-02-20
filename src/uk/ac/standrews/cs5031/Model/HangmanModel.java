package uk.ac.standrews.cs5031.Model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Model Class of the MVC of the Hangman game.
 * Implements the IHangmanModel interface.
 * Holds the Countries, Counties and Cities Arrays used within the game's logic.
 */
public class HangmanModel implements IHangmanModel {
    private static String[] Counties = { "Argyll and Bute", "Caithness",  "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross" };
    private static String[] Countries = { "Scotland", "England", "Wales", "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands", "Spain", "Portugal",
            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece" };
    private static String[] Cities = { "St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk" };

    private static ArrayList<String> CustomWords = new ArrayList<String>();

    /**
     * @return Countries The Array of Country names
     */
    public String[] getCountries() {
        return Countries;
    }

    /**
     * @return Counties The Array of County names
     */
    public String[] getCounties() {
        return Counties;
    }

    /**
     * @returnThe Cities Array of names of cities
     */
    public String[] getCities() {
        return Cities;
    }

    /**
     * @return CustomWords Array List containing several Custom Words
     */
    public ArrayList<String> getCustomWords() {
        return CustomWords;
    }

}
