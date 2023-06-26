package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.GameResult;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for an EndJson
 */
class EndJsonTest {

  /**
   * Tests that the correct end-game reason and result are returned
   */
  @Test
  void endGame() {
    EndJson endJson = new EndJson(GameResult.WIN, "You sunk all your opponent's ships!");
    assertEquals(endJson.result(), GameResult.WIN);
    assertEquals(endJson.reason(), "You sunk all your opponent's ships!");
  }

}