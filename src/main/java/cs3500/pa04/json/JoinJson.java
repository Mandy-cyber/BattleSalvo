package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.GameType;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "name": "github_username",
 *   "game-type": "SINGLE/MULTI"
 * }
 * </code>
 * </p>
 *
 * @param name the GitHub username of a player
 * @param gameType the type of game being played (single = against sever, multi = against other AI)
 */
public record JoinJson(
    @JsonProperty("name") String name,
    @JsonProperty("game-type") String gameType) {

  /**
   * Initializes a JoinJson (primarily for testing purposes)
   *
   * @param name a user's GitHub username
   * @param gameType the type of game being played
   */
  public JoinJson(String name, String gameType) {
    this.name = name;
    this.gameType = gameType;
  }
}
