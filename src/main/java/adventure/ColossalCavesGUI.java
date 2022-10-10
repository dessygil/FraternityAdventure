package adventure;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.json.simple.parser.ParseException;

import java.awt.Container;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ColossalCavesGUI extends JFrame {

  public static final int WIDTH = 450;
  public static final int HEIGHT = 650;
  private Game controller;
  private Container contentPane;
  private JTextArea outputArea;
  private JTextArea itemOutput;
  private JTextArea nameArea;
  private JTextField inputArea;
  private JTextField adventureField;
  private JTextField saveField;
  private JTextField nameField;
  private String userName;

  /**
   * 
   * @param game
   */
  public ColossalCavesGUI(Game game) {
    super();
    userName = "default";
    controller = new Game();
    setUpSize();
    setMainContainer();
  }

  /**
   * 
   */
  public void setUpSize() {
    this.setSize(HEIGHT, WIDTH);
    this.setTitle("Desmond Gilmour's Colossal Caves Adventure");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * 
   */
  public void setMainContainer() {
    contentPane = getContentPane();
    contentPane.setLayout(new FlowLayout());
    contentPane.add(setUpNameArea());
    contentPane.add(setUpMenu());
    contentPane.add(setUpPanelForCommandsAndInventory());
    contentPane.add(setUpPanelForMainText());
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setUpNameArea() {
    JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
    final int characterCount = 20;
    this.nameArea = new JTextArea(1, characterCount);
    nameArea.setEditable(false);
    listPane.add(nameArea);

    return listPane;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setUpPanelForCommandsAndInventory() {
    JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.X_AXIS));
    listPane.add(setInputTextField());
    listPane.add(setItemTextArea());
    return listPane;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setInputTextField() {
    JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

    this.inputArea = new JTextField();
    inputArea.setEditable(true);
    inputArea.setText("Please enter you commands here.");
    inputArea.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String cmdString = inputArea.getText();

        Parser gameParser = new Parser();
        try {
          Command gameCommand = gameParser.parseUserCommand(cmdString);

          if (gameCommand.getActionWord().equals("quit")) {
            System.exit(0);
          }
          outputArea.append("\n");
          outputArea.append(controller.runGame(controller, gameCommand));
          itemOutput.setText(controller.getAdventureObj().getPlayer().printItemList());
        } catch (InvalidCommandException xcpt) {
          JOptionPane.showInputDialog(this, xcpt);
        } catch (NullPointerException xcpt) {
          JOptionPane.showInputDialog(this, xcpt);
        }
      }
    });

    JLabel jLabel = new JLabel("Input for commands:");

    listPane.add(jLabel);
    listPane.add(inputArea);

    return listPane;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setItemTextArea() {
    JPanel listPane = makeJpanel();
    
    final int characterCount = 20;
    final int lineCount = 5;

    this.itemOutput = new JTextArea(lineCount, characterCount);
    itemOutput.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(itemOutput);

    JLabel jLabel = new JLabel("Inventory list:");

    listPane.add(jLabel);
    listPane.add(scrollPane);

    return listPane;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setUpPanelForMainText() {
    JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
    listPane.add(setMainTextArea());
    return listPane;
  }

  /**
   * 
   * @return Jpanel
   */
  public JPanel makeJpanel() {
    JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
    return listPane;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setMainTextArea() {
    JPanel listPane = makeJpanel();

    final int characterCount = 50;
    final int lineCount = 15;

    this.outputArea = new JTextArea(lineCount, characterCount);
    outputArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(outputArea);

    JLabel jLabel = new JLabel("Output for the game:");

    listPane.add(jLabel);
    listPane.add(scrollPane);

    return listPane;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setUpMenu() {
    JPanel listPane = new JPanel();
    listPane.setLayout(new BoxLayout(listPane, BoxLayout.X_AXIS));

    listPane.add(setUpLoadGame());
    listPane.add(setUpSaveGame());
    listPane.add(setUpName());

    return listPane;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setUpName() {

    JPanel listPaneName = new JPanel();
    listPaneName.setLayout(new BoxLayout(listPaneName, BoxLayout.PAGE_AXIS));

    this.nameField = new JTextField();
    nameField.setEditable(true);
    nameField.setText("Please enter player name");
    nameField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        userName = nameField.getText();
        nameArea.setText(userName);
        if (controller.getAdventureObj() != null) {
          controller.getAdventureObj().getPlayer().setName(userName);
        }
      }
    });

    JLabel jLabelName = new JLabel("Change name:");

    listPaneName.add(jLabelName);
    listPaneName.add(nameField);

    return listPaneName;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setUpSaveGame() {

    JPanel listPaneSave = makeJpanel();
    this.saveField = new JTextField();

    saveField.setEditable(true);
    saveField.setText("Hit enter to save as current name");
    saveField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Serialization
        try {
          // Saving of object in a file
          FileOutputStream outPutStream = new FileOutputStream(controller.getAdventureObj().getPlayer().getName());
          ObjectOutputStream outPutDest = new ObjectOutputStream(outPutStream);

          // Method for serialization of object
          outPutDest.writeObject(controller.getAdventureObj());

          outPutDest.close();
          outPutStream.close();

          outputArea.append("\nObject has been serialized");

        } catch (IOException xcpt) {
          JOptionPane.showInputDialog(this, xcpt);
        }
      }
    });

    JLabel jLabelSave = new JLabel("Save File:");

    listPaneSave.add(jLabelSave);
    listPaneSave.add(saveField);

    return listPaneSave;
  }

  /**
   * 
   * @return JPanel
   */
  public JPanel setUpLoadGame() {

    JPanel listPaneLoad = new JPanel();
    listPaneLoad.setLayout(new BoxLayout(listPaneLoad, BoxLayout.PAGE_AXIS));

    this.adventureField = new JTextField();
    adventureField.setEditable(true);
    adventureField.setText("Please start or load an adventure");
    adventureField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        String fileToOpen = adventureField.getText();
        ArrayList<String> argsToUse = new ArrayList<>();

        if (fileToOpen.contains(".json")) {
          argsToUse.add("-a");
          argsToUse.add(fileToOpen);
        } else {
          argsToUse.add("-l");
          argsToUse.add(fileToOpen);
        }

        controller.setStringArgs(argsToUse);

        try {
          controller.decideToStartOrLoad(controller);
          outputArea.append(controller.printWelcomeMessage());
          controller.getAdventureObj().getPlayer().setName(userName);
          outputArea.append("\n" + controller.printRoom(controller.getAdventureObj().getCurrentRoom()));
        } catch (InvalidJSONFileInput xcpt) {
          JOptionPane.showInputDialog(this, xcpt);
        } catch (IOException xcpt) {
          JOptionPane.showInputDialog(this, xcpt);
        } catch (ParseException xcpt) {
          JOptionPane.showInputDialog(this, xcpt);
        } catch (NullPointerException xcpt) {
          JOptionPane.showInputDialog(this, xcpt);
        }
      }
    });

    JLabel jLabelAdventure = new JLabel("Load File:");

    listPaneLoad.add(jLabelAdventure);
    listPaneLoad.add(adventureField);

    return listPaneLoad;
  }

  /**
   * 
   * @param event
   */
  public void playerNameAction(ActionEvent event) {
    userName = nameField.getText();
    nameArea.setText(userName);
  }

  public static void main(String[] args) {
    Game gameToSendIn = new Game();
    ColossalCavesGUI GameGUI = new ColossalCavesGUI(gameToSendIn);
    GameGUI.setVisible(true);
  }
}
