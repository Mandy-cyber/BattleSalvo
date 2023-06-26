package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.GameType;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for a JoinJson
 */
class JoinJsonTest {

  /**
   * Tests that the correct join message is constructed
   */
  @Test
  void joinTest() {
    JoinJson joinJson = new JoinJson("Mandy-cyber", GameType.SINGLE.toString());
    assertEquals(joinJson.name(), "Mandy-cyber");
    assertEquals(joinJson.gameType(), "SINGLE");
  }
}