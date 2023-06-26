package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa03.model.Coord;
import java.util.List;

/**
 * JSON format of this record:
 * <p>
 * <code>
 * {
 *   "coordinates": {"list of shots in the volley"}
 * }
 * </code>
 * </p>
 *
 * @param coordinates the list of coordinates/shots in the volley
 */
public record VolleyJson(
      @JsonProperty("coordinates") List<Coord> coordinates) {

  /**
   * Initializes a VolleyJson (primarily for testing purposes)
   *
   * @param coordinates the list of coordinates/shots in the volley
   */
  public VolleyJson(List<Coord> coordinates) {
    this.coordinates = coordinates;
  }
}
