package uk.ac.standrews.cs5031.Controller;

import org.junit.jupiter.api.Test;
import uk.ac.standrews.cs5031.Hangman;

import static org.junit.jupiter.api.Assertions.*;

class HangmanWordsControllerTest {
    @Test
    void getWordFromCategory() throws Exception{
        HangmanWordsController hwc = new HangmanWordsController();
        if(hwc.getWordFromCategory(1).isEmpty())
        assert false;

    }

    @Test
    void getWordFromFile() {
    }

}