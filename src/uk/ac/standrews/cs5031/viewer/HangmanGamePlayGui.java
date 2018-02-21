package uk.ac.standrews.cs5031.viewer;

import uk.ac.standrews.cs5031.controller.HangmanGuiController;
import uk.ac.standrews.cs5031.controller.IHangmanGuiController;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 * HangmanGamePlayGUI Class.
 * Implements Observer, Action Listener and Key Listener.
 * This sets up and runs the 'Game play' part of the GUI.
 */
public class HangmanGamePlayGui implements Observer, ActionListener, KeyListener {
  
  private IHangmanGuiController controller;

  private JFrame gameFrame;

  private static int DEFAULT_FRAME_WIDTH = 400;
  private static int DEFAULT_FRAME_HEIGHT = 500;

  static final  int DIALOG_GAME_WON_ = 1;
  static final  int DIALOG_GAME_LOST = 2;
  static final  int DIALOG_GAME_QUIT = 3;
  static final  int DIALOG_GAME_RESET = 4;

  static final  String BUTTON_QUIT_GAME_COMMAND = "Quit Game";
  static final  String BUTTON_HINT_COMMAND = "Hint/Help";
  static final  String BUTTON_ADVANCE_COMMAND = "SUBMIT";
  static final  String BUTTON_START_COMMAND = "START GAME";
  static final  String BUTTON_CUSTOM_SOURCE_COMMAND = "CUSTOM";
  static final  String BUTTON_SELECT_AND_UPLOAD_COMMAND = "CHOOSE FILE";
  static final  String BUTTON_RESET_COMMAND = "RESET";
  static final  String BUTTON_FEEDBACK = "BUTTON PRESSED: ";
  static final  String[] DefaultCategories = {"", "COUNTIES", "COUNTRIES", "CITIES"};


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
  private JTextField currentOutputViewField;

  private Container wordSourceGrid = new Container();
  private Container startButtonGrid = new Container();
  private Container gameStatsGrid = new Container();
  private Container gamePlayControlsGrid = new Container();

  private JPanel statsAndFeedbackPanel = new JPanel();
  private JPanel viewCurrentOutputPanel = new JPanel();
  private JPanel gamePlayControlsPanel = new JPanel();

  String chosenWordsDirectory = "";

  private JFileChooser chooser;
  //private String ChosenRandomWord;

  /**
   * Sets up the graphics the GUI.
   *
   * @param controller An instance of the IHangmanGUIController as the controller for this GUI.
   */
  public HangmanGamePlayGui(IHangmanGuiController controller) {
    this.controller = controller;

    gameFrame = new JFrame("HANGMAN");
    gameFrame.setLayout(new BorderLayout());
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameFrame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

    gameFrame.setVisible(true);

    wordSourceGrid.setLayout(new GridLayout(1, 2));
    wordSourceGrid.add(categoriesDropdown);
    wordSourceGrid.add(chooseFileButton);

    startButtonGrid.setLayout(new GridLayout(1, 1));
    startButtonGrid.add(startGameButton);

    gameStatsGrid.setLayout(new GridLayout(5, 3));
    gameStatsGrid.add(gameStatsViewField, BorderLayout.EAST);
    gameStatsGrid.add(feedBackViewField, BorderLayout.WEST);

    addControlElements();
    addViewElements();

    gamePlayControlsPanel.add(gamePlayControlsGrid);

    gameFrame.getContentPane().add(statsAndFeedbackPanel, BorderLayout.NORTH);
    gameFrame.getContentPane().add(currentOutputViewField, BorderLayout.CENTER);
    gameFrame.getContentPane().add(gamePlayControlsPanel, BorderLayout.SOUTH);

    gameFrame.getRootPane().setDefaultButton(advanceButton);

    addActionListenerForButtons(this);
    ((Observable) controller).addObserver(this);//add controller

    gameFrame.paintAll(gameFrame.getGraphics());
    gameFrame.pack();
  }

  /**
   * Adds Action Listener to all the necessary objects to.
   *
   * @param actionListener The action listener for all buttons and text areas.
   */
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

  /**
   * Adds all the buttons and sets up the layout for all the control elements.
   */
  private void addControlElements() {
    gamePlayControlsGrid.setLayout(new GridLayout(2, 3));
    gamePlayControlsGrid.add(inputCharacterField);
    gamePlayControlsGrid.add(advanceButton);
    gamePlayControlsGrid.add(quitButton);
    gamePlayControlsGrid.add(hintButton);
    gamePlayControlsGrid.add(resetButton);
  }

  /**
   * Adds all text view areas and sets up their relative layout.
   */
  private void addViewElements() {
    statsAndFeedbackPanel.add(wordSourceGrid);
    statsAndFeedbackPanel.add(startButtonGrid);
    statsAndFeedbackPanel.add(gameStatsGrid);

    //currentOutputViewField.setSize(100, 100);
    currentOutputViewField = new JTextField(20);
    viewCurrentOutputPanel.add(currentOutputViewField);
    currentOutputViewField.setText("" + controller.getCurrentPredictedChars());
    currentOutputViewField.setEditable(false);

  }

