package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * test class for computer
 */
class ComputerTest {

  /**
   * tests initshots
   */
  @Test
  public void testinitShots() {

    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 2);

    Board b1 = new Board();
    b1.board = new char[7][7];
    BoardSetup bs = new BoardSetup();
    Board b2 = new Board();
    Computer com = new Computer(bs, b1, b2, b2);
    com.setup(7, 7, specs);

    List<Coord> expected = new ArrayList<>();
    expected.add(new Coord(0, 0));
    expected.add(new Coord(1, 0));
    expected.add(new Coord(2, 0));
    expected.add(new Coord(3, 0));
    expected.add(new Coord(4, 0));
    expected.add(new Coord(5, 0));
    expected.add(new Coord(6, 0));
    expected.add(new Coord(0, 1));
    expected.add(new Coord(1, 1));
    expected.add(new Coord(2, 1));
    expected.add(new Coord(3, 1));
    expected.add(new Coord(4, 1));
    expected.add(new Coord(5, 1));
    expected.add(new Coord(6, 1));
    expected.add(new Coord(0, 2));
    expected.add(new Coord(1, 2));
    expected.add(new Coord(2, 2));
    expected.add(new Coord(3, 2));
    expected.add(new Coord(4, 2));
    expected.add(new Coord(5, 2));
    expected.add(new Coord(6, 2));
    expected.add(new Coord(0, 3));
    expected.add(new Coord(1, 3));
    expected.add(new Coord(2, 3));
    expected.add(new Coord(3, 3));
    expected.add(new Coord(4, 3));
    expected.add(new Coord(5, 3));
    expected.add(new Coord(6, 3));
    expected.add(new Coord(0, 4));
    expected.add(new Coord(1, 4));
    expected.add(new Coord(2, 4));
    expected.add(new Coord(3, 4));
    expected.add(new Coord(4, 4));
    expected.add(new Coord(5, 4));
    expected.add(new Coord(6, 4));
    expected.add(new Coord(0, 5));
    expected.add(new Coord(1, 5));
    expected.add(new Coord(2, 5));
    expected.add(new Coord(3, 5));
    expected.add(new Coord(4, 5));
    expected.add(new Coord(5, 5));
    expected.add(new Coord(6, 5));
    expected.add(new Coord(0, 6));
    expected.add(new Coord(1, 6));
    expected.add(new Coord(2, 6));
    expected.add(new Coord(3, 6));
    expected.add(new Coord(4, 6));
    expected.add(new Coord(5, 6));
    expected.add(new Coord(6, 6));

    assertEquals(expected, com.shots);
  }

  /**
   * tests setup
   */
  @Test
  public void testsetUp() {

    BoardSetup bs = new BoardSetup(4);
    Board board = new Board();
    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 2);

    char[][] expectedBoard =
          {{'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', 'S', 'S', 'S'},
              {'0', 'S', 'S', '0', '0', 'S'},
              {'0', 'S', 'S', '0', '0', 'S'},
              {'0', 'S', 'S', '0', '0', 'S'},
              {'0', 'S', 'S', '0', '0', 'S'},
              {'0', 'S', 'S', 'S', '0', '0'},
              {'0', '0', 'S', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'}};

    Computer com = new Computer(bs, board, board, board);
    com.setup(10, 6, specs);
    assertArrayEquals(expectedBoard, com.myBoard.board);

  }

  /**
   * tests what setup returms
   */
  @Test
  public void testreturnsetUp() {

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

    BoardSetup bs = new BoardSetup(4);
    Board board = new Board();
    Computer com = new Computer(bs, board, board, board);
    assertEquals(los, com.setup(10, 6, specs));
  }

  /**
   * tests setup
   */
  @Test
  public void testsetUp2() {

    BoardSetup bs = new BoardSetup(4);
    Board board = new Board();
    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 2);

    char[][] expectedBoard =
          {{'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', 'S', 'S', 'S'},
              {'0', 'S', 'S', '0', '0', 'S'},
              {'0', 'S', 'S', '0', '0', 'S'},
              {'0', 'S', 'S', '0', '0', 'S'},
              {'0', 'S', 'S', '0', '0', 'S'},
              {'0', 'S', 'S', 'S', '0', '0'},
              {'0', '0', 'S', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'}};

    Computer com = new Computer(bs, board, board, board);
    com.setup(10, 6, specs);
    assertArrayEquals(expectedBoard, com.myBoard.board);

  }

  /**
   * tests what the setup makes as list
   */
  @Test
  public void testsetUplist() {

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

    BoardSetup bs = new BoardSetup(4);
    Board board = new Board();
    Computer com = new Computer(bs, board, board, board);
    assertEquals(los, com.setup(10, 6, specs));
  }

  /**
   * tests takeshots
   */
  @Test
  public void testtakeShots() {

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
    b.ships = los;
    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 2);
    Board irr = new Board();
    BoardSetup bs = new BoardSetup();
    Computer com = new Computer(bs, b, irr, irr);
    com.setup(10, 6, specs);
    b.board = newBoard;

    List<Coord> expected = new ArrayList<>();
    expected.add(new Coord(0, 0));
    expected.add(new Coord(1, 0));
    expected.add(new Coord(2, 0));
    expected.add(new Coord(3, 0));
    expected.add(new Coord(4, 0));

    assertEquals(expected, com.takeShots());
  }

  /**
   * tests report damage
   */
  @Test
  public void testreportDamage() {

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
    Board b = new Board();
    b.ships = los;
    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 2);
    Board irr = new Board();
    BoardSetup bs = new BoardSetup();
    Computer com = new Computer(bs, b, irr, irr);
    com.setup(10, 6, specs);
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
    b.board = newBoard;
    irr.board = newBoard;
    List<Coord> shotsTaken = new ArrayList<>();
    shotsTaken.add(new Coord(0, 0));
    shotsTaken.add(new Coord(3, 1));
    shotsTaken.add(new Coord(5, 4));

    List<Coord> expected = new ArrayList<>();
    expected.add(new Coord(3, 1));
    expected.add(new Coord(5, 4));

    assertEquals(expected, com.reportDamage(shotsTaken));
  }

  /**
   * tests successful hits
   */
  @Test
  public void testsuccessfulHits() {

    BoardSetup bs = new BoardSetup();
    Board b = new Board();
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
    Board irr = new Board();
    Computer com = new Computer(bs, b, irr, irr);

    com.opponentBoard.board = newBoard;

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
    b.ships = los;
    irr.ships = los;
    Map<ShipType, Integer> specs = new HashMap<>();
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 1);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 2);
    com.setup(10, 6, specs);
    b.board = newBoard;

    List<Coord> expected = new ArrayList<>();
    expected.add(new Coord(3, 1));
    expected.add(new Coord(5, 4));

    char[][] updatedBoard =
          {{'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', 'H', 'S', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', '0', '0', 'H'},
              {'0', 'S', 'H', '0', '0', 'S'},
              {'0', 'S', 'H', 'S', '0', '0'},
              {'0', '0', 'H', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'},
              {'0', '0', '0', 'S', '0', 'S'}};

    com.successfulHits(expected);

    assertArrayEquals(updatedBoard, com.opponentBoard.board);
  }
}


