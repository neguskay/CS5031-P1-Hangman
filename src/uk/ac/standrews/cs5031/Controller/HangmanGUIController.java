package uk.ac.standrews.cs5031.Controller;

import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.HangmanModelInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class HangmanGUIController extends Observable implements IHangmanGUIController {
    public String GameStats;
    public String FeedbackMessage;
    public String CurrentPredictedChars;


    public int RemainingGuesses;
    public int TotalMadeGuesses;
    public int RemainingHints;

    private static int CategoryNumber;

    public String FileDirectory;
    public String ChosenWord;
    public static String MESSAGE_CORRECT_GUESS = "Right Guess!";
    public static String MESSAGE_WRONG_GUESS = "Wrong Guess!";
    public static String MESSAGE_HINT_GUESS = "Try: ";
    public static String MESSAGE_NO_HINTS_GUESS = "You used all hints!";

    public ArrayList<Character> CharsGuessed = new ArrayList<Character>();
    public ArrayList<Character> CharsRemaining = new ArrayList<Character>();

    String[] Countries;
    String[] Counties;
    String[] Cities;
    ArrayList<String> CustomWords = new ArrayList<String>();

    public HangmanModelInterface hangmanModel = new HangmanModel();

    public HangmanGUIController(int ComboBoxIndex, String FileDirectory){
        this.Countries = hangmanModel.getCountries();
        this.Counties = hangmanModel.getCounties();
        this.Cities= hangmanModel.getCities();
        this.CustomWords = hangmanModel.getCustomWords();

        this.CategoryNumber = ComboBoxIndex;
        this.FileDirectory = FileDirectory;

        this.ChosenWord = setChosenWord(CategoryNumber);
        this.GameStats = "";
        this.CurrentPredictedChars = "";

        this.TotalMadeGuesses = 0;
        this.RemainingGuesses = initRemainingGuesses(ChosenWord);
        this.RemainingHints = 3;
        update();
        initCharsLists();
    }

    public int initRemainingGuesses(String chosenWord) {
        update();
        return chosenWord.length()/2;
    }

    public void initCharsLists(){
        this.CharsRemaining.clear();
        this.CharsGuessed.clear();

        for(int i = 0; i < ChosenWord.length(); ++i) {
            if (!CharsRemaining.contains(ChosenWord.charAt(i))) {
                this.CharsRemaining.add(ChosenWord.charAt(i));
            }
        }
        System.out.println("Characters To Be Guessed: "+CharsRemaining.toString());
        System.out.println("Characters Guessed: "+CharsGuessed.toString()+ "check: Is is empty?");
        update();
    }

    public String getWordFromCategory(int Category) {
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
        update();
        return ChosenWord;
    }

    public String getWordFromFile(String FileName) {
        String line;//change line to a suitable name

        try {
            FileReader file = new FileReader(FileName);
            BufferedReader reader = new BufferedReader(file);
            while((line = reader.readLine()) != null) {
                CustomWords.add(line);
            }
            update();
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

    public String setChosenWord(int ComboBoxIndex){
        String Word = "";
        if(ComboBoxIndex == 0){
            Word = getWordFromFile(FileDirectory);
            System.out.println("Retrieving Chosen Word from file: "+ FileDirectory);
        }else if((ComboBoxIndex > 0)&&(ComboBoxIndex<4)){
            Word = getWordFromCategory(CategoryNumber);
            System.out.println("Retrieving Chosen Word From: "+ getCategoryName(CategoryNumber));
        }
        System.out.println("Setting Chosen Word");
        update();
        return Word;
    }

    public String getCategoryName(int categoryNumber) {
        String CategoryName = "";
        switch (categoryNumber){
            case 1: CategoryName = "Counties";
                break;
            case 2: CategoryName = "Countries";
                break;
            case 3: CategoryName = "Cities";
                break;
        }
        update();
        return CategoryName;
    }

    public void updateGameStats(){
        updateCurrentPredictedChars();
        this.GameStats = "Remaining Guesses: "+ this.RemainingGuesses+"  "+
                         "Total Guesses Made: "+ this.TotalMadeGuesses+"  "+
                         "Remaining Hints: "+this.RemainingHints + "   ";
        update();
    }

    public void updateCurrentPredictedChars(){
        //this.CurrentPredictedChars = "";
        for (int i = 0; i < ChosenWord.length(); ++i) {
            if (CharsGuessed.contains(ChosenWord.charAt(i))) {
                System.out.print(ChosenWord.charAt(i));
                this.CurrentPredictedChars += ChosenWord.charAt(i);
            } else {
                System.out.print("-");
                this.CurrentPredictedChars += "-";
            }
        }
        System.out.println("");
        update();
    }
    public void updateFeedbackMessage(String feedbackMessage){
        this.FeedbackMessage = feedbackMessage;
        System.out.println(FeedbackMessage);
        update();
    }
    public boolean isCharCorrect(char userChar){
        System.out.println("Checking Char: "+ userChar);
        boolean isCorrect = false;
        for(int i = 0; i < CharsRemaining.size(); ++i) { // Loop over the not got
            if (CharsRemaining.get(i) == userChar) {
                CharsRemaining.remove(i);
                CharsGuessed.add(userChar);
                isCorrect = true;
            }
        }
        this.TotalMadeGuesses+=1;
        update();
        return isCorrect;
    }
    public boolean isGameWon() {
        boolean gameWon;
        if ((CharsRemaining.size() == 0) && (!(this.RemainingGuesses <0))) {
            gameWon =  true;
        } else {
            gameWon =  false;
        }
        update();
        return gameWon;
    }
    public boolean isGameLost(){
        boolean gameLost;
        if ((CharsRemaining.size() > 0) && ((this.RemainingGuesses <=0))) {
            gameLost =  true;
        } else {
            gameLost =  false;
        }
        update();
        return gameLost;
    }

    public void checkSubmittedChar(char userChar){
        if (!isGameWon() && !isGameLost()){
            if(isCharCorrect(userChar)){
                updateFeedbackMessage(MESSAGE_CORRECT_GUESS);
            }
            else if (!isCharCorrect(userChar)){
                this.RemainingGuesses-=1;
                updateFeedbackMessage(MESSAGE_WRONG_GUESS);
            }

            updateCurrentPredictedChars();
            updateGameStats();
        }
        update();
    }

    public void getHint(){
        if(!(RemainingHints<1)){
            updateFeedbackMessage(MESSAGE_HINT_GUESS+ ChosenWord.charAt((int)(Math.random()*ChosenWord.length())));
            RemainingHints-=1;
        }
        else{
            updateFeedbackMessage(MESSAGE_NO_HINTS_GUESS);
        }
        update();
    }

    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    public String getGameStats(){
        return GameStats;
    }

    public String getFeedbackMessage(){
        return FeedbackMessage;
    }

    public String getCurrentPredictedChars(){
        return CurrentPredictedChars;
    }
}
