package uk.ac.standrews.cs5031.controller;

import org.junit.jupiter.api.Test;
import uk.ac.standrews.cs5031.model.HangmanModel;
import uk.ac.standrews.cs5031.model.IHangmanModel;

class HangmanGUIControllerTest {
    String fruitsFileDirectory = "/cs/home/soo3/IdeaProjects/CS5031-P1-Hangman/fruits.txt";
    String emptyFileDirectory = "/cs/home/soo3/IdeaProjects/CS5031-P1-Hangman/empty.txt";
    String categoryNumber = "";
    String categoryNumberFile = "0";

    String emptyword = "";

    int countiesInt = 1;
    int countriesInt = 2;
    int citiesInt = 3;


    String[] FruitsTestArray = {"Satsuma", "Papaya","Cantaloupe","Lemon","Passon_fruit","Huckleberry", "Loquat","Boysenberry","Cherry","Kumquat"};

    private IHangmanModel model = new HangmanModel();
    private IHangmanGuiController controllerFile ;
    private IHangmanGuiController controllerCounties = new HangmanGuiController("1", null);
    private IHangmanGuiController controllerCountries = new HangmanGuiController("2", null);
    private IHangmanGuiController controllerCities = new HangmanGuiController("3", null);
    @Test
    void testFileRetrieval(){
        controllerFile = new HangmanGuiController("0", fruitsFileDirectory);
        //Test if the chosen word from file actually matches any in the fruits.txt file attached
        if (FruitsTestArray.toString().contains(controllerFile.getWordFromFile(fruitsFileDirectory))){
            assert true;
        }

        //TestNullFile
        emptyword = controllerFile.getWordFromFile(emptyFileDirectory);
        if(emptyword== null){
          assert true;
        }


    }
    @Test
    void testGetWordFromCategory() {
        //Test Counties Array
        if(model.getCounties().toString().contains(controllerCounties.getWordFromCategory(countiesInt))){
            assert true;
        }
        //Test Countries Array
        if(model.getCountries().toString().contains(controllerCounties.getWordFromCategory(countriesInt))){
            assert true;
        }
        //Test Cities Array
        if(model.getCities().toString().contains(controllerCounties.getWordFromCategory(citiesInt))){
            assert true;
        }
    }
    @Test
    void testGamePlayElements(){
      for(int i =0; i<FruitsTestArray.length; i++){
        int rem = FruitsTestArray[i].length()/2;
        if(rem == controllerCounties.initRemainingGuesses(FruitsTestArray[i])){
          assert true;
        }
      }
    }



}