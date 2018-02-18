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

public class HangmanGUI implements Observer, ActionListener{

    private HangmanModelInterface model;
    private HangmanWordsController wController;
    private HangmanGameplayController gController;
    private HangmanViewer viewer;

    private JFrame hangmanFrame;
    private JPanel controlPanel;
    private JPanel viewPanel;

    private static int DEFAULT_FRAME_WIDTH = 400;
    private static int DEFAULT_FRAME_HEIGHT = 500;

    protected static String BUTTON_NEW_GAME_COMMAND = "New Game";
    protected static String BUTTON_HINT_COMMAND = "Hint ?";
    protected static String BUTTON_ADVANCE_COMMAND= "SUBMIT";
    protected static String BUTTON_DEFAULT_SOURCE_COMMAND = "DEFAULT";
    protected static String BUTTON_CUSTOM_SOURCE_COMMAND = "CUSTOM";
    protected static String BUTTON_SELECT_AND_UPLOAD_COMMAND = "CHOOSE FILE";

    protected static String[] DefaultCategories = {"COUNTIES","COUNTRIES","CITIES"};

    private JButton newGameButton;
    private JButton hintButton;
    private JButton advanceButton;
    private JButton chooseFileButton;
    private JButton customSourceButton;
    private JButton defaultSourceButton;

    private JTextField inputCharacterField;
    private JTextField outputViewField;


    public HangmanGUI(){
        this.model = new HangmanModel();

        hangmanFrame = new JFrame("HANGMAN");
        hangmanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hangmanFrame.setSize(DEFAULT_FRAME_WIDTH,DEFAULT_FRAME_HEIGHT);
        hangmanFrame.setVisible(true);

        controlPanel = new JPanel();
        viewPanel = new JPanel();

        hangmanFrame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        hangmanFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);

        addControlElements();
        addViewElements();

        addActionListenerForButtons(this);
        ((Observable)model).addObserver(this);


    }

    private void addActionListenerForButtons(ActionListener actionListener) {
        newGameButton.addActionListener(actionListener);
        hintButton.addActionListener(actionListener);
        defaultSourceButton.addActionListener(actionListener);
        customSourceButton.addActionListener(actionListener);
        advanceButton.addActionListener(actionListener);
        chooseFileButton.addActionListener(actionListener);
    }


    private void addControlElements(){
        newGameButton = new JButton(BUTTON_NEW_GAME_COMMAND);
        hintButton = new JButton(BUTTON_HINT_COMMAND);
        defaultSourceButton = new JButton(BUTTON_DEFAULT_SOURCE_COMMAND);
        customSourceButton = new JButton(BUTTON_CUSTOM_SOURCE_COMMAND);
        advanceButton = new JButton(BUTTON_ADVANCE_COMMAND);
        chooseFileButton = new JButton(BUTTON_SELECT_AND_UPLOAD_COMMAND);

        controlPanel.add(newGameButton);
        controlPanel.add(hintButton);
        controlPanel.add(defaultSourceButton);
        controlPanel.add(customSourceButton);
        controlPanel.add(advanceButton);
        controlPanel.add(chooseFileButton);
    }

    private void addViewElements(){
        inputCharacterField = new JTextField("hmm",10);
        inputCharacterField.setEditable(true);
        inputCharacterField.setVisible(true);
        outputViewField = new JTextField("lol new", 20 );
        outputViewField.setEditable(false);
        outputViewField.setVisible(true);

        viewPanel.add(inputCharacterField);
        viewPanel.add(outputViewField);

    }


    @Override
    public void update(Observable observable, Object object) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                outputViewField.setText("skrere");
                hangmanFrame.repaint();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == newGameButton ){
            outputViewField.setVisible(true);
            outputViewField.setText("2+2 is 4");
            System.out.println("New Game Pressed");
        }

    }
}
