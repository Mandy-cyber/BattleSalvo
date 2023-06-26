package cs3500.pa03.model;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * class to test boardsetup
 */
class BoardSetupTest {

  /**
   * tests makeboard
   */
  @Test
  public void testmakeBoard() {
    BoardSetup bs = new BoardSetup(4);
    char[][] expectedOriginalBoard =
          {{'0', '0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0'}};
    Board board = new Board();
    bs.makeBoard(6, 10, board);
    assertArrayEquals(expectedOriginalBoard, board.board);
  }

  /**
   * tests addships
   */
  @Test
  public void testaddShips() {
    BoardSetup bs = new BoardSetup(4);
    Board board = new Board();
    bs.makeBoard(6, 10, board);
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

    bs.addShips(board, specs);
    assertArrayEquals(expectedBoard, board.board);
  }

  /**
   * tests the random constructor
   */
  @Test
  public void testrandomConstructor() {
    BoardSetup bs = new BoardSetup();
    char[][] expectedOriginalBoard =
          {{'0', '0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', '0', '0', '0'},
              {'0', '0', '0', '0', '0', '0'}, {'0', '0', '0', '0', '0', '0'}};
    Board board = new Board();
    bs.makeBoard(6, 7, board);
    assertArrayEquals(expectedOriginalBoard, board.board);
  }
}