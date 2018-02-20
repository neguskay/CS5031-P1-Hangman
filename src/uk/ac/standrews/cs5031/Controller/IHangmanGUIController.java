package uk.ac.standrews.cs5031.Controller;

public interface IHangmanGUIController {
    public abstract String getWordFromFile(String FileName);
    public abstract String getWordFromCategory(int Category);
    //public abstract void update();
    public abstract void getHint();

    public abstract boolean isGameLost();

    public abstract boolean isGameWon();

    public abstract boolean isCharCorrect(char InputChar);

    public abstract String getGameStats();

    public abstract void updateFeedbackMessage(String Feedbackmessage);

    public abstract String getFeedbackMessage();

    public abstract String getCurrentPredictedChars();

    public abstract void decreaseRemainingHints();

    public abstract void updateCurrentPredictedChars();

    public abstract void updateGameStats();

    public abstract String getMessageCorrectGuess();

    public abstract String getMessageGameWonGuess();
    public abstract String getMessageHintGuess();
    public abstract String getMessageNoHintsGuess();

    public abstract String getMessageWrongGuess();
}
