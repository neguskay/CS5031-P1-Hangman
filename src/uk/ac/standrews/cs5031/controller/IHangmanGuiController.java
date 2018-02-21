package uk.ac.standrews.cs5031.controller;


/**
 * HangmanGUIController's interface.
 * Implemented by HangmanGUIController Class
 */
public interface IHangmanGuiController {

  void getHint();

  boolean isGameLost();

  boolean isGameWon();

  boolean isCharCorrect(char inputChar);

  void updateFeedbackMessage(String feedbackmessage);

  void decreaseRemainingHints();

  void updateCurrentPredictedChars();

  void updateGameStats();

  String getGameStats();

  String getFeedbackMessage();

  String getCurrentPredictedChars();

  String getMessageCorrectGuess();

  String getMessageGameWonGuess();

  String getMessageHintGuess();

  String getMessageNoHintsGuess();

  String getMessageWrongGuess();

  String getWordFromFile(String fileName);

  String getWordFromCategory(int category);
}
