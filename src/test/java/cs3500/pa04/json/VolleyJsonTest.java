package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for a VolleyJson
 */
class VolleyJsonTest {

  /**
   * Tests that the correct list of coordinates (volley) is constructed
   */
  @Test
  void volleyTest() {
    List<Coord> coordList = new ArrayList<>(List.of(
        new Coord(0, 0),
        new Coord(1, 1)
    ));
    VolleyJson volleyJson = new VolleyJson(coordList);
    assertEquals(volleyJson.coordinates(), coordList);
  }
}