package uk.ac.standrews.cs5031.Controller;

import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.IHangmanModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**Controller Class of the MVC
 * Implements the IHangmanGUIController interface
 * Extends the Observable class and updates the GUI classes within the Viewer Package
 * Methods within this class are accessed via the interface stated above to control the game.
 * Gets the Countries,Counties and Cities from the Model Interface and checks against inputs from the GUIs.
 * Determines how and when the game is played/advanced/won
 */
public class HangmanGUIController extends Observable implements IHangmanGUIController {
    public String GameStats;
    public String FeedbackMessage;
    public String CurrentPredictedChars;


    public int RemainingGuesses;
    public int TotalMadeGuesses;
    public int RemainingHints;

    private int CategoryNumber;

    public String FileDirectory;
    public String ChosenWord;

    final static String MESSAGE_CORRECT_GUESS = "Right Guess!";
    final static String MESSAGE_WRONG_GUESS = "Wrong Guess!";
    final static String MESSAGE_HINT_GUESS = "Try: ";
    final static String MESSAGE_NO_HINTS_GUESS = "You used all hints!";
    final static String MESSAGE_GAME_WON_GUESS = "You used all hints!";

    public ArrayList<Character> CharsGuessed = new ArrayList<Character>();
    public ArrayList<Character> CharsRemaining = new ArrayList<Character>();

    String[] Countries;
    String[] Counties;
    String[] Cities;
    ArrayList<String> CustomWords = new ArrayList<String>();

    private IHangmanModel model = new HangmanModel();