  /**
   * The runnable method being implemented to keep the GUI updated.
   *
   * @param arg0 Observable class.
   * @param arg1 Observable object.
   */
  @Override
  public void update(Observable arg0, Object arg1) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        gameStatsViewField.setText("" + controller.getGameStats());
        feedBackViewField.setText("" + controller.getFeedbackMessage());
        currentOutputViewField.setText(controller.getCurrentPredictedChars());
        gameFrame.repaint();
      }
    });
  }

  /**
   * Performs several method calls depending on which buttons have been pressed.
   *
   * @param actionEvent Registers actions such as button clicks.
   */
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getSource() == chooseFileButton) {
      System.out.println(BUTTON_FEEDBACK + chooseFileButton.getLabel());
      getWordsFromCustomFile();
    }
    if (actionEvent.getSource() == categoriesDropdown) {
      System.out.println("Category selection: " + categoriesDropdown.getSelectedItem());
      setWordsCategory();
    }
    if (actionEvent.getSource() == startGameButton) {
      System.out.println(BUTTON_FEEDBACK + startGameButton.getLabel());
      initGamePlay();
    }
    if (actionEvent.getSource() == hintButton) {
      System.out.println(BUTTON_FEEDBACK + hintButton.getLabel());
      controller.getHint();
    }
    if (actionEvent.getSource() == quitButton) {
      System.out.println(BUTTON_FEEDBACK + quitButton.getLabel());
      runDialog(DIALOG_GAME_QUIT);
    }
    if (actionEvent.getSource() == resetButton) {
      System.out.println(BUTTON_FEEDBACK + resetButton.getLabel());
      runDialog(DIALOG_GAME_RESET);
    }
    if (actionEvent.getSource() == advanceButton) {

      runGamePlay();
    }


  }

  /**
   * Gets a file path from the GUI using the JFileChooser class.
   *
   * @return FilePath The location of the file in the file directories.
   */
  private String getCustomWordsDirectory() {
    String filePath = "";
    chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("Choose Words File to Upload");
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

    chooser.setAcceptAllFileFilterUsed(true);
    //
    if (chooser.showOpenDialog(gameFrame) == JFileChooser.APPROVE_OPTION) {
      System.out.println("getCurrentDirectory(): "
          + chooser.getCurrentDirectory());
      filePath = chooser.getSelectedFile().getPath();
      System.out.println("getSelectedFile() : "
          + chooser.getSelectedFile());
    } else {
      System.out.println("No Selection ");
    }
    return filePath;
  }

  /**
   * Runs a JOptionPane Dialog to confirm user's request.
   *
   * @param dialogOption an integer that determines what the JOption pane should show.
   */
  private void runDialog(int dialogOption) {
    String dialogMessage = "";
    String dialogTitle = "Game Over Dialog";

    switch (dialogOption) {
      case 1:
        dialogMessage = "You Won! New Game?";
        break;
      case 2:
        dialogMessage = "You Lost! NewGame?";
        break;
      case 3:
        dialogMessage = "Are You Sure?";
        break;
      case 4:
        dialogMessage = "Are You Sure?";
        break;
      default: System.out.println("No default action");
        break;
    }
    Object[] quitOptions = {"YES", "NO"};
    int response = JOptionPane.showOptionDialog(gameFrame, dialogMessage, dialogTitle,
          JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
          null, quitOptions, quitOptions[0]);
    if (response == JOptionPane.YES_OPTION) {
      if ((dialogOption == 1) || (dialogOption == 2)) {
        gameFrame.dispose();
        new HangmanGamePlayGui(new HangmanGuiController("1", null));
      }
      if (dialogOption == 3) {
        gameFrame.dispose();
        new HangmanGui();
      }
      if (dialogOption == 4) {
        gameFrame.dispose();
        new HangmanGamePlayGui(new HangmanGuiController("1", null));
      }
    }
  }

  /**
   * Gets a random word from the custom file by calling other methods.
   */
  private void getWordsFromCustomFile() {
    chosenWordsDirectory = getCustomWordsDirectory();
    feedBackViewField.setText(chosenWordsDirectory);
    //ChosenRandomWord = controller.getWordFromFile(ChosenWordsDirectory);

  }

  /**
   * Sets the chosen Default category of words array to choose the random word from.
   */
  private void setWordsCategory() {

  }

  /**
   * Initiates the game by disabling some of the buttons.
   */
  private void initGamePlay() {
    //CurrentOutput = "";
    categoriesDropdown.setVisible(false);
    chooseFileButton.setVisible(false);
    startGameButton.setVisible(false);

  }

  /**
   * Runs the game's logic.
   */
  private void runGamePlay() {
    System.out.println(BUTTON_FEEDBACK + advanceButton.getLabel());
    //currentOutputViewField.setEditable(true);
    //char UserIn = ' ';
    inputCharacterField.setEditable(true);
    String currentIn = inputCharacterField.getText();
    char userIn = currentIn.toLowerCase(Locale.getDefault()).charAt(0);
    checkSubmittedChar(userIn);
  }

  /**
   * checks if a submitted guess character is a valid character.
   *
   * @param userChar the submitted character guess.
   */
  public void checkSubmittedChar(char userChar) {
    if (!controller.isGameWon() && !controller.isGameLost()) {
      if (controller.isCharCorrect(userChar)) {
        controller.updateFeedbackMessage(controller.getMessageCorrectGuess());
      } else if (!controller.isCharCorrect(userChar)) {
        controller.decreaseRemainingHints();
        controller.updateFeedbackMessage(controller.getMessageWrongGuess());
      }

      controller.updateCurrentPredictedChars();
      controller.updateGameStats();
    }
    if (controller.isGameWon()) {
      runDialog(DIALOG_GAME_WON_);

    }
    if (controller.isGameLost()) {
      runDialog(DIALOG_GAME_LOST);
    }
    //update();
  }

  /**
   * @param keyEvent Listens to keyboard keys if typed.
   */
  @Override
  public void keyTyped(KeyEvent keyEvent) {

  }

  /**
   * @param keyEvent Listens to keyboard keys if pressed.
   */
  @Override
  public void keyPressed(KeyEvent keyEvent) {
    if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
      runGamePlay();
    }
  }

  /**
   * @param keyEvent Listens to keyboard keys if released after a press.
   */
  @Override
  public void keyReleased(KeyEvent keyEvent) {

  }
}
