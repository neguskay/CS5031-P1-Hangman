package uk.ac.standrews.cs5031.Viewer;

import uk.ac.standrews.cs5031.Controller.*;
import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.HangmanModelInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

public class HangmanGamePlayGUI implements Observer, ActionListener, KeyListener {

    private HangmanModelInterface model;
    private IHangmanGUIController controller;

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

    private JTextField inputCharacterField = new JTextField(20);
    private JTextField gameStatsViewField = new JTextField(20);
    private JTextField feedBackViewField = new JTextField(20);
    private JTextField currentOutputViewField ;

    private Container wordSourceGrid = new Container();
    private Container startButtonGrid = new Container();
    private Container gameStatsGrid = new Container();
    private Container outputViewGrid = new Container();
    private Container gamePlayControlsGrid = new Container();

    private JPanel statsAndFeedbackPanel = new JPanel();
    private JPanel viewCurrentOutputPanel = new JPanel();
    private JPanel gamePlayControlsPanel = new JPanel();

    String ChosenRandomWord = "";
    String ChosenWordsDirectory = "";
    String CurrentOutput = "";

    private int RemaingGuesses;

    private JFileChooser chooser;

    public HangmanGamePlayGUI(){
        this.model = new HangmanModel();
        this.controller = new HangmanGUIController(1,null);

        gameFrame = new JFrame("HANGMAN");
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(DEFAULT_FRAME_WIDTH,DEFAULT_FRAME_HEIGHT);

        gameFrame.setVisible(true);




        wordSourceGrid.setLayout(new GridLayout(1, 2));
        wordSourceGrid.add(categoriesDropdown);
        wordSourceGrid.add(chooseFileButton);

        startButtonGrid.setLayout(new GridLayout(1, 1));
        startButtonGrid.add(startGameButton);

        gameStatsGrid.setLayout(new GridLayout(5, 3));
        gameStatsGrid.add(gameStatsViewField,BorderLayout.EAST);
        gameStatsGrid.add(feedBackViewField,BorderLayout.WEST);


        gamePlayControlsGrid.setLayout(new GridLayout(2,3));
        gamePlayControlsGrid.add(inputCharacterField);
        gamePlayControlsGrid.add(advanceButton);
        gamePlayControlsGrid.add(quitButton);
        gamePlayControlsGrid.add(hintButton);
        gamePlayControlsGrid.add(resetButton);

        statsAndFeedbackPanel.add(wordSourceGrid);
        statsAndFeedbackPanel.add(startButtonGrid);
        statsAndFeedbackPanel.add(gameStatsGrid);

        //currentOutputViewField.setSize(100, 100);
        currentOutputViewField = new JTextField( 20);
        viewCurrentOutputPanel.add(currentOutputViewField);
        currentOutputViewField.setText(""+controller.getCurrentPredictedChars());
        currentOutputViewField.setEditable(false);

        gamePlayControlsPanel.add(gamePlayControlsGrid);

        gameFrame.getContentPane().add(statsAndFeedbackPanel, BorderLayout.NORTH);
        gameFrame.getContentPane().add(currentOutputViewField, BorderLayout.CENTER);
        gameFrame.getContentPane().add(gamePlayControlsPanel, BorderLayout.SOUTH);

        gameFrame.getRootPane().setDefaultButton(advanceButton);

        addActionListenerForButtons(this);
            ((Observable)controller).addObserver(this);//add controller

        gameFrame.paintAll(gameFrame.getGraphics());
        gameFrame.pack();
    }

    private void addActionListenerForButtons(ActionListener actionListener) {
        startGameButton.addActionListener(actionListener);
        customSourceButton.addActionListener(actionListener);
        advanceButton.addActionListener(actionListener);
        chooseFileButton.addActionListener(actionListener);
        quitButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
        categoriesDropdown.addActionListener(actionListener);
        hintButton.addActionListener(actionListener);
    }


    private void addControlElements(){

    }

    private void addViewElements(){

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                gameStatsViewField.setText(""+controller.getGameStats());
                feedBackViewField.setText(""+controller.getFeedbackMessage());
                currentOutputViewField.setText(controller.getCurrentPredictedChars());
                gameFrame.repaint();
            }
        });
    }


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
        if(actionEvent.getSource() == hintButton){
            System.out.println(BUTTON_FEEDBACK+ hintButton.getLabel());
            controller.getHint();
        }
        if(actionEvent.getSource() == quitButton){
            System.out.println(BUTTON_FEEDBACK+ quitButton.getLabel());
            runQuitGameDialog();
        }
        if(actionEvent.getSource() == resetButton){
            System.out.println(BUTTON_FEEDBACK+ resetButton.getLabel());
            runQuitGameDialog();
        }
        if(actionEvent.getSource() == advanceButton){

                runGamePlay();
        }


    }

    //private String nullCategory(){
        //return "Please Choose a Valid Category or Select Custom Words File to Start the game";
    //}
    private String getCustomWordsDirectory(){
        String FilePath = "";
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose Words File to Upload");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

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
        feedBackViewField.setText(ChosenWordsDirectory);
        ChosenRandomWord = controller.getWordFromFile(ChosenWordsDirectory);

    }

    private void setWordsCategory(){
        if(categoriesDropdown.getSelectedIndex() == 0){
            //outputViewField.setText(nullCategory());
            controller = new HangmanGUIController(categoriesDropdown.getSelectedIndex(), this.ChosenWordsDirectory);
        }
        else if((categoriesDropdown.getSelectedIndex()>0)&& (categoriesDropdown.getSelectedIndex()<4)){
            controller = new HangmanGUIController(categoriesDropdown.getSelectedIndex(), null);
        }
    }

    private void initGamePlay(){
        CurrentOutput = "";
        categoriesDropdown.setVisible(false);
        chooseFileButton.setVisible(false);
        startGameButton.setVisible(false);

    }
    private void runGamePlay(){
        System.out.println(BUTTON_FEEDBACK+ advanceButton.getLabel());
        //currentOutputViewField.setEditable(true);
        //char UserIn = ' ';
        inputCharacterField.setEditable(true);
        String CurrentIn = inputCharacterField.getText();
        char UserIn = CurrentIn.charAt(0);

        controller.checkSubmittedChar(UserIn);


    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
            runGamePlay();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
