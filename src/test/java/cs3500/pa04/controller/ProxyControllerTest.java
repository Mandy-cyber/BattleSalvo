package cs3500.pa04.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.BoardSetup;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.json.EndJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.json.VolleyJson;
import cs3500.pa04.mocks.Mocket;
import cs3500.pa04.model.AiComputer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test correct responses for different requests from the socket using a Mock Socket (Mocket)
 */
public class ProxyControllerTest {

  private ByteArrayOutputStream testLog;
  private ProxyController controller;


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());


  }


  /**
   * Check that the controller ends the game
   */
  @Test
  public void testEndGame() {
    // Prepare sample message
    EndJson endMessage = new EndJson(GameResult.LOSE,
        "Your player did not return a valid volley");
    JsonNode sampleMessage = createSampleMessage("end-game", endMessage);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));
    this.controller = new ProxyController(socket,
        new AiComputer(new BoardSetup(), new Board(), new Board(), new Board()));

    // run the controller and verify the response
    this.controller.runBattleSalvo();
    responseToClass(MessageJson.class);
  }

  /**
   * Check that the controller joins the game
   */
  @Test
  public void testJoin() {
    // Prepare sample message
    MessageJson message = new MessageJson("join", null);
    JsonNode sampleMessage = JsonUtils.serializeRecord(message);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));
    this.controller = new ProxyController(socket,
        new AiComputer(new BoardSetup(), new Board(), new Board(), new Board()));

    // run the controller and verify the response
    this.controller.runBattleSalvo();
    responseToClass(MessageJson.class);
  }

  /**
   * Check that the controller takes shots
   */
  @Test
  public void testTakeShots() {
    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.CARRIER, 2);
    fleet.put(ShipType.BATTLESHIP, 4);
    fleet.put(ShipType.DESTROYER, 1);
    fleet.put(ShipType.SUBMARINE, 3);
    // Prepare sample message
    JsonNode sampleMessage = createSampleMessage("setup",
        new SetupJson(10, 10, fleet));

    AiComputer theCom = new AiComputer(new BoardSetup(), new Board(), new Board(), new Board());
    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));
    this.controller = new ProxyController(socket,
        theCom);

    // run the controller and verify the response
    this.controller.runBattleSalvo();
    responseToClass(MessageJson.class);





    // Prepare sample message
    List<Coord> sampleShots = new ArrayList<>(List.of(
        new Coord(0, 0), new Coord(1, 1)));
    JsonNode sampleMessage1 = createSampleMessage("take-shots",
        new VolleyJson(sampleShots));

    // Create the client with all necessary messages
    Mocket socket1 = new Mocket(this.testLog, List.of(sampleMessage.toString()));
    this.controller = new ProxyController(socket,
        theCom);

    // run the controller and verify the response
    this.controller.runBattleSalvo();
    responseToClass(MessageJson.class);
  }


  /**
   * Checks that an invalid message is thrown
   */
  @Test
  public void testInvalid() {
    // Prepare sample invalid message
    JsonNode sampleMessage = createSampleMessage("invalid", null);

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));
    this.controller = new ProxyController(socket,
        new AiComputer(new BoardSetup(), new Board(), new Board(), new Board()));

    // run the controller and ensure exception is thrown
    assertThrows(IllegalArgumentException.class, () ->
        this.controller.runBattleSalvo());
  }


  /**
   * Check that the controller sets up the game
   */
  @Test
  public void testSetup() {
    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.CARRIER, 2);
    fleet.put(ShipType.BATTLESHIP, 4);
    fleet.put(ShipType.DESTROYER, 1);
    fleet.put(ShipType.SUBMARINE, 3);
    // Prepare sample message
    JsonNode sampleMessage = createSampleMessage("setup",
        new SetupJson(10, 10, fleet));

    // Create the client with all necessary messages
    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));
    this.controller = new ProxyController(socket,
        new AiComputer(new BoardSetup(), new Board(), new Board(), new Board()));

    // run the controller and verify the response
    this.controller.runBattleSalvo();
    responseToClass(MessageJson.class);
  }



  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Try converting the current test log to a string of a certain class.
   *
   * @param classRef Type to try converting the current test stream to.
   * @param <T>      Type to try converting the current test stream to.
   */
  private <T> void responseToClass(@SuppressWarnings("SameParameterValue") Class<T> classRef) {
    try {
      JsonParser jsonParser = new ObjectMapper().createParser(logToString());
      jsonParser.readValueAs(classRef);
      // No error thrown when parsing to a GuessJson, test passes!
    } catch (IOException e) {
      // Could not read
      // -> exception thrown
      // -> test fails since it must have been the wrong type of response.
      fail();
    }
  }

  /**
   * Create a JsonNode for some name and arguments.
   *
   * @param messageName name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a JsonNode for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson = new MessageJson(messageName,
        JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }
}

