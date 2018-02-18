package uk.ac.standrews.cs5031.Model;

import java.util.ArrayList;

public interface HangmanModelInterface {
    public abstract String[] getCountries();

    public abstract String[] getCounties();

    public abstract String[] getCities();

    public abstract ArrayList<String> getCustomWords();
}
