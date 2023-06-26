package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa04.model.GameType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests to serialize records into Json nodes
 */
class JsonUtilsTest {
  EndJson endJson;
  JoinJson joinJson;
  VolleyJson volleyJson;

  /**
   * Initializes records for testing
   */
  @BeforeEach
  public void setup() {
    endJson = new EndJson(GameResult.LOSE, "Your opponent sunk all your ships!");
    joinJson = new JoinJson("jhippler", GameType.MULTI.toString());
    volleyJson = new VolleyJson(new ArrayList<>(List.of(
        new Coord(0, 0),
        new Coord(1, 1),
        new Coord(2, 2))));
  }

  /**
   * Tests that different records are serialized correctly or that an error is thrown
   */
  @Test
  void serializeRecord() {
    JsonNode endNode = JsonUtils.serializeRecord(endJson);
    assertEquals(endNode.toString(),
        "{\"result\":\"LOSE\",\"reason\":\"Your opponent sunk all your ships!\"}");

    JsonNode joinNode = JsonUtils.serializeRecord(joinJson);
    assertEquals(joinNode.toString(),
        "{\"name\":\"jhippler\",\"game-type\":\"MULTI\"}");

    JsonNode volleyNode = JsonUtils.serializeRecord(volleyJson);
    assertEquals(volleyNode.toString(),
        "{\"coordinates\":[{\"x\":0,\"y\":0},{\"x\":1,\"y\":1},{\"x\":2,\"y\":2}]}");

    // TODO: ask TA how to do this properly lol
    assertThrows(IllegalArgumentException.class, () -> {
      MockJsonUtils.serializeRecord(new VolleyJson(new ArrayList<>(List.of(
          new Coord(0, 0),
          new Coord(3, 4)
      ))));
    });
  }
}