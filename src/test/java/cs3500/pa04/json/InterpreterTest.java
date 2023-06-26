package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for interpreting JSON messages
 */
class InterpreterTest {
  Interpreter setup;
  Interpreter reportDamage;
  Interpreter successfulHits;
  Interpreter endGame;




  /**
   * Initializes Interpreters for testing
   */
  @BeforeEach
  public void setup() {
    // 'SETUP' INTERPRETER
    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.CARRIER, 2);
    fleet.put(ShipType.BATTLESHIP, 4);
    fleet.put(ShipType.DESTROYER, 1);
    fleet.put(ShipType.SUBMARINE, 3);

    JsonNode setupNode = JsonUtils.serializeRecord(new SetupJson(10, 10, fleet));
    MessageJson setupMsg = new MessageJson("setup", setupNode);
    setup = new Interpreter(setupMsg);

    // 'COORD' INTERPRETERS
    List<Coord> exampleCoords = new ArrayList<>(
        List.of(new Coord(0, 0), new Coord(1, 1)));
    MessageJson listMsg = new MessageJson("report-damage",
        JsonUtils.serializeRecord(new VolleyJson(exampleCoords)));
    MessageJson listMsg2 = new MessageJson("successful-hits",
        JsonUtils.serializeRecord(new VolleyJson(exampleCoords)));

    reportDamage = new Interpreter(listMsg);
    successfulHits = new Interpreter(listMsg2);

    // 'END GAME' INTERPRETER
    MessageJson endMsg = new MessageJson("end-game",
        JsonUtils.serializeRecord(
            new EndJson(GameResult.WIN, "You sunk all your opponent's ships")));

    endGame = new Interpreter(endMsg);
  }

  /**
   * Tests that the correct dimensions are returned from a setup message
   */
  @Test
  void interpretSetupDims() {
    List<Integer> expectedDims = new ArrayList<>(List.of(10, 10));
    List<Integer> actualDims = setup.interpretSetupDims();

    assertEquals(actualDims.size(), 2);
    assertEquals(expectedDims.get(0), actualDims.get(0));
    assertEquals(expectedDims.get(1), actualDims.get(1));
  }

  /**
   * Tests that the correct fleet specifications are returned from a setup message
   */
  @Test
  void interpretSetupSpecs() {
    Map<ShipType, Integer> expectedSpecs = new HashMap<>();
    expectedSpecs.put(ShipType.CARRIER, 2);
    expectedSpecs.put(ShipType.BATTLESHIP, 4);
    expectedSpecs.put(ShipType.DESTROYER, 1);
    expectedSpecs.put(ShipType.SUBMARINE, 3);
    Map<ShipType, Integer> actualSpecs = setup.interpretSetupSpecs();

    assertEquals(actualSpecs.size(), expectedSpecs.size());
    assertEquals(actualSpecs.get(ShipType.CARRIER), 2);
    assertEquals(actualSpecs.get(ShipType.BATTLESHIP), 4);
    assertEquals(actualSpecs.get(ShipType.DESTROYER), 1);
    assertEquals(actualSpecs.get(ShipType.SUBMARINE), 3);
  }

  /**
   * Tests that the correct list of coordinates are returned from a report-damage message,
   * and a successful-hits message
   */
  @Test
  void interpretCoordList() {
    List<Coord> expectedCoords = new ArrayList<>(
        List.of(new Coord(0, 0), new Coord(1, 1)));
    List<Coord> actualCoords = reportDamage.interpretCoordList();

    assertEquals(expectedCoords.size(), actualCoords.size());
    assertEquals(actualCoords.get(0), expectedCoords.get(0));
    assertEquals(actualCoords.get(1), expectedCoords.get(1));
  }

  /**
   * Tests that the correct result of a game and reason for the game ending are returned
   * from an end-game message
   */
  @Test
  void interpretEndGameResult() {
    assertEquals(endGame.interpretEndGameResult(), GameResult.WIN);
    assertTrue(endGame.interpretEndGameReason().contains("You sunk all your opponent's ships"));
  }
}