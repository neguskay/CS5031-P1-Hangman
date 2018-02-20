package uk.ac.standrews.cs5031.Controller;

import org.junit.jupiter.api.Test;
import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.IHangmanModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HangmanGUIControllerTest {
    String FileDirectory = "/cs/home/soo3/IdeaProjects/CS5031-P1-Hangman/fruits.txt";
    String CategoryNumber = "";
    String CategoryNumberFile = "0";

    int CountiesInt = 1;
    int CountriesInt = 2;
    int CitiesInt = 3;


    String[] FruitsTestArray = {"Satsuma", "Papaya","Cantaloupe","Lemon","Passon_fruit","Huckleberry", "Loquat","Boysenberry","Cherry","Kumquat"};

    private IHangmanModel model = new HangmanModel();
    private IHangmanGUIController controllerFile ;
    private IHangmanGUIController controllerCounties = new HangmanGUIController("1", null);
    private IHangmanGUIController controllerCountries = new HangmanGUIController("2", null);
    private IHangmanGUIController controllerCities = new HangmanGUIController("3", null);
    @Test
    void testFileRetrieval(){
        controllerFile = new HangmanGUIController("0", FileDirectory);
        if (FruitsTestArray.toString().contains(controllerFile.getWordFromFile(FileDirectory))){
            assert true;
        }

    }
    @Test
    void testGetWordFromCategory() {
        //Test Counties Array

        if(model.getCounties().toString().contains(controllerCounties.getWordFromCategory(CountiesInt))){
            assert true;
        }
        //Test Countries Array
        if(model.getCountries().toString().contains(controllerCounties.getWordFromCategory(CountriesInt))){
            assert true;
        }
        //Test Cities Array
        if(model.getCities().toString().contains(controllerCounties.getWordFromCategory(CitiesInt))){
            assert true;
        }
    }



}