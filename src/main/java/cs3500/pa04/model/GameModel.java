package cs3500.pa04.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.BoardSetup;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.json.Interpreter;
import cs3500.pa04.json.JoinJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.Translator;
import java.util.List;
import java.util.Map;

/**
 * Represents a model in the game of BattleSalvo
 */
public class GameModel {
  private Translator translator;
  /**
   * field for the AI player
   */
  private AiComputer computer;

  private ObjectMapper mapper;

  /**
   * Instantiates a GameModel with a new computer, translator, and mapper
   */
  public GameModel() {
    computer = new AiComputer(new BoardSetup(), new Board(), new Board(), new Board());
    translator = new Translator();
    mapper = new ObjectMapper();
  }

  /**
   * Handles the message to start/join a game of BattleSalvo
   *
   * @return the join message to send back to the server
   */
  public JsonNode handleJoin() {
    System.out.println("JOIN");
    MessageJson messageToServer = new MessageJson("join",
        JsonUtils.serializeRecord(new JoinJson("jhippler", GameType.SINGLE.toString())));
    return JsonUtils.serializeRecord(messageToServer);
  }


  /**
   * helpers to get user info
   *
   */
  public String getPlayerName() {
    return "jhippler";
  }

  /**
   *
   */
  public GameType getPlayerGameType() {
    return GameType.SINGLE;
  }


  /**
   * Gets the computer of this model
   *
   * @return the AIComputer behind this model
   */
  public Player getComputer() {
    return computer;
  }


  /**
   * Handles the message to initialize and setup a game
   *
   * @param message message with the
   * @return message to send to server of ships that have been placed
   */
  public JsonNode handleSetup(MessageJson message) {
    System.out.println("SETUP: " + message);
    Interpreter interpretSetup = new Interpreter(message);
    List<Integer> dimList = interpretSetup.interpretSetupDims();
    Map<ShipType, Integer> specs = interpretSetup.interpretSetupSpecs();
    List<Ship> shipList = computer.setup(dimList.get(0), dimList.get(1), specs);
    return translator.clientSetup(shipList);
  }

  /**
   * Handles the message to prompt the client to take shots
   *
   * @return message to send to server of shots taken
   */
  public JsonNode handleTakeShots() {
    System.out.println("TAKE-SHOTS");
    List<Coord> shots = computer.takeShots();
    System.out.println(translator.clientTakeShots(shots).toString());
    return translator.clientTakeShots(shots);
  }



  /**
   * Handles receiving a list of shots that hit the computer, and updates the computer's
   * view of its own board accordingly.
   *
   * @param message the report-damage message in json format
   *  @return the message to send back to the server
   */
  public JsonNode handleReportDamage(MessageJson message) {
    System.out.println("REPORT-DAMAGE: " + message);
    Interpreter interpreter = new Interpreter(message);
    // update the computer's board
    List<Coord> reportedShots = interpreter.interpretCoordList();
    List<Coord> shotsThatHit = computer.reportDamage(reportedShots);
    // send out a client response with shots that hit
    return translator.clientReportDamage(shotsThatHit);
  }


  /**
   * Handles receiving a list of successful hits and updates the computer's view of the
   * opponent's board accordingly
   *
   * @param message the successful-hits message in json format
   * @return the message to send back to the server
   */
  public JsonNode handleSuccessfulHits(MessageJson message) {
    System.out.println("SUCCESSFUL-HITS: " + message);
    Interpreter interpreter = new Interpreter(message);
    // update the opponent's board
    List<Coord> successfulHits = interpreter.interpretCoordList();
    computer.successfulHits(successfulHits);
    // send out an empty client response
    return translator.clientSuccessfulHits();
  }


  /**
   * Ends a game of BattleSalvo
   *
   * @param message the end of game message in json format
   * @return the game ending response
   */
  public JsonNode endBattleSalvo(MessageJson message) {
    System.out.println("END-GAME: " + message);
    return translator.clientEndGame();
  }

}
