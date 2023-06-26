package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ShipAdapter;
import java.util.List;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "": "list of ship adapters"
 * }
 * </code>
 * </p>
 *
 * @param fleet the list of ships in the fleet
 */
public record FleetJson(
    @JsonProperty() List<ShipAdapter> fleet) {

  /**
   * Initializes a FleetJson (primarily for testing purposes)
   *
   * @param fleet the list of ships in the fleet
   */
  public FleetJson(List<ShipAdapter> fleet) {
    this.fleet = fleet;
  }
}
