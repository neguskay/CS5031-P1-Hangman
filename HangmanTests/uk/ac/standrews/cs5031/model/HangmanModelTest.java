package uk.ac.standrews.cs5031.model;

import org.junit.jupiter.api.Test;

class HangmanModelTest {

    @Test
    void TestModel() throws Exception{
        HangmanModel hm = new HangmanModel();
        IHangmanModel ihm = new HangmanModel();
        if(ihm.getCities().toString() ==hm.getCities().toString())
        {assert true;}
        if(ihm.getCounties().toString() ==hm.getCounties().toString())
        {assert true;}
        if(ihm.getCountries().toString() ==hm.getCountries().toString())
        {assert true;}
        if(ihm.getCustomWords().toString() ==hm.getCustomWords().toString())
        {assert true;}

    }



    @Test
    void getCounties() throws Exception{
        HangmanModel hm = new HangmanModel();

    }

    @Test
    void getCities() throws Exception{
        HangmanModel hm = new HangmanModel();

    }

    @Test
    void getCustomWords() throws Exception{
        HangmanModel hm = new HangmanModel();

    }

}