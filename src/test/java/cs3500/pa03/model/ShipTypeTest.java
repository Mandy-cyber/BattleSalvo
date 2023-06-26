package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * test class for shiptype
 */
class ShipTypeTest {

  /**
   * tests Shiptype numbers
   */
  @Test
  public void testShipType() {
    assertEquals(6, ShipType.CARRIER.size);
    assertEquals(5, ShipType.BATTLESHIP.size);
    assertEquals(4, ShipType.DESTROYER.size);
    assertEquals(3, ShipType.SUBMARINE.size);
  }

}