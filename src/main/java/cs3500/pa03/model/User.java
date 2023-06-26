package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class to represent user player
 */
public class User implements Player {

  /**
   * field to hold the ai board
   */
  public Board myBoard;
  /**
   * field to hold the opponents board
   */
  public Board opponentBoard;

  /**
   * field for board setup
   */
  BoardSetup bs;

  /**
   * field to hold the dummy board
   */
  public Board dummyBoard;

  /**
   * field to hold the list of shots
   */
  public List<Coord> shots;

  /**
   * constructor to make computer
   *
   * @param bs boardstup
   * @param board ai board
   * @param oppponentBoard user board
   * @param dummyBoard dummyboard to keep track of
   */
  public User(BoardSetup bs, Board board, Board oppponentBoard, Board dummyBoard) {
    this.bs = bs;
    this.myBoard = board;
    this.opponentBoard = oppponentBoard;
    this.dummyBoard = dummyBoard;
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return null;
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    this.bs.makeBoard(width, height, this.myBoard);
    return this.bs.addShips(this.myBoard, specifications);
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    return this.shots;
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   *     shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    ArrayList<Coord> listOfHurt = new ArrayList<>();
    for (Coord c : opponentShotsOnBoard) {
      if (this.myBoard.checkHit(c)) {
        listOfHurt.add(c);
      } else if (this.myBoard.board[c.y][c.x] == '0') {
        this.myBoard.updateBoard(c, 'M');
      }
    }
    return listOfHurt;
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    for (Coord c : shotsThatHitOpponentShips) {
      if (this.opponentBoard.checkHit(c)) {
        this.opponentBoard.updateBoard(c, 'H');
        this.dummyBoard.updateBoard(c, 'H');
      }
    }
  }


  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
  }
}
