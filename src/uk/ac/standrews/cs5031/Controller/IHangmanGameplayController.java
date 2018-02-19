package uk.ac.standrews.cs5031.Controller;

public interface IHangmanGameplayController {
    boolean getNextGuess();
    boolean isGameWon();
    boolean isGameLost();
    void getHint();

}
