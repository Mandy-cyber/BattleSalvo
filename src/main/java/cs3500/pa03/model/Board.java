package cs3500.pa03.model;

import java.util.List;

/**
 * class to represent a board
 */
public class Board {

  /**
   * field for the 2D array of char of board
   */
  public char[][] board;

  /**
   * field for the boards list of ships
   */
  public List<Ship> ships;


  /**
   * empty constructor for a board
   */
  public Board() {
  }

  /**
   * calculates the number of ships left on a board
   *
   * @return number of ships left
   */
  public int numOfShipsLeft() {
    int count = 0;
    for (Ship s : this.ships) {
      if (s.checkStatus(this).equals("alive")) {
        count++;
      }
    }
    return count;
  }

  /**
   * sees if a coordinate hits a ship
   *
   * @param c coordinate
   * @return whether a ship has been hit
   */
  public boolean checkHit(Coord c) {
    char look = this.board[c.y][c.x];
    return look == 'S';
  }

  /**
   * updates the char on a board
   *
   * @param c      coorduinate to updayte
   * @param update what to update it to
   */
  public void updateBoard(Coord c, char update) {
    this.board[c.y][c.x] = update;
  }

  /**
   * calculates the number of empty spaces left on a board
   *
   * @return the number of empty spaces on a board
   */
  public int numEmptySpaces() {
    int count = 0;
    for (int row = 0; row < this.board.length; row++) {
      for (int column = 0; column < this.board[0].length; column++) {
        if (this.board[row][column] == '0') {
          count++;
        }
      }
    }
    return count;
  }
}
