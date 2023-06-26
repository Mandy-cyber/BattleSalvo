package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * class to represent setting a board up
 */
public class BoardSetup {

  /**
   * field for random
   */
  private Random random;

  /**
   * field for list of ships
   */
  public ArrayList<Ship> listOfShips = new ArrayList<>();

  /**
   * random constructor for board setup
   */
  public BoardSetup() {
    random = new Random();
  }

  /**
   * seeded constructor for boardsetup
   *
   * @param seed seed for random
   */
  public BoardSetup(int seed) {
    random = new Random(seed);
  }

  /**
   * makes a board with the given parameters
   *
   * @param width width
   * @param height height
   * @param board board to make
   */
  public void makeBoard(int width, int height, Board board) {
    board.board = new char[height][width];
    for (int i = 0; i < board.board.length; i++) {
      for (int j = 0; j < board.board[i].length; j++) {
        board.board[i][j] = '0';
      }
    }
  }

  /**
   * helper to see if ship can be placed
   *
   * @param board board to check
   * @param x x position
   * @param y y position
   * @param shipType kind of ship
   * @param direction horizontal or vertical
   * @return whether ship can be placed
   */
  private boolean canPlaceShip(Board board, int x, int y, ShipType shipType, int direction) {
    if (direction == 0) {  // Horizontal
      if (x + shipType.size > board.board[0].length) {
        return false;
      }
      for (int i = x; i < x + shipType.size; i++) {
        if (board.board[y][i] != '0') {
          return false;
        }
      }
    } else {  // Vertical
      if (y + shipType.size > board.board.length) {
        return false;
      }
      for (int i = y; i < y + shipType.size; i++) {
        if (board.board[i][x] != '0') {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * method to add ships to a board
   *
   * @param board board to add to
   * @param specs specifications of what to add
   * @return the list of ships that were added
   */
  public ArrayList<Ship> addShips(Board board, Map<ShipType, Integer> specs) {
    addShipsHelper(board, ShipType.CARRIER, specs);
    addShipsHelper(board, ShipType.BATTLESHIP, specs);
    addShipsHelper(board, ShipType.DESTROYER, specs);
    addShipsHelper(board, ShipType.SUBMARINE, specs);
    return this.listOfShips;
  }

  /**
   * helper to place  kind of ship
   *
   * @param board board to add to
   * @param shipType kind of ship
   * @param specs what to add
   */
  private void addShipsHelper(Board board, ShipType shipType, Map<ShipType, Integer> specs) {
    for (int i = 0; i < specs.get(shipType); i++) {
      boolean placed = false;
      while (!placed) {
        int x = this.random.nextInt(board.board[0].length);
        int y = this.random.nextInt(board.board.length);
        int direction = this.random.nextInt(2);  // 0 for horizontal, 1 for vertical

        if (this.canPlaceShip(board, x, y, shipType, direction)) {
          placeShip(board, x, y, shipType, direction);
          placed = true;
        }
      }
    }
  }

  /**
   * helper to place one ship
   *
   * @param board where  to add to
   * @param x x position
   * @param y y position to add to
   * @param shipType kind of ship
   * @param direction vertical or horizontal
   */
  private void placeShip(Board board, int x, int y, ShipType shipType, int direction) {
    Ship ship = new Ship(shipType, new ArrayList<Coord>());
    if (direction == 0) {  // Horizontal
      for (int i = x; i < x + shipType.size; i++) {
        board.board[y][i] = 'S';
        ship.location.add(new Coord(i, y));
      }
      // Ship ship = new Ship(shipType, new ArrayList<Coord>());
    } else {  // Vertical
      for (int i = y; i < y + shipType.size; i++) {
        board.board[i][x] = 'S';
        ship.location.add(new Coord(x, i));
      }
    }
    this.listOfShips.add(ship);
  }

}
