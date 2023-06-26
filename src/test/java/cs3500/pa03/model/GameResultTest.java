package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * test class for GamResult enum
 */
class GameResultTest {

  /**
   * tests stringToResult
   */
  @Test
  public void testStringToResult() {

    assertEquals(GameResult.DRAW, GameResult.stringToResult("TIE"));
    assertEquals(GameResult.DRAW, GameResult.stringToResult("DRAW"));
    assertEquals(GameResult.LOSE, GameResult.stringToResult("LOSE"));
  }


}