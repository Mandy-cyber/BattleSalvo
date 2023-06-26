package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test class to test ShipAdapter
 */
class ShipAdapterTest {

  /**
   * field for shipAdapter 1
   */
  ShipAdapter sa1;
  /**
   * field for shipAdapter 2
   */
  ShipAdapter sa2;
  /**
   * field for shipAdapter 3
   */
  ShipAdapter sa3;

  Ship ship4;

  ShipAdapter sa4;


  /*
  instantiate ship adapters
   */
  @BeforeEach
  public void setup() {
    ArrayList<Coord> b1loc = new ArrayList<Coord>();
    b1loc.add(new Coord(1, 2));
    b1loc.add(new Coord(1, 3));
    b1loc.add(new Coord(1, 4));
    b1loc.add(new Coord(1, 5));
    b1loc.add(new Coord(1, 6));
    Ship ship1 = new Ship(ShipType.BATTLESHIP, b1loc);
    this.sa1 = new ShipAdapter(ship1);
    System.out.println(ship1.location);

    ArrayList<Coord> b2loc = new ArrayList<Coord>();
    b2loc.add(new Coord(2, 2));
    b2loc.add(new Coord(3, 2));
    b2loc.add(new Coord(4, 2));
    b2loc.add(new Coord(5, 2));

    Ship ship2 = new Ship(ShipType.DESTROYER, b2loc);
    this.sa2 = new ShipAdapter(ship2);

    ArrayList<Coord> b3loc = new ArrayList<Coord>();
    b3loc.add(new Coord(2, 2));
    b3loc.add(new Coord(3, 2));
    b3loc.add(new Coord(4, 2));
    b3loc.add(new Coord(5, 2));

    Ship ship3 = new Ship(ShipType.DESTROYER, b3loc);
    this.sa3 = new ShipAdapter(ship3);

    ArrayList<Coord> b4loc = new ArrayList<Coord>();
    b4loc.add(new Coord(2, 2));
    b4loc.add(new Coord(3, 3));
    b4loc.add(new Coord(4, 7));
    b4loc.add(new Coord(5, 2));

    this.ship4 = new Ship(ShipType.DESTROYER, b4loc);
    this.sa4 = new ShipAdapter(ship4);
  }

  /**
   * test getCoord
   */
  @Test
  void getCoord() {
    assertEquals(new Coord(1, 2), this.sa1.getCoord());
    assertEquals(new Coord(2, 2), this.sa2.getCoord());
  }

  /**
   * test getLength
   */
  @Test
  void getLength() {
    assertEquals(5, this.sa1.getLength());
    assertEquals(4, this.sa2.getLength());
  }

  /**
   * test getDirection
   */
  @Test
  void getDirection() {
    assertEquals("VERTICAL", this.sa1.getDirection());
    assertEquals("HORIZONTAL", this.sa2.getDirection());
  }

  /**
   * test overriden equals
   */
  @Test
  void testEquals() {

    assertFalse(this.sa1.equals(sa2));
    assertEquals(this.sa2, sa3);
    assertFalse("null".equals(sa1));
    assertFalse(sa1.equals("null"));
    assertFalse(this.sa1.equals(sa4));

    ArrayList<Coord> b5loc = new ArrayList<Coord>();
    b5loc.add(new Coord(2, 2));
    b5loc.add(new Coord(2, 3));
    b5loc.add(new Coord(2, 4));
    b5loc.add(new Coord(2, 5));

    Ship ship5 = new Ship(ShipType.DESTROYER, b5loc);

    assertFalse(new ShipAdapter(ship5).equals(this.sa2));


    ArrayList<Coord> b4loc = new ArrayList<Coord>();
    b4loc.add(new Coord(2, 2));
    b4loc.add(new Coord(3, 3));
    b4loc.add(new Coord(4, 7));
    b4loc.add(new Coord(5, 2));

    this.ship4 = new Ship(ShipType.DESTROYER, b4loc);
    ShipAdapter sa4 = new ShipAdapter(ship4);

    ArrayList<Coord> b6loc = new ArrayList<Coord>();
    b6loc.add(new Coord(3, 3));
    b6loc.add(new Coord(3, 2));
    b6loc.add(new Coord(4, 7));
    b6loc.add(new Coord(5, 2));
    b6loc.add(new Coord(2, 5));


    Ship ship6 = new Ship(ShipType.DESTROYER, b6loc);
    ShipAdapter sa6 = new ShipAdapter(ship6);
    assertNotEquals(sa6, new ShipAdapter(ship5));
  }

}