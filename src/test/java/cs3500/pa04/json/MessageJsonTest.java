package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa03.model.GameResult;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for a MessageJson
 */
class MessageJsonTest {

  /**
   * Tests that the correct message is constructed
   */
  @Test
  void messageTest() {
    EndJson endJson = new EndJson(GameResult.WIN, "You sunk all your opponent's ships!");
    JsonNode endNode = JsonUtils.serializeRecord(endJson);
    MessageJson messageJson = new MessageJson("end-game", endNode);

    assertEquals(messageJson.methodName(), "end-game");
    assertEquals(messageJson.arguments().toString(),
        "{\"result\":\"WIN\",\"reason\":\"You sunk all your opponent's ships!\"}");
  }

}