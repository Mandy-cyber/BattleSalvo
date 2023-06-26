package cs3500.pa03.model;

/**
 * enum for gameResult
 */
public enum GameResult {

  /**
   * options for game to end
   */
  WIN, LOSE, DRAW;

  /**
   * Determines the GameResult using the given string
   *
   * @param str a string-format of a GameResult (i.e. "WIN")
   * @return the associated game result
   */
  public static GameResult stringToResult(String str) {
    str = str.toUpperCase();
    if (str.contains("WIN")) {
      return GameResult.WIN;
    } else if (str.contains("DRAW") || str.contains("TIE")) {
      return GameResult.DRAW;
    } else {
      return GameResult.LOSE;
    }
  }
}
