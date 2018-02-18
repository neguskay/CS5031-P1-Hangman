package uk.ac.standrews.cs5031.Controller;

import org.junit.jupiter.api.Test;

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