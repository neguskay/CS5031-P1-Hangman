package uk.ac.standrews.cs5031.Controller;

public interface IHangmanGUIController {
    public abstract String getWordFromFile(String FileName);
    public abstract void checkSubmittedChar(char userChar);

    //public abstract void update();
    public abstract void getHint();
    public abstract boolean isGameLost();
    public abstract boolean isGameWon();
    public abstract String getGameStats();
    public abstract String getFeedbackMessage();
    public abstract String getCurrentPredictedChars();
}
