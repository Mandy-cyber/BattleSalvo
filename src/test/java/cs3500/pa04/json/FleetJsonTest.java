package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import cs3500.pa04.model.ShipAdapter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for a FleetJson
 */
class FleetJsonTest {
  Coord coord0;
  Coord coord1;
  Coord coord2;
  Coord coord3;
  Coord coord4;
  Coord coord5;
  Ship carrier;
  Ship battleship;
  Ship destroyer;
  Ship submarine;
  List<ShipAdapter> shipAdapters;
  FleetJson fleetJson;

  /**
   * Initialize a FleetJson for testing
   */
  @BeforeEach
  public void setup() {
    // coordinates
    coord0 = new Coord(0, 0);
    coord1 = new Coord(1, 1);
    coord2 = new Coord(2, 2);
    coord3 = new Coord(3, 3);
    coord4 = new Coord(4, 4);
    coord5 = new Coord(5, 5);

    // ships
    carrier = new Ship(ShipType.CARRIER, new ArrayList<>(List.of(coord0, coord1, coord2,
        coord3, coord4, coord5)));
    battleship = new Ship(ShipType.BATTLESHIP, new ArrayList<>(List.of(coord0, coord1, coord2,
        coord3, coord4)));
    destroyer = new Ship(ShipType.DESTROYER, new ArrayList<>(List.of(coord0, coord1, coord2,
        coord3)));
    submarine = new Ship(ShipType.SUBMARINE, new ArrayList<>(List.of(coord0, coord1, coord2)));

    // ShipAdapters and fleet
    shipAdapters = new ArrayList<>(List.of(
        new ShipAdapter(carrier),
        new ShipAdapter(battleship),
        new ShipAdapter(destroyer),
        new ShipAdapter(submarine)));

    fleetJson = new FleetJson(shipAdapters);
  }

  /**
   * Tests that the correct list of ship adapters is created in a FleetJson
   */
  @Test
  void fleetTest() {
    List<ShipAdapter> actualShips = fleetJson.fleet();
    assertEquals(actualShips.size(), shipAdapters.size());
    assertEquals(actualShips.get(0), shipAdapters.get(0));
    assertEquals(actualShips.get(1), shipAdapters.get(1));
    assertEquals(actualShips.get(2), shipAdapters.get(2));
    assertEquals(actualShips.get(3), shipAdapters.get(3));
  }

}