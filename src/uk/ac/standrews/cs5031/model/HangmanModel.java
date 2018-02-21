package uk.ac.standrews.cs5031.model;

import java.util.ArrayList;

/**
 * model Class of the MVC of the Hangman game.
 * Implements the IHangmanModel interface.
 * Holds the Countries, Counties and Cities Arrays used within the game's logic.
 */
public class HangmanModel implements IHangmanModel {
  private static String[] counties = {"Argyll and Bute", "Caithness", "Kingdom of Fife",
    "East Lothian", "Highland", "Dumfries and Galloway",
    "Renfrewshire", "Scottish Borders", "Perth and Kinross"};
  private static String[] countries = {"Scotland", "England", "Wales",
    "Northern Ireland", "Ireland",
    "France", "Germany", "Netherlands", "Spain", "Portugal",
    "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece"};
  private static String[] cities = {"St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
    "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk"};

  private static ArrayList<String> customWords = new ArrayList<String>();

  /**
   * @return Countries The Array of Country names.
   */
  public String[] getCountries() {
    String [] countriesret = countries;
    return countriesret;
  }

  /**
   * @return Counties The Array of County names.
   */
  public String[] getCounties() {
    String [] countiesret = counties;
    return countiesret;
  }

  /**
   * @returnThe Cities Array of names of cities.
   */
  public String[] getCities() {
    String [] citiesret = cities;
    return citiesret;
  }

  /**
   * @return CustomWords Array List containing several Custom Words.
   */
  public ArrayList<String> getCustomWords() {
    ArrayList<String> customret = customWords;
    return customret;
  }

}
