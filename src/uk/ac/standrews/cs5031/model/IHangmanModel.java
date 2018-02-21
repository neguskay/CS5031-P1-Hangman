package uk.ac.standrews.cs5031.model;

import java.util.ArrayList;

/**
 * HangmanModel's interface.
 * Implemented by HangmanModel Class
 */
public interface IHangmanModel {

  public abstract String[] getCountries();

  public abstract String[] getCounties();

  public abstract String[] getCities();

  public abstract ArrayList<String> getCustomWords();
}
