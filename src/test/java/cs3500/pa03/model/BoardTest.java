package cs3500.pa03.model;



import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * test class for board
 */
class BoardTest {

  /**
   * tests numofshipsleft checkhit and updateboard
   */
  @Test
  public void testnumOfShipsLeft() {
    BoardSetup bs = new BoardSetup(4);
    Board board = new Board();
    User user = new User(bs, board, board, board);

    ArrayList<Coord> c1loc = new ArrayList<Coord>();
    c1loc.add(new Coord(2, 2));
    c1loc.add(new Coord(2, 3));
    c1loc.add(new Coord(2, 4));
    c1loc.add(new Coord(2, 5));
    c1loc.add(new Coord(2, 6));
    c1loc.add(new Coord(2, 7));
    Ship carrier1 = new Ship(ShipType.CARRIER, c1loc);
    List<Ship> los = new ArrayList<>();
    los.add(carrier1);

    ArrayList<Coord> b1loc = new ArrayList<Coord>();
    b1loc.add(new Coord(1, 2));
    b1loc.add(new Coord(1, 3));
    b1loc.add(new Coord(1, 4));
    b1loc.add(new Coord(1, 5));
    b1loc.add(new Coord(1, 6));
    Ship battleship1 = new Ship(ShipType.BATTLESHIP, b1loc);
    los.add(battleship1);

    ArrayList<Coord> d1loc = new ArrayList<Coord>();
    d1loc.add(new Coord(5, 2));
    d1loc.add(new Coord(5, 3));
    d1loc.add(new Coord(5, 4));
    d1loc.add(new Coord(5, 5));
    Ship destroyer1 = new Ship(ShipType.DESTROYER, d1loc);
    los.add(destroyer1);

    ArrayList<Coord> d2loc = new ArrayList<Coord>();
    d2loc.add(new Coord(3, 6));
    d2loc.add(new Coord(3, 7));
    d2loc.add(new Coord(3, 8));
    d2loc.add(new Coord(3, 9));
    Ship destroyer2 = new Ship(ShipType.DESTROYER, d2loc);
    los.add(destroyer2);


    ArrayList<Coord> s2loc = new ArrayList<Coord>();
    s2loc.add(new Coord(3, 1));
    s2loc.add(new Coord(4, 1));
    s2loc.add(new Coord(5, 1));
    Ship submarine2 = new Ship(ShipType.SUBMARINE, s2loc);
    los.add(submarine2);
    ArrayList<Coord> s1loc = new ArrayList<Coord>();
    s1loc.add(new Coord(5, 7));
    s1loc.add(new Coord(5, 8));
    s1loc.add(new Coord(5, 9));
    Ship submarine1 = new Ship(ShipType.SUBMARINE, s1loc);
    los.add(submarine1);

    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 2);

    char[][] newBoard =
          {{'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', 'S', 'S', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', 'S', '0', '0'},
              {'0', '0', 'H', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'}};

    Board b = new Board();
    b.board = newBoard;
    b.ships = los;

    assertEquals(5, b.numOfShipsLeft());
    assertTrue(b.checkHit(new Coord(3, 1)));
    assertFalse(b.checkHit(new Coord(0, 0)));

    b.updateBoard(new Coord(3, 1), 'H');
    char[][] updatedBoard =
          {{'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', 'H', 'S', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', 'S', '0', '0'},
              {'0', '0', 'H', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'}};

    assertArrayEquals(updatedBoard, b.board);
  }
}