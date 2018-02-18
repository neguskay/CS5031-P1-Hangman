package uk.ac.standrews.cs5031.Viewer;

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
    private HangmanWordsController wController;
    private HangmanGameplayController gController;
    private HangmanViewer viewer;

    private JFrame gameFrame;
    private JPanel controlPanel;
    private JPanel viewPanel;

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

    protected static String BUTTON_FEEDBACK = "BUTTON PRESSED: ";
    protected static String[] DefaultCategories = {"", "COUNTIES","COUNTRIES","CITIES"};


    private JButton quitButton = new JButton(BUTTON_QUIT_GAME_COMMAND);
    private JButton hintButton = new JButton(BUTTON_HINT_COMMAND);
    private JButton advanceButton = new JButton(BUTTON_ADVANCE_COMMAND);
    private JButton chooseFileButton = new JButton(BUTTON_SELECT_AND_UPLOAD_COMMAND);
    private JButton customSourceButton = new JButton(BUTTON_CUSTOM_SOURCE_COMMAND);
    private JButton startGameButton = new JButton(BUTTON_START_COMMAND);

    private JComboBox categoriesDropdown = new JComboBox(DefaultCategories);

    private JTextField inputCharacterField = new JTextField("input");
    private JTextField outputViewField = new JTextField("output");

    private Container wordSourceGrid = new Container();
    private Container gamerInputGrid = new Container();


    public HangmanGamePlayGUI(){
        this.model = new HangmanModel();

        gameFrame = new JFrame("HANGMAN");
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(DEFAULT_FRAME_WIDTH,DEFAULT_FRAME_HEIGHT);

        gameFrame.setVisible(true);

        controlPanel = new JPanel();
        viewPanel = new JPanel();

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
                //outputViewField.setText("skrere");
                gameFrame.repaint();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == chooseFileButton ) {
            System.out.println(BUTTON_FEEDBACK + chooseFileButton.getLabel());
            chooseFileButton.setVisible(false);
        }
        if(actionEvent.getSource() == categoriesDropdown){
            if(categoriesDropdown.getSelectedItem() == DefaultCategories[0]){
                outputViewField.setText("lollll");
            }
            else if(categoriesDropdown.getSelectedItem() == DefaultCategories[1]){
                System.out.println("Category Selected: "+ DefaultCategories[1]);
            }

        }
        if(actionEvent.getSource() == startGameButton ){
            categoriesDropdown.setVisible(false);
            chooseFileButton.setVisible(false);
            startGameButton.setVisible(false);

        }
        if(actionEvent.getSource() == advanceButton){

        }
        if(actionEvent.getSource() == hintButton){

        }

    }
}
