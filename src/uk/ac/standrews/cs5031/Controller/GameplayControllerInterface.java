package uk.ac.standrews.cs5031.Controller;

public interface GameplayControllerInterface {
    void showWord();
    boolean getNextGuess();
    boolean isGameWon();
    boolean isGameLost();
    void getHint();

    String showWordGUI();
    boolean getNextGuessGUI(char UserChar);
    boolean isGameWonGUI();
    boolean isGameLostGUI();
    void getHintGUI();

    int getRemainingGuessesGUI();

}
