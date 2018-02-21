package uk.ac.standrews.cs5031.controller;

import uk.ac.standrews.cs5031.model.HangmanModel;
import uk.ac.standrews.cs5031.model.IHangmanModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Random;

/**
 * controller Class of the MVC
 * Implements the IHangmanGUIController interface
 * Extends the Observable class and updates the GUI classes within the viewer Package
 * Methods within this class are accessed via the interface stated above to control the game.
 * Gets the Countries,Counties and Cities from the model Interface
 * Checks against inputs from the GUIs.
 * Determines how and when the game is played/advanced/won
 */
public class HangmanGuiController extends Observable implements IHangmanGuiController {
  public String gameStats;
  public String feedbackMessage;
  public String currentPredictedChars;

  public int remainingGuesses;
  public int totalMadeGuesses;
  public int remainingHints;

  private int categoryNumber;

  public String fileDirectory;
  public String chosenWord;

  static final String MESSAGE_CORRECT_GUESS = "Right Guess!";
  static final String MESSAGE_WRONG_GUESS = "Wrong Guess!";
  static final String MESSAGE_HINT_GUESS = "Try: ";
  static final String MESSAGE_NO_HINTS_GUESS = "You used all hints!";
  static final String MESSAGE_GAME_WON_GUESS = "You used all hints!";

  public ArrayList<Character> charsGuessed = new ArrayList<Character>();
  public ArrayList<Character> charsRemaining = new ArrayList<Character>();

  String[] countries;
  String[] counties;
  String[] cities;
  ArrayList<String> customWords = new ArrayList<String>();

  private IHangmanModel model = new HangmanModel();

  /**
   * The Controller which initiates the controller class.
   * @param comboBoxIndex Item number from the Combo box used to select the default array of words.
   * @param fileDirectory File Directory of the file containing the custom words.
   */
  public HangmanGuiController(String comboBoxIndex, String fileDirectory) {
    this.countries = model.getCountries();
    this.counties = model.getCounties();
    this.cities = model.getCities();
    this.customWords = model.getCustomWords();

    this.categoryNumber = Integer.parseInt(comboBoxIndex);
    this.fileDirectory = fileDirectory;

    this.chosenWord = setChosenWord(categoryNumber);
    this.gameStats = "";
    this.currentPredictedChars = "";

    this.totalMadeGuesses = 0;
    this.remainingGuesses = initRemainingGuesses(chosenWord);
    this.remainingHints = 3;
    update();
    initCharsLists();
  }

  /**
   * Initiates the value of the number of guesses the user gets to play with within the game.
   *
   * @param chosenWord Word to be guessed.
   * @return length of chosen word/2 as the number of remaining guesses.
   */
  public int initRemainingGuesses(String chosenWord) {
    update();
    return chosenWord.length() / 2;
  }

  /**
   * Initialises the following lists.
   * CharsRemaining contains characters of the chosen word that have yet to be guessed.
   * CharsGuessed contains characters of the chosen word which have been guessed correctly.
   */
  public void initCharsLists() {
    this.charsRemaining.clear();
    this.charsGuessed.clear();
    //Locale locale = new Locale(chosenWord);
    for (int i = 0; i < chosenWord.length(); ++i) {
      if (!charsRemaining.contains(chosenWord.toLowerCase(Locale.getDefault()).charAt(i))) {
        this.charsRemaining.add(chosenWord.toLowerCase(Locale.getDefault()).charAt(i));
      }
    }
    System.out.println("Characters To Be Guessed: " + charsRemaining.toString());
    System.out.println("Characters Guessed: " + charsGuessed.toString() + "check: Is is empty?");
    update();
  }

