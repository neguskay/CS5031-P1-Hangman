package uk.ac.standrews.cs5031.Controller;

public interface GameplayControllerInterface {
    void showWord();
    boolean getNextGuess();
    boolean isGameWon();
    boolean isGameLost();
    void getHint();
}
