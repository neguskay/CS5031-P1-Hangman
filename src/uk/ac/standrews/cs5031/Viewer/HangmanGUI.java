package uk.ac.standrews.cs5031.Viewer;

import uk.ac.standrews.cs5031.Controller.HangmanGUIController;
import uk.ac.standrews.cs5031.Controller.IHangmanGUIController;
import uk.ac.standrews.cs5031.Model.HangmanModel;
import uk.ac.standrews.cs5031.Model.IHangmanModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanGUI implements ActionListener{

    private IHangmanGUIController controller;

    private JFrame hangmanFrame;
    private JPanel controlPanel;
    private JPanel viewPanel;

    private static int DEFAULT_FRAME_WIDTH = 400;
    private static int DEFAULT_FRAME_HEIGHT = 500;
    private static int DEFAULT_BUTTON_WIDTH = 100;
    private static int DEFAULT_BUTTON_HEIGHT = 20;
    private static int DEFAULT_TEXT_AREA_WIDTH = 100;
    private static int DEFAULT_TEXT_AREA_HEIGHT = 50;

    protected static String BUTTON_NEW_GAME_DEFAULT_WORDS_COMMAND = "New Game - Default Words";
    protected static String BUTTON_NEW_GAME_CUSTOM_WORDS_COMMAND = "New Game - Custom Words";
    protected static String BUTTON_INFO_COMMAND = "Info/Help";

    protected static String BUTTON_FEEDBACK = "BUTTON PRESSED: ";

    private JButton newGameDefaultWordsButton = new JButton(BUTTON_NEW_GAME_DEFAULT_WORDS_COMMAND);
    private JButton newGameCustomWordsButton = new JButton(BUTTON_NEW_GAME_CUSTOM_WORDS_COMMAND);
    private JButton infoButton = new JButton(BUTTON_INFO_COMMAND);

    private JTextField inputCharacterField = new JTextField("");
    private JTextField outputViewField = new JTextField("");

    private Container welcomeGrid = new Container();
    private Container textGrid = new Container();


    public HangmanGUI(){
        hangmanFrame = new JFrame("HANGMAN");
        hangmanFrame.setLayout(new BorderLayout());
        hangmanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hangmanFrame.setSize(DEFAULT_FRAME_WIDTH,DEFAULT_FRAME_HEIGHT);

        hangmanFrame.setVisible(true);

        controlPanel = new JPanel();
        viewPanel = new JPanel();

        addActionListenerForButtons(this);

        addControlElements();
        addViewElements();

        hangmanFrame.paintAll(hangmanFrame.getGraphics());
        hangmanFrame.pack();
    }

    private void addActionListenerForButtons(ActionListener actionListener) {
        newGameDefaultWordsButton.addActionListener(actionListener);
        newGameCustomWordsButton.addActionListener(actionListener);
        infoButton.addActionListener(actionListener);
    }


    private void addControlElements(){

        welcomeGrid.setLayout(new GridLayout(5, 4));
        //welcomeGrid.setSize(100,100);

        welcomeGrid.add(newGameDefaultWordsButton);
        welcomeGrid.add(newGameCustomWordsButton);
        welcomeGrid.add(infoButton);

        controlPanel.add(welcomeGrid, BorderLayout.NORTH);
        hangmanFrame.getContentPane().add(controlPanel, BorderLayout.NORTH);
    }

    private void addViewElements(){
        textGrid.setLayout(new GridLayout(5, 4));
        //textGrid.setSize(500, 250);

        outputViewField.setVisible(true);
        outputViewField.setEditable(false);

        textGrid.add(outputViewField);
        textGrid.add(outputViewField);
        viewPanel.add(textGrid);

        hangmanFrame.getContentPane().add(outputViewField,BorderLayout.CENTER);
    }

    private void initNewGame(IHangmanGUIController controller){
        hangmanFrame.dispose();
        new HangmanGamePlayGUI(controller);
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == newGameDefaultWordsButton ){
            //outputViewField.setText("2+2 is 4, - 1 that's 3, QUICK MAFS ");
            controller = new HangmanGUIController("1",null );
            initNewGame(controller);
            System.out.println(BUTTON_FEEDBACK+ newGameDefaultWordsButton.getLabel());
        }
        if (actionEvent.getSource() == newGameCustomWordsButton ){
            //outputViewField.setText("2+2 is 4, - 1 that's 3, QUICK MAFS ");
            controller = new HangmanGUIController("0",null );
            initNewGame(controller);
            System.out.println(BUTTON_FEEDBACK+ newGameCustomWordsButton.getLabel());
        }

        if(actionEvent.getSource() == infoButton){
            outputViewField.setText("WELCOME TO HANG MAN");
            System.out.println(BUTTON_FEEDBACK+ infoButton.getLabel());
        }

    }
}