  /**
   * Gets and sets the Chosen 'random' word to be guessed within the game.
   *
   * @param category A number value corresponding to a category.
   * @return ChosenWord the word to be guessed.
   */
  public String getWordFromCategory(int category) {
    System.out.println();
    System.out.println("Getting word from set category");
    System.out.println();
    String chosenWord = "";
    Random random = new Random();
    switch (category) {
      case 1:
        chosenWord = counties[random.nextInt(counties.length)];
        break;
      case 2:
        chosenWord = countries[random.nextInt(countries.length)];
        break;
      case 3:
        chosenWord = cities[random.nextInt(cities.length)];
        break;
      default:
        System.out.println("This won't hapopen");
        break;

    }
    update();
    return chosenWord;
  }

  /**
   * Gets and sets a chosen random word after retrieving the said word from a given file
   *
   * @param fileName File directory from which the word will be retrieved
   * @return a chosen word.
   */
  public String getWordFromFile(String fileName) {
    String line;//change line to a suitable name
    BufferedReader reader = null;
    try {
      //FileInputStream fis = new FileInputStream(fileName);
      //InputStreamReader isr = new InputStreamReader(fis);
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
      while ((line = reader.readLine()) != null) {
        customWords.add(line);
      }
      update();
      return customWords.get((int) (Math.random() * customWords.size()));
    } catch (FileNotFoundException exception) {
      System.out.println("File error");
      System.out.println("File Was Not Found");
      return "";
    } catch (IOException exception) {
      System.out.println("IO error");
      System.out.println("File Invalid");
      return "";
    } finally {
      try {
        if ( reader != null) {
          System.out.println("Closing the Pipe");
          reader.close();
        }
      } catch (IOException exception) {
        System.out.println(exception.getMessage());
      }

    }
  }

  /**
   * Determines the source of the random word
   * I.E. either from a 'custom user-determined' file or from the default words arrays
   *
   * @param comboBoxIndex the number of the item chosen in the combo box
   * @return Word the randomly chosen word
   */
  public String setChosenWord(int comboBoxIndex) {
    String word = "";
    if ((comboBoxIndex == 0) && (!(fileDirectory == null))) {
      word = getWordFromFile(fileDirectory);
      System.out.println("Retrieving Chosen Word from file: " + fileDirectory);
    } else if ((comboBoxIndex > 0) && (comboBoxIndex < 4)) {
      word = getWordFromCategory(categoryNumber);
      System.out.println("Retrieving Chosen Word From: " + getCategoryName(categoryNumber));
    }
    System.out.println("Setting Chosen Word");
    update();
    return word;
  }

  /**
   * Determines the category name chosen based on the category number associated with it.
   *
   * @param categoryNumber Counties = 1, Countries = 2, Cities = 3.
   * @return CategoryName Name of category.
   */
  public String getCategoryName(int categoryNumber) {
    String categoryName = "";
    switch (categoryNumber) {
      case 1:
        categoryName = "Counties";
        break;
      case 2:
        categoryName = "Countries";
        break;
      case 3:
        categoryName = "Cities";
        break;
      default:
        System.out.println("This should not happen!");
        break;
    }
    update();
    return categoryName;
  }

  /**
   * Updates the gamer via the GUI with the current stats within the game.
   * Remaining guesses, Total Guesses Made, Remaining Hints.
   */
  public void updateGameStats() {
    updateCurrentPredictedChars();
    this.gameStats = "Remaining Guesses: " + this.remainingGuesses + "  "
      + "Total Guesses Made: " + this.totalMadeGuesses + "  "
      + "Remaining Hints: " + this.remainingHints + "   ";
    update();
  }

  /**
   * Updates the GUI and hence gamer with their progress of the game.
   * shows the 'charcters correctly predicted' and prints '-' to replace those not guessed.
   */
  public void updateCurrentPredictedChars() {
    this.currentPredictedChars = "";
    for (int i = 0; i < chosenWord.length(); ++i) {
      if (charsGuessed.contains(chosenWord.toLowerCase(Locale.getDefault()).charAt(i))) {
        System.out.print(chosenWord.charAt(i));
        this.currentPredictedChars += chosenWord.charAt(i);
      } else {
        System.out.print("-");
        this.currentPredictedChars += "-";
      }
    }
    System.out.println("");
    update();
  }

