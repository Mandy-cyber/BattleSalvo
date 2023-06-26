package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.ShipType;
import java.util.Map;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "width": "numerical value",
 *   "height": "numerical value",
 *   "fleet-spec": {
 *      "CARRIER": "numerical value",
 *      "BATTLESHIP": "numerical value",
 *      "DESTROYER": "numerical value",
 *      "SUBMARINE": "numerical value"
 *   }
 * }
 * </code>
 * </p>
 *
 * @param width the width of the board
 * @param height the height of the board
 * @param fleetSpecs the specifications of the fleet for the board
 */
public record SetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") Map<ShipType, Integer> fleetSpecs) {

  /**
   * Initializes a SetupJson (primarily used for testing purposes)
   *
   * @param width the width of the board
   * @param height the height of the board
   * @param fleetSpecs the specifications of the fleet for the board
   */
  public SetupJson(int width, int height, Map<ShipType, Integer> fleetSpecs) {
    this.width = width;
    this.height = height;
    this.fleetSpecs = fleetSpecs;
  }

}
