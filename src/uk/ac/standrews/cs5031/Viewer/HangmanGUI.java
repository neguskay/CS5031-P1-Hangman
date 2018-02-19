package uk.ac.standrews.cs5031.Viewer;

import uk.ac.standrews.cs5031.Controller.HangmanGameplayController;
import uk.ac.standrews.cs5031.Controller.HangmanWordsController;
import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.IHangmanModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class HangmanGUI implements ActionListener{

    private IHangmanModel model;
    private HangmanWordsController wController;
    private HangmanGameplayController gController;
    private HangmanViewer viewer;

    private JFrame hangmanFrame;
    private JPanel controlPanel;
    private JPanel viewPanel;

    private static int DEFAULT_FRAME_WIDTH = 300;
    private static int DEFAULT_FRAME_HEIGHT = 300;
    private static int DEFAULT_BUTTON_WIDTH = 100;
    private static int DEFAULT_BUTTON_HEIGHT = 20;
    private static int DEFAULT_TEXT_AREA_WIDTH = 100;
    private static int DEFAULT_TEXT_AREA_HEIGHT = 50;

    protected static String BUTTON_NEW_GAME_COMMAND = "New Game";
    protected static String BUTTON_INFO_COMMAND = "Hint and Help";


    protected static String BUTTON_FEEDBACK = "BUTTON PRESSED: ";

    private JButton newGameButton = new JButton(BUTTON_NEW_GAME_COMMAND);
    private JButton infoButton = new JButton(BUTTON_INFO_COMMAND);

    private JTextField inputCharacterField = new JTextField("");
    private JTextField outputViewField = new JTextField("");

    private Container welcomeGrid = new Container();
    private Container textGrid = new Container();


    public HangmanGUI(){
        this.model = new HangmanModel();

        hangmanFrame = new JFrame("HANGMAN");
        hangmanFrame.setLayout(new BorderLayout());
        hangmanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hangmanFrame.setSize(DEFAULT_FRAME_WIDTH,DEFAULT_FRAME_HEIGHT);

        hangmanFrame.setVisible(true);

        controlPanel = new JPanel();
        viewPanel = new JPanel();

        addActionListenerForButtons(this);


        welcomeGrid.setLayout(new GridLayout(3, 1));
        welcomeGrid.setSize(100,100);

        textGrid.setLayout(new GridLayout(1, 1));
        textGrid.setSize(500, 250);

        outputViewField.setVisible(true);
        outputViewField.setEditable(false);

        inputCharacterField.setVisible(true);
        inputCharacterField.setEditable(false);

        welcomeGrid.add(newGameButton);
        welcomeGrid.add(infoButton);

        textGrid.add(outputViewField);

        controlPanel.add(welcomeGrid, BorderLayout.NORTH);
        viewPanel.add(outputViewField);

        hangmanFrame.add(controlPanel, BorderLayout.NORTH);
        hangmanFrame.add(outputViewField,BorderLayout.CENTER);


    }

    private void addActionListenerForButtons(ActionListener actionListener) {
        newGameButton.addActionListener(actionListener);
        infoButton.addActionListener(actionListener);
    }


    private void addControlElements(){

    }

    private void addViewElements(){

    }

    private void initNewGame(){
        hangmanFrame.dispose();
        new HangmanGamePlayGUI();
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == newGameButton ){
            //outputViewField.setText("2+2 is 4, - 1 that's 3, QUICK MAFS ");
            initNewGame();
            System.out.println(BUTTON_FEEDBACK+ newGameButton.getLabel());
        }
        else if(actionEvent.getSource() == infoButton){
            outputViewField.setText("WELCOME TO HANG MAN");
            System.out.println(BUTTON_FEEDBACK+ infoButton.getLabel());
        }

    }
}
