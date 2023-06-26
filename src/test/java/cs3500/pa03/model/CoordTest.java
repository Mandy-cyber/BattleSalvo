package cs3500.pa03.model;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * test class for coord
 */
class CoordTest {

  /**
   * tests the override equals
   */
  @Test
  public void testequals() {
    Coord c1 = new Coord(1, 2);
    Coord c2 = new Coord(0, 1);

    assertFalse(c1.equals(c2));
    assertTrue(c1.equals(c1));
    assertFalse(c1.equals(null));
  }

}