    /**
     * @param ComboBoxIndex Item number from the Combo box used to select one of the 3 default arrays of words
     * @param FileDirectory File Directory of the file containing the custom words the user wishes to play with
     */
    public HangmanGUIController(String ComboBoxIndex, String FileDirectory){
        this.Countries = model.getCountries();
        this.Counties = model.getCounties();
        this.Cities= model.getCities();
        this.CustomWords = model.getCustomWords();

        this.CategoryNumber = Integer.parseInt(ComboBoxIndex);
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
    /**
     * Initiates the value of the number of guesses the user gets to play with within the game
     * @param chosenWord Word to be guessed
     * @return length of chosen word/2 as the number of remaining guesses
     */
    public int initRemainingGuesses(String chosenWord) {
        update();
        return chosenWord.length()/2;
    }
    /**
     * Initialises the following lists
     * CharsRemaining contains characters of the chosen word that have yet to be guessed
     * CharsGuessed contains characters of the chosen word which have been guessed correctly
     */
    public void initCharsLists(){
        this.CharsRemaining.clear();
        this.CharsGuessed.clear();

        for(int i = 0; i < ChosenWord.length(); ++i) {
            if (!CharsRemaining.contains(ChosenWord.toLowerCase().charAt(i))) {
                this.CharsRemaining.add(ChosenWord.toLowerCase().charAt(i));
            }
        }
        System.out.println("Characters To Be Guessed: "+CharsRemaining.toString());
        System.out.println("Characters Guessed: "+CharsGuessed.toString()+ "check: Is is empty?");
        update();
    }

    /**
     * Gets and sets the Chosen 'random' word to be guessed within the game
     * @param Category A number value corresponding to a category
     * @return ChosenWord the word to be guessed
     */
    public String getWordFromCategory(int Category) {
        System.out.println();
        System.out.println("Getting word from set category");
        System.out.println();
        String ChosenWord = "";
        Random random = new Random();
        switch (Category){
            case 1: ChosenWord = Counties[random.nextInt(Counties.length)];
                break;
            case 2: ChosenWord = Countries[random.nextInt(Countries.length)];
                break;
            case 3: ChosenWord = Cities[random.nextInt(Cities.length)];
                break;
            default: System.out.println("This won't hapopen");
                break;

        }
        update();
        return ChosenWord;
    }

    /**
     * Gets and sets a chosen random word after retrieving the said word from a given file
     * @param FileName File directory from which the word will be retrieved
     * @return a chosen word.
     */
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

    /**
     * Determines the source of the random word
     * I.E. either from a 'custom user-determined' file or from the default words arrays
     * @param ComboBoxIndex the number of the item chosen in the combo box
     * @return Word the randomly chosen word
     */
    public String setChosenWord(int ComboBoxIndex){
        String Word = "";
        if((ComboBoxIndex == 0)&& (!(FileDirectory==null))){
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

    /**
     * Determines the category name chosen based on the category number associated with it
     * @param categoryNumber Counties = 1, Countries = 2, Cities = 3
     * @return CategoryName Name of category
     */
    public String getCategoryName(int categoryNumber) {
        String CategoryName = "";
        switch (categoryNumber){
            case 1: CategoryName = "Counties";
                break;
            case 2: CategoryName = "Countries";
                break;
            case 3: CategoryName = "Cities";
                break;
            default: System.out.println("This should not happen!");
                break;
        }
        update();
        return CategoryName;
    }

    /**
     * Updates the gamer via the GUI with the current stats within the game
     * Remaining guesses, Total Guesses Made, Remaining Hints
     */
    public void updateGameStats(){
        updateCurrentPredictedChars();
        this.GameStats = "Remaining Guesses: "+ this.RemainingGuesses+"  "+
                         "Total Guesses Made: "+ this.TotalMadeGuesses+"  "+
                         "Remaining Hints: "+this.RemainingHints + "   ";
        update();
    }

    /**
     * Updates the GUI and hence gamer with their progress of the game
     * shows the 'charcters correctly predicted' and prints '-' to replace those not guessed
     */
    public void updateCurrentPredictedChars(){
        this.CurrentPredictedChars = "";
        for (int i = 0; i < ChosenWord.length(); ++i) {
            if (CharsGuessed.contains(ChosenWord.toLowerCase().charAt(i))) {
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

    /**
     * Sets and updates the user with Feedback messages
     * @param feedbackMessage Message for the player from the game
     */
    public void updateFeedbackMessage(String feedbackMessage){
        this.FeedbackMessage = feedbackMessage;
        System.out.println(FeedbackMessage);
        update();
    }

    /**
     * Passes and checks one character guess, if it exist as a character of the randomly chosen word
     * @param userChar the user's guessed alphabet/character
     * @return isCorrect True/False
     */
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

    /**
     * Returns a true/false value after checking if a user has WON the game or not.
     * @return True/False
     */
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

    /**
     * Returns a true/false value after checking if a user has LOST the game or not.
     * @return True/False
     */
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

    /**
     * Called when the gamer needs a hint.
     * Searches through remining guesses and gives the gamer a 'hint' if they have > 0 number of hints left
     * Reduces number of hints as a result
     */
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

    /**
     * Called to alert all Observer classes e.g. the GUI of changes
     * Sets all changes and notifies all Observer classes
     */
    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Reduces the Total Remaining Guesses by 1 when called
     */
    public void decreaseRemainingHints(){
        this.RemainingGuesses-=1;
    }

    /**
     * @return GameStats Updated 'score and stats' of the game
     */
    public String getGameStats(){
        return GameStats;
    }

    /**
     * @return FeedbackMessage Returns messages to gui when needed
     */
    public String getFeedbackMessage(){
        return FeedbackMessage;
    }

    /**
     * @return CurrentPredictedChars user predicted/guessed characters
     */
    public String getCurrentPredictedChars(){
        return CurrentPredictedChars;
    }

    /**
     * @return ChosenWord the currently generated random word being guessed
     */
    public String getChosenWord() {
        return ChosenWord;
    }

    /**
     * @return FileDirectory the directory of the custom chosen words file
     */
    public String getFileDirectory() {
        return FileDirectory;
    }

    /**
     * @return MESSAGE_CORRECT_GUESS A config message
     */
    public String getMessageCorrectGuess() {
        return MESSAGE_CORRECT_GUESS;
    }
    /**
     * @return MESSAGE_GAME_WON_GUESS A config message
     */
    public String getMessageGameWonGuess() {
        return MESSAGE_GAME_WON_GUESS;
    }
    /**
     * @return MESSAGE_HINT_GUESS A config message
     */
    public String getMessageHintGuess() {
        return MESSAGE_HINT_GUESS;
    }
    /**
     * @return MESSAGE_NO_HINTS_GUESS A config message
     */
    public String getMessageNoHintsGuess() {
        return MESSAGE_NO_HINTS_GUESS;
    }
    /**
     * @return MESSAGE_WRONG_GUESS A config message
     */
    public String getMessageWrongGuess() {
        return MESSAGE_WRONG_GUESS;
    }
}