  /**
   * Sets and updates the user with Feedback messages.
   *
   * @param feedbackMessage Message for the player from the game.
   */
  public void updateFeedbackMessage(String feedbackMessage) {
    this.feedbackMessage = feedbackMessage;
    System.out.println(feedbackMessage);
    update();
  }

  /**
   * Passes and checks one character guess, if it exist as a character of the randomly chosen word.
   *
   * @param userChar the user's guessed alphabet/character.
   * @return isCorrect True/False.
   */
  public boolean isCharCorrect(char userChar) {
    System.out.println("Checking Char: " + userChar);
    boolean isCorrect = false;
    for (int i = 0; i < charsRemaining.size(); ++i) { // Loop over the not got
      if (charsRemaining.get(i) == userChar) {
        charsRemaining.remove(i);
        charsGuessed.add(userChar);
        isCorrect = true;
      }
    }
    this.totalMadeGuesses += 1;
    update();
    return isCorrect;
  }

  /**
   * Returns a true/false value after checking if a user has WON the game or not.
   *
   * @return True/False.
   */
  public boolean isGameWon() {
    boolean gameWon;
    if ((charsRemaining.size() == 0) && (!(this.remainingGuesses < 0))) {
      gameWon = true;
    } else {
      gameWon = false;
    }
    update();
    return gameWon;
  }

  /**
   * Returns a true/false value after checking if a user has LOST the game or not.
   *
   * @return True/False.
   */
  public boolean isGameLost() {
    boolean gameLost;
    if ((charsRemaining.size() > 0) && ((this.remainingGuesses <= 0))) {
      gameLost = true;
    } else {
      gameLost = false;
    }
    update();
    return gameLost;
  }

  /**
   * Called when the gamer needs a hint.
   * Searches through remining guesses and gives the gamer a 'hint'.
   * if they have > 0 number of hints left.
   * Reduces number of hints as a result.
   */
  public void getHint() {
    if (!(remainingHints < 1)) {
      updateFeedbackMessage(MESSAGE_HINT_GUESS
          + chosenWord.charAt((int) (Math.random() * chosenWord.length())));
      remainingHints -= 1;
    } else {
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
   * Reduces the Total Remaining Guesses by 1 when called.
   */
  public void decreaseRemainingHints() {
    this.remainingGuesses -= 1;
  }

  /**
   * @return GameStats Updated 'score and stats' of the game.
   */
  public String getGameStats() {
    return gameStats;
  }

  /**
   * @return FeedbackMessage Returns messages to gui when needed.
   */
  public String getFeedbackMessage() {
    return feedbackMessage;
  }

  /**
   * @return CurrentPredictedChars user predicted/guessed characters.
   */
  public String getCurrentPredictedChars() {
    return currentPredictedChars;
  }

  /**
   * @return ChosenWord the currently generated random word being guessed.
   */
  public String getChosenWord() {
    return chosenWord;
  }

  /**
   * @return FileDirectory the directory of the custom chosen words file.
   */
  public String getFileDirectory() {
    return fileDirectory;
  }

  /**
   * @return MESSAGE_CORRECT_GUESS A config message.
   */
  public String getMessageCorrectGuess() {
    return MESSAGE_CORRECT_GUESS;
  }

  /**
   * @return MESSAGE_GAME_WON_GUESS A config message.
   */
  public String getMessageGameWonGuess() {
    return MESSAGE_GAME_WON_GUESS;
  }

  /**
   * @return MESSAGE_HINT_GUESS A config message.
   */
  public String getMessageHintGuess() {
    return MESSAGE_HINT_GUESS;
  }

  /**
   * @return MESSAGE_NO_HINTS_GUESS A config message.
   */
  public String getMessageNoHintsGuess() {
    return MESSAGE_NO_HINTS_GUESS;
  }

  /**
   * @return MESSAGE_WRONG_GUESS A config message.
   */
  public String getMessageWrongGuess() {
    return MESSAGE_WRONG_GUESS;
  }
}
