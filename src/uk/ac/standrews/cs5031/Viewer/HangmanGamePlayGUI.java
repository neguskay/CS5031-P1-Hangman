package uk.ac.standrews.cs5031.Viewer;

import uk.ac.standrews.cs5031.Controller.GameplayControllerInterface;
import uk.ac.standrews.cs5031.Controller.HangmanGameplayController;
import uk.ac.standrews.cs5031.Controller.HangmanWordsController;
import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.HangmanModelInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class HangmanGamePlayGUI implements Observer, ActionListener {

    private HangmanModelInterface model;
    private HangmanWordsController wController = new HangmanWordsController();
    private GameplayControllerInterface gController;

    private JFrame gameFrame;

    private static int DEFAULT_FRAME_WIDTH = 400;
    private static int DEFAULT_FRAME_HEIGHT = 500;
    private static int DEFAULT_BUTTON_WIDTH = 100;
    private static int DEFAULT_BUTTON_HEIGHT = 20;
    private static int DEFAULT_TEXT_AREA_WIDTH = 100;
    private static int DEFAULT_TEXT_AREA_HEIGHT = 50;

    protected static String BUTTON_QUIT_GAME_COMMAND = "Quit Game";
    protected static String BUTTON_HINT_COMMAND = "Hint/Help";
    protected static String BUTTON_ADVANCE_COMMAND= "SUBMIT";
    protected static String BUTTON_START_COMMAND = "START GAME";
    protected static String BUTTON_CUSTOM_SOURCE_COMMAND = "CUSTOM";
    protected static String BUTTON_SELECT_AND_UPLOAD_COMMAND = "CHOOSE FILE";
    protected static String BUTTON_RESET_COMMAND = "RESET";

    protected static String BUTTON_FEEDBACK = "BUTTON PRESSED: ";
    protected static String[] DefaultCategories = {"", "COUNTIES","COUNTRIES","CITIES"};


    private JButton quitButton = new JButton(BUTTON_QUIT_GAME_COMMAND);
    private JButton hintButton = new JButton(BUTTON_HINT_COMMAND);
    private JButton advanceButton = new JButton(BUTTON_ADVANCE_COMMAND);
    private JButton chooseFileButton = new JButton(BUTTON_SELECT_AND_UPLOAD_COMMAND);
    private JButton customSourceButton = new JButton(BUTTON_CUSTOM_SOURCE_COMMAND);
    private JButton startGameButton = new JButton(BUTTON_START_COMMAND);
    private JButton resetButton = new JButton(BUTTON_RESET_COMMAND);

    private JComboBox categoriesDropdown = new JComboBox(DefaultCategories);

    private JTextField inputCharacterField = new JTextField("");
    private JTextField outputViewField = new JTextField("output");

    private Container wordSourceGrid = new Container();
    private Container gamerInputGrid = new Container();

    String ChosenRandomWord = "";
    String ChosenWordsDirectory = "";
    String CurrentOutput = "";

    private int RemaingGuesses;

    private JFileChooser chooser;

    public HangmanGamePlayGUI(){
        this.model = new HangmanModel();

        gameFrame = new JFrame("HANGMAN");
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(DEFAULT_FRAME_WIDTH,DEFAULT_FRAME_HEIGHT);

        gameFrame.setVisible(true);

        addActionListenerForButtons(this);


        wordSourceGrid.setLayout(new GridLayout(2, 2));
        wordSourceGrid.add(categoriesDropdown);
        wordSourceGrid.add(chooseFileButton);
        wordSourceGrid.add(startGameButton, BorderLayout.SOUTH);

        gamerInputGrid.setLayout(new GridLayout(2,3));
        gamerInputGrid.add(inputCharacterField);
        gamerInputGrid.add(advanceButton);
        gamerInputGrid.add(quitButton);
        gamerInputGrid.add(hintButton);
        gamerInputGrid.add(resetButton);

        gameFrame.add(wordSourceGrid,BorderLayout.NORTH);
        gameFrame.add(outputViewField, BorderLayout.CENTER);

        gameFrame.add(gamerInputGrid,BorderLayout.SOUTH);

        ((Observable)model).addObserver(this);
    }

    private void addActionListenerForButtons(ActionListener actionListener) {
        startGameButton.addActionListener(actionListener);
        customSourceButton.addActionListener(actionListener);
        advanceButton.addActionListener(actionListener);
        chooseFileButton.addActionListener(actionListener);
        quitButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
        categoriesDropdown.addActionListener(actionListener);
    }


    private void addControlElements(){

    }

    private void addViewElements(){

    }

    @Override
    public void update(Observable observable, Object object) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                gameFrame.repaint();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == chooseFileButton ) {
            System.out.println(BUTTON_FEEDBACK + chooseFileButton.getLabel());
            getWordsFromCustomFile();
        }
        if(actionEvent.getSource() == categoriesDropdown){
            System.out.println("Category selection: " + categoriesDropdown.getSelectedItem());
            setWordsCategory();
        }
        if(actionEvent.getSource() == startGameButton ){
            System.out.println(BUTTON_FEEDBACK+ startGameButton.getLabel());
            initGamePlay();
        }
        if(actionEvent.getSource() == advanceButton){
            System.out.println(BUTTON_FEEDBACK+ advanceButton.getLabel());
            runGamePlay();
        }
        if(actionEvent.getSource() == hintButton){
            System.out.println(BUTTON_FEEDBACK+ hintButton.getLabel());
        }
        if(actionEvent.getSource() == quitButton){
            System.out.println(BUTTON_FEEDBACK+ quitButton.getLabel());
            runQuitGameDialog();
        }
        if(actionEvent.getSource() == resetButton){
            System.out.println(BUTTON_FEEDBACK+ resetButton.getLabel());
            runQuitGameDialog();
        }

    }

    private String nullCategory(){
        return "Please Choose a Valid Category or Select Custom Words File to Start the game";
    }
    private String getCustomWordsDirectory(){
        String FilePath = "";
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose Words File to Upload");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(true);
        //
        if (chooser.showOpenDialog(gameFrame) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            FilePath = chooser.getSelectedFile().getPath();
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }
        return FilePath;
    }

    private void runQuitGameDialog(){
        Object[] QuitOptions = {"YES", "NO"};
        int Response = JOptionPane.showOptionDialog(gameFrame, "Are You Sure?", "Warning",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, QuitOptions, QuitOptions[0]);
        if(Response == JOptionPane.YES_OPTION){
            gameFrame.dispose();
            new HangmanGUI();
        }

    }

    private void getWordsFromCustomFile(){
        ChosenWordsDirectory = getCustomWordsDirectory();
        outputViewField.setText(ChosenWordsDirectory);
        ChosenRandomWord = wController.getWordFromFile(ChosenWordsDirectory);
    }

    private void setWordsCategory(){
        if(categoriesDropdown.getSelectedIndex() == 0){
            outputViewField.setText(nullCategory());
        }
        else {
            outputViewField.setText(categoriesDropdown.getSelectedItem().toString());
            ChosenRandomWord = wController.getWordFromCategory(categoriesDropdown.getSelectedIndex());
        }
    }

    private void initGamePlay(){
        CurrentOutput = "";
        categoriesDropdown.setVisible(false);
        chooseFileButton.setVisible(false);
        startGameButton.setVisible(false);
        outputViewField.setText("Enter a Character");
        gController = new HangmanGameplayController(ChosenRandomWord);
        this.RemaingGuesses = gController.getRemainingGuessesGUI();

        CurrentOutput += gController.showWordGUI()+ "\n"
                + " GUESS-REM: "+ gController.getRemainingGuessesGUI()+"\n"
                + "Enter a Guess \n";
        outputViewField.setText(CurrentOutput);
    }
    private void runGamePlay(){
        char UserIn = ' ';
        String CurrentIn = inputCharacterField.getText();;
        while(!gController.isGameLostGUI() && !gController.isGameWonGUI()){
            if((CurrentIn.isEmpty()) ||(CurrentIn.length()>1)){
                CurrentOutput+= "Guess one char at a time\n";
            }
            else {
                UserIn = CurrentIn.charAt(0);
                boolean isCorrect = gController.getNextGuessGUI(UserIn);

                if (isCorrect) {
                    CurrentOutput += "Correct Guess\n";
                } else if (!isCorrect) {
                    CurrentOutput += "Wrong Guess!";
                }


                CurrentOutput += gController.showWordGUI() + "\n"
                        + " GUESS-REM: " + RemaingGuesses + "\n"
                        + "Enter a Guess \n";
                outputViewField.setText(CurrentOutput);
            }

            if(gController.isGameWonGUI()){
                CurrentOutput += "Game Won\n";
                outputViewField.setText(CurrentOutput);
            }


        }

    }
}
