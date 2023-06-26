package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.ShipType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for a JSON setup message
 */
class SetupJsonTest {

  /**
   * Tests that a SetupJson is constructed correctly
   */
  @Test
  void setup() {
    Map<ShipType, Integer> fleet = new HashMap<>();
    fleet.put(ShipType.CARRIER, 2);
    fleet.put(ShipType.BATTLESHIP, 4);
    fleet.put(ShipType.DESTROYER, 1);
    fleet.put(ShipType.SUBMARINE, 3);
    SetupJson setup = new SetupJson(10, 10, fleet);

    assertEquals(setup.width(), 10);
    assertEquals(setup.height(), 10);
    assertEquals(setup.fleetSpecs().get(ShipType.CARRIER).intValue(), 2);
    assertEquals(setup.fleetSpecs().get(ShipType.BATTLESHIP).intValue(), 4);
    assertEquals(setup.fleetSpecs().get(ShipType.DESTROYER).intValue(), 1);
    assertEquals(setup.fleetSpecs().get(ShipType.SUBMARINE).intValue(), 3);
  }
}