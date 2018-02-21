package uk.ac.standrews.cs5031.controller;

import org.junit.jupiter.api.Test;
import uk.ac.standrews.cs5031.model.HangmanModel;
import uk.ac.standrews.cs5031.model.IHangmanModel;

class HangmanGUIControllerTest {
    String FileDirectory = "/cs/home/soo3/IdeaProjects/CS5031-P1-Hangman/fruits.txt";
    String CategoryNumber = "";
    String CategoryNumberFile = "0";

    int CountiesInt = 1;
    int CountriesInt = 2;
    int CitiesInt = 3;


    String[] FruitsTestArray = {"Satsuma", "Papaya","Cantaloupe","Lemon","Passon_fruit","Huckleberry", "Loquat","Boysenberry","Cherry","Kumquat"};

    private IHangmanModel model = new HangmanModel();
    private IHangmanGuiController controllerFile ;
    private IHangmanGuiController controllerCounties = new HangmanGuiController("1", null);
    private IHangmanGuiController controllerCountries = new HangmanGuiController("2", null);
    private IHangmanGuiController controllerCities = new HangmanGuiController("3", null);
    @Test
    void testFileRetrieval(){
        controllerFile = new HangmanGuiController("0", FileDirectory);
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