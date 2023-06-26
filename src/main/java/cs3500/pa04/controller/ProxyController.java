package cs3500.pa04.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.BoardSetup;
import cs3500.pa03.model.Computer;
import cs3500.pa03.model.Player;
import cs3500.pa03.view.Display;
import cs3500.pa04.json.Interpreter;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.Translator;
import cs3500.pa04.model.AiComputer;
import cs3500.pa04.model.GameModel;
import cs3500.pa04.model.GameType;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a proxy controller/dealer for a game of BattleSalvo
 */
public class ProxyController implements Controller {
  /**
   * Input to the controller (primarily for testing)
   */
  private Readable input;

  /**
   * Output to the controller (primarily for testing)
   */
  private PrintStream output;

  /**
   * field for the input stream to read
   */
  private InputStream readable;

  /**
   * field for the AI player
   */
  private AiComputer computer;

  /**
   * The server to be played with
   */
  private Socket server;

  /**
   * The model of the game
   */
  private GameModel model;
  private final ObjectMapper mapper = new ObjectMapper();


  /**
   * Initializes a ProxyController with the given server to connect to,
   * and player to use against the server
   *
   * @param server the server to be connected tp
   * @param player the player being used against the server
   * @throws RuntimeException if a ProxyController could not be created
   */
  public ProxyController(Socket server, AiComputer player) {
    try {
      this.server = server;
      this.computer = player;
      this.readable = server.getInputStream();
      this.input = new InputStreamReader(this.readable);
      this.output = new PrintStream(server.getOutputStream());
      this.model = new GameModel();
    } catch (IOException e) {
      throw new RuntimeException("Could not create a ProxyController, please try again.");
    }
  }


  /**
   * Runs a game of BattleSalvo
   */
  @Override
  public void runBattleSalvo() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.readable);
      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        System.out.println(message);
        delegateMessage(message);
      }
    } catch (IOException e) {
      System.out.println("ERROR OCCURRED <3");
      e.printStackTrace();
    }
  }

  /**
   *
   *
   */
  private void delegateMessage(MessageJson message) throws IOException {
    String name = message.methodName();
    if ("join".equals(name)) {
      // JOINING THE GAME
      output.println(model.handleJoin());
      System.out.println("-------------------------------------------------------------\n");

    } else if ("setup".equals(name)) {
      // SETTING UP THE GAME
      output.println(model.handleSetup(message));
      System.out.println("-------------------------------------------------------------\n");

    } else if ("take-shots".equals(name)) {
      // TAKING SHOTS
      output.println(model.handleTakeShots());
      System.out.println("-------------------------------------------------------------\n");

    } else if ("report-damage".equals(name)) {
      // REPORTING DAMAGE
      output.println(model.handleReportDamage(message));
      System.out.println("-------------------------------------------------------------\n");

    } else if ("successful-hits".equals(name)) {
      // SUCCESSFUL HITS
      output.println(model.handleSuccessfulHits(message));
      System.out.println("-------------------------------------------------------------\n");

    } else if ("end-game".equals(name)) {
      // END OF GAME
      output.println(model.endBattleSalvo(message));
      server.close();
      System.out.println("-------------------------------------------------------------\n");

    } else {
      // INVALID MESSAGE
      throw new IllegalArgumentException("Invalid message provided");
    }
  }
}




