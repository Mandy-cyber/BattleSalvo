package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.GameResult;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "result": "WIN",
 *   "reason": "Player 1 sank all of Player 2's ships"
 * }
 * </code>
 * </p>
 *
 * @param result the `result of the game
 * @param reason the reason the game ended
 */
public record EndJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason) {

  /**
   * Initializes an EndJson (primarily for testing purposes)
   *
   * @param result the result of the game
   * @param reason the reason the game has ended
   */
  public EndJson(GameResult result, String reason) {
    this.result = result;
    this.reason = reason;
  }
}
