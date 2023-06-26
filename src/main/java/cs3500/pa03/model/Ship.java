package cs3500.pa03.model;

import java.util.ArrayList;

/**
 * class to represent a ship
 */
public class Ship {
  /**
   * field for the kind of ship
   */
  public ShipType kind;

  /**
   * field for the list of ship coordinates
   */
  public ArrayList<Coord> location;

  /**
   * constructor to initialize ship
   *
   * @param kind type of ship
   * @param location ship's location
   */
  public Ship(ShipType kind, ArrayList<Coord> location) {
    this.kind = kind;
    this.location = location;
  }

  /**
   * overide equald
   *
   * @param q2 other object
   * @return whether objects are equal
   */
  @Override
  public boolean equals(Object q2) {
    if (q2 instanceof Ship) {
      Ship other = (Ship) q2;
      return (this.kind.equals(other.kind)
          && this.location.equals(other.location));
    }
    return false;
  }

  /**
   * checks if a boat is still alive
   *
   * @param board board to check
   * @return whether boat is sunk or alive
   */
  public String checkStatus(Board board) {
    for (Coord c : this.location) {
      if (board.board[c.y][c.x] != 'H') {
        return "alive";
      }
    }
    return "sunk";
  }
}